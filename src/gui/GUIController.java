package gui;

public class GUIController {

	private static GUIController ControllerIstance = null;
	
	private GUIController() {
		// Non è possibile creare un'altra istanza di GUIController
	}
	
	public static void main(String args[]) {
		
		GUIController GuiController = GUIController.getIstance();
		
		GuiController.start();
		
	}
	
	public static GUIController getIstance() {
		if(ControllerIstance == null)
			ControllerIstance = new GUIController();
		
		return ControllerIstance;
	}
	
	public void start() {
		LoginWindow LoginScreen = new LoginWindow();
		LoginScreen.setVisible(true);
	}
}