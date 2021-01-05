package objects;

public class Project {
	
	int ID;
	String Name;
	String Description;
	String Type;
	String Scope;
	int MaxEmployee;
	
	
	public Project(int ID, String Name, String Description,String Type,
			String Scope, int MaxEmployee) {
		super();
		this.ID = ID;
		this.Name = Name;
		this.Description = Description;
		this.Type = Type;
		this.Scope = Scope;
		this.MaxEmployee = MaxEmployee;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}


	public String getScope() {
		return Scope;
	}


	public void setScope(String scope) {
		Scope = scope;
	}


	public int getMaxEmployee() {
		return MaxEmployee;
	}


	public void setMaxEmployee(int maxEmployee) {
		MaxEmployee = maxEmployee;
	}
		
}
