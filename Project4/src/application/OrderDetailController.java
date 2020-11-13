package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class OrderDetailController {

    @FXML
    private ListView<String> orderTextArea;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField priceTextField;

    @FXML
    private Button saveButton;

    //Order order = new Order();
    @FXML
    void addSameOrder(ActionEvent event) {
    	
    	
    	
    }

    @FXML
    void clearOrder(ActionEvent event) {
    	
    	orderTextArea.getItems().clear();
    	priceTextField.setText("0");

    }

    @FXML
    void removeOrder(ActionEvent event) {

    }

    @FXML
    void returnOrderScreen(ActionEvent event) {

    }
    
    @FXML
    void saveOrder(ActionEvent event) throws IOException {
    	
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		
		//get the reference of the target file
		File targeFile = chooser.showSaveDialog(stage);
			
		BufferedWriter bf = new BufferedWriter(new FileWriter(targeFile));
		//bf.write(order.printOrder());
		bf.flush();
		bf.close();

    }
    
    public void setListView(ArrayList<String> orderLines) {
    	ObservableList<String> orderLinesList = FXCollections.observableArrayList(orderLines);
    	orderTextArea.setItems(orderLinesList);
    }


}