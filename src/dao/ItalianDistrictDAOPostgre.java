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

	/**
	 * Get all region names from the database.
	 * @author CoeusDevTeam
	 * @return A String ArrayList of all the region names fetched.
	 */
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
	
	/**
	 * Get all province names from the database based on the region name.
	 * @author CoeusDevTeam
	 * @param RegionName -> A String representing the region name.
	 * @return A String ArrayList of all the province names fetched.
	 */
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
	
	/**
	 * Get all province acronyms from the database based on the region name.
	 * @author CoeusDevTeam
	 * @param RegionName -> A String representing the region name.
	 * @return A String ArrayList of all the province acronyms fetched.
	 */
	@Override
	public ArrayList<String> getAllProvinceAcronymsByRegionName(String RegionName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProvinceAcronym\" FROM \"ItalianDistrict\" WHERE \"RegionName\" = ? GROUP BY \"ProvinceAcronym\"");
			PrepStm.setString(1, RegionName);
			ResultSet Rs = PrepStm.executeQuery();
			ArrayList<String> tmpList = new ArrayList<String>();
			while(Rs.next()) {
				tmpList.add(Rs.getString("ProvinceAcronym"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}

	/**
	 * Get all districts from the database based on the province acronym.
	 * @author CoeusDevTeam
	 * @param ProvinceAcronym -> A String representing the province acronym.
	 * @return A String ArrayList of all the districts fetched.
	 */
	@Override
	public ArrayList<String> getAllDistrictsByProvinceAcronym(String ProvinceAcronym) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"DistrictName\" FROM \"ItalianDistrict\" WHERE \"ProvinceAcronym\" = ?");
			PrepStm.setString(1, ProvinceAcronym);
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
	
	/**
	 * Get all districts from the database based on the province name.
	 * @author CoeusDevTeam
	 * @param ProvinceName -> A String representing the province name.
	 * @return A String ArrayList of all the districts fetched.
	 */
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
	
	/**
	 * Get district code from the database based on province name and district name.
	 * @author CoeusDevTeam
	 * @param ProvinceName -> A String representing the province name.
	 * @param DistrictName -> A String representing the district name.
	 * @return A String of the fetched district code.
	 */
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
