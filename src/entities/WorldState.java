package entities;

import controller.Controller;

public class WorldState {

	private String StateID;
	private String Name;
	
	private Controller MainController = Controller.getInstance();
	
	public WorldState(String stateID, String name) {
		super();
		StateID = stateID;
		Name = name;
	}

	public String getStateID() {
		return StateID;
	}

	public void setStateID(String stateID) {
		StateID = stateID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
}
