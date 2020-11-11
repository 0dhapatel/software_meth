package application;

import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;

public class SampleController {
	
	public ComboBox comboBox;
	
	
	public void initialize() {
	    comboBox.getItems().removeAll(comboBox.getItems());
	    comboBox.getItems().addAll("Option A", "Option B", "Option C");
	    comboBox.getSelectionModel().select("Option B");
	}
}
