package objects;

public class Project {
	
	int ProjID;
	String ProjName;
	String ProjDescription;
	String ProjType;
	String ProjScope;
	int ProjMaxEmployee;
	
	
	public Project(int ProjID, String ProjName, String ProjDescription,String ProjType,
			String ProjScope, int ProjMaxEmployee) {
		super();
		this.ProjID = ProjID;
		this.ProjName = ProjName;
		this.ProjDescription = ProjDescription;
		this.ProjType = ProjType;
		this.ProjScope = ProjScope;
		this.ProjMaxEmployee = ProjMaxEmployee;
	}
	
	public int getProjID() {
		return ProjID;
	}
	public void setProjID(int projID) {
		ProjID = projID;
	}
	public String getProjName() {
		return ProjName;
	}
	public void setProjName(String projName) {
		ProjName = projName;
	}
	public String getProjDescription() {
		return ProjDescription;
	}
	public void setProjDescription(String projDescription) {
		ProjDescription = projDescription;
	}
	public String getProjType() {
		return ProjType;
	}
	public void setProjType(String projType) {
		ProjType = projType;
	}
	public String getProjScope() {
		return ProjScope;
	}
	public void setProjScope(String projScope) {
		ProjScope = projScope;
	}
	public int getProjMaxEmployee() {
		return ProjMaxEmployee;
	}
	public void setProjMaxEmployee(int projMaxEmployee) {
		ProjMaxEmployee = projMaxEmployee;
	}
		
}
