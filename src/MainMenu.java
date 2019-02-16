/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MainMenu extends FeaturePage{
	static Button accountManagement;
	static Button productManagement;
	static Button orderManagement;
	static Button inventoryManagement;
	static Button stockManagement;
	//Main Menu buttons are static because they will always lead to the same place
	
	public MainMenu()
	{
		super();
		accountManagement = new Button("    Account\nManagement");
		productManagement = new Button("    Product\nManagement");
		orderManagement = new Button("      Order\nManagement");
		inventoryManagement = new Button("   Inventory\nManagement");
		stockManagement = new Button("      Stock\nManagement");
	}
	
	/**
	 * Displays all the features of the program in circles as well as admin/staff details at the bottom
	 * 
	 *@param curUser
	 * 				method receiving curUser which is from the Staff class that store the user ID of current logged-in user 
	 * @return Scene : mainMenuScene scene
	 */
	public Scene mainMenuScene(Staff curUser) 
	{
		StackPane background = new StackPane();
		
		BorderPane mainPane = new BorderPane();
		
		HBox topBar = new HBox(10); //The header bar
		topBar.setPadding(new Insets(10, 10, 10, 10));
		topBar.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		topBar.setMinHeight(60);
		
		Label pgTitle = new Label("Stockies - Main Menu");
		pgTitle.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");
		pgTitle.setTextFill(Color.WHITE);
		pgTitle.setMinWidth(1075);
		
		GridPane buttons = new GridPane(); //The buttons in the center of the scene
		
		if (curUser.getUserID().substring(0, 3).equals("ADM")) //Display the admin main menu buttons
		{
			buttons.setVgap(-30);
			buttons.setHgap(-20);
			buttons.setPadding(new Insets(10, 35, 10, 35));
			buttons.setStyle("-fx-background-color: transparent");
			
			makeMenuBtn(accountManagement, "#1AC919", "#1EEA1D"); //Creating the individual menu buttons for admins
			makeMenuBtn(productManagement, "#4286F4", "#5997F9");
			makeMenuBtn(orderManagement, "#AC22D6", "#BE46E2");
			makeMenuBtn(inventoryManagement, "#DB0D70", "#FC2D90");
			makeMenuBtn(stockManagement, "#C40000", "#E81717");
			
			buttons.add(accountManagement, 0, 0);
			buttons.add(productManagement, 1, 1);
			buttons.add(orderManagement, 2, 0);
			buttons.add(inventoryManagement, 3, 1);
			buttons.add(stockManagement, 4, 0);
		}
		else //Display the staff main menu buttons
		{
			buttons.setVgap(-30);
			buttons.setHgap(30);
			buttons.setPadding(new Insets(10, 180, 10, 180));
			buttons.setStyle("-fx-background-color: transparent");
			
			makeMenuBtn(productManagement, "#4286F4", "#5997F9"); //Creating the individual menu buttons for staff
			makeMenuBtn(orderManagement, "#AC22D6", "#BE46E2");
			makeMenuBtn(inventoryManagement, "#DB0D70", "#FC2D90");
			
			buttons.add(productManagement, 1, 1);
			buttons.add(orderManagement, 2, 0);
			buttons.add(inventoryManagement, 3, 1);
		}
		
		GridPane btmBar = new GridPane(); //Bottom bar with user details
		btmBar.setVgap(10);
		btmBar.setHgap(30);
		btmBar.setPadding(new Insets(15, 30, 30, 30));
		btmBar.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		btmBar.setMinHeight(200);
		
		Button logOut = logOutBtn;
		
		ImageView profilePic = new ImageView();
		
		Label btmBarTitle = new Label("Currently Logged In: ");
		btmBarTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-underline: true");
		btmBarTitle.setTextFill(Color.WHITE);
		
		Label userName = new Label(curUser.getUsername());
		userName.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-border-width: 0 0 1 0; -fx-border-color: white; -fx-underline: true");
		userName.setTextFill(Color.WHITE);
		
		Label pos = new Label(curUser.getPos());
		pos.setStyle("-fx-font-size: 22px; -fx-font-weight: bold");
		pos.setTextFill(Color.WHITE);
		
		Label userID = new Label("User ID: " + curUser.getUserID());
		userID.setStyle("-fx-font-size: 18px");
		userID.setTextFill(Color.WHITE);
		
		Label email = new Label("Email: " + curUser.getEmail());
		email.setStyle("-fx-font-size: 18px");
		email.setTextFill(Color.WHITE);
		
		Label phone = new Label("Phone No: " + curUser.getPhoneNo());
		phone.setStyle("-fx-font-size: 18px");
		phone.setTextFill(Color.WHITE);
		
		try
		{
			FileInputStream imgStream = new FileInputStream(BACKGROUND_IMG);
			Image image = new Image(imgStream, 1200, 800, false, false);
			ImageView bgImageView = new ImageView(image);
			background.getChildren().add(bgImageView);
			
			imgStream = new FileInputStream(curUser.getProfileImgSrc());
			image = new Image(imgStream, 170, 170, false, false);
			profilePic = new ImageView(image);
		}
		catch (FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: Image not found");
		}
		
		topBar.getChildren().addAll(pgTitle, logOut);
		
		btmBar.add(btmBarTitle, 0, 0);
		btmBar.add(profilePic, 0, 1, 1, 5);
		btmBar.add(userName, 1, 1);
		btmBar.add(pos, 1, 2);
		btmBar.add(userID, 1, 4);
		btmBar.add(email, 1, 5);
		btmBar.add(phone, 2, 5);
		
		mainPane.setTop(topBar);
		mainPane.setCenter(buttons);
		mainPane.setBottom(btmBar);
		background.getChildren().add(mainPane);
		
		Scene scene = new Scene(background);
		return scene;
	}
	
	/**
	 * To create individual menu buttons
	 * @param menuBtn
	 * 				method receiving the button to be styled
	 * @param baseColor
	 * 				method receiving the hashcode for the "original" colour of the button
	 * @param hoverColor
	 * 				method receiving the hashcode for the colour the appears when the mouse hovers over it 
	 */
	public void makeMenuBtn(Button menuBtn, String baseColor, String hoverColor) 
	{
		DropShadow shadow = new DropShadow();
		
		menuBtn.setPrefSize(240, 240);
		menuBtn.setWrapText(true);
		menuBtn.setStyle("-fx-background-radius: 300px;" //Original appearance
				+ "-fx-text-align: center;"
				+ "-fx-font-size: 20px;"
				+ "-fx-background-color: " + baseColor + ";"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight:bold;"
				+ "-fx-border-color: white;"
				+ "-fx-border-width: 5px;"
				+ "-fx-border-radius: 300px");
		
		menuBtn.setOnMouseEntered(e -> {
			menuBtn.setEffect(shadow);
			menuBtn.setStyle("-fx-background-radius: 300px;" //On hover appearance
					+ "-fx-font-size: 22px;"
					+ "-fx-background-color: " + hoverColor + ";"
					+ "-fx-text-fill: white;"
					+ "-fx-font-weight:bold;"
					+ "-fx-border-color: white;"
					+ "-fx-border-width: 5px;"
					+ "-fx-border-radius: 300px");
		});
		
		menuBtn.setOnMouseExited(e -> {
			menuBtn.setEffect(null);
			menuBtn.setStyle("-fx-background-radius: 300px;" //Mouse exit appearance
					+ "-fx-font-size: 20px;"
					+ "-fx-background-color: " + baseColor + ";"
					+ "-fx-text-fill: white;"
					+ "-fx-font-weight:bold;"
					+ "-fx-border-color: white;"
					+ "-fx-border-width: 5px;"
					+ "-fx-border-radius: 300px");
		});
	}
}
