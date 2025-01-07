package com.preparedstatementchallenge;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {

	private static String USE_SCHEMA = "USE storefront";
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static int MYSQL_DB_NOT_FOUND = 1049;
	private static String ORDER_INSERT = "INSERT INTO storefront.order (order_date) VALUES (?)";
	private static String ORDER_DETAIL_INSERT = "INSERT INTO storefront.order_details " +
												"(item_description,order_id,quantity) VALUES (?, ?, ?)";

	public static void main(String[] args) {

		var dataSource = new MysqlDataSource();

		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("storefront");

		try {
			dataSource.setContinueBatchOnError(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try (Connection connection = dataSource.getConnection(
				System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {
			addOrdersFromFile(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean checkSchema(Connection conn) throws SQLException {

		try (Statement statement = conn.createStatement()) {
			statement.execute(USE_SCHEMA);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
			System.err.println("Message: " + e.getMessage());

			if (conn.getMetaData().getDatabaseProductName().equals("MySQL")
				&& e.getErrorCode() == MYSQL_DB_NOT_FOUND) {
				return false;
			} else {
				throw e;
			}
		}
		return true;
	}

	private static void insertOrderInfo(Statement statement, String orderDate, String[] orderItems)
			throws SQLException {

		if (orderItems[0] != null) {
			//LocalDateTime orderDate = LocalDateTime.now();
			//String dateText = orderDate.format(dtf);
			System.out.println(orderDate);
			String insertOrder = "INSERT INTO storefront.order (order_date) VALUES ('%s')".formatted(orderDate);
			System.out.println(insertOrder);
			statement.execute(insertOrder, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			int orderId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
			String insertOrderDetails = "INSERT INTO storefront.order_details (item_description,order_id,quantity)" +
										" VALUES ('%s',%d,%d)";

			for (String item : orderItems) {
				String orderDetailsQuery = insertOrderDetails.formatted(item,orderId);
				System.out.println(orderDetailsQuery);
				statement.execute(orderDetailsQuery);
			}
		}
	}

	private static int insertOrder(PreparedStatement ps, Connection conn, String orderDate) throws SQLException {

		int orderId = -1;
		ps.setString(1, orderDate);
		int insertedCount = ps.executeUpdate();
		if (insertedCount > 0) {
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				orderId = generatedKeys.getInt(1);
				System.out.println("Auto-incremented ID: " + orderId);
			}
		}
		return orderId;
	}

	private static void addOrderDetail(PreparedStatement ps, Connection conn, String itemDescription, int orderId,
									   int qty) throws SQLException {

		ps.setString(1, itemDescription);
		ps.setInt(2, orderId);
		ps.setInt(3, qty);
		ps.addBatch();
	}

	private static void deleteOrderDetails(Connection conn, Statement statement, int orderId) throws SQLException {

		try {
			System.out.println("AUTOCOMMIT = " + conn.getAutoCommit());
			conn.setAutoCommit(false);
			String deleteOrderDetail = "DELETE FROM storefront.order_details WHERE order_id=%d"
					.formatted(orderId);
			String deleteOrder = "DELETE FROM storefront.order WHERE order_id=%d".formatted(orderId);
			statement.addBatch(deleteOrderDetail);
			statement.addBatch(deleteOrder);
			int[] results = statement.executeBatch();
			System.out.println(Arrays.toString(results));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
		conn.setAutoCommit(true);
	}

	private static void addOrdersFromFile(Connection conn) throws SQLException {

		List<String> records = null;
		try {
			records = Files.readAllLines(Path.of("Orders.csv"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String lastOrderDate = null;
		int orderId = -1;

		try (PreparedStatement psOrder = conn.prepareStatement(ORDER_INSERT, Statement.RETURN_GENERATED_KEYS);
			 PreparedStatement psOrderDetail = conn.prepareStatement(ORDER_DETAIL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

			conn.setAutoCommit(false);

			for (String record : records) {
				String[] columnValues = record.split(",");
				try {
					if (Objects.equals(columnValues[0], "order")) {
						if (lastOrderDate == null || !lastOrderDate.equals(columnValues[1])) {
							lastOrderDate = columnValues[1];
							orderId = insertOrder(psOrder, conn, lastOrderDate);
						}
					} else {
						addOrderDetail(psOrderDetail, conn, columnValues[2], orderId, Integer.parseInt(columnValues[1]));
					}
				} catch (SQLException e) {
					System.err.printf("%d (%s) %s%n", e.getErrorCode(), e.getSQLState(), e.getMessage());
					System.err.println("Problem: " + psOrder);
					System.err.println("Order: " + record);
				}
			}
			int[] inserts = psOrderDetail.executeBatch();
			int totalInserts = Arrays.stream(inserts).sum();
			System.out.printf("%d (%d) item records added.%n", inserts.length, totalInserts);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		}
	}
}
