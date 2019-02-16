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

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ProductManagement extends FeaturePage{

	public ProductManagement()
	{
		super();
	}
	
	/**
	 * productListScene is sort of like the "main" method of this class, all methods in this class are invoked in this method
	 * 
	 *@param productList
	 *   			method receiving an array list that stores all the products
	 * @return Scene : productListScene scene
	 */
	public Scene productListScene(ArrayList<Product> productList) 
	{
		StackPane background = new StackPane();
		
		VBox menu = new VBox(10);
		menu.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		menu.setPrefWidth(320);
		menu.setPadding(new Insets(10, 10, 10, 10));
		menu.setAlignment(Pos.TOP_CENTER);
		VBox subMenu = new VBox(10);
		subMenu.setStyle("-fx-background-color: transparent");
		subMenu.setPrefWidth(320);
		
		Label info = new Label("Stockies");
		Label info2 = new Label("We aim to provide our customers with affordable prices and "
				+ "quality products that would otherwise be wasted and thrown away. Our offerings "
				+ "consist of a variety of canned food products including meats, fruits, soups and more.");
		HBox searchBar = new HBox(10);
		TextField searchBox = new TextField();
		searchBox.setPrefWidth(220);
		
		Label srchHead = new Label("Search");
		Label srchInfo = new Label("Use the field above to search for Product Code or Product Name");
		Label msg = new Label();
		Label filterHead = new Label("Product Filters");
		Label filterInfo = new Label("Use the following to filter displayed products");
		srchHead.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		srchHead.setTextFill(Color.WHITE);
		srchInfo.setStyle("-fx-font-size: 14px");
		srchInfo.setTextFill(Color.WHITE);
		srchInfo.setWrapText(true);
		info.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		info.setTextFill(Color.WHITE);
		info2.setStyle("-fx-font-size: 16px");
		info2.setTextFill(Color.WHITE);
		info2.setWrapText(true);
		info2.setTextAlignment(TextAlignment.CENTER);
		msg.setStyle("-fx-font-size: 14px");
		msg.setTextFill(Color.WHITE);
		filterHead.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
		filterHead.setTextFill(Color.WHITE);
		filterInfo.setStyle("-fx-font-size: 14px");
		filterInfo.setTextFill(Color.WHITE);
		filterInfo.setWrapText(true);
		
		Button searchBtn = new Button("Search");
		Button filterBtn = new Button("Filter");
		
		ToggleGroup filter = new ToggleGroup();
		RadioButton meat = new RadioButton("Meats");
		meat.setToggleGroup(filter);
		meat.setTextFill(Color.WHITE);
		meat.setSelected(true);
		RadioButton soup = new RadioButton("Soups");
		soup.setToggleGroup(filter);
		soup.setTextFill(Color.WHITE);
		RadioButton fruit = new RadioButton("Fruits");
		fruit.setToggleGroup(filter);
		fruit.setTextFill(Color.WHITE);
		RadioButton etc = new RadioButton("Others");
		etc.setToggleGroup(filter);
		etc.setTextFill(Color.WHITE);
		
		searchBar.getChildren().addAll(searchBox, searchBtn);
		subMenu.getChildren().addAll(meat, soup, fruit, etc);
		menu.getChildren().addAll(createInterfaceBtns(), new Label(), info, info2, new Label(), srchHead, 
				searchBar, srchInfo, msg, new Label(), filterHead, filterInfo, subMenu, filterBtn);
		
		ScrollPane prodGridScroll = new ScrollPane(); //The scrollpane containing gridpane containing the products
		prodGridScroll.setMaxHeight(800);
		prodGridScroll.setPrefWidth(800);
		prodGridScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
		prodGridScroll.setPannable(true);
		
		GridPane prodGrid = new GridPane(); //The gridpane containing the products
		prodGrid.setVgap(10);
		prodGrid.setHgap(50);
		prodGrid.setStyle("-fx-background-color: transparent");
		
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
		
		ArrayList<Button> prodBtns = new ArrayList<Button>(); //An arraylist of buttons corresponding to each product
		
		searchBtn.setOnAction(e -> { //Produces a list of products based on the search query
			try
			{
				int results = genProdListwithSearch(searchBox.getText(), prodGrid, prodBtns, productList);
				msg.setText(results + " matching product(s) found.");
			}
			catch (FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Error: Product Image not found");
			}
		});
		
		filterBtn.setOnAction(e -> { //Produces a list of products based on the filter
			try
			{
				genProdListwithFilter(filter, prodGrid, prodBtns, productList);
			}
			catch (FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Error: Product Image not found");
			}
		});
		
		try
		{
			genProdList(prodGrid, prodBtns, productList); //Produce the full default product list
		}
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: Product Image not found");
		}
		
		menu.prefHeightProperty().bind(background.heightProperty());
		
		prodGridScroll.setContent(prodGrid);
		hbox.getChildren().addAll(menu, prodGridScroll);
		background.getChildren().addAll(hbox);
		
		Scene scene = new Scene(background);
		return scene;
	}
	
	/**
	 * Generates the full default list of products
	 * 
	 * 	@param prodGrid
	 * 			method receiving gridPane that shows the products in the form of boxes
	 * @param prodBtns
	 * 			method receiving buttons that opens an extra small window showing the descriptions of the products
	 * @param productList
	 *   		method receiving an array list that stores all the products
	 * @throws FileNotFoundException
	 */
	public void genProdList(GridPane prodGrid, ArrayList<Button> prodBtns, ArrayList<Product> productList) throws FileNotFoundException
	{
		prodGrid.getChildren().clear(); //Empty the gridpane and list of buttons for a new list of products
		prodBtns.clear();
		
		int rowCtr = 0, colCtr = 0;
		
		for(int i = 0; i < productList.size(); i++)
		{	
			if (colCtr == 2)
			{
				rowCtr++;
				colCtr = 0;
			}
			
			Button b = new Button();
			
			prodBtns.add(b);
			prodGrid.add(createProductPane(productList.get(i), prodBtns.get(i)), colCtr, rowCtr);
			colCtr++;
		}
	}
	
	/**
	 * Generates a list of products according to the search bar
	 * 
	 * @param search
	 * 			method receiving the string for the search query
	 *@param prodGrid
	 * 			method receiving gridPane that shows the products in the form of boxes
	 * @param prodBtns
	 * 			method receiving buttons that opens an extra small window showing the descriptions of the products
	 * @param productList
	 *   		method receiving an array list that stores all the products
	 * @return results : int 
	 * 			counts the number of products that match the search 
	 * @throws FileNotFoundException
	 */
	public int genProdListwithSearch(String search, GridPane prodGrid, ArrayList<Button> prodBtns, 
			ArrayList<Product> productList) throws FileNotFoundException 
	{
		prodGrid.getChildren().clear(); //Empty the gridpane and list of buttons for a new list of products
		prodBtns.clear();
		
		int rowCtr = 0, colCtr = 0;
		int results = 0;
		
		for(int i = 0; i < productList.size(); i++)
		{	
			if (colCtr == 2)
			{
				rowCtr++;
				colCtr = 0;
			}
			
			if (search.length() == 1)
			{
				if ((productList.get(i).getProdCode()).charAt(0) == search.toUpperCase().charAt(0) || (productList.get(i).getProdName()).substring(0, 1).equals(search.toUpperCase()))
				{	
					Button b = new Button();
					prodBtns.add(b);
					prodGrid.add(createProductPane(productList.get(i), prodBtns.get(results)), colCtr, rowCtr);
					colCtr++;
					results++;
				}
			}
			else 
			{	
				if ((productList.get(i).getProdCode()).contains(search.toUpperCase()) || ((productList.get(i).getProdName()).toUpperCase()).contains(search.toUpperCase()))
				{	
					Button b = new Button();
					prodBtns.add(b);
					prodGrid.add(createProductPane(productList.get(i), prodBtns.get(results)), colCtr, rowCtr);
					colCtr++;
					results++;
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Generates a list of products according to the filter
	 * 
	 * @param filter
	 * 		method receiving the choice of which the category of the products to be filtered out 
	 * @param prodGrid
	 * 			method receiving gridPane that shows the products in the form of boxes
	 * @param prodBtns
	 * 			method receiving buttons that opens an extra small window showing the descriptions of the products
	 * @param productList
	 *   		method receiving an array list that stores all the products
	 * @throws FileNotFoundException
	 */
	public void genProdListwithFilter(ToggleGroup filter, GridPane prodGrid, ArrayList<Button> prodBtns, 
			ArrayList<Product> productList) throws FileNotFoundException 
	//
	{
		prodGrid.getChildren().clear(); //Empty the gridpane and list of buttons for a new list of products
		prodBtns.clear();
		RadioButton selection = (RadioButton) filter.getSelectedToggle();
		String filterText = selection.getText();
		
		int rowCtr = 0, colCtr = 0;
		int results = 0;
		
		for(int i = 0; i < productList.size(); i++)
		{	
			if (colCtr == 2)
			{
				rowCtr++;
				colCtr = 0;
			}
			
			if (filterText.equals("Meats"))
			{	
				if (productList.get(i).getProdCode().charAt(0) == ('M'))
				{
					Button b = new Button();
					prodBtns.add(b);
					prodGrid.add(createProductPane(productList.get(i), prodBtns.get(results)), colCtr, rowCtr);
					colCtr++;
					results++;
				}
			}
			
			else if (filterText.equals("Soups"))
			{	
				if (productList.get(i).getProdCode().charAt(0) == ('S'))
				{
					Button b = new Button();
					prodBtns.add(b);
					prodGrid.add(createProductPane(productList.get(i), prodBtns.get(results)), colCtr, rowCtr);
					colCtr++;
					results++;
				}
			}
			
			else if (filterText.equals("Fruits"))
			{	
				if (productList.get(i).getProdCode().charAt(0) == ('F'))
				{
					Button b = new Button();
					prodBtns.add(b);
					prodGrid.add(createProductPane(productList.get(i), prodBtns.get(results)), colCtr, rowCtr);
					colCtr++;
					results++;
				}
			}
			
			else if (filterText.equals("Others"))
			{	
				if (productList.get(i).getProdCode().charAt(0) == ('A'))
				{
					Button b = new Button();
					prodBtns.add(b);
					prodGrid.add(createProductPane(productList.get(i), prodBtns.get(results)), colCtr, rowCtr);
					colCtr++;
					results++;
				}
			}
		}
	}
	
	/**
	 * Creates the individual boxes for each product
	 * 
	 * @param product
	 * 		method receiving product that is created for each of the boxes containing the product information
	 * @param btn
	 * 		method receiving button that shows all the description of the product
	 * @return productPane: StackPane
	 * 
	 * @throws FileNotFoundException
	 */
	public StackPane createProductPane(Product product, Button btn) throws FileNotFoundException 
	{
		StackPane productPane = new StackPane();
		HBox productPaneDesc = new HBox(10);
		VBox productPaneArr = new VBox(10);
		productPaneArr.setPrefSize(36, 295);
		productPaneArr.setPadding(new Insets(10, 10, 10, 10));
		productPaneArr.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		
		FileInputStream imgStream = new FileInputStream(product.getProdImgSrc());
		Image image = new Image(imgStream, 320, 215, false, false);
		ImageView imgView = new ImageView(image);
		
		Label prodCode = new Label(product.getProdCode());
		prodCode.setStyle("-fx-background-color: #1AC919; -fx-font-weight: bold; -fx-font-size: 20px");
		prodCode.setAlignment(Pos.CENTER);
		prodCode.setPrefSize(100, 65);
		prodCode.setTextFill(Color.WHITE);
		
		Label prodName = new Label(product.getProdName());
		prodName.setStyle("-fx-background-color: #2E2F37");
		prodName.setAlignment(Pos.CENTER_LEFT);
		prodName.setPrefSize(225, 65);
		prodName.setFont(Font.font("Verdana", 18));
		prodName.setWrapText(true);
		prodName.setTextFill(Color.WHITE);
		
		productPaneDesc.getChildren().addAll(prodCode, prodName);
		productPaneArr.getChildren().addAll(imgView, productPaneDesc);
		
		btn.setMinSize(250, 300);
		btn.setStyle("-fx-opacity: 0");
		DropShadow shadow = new DropShadow();
		
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() { //When the mouse hovers over it
			@Override
			public void handle(MouseEvent t)
			{
				productPane.setEffect(shadow);
				prodCode.setStyle("-fx-background-color: #1EEA1D; -fx-font-weight: bold; -fx-font-size: 20px");
			}
		});
		
		btn.setOnMouseExited(new EventHandler<MouseEvent>() { //When the mouse stops hovering over it
			@Override
			public void handle(MouseEvent t)
			{
				productPane.setEffect(null);
				prodCode.setStyle("-fx-background-color: #1AC919; -fx-font-weight: bold; -fx-font-size: 20px");
			}
		});
		
		btn.setOnAction(e -> { //Opens a new stage with product details when a product is clicked
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(20, 20, 20, 20));
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setStyle("-fx-background-color: #2E2F37");
			
			try 
			{
				FileInputStream imgStream2 = new FileInputStream(product.getProdImgSrc());
				Image img2 = new Image(imgStream2, 320, 215, false, false);
				ImageView imgView2 = new ImageView(img2);
			
				Label prodCodeLbl = new Label(product.getProdCode());
				prodCodeLbl.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
				prodCodeLbl.setTextFill(Color.WHITE);
				
				Label prodNameLbl = new Label(product.getProdName());
				prodNameLbl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
				prodNameLbl.setTextFill(Color.WHITE);
				
				Label prodDescLbl = new Label(product.getProdDesc());
				prodDescLbl.setAlignment(Pos.CENTER);
				prodDescLbl.setTextFill(Color.WHITE);
				
				Label price = new Label("Price: ");
				price.setStyle("-fx-font-weight: bold;");
				price.setTextFill(Color.WHITE);
				Label prodPriceLbl = new Label("RM " + product.getPrice());
				prodPriceLbl.setTextFill(Color.WHITE);
				
				Label weight = new Label("Weight: ");
				weight.setStyle("-fx-font-weight: bold;");
				weight.setTextFill(Color.WHITE);
				Label prodWeightLbl = new Label(product.getWeight() + " grams");
				prodWeightLbl.setTextFill(Color.WHITE);
				
				Label qty = new Label("Quantity Available: ");
				qty.setStyle("-fx-font-weight: bold;");
				qty.setTextFill(Color.WHITE);
				Label prodQtyLbl = new Label(product.getQty() + "");
				prodQtyLbl.setTextFill(Color.WHITE);
				
				Label manuDate = new Label("Date Manufactured: ");
				manuDate.setStyle("-fx-font-weight: bold;");
				manuDate.setTextFill(Color.WHITE);
				Label prodManuDateLbl = new Label(product.getManuDate() + "");
				prodManuDateLbl.setTextFill(Color.WHITE);
				
				Label expDate = new Label("Best Before: ");
				expDate.setStyle("-fx-font-weight: bold;");
				expDate.setTextFill(Color.WHITE);
				Label prodExpDateLbl = new Label(product.getExpDate() + "");
				prodExpDateLbl.setTextFill(Color.WHITE);
					
				grid.add(imgView2, 0, 0, 10, 1);
				GridPane.setHalignment(imgView2, HPos.CENTER);
				grid.add(prodCodeLbl, 0, 1, 10, 1);
				GridPane.setHalignment(prodCodeLbl, HPos.CENTER);
				grid.add(prodNameLbl, 0, 2, 10, 1);
				GridPane.setHalignment(prodNameLbl, HPos.CENTER);
				grid.add(prodDescLbl, 0, 3, 10, 1);
				GridPane.setHalignment(prodDescLbl, HPos.CENTER);
				grid.add(price, 0, 5);
				grid.add(prodPriceLbl, 1, 5);
				grid.add(weight, 8, 5);
				grid.add(prodWeightLbl, 9, 5);
				grid.add(manuDate, 0, 6);
				grid.add(prodManuDateLbl, 1, 6);
				grid.add(expDate, 8, 6);
				grid.add(prodExpDateLbl, 9, 6);
				grid.add(qty, 0, 7);
				grid.add(prodQtyLbl, 1, 7);
				
				Stage stage = new Stage();
				Scene scene = new Scene(grid);
				stage.setTitle(product.getProdName());
				stage.setScene(scene);
				stage.setAlwaysOnTop(true);
				stage.show();
			}
			catch (FileNotFoundException ex) 
			{
				System.out.println("Error. Product image \"" + product.getProdImgSrc() + "\" not found");
			}
		});
		
		productPane.getChildren().addAll(productPaneArr, btn);
		
		return productPane;
	}
}
