package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class ItalianDistrictDAOPostgre extends DAOPostgre implements ItalianDistrictDAO {

	public ItalianDistrictDAOPostgre() {
		
	}
	
	public static void main(String args[]) {
	
		ItalianDistrictDAO ItalianDistrictsDAO = new ItalianDistrictDAOPostgre();
	
	ArrayList<String> RegionNamesList = ItalianDistrictsDAO.getAllRegionNames();
	
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


	@Override
	public ArrayList<String> getAllRegionNames() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT \"RegionName\" FROM \"ItalianDistrict\" GROUP BY \"RegionName\"");
			ArrayList<String> tmpList = new ArrayList<String>();
			while(Rs.next()) {
				tmpList.add(Rs.getString("RegionName"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	@Override
	public ArrayList<String> getAllProvinceNameByRegionName(String RegionName) {
		 
		loadDriver();
	
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProvinceName\" FROM \"ItalianDistrict\" WHERE \"RegionName\" = ? GROUP BY \"RegionName\"");
			PrepStm.setString(1, RegionName);
			ResultSet Rs = PrepStm.executeQuery();
			ArrayList<String> TmpList = new ArrayList<String>();
			while(Rs.next()) {
				TmpList.add(Rs.getString("ProvinceName"));
			}
			Conn.close();
			return TmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	
	
	
	@Override
	public LinkedList<String> getAllProvinceAcronymsByRegionName(String RegionName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProvinceAcronym\" FROM \"ItalianDistrict\" WHERE \"RegionName\" = ? GROUP BY \"ProvinceAcronym\"");
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
	
	@Override
	public LinkedList<String> getAllDistrictsByProvinceAcronym(String ProvinceAcronym) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"DistrictName\" FROM \"ItalianDistrict\" WHERE \"ProvinceAcronym\" = ?");
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
