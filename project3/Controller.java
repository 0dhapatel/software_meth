
package application;

public class Controller {
  @FXML
    void importFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Source File for the Import");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
		//write code to read from the file.
    }
    
    @FXML
    void exportFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file
		//write code to write to the file.
    }
	public void makeWithdraw() {
        double amount = Double.parseDouble(withdrawField.getText());
        account1.withdraw(amount);
        balanceField.setText("$" + String.format("%.2f", account1.getBalance())); //String.valueOf(account1.getBalance()));
        withdrawField.setText("");
    }

    public void makeDeposit() {
        double amount = Double.parseDouble(depositField.getText());
        account1.deposit(amount);
        balanceField.setText("$" + String.format("%.2f", account1.getBalance())); //String.valueOf(account1.getBalance()));
        depositField.setText("");
    }
}
