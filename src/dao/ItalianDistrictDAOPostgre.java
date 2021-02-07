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
	
//	public static void main(String args[]) {
//	
//		ItalianDistrictDAO ItalianDistrictsDAO = new ItalianDistrictDAOPostgre();
//	
//	ArrayList<String> RegionNamesList = ItalianDistrictsDAO.getAllRegionNames();
//	
//	System.out.format(" [LISTA REGIONI ITALIANI]\n");
//	for(String s : RegionNamesList) {
//		System.out.println(" " + s);
//	}
//	System.out.format("\n\n");
//	
//	LinkedList<String> ProvinceAcronymsList = ItalianDistrictsDAO.getAllProvinceAcronymsByRegionName("Campania");
//	
//	System.out.format(" [LISTA ACRONIMI PROVINCE ITALIANE]\n");
//	for(String s : ProvinceAcronymsList) {
//		System.out.println(" " + s);
//	}
//	System.out.format("\n\n");
//	
//	LinkedList<String> DistrictNamesList = ItalianDistrictsDAO.getAllDistrictsByProvinceAcronym("NA");
//	
//	System.out.format(" [LISTA COMUNI ITALIANI]\n");
//	for(String s : DistrictNamesList) {
//		System.out.println(" " + s);
//	}
//	System.out.format("\n\n");
//	
//}


	@Override
	public ArrayList<String> getAllRegionNames() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT \"RegionName\" FROM \"ItalianDistrict\" GROUP BY \"RegionName\" ORDER BY \"RegionName\" ASC");
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
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProvinceName\" FROM \"ItalianDistrict\" WHERE \"RegionName\" = ? GROUP BY \"ProvinceName\" ORDER BY \"ProvinceName\" ASC");
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
	
	@Override
	public ArrayList<String> getAllDistrictsByProvinceName(String ProvinceName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"DistrictName\" FROM \"ItalianDistrict\" WHERE \"ProvinceName\" = ?");
			PrepStm.setString(1, ProvinceName);
			ResultSet Rs = PrepStm.executeQuery();
			ArrayList<String> tmpList = new ArrayList<String>();
			while(Rs.next()) {
				tmpList.add(Rs.getString("DistrictName"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public String getDistrictCodeByProvinceAndDistrictName(String ProvinceName, String DistrictName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"DistrictCode\"\r\n"
					+ "FROM public.\"ItalianDistrict\"\r\n"
					+ "WHERE \"ProvinceName\" = ? AND \"DistrictName\" = ?");
			PrepStm.setString(1, ProvinceName);
			PrepStm.setString(2, DistrictName);
			ResultSet Rs = PrepStm.executeQuery();
			String DistrictCode = "";
			while(Rs.next()) {
				DistrictCode = Rs.getString("DistrictCode");
			}
			Conn.close();
			return DistrictCode;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	
}
