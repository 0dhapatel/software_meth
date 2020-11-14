package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class SampleController{
	
	private static Order order;
	
	/**
	 * Formats a double parameter to two decimal places of precision
	 */
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	public ComboBox<String> sandwiches;
	public ListView<String> basic;
	public ImageView imageChooser;
	
	ObservableList<String> type = FXCollections.observableArrayList("Chicken", "Beef", "Fish");
	ObservableList<String> basicIngredients;
	
	public TextArea output;
	public Button addButton;
	public TextField prices;
	public Button orderShow;
	
	protected static Order passOrder() {
		return order;
	}
	
	public void initialize() {
		sandwiches.setItems(type);
		Sandwich ordered;
		Image img;
		if (Order.lineNumber == 0) {
			order = new Order();
		}
		sandwiches.setValue("Chicken");
		ordered = new Chicken();
		img = new Image("Chicken.jpg");
		
		basicIngredients = FXCollections.observableArrayList(Arrays.asList(ordered.basicIngredient().split(",")));
		basic.setItems(basicIngredients);
		imageChooser.setImage(img);
		prices.setText("$"+df2.format(ordered.price()));
		
	}
	
	public void comboClicked (ActionEvent event) {
		Sandwich item;
		switch(sandwiches.getValue()) {
		case "Beef":
			item = new Beef();
			break;
		case "Fish":
			item = new Fish();
			break;
		default:
			item = new Chicken();
		}
		basicIngredients = FXCollections.observableArrayList(Arrays.asList(item.basicIngredient().split(",")));
		basic.setItems(basicIngredients);
		String imgName = sandwiches.getValue() + ".jpg";
		Image img = new Image(imgName);
		imageChooser.setImage(img);
		prices.setText("$"+df2.format(item.price()));
	}
	
	public void clearList(ActionEvent event) {
		if(order.lineNum() == 0) { 
			 output.appendText("Nothing in Order\n"); 
		} else {
			ArrayList<String> print = order.printOrder(); 
			for (int i = 0; i<print.size(); i++) {
				output.appendText(print.get(i)+"\n"); 
			}
		}
	}
	
	public void addOrder(ActionEvent event) {
		Sandwich item;
		switch(sandwiches.getValue()) {
		case "Beef":
			item = new Beef();
			break;
		case "Fish":
			item = new Fish();
			break;
		default:
			item = new Chicken();
		}
		OrderLine obj = new OrderLine(item);
		if (order.add(obj)) {
			output.appendText("Order Added\n");
		} else {
			output.appendText("Order Cannot Be Added\n");
		}
		initialize();
	}
	
	public void showOrder(ActionEvent event) {
		try {
			Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("OrderDetail.fxml"));
			Scene scene = new Scene(root,700,700);
			Stage stage = new Stage() ;
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Order Detail");
			output.clear();
			initialize();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
