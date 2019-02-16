/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

import javax.swing.JOptionPane;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;

public class FeaturePage {
	Button backBtn;
	static Button menuBtn; //Menu and Log Out buttons are static because every 'instance' of them has the same function
	static Button logOutBtn;
	final String BACKGROUND_IMG = "images/Background.jpg"; //Fix the background image URL
	
	public FeaturePage()
	{
		backBtn = new Button("Back");
		menuBtn = new Button("Main Menu");
		logOutBtn = new Button("Log Out");
		
		DropShadow shadow = new DropShadow();
		
		backBtn.setPrefWidth(80);
		menuBtn.setPrefWidth(120);
		logOutBtn.setPrefWidth(80);
		
		backBtn.setStyle("-fx-background-color: #1AC919;"
				+ "-fx-text-fill: white; -fx-font-weight:bold");
		
		backBtn.setOnMouseEntered(e -> {
				backBtn.setText("<< Back");
				backBtn.setEffect(shadow);
				backBtn.setStyle("-fx-background-color: #1EEA1D;"
						+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
		
		backBtn.setOnMouseExited(e -> {
			backBtn.setText("Back");
			backBtn.setEffect(null);
			backBtn.setStyle("-fx-background-color: #1AC919;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
		
		menuBtn.setStyle("-fx-background-color: #1AC919;"
				+ "-fx-text-fill: white; -fx-font-weight:bold");
		
		menuBtn.setOnMouseEntered(e -> {
				menuBtn.setEffect(shadow);
				menuBtn.setStyle("-fx-background-color: #1EEA1D;"
						+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
		
		menuBtn.setOnMouseExited(e -> {
			menuBtn.setEffect(null);
			menuBtn.setStyle("-fx-background-color: #1AC919;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
		
		logOutBtn.setStyle("-fx-background-color: #1AC919;"
				+ "-fx-text-fill: white; -fx-font-weight:bold");
		
		logOutBtn.setOnMouseEntered(e -> {
			logOutBtn.setEffect(shadow);
			logOutBtn.setStyle("-fx-background-color: #1EEA1D;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
		
		logOutBtn.setOnMouseExited(e -> {
			logOutBtn.setEffect(null);
			logOutBtn.setStyle("-fx-background-color: #1AC919;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
	}
	
	/**
	 * Creates a VBox with three buttons - 'Back', 'Main Menu' and 'Log Out'
	 * 
	 * @return HBox : menuBtnList
	 */
	public HBox createInterfaceBtns() 
	{	
		HBox menuBtnList = new HBox(10);
		menuBtnList.getChildren().addAll(backBtn, menuBtn, logOutBtn);
		return menuBtnList;
	}
}
