/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Login {
	final String BACKGROUND_IMG = "images/Background.jpg"; //Fix the background image URL
	final String LOGO_IMG = "images/LogoTransparent.png";
	
	Button logIn; //Button defined as global because it links to a page outside this class
	TextField userName; //These variables are global because they are used by multiple methods
	PasswordField passWord; 
	ToggleGroup role;
	Label message;
	
	public Login()
	{
		logIn = new Button("Log In");
		userName = new TextField();
		passWord = new PasswordField(); 
		role = new ToggleGroup();
		message = new Label();
	}
	
	/**
	 * 
	 * @return Scene : loginScene scene
	 * 
	 * @throws FileNotFoundException
	 */
	public Scene loginScene() throws FileNotFoundException
	{
		StackPane background = new StackPane();
		GridPane fields = new GridPane();
		fields.setHgap(25);
		fields.setVgap(25);
		fields.setTranslateX(140);
		fields.setTranslateY(140);
		
		FileInputStream imgStream = new FileInputStream(BACKGROUND_IMG);
		Image image = new Image(imgStream, 1200, 800, false, false);
		ImageView bgImageView = new ImageView(image);
		
		imgStream = new FileInputStream(LOGO_IMG);
		image = new Image(imgStream);
		ImageView logoImageView = new ImageView(image);
		
		Rectangle loginBox = new Rectangle();
		loginBox.setWidth(1000);
		loginBox.setHeight(600);
		loginBox.setX(100);
		loginBox.setY(100);
		loginBox.setFill(new Color(1.0, 1.0, 1.0, 0.9));
		
		Text user = new Text("Username: ");
		user.setFont(Font.font("Verdana", 25));
		Text pass = new Text("Password: ");
		pass.setFont(Font.font("Verdana", 25));
		userName.setMinWidth(400);
		userName.setMinHeight(50);
		userName.setStyle("-fx-font-size: 20px");
		passWord.setMinWidth(400);
		passWord.setMinHeight(50);
		passWord.setStyle("-fx-font-size: 20px");
		
		message.setFont(Font.font("Verdana", 15));
		message.setTextFill(Color.RED);
		
		RadioButton admin = new RadioButton("Admin");
		admin.setFont(Font.font("Verdana", 20));
		admin.setToggleGroup(role);
		admin.setSelected(true);
		RadioButton staff = new RadioButton("Staff");
		staff.setFont(Font.font("Verdana", 20));
		staff.setToggleGroup(role);
		
		logIn.setFont(Font.font("Verdana", 20));
		logIn.setMinWidth(150);
		logIn.setMinHeight(50);
		
		fields.add(logoImageView, 0, 0, 2, 10);
		fields.add(user, 3, 1);
		fields.add(pass, 3, 5);
		fields.add(userName, 3, 2, 2, 1);
		fields.add(passWord, 3, 6, 2, 1);
		fields.add(message, 3, 7, 7, 1);
		fields.add(admin, 3, 8);
		fields.add(staff, 4, 8);
		fields.add(logIn, 3, 9, 2, 1);
		fields.setHalignment(logIn, HPos.CENTER);
		fields.setHalignment(admin, HPos.CENTER);
		fields.setHalignment(staff, HPos.CENTER);
		
		background.getChildren().addAll(bgImageView, loginBox, fields);
		
		Scene scene = new Scene(background);
		return scene;
	}
	
	/**
	 * Returns true if username & password exist and match, else returns false
	 * 
	 * @param staffList
	 * 			method receiving array list which contains all the information of the staffs
	 * @param adminList
	 * 			method receiving array list which contains all the information of the admins
	 * @return	boolean
	 */
	public boolean loginDetailsCorrect(ArrayList<Staff> staffList, ArrayList<Admin> adminList) 
	{
		if (userName.getText().isEmpty() || passWord.getText().isEmpty()) //Checks if username or password field is empty
		{
			message.setText("One or more fields have not been filled. \nPlease fill in all fields before continuing.");
			return false;
		}
		
		else
		{
			String username = userName.getText();
			String password = passWord.getText();
			RadioButton roleChoice = (RadioButton) role.getSelectedToggle();
			String roleText = roleChoice.getText();
			boolean userFound = false;
			boolean passCorrect = false;
			
			if (roleText.equals("Staff")) //If the staff role is selected
			{
				for (int i = 0; i < staffList.size(); i++)
				{
					if (username.equals(staffList.get(i).getUsername()) == true) //If a matching username is found in the arraylist
					{
						userFound = true;
						if (password.equals(staffList.get(i).getPassword())) //If the password matches the username
						{
							passCorrect = true;
						}
						
						else
						{
							passCorrect = false;
						}
						break;
					}
				}
			}
			
			else if (roleText.equals("Admin")) //If the admin role is selected
			{
				for (int i = 0; i < adminList.size(); i++)
				{
					if (username.equals(adminList.get(i).getUsername())) //If a matching username is found in the arraylist
					{
						userFound = true;
						if (password.equals(adminList.get(i).getPassword())) //If the password matches the username
						{ 
							passCorrect = true;
						}
						
						else
						{
							passCorrect = false;
						}
						break;
					}
				}
			}
			
			if (userFound)
			{
				if (passCorrect)
				{
					message.setText(" "); //Reset the error message if all details are correct
					return true;
				}
				else
				{
					message.setText("Incorrect password");
					return false;
				}
			}
			
			else
			{
				message.setText("No such user found. \nDid you select the correct role?");
				return false;
			}
		}
	}// end method
}
