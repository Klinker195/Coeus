package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Controller;
import entities.Employee;
import entities.User;
import entities.UserAdmin;
import entities.UserFounder;
import entities.UserStandard;

public class UserDAOPostgre extends DAOPostgre implements UserDAO {

	private Controller MainController = Controller.getInstance();;
	
	public UserDAOPostgre() {
		
	}
	
	/**
	 * Checks the existance of a certain user in the database based on the inserted CF.
	 * @author CoeusDevTeam
	 * @param CF -> A string representing the employee's CF associated with the account.
	 * @return True if the user exists, otherwise false.
	 */
	@Override
	public boolean existsByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"User\"\r\n"
					+ "WHERE \"EmployeeCF\" = ?\r\n"
					+ "ORDER BY \"CF\" ASC ");
			PrepStm.setString(1, CF);
			ResultSet rs = PrepStm.executeQuery();
			
			if(rs.isBeforeFirst()) {
				Conn.close();
				return true;
			} else {
				Conn.close();
				return false;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
		return false;
		
	}
	
	@Override
	public boolean isFounder(String EmployeeCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM \"User\" WHERE \"Founder\" = true AND \"EmployeeCF\" = ?");
			PrepStm.setString(1, EmployeeCF);
			ResultSet Rs = PrepStm.executeQuery();
			if(Rs.isBeforeFirst()) {
				Conn.close();
				return true;
			} else {
				Conn.close();
				return false;
			}
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return false;
	}
	
	@Override
	public boolean isAdmin(String EmployeeCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM \"User\" WHERE \"Admin\" = true AND \"EmployeeCF\" = ?");
			PrepStm.setString(1, EmployeeCF);
			ResultSet Rs = PrepStm.executeQuery();
			if(Rs.isBeforeFirst()) {
				Conn.close();
				return true;
			} else {
				Conn.close();
				return false;
			}
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return false;
	}
	
	@Override
	public void promote(String EmployeeCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("UPDATE public.\"User\"\r\n"
					+ "	SET \"Admin\" = true\r\n"
					+ "	WHERE \"EmployeeCF\" = ?;");
			PrepStm.setString(1, EmployeeCF);

			PrepStm.executeUpdate();
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public void demote(String EmployeeCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("UPDATE public.\"User\"\r\n"
					+ "	SET \"Admin\" = false\r\n"
					+ "	WHERE \"EmployeeCF\" = ?;");
			PrepStm.setString(1, EmployeeCF);

			PrepStm.executeUpdate();
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public boolean searchFounder() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT * FROM \"User\" WHERE \"Founder\" = true");
			if(!Rs.next()) {
				Conn.close();
				return true;
			} else {
				Conn.close();
				return false;
			}
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return false;
	}
	
	@Override
	public void insertStandardUser(User NewUser) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"User\"(\r\n"
					+ "	\"EmployeeCF\", \"Password\", \"Admin\", \"Founder\")\r\n"
					+ "	VALUES (?, ?, ?, ?);");
			PrepStm.setString(1, NewUser.getEmployee().getCF());
			PrepStm.setString(2, NewUser.getPassword());
			PrepStm.setBoolean(3, false);
			PrepStm.setBoolean(4, false);
			PrepStm.executeUpdate();
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public void insertUserFounder(User NewFounder) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"User\"(\r\n"
					+ "	\"EmployeeCF\", \"Password\", \"Admin\", \"Founder\")\r\n"
					+ "	VALUES (?, ?, ?, ?);");
			PrepStm.setString(1, NewFounder.getEmployee().getCF());
			PrepStm.setString(2, NewFounder.getPassword());
			PrepStm.setBoolean(3, false);
			PrepStm.setBoolean(4, true);
			PrepStm.executeUpdate();
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public User getUserByCF(String EmployeeCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT *\r\n"
					+ "FROM public.\"User\"\r\n"
					+ "WHERE \"EmployeeCF\" = ?;");
			PrepStm.setString(1, EmployeeCF);
			ResultSet rs = PrepStm.executeQuery();
			Conn.close();
			if(rs.isBeforeFirst()) {
				rs.next();
				if(rs.getBoolean("Founder")) {
					return new UserFounder(MainController.getEmployeeDAO().getEmployeeByCF(EmployeeCF), rs.getString("Password"));
				} else if(rs.getBoolean("Admin")) {
					return new UserAdmin(MainController.getEmployeeDAO().getEmployeeByCF(EmployeeCF), rs.getString("Password"));
				} else {
					return new UserStandard(MainController.getEmployeeDAO().getEmployeeByCF(EmployeeCF), rs.getString("Password"));
				}
			}
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public ArrayList<User> getAllUsers() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"EmployeeUser\"");
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<User> tmpList = new ArrayList<User>();

			Conn.close();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					
					if(rs.getBoolean("Founder")) {
						tmpList.add(new UserFounder(MainController.getEmployeeDAO().getEmployeeByCF(rs.getString("CF")), ""));
					} else if(rs.getBoolean("Admin")) {
						tmpList.add(new UserAdmin(MainController.getEmployeeDAO().getEmployeeByCF(rs.getString("CF")), ""));
					} else {
						tmpList.add(new UserStandard(MainController.getEmployeeDAO().getEmployeeByCF(rs.getString("CF")), ""));
					}
					
				}
				return tmpList;
			} else {
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public String getPasswordByEmployeeCF(String EmployeeCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Password\"\r\n"
					+ "FROM public.\"User\"\r\n"
					+ "WHERE \"EmployeeCF\" = ?;");
			PrepStm.setString(1, EmployeeCF);
			ResultSet rs = PrepStm.executeQuery();
			
			String Password;

			if(rs.isBeforeFirst()) {
				rs.next();
				Password = rs.getString("Password");
				Conn.close();
				return Password;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
}
