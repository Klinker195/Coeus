package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class ItalianDistrictDAOPostgre extends DAOPostgre {

	public ItalianDistrictDAOPostgre() {
		
	}
	
	public static void main(String args[]) {
	
		ItalianDistrictDAOPostgre ItalianDistrictsDAO = new ItalianDistrictDAOPostgre();
	
	LinkedList<String> RegionNamesList = ItalianDistrictsDAO.getAllRegionNames();
	
	System.out.format(" [LISTA REGIONI ITALIANI]\n");
	for(String s : RegionNamesList) {
		System.out.println(" " + s);
	}
	System.out.format("\n\n");
	
	LinkedList<String> ProvinceAcronymsList = ItalianDistrictsDAO.getAllProvinceAcronymsByRegionName("Campania");
	
	System.out.format(" [LISTA ACRONIMI PROVINCE ITALIANE]\n");
	for(String s : ProvinceAcronymsList) {
		System.out.println(" " + s);
	}
	System.out.format("\n\n");
	
	LinkedList<String> DistrictNamesList = ItalianDistrictsDAO.getAllDistrictsByProvinceAcronym("NA");
	
	System.out.format(" [LISTA COMUNI ITALIANI]\n");
	for(String s : DistrictNamesList) {
		System.out.println(" " + s);
	}
	System.out.format("\n\n");
	
}


	public LinkedList<String> getAllRegionNames() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT \"RegionName\" FROM \"ItalianDistrict\" GROUP BY \"RegionName\"");
			LinkedList<String> tmpList = new LinkedList<String>();
			while(Rs.next()) {
				tmpList.addLast(Rs.getString("RegionName"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	public LinkedList<String> getAllProvinceAcronymsByRegionName(String RegionName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProvinceAcronym\" FROM \"ItalianDistricts\" WHERE \"RegionName\" = ? GROUP BY \"ProvinceAcronym\"");
			PrepStm.setString(1, RegionName);
			ResultSet Rs = PrepStm.executeQuery();
			LinkedList<String> tmpList = new LinkedList<String>();
			while(Rs.next()) {
				tmpList.addLast(Rs.getString("ProvinceAcronym"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	public LinkedList<String> getAllDistrictsByProvinceAcronym(String ProvinceAcronym) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"DistrictName\" FROM \"ItalianDistricts\" WHERE \"ProvinceAcronym\" = ?");
			PrepStm.setString(1, ProvinceAcronym);
			ResultSet Rs = PrepStm.executeQuery();
			LinkedList<String> tmpList = new LinkedList<String>();
			while(Rs.next()) {
				tmpList.addLast(Rs.getString("DistrictName"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
}
