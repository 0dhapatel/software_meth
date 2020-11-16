package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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

public class SampleController {

	private static Order order;

	/**
	 * Formats a double parameter to two decimal places of precision
	 */
	private static DecimalFormat df2 = new DecimalFormat("0.00");

	final int MAX_INGREDIENTS = 6;
	final double PER_EXTRA = 1.99;

	public ComboBox<String> sandwiches;
	public ListView<String> basic;
	public ListView<String> extra;
	public ListView<String> extraToAdd;
	public ImageView imageChooser;

	ObservableList<String> type = FXCollections.observableArrayList("Chicken", "Beef", "Fish");
	ObservableList<String> basicIngredients;
	ObservableList<String> extras = FXCollections.observableArrayList("Tomatoes", "Lettuce", "Onions", "Olives",
			"Ketchup", "Chipotle", "Cheese", "Ranch", "Mayo", "Spinach");
	ObservableList<String> extrasToAdd = FXCollections.observableArrayList();

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
		prices.setText("$" + df2.format(ordered.price()));
		extra.setItems(extras);
		extraToAdd.setItems(extrasToAdd);
		extra.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		extraToAdd.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	public void addExtra(ActionEvent event) {
		ObservableList<String> selectedItems = extra.getSelectionModel().getSelectedItems();
		if(selectedItems.size() == 0) {
			output.appendText("Click an Extra Ingredient to be added. Maximum Extra Ingredient is 6.\n");
			return;
		}
		if (extrasToAdd.size() + selectedItems.size() <= MAX_INGREDIENTS) {
			Sandwich item;
			switch (sandwiches.getValue()) {
			case "Beef":
				item = new Beef();
				break;
			case "Fish":
				item = new Fish();
				break;
			default:
				item = new Chicken();
			}
			extrasToAdd.addAll(selectedItems);
			extras.removeAll(selectedItems);
			extra.setItems(extras);
			extraToAdd.setItems(extrasToAdd);
			prices.setText("$" + df2.format(item.price() + extrasToAdd.size()*PER_EXTRA));
			output.appendText("Extra Ingredients have been added\n");
		}
		else {
			output.appendText("Extra Ingredient cannot be Added. Maximum Extra Ingredient is 6.\n");
		}
	}

	public void removeExtra(ActionEvent event) {
		if(extrasToAdd.size() == 0) {
			output.appendText("Extra Ingredients has already been removed\n");
			return;
		}
		ObservableList<String> selectedItems = extraToAdd.getSelectionModel().getSelectedItems();
		Sandwich item;
		switch (sandwiches.getValue()) {
		case "Beef":
			item = new Beef();
			break;
		case "Fish":
			item = new Fish();
			break;
		default:
			item = new Chicken();
		}
		extras.addAll(selectedItems);
		extrasToAdd.removeAll(selectedItems);
		extra.setItems(extras);
		extraToAdd.setItems(extrasToAdd);
		output.appendText("Extra Ingredients have been removed\n");
		prices.setText("$" + df2.format(item.price() + extrasToAdd.size()*PER_EXTRA));
	}
	
	public void clearList(ActionEvent event) {
		if(extrasToAdd.size() == 0) {
			output.appendText("Extra Ingredients has already been cleared\n");
			return;
		}
		Sandwich item;
		switch (sandwiches.getValue()) {
		case "Beef":
			item = new Beef();
			break;
		case "Fish":
			item = new Fish();
			break;
		default:
			item = new Chicken();
		}
		extras.addAll(extrasToAdd);
		extrasToAdd.removeAll(extrasToAdd);
		extra.setItems(extras);
		extraToAdd.setItems(extrasToAdd);
		output.appendText("Extra Ingredients has been cleared\n");
		prices.setText("$" + df2.format(item.price() + extrasToAdd.size()*PER_EXTRA));
	}

	public void comboClicked(ActionEvent event) {
		Sandwich item;
		switch (sandwiches.getValue()) {
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
		prices.setText("$" + df2.format(item.price() + extrasToAdd.size()*PER_EXTRA));
	}


	public void addOrder(ActionEvent event) {
		Sandwich item;
		switch (sandwiches.getValue()) {
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
		ArrayList<Extra> ingredients = new ArrayList<Extra>();
		for (int i = 0; i < extrasToAdd.size(); i++) {
			String extra = extrasToAdd.get(i);
			ingredients.add(new Extra(extra));
		}
		if (ingredients.size() > 0) {
			item.extras.addAll(ingredients);
		}
		if (order.add(obj)) {
			output.appendText("Order Added\n");
		} else {
			output.appendText("Order Cannot Be Added\n");
		}
		clearList(event);
		initialize();
	}

	public void showOrder(ActionEvent event) {
		try {
			Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("OrderDetail.fxml"));
			Scene scene = new Scene(root, 700, 700);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Order Detail");
			output.clear();
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
