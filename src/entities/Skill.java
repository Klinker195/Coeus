package entities;

import controller.Controller;

public class Skill {

	private String Name;
	private Controller MainController = Controller.getInstance();
	
	public Skill(String Name) {
		super();
		setName(Name);
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}
	
}
