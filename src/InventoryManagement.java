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

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InventoryManagement extends FeaturePage{
	
	Stage stage = new Stage();
	Scene scene2;
	
	public InventoryManagement()
	{
		super();
	}
	
	/**
	 *  This method check shows all the products that are available in a more simple form (listed form)
	 *  
	 * @param productList
	 *  			method receiving an array list that stores all the products
	 * @return Scene : viewInv scene
	 */
	public Scene viewInv (ArrayList<Product> productList)
	{
		
		ArrayList<Label> labelList = new ArrayList<Label>();		//labelList contains the "table headers" when displaying the list of products
		StackPane mainPane = new StackPane();						//the main pane containing the background image 
		
		BorderPane borderPane = new BorderPane();					//the border pane that goes on top of the stack pane, setting HBox topBar as top and the pane that display the list in the center
		
		try
		{
			ImageView img = new ImageView (new Image (new FileInputStream (BACKGROUND_IMG), 1205, 800, false, false));
			mainPane.getChildren().add(img);
			
			
		}catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: " + BACKGROUND_IMG + " not found");
		}
		
		HBox topBar = new HBox(15); //The header bar
		topBar.setPadding(new Insets(10, 10, 10, 10));
		topBar.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		topBar.setMinHeight(40);
		topBar.getChildren().add(createInterfaceBtns());
		topBar.getChildren().add(new Label("																			"));
		//to create the huge gaps between the green and the button to the most right hand side of the top panel hahaha
	
		
		BorderPane blueBdrPane = new BorderPane(); //borderPane center containing scrollPane containing gridPane
		blueBdrPane.setPadding(new Insets(15, 30, 30, 30));
		blueBdrPane.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		blueBdrPane.setMaxSize(1050, 600);
		
		ScrollPane scrollPane = new ScrollPane(); //The scrollpane containing gridpane containing the products
		scrollPane.setMaxHeight(600);
		scrollPane.setPrefWidth(800);
		scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
		scrollPane.setPannable(true);
		
		GridPane bluePane = new GridPane();
		bluePane.setVgap(20);
		bluePane.setHgap(55);
		
		DropShadow shadow = new DropShadow();
		
		Button InvList = new Button("Inventory List");				//when clicked, display the product list 
		
		InvList.setStyle("-fx-background-color: #4286F4; -fx-text-fill: white; -fx-font-weight:bold");
		
		InvList.setOnMouseEntered(e -> {
			InvList.setEffect(shadow);
			InvList.setStyle("-fx-background-color: #5997F9; -fx-text-fill: white; -fx-font-weight:bold");
		});
		
		InvList.setOnMouseExited(e->{
			
			InvList.setEffect(null);
			InvList.setStyle("-fx-background-color: #4286F4; -fx-text-fill: white; -fx-font-weight:bold");
			});
		
		Button stockList = new Button("Stock List");				//when clicked, show the graph of total products for each category pf the products
		
		stockList.setStyle("-fx-background-color: #DB0D70; -fx-text-fill: white; -fx-font-weight:bold");
		
		stockList.setOnMouseEntered(e -> {
			stockList.setEffect(shadow);
			stockList.setStyle("-fx-background-color: #FC2D90; -fx-text-fill: white; -fx-font-weight:bold");
		});
		
		stockList.setOnMouseExited(e->{
			
			stockList.setEffect(null);
			stockList.setStyle("-fx-background-color: #DB0D70; -fx-text-fill: white; -fx-font-weight:bold");
			});
		
		HBox innerTopBar = new HBox(10);
		innerTopBar.setPadding(new Insets(0,0,20,0));
		innerTopBar.setAlignment(Pos.CENTER);
	
		Label title = new Label("\t\t\tInventory List\t\t\t");
		title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-border-width: 0 0 1 0; -fx-border-color: white");
		title.setTextFill(Color.WHITE);
		title.setAlignment(Pos.CENTER);
		
		Label num = new Label("No.");
		num.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		num.setTextFill(Color.WHITE);
		
		Label space = new Label("");
		space.setMinWidth(50);
		
		Label cd = new Label ("Code");
		cd.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		cd.setTextFill(Color.WHITE);
		
		Label namelbl= new Label ("Name");
		namelbl.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		namelbl.setTextFill(Color.WHITE);
		
		Label qty = new Label("Qty");
		qty.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		qty.setTextFill(Color.WHITE);
		
		labelList.add(num);
		labelList.add(space);
		labelList.add(cd);
		labelList.add(namelbl);
		labelList.add(qty);
		
		ComboBox <String> combobox = new ComboBox <>();
		combobox.getItems().addAll("All", "Less than 50", "Between 50 and 100", "More than 100");
	
		Button show = new Button("Show");		//change the display of the list of items when clicked
		
		show.setOnAction(e-> {	
			try{
				if(combobox.getValue().equals("All"))
				{	
						inventoryList(bluePane, productList, labelList, combobox, show);
				}
				else if(combobox.getValue().equals("Less than 50"))
				{	
						displaySpecificQty(bluePane, productList,labelList, combobox, show, 0, 50);
				}
				else if(combobox.getValue().equals("Between 50 and 100"))
				{
						displaySpecificQty(bluePane, productList,labelList, combobox, show, 50, 100);
				}
				else if(combobox.getValue().equals("More than 100"))
				{
						displaySpecificQty(bluePane, productList,labelList, combobox, show, 100, 500);
				}	
			}
			catch(FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Error: Product Image not found");
			}
		} );
		
		InvList.setOnAction(e->{
			
			innerTopBar.getChildren().clear();
			innerTopBar.getChildren().add(title);
			
			blueBdrPane.setTop(innerTopBar);
			try
			{
				inventoryList(bluePane, productList, labelList, combobox, show);
				combobox.getSelectionModel().selectFirst();
				
			}catch(FileNotFoundException ex)
			{
				JOptionPane.showMessageDialog(null, "Error: Product Image not found");
			}
			
		});
		 
		stockList.setOnAction(e->{			//setOnAction fo button that shows the graph 
			chart(productList);
			stage.show();
		});
		
		topBar.getChildren().add(InvList);
		topBar.getChildren().add(stockList);
		
		scrollPane.setContent(bluePane);
		blueBdrPane.setCenter(scrollPane);
		
		borderPane.setTop(topBar);
		borderPane.setCenter(blueBdrPane);
		
		mainPane.getChildren().add(borderPane);
		
		Scene scene = new Scene(mainPane);
		return scene;
	}
	
	
	/**
	 * Generates the whole list of the stocks in a list form 
	 * 
	 * @param bluePane
	 * 				method receiving gridPane which contains the list of items that are going to be displayed 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 * @param labelList
	 * 				method receiving the headers of the display list
	 * @param combobox
	 * 				method receiving a dropbox
	 * @param show
	 * 				method receiving a button show that appears beside the dropbox
	 * @throws FileNotFoundException
	 */
	public void inventoryList (GridPane bluePane, ArrayList<Product> productList, ArrayList<Label> labelList, ComboBox <String> combobox, Button show) throws FileNotFoundException
	{
		
		bluePane.getChildren().clear(); //Empty the gridpane and list of buttons for a new list of products
		
		String name= "";
		int cur = 0;
		int cctr1 = 1, cctr2 = 2, cctr3 = 3, cctr4 = 4, cctr5 = 5; 	//column counter
		int rctr2 = 2; //row counter
		
		Label info = new Label("Show items with quantity: ");
		info.setStyle("-fx-font-size: 14px; -fx-font-weight: bold ");
		info.setTextFill(Color.WHITE);
		
		bluePane.setHalignment(info, HPos.RIGHT);
		bluePane.setHalignment(show, HPos.RIGHT);
		
		bluePane.add(info, 4, 0);
		bluePane.add(combobox, 5, 0);
		bluePane.add(show, 5, 0, 2, 1);
		bluePane.add(labelList.get(0), 1, 1);
		bluePane.add(labelList.get(1), 2, 1);
		bluePane.add(labelList.get(2), 3, 1);
		bluePane.add(labelList.get(3), 4, 1);
		bluePane.add(labelList.get(4), 5, 1);
		
		for(int i = 0; i < productList.size(); i++)
		{	
			cur = i +1;
			name = Integer.toString(cur);
			Label n = new Label("  "+name);
			n.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
			n.setTextFill(Color.WHITE);
			
			bluePane.add(n, cctr1, rctr2);
			bluePane.add(new ImageView (new Image (new FileInputStream (productList.get(i).getProdImgSrc()), 80, 80, false, false)) , cctr2, rctr2);
		
			
			Label prodCode = new Label(productList.get(i).getProdCode());
			prodCode.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
			prodCode.setTextFill(Color.WHITE);
			bluePane.add(prodCode, cctr3, rctr2);
			
			Label prodName = new Label(productList.get(i).getProdName());
			prodName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
			prodName.setTextFill(Color.WHITE);
			bluePane.add(prodName, cctr4, rctr2);
			
			Label prodQty = new Label(Integer.toString(productList.get(i).getQty()));
			prodQty.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
			prodQty.setTextFill(Color.WHITE);
			bluePane.add(prodQty, cctr5, rctr2);
			
			rctr2++;		//so that it goes to the next row
		}//end for loop
		
	}//end method
	
	/**
	 * Generates list of products within specific ranges
	 * 
	 * @param bluePane
	 *  			method receiving gridPane which contains the list of items that are going to be displayed 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 * @param labelList
	 * 				method receiving the headers of the display list
	 * @param combobox
	 * 				method receiving a dropbox
	 * @param show
	 * 				method receiving a button show that appears beside the dropbox
	 * @param qty1
	 * 				method receiving the int variable that acts as the lower limit of the range
	 * @param qty2
	 * 				method receiving the int variable that acts as the higher limit of the range
	 * @throws FileNotFoundException
	 */
	public void displaySpecificQty(GridPane bluePane, ArrayList<Product> productList, ArrayList<Label> labelList, ComboBox <String> combobox, Button show, int qty1, int qty2) throws FileNotFoundException
	{
		bluePane.getChildren().clear(); //Empty the gridpane and list of buttons for a new list of products
		
		int num =1;
		String numString ="";
		int cctr1 = 1, cctr2 = 2, cctr3 = 3, cctr4 = 4, cctr5 = 5; 	//column counter
		int rctr2 = 2; //row counter
		
		for(int i = 0; i < productList.size(); i++)
		{		
			if(productList.get(i).getQty() >= qty1 && productList.get(i).getQty() < qty2)
			{
				
				numString = Integer.toString(num);
				Label n = new Label("  "+numString);
				n.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
				n.setTextFill(Color.WHITE);
				
				bluePane.add(n, cctr1, rctr2);
				bluePane.add(new ImageView (new Image (new FileInputStream (productList.get(i).getProdImgSrc()), 80, 80, false, false)) , cctr2, rctr2);
				
				Label prodCode = new Label(productList.get(i).getProdCode());
				prodCode.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
				prodCode.setTextFill(Color.WHITE);
				bluePane.add(prodCode, cctr3, rctr2);
				
				Label prodName = new Label(productList.get(i).getProdName());
				prodName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
				prodName.setTextFill(Color.WHITE);
				bluePane.add(prodName, cctr4, rctr2);
				
				Label prodQty = new Label(Integer.toString(productList.get(i).getQty()));
				prodQty.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
				prodQty.setTextFill(Color.WHITE);
				bluePane.add(prodQty, cctr5, rctr2);
				
				rctr2++;
				num++;
			}
		}//end for loop
		
		Label info = new Label("Show items with quantity: ");
		info.setStyle("-fx-font-size: 14px; -fx-font-weight: bold ");
		info.setTextFill(Color.WHITE);
		
		bluePane.setHalignment(info, HPos.RIGHT);
		bluePane.setHalignment(show, HPos.RIGHT);
		
		bluePane.add(info, 4, 0);
		bluePane.add(combobox, 5, 0);
		bluePane.add(show, 5, 0, 2, 1);
		bluePane.add(labelList.get(0), 1, 1);
		bluePane.add(labelList.get(1), 2, 1);
		bluePane.add(labelList.get(2), 3, 1);
		bluePane.add(labelList.get(3), 4, 1);
		bluePane.add(labelList.get(4), 5, 1);
		
	}//end method
	
	/**
	 * method that display the bar graph that shows the total qty of products for each category
	 * 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 */
	public void chart(ArrayList<Product> productList)
	{
		BorderPane blueTopPane = new BorderPane(); 
		blueTopPane.setPadding(new Insets(15, 30, 30, 30));
		
		final String meats = "Meats";
	    final String soups = "Soups";
	    final String fruits = "Fruits";
	    final String others = "Others";
	    
	    double tMeats = 0;
	    double tSoups = 0;
	    double tFruits = 0;
	    double tOthers = 0;
	   
			for(int i = 0; i < productList.size(); i++)
			{	
				if (productList.get(i).getProdCode().charAt(0) == ('M'))
				{
					tMeats += productList.get(i).getQty();
				}
				else if (productList.get(i).getProdCode().charAt(0) == ('S'))
				{
					tSoups += productList.get(i).getQty();
				}
				else if (productList.get(i).getProdCode().charAt(0) == ('F'))
				{
					tFruits += productList.get(i).getQty();
				}
				else if (productList.get(i).getProdCode().charAt(0) == ('A'))
				{
					tOthers += productList.get(i).getQty();
				}
			
			}//end for loop
	    
	    final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Current Stocks Chart");
        xAxis.setLabel("Product");       
        yAxis.setLabel("Quantity");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2017");       
        series1.getData().add(new XYChart.Data(meats, tMeats));
        series1.getData().add(new XYChart.Data(soups, tSoups));
        series1.getData().add(new XYChart.Data(fruits, tFruits));
        series1.getData().add(new XYChart.Data(others, tOthers));    
        
        bc.getData().add(series1);
        blueTopPane.setCenter(bc);
        
        
        scene2 = new Scene (blueTopPane,800,600);
		stage.setTitle("Current Stocks Chart");
		stage.setScene(scene2);
		for(Node n:bc.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #4286F4;");
        }
		
	}
}