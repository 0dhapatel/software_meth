package application;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class SampleController implements Initializable{

	public ComboBox<String> sandwiches;
	public ListView<String> basic;
	public ImageView imageChooser;
	
	ObservableList<String> type = FXCollections.observableArrayList("Chicken", "Beef", "Fish");
	ObservableList<String> basicIngredients;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sandwiches.setItems(type);
		sandwiches.setValue("Chicken");
		Sandwich defaultItem = new Chicken();
		basicIngredients = FXCollections.observableArrayList(Arrays.asList(defaultItem.basicIngredient().split(",")));
		basic.setItems(basicIngredients);
		//Image image = new Image(path.toURI());
		//imageChooser = new ImageView(image);
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
	}
	
}
