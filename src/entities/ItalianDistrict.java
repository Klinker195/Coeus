package entities;

import controller.Controller;

public class ItalianDistrict {

	private String DistrictCode;
	private String DistrictName;
	private String RegionName;
	private String ProvinceName;
	private String ProvinceAcronym;
	
	private Controller MainController = Controller.getInstance();
	
	public ItalianDistrict(String districtCode, String districtName, String regionName, String provinceName,
			String provinceAcronym) {
		super();
		DistrictCode = districtCode;
		DistrictName = districtName;
		RegionName = regionName;
		ProvinceName = provinceName;
		ProvinceAcronym = provinceAcronym;
	}

	public String getDistrictCode() {
		return DistrictCode;
	}

	public void setDistrictCode(String districtCode) {
		DistrictCode = districtCode;
	}

	public String getDistrictName() {
		return DistrictName;
	}

	public void setDistrictName(String districtName) {
		DistrictName = districtName;
	}

	public String getRegionName() {
		return RegionName;
	}

	public void setRegionName(String regionName) {
		RegionName = regionName;
	}

	public String getProvinceName() {
		return ProvinceName;
	}

	public void setProvinceName(String provinceName) {
		ProvinceName = provinceName;
	}

	public String getProvinceAcronym() {
		return ProvinceAcronym;
	}

	public void setProvinceAcronym(String provinceAcronym) {
		ProvinceAcronym = provinceAcronym;
	}
	
}
