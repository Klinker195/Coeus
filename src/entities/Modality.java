package entities;

import controller.Controller;

public class Modality {

	private String Name;
	
	private Controller MainController = Controller.getInstance();
	
	public Modality(String Name) {
		this.Name = Name;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
}
