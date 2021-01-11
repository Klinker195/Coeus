package entities;

public class Skill {

	private int ID;
	private String Name;
	
	public Skill(int ID, String Name) {
		super();
		this.ID = ID;
		this.Name = Name;
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
	
}
