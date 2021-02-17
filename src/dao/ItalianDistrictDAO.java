package dao;

import java.util.ArrayList;
import java.util.LinkedList;

public interface ItalianDistrictDAO {

	ArrayList<String> getAllRegionNames();

	ArrayList<String> getAllProvinceNameByRegionName(String RegionName);

	ArrayList<String> getAllProvinceAcronymsByRegionName(String RegionName);

	ArrayList<String> getAllDistrictsByProvinceAcronym(String ProvinceAcronym);
	
	ArrayList<String> getAllDistrictsByProvinceName(String ProvinceName);
	
	String getDistrictCodeByProvinceAndDistrictName(String ProvinceName, String DistrictName);

}