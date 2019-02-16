/*
    
    Team Members:

    - Oliver Sim 0327159
    - Ip Kar Hoe 0328380
    - Edward Go 0327189
    - Yip Wei Zhen 0322820

*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
import javafx.scene.layout.BorderPane;
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

public class StockManagement extends FeaturePage {
	
	ArrayList <TextField> textField = new ArrayList <TextField>();
	ArrayList <Text> text = new ArrayList <Text>();
	String prodImageSrc = ""; 
	
	public StockManagement()
	{
		//ArrayList for textField
		for (int i = 0; i<9; i++)
		{
			textField.add(new TextField(""));
		}
		
		//ArrayList for text
		text.add(new Text ("Product Name: "));
		text.add(new Text ("Product Code: "));
		text.add(new Text ("Product Description: "));
		text.add(new Text ("Product Manufacturer: "));
		text.add(new Text ("Product Price: "));
		text.add(new Text ("Product Weight: "));
		text.add(new Text ("Product Quantity: "));
		text.add(new Text ("Product Manu. Date: "));
		text.add(new Text ("Product Exp. Date: "));
	}
	
	/**
	 * stockManagementScene is sort of like the "main" method of this class, all methods in this class are invoked in this method
	 * 
	 *@param productList
	 *   			method receiving an array list that stores all the products
	 * @return Scene : stockManagementScene scene
	 */
	public Scene stockManagementScene(ArrayList<Product> productList)
	{
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
		
		BorderPane mainPane = new BorderPane();
		
		HBox menuBar = new HBox(10); //The header bar
		menuBar.setPadding(new Insets(10, 10, 10, 10));
		menuBar.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		menuBar.setMinHeight(60);
		menuBar.getChildren().add(createInterfaceBtns());
		
		HBox addDeleteProducts = new HBox(150); //The header bar
		addDeleteProducts.setPadding(new Insets(150, 100, 100, 130));
		addDeleteProducts.setStyle("-fx-background-color: transparent");
		addDeleteProducts.setMinHeight(60);
		
		Button addProduct = new Button("   Add\nProduct");
		Button deleteProduct = new Button(" Delete\nProduct");
		
		makeMenuBtn(addProduct, "#1AC919", "#1EEA1D"); 
		makeMenuBtn(deleteProduct, "#C40000", "#E81717");
		
		addProduct.setOnAction(e -> {
			addProduct(productList);
		});
		
		deleteProduct.setOnAction(e -> {
			try{
			deleteProduct(productList);
			}
			catch (FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Error: Product image not found");
			}
		});
		
		addDeleteProducts.getChildren().addAll(addProduct, deleteProduct);
		mainPane.setCenter(addDeleteProducts);
		
		mainPane.setTop(menuBar);
		background.getChildren().add(mainPane);
		
		Scene scene = new Scene (background);
		return scene;
	}
	
	/**
	 * To create individual menu buttons
	 * 
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
		
		menuBtn.setPrefSize(400, 400);
		menuBtn.setWrapText(true);
		menuBtn.setStyle("-fx-background-radius: 300px;" //Original appearance
				+ "-fx-text-align: center;"
				+ "-fx-font-size: 30px;"
				+ "-fx-background-color: " + baseColor + ";"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight:bold;"
				+ "-fx-border-color: white;"
				+ "-fx-border-width: 5px;"
				+ "-fx-border-radius: 300px");
		
		menuBtn.setOnMouseEntered(e -> {
			menuBtn.setEffect(shadow);
			menuBtn.setStyle("-fx-background-radius: 300px;" //On hover appearance
					+ "-fx-font-size: 32px;"
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
					+ "-fx-font-size: 30px;"
					+ "-fx-background-color: " + baseColor + ";"
					+ "-fx-text-fill: white;"
					+ "-fx-font-weight:bold;"
					+ "-fx-border-color: white;"
					+ "-fx-border-width: 5px;"
					+ "-fx-border-radius: 300px");
		});
	}
	
	/**
	 * Adds product to the existing list of products 
	 * 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 */
	public void addProduct (ArrayList<Product> productList) {
		
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
		
		Label pgTitle = new Label("Add Product Form");
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
		
		for (int i = 1; i<=text.size(); i++)
		{
			text.get(i-1).setStyle("-fx-font-weight: bold");
			gridPane.add(text.get(i-1), 0, i);
		}
		 
		for (int i = 1; i<=textField.size(); i++)
		{
			gridPane.add(textField.get(i-1), 1, i);
		}
		
		Button addImg = new Button("Add Image File");
		gridPane.add(addImg, 3, 1);
		
		addImg.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select File");
			FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("Image Files (*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif");
	        fileChooser.getExtensionFilters().add(ext);
			File file = fileChooser.showOpenDialog(stage);
			
			if(file != null)
			{
				
				msg.setText(file.toString());
				prodImageSrc = file.toString();
				
				try {
					FileInputStream imgnew = new FileInputStream(file.toString());
					Image imgnew2 = new Image(imgnew, 300, 300, false, false);
					img.setImage(imgnew2);
				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				}
			}
		});
		
		Button addProduct = new Button("Add Product");
		gridPane.setHalignment(addProduct, HPos.RIGHT);
		gridPane.add(addProduct, 5, 9);
		
		addProduct.setOnAction(e -> {
			if (checkValue(prodImageSrc))
			{
				try{
				errorChecking.setTextFill(Color.GREEN);
				errorChecking.setText("Product Successfully Added.\nYou may now close this window.");
				addProducttoFeature(productList);
				addProduct.setDisable(true);
				}
				catch (FileNotFoundException ex)
				{
					JOptionPane.showMessageDialog(null, "Error: Product.txt not found");
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
		stage.setTitle("Add Product");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * checks whether the input textfields are empty or not 
	 * 
	 * @param prodImageSrc
	 * 				method receiving String of the directory/name of the image file
	 * 
	 * @return	correct : a boolean flag
	 */
	public boolean checkValue(String prodImageSrc) { 
		boolean correct = true;
		
		for (int i=0; i<textField.size(); i++) 
		{
			if (textField.get(i).getText().isEmpty() || textField.get(i).getText() == null){
				correct = false; 
				break;
			}
		}
		
		if (prodImageSrc.isEmpty() || prodImageSrc == null)
			correct = false;
		else
		{
			if (prodImageSrc.contains(" "))
				correct = false;
		}
		
		if (correct){
			try { 
				Double.parseDouble(textField.get(4).getText());
				Double.parseDouble(textField.get(5).getText());
				Integer.parseInt(textField.get(6).getText());
			} catch (NumberFormatException ex) {
				correct = false;
			}
		}
		
		return correct;
	}
	
	/**
	 * Adding the newly added products to the text file 
	 * 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 * @throws FileNotFoundException
	 */
	public void addProducttoFeature(ArrayList<Product> productList) throws FileNotFoundException {
		Product product = new Product();
		product.setProdName(textField.get(0).getText());
		product.setProdCode(textField.get(1).getText());
		product.setProdDesc(textField.get(2).getText());
		product.setManufacture(textField.get(3).getText());
		product.setPrice(Double.parseDouble(textField.get(4).getText()));
		product.setWeight(Double.parseDouble(textField.get(5).getText()));
		product.setQty(Integer.parseInt(textField.get(6).getText()));
		product.setManuDate(textField.get(7).getText());
		product.setExpDate(textField.get(8).getText());
		product.setProdImgSrc(prodImageSrc);
		
		productList.add(product);
		
		File prodFile = new File("Product.txt");
		PrintWriter output = new PrintWriter (prodFile);
		
		for (int i=0; i<productList.size(); i++)
		{
			output.println(productList.get(i).getProdCode() + " " + productList.get(i).getProdImgSrc() + " " 
					+ productList.get(i).getPrice() + " " + productList.get(i).getWeight() + " " + productList.get(i).getQty() + " " 
					+ productList.get(i).getManuDate() + " " + productList.get(i).getExpDate());
			output.println(productList.get(i).getProdName());
			output.println(productList.get(i).getProdDesc());
			output.println(productList.get(i).getManufacture());
			output.println();
		}
		
		output.close();
	}
	
	/**
	 * Delete product from the existing list of products 
	 * 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 *   
	 * @throws FileNotFoundException
	 */
	public void deleteProduct (ArrayList<Product> productList) throws FileNotFoundException {
		
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
		int cctr1 = 1, cctr2 = 2, cctr3 = 3, cctr4 = 4, cctr5 = 5, cctr6 = 6; 	//column counter
		int rctr2 = 2; //row counter
		
		//GridPane for DeleteProduct
		GridPane gridPane = new GridPane();
		gridPane.setVgap(20);
		gridPane.setHgap(40);
		
		Label num = new Label("No.");
		num.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		num.setTextFill(Color.BLACK);
		
		Label space = new Label("");
		space.setMinWidth(50);
		
		Label cd = new Label ("Code");
		cd.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		cd.setTextFill(Color.BLACK);
		
		Label namelbl= new Label ("Name");
		namelbl.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		namelbl.setTextFill(Color.BLACK);
		
		Label qty = new Label("Qty");
		qty.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		qty.setTextFill(Color.BLACK);
		
		Label chkBox = new Label("Delete?");
		chkBox.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		chkBox.setTextFill(Color.BLACK);
		
		gridPane.add(num, 1, 1);
		gridPane.add(space, 2, 1);
		gridPane.add(cd, 3, 1);
		gridPane.add(namelbl, 4, 1);
		gridPane.add(qty, 5, 1);
		gridPane.add(chkBox, 6, 1);
		
		ArrayList <CheckBox> checkBoxList = new ArrayList<CheckBox>();
		
		for(int i = 0; i < productList.size(); i++)
		{	
			cur = i +1;
			name = Integer.toString(cur);
			Label n = new Label("  "+name);
			n.setStyle("-fx-font-size: 20px;");
			n.setTextFill(Color.BLACK);
			
			gridPane.add(n, cctr1, rctr2);
			gridPane.add(new ImageView (new Image (new FileInputStream (productList.get(i).getProdImgSrc()), 80, 80, false, false)) , cctr2, rctr2);
		
			
			Label prodCode = new Label(productList.get(i).getProdCode());
			prodCode.setStyle("-fx-font-size: 20px;");
			prodCode.setTextFill(Color.BLACK);
			gridPane.add(prodCode, cctr3, rctr2);
			
			Label prodName = new Label(productList.get(i).getProdName());
			prodName.setStyle("-fx-font-size: 20px;");
			prodName.setTextFill(Color.BLACK);
			gridPane.add(prodName, cctr4, rctr2);
			
			Label prodQty = new Label(Integer.toString(productList.get(i).getQty()));
			prodQty.setStyle("-fx-font-size: 20px;");
			prodQty.setTextFill(Color.BLACK);
			gridPane.add(prodQty, cctr5, rctr2);
			
			checkBoxList.add(new CheckBox());
			gridPane.setHalignment(checkBoxList.get(i), HPos.CENTER);
			gridPane.add(checkBoxList.get(i), cctr6, rctr2);
			
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
			deleteButton(checkBoxList, productList, stage);
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
	 * Removing the products selected from the text files
	 * 
	 * @param checkBoxList
	 * 				method reiving array list that contains all the checkboxes to be displayed beside each product 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 * @param stage
	 * 				method receiving "pop up" stage that asks whether the user want to confirm the deletion of product
	 * @throws FileNotFoundException
	 */
	public void deleteButton(ArrayList<CheckBox> checkBoxList, ArrayList<Product> productList, Stage stage) throws FileNotFoundException
	{
		int ctr=0, ctr2=0, deleteConfirm;

		for (int i=0; i<checkBoxList.size(); i++) 
		{
			if (checkBoxList.get(i).isSelected())
				ctr++;
		}
		
		deleteConfirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these " + ctr + " product(s)?", "Delete Product?", 2);
		if (deleteConfirm == JOptionPane.OK_OPTION)
		{
			stage.close();

			for (int i=0; i<checkBoxList.size(); i++) 
			{
				if (checkBoxList.get(i).isSelected())
				{
					checkBoxList.remove(i);
					productList.remove(i);
					i = -1;
				}
			}
			
			File prodFile = new File("Product.txt");
			PrintWriter output = new PrintWriter (prodFile);
			
			for (int i=0; i<productList.size(); i++)
			{
				output.println(productList.get(i).getProdCode() + " " + productList.get(i).getProdImgSrc() + " " 
						+ productList.get(i).getPrice() + " " + productList.get(i).getWeight() + " " + productList.get(i).getQty() + " " 
						+ productList.get(i).getManuDate() + " " + productList.get(i).getExpDate());
				output.println(productList.get(i).getProdName());
				output.println(productList.get(i).getProdDesc());
				output.println(productList.get(i).getManufacture());
				output.println();
			}
			
			output.close();
		}
	}
}
