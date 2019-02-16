/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


public class AccountManagement extends FeaturePage
{            
	
	final String STAFF_FILE = "Staff.txt"; //Fixes the files from which to read data
	final String ADMIN_FILE = "Admin.txt";
	
	ArrayList<Staff> staffList = new ArrayList<Staff>(); //array list hold all the staff object
	ArrayList<Admin> adminList = new ArrayList<Admin>(); //array list hold all the admin object
	ArrayList <TextField> tf = new ArrayList <TextField>();   //array list hold edit account text field 
	ArrayList<TextField> tfadd = new ArrayList<TextField>(); // array list hold add account text field 
	ArrayList <Text> txt = new ArrayList <Text>(); // array list hold all the label in the form
	String ProfileImgSrc = "";
	RadioButton btnAdmin = new RadioButton("Admin");
	RadioButton btnStaff = new RadioButton("Staff");
	ArrayList <Button> editBtnList = new ArrayList<Button>(); //hold edit button for adminList
	ArrayList <Button> editBtnList2 = new ArrayList<Button>(); //hold edit button for staffList
	Button savebtn = new Button("Save");
	Label refreshMsg = new Label();
	
	Stage stage = new Stage();
	
	public AccountManagement() 
	{
		//ArrayList for edit account textField
		for(int i = 0; i < 6; i++)
		{
			tf.add(new TextField(""));
		}//end for loop
		
		//ArrayList for add account textField
		for(int i = 0; i < 6; i++)
		{
			tfadd.add(new TextField(""));
		}//end for loop
		
		
		//ArrayList for text
		txt.add(new Text ("UserName: "));
		txt.add(new Text ("Password: "));
		txt.add(new Text ("Position: "));
		txt.add(new Text ("Email address: "));
		txt.add(new Text ("Phone Number: "));
		txt.add(new Text ("Admin code: "));
		
	}
		
	//Method show the main scene in account management class
	//Parameter pass through 2 array list and 1 object
	//It return to the background scene
	public Scene accountListScene(ArrayList<Admin> adminList,ArrayList<Staff> staffList, Staff curUser) 
	{ 
		StackPane background = new StackPane();
//side bar		
		VBox sidebar = new VBox(10);
		sidebar.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		sidebar.setPrefWidth(320);
		sidebar.setPadding(new Insets(10, 10, 10, 10));
		sidebar.setAlignment(Pos.TOP_CENTER);
		
       HBox hbox = new HBox(80);
		
		try
		{
			FileInputStream imgStream = new FileInputStream(BACKGROUND_IMG);
			Image image = new Image(imgStream, 1200, 800, false, false);
			ImageView bgImageView = new ImageView(image);
			background.getChildren().add(bgImageView);
		}
		catch (FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: " + BACKGROUND_IMG + " not found");
		}
		
		Label title = new Label("User Setting");
		Label addinfo = new Label("For creating a new account, please press ADD button to enter new account information. Thankyou.");
		addinfo.setStyle("-fx-font-size: 16px");
		addinfo.setTextFill(Color.WHITE);
		addinfo.setWrapText(true);
		addinfo.setTextAlignment(TextAlignment.CENTER);
		Label deleteinfo = new Label("For deleting a new account, please press DELETE button to enter new account information. Thankyou.");
		deleteinfo.setStyle("-fx-font-size: 16px");
		deleteinfo.setTextFill(Color.WHITE);
		deleteinfo.setWrapText(true);
		deleteinfo.setTextAlignment(TextAlignment.CENTER);
		Label editinfo = new Label("For Editing an account, please press EDIT button to alter the account information. Thankyou.");
		editinfo.setStyle("-fx-font-size: 16px");
		editinfo.setTextFill(Color.WHITE);
		editinfo.setWrapText(true);
		editinfo.setTextAlignment(TextAlignment.CENTER);
		
		Label myaccount = new Label("My account");
		
		ScrollPane userGridScroll = new ScrollPane(); //The scrollpane containing gridpane containing the all the user list
		userGridScroll.setMaxHeight(900);
	 	userGridScroll.setPrefWidth(300);
		userGridScroll.setStyle("-fx-background: transparent; -fx-background-color: white;");
		userGridScroll.setPannable(true);
		
		
		
		title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		title.setTextFill(Color.WHITE);
		title.setWrapText(true);
		title.setTextAlignment(TextAlignment.CENTER);
		
		
		DropShadow shadow = new DropShadow();
		Button addBtn = new Button("Add");
		Button deleteBtn = new Button("Delete");
		
		
		addBtn.setPrefWidth(80);
		deleteBtn.setPrefWidth(80);
		
		
		
		addBtn.setStyle("-fx-background-color: #1AC919;"
				+ "-fx-text-fill: white; -fx-font-weight:bold");
		
		deleteBtn.setStyle("-fx-background-color: #1AC919;"
				+ "-fx-text-fill: white; -fx-font-weight:bold");
		
		
		
		
		
		addBtn.setOnMouseEntered(e -> {
			addBtn.setEffect(shadow);
			addBtn.setStyle("-fx-background-color: #1EEA1D;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
	});
		
		addBtn.setOnMouseExited(e -> {
			addBtn.setEffect(null);
			addBtn.setStyle("-fx-background-color: #1AC919;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
		});  
		
		deleteBtn.setOnMouseEntered(e -> {
			deleteBtn.setEffect(shadow);
			deleteBtn.setStyle("-fx-background-color: #1EEA1D;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
	});
		
		deleteBtn.setOnMouseExited(e -> {
			deleteBtn.setEffect(null);
			deleteBtn.setStyle("-fx-background-color: #1AC919;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
		
		

		
		
		addBtn.setOnAction( e ->
		{
			addUser(adminList, staffList);
			 
		});
		
		deleteBtn.setOnAction(e ->
		{
			try 
			{
				deleteUser(adminList, staffList );
			} catch (FileNotFoundException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		
	      
//Show current logged in account in the account management scene	
	    GridPane showAccount = new GridPane();
	    
	    showAccount.setVgap(10);
	    showAccount.setHgap(30);
	    showAccount.setPadding(new Insets(15, 30, 30, 30));
	    showAccount.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
	    showAccount.setMinHeight(200);
		
	    ImageView profilePic1 = new ImageView();
	    
	    try
		{
		
		FileInputStream imgStream = new FileInputStream(curUser.getProfileImgSrc());
		Image image = new Image(imgStream, 170, 170, false, false);
		profilePic1 = new ImageView(image);
		}
		catch (FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: Image not found");
		}
		
		Label btmBarTitle = new Label("Currently Logged In: ");
		btmBarTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-underline: true");
		btmBarTitle.setTextFill(Color.WHITE);
		
		Label userName1 = new Label(curUser.getUsername());
		userName1.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-border-width: 0 0 1 0; -fx-border-color: white; -fx-underline: true");
		userName1.setTextFill(Color.WHITE);
		
		Label pos1 = new Label(curUser.getPos());
		pos1.setStyle("-fx-font-size: 22px; -fx-font-weight: bold");
		pos1.setTextFill(Color.WHITE);
		
		Label userID1 = new Label("User ID: " + curUser.getUserID());
		userID1.setStyle("-fx-font-size: 18px");
		userID1.setTextFill(Color.WHITE);
		
		Label email1 = new Label("Email: " + curUser.getEmail());
		email1.setStyle("-fx-font-size: 18px");
		email1.setTextFill(Color.WHITE);
		
		Label phone = new Label("Phone No: " + curUser.getPhoneNo());
		phone.setStyle("-fx-font-size: 18px");
		phone.setTextFill(Color.WHITE);
		

		GridPane userList = new GridPane(); //The gridpane hold the user list
		userList.setVgap(10);
		userList.setHgap(50);
		userList.setStyle("-fx-background-color: transparent");
		userList.add(myaccount,0,0);
		
		try
		{
			genAccountlist(userList,adminList,staffList);
			
			for (int j=0; j<editBtnList.size(); j++) 
			{ 
				final int k = j;

			   editBtnList.get(j).setOnAction(e ->
			{
				try {
					editAcc(adminList,staffList,editBtnList,k,"Admin");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}); 
			}//end for loop
			
			
			for (int j=0; j<editBtnList2.size(); j++) 
			{
				final int k = j;

			   editBtnList2.get(j).setOnAction(e ->
			{
				try {
					editAcc(adminList,staffList,editBtnList2,k,"Staff");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}); 
			   
		   }// end for loop
		}
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: Admin Image not found");
		}

		
		
		userGridScroll.setContent(userList);
		
		
		showAccount.add(btmBarTitle, 0, 0);
		showAccount.add(profilePic1, 0, 1, 1, 5);
		showAccount.add(userName1, 1, 1);
		showAccount.add(pos1, 1, 2);
		showAccount.add(userID1, 1, 4);
		showAccount.add(email1, 1, 5);
		showAccount.add(phone, 2, 5);
		
		VBox showPane = new VBox(); 
	    
		sidebar.getChildren().addAll(createInterfaceBtns(),title,addinfo,addBtn,deleteinfo,deleteBtn,editinfo);
	    hbox.getChildren().add(sidebar);
	    showPane.getChildren().add(showAccount);
	    showPane.getChildren().add(userGridScroll);
	    hbox.getChildren().add(showPane);
		background.getChildren().addAll(hbox);
		
		Scene scene = new Scene(background);
		return scene; 
	}	
	
	
	
	/**
	 * This method generate list for all accounts
	 * 
	 * @param userPane
	 *         pane hold the user scene
	 * @param adminList
	 *        array list that hold admin object
	 * @param staffList
	 *        array list that hold staff object
	 * @throws FileNotFoundException
	 * @return void
	 */
	public void genAccountlist(GridPane userPane,ArrayList<Admin> adminList,ArrayList<Staff> staffList) throws FileNotFoundException  
	{
		editBtnList.clear();
		editBtnList2.clear();
		
		Label OtherAcc = new Label("All Account");
		OtherAcc.setStyle("-fx-font-size: 16px; -fx-underline: true");
		OtherAcc.setTextFill(Color.BLACK);
		
		userPane.add(OtherAcc,0,0);
		
		  int rowCtr = 1, colCtr = 0;

		for(int i = 0; i < adminList.size(); i++)
		{	
			Button editBtn = new Button("Edit");
			DropShadow shadow = new DropShadow();
			editBtn.setPrefWidth(80);
			
			editBtn.setStyle("-fx-background-color: #1AC919;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
			
			editBtn.setOnMouseEntered(e -> {
				editBtn.setEffect(shadow);
				editBtn.setStyle("-fx-background-color: #1EEA1D;"
						+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
			
			editBtn.setOnMouseExited(e -> {
				editBtn.setEffect(null);
				editBtn.setStyle("-fx-background-color: #1AC919;"
						+ "-fx-text-fill: white; -fx-font-weight:bold");
			});       
			
			    userPane.add(new ImageView (new Image (new FileInputStream (((Admin) adminList.get(i)).getProfileImgSrc()), 80, 80, false, false)) ,colCtr, rowCtr);
				
				
				Label Username = new Label(((Admin) adminList.get(i)).getUsername());
				Username.setStyle("-fx-font-size: 16px; ");
				Username.setTextFill(Color.BLACK);
				userPane.add(Username,colCtr+1, rowCtr);
				
				
				
				Label Pos = new Label(((Admin) adminList.get(i)).getPos());
				Pos.setStyle("-fx-font-size: 16px; ");
				Pos.setTextFill(Color.BLACK);
				userPane.add(Pos, colCtr+2, rowCtr);
				
				userPane.add(editBtn, colCtr+5, rowCtr);
				editBtnList.add(editBtn);
				
				colCtr = 0;
				rowCtr++;

		}//end for loop
		
		for(int i = 0; i < staffList.size(); i++)
		{	
			Button editBtn = new Button("Edit");
			DropShadow shadow = new DropShadow();
			editBtn.setPrefWidth(80);
			
			editBtn.setStyle("-fx-background-color: #1AC919;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
			
			editBtn.setOnMouseEntered(e -> {
				editBtn.setEffect(shadow);
				editBtn.setStyle("-fx-background-color: #1EEA1D;"
						+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
			
			editBtn.setOnMouseExited(e -> {
				editBtn.setEffect(null);
				editBtn.setStyle("-fx-background-color: #1AC919;"
						+ "-fx-text-fill: white; -fx-font-weight:bold");
			});      
			
			    userPane.add(new ImageView (new Image (new FileInputStream (((Staff)staffList.get(i)).getProfileImgSrc()), 80, 80, false, false)) ,colCtr, rowCtr);
				
				
				Label Username = new Label(((Staff) staffList.get(i)).getUsername());
				Username.setStyle("-fx-font-size: 16px; ");
				Username.setTextFill(Color.BLACK);
				userPane.add(Username,colCtr+1, rowCtr);
				
				Label Pos = new Label(((Staff) staffList.get(i)).getPos());
				Pos.setStyle("-fx-font-size: 16px; ");
				Pos.setTextFill(Color.BLACK);
				userPane.add(Pos, colCtr+2, rowCtr);
				
				userPane.add(editBtn, colCtr+5, rowCtr);
				editBtnList2.add(editBtn);
				
				colCtr = 0;
				rowCtr++;

		}//end for loop
		
		userPane.add(refreshMsg, 0, rowCtr + 1, 3, 1);
	}
	
	
  
	/**
	 * This method handling the action when the add button is pressed
	 * 
	 * @param adminList
	 *        array list that hold admin object
	 * @param staffList
	 *        array list that hold staff object
	 * 
	 * @return void
	 */
	public void addUser(ArrayList<Admin> adminList, ArrayList<Staff> staffList)
	{
		Stage stage = new Stage();
		
        StackPane background = new StackPane();
		
		try{
			FileInputStream imgStream = new FileInputStream(BACKGROUND_IMG);
			Image image = new Image(imgStream, 1200, 800, false, false);
			ImageView bgImageView = new ImageView(image);
			background.getChildren().add(bgImageView);
		}
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: " + BACKGROUND_IMG + " not found");
		}
		
		Label pgTitle = new Label("Add User Form");
		pgTitle.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");
		
		Rectangle loginBox = new Rectangle();
		loginBox.setWidth(1000);
		loginBox.setHeight(700);
		loginBox.setX(100);
		loginBox.setY(100);
		loginBox.setFill(new Color(1.0, 1.0, 1.0, 0.9));
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(25);
		gridPane.setVgap(25);
		gridPane.setTranslateX(140);
		gridPane.setTranslateY(120);
		gridPane.getChildren().clear();
		
		ImageView img = new ImageView();
		gridPane.setValignment(img, VPos.TOP);
		Label msg = new Label();
		Label errorChecking = new Label();
		errorChecking.setWrapText(true);
		msg.setWrapText(true);
		msg.setMaxWidth(300);
		gridPane.add(img, 3, 2, 3, 8);
		gridPane.add(msg, 4, 1);
		gridPane.add(errorChecking, 3, 9, 2, 1);

		for (int i = 1; i<=txt.size(); i++)
		{
			txt.get(i-1).setStyle("-fx-font-weight: bold");
			txt.get(i-1).setFill(Color.BLACK);
			gridPane.add(txt.get(i-1), 0, i);
			
		}
		
		 
		for (int i = 1; i<=tfadd.size(); i++)
		{
			gridPane.add(tfadd.get(i-1), 1, i);
		}
		
		ToggleGroup group = new ToggleGroup();
	 	btnAdmin.setToggleGroup(group);
	    btnStaff.setToggleGroup(group);
		
		gridPane.add(btnAdmin, 3, 11);
		gridPane.add(btnStaff, 4, 11);
		
		btnStaff.setOnAction(e ->
		{
			txt.get(5).setDisable(true);   
			tfadd.get(5).setDisable(true);  //when staff button is selected disable the text field
		});
		
		btnAdmin.setOnAction(e ->
		{
			txt.get(5).setDisable(false);  
			tfadd.get(5).setDisable(false);  //when admin button is selected enable the text field
		});
		
		Button addImgBtn = new Button("Add Image File");
		gridPane.add(addImgBtn, 3, 1);
		
		addImgBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select File");
			FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("Image Files (*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif");
	        fileChooser.getExtensionFilters().add(ext);
			File file = fileChooser.showOpenDialog(stage);
			
			if(file != null)
			{
				
				msg.setText(file.toString());
				ProfileImgSrc = file.toString();
				
				try {
					FileInputStream imgnew = new FileInputStream(file.toString());
					Image imgnew2 = new Image(imgnew, 300, 300, false, false);
					img.setImage(imgnew2);
				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				}
			}
			
		});
		
		
		Button addAccount = new Button("Add Account");
		gridPane.setHalignment(addAccount, HPos.RIGHT);
		gridPane.add(addAccount, 3, 12);
		
		addAccount.setOnAction(e -> {
			if (checkValue(ProfileImgSrc,"add"))
			{
				try{
				errorChecking.setTextFill(Color.GREEN);
				errorChecking.setText("User Successfully Added.\nYou may now close this window.");
				addUsertoFeature(adminList,staffList);  
				addAccount.setDisable(true);
				refreshMsg.setText("Please exit Account Management for your changes to be applied");
				refreshMsg.setTextFill(Color.GREEN);
				}
				catch (FileNotFoundException ex)
				{
					JOptionPane.showMessageDialog(null, "Error: File not found");
				}
			}
			else 
			{
				errorChecking.setTextFill(Color.RED);
				errorChecking.setText("Error: Please ensure all fields are filled/valid\nNote: Image file name cannot have a space");
			}
				
				
		});
		
		
		gridPane.add(pgTitle, 0, 0);
		background.getChildren().addAll(loginBox, gridPane);
		Scene scene = new Scene (background);
		stage.setTitle("Add Account");
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	/**
	 *This method for checking for valid information in each text field
	 * 
	 * @param ProfileImgSrc
	 *        String that hold the name of the profile picture's name
	 * @param form
	 *        String that act as a variable to differentiate the function
	 * @return correct: boolean 
	 */
	public boolean checkValue(String ProfileImgSrc, String form)  
	{ 
		boolean correct = true;
		
		if(btnAdmin.isSelected())
		{
		  if(form.equals("edit"))
		  {
			for (int i=0; i<tf.size(); i++) 
		  {
			if (tf.get(i).getText().isEmpty() || tf.get(i).getText() == null){
				correct = false; 
				break;
			}
		  }
		  }
		
		else if(form.equals("add"))
		{
		   for (int i=0; i<tfadd.size(); i++) 
		  {	
		   if (tfadd.get(i).getText().isEmpty() || tfadd.get(i).getText() == null){
				correct = false; 
				break;
			}
		  }
		}   
		}else if (btnStaff.isSelected())
		{
			if(form.equals("edit"))
			  {
			for (int i=0; i<(tf.size()-1); i++) 
			{
				if (tf.get(i).getText().isEmpty() || tf.get(i).getText() == null){
					correct = false; 
					break;
				}
			}
			  }
			else if(form.equals("add"))
			{
			for (int i=0; i<(tfadd.size()-1); i++) 
			{
			    if (tfadd.get(i).getText().isEmpty() || tfadd.get(i).getText() == null){
					correct = false; 
					break;
				}
			}
			}
		}
		
		if (ProfileImgSrc.isEmpty() || ProfileImgSrc == null)
			correct = false;
		else
		{
			if (ProfileImgSrc.contains(" "))
				correct = false;
		}
		
		if(btnAdmin.isSelected())
		{
		if (correct){
			try { 
				if(form.equals("edit"))
				{
				Integer.parseInt(tf.get(5).getText());
				}
				else if(form.equals("add"))
				{
				Integer.parseInt(tfadd.get(5).getText());
				}
			} catch (NumberFormatException ex) {
				correct = false;
			}
			
			
		} 
		else if (btnStaff.isSelected())
		{
			correct = true;
		}
		
		}
		return correct;
	}
	
	
	/**
	 * This method for action in add account button in add account scene which add new object into array list and overwriting the file
	 * 
	 * @param adminList
	 *        array list that hold admin object
	 * @param staffList
	 *        array list that hold staff object
	 * @throws FileNotFoundException
	 * @return void
	 */
	public void addUsertoFeature(ArrayList<Admin> adminList, ArrayList<Staff> staffList) throws FileNotFoundException
	{
	  
		if(btnAdmin.isSelected())
		{
		    Admin admin = new Admin();
			admin.setUsername(tfadd.get(0).getText());
			admin.setPassword(tfadd.get(1).getText());
			admin.setUserID("ADM_00"+ adminList.size());
			admin.setProfileImgSrc(ProfileImgSrc);
			admin.setPos(tfadd.get(2).getText());
			admin.setEmail(tfadd.get(3).getText());
			admin.setPhoneNo(tfadd.get(4).getText());
			admin.setAdminCode(Integer.parseInt(tfadd.get(5).getText()));
			
			adminList.add(admin);
			
			File adminFile = new File("Admin.txt");
			PrintWriter output;
			try {
				   output = new PrintWriter (ADMIN_FILE);
				
				for (int i=0; i<adminList.size(); i++)
				{
					output.println(adminList.get(i).getUsername() + " " + adminList.get(i).getPassword() + " " 
							+ adminList.get(i).getUserID() + " " + adminList.get(i).getProfileImgSrc() + " " + adminList.get(i).getPos() + " " 
							+ adminList.get(i).getEmail() + " " + adminList.get(i).getPhoneNo() + " " +adminList.get(i).getAdminCode());
				}//end for loop
				
				output.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		 
		}//end if 
		else if (btnStaff.isSelected())
		{
			Staff staff = new Staff();
			staff.setUsername(tfadd.get(0).getText());
			staff.setPassword(tfadd.get(1).getText());
			staff.setUserID("STA_00"+ adminList.size());
			staff.setProfileImgSrc(ProfileImgSrc);
			staff.setPos(tfadd.get(2).getText());
			staff.setEmail(tfadd.get(3).getText());
			staff.setPhoneNo(tfadd.get(4).getText());
			
			
			staffList.add(staff);
			
			File staffFile = new File("staff.txt");
			PrintWriter output2;
			try {
				output2 = new PrintWriter (STAFF_FILE);
				for (int i=0; i<staffList.size(); i++)
				{
					output2.println(staffList.get(i).getUsername() + " " + staffList.get(i).getPassword() + " " 
							+ staffList.get(i).getUserID() + " " + staffList.get(i).getProfileImgSrc() + " " + staffList.get(i).getPos() + " " 
							+ staffList.get(i).getEmail() + " " + staffList.get(i).getPhoneNo());
				}
				
				output2.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
				
			}
		}//end if
		
	}
	
	
	/**
	 * This method handling the action when the delete button pressed
	 * 
	 * @param adminList
	 *        array list that hold admin object
	 * @param staffList
	 *        array list that hold staff object
	 * @throws FileNotFoundException
	 * 
	 * @return void
	 */
	public void deleteUser(ArrayList<Admin> adminList ,ArrayList<Staff> staffList) throws FileNotFoundException
	{
        Stage stage = new Stage();
		
		StackPane background = new StackPane();
		
		try
		{
			FileInputStream imgStream = new FileInputStream(BACKGROUND_IMG);
			Image image = new Image(imgStream, 1200, 800, false, false);
			ImageView bgImageView = new ImageView(image);
			background.getChildren().add(bgImageView);
		}
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: " + BACKGROUND_IMG + " not found");
		}
		
		Rectangle loginBox = new Rectangle();
		loginBox.setWidth(1000);
		loginBox.setHeight(600);
		loginBox.setX(100);
		loginBox.setY(100);
		loginBox.setFill(new Color(1.0, 1.0, 1.0, 0.9));
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;"); 
		scrollPane.setPannable(true);
		scrollPane.setTranslateX(140);
		scrollPane.setTranslateY(0);
		scrollPane.setMaxHeight(550);
		
		String name= "";
		int cur = 0;
		int cctr1 = 1, cctr2 = 2, cctr3 = 3, cctr4 = 4, cctr5 = 5; 	//column counter
		int rctr2 = 2; //row counter
		
		//GridPane for DeleteUser
		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(40);
		
		Label num = new Label("No.");
		num.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		num.setTextFill(Color.BLACK);
		
		Label space = new Label("");
		space.setMinWidth(50);
		
		Label userName = new Label ("Username");
		userName.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		userName.setTextFill(Color.BLACK);
		
		Label Pos= new Label ("Position");
		Pos.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		Pos.setTextFill(Color.BLACK);
		
		Label chkBox = new Label("Delete?");
		chkBox.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		chkBox.setTextFill(Color.BLACK);
		
		gridPane.add(num, 1, 1);
		gridPane.add(space, 2, 1);
		gridPane.add(userName, 3, 1);
		gridPane.add(Pos, 4, 1);
		gridPane.add(chkBox, 5, 1);
		
		ArrayList <CheckBox> checkBoxList = new ArrayList<CheckBox>();
		
		for(int i = 0; i < adminList.size(); i++)
		{	
			cur = i +1;
			name = Integer.toString(cur);
			Label n = new Label("  "+name);
			n.setStyle("-fx-font-size: 20px;");
			n.setTextFill(Color.BLACK);
			
			gridPane.add(n, cctr1, rctr2);
			gridPane.add(new ImageView (new Image (new FileInputStream (adminList.get(i).getProfileImgSrc()), 80, 80, false, false)) , cctr2, rctr2);
		
			
			Label adminName = new Label(adminList.get(i).getUsername());
			adminName.setStyle("-fx-font-size: 20px;");
			adminName.setTextFill(Color.BLACK);
			gridPane.add(adminName, cctr3, rctr2);
			
			Label position = new Label(adminList.get(i).getPos());
			position.setStyle("-fx-font-size: 20px;");
			position.setTextFill(Color.BLACK);
			gridPane.add(position, cctr4, rctr2);
	
			
			checkBoxList.add(new CheckBox());
			gridPane.setHalignment(checkBoxList.get(i), HPos.CENTER);
			gridPane.add(checkBoxList.get(i), cctr5, rctr2);
			
			rctr2++;
		}//end for loop
		
		for(int i = 0; i < staffList.size(); i++)
		{	
			cur = i + adminList.size();
			name = Integer.toString(cur);
			Label n = new Label("  "+name);
			n.setStyle("-fx-font-size: 20px;");
			n.setTextFill(Color.BLACK);
			
			gridPane.add(n, cctr1, rctr2);
			gridPane.add(new ImageView (new Image (new FileInputStream (staffList.get(i).getProfileImgSrc()), 80, 80, false, false)) , cctr2, rctr2);
		
			
			Label staffName = new Label(staffList.get(i).getUsername());
			staffName.setStyle("-fx-font-size: 20px;");
			staffName.setTextFill(Color.BLACK);
			gridPane.add(staffName, cctr3, rctr2);
			
			Label position = new Label(staffList.get(i).getPos());
			position.setStyle("-fx-font-size: 20px;");
			position.setTextFill(Color.BLACK);
			gridPane.add(position, cctr4, rctr2);
	
			
			checkBoxList.add(new CheckBox());
			gridPane.setHalignment(checkBoxList.get(i), HPos.CENTER);
			gridPane.add(checkBoxList.get(i+adminList.size()), cctr5, rctr2);
			
			rctr2++;
		}//end for loop   
		
        DropShadow shadow = new DropShadow();
		
		Button deleteButton = new Button("Delete");
		deleteButton.setTranslateX(450);
		deleteButton.setTranslateY(350);
		deleteButton.setPrefSize(100, 50);
		
		deleteButton.setStyle("-fx-background-color: #1AC919;"
				+ "-fx-text-fill: white; -fx-font-weight:bold");
		
		deleteButton.setOnMouseEntered(e -> {
				deleteButton.setEffect(shadow);
				deleteButton.setStyle("-fx-background-color: #1EEA1D;"
						+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
		
		deleteButton.setOnMouseExited(e -> {
			deleteButton.setEffect(null);
			deleteButton.setStyle("-fx-background-color: #1AC919;"
					+ "-fx-text-fill: white; -fx-font-weight:bold");
		});
		
		deleteButton.setOnAction(e -> {
			try{
			deleteUserBtn(checkBoxList, adminList,staffList, stage);
			}catch (FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Error: Product file not found");
			}
		});
		
		scrollPane.setContent(gridPane);
		background.getChildren().addAll(loginBox, scrollPane, deleteButton);
		Scene scene = new Scene (background);
		stage.setTitle("Delete Product");
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	
	/**
	 * This method handling the action when the delete button is pressed in the delete scene
	 * 
	 * @param checkBoxList
	 *        array list that hold all the checkbox object
	 * @param adminList
	 *        array list that hold admin object
	 * @param staffList
	 *        array list that hold staff object
	 * @param stage
	 *        stage object
	 * @throws FileNotFoundException
	 * @return void
	 */
	public void deleteUserBtn(ArrayList<CheckBox> checkBoxList, ArrayList<Admin> adminList, ArrayList<Staff> staffList, Stage stage) throws FileNotFoundException
	{
		int ctr=0, ctr2=0, deleteConfirm;

		for (int i=0; i<checkBoxList.size(); i++) 
		{
			if (checkBoxList.get(i).isSelected())
				ctr++;
		}//end for loop
		
		deleteConfirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these " + ctr + " account(s)?", "Delete Account?", 2);
		if (deleteConfirm == JOptionPane.OK_OPTION)
		{
			stage.close();

			for (int i=0; i<checkBoxList.size(); i++) 
			{
				if (checkBoxList.get(i).isSelected())
				{
				   
					checkBoxList.remove(i);
                    if(i>=adminList.size())
                    {
                       staffList.remove(i-adminList.size());
                                        
                    }
                    else
                    {
                       adminList.remove(i);
                    }
					
					
					i = -1;
				}//end if
				
			}//end for loop
			
			File adminFile = new File("Admin.txt");
			PrintWriter output;
			try {
				   output = new PrintWriter (ADMIN_FILE);
				
				for (int i=0; i<adminList.size(); i++)
				{
					output.println(adminList.get(i).getUsername() + " " + adminList.get(i).getPassword() + " " 
							+ adminList.get(i).getUserID() + " " + adminList.get(i).getProfileImgSrc() + " " + adminList.get(i).getPos() + " " 
							+ adminList.get(i).getEmail() + " " + adminList.get(i).getPhoneNo() + " " +adminList.get(i).getAdminCode());
				}
				
				output.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			File staffFile = new File("staff.txt");
			PrintWriter output2;
			try 
			{
				output2 = new PrintWriter (STAFF_FILE);
				for (int i=0; i<staffList.size(); i++)
				{
					output2.println(staffList.get(i).getUsername() + " " + staffList.get(i).getPassword() + " " 
							+ staffList.get(i).getUserID() + " " + staffList.get(i).getProfileImgSrc() + " " + staffList.get(i).getPos() + " " 
							+ staffList.get(i).getEmail() + " " + staffList.get(i).getPhoneNo());
				}//end for loop
				
				output2.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			refreshMsg.setText("Please exit Account Management for any changes to be applied");
			refreshMsg.setTextFill(Color.GREEN);
	}
  }
	
	
	/**
	 * This method handling the action when each edit button is pressed 
	 * 
	 * @param adminList
	 *        array list that hold admin object
	 * @param staffList
	 *        array list that hold staff object
	 * @param editBtnList
	 *        array list that hold all of the edit button 
	 * @param index
	 *        an integer act as the index for the button in the array list
	 * @param role
	 *        a string act as variable to differentiate the function
	 * @throws FileNotFoundException
	 * @return void
	 */
	public void editAcc(ArrayList<Admin> adminList, ArrayList<Staff> staffList,ArrayList <Button> editBtnList,int index, String role) throws FileNotFoundException
	{
		System.out.println(index);
		System.out.println(adminList.size());
        Stage stage = new Stage();
		
        StackPane background = new StackPane();
		
		try{
			FileInputStream imgStream = new FileInputStream(BACKGROUND_IMG);
			Image image = new Image(imgStream, 1200, 800, false, false);
			ImageView bgImageView = new ImageView(image);
			background.getChildren().add(bgImageView);
		}
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: " + BACKGROUND_IMG + " not found");
		}
		
		Label pgTitle = new Label("Edit User Account");
		pgTitle.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");
		
		Rectangle loginBox = new Rectangle();
		loginBox.setWidth(1000);
		loginBox.setHeight(600);
		loginBox.setX(100);
		loginBox.setY(100);
		loginBox.setFill(new Color(1.0, 1.0, 1.0, 0.9));
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(25);
		gridPane.setVgap(25);
		gridPane.setTranslateX(140);
		gridPane.setTranslateY(120);
		gridPane.getChildren().clear();
		
		ImageView img = new ImageView();
		gridPane.setValignment(img, VPos.TOP);
		Label msg = new Label();
		Label errorChecking = new Label();
		errorChecking.setWrapText(true);
		msg.setWrapText(true);
		msg.setMaxWidth(300);
		gridPane.add(img, 3, 2, 3, 8);
		gridPane.add(msg, 4, 1);
		gridPane.add(errorChecking, 3, 9, 2, 1);
		
		if (role.equals("Admin"))   //set information for each selected admin user 
		{
			    String username = adminList.get(index).getUsername();
			    String pwd = adminList.get(index).getPassword();
			    String pos = adminList.get(index).getPos();
			    String email = adminList.get(index).getEmail();
			    String phone = adminList.get(index).getPhoneNo();
			    int adcode = adminList.get(index).getAdminCode();
				tf.get(0).setText(username);
				tf.get(1).setText(pwd);
				tf.get(2).setText(pos);
				tf.get(3).setText(email);
				tf.get(4).setText(phone);
				tf.get(5).setText(adcode+""); 
		}
		else if (role.equals("Staff"))  //set information for each selected staff user 
		{
				String username2 = staffList.get(index).getUsername();
			    String pwd2 = staffList.get(index).getPassword();
			    String pos2 = staffList.get(index).getPos();
			    String email2 = staffList.get(index).getEmail();
			    String phone2 = staffList.get(index).getPhoneNo();
			
			   
				tf.get(0).setText(username2);
				tf.get(1).setText(pwd2);
				tf.get(2).setText(pos2);
				tf.get(3).setText(email2);
				tf.get(4).setText(phone2);
				
				txt.get(5).setDisable(true);
				tf.get(5).setDisable(true);
		}//end if
		  
		for (int j = 1; j<=txt.size(); j++)
		{
			txt.get(j-1).setStyle("-fx-font-weight: bold");
			txt.get(j-1).setFill(Color.BLACK);
			gridPane.add(txt.get(j-1), 0, j);
			
		}//end for loop
		
		 
		for (int j = 1; j<=tf.size(); j++)
		{
			gridPane.add(tf.get(j-1), 1, j);
		}//end for loop

		Button addImgBtn = new Button("Add Image File");
		gridPane.add(addImgBtn, 3, 1);
		
		addImgBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select File");
			FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("Image Files (*.png, *.jpg, "
					+ ".gif)", "*.png", "*.jpg", "*.gif");
	        fileChooser.getExtensionFilters().add(ext);
			File file = fileChooser.showOpenDialog(stage);
			
			if(file != null)
			{
				
				msg.setText(file.toString());
				ProfileImgSrc = file.toString();
				
				try {
					FileInputStream imgnew = new FileInputStream(file.toString());
					Image imgnew2 = new Image(imgnew, 300, 300, false, false);
					img.setImage(imgnew2);
				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				}
			}//end if
			
		});
		
		gridPane.setHalignment(savebtn, HPos.RIGHT);
		gridPane.add(savebtn, 4, 11);
		
		savebtn.setOnAction(e -> {
			if (checkValue(ProfileImgSrc,"edit"))
			{
				errorChecking.setTextFill(Color.GREEN);
				errorChecking.setText("User Successfully Edited.\nYou may now close this window.");
				
				if (role.equals("Admin"))
				{
						try {
							UpdateUsertoFeature(adminList, staffList,editBtnList,index,"Admin");
							savebtn.setDisable(true);
							refreshMsg.setText("Please exit Account Management for your changes to be applied");
							refreshMsg.setTextFill(Color.GREEN);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				
				else if(role.equals("Staff"))
				{
						try {
							UpdateUsertoFeature(adminList, staffList,editBtnList2,index,"Staff");
							savebtn.setDisable(true);
							refreshMsg.setText("Please exit Account Management for your changes to be applied");
							refreshMsg.setTextFill(Color.GREEN);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}//end if
			}
			else 
			{
				errorChecking.setTextFill(Color.RED);
				errorChecking.setText("Error: Please ensure all fields are filled/valid\nNote: Image file name cannot have a space");
			}//end if
				
				
		});
		
		
		gridPane.add(pgTitle, 0, 0);
		background.getChildren().addAll(loginBox, gridPane);
		Scene scene = new Scene (background);
		stage.setTitle("Edit Account");
		stage.setScene(scene);
		stage.show();
	}
	

	/**
	 * This method handling the action when the save button in the edit scene is pressed. It will replace the object in the specific index and overwriting the whole file
	 * 
	 * @param adminList
	 *        array list that hold admin object
	 * @param staffList
	 *        array list that hold staff object
	 * @param editBtnList
	 *        array list that hold all of the edit object
	 * @param a
	 *        an integer act as the index number in the array list
	 * @param role
	 *        a string act as a variable to differentiate the function
	 * @throws FileNotFoundException
	 * @return void
	 */
	public void UpdateUsertoFeature(ArrayList<Admin> adminList, ArrayList<Staff> staffList,ArrayList <Button> editBtnList,int a, String role) throws FileNotFoundException
	{
		   
		if(role.equals("Admin"))
		{
		 
		    Admin admin = new Admin();
			admin.setUsername(tf.get(0).getText());
			admin.setPassword(tf.get(1).getText());
			admin.setUserID("ADM_00"+ adminList.size());
			admin.setProfileImgSrc(ProfileImgSrc);
			admin.setPos(tf.get(2).getText());
			admin.setEmail(tf.get(3).getText());
			admin.setPhoneNo(tf.get(4).getText());
			admin.setAdminCode(Integer.parseInt(tf.get(5).getText()));
			
			adminList.set(a,admin);
			
			File adminFile = new File("Admin.txt");
			PrintWriter output;
			try {
				   output = new PrintWriter (ADMIN_FILE);
				
				for (int i=0; i<adminList.size(); i++)
				{
					output.println(adminList.get(i).getUsername() + " " + adminList.get(i).getPassword() + " " 
							+ adminList.get(i).getUserID() + " " + adminList.get(i).getProfileImgSrc() + " " + adminList.get(i).getPos() + " " 
							+ adminList.get(i).getEmail() + " " + adminList.get(i).getPhoneNo() + " " +adminList.get(i).getAdminCode());
				}
				
				output.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(role.equals("Staff"))
		{
			Staff staff = new Staff();
			staff.setUsername(tf.get(0).getText());
			staff.setPassword(tf.get(1).getText());
			staff.setUserID("STA_00"+ adminList.size());
			staff.setProfileImgSrc(ProfileImgSrc);
			staff.setPos(tf.get(2).getText());
			staff.setEmail(tf.get(3).getText());
			staff.setPhoneNo(tf.get(4).getText());
			
			
			staffList.set(a,staff);
			
			
			
			File staffFile = new File("staff.txt");
			PrintWriter output2;
			try {
				output2 = new PrintWriter (STAFF_FILE);
				for (int i=0; i<staffList.size(); i++)
				{
					output2.println(staffList.get(i).getUsername() + " " + staffList.get(i).getPassword() + " " 
							+ staffList.get(i).getUserID() + " " + staffList.get(i).getProfileImgSrc() + " " + staffList.get(i).getPos() + " " 
							+ staffList.get(i).getEmail() + " " + staffList.get(i).getPhoneNo());
				}
				
				output2.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}


