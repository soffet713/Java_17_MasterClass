package com.jdbcchallenges;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	private static String USE_SCHEMA = "USE storefront";

	private static int MYSQL_DB_NOT_FOUND = 1049;

	public static void main(String[] args) {

		var dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setUser(System.getenv("MYSQLUSER"));
		dataSource.setPassword(System.getenv("MYSQLPASS"));

		try (Connection conn = dataSource.getConnection()) {
			DatabaseMetaData metaData = conn.getMetaData();
			System.out.println(metaData.getSQLStateType());
			if (!checkSchema(conn)) {
				System.out.println("Storefront schema does not exist.");
				setUpSchema(conn);
			}
			//addColumn(conn, "order_details", "quantity");
		} catch (SQLException e) {
			throw new RuntimeException(e);
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

	private static void setUpSchema(Connection conn) throws SQLException {

		String createSchema = "CREATE SCHEMA storefront";

		String createOrder = """
				CREATE TABLE storefront.order (
				order_id int NOT NULL AUTO_INCREMENT,
				order_date DATETIME NOT NULL,
				PRIMARY KEY (order_id)
				)""";

		String createOrderDetails = """
				CREATE TABLE storefront.order_details (
				order_detail_id int NOT NULL AUTO_INCREMENT,
				item_description text,
				order_id int DEFAULT NULL,
				PRIMARY KEY (order_detail_id),
				KEY FK_ORDERID (order_id),
				CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
				REFERENCES storefront.order (order_id) ON DELETE CASCADE
				)""";

		try (Statement statement = conn.createStatement()) {
			System.out.println("Creating storefront database.");
			statement.execute(createSchema);
			if (checkSchema(conn)) {
				statement.execute(createOrder);
				System.out.println("Successfully created Order.");
				statement.execute(createOrderDetails);
				System.out.println("Successfully created Order Details.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void addColumn(Connection conn, String tableName, String columnName, String dataType)
			throws SQLException {

		String alterOrderDetails = """
				ALTER TABLE storefront.%s
				ADD %s %s NOT NULL;
				""".formatted(tableName, columnName, dataType);

		try (Statement statement = conn.createStatement()) {
			if (checkSchema(conn)) {
				statement.execute(alterOrderDetails);
				System.out.printf("Successfully added %s to %s.".formatted(columnName, tableName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
