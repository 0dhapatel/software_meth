package application;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class SampleController implements Initializable{
	
	private Order order;
	
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sandwiches.setItems(type);
		Sandwich ordered;
		Image img;
		if(Order.lineNumber == 0) {
			order = new Order();
			sandwiches.setValue("Chicken");
			ordered = new Chicken();
			img = new Image("Chicken.jpg");
		} /*else {
			addButton.setText("Update Order");
			order = new Order();
			sandwiches.setValue("Fish");
			ordered = new Fish();
			img = new Image("Fish.jpg");
			Order.lineNumber = 0;
		}*/ else {
			Order.lineNumber = 0;
			addButton.setText("Update Order");
			OrderLine editOrder = order.startOrder();
			ordered = editOrder.getSandwich();
			sandwiches.setValue(ordered.type());
			img = new Image(ordered.type()+".jpg");
		}
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
		OrderLine obj = new OrderLine(item, order.lineNum()+1);
		Boolean addingOrder = order.add(obj);
		output.appendText(addingOrder +"Order added\n");
	}
	
	public void showOrder(ActionEvent event) {
		if(order.lineNum() == 0) { 
			 output.appendText("Nothing in Order\n"); 
		} else {
			ArrayList<String> print = order.printOrder(); 
			for (int i =0; i<print.size(); i++) {
				output.appendText(print.get(i)+"\n"); 
			}
		}
		 
	}
	
}
