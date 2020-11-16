package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class OrderDetailController {
	
	private Order order = SampleController.passOrder();
	
	/**
	 * Formats a double parameter to two decimal places of precision
	 */
	private static DecimalFormat df2 = new DecimalFormat("0.00");

    @FXML
    private ListView<String> orderList;
    ObservableList<String> orderedSandwich;

    @FXML
    private Button back;
    
    @FXML
    private TextField price;
    
    @FXML
    private TextArea output2;

    public void initialize() {
    	orderedSandwich = FXCollections.observableArrayList(order.printOrder());
    	orderList.setItems(orderedSandwich);
    	price.setText("$"+df2.format(order.totalAmount()));
	}
    
    @FXML
    void addSameOrder(ActionEvent event) {
    	if (orderList.getSelectionModel().getSelectedItem() == null) {
    		output2.appendText("Please Select a Sandwich to Add to Order\n");
    		return;
    	}
    	String orderedSandwich = orderList.getSelectionModel().getSelectedItem();
    	int index = Character.getNumericValue(orderedSandwich.charAt(0));
    	OrderLine sandwich = order.sameOrder(index-1);
    	OrderLine copy = new OrderLine(sandwich.getSandwich());
    	order.add(copy);
    	initialize();
    	output2.appendText("Sandwich Added to Order\n");
    }

    @FXML
    void clearOrder(ActionEvent event) {
    	order.clearOrder();
    	initialize();
    	output2.appendText("Order Cleared\n");
    }

    @FXML
    void removeOrder(ActionEvent event) {
    	if (orderList.getSelectionModel().getSelectedItem() == null) {
    		output2.appendText("Please Select a Sandwich to Remove to Order\n");
    		return;
    	}
    	String orderedSandwich = orderList.getSelectionModel().getSelectedItem();
    	int index = Character.getNumericValue(orderedSandwich.charAt(0));
    	OrderLine sandwich = order.sameOrder(index-1);
    	order.remove(sandwich);
    	initialize();
    	output2.appendText("Sandwich Removed to Order\n");
    }

    @FXML
    void returnOrderScreen(ActionEvent event) {
    	back.getScene().getWindow().hide();
    }
    
    @FXML
    void saveOrder(ActionEvent event) throws IOException {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
		//write code to write to the file.
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
			ArrayList<String> print = order.printOrder();
			for (int i = 0; i<print.size(); i++) {
				writer.write(print.get(i)+"\n"); 
			}
			writer.close();
			output2.appendText("Export Complete\n");
		} catch (IOException e) {
			output2.appendText("File cannot be written.\n");
		}
    }

}
