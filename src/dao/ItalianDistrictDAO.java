package dao;

import java.util.ArrayList;
import java.util.LinkedList;

public interface ItalianDistrictDAO {

	ArrayList<String> getAllRegionNames();

	ArrayList<String> getAllProvinceNameByRegionName(String RegionName);

	LinkedList<String> getAllProvinceAcronymsByRegionName(String RegionName);

	LinkedList<String> getAllDistrictsByProvinceAcronym(String ProvinceAcronym);

}