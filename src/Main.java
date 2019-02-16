/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;

public class Main extends Application{
	final String STAFF_FILE = "Staff.txt"; //Fixes the files from which to read data
	final String ADMIN_FILE = "Admin.txt";
	final String PRODUCT_FILE = "Product.txt";
	
	ArrayList<Staff> staffList = new ArrayList<Staff>(); 
	ArrayList<Admin> adminList = new ArrayList<Admin>(); 
	ArrayList<Product> productList = new ArrayList<Product>();
	//ArrayLists used to store staff, admin and product data so files don't have to be repeatedly accessed
	//ArrayLists initialized as global variables so they dont have to be passed to every method
	
	Staff curUser = new Staff(); //Store the user ID of current logged-in user
	String curUserType;
	Boolean isOmPage; 
	//Order Management has a warning window, so this value allows the window to appear when the main menu button
	//is pressed only when on the order management page
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException
	{
		try
		{
			getStaffData(); //Load staff and admin data into arraylists
			getProductData();
		}
		catch (FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Staff file/Admin file/Product file not found.");
		}
		
		isOmPage = false;
		
		Login loginPage = new Login(); //Creates a new login page class
		Scene loginScene = loginPage.loginScene();
		MainMenu menuPage = new MainMenu(); //Create a new main menu class
		ProductManagement productListPage = new ProductManagement(); //Creates a new product management page class
		OrderManagement om = new OrderManagement(); //Create new order management page class
		InventoryManagement im = new InventoryManagement(); //Create a new inventory management page class
		StockManagement stockManagement = new StockManagement();
		AccountManagement accountListPage = new AccountManagement();
		
		loginPage.logIn.setOnAction(e -> {
			if(loginPage.loginDetailsCorrect(staffList, adminList)) //Checks if username and password entered are found and are matching
			{
				RadioButton temp = (RadioButton)loginPage.role.getSelectedToggle();
				curUserType = temp.getText();

				if (curUserType.equals("Admin"))
				{
					for (int i = 0; i < adminList.size(); i++)
					{
						if (adminList.get(i).getUsername().equals(loginPage.userName.getText()))
						{
							curUser.copyOf((Staff)adminList.get(i)); //Copies the fields of the matching user admin account to curUser
							primaryStage.setScene(menuPage.mainMenuScene(curUser));
							break;
						}
					}
				}
				
				else if (curUserType.equals("Staff"))
				{
					for (Staff s : staffList)
					{
						if (s.getUsername().equals(loginPage.userName.getText()))
						{
							curUser.copyOf(s); //Copies the fields of the matching user staff account to curUser
							primaryStage.setScene(menuPage.mainMenuScene(curUser));
							break;
						}
					}
				}
				
				primaryStage.setTitle("Main Menu");
			}
		});
		
		//(FROM)Main Menu Button Links
			MainMenu.accountManagement.setOnAction(e -> {
				primaryStage.setTitle("Account Management");
				primaryStage.setScene(accountListPage.accountListScene(adminList, staffList, curUser));
			});
			
			MainMenu.productManagement.setOnAction(e -> {
				primaryStage.setTitle("Product Management");
				primaryStage.setScene(productListPage.productListScene(productList));
			});
			
			MainMenu.orderManagement.setOnAction(e -> {
				isOmPage = true;
				primaryStage.setTitle("Order Management");
				primaryStage.setScene(om.viewOutOfStockScene(productList));
			});
			
			MainMenu.inventoryManagement.setOnAction(e -> {
				primaryStage.setTitle("Inventory Management");
				primaryStage.setScene(im.viewInv(productList));
			});
			
			MainMenu.stockManagement.setOnAction(e -> {
				primaryStage.setTitle("Stock Management");
				primaryStage.setScene(stockManagement.stockManagementScene(productList));
			});
			
		//Back button links
			accountListPage.backBtn.setOnAction(e -> {
				primaryStage.setTitle("Main Menu");
				primaryStage.setScene(menuPage.mainMenuScene(curUser));
			});
			
			productListPage.backBtn.setOnAction(e -> {
				primaryStage.setTitle("Main Menu");
				primaryStage.setScene(menuPage.mainMenuScene(curUser));
			});
			
			im.backBtn.setOnAction(e -> {
				primaryStage.setTitle("Main Menu");
				primaryStage.setScene(menuPage.mainMenuScene(curUser));
			});
			
			om.backBtn.setOnAction(e -> {
				isOmPage = false;
				om.exitMsgWindow(primaryStage, menuPage, curUser);
			});
			
			stockManagement.backBtn.setOnAction(e -> {
				primaryStage.setTitle("Main Menu");
				primaryStage.setScene(menuPage.mainMenuScene(curUser));
			});
			
		//(TO)Main Menu button link
			FeaturePage.menuBtn.setOnAction(e -> {
				if(isOmPage) //Show warning window if current page is order management
				{
					isOmPage = false;
					om.exitMsgWindow(primaryStage, menuPage, curUser);
				}
				
				else
				{
					primaryStage.setTitle("Main Menu");
					primaryStage.setScene(menuPage.mainMenuScene(curUser));
				}
			});
			
		//Log out button link
			FeaturePage.logOutBtn.setOnAction(e -> { //Returns user to log in page when "Log Out" button is pressed
				int logOutConfirm = JOptionPane.CANCEL_OPTION;
				logOutConfirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log Out", 2); //Asks the user if they are sure
				
				if (logOutConfirm == JOptionPane.OK_OPTION) //Return to log in page when user presses 'OK'
				{
					loginPage.userName.setText(""); //Clear the username and password fields
					loginPage.passWord.setText("");
					primaryStage.setTitle("Log In");
					primaryStage.setScene(loginScene);
				}
			});

		primaryStage.setTitle("Log In"); 
		primaryStage.setScene(loginScene); //Set startup screen as the login screen
		primaryStage.show();
	}
	
	/**
	 * Method that obtains all the information and details of the staff
	 * 
	 * @throws FileNotFoundException
	 */
	public void getStaffData() throws FileNotFoundException 
	//Used to load staff and admin data to their respective arraylists from file
	//this method does not return anything
	//no parameters are passed into the method 
	{
		File staffFile = new File(STAFF_FILE);
		File adminFile = new File(ADMIN_FILE);
		
		Scanner staffInput = new Scanner(staffFile);
		Scanner adminInput = new Scanner(adminFile);
		
		while(staffInput.hasNext())
		{
			Staff tempStaff = new Staff(); //Create a temporary Staff object to store all details
			tempStaff.setUsername(staffInput.next());
			tempStaff.setPassword(staffInput.next());
			tempStaff.setUserID(staffInput.next());
			tempStaff.setProfileImgSrc(staffInput.next());
			tempStaff.setPos(staffInput.next());
			tempStaff.setEmail(staffInput.next());
			tempStaff.setPhoneNo(staffInput.next());
			
			staffList.add(tempStaff); //Add staff object into staff arraylist
		}
		
		while(adminInput.hasNext())
		{
			Admin tempAdmin = new Admin(); //Create a temporary Admin object to store all details
			tempAdmin.setUsername(adminInput.next());
			tempAdmin.setPassword(adminInput.next());
			tempAdmin.setUserID(adminInput.next());
			tempAdmin.setProfileImgSrc(adminInput.next());
			tempAdmin.setPos(adminInput.next());
			tempAdmin.setEmail(adminInput.next());
			tempAdmin.setPhoneNo(adminInput.next());
			tempAdmin.setAdminCode(adminInput.nextInt());
			
			adminList.add(tempAdmin); //Add admin object into admin arraylist
		}
		
		staffInput.close();
		adminInput.close();
	}
	
	/**
	 * Method that obtains all the information and descriptions of the products
	 * 
	 * @throws FileNotFoundException
	 */
	public void getProductData() throws FileNotFoundException 
	//Used to load product data to arraylist from file
	//this method does not return anything
	//no parameters are passed into the method 
	{
		File prodFile = new File(PRODUCT_FILE);
		
		Scanner prodInput = new Scanner(prodFile);
		
		while(prodInput.hasNext())
		{
			Product tempProd = new Product(); //Create a temporary Product object to store all details
			tempProd.setProdCode(prodInput.next());
			tempProd.setProdImgSrc(prodInput.next());
			tempProd.setPrice(prodInput.nextDouble());
			tempProd.setWeight(prodInput.nextDouble());
			tempProd.setQty(prodInput.nextInt());
			tempProd.setManuDate(prodInput.next());
			tempProd.setExpDate(prodInput.next());
			tempProd.setProdName(prodInput.next() + prodInput.nextLine());
			tempProd.setProdDesc(prodInput.nextLine());
			tempProd.setManufacture(prodInput.nextLine());
			
			productList.add(tempProd); //Add product object into product arraylist
		}
	}
	
	public static void main(String [] args)
	{
		Application.launch(args);
	}
}
