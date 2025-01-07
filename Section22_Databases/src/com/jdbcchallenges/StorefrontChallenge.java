package com.jdbcchallenges;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class StorefrontChallenge {

	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/storefront?continueBatchOnError=false",
				System.getenv("MYSQLUSER"), System.getenv("MYSQLPASS"));
			 Statement statement = connection.createStatement();
		) {
			//String[] items = {"Laptop","TV","PlayStation","Bowling Ball"};
			//insertOrderInfo(statement,items);
			deleteOrderDetails(connection, statement, 1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static void insertOrderInfo(Statement statement, String[] orderItems)
			throws SQLException {

		if (orderItems[0] != null) {
			LocalDateTime orderDate = LocalDateTime.now();
			String dateText = orderDate.format(dtf);
			System.out.println(dateText);
			String insertOrder = "INSERT INTO storefront.order (order_date) VALUES ('%s')".formatted(dateText);
			System.out.println(insertOrder);
			statement.execute(insertOrder, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			int orderId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
			String insertOrderDetails = "INSERT INTO storefront.order_details (item_description,order_id) VALUES ('%s',%d)";

			for (String item : orderItems) {
				String orderDetailsQuery = insertOrderDetails.formatted(item,orderId);
				System.out.println(orderDetailsQuery);
				statement.execute(orderDetailsQuery);
			}
		}
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
}
