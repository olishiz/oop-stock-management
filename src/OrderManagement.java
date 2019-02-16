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

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class OrderManagement extends FeaturePage {
	
	int totalProd;
	int rowctr;
	boolean alreadyOrdered = false; 
	Stage stage = new Stage();
	Stage stage2 = new Stage();
	Scene scene2, scene3, scene4;
	
	public OrderManagement()
	{
		super();
	}
	
	/**
	 * Shows all products that are already out of stock in a list 
	 * 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 * @return Scene : viewOutOfStockScene scene
	 */
	public Scene viewOutOfStockScene (ArrayList<Product> productList)
	{
		
		StackPane mainPane = new StackPane();			//the main pane containing the background image 
		
		BorderPane borderPane = new BorderPane();		//the border pane that goes on top of the stack pane, setting HBox topBar as top and the pane that display the list in the center
		
		try
		{
			ImageView img = new ImageView (new Image (new FileInputStream (BACKGROUND_IMG), 1200, 800, false, false));
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
		topBar.getChildren().add(new Label("																				"));
		//to create the huge gaps between the green and the button to the most right hand side of the top panel hahaha 
		
		
		BorderPane blueBdrPane = new BorderPane(); //borderPane center containing scrollPane containing gridPane
		blueBdrPane.setPadding(new Insets(15, 30, 30, 30));
		blueBdrPane.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		blueBdrPane.setMaxSize(1000, 600);
		
		
		HBox innerTopBar = new HBox(10);
		
		Label title = new Label("\t\t\tOut Of Stock Products\t\t\t");
		title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-border-width: 0 0 1 0; -fx-border-color: white");
		title.setTextFill(Color.WHITE);
		title.setAlignment(Pos.CENTER);
		
		innerTopBar.setAlignment(Pos.CENTER);
		innerTopBar.getChildren().add(title);
		
		ScrollPane scrollPane = new ScrollPane(); //The scrollpane containing gridpane containing the products
		scrollPane.setMaxHeight(600);
		scrollPane.setPrefWidth(800);
		scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
		scrollPane.setPannable(true);
		
		GridPane bluePane = new GridPane();		//the gridPane contains the list of items that are going to be displayed
		bluePane.setVgap(20);
		bluePane.setHgap(60);
		
	
		try
		{
			listOutOfStockProds(bluePane, productList);
			
		}catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, "Error: Product Image not found");
		}
		
		DropShadow shadow = new DropShadow();
		
		Button view = new Button("View Orders");		//Only displays orders after orders are made
		
		view.setStyle("-fx-background-color: #4286F4; -fx-text-fill: white; -fx-font-weight:bold");
		
		view.setOnMouseEntered(e -> {
			view.setEffect(shadow);
			view.setStyle("-fx-background-color: #5997F9; -fx-text-fill: white; -fx-font-weight:bold");
		});
		
		view.setOnMouseExited(e->{
			
			view.setEffect(null);
			view.setStyle("-fx-background-color: #4286F4; -fx-text-fill: white; -fx-font-weight:bold");
			});
		
		view.setOnAction(e->{
			HBox warn = new HBox(10);
			warn.setPadding(new Insets(20,20,20,20));
			warn.setAlignment(Pos.CENTER);
			
			Label warning = new Label("View Orders is available only after orders are made");
			warning.setStyle("-fx-font-size: 14px");
			warning.setTextFill(Color.RED);
			
			warn.getChildren().add(warning);
			scene4 = new Scene (warn, 400, 80);
			stage2.setTitle("Warning");
			stage2.setScene(scene4);
			stage2.show();
		});
		
		topBar.getChildren().add(view);
		
		Button order = new Button ("Order");			//to order the products and add the ordered items to a display list, accessible through "view orders" button
		order.setStyle("-fx-background-color: #C40000; -fx-text-fill: white; -fx-font-weight:bold");
		
		order.setOnMouseEntered(e -> {
			order.setEffect(shadow);
			order.setStyle("-fx-background-color: #E81717; -fx-text-fill: white; -fx-font-weight:bold");
		});
		
		order.setOnMouseExited(e->{
			
			order.setEffect(null);
			order.setStyle("-fx-background-color: #C40000; -fx-text-fill: white; -fx-font-weight:bold");
			});
		
		order.setOnAction(e->{
			//JOptionPane.showConfirmDialog(null, "choose one", "choose one", JOptionPane.YES_NO_OPTION);		//JOptionPane doesn't run on my Mac for some reason
				cfmMessage(topBar, bluePane,productList, view);
		});
		
		Button ordered = new Button ("All Items have been ordered");
		ordered.setStyle("-fx-background-color: #1AC919; -fx-text-fill: white; -fx-font-weight:bold");
		
		if(alreadyOrdered)
		{
			rowctr = totalProd+=3;
			bluePane.add(ordered, 5, rowctr);
		}
		
		topBar.getChildren().add(order);	
		
		scrollPane.setContent(bluePane);
		blueBdrPane.setTop(innerTopBar);
		blueBdrPane.setCenter(scrollPane);
		
		borderPane.setTop(topBar);
		borderPane.setCenter(blueBdrPane);
		
		mainPane.getChildren().add(borderPane);
		Scene scene = new Scene (mainPane);
		return scene;
		
	}
	
	/**
	 * Generates the list of out of stock products (quantity equals zero)
	 * 
	 * @param bluePane
	 *  			method receiving gridPane which contains the list of items that are going to be displayed 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 * @throws FileNotFoundException
	 */
	public void listOutOfStockProds (GridPane bluePane, ArrayList<Product> productList) throws FileNotFoundException
	{
		bluePane.getChildren().clear(); //Empty the gridpane and list of buttons for a new list of products
		
		String name= "";
		int cur = 0;
		int cctr1 = 1, cctr2 = 2, cctr3 = 3, cctr4 = 4, cctr5 = 5; 	//column counter
		int rctr2 = 2; //row counter
		totalProd = 0;
		
		Label num = new Label("No.");
		num.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		num.setTextFill(Color.WHITE);
		
		Label cd = new Label ("Code");
		cd.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		cd.setTextFill(Color.WHITE);
		
		Label namelbl= new Label ("Name");
		namelbl.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		namelbl.setTextFill(Color.WHITE);
		
		Label price = new Label("Price");
		price.setStyle("-fx-font-size: 22px; -fx-font-weight: bold ; -fx-underline: true");
		price.setTextFill(Color.WHITE);
		
		Label space = new Label("");
		space.setMinWidth(50);
		
		bluePane.add(num, 1, 1);
		bluePane.add(space, 2, 1);
		bluePane.add(cd, 3, 1);
		bluePane.add(namelbl, 4, 1);
		bluePane.add(price, 5, 1);
		cur = 1;
		
		for(int i = 0; i < productList.size(); i++)
		{	
			if(productList.get(i).getQty() == 0)
			{
				
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
				
				Label prodPrice = new Label(String.format("%.2f", productList.get(i).getPrice()));
				prodPrice.setStyle("-fx-font-size: 16px; -fx-font-weight: bold ");
				prodPrice.setTextFill(Color.WHITE);
				bluePane.add(prodPrice, cctr5, rctr2);
				
				rctr2++;
				
				totalProd++;
				
				cur++;
			}//end if
		}//end for loop
	}//end method
	
	/**
	 * a "pop up" stage that asks whether the user would like to confirms their order(s) or not
	 * 
	 * @param topBar
	 * 				method receiving topBar which contains buttons such as back, main menu, logout as well as buttons in the feature itself on the right hand side
	 * @param bluePane
	 *  			method receiving gridPane which contains the list of items that are going to be displayed 
	 * @param productList
	 *   			method receiving an array list that stores all the products
	 * @param view
	 * 				methpd recieving button view that shows the items that have been ordered
	 */
	public void cfmMessage(HBox topBar, GridPane bluePane, ArrayList<Product> productList, Button view) 
	{
		
		VBox cfm = new VBox(10);
		cfm.setAlignment(Pos.CENTER);
		cfm.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		
		stage.show();
		Label q = new Label ("Are you sure you want to place the orders?\n\t\t\tBulk Order Qty: 100");
		q.setTextFill(Color.WHITE);
		cfm.setAlignment(Pos.CENTER);
		cfm.getChildren().add(q);
		
		Button yes = new Button ("Yes");
		Button no = new Button ("No");
		yes.setPrefSize(50, 10);
		no.setPrefSize(50, 10);
		
		DropShadow shadow = new DropShadow();
		
		yes.setStyle("-fx-background-color:#1AC919; -fx-text-fill: white; -fx-font-weight:bold");
		
		yes.setOnMouseEntered(e -> {
			yes.setEffect(shadow);
			yes.setStyle("-fx-background-color: #1EEA1D; -fx-text-fill: white; -fx-font-weight:bold");
		});
		
		yes.setOnMouseExited(e->{
			
			yes.setEffect(null);
			yes.setStyle("-fx-background-color: #1AC919; -fx-text-fill: white; -fx-font-weight:bold");
			});
		
		
		no.setStyle("-fx-background-color: #C40000; -fx-text-fill: white; -fx-font-weight:bold");
		
		no.setOnMouseEntered(e -> {
			no.setEffect(shadow);
			no.setStyle("-fx-background-color: #E81717; -fx-text-fill: white; -fx-font-weight:bold");
		});
		
		no.setOnMouseExited(e->{
			
			no.setEffect(null);
			no.setStyle("-fx-background-color: #C40000; -fx-text-fill: white; -fx-font-weight:bold");
			});
		
		
		HBox a = new HBox(30); 	//so that buttons yes and no are arranged side to side
		a.setAlignment(Pos.CENTER);
		a.setPadding(new Insets(10,10,10,20));
		
		a.getChildren().addAll(yes, no);
		cfm.getChildren().add(a);
		
		yes.setOnAction(e2->{
			
			if(!alreadyOrdered)
			{	
				Label qq = new Label("Order has successfully been made.\nReturning to previous page...");
				qq.setTextFill(Color.WHITE);
				cfm.getChildren().add(qq);
				PauseTransition delay = new PauseTransition (Duration.seconds(2.2));				//will automatically close the stage after 2.2 seconds
				delay.setOnFinished(event-> stage.close());
				delay.play();
				
				Button ordered = new Button ("All Items have been ordered");
				ordered.setStyle("-fx-background-color: #1AC919; -fx-text-fill: white; -fx-font-weight:bold");
				
				
					rowctr = totalProd+=3;
					bluePane.add(ordered, 5, rowctr);
					alreadyOrdered = true;
				}else																				//if the users tried to order again after ordering the items before
				{
					Label qq2 = new Label("Order was already made.\nReturning to previous page...");
					qq2.setTextFill(Color.RED);
					cfm.getChildren().add(qq2);
					PauseTransition delay = new PauseTransition (Duration.seconds(2.2));
					delay.setOnFinished(event-> stage.close());
					delay.play();
				}
				
				//now there's a list of ordered products when "view orders" button is clicked
				view.setOnAction(e->{	
					BorderPane outerMiniPane = new BorderPane();
					outerMiniPane.setPadding(new Insets(10,10,10,10));
					outerMiniPane.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
					
					GridPane miniPane = new GridPane();
					miniPane.setVgap(20);
					miniPane.setHgap(25);
					
					try
					{
						miniListOutOfStock(outerMiniPane, miniPane, productList);			//displaying ordered products
					
					}catch (FileNotFoundException ex)
					{
						JOptionPane.showMessageDialog(null, "Error");
					}
					
					scene3 = new Scene (outerMiniPane, 600, 600);
					stage2.setTitle("View Orders");
					stage2.setScene(scene3);
					stage2.show();
							
				});
		
			});
		
			no.setOnAction(e2->{
				
				Label qqq = new Label("Order has not been made.\nReturning to previous page...");
				qqq.setTextFill(Color.WHITE);
				cfm.getChildren().add(qqq);
				PauseTransition delay = new PauseTransition (Duration.seconds(1));
				delay.setOnFinished(event-> stage.close());
				delay.play();
				
			});
			
			scene2 = new Scene (cfm,400,170);
			stage.setTitle("Confirmation");
			stage.setScene(scene2);
		
	}
	
	/**
	 * displaying list of ordered products under "view order" button 
	 * 
	 * @param outerMiniPane
	 * 				method receiving the borderPane that contains the miniPane
	 * @param miniPane
	 * 				method receiving the borderPane which contains the list of products to be displayed
	 *  @param productList
	 *   			method receiving an array list that stores all the products
	 * @throws FileNotFoundException
	 */
	public void miniListOutOfStock(BorderPane outerMiniPane, GridPane miniPane, ArrayList<Product> productList) throws FileNotFoundException		
	{
		String name= "";
		int cur = 1;
		int cctr1 = 1, cctr2 = 2, cctr3 = 3, cctr4 = 4, cctr5 = 5; 	//column counter
		int rctr2 = 2; //row counter
		double grandTotal = 0;
		
		Label num = new Label("No.");
		num.setStyle("-fx-font-size: 14px; -fx-font-weight: bold ; -fx-underline: true");
		num.setTextFill(Color.WHITE);
		
		Label cd = new Label ("Code");
		cd.setStyle("-fx-font-size: 14px; -fx-font-weight: bold ; -fx-underline: true");
		cd.setTextFill(Color.WHITE);
		
		Label pName= new Label ("Name");
		pName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold ; -fx-underline: true");
		pName.setTextFill(Color.WHITE);
		
		Label price = new Label("Price");
		price.setStyle("-fx-font-size: 14px; -fx-font-weight: bold ; -fx-underline: true");
		price.setTextFill(Color.WHITE);
		
		Label sub = new Label("Sub Total");
		sub.setStyle("-fx-font-size: 14px; -fx-font-weight: bold ; -fx-underline: true");
		sub.setTextFill(Color.WHITE);
			
		miniPane.add(num, 1, 1);
		miniPane.add(cd, 2, 1);
		miniPane.add(pName, 3, 1);
		miniPane.add(price, 4, 1);
		miniPane.add(sub, 5, 1);
		
		for(int i = 0; i < productList.size(); i++)
		{	
			if(productList.get(i).getQty() == 0)
			{
				name = Integer.toString(cur);
				Label n = new Label("  "+name);
				n.setStyle("-fx-font-size: 14px");
				n.setTextFill(Color.WHITE);
				miniPane.add(n, cctr1, rctr2);
				
				Label prodCode = new Label(productList.get(i).getProdCode());
				prodCode.setStyle("-fx-font-size: 14px ");
				prodCode.setTextFill(Color.WHITE);
				miniPane.add(prodCode, cctr2, rctr2);
				
				Label prodName = new Label(productList.get(i).getProdName());
				prodName.setStyle("-fx-font-size: 14px");
				prodName.setTextFill(Color.WHITE);
				miniPane.add(prodName, cctr3, rctr2);
				
				Label prodPrice = new Label(String.format("%.2f", productList.get(i).getPrice()));
				prodPrice.setStyle("-fx-font-size: 14px");
				prodPrice.setTextFill(Color.WHITE);
				miniPane.add(prodPrice, cctr4, rctr2);
				
				grandTotal +=  productList.get(i).getPrice()*100;
				Label total = new Label(String.format("%.2f", productList.get(i).getPrice()*100));
				total.setStyle("-fx-font-size: 14px");
				total.setTextFill(Color.WHITE);
				miniPane.add(total, cctr5, rctr2);
				
				rctr2++;
				cur++;
			}	
		}
		
		GridPane ttl = new GridPane();
		ttl.setPadding(new Insets(10, 10, 10, 100));
		
		Label ttlLabel = new Label("			Grand Total: RM" + String.format("%.2f", grandTotal));
		ttlLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold ");
		ttlLabel.setTextFill(Color.WHITE);
		ttl.setAlignment(Pos.CENTER);
		ttl.add(ttlLabel, 0, rctr2++);
		
		outerMiniPane.setCenter(miniPane);
		outerMiniPane.setBottom(ttl);
	}
	
	/**
	 * Before leaving the feature, let users know that the "view orders" button will be empty after clicking yes for leaving the feature
	 * 
	 * @param primaryStage
	 * 				method reciving stage which is a new Stage that will appear on top of the main stage
	 * @param menuPage
	 * 				method receiving menuPage which is from the class MainMenu, this is to let the prompt Stage to appear before leaving the feature when the "main menu" button is clicked 
	 * @param curUser
	 * 				method receiving curUser which is from the Staff class that store the user ID of current logged-in user 
	 */
	public void exitMsgWindow(Stage primaryStage, MainMenu menuPage, Staff curUser)
	{
		Stage stage = new Stage();
		VBox outerWarn = new VBox(10);
		outerWarn.setPadding(new Insets(10,10,10,10));
		outerWarn.setAlignment(Pos.CENTER);
		outerWarn.setStyle("-fx-background-color: #2E2F37; -fx-opacity: 0.95;");
		
		stage.show();
		
		Label warning = new Label("Kindly note that View Orders will not be accessible after leaving this page.\nThe current ordered list will be sent to your Gmail. ");
		Label confirm = new Label("Do you wish to return back to the main menu? ");
		warning.setStyle("-fx-font-size: 13px");
		warning.setTextFill(Color.WHITE);
		confirm.setStyle("-fx-font-size: 12px");
		confirm.setTextFill(Color.WHITE);
		
		outerWarn.getChildren().addAll(warning, confirm);
		
		Button yes = new Button ("Yes");
		Button no = new Button ("No");
		yes.setPrefSize(50, 10);
		no.setPrefSize(50, 10);
		
		DropShadow shadow = new DropShadow();
		
		yes.setStyle("-fx-background-color:#1AC919; -fx-text-fill: white; -fx-font-weight:bold");
		
		yes.setOnMouseEntered(e1 -> {
			yes.setEffect(shadow);
			yes.setStyle("-fx-background-color: #1EEA1D; -fx-text-fill: white; -fx-font-weight:bold");
		});
		
		yes.setOnMouseExited(e1->{
			
			yes.setEffect(null);
			yes.setStyle("-fx-background-color: #1AC919; -fx-text-fill: white; -fx-font-weight:bold");
			});
		
		
		no.setStyle("-fx-background-color: #C40000; -fx-text-fill: white; -fx-font-weight:bold");
		
		no.setOnMouseEntered(e1 -> {
			no.setEffect(shadow);
			no.setStyle("-fx-background-color: #E81717; -fx-text-fill: white; -fx-font-weight:bold");
		});
		
		no.setOnMouseExited(e1->{
			
			no.setEffect(null);
			no.setStyle("-fx-background-color: #C40000; -fx-text-fill: white; -fx-font-weight:bold");
			});
		
		
		
		HBox a = new HBox(30); 	//so that buttons yes and no are arranged side to side
		a.setAlignment(Pos.CENTER);
		a.setPadding(new Insets(10,10,10,20));
		
		a.getChildren().addAll(yes, no);
		outerWarn.getChildren().add(a);
		
		Scene scene2 = new Scene(outerWarn, 500, 150);
		stage.setTitle("Warning");
		stage.setScene(scene2);
		
			yes.setOnAction(e1->{
				primaryStage.setTitle("Main Menu");
				primaryStage.setScene(menuPage.mainMenuScene(curUser));
				
				stage.close();
			});
			
			no.setOnAction(e1->{
				stage.close();
			});
	}
}
