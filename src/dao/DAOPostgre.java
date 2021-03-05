package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOPostgre {

	/**
	 * Tries to load the Postgre Driver.
	 * @author CoeusDevTeam
	 */
	protected void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("Class Not Found:\n " + e);
		}
	}

	/**
	 * Tries to establish a connection with a certain database based on URL, username and password.
	 * @author CoeusDevTeam
	 * @return A Connection object to a certain database.
	 * @see Connection
	 */
	protected Connection tryConnection() {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:postgresql://coeusdb.c2p2pfdwi5ds.us-east-2.rds.amazonaws.com:5432/postgres", "postgres", "postgres");
			return Conn;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
}
