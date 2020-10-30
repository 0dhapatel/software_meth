package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;;

public class Controller {
	
	private AccountDatabase database = new AccountDatabase();
	
	public TextField firstName;
	public TextField lastName;
	public TextField month;
	public TextField day;
	public TextField year;
	public TextField balance;
	
	public TextField firstName2;
	public TextField lastName2;
	public TextField amount;
	
	public RadioButton checking;
	public RadioButton savings;
	public RadioButton moneyMarket;
	
	public Button openAccount;
	public Button closeAccount;
	public Button clearForm;
	
	public CheckBox direct;
	public CheckBox loyal;
	
	public MenuItem printAccount;
	public MenuItem printAccountByDate;
	public MenuItem printAccountByName;
	
	public ToggleGroup radioB;
	public ToggleGroup radioBu;
	public TextArea output;
	
	/**
	 * This method splits the user's input into an array.
	 * 
	 * @return new Checking object
	 * @param cmdArr is an array that holds the user's input
	 */
	private Account createAccount (String[] cmdArr) {
		char accountType = cmdArr[0].charAt(0);
		String fname = cmdArr[1];
		String lname = cmdArr[2];
		Profile holder = new Profile(fname, lname);
		double balance = Double.parseDouble(cmdArr[3]);
		Date dateOpen = new Date(Integer.parseInt(cmdArr[4]), Integer.parseInt(cmdArr[5]), Integer.parseInt(cmdArr[6]));
		if (cmdArr[0].equals("C")) {
			boolean directDeposit = Boolean.parseBoolean(cmdArr[7]);
			return new Checking(directDeposit, holder, balance, dateOpen, accountType);
		}
		else if (cmdArr[0].equals("S")) {
			boolean isLoyal = Boolean.parseBoolean(cmdArr[7]);
			return new Savings(isLoyal, holder, balance, dateOpen, accountType);
		}
		int withdrawals = Integer.parseInt(cmdArr[7]);
		return new MoneyMarket(withdrawals, holder, balance, dateOpen, accountType);
	}
	
	/**
	 * This method splits the user's input into an object.
	 * 
	 * @return new Checking, Savings, or Money Market object
	 * @param cmdArr is an array that holds the user's input array
	 */
	private Account accountType(String[] cmdArr) {
		char accountType = cmdArr[0].charAt(0);
		String fname = cmdArr[1];
		String lname = cmdArr[2];
		Profile holder = new Profile(fname, lname);
		if (cmdArr[0].equals("C")) {
			return new Checking(false, holder, 0, null, accountType);
		}
		if (cmdArr[0].equals("S")) {
			return new Savings(false, holder, 0, null, accountType);
		}
		return new MoneyMarket(0, holder, 0, null, accountType);
	}
	
	/**
	 * If the client inputs an 'O', and it's followed by either a 'C', 'S', or 'M',
	 * a checking, savings, or money market account are going to be opened
	 * respectively.
	 * @param cmdArray is an array that holds the user's input
	 */
	private void open(String[] cmdArray) {
		try {
			Account account = createAccount(cmdArray);
			if (database.add(account)) {
				output.appendText("Account opened and added to the database.\n");
			} else {
				output.appendText("Account is already in the database.\n");
			}
		} catch (Exception e) {
			output.appendText("Input data type mismatch.\n");

		}
	}
	
	/**
	 * if the client inputs a 'C', and it's followed by either a 'C', 'S', or 'M', a
	 * checking, savings, or money market account are going to be closed
	 * respectively.
	 * 
	 * @param cmdArray is an array that holds the user's input
	 */
	private void close(String[] cmdArray) {

		try {
			Account checkingAccount = accountType(cmdArray);
			if (database.remove(checkingAccount)) {
				output.appendText("Account closed and removed from the database.\n");
			} else {
				output.appendText("Account does not exist.\n");
			}

		} catch (Exception e) {
			output.appendText("Input data type mismatch.\n");

		}
	}
	
	private String [] createOpenArray () {
			String[] cmd = new String [8];
			cmd[1] = firstName.getText();
			cmd[2] = lastName.getText();
			cmd[3] = balance.getText();
			cmd[4] = month.getText();
			cmd[5] = day.getText();
			cmd[6] = year.getText();
			if (radioB.getSelectedToggle().equals(checking)) {
				cmd[0] = "C";
				if (direct.isSelected()) {
					cmd[7] = "true";
				} else {
					cmd[7] = "false";
				}
			} else if (radioB.getSelectedToggle().equals(savings)) {
				cmd[0] = "S";
				if (loyal.isSelected()) {
					cmd[7] = "true";
				} else {
					cmd[7] = "false";
				}
			} else if (radioB.getSelectedToggle().equals(moneyMarket)) {
				cmd[0] = "M";
				cmd[7] = "0";
			}
			return cmd;
	}
	
	private String [] createArray () {
		String[] cmd = new String [3];
		cmd[1] = firstName.getText();
		cmd[2] = lastName.getText();
		if (radioB.getSelectedToggle().equals(checking)) {
			cmd[0] = "C";
		} else if (radioB.getSelectedToggle().equals(savings)) {
			cmd[0] = "S";
		} else if (radioB.getSelectedToggle().equals(moneyMarket)) {
			cmd[0] = "M";
		}
		return cmd;
	}
	
	public void onButtonClicked (ActionEvent event) {
		if (event.getSource().equals(openAccount)) {
			if (errorsOpen()) {
				return;
			}
			String [] cmdArray = createOpenArray();
			if (cmdArray[0].length() > 1) {
				output.appendText("Command '" + cmdArray[0] + "' not supported!\n");
			} else {
				Date open = new Date(Integer.parseInt(cmdArray[4]), Integer.parseInt(cmdArray[5]), Integer.parseInt(cmdArray[6]));
				if (!date(open)) {
					output.appendText(open.toString() + " is not a valid date!\n");
				} else if (cmdArray[0].equals("C") || cmdArray[0].equals("S")) {
					if (!(bool(cmdArray[7]))) {
						output.appendText("Input data type mismatch.\n");
					} else {
						open(cmdArray);
					}
				} else {
					open(cmdArray);
				}
			}
			return;
		} else if (event.getSource().equals(closeAccount)) {
			if(errorsClose()) {
				return;
			}
			String [] cmdArray = createArray();
			close(cmdArray);
		}
		else if (event.getSource().equals(clearForm)) {
			
		}
	}
	
	public void onMenuPrint(ActionEvent event) {
		if(database.getSize()==0) {
			output.appendText("Database is empty. \n");
			return;
		}
		String [] print = new String [database.getSize()];
		if (event.getSource().equals(printAccount)) {
			output.appendText("--Listing accounts in the database--\n");
			print = database.printAccounts();
			for(int i = 0; i<database.getSize(); i++) {
				output.appendText(print[i]+"\n");
			}
			output.appendText("--end of listing--\n");
	        return;
        }
		if (event.getSource().equals(printAccountByDate)) {
			output.appendText("\n--Printing statements by date opened--\n\n");
			print = database.printByDateOpen();
        }
		if (event.getSource().equals(printAccountByName)) {
			output.appendText("\n--Printing statements by last name--\n\n");
			print = database.printByLastName();
        }
		for(int i = 0; i<database.getSize(); i++) {
			output.appendText(print[i]+"\n");
		}
		output.appendText("--end of printing--\n");
        return;
	}
	
	 /**
     * Takes an the event argument and disables the direct deposit and is loyal
     * radio buttons based on which button is selected.
     *
     * @param event Controls event of RadioButton press and decides what to do after.
     */
    public void radioClicked (ActionEvent event) {
        direct.setSelected(false);
        loyal.setSelected(false);
        if (event.getSource().equals(checking)) {
            direct.setDisable(false);
            loyal.setDisable(true);
        } else if (event.getSource().equals(savings)) {
            direct.setDisable(true);
            loyal.setDisable(false);
        } else if (event.getSource().equals(moneyMarket)){
            direct.setDisable(true);
            loyal.setDisable(true);
        }
    }
	
	/**
	 * This method checks if the date is valid
	 * @param dateOpen is the date given
	 * @return true if the date is valid, or false if the date is not valid
	 */
	private boolean date(Date open) {
		if (open.isValid()) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * This method checks if the boolean is valid
	 * @param check is the string of the boolean to check
	 * @return  "true".equals(check) || "false".equals(check)
	 */
	private boolean bool(String check) {
		return "true".equals(check.toLowerCase()) || "false".equals(check.toLowerCase());
	}
	
	//@FXML
    public void importFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Source File for the Import");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
		//write code to read from the file.
		try {
			Scanner read = new Scanner(sourceFile);
			while(read.hasNextLine()) {
				String account = read.nextLine();
				String [] cmdArray = account.split(",|/");
				try {
					if (cmdArray[0].length() > 1) {
						output.appendText("Command '" + cmdArray[0] + "' not supported!\n");
					} else {
						Date open = new Date(Integer.parseInt(cmdArray[4]), Integer.parseInt(cmdArray[5]), Integer.parseInt(cmdArray[6]));
						if (!date(open)) {
							output.appendText(open.toString() + " is not a valid date!\n");
						} else if (cmdArray[0].equals("C") || cmdArray[0].equals("S")) {
							if (!(bool(cmdArray[7]))) {
								output.appendText("Input data type mismatch.\n");
							} else {
								open(cmdArray);
							}
						} else {
							open(cmdArray);
						}
					}
				} catch (Exception e) {
					output.appendText("Input data type mismatch.\n");
				}
			}
			output.appendText("Import Complete\n");
			read.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			output.appendText("Error Opening File\n");
		}
		
    }
    
    //@FXML
    public void exportFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
		//write code to write to the file.
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
			for(int i = 0; i<database.getSize(); i++) {
				String accountType = database.getAccounts(i).getAccountType()+",";
				String name = database.getAccounts(i).getProfile().replace(" ", ",");
				String balance = ","+database.getAccounts(i).getBalance()+",";
				String date = database.getAccounts(i).getDate().toString()+",";
				String extra = database.getAccounts(i).getExtra();
				String mergedAccount = (accountType + name + balance + date + extra +"\n");
				writer.write(mergedAccount);
			}
			writer.close();
			output.appendText("Export Comlete\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			output.appendText("File cannot be written.");
		}
    }
    
    /**
     * Checks for simple errors in the GUI for numeric, alphabetic, or button/radio selection.
     *
     * @return Returns true or false as the return type for the function.
     */
    public boolean errorsOpen() {
        if (notAlpha(firstName.getText())) {
            output.appendText("Error: First name must be alphabetic and not null!\n");
            return true;
        }
        if (notAlpha(lastName.getText())) {
            output.appendText("Error: Last Name must be alphabetic and not null!\n");
            return true;
        }
        if (notNumeric(day.getText())) {
            output.appendText("Error: Day must be numeric and not null!\n");
            return true;
        }
        if (notNumeric(year.getText())) {
            output.appendText("Error: Year must be numeric and not null!\n");
            return true;
        }
        if (notNumeric(month.getText())) {
            output.appendText("Error: Month must be numeric and not null!\n");
            return true;
        }
        if (notNumeric(balance.getText())) {
            output.appendText("Error: Number of balance must be numeric and not null!\n");
            return true;
        }
        if (radioB.getSelectedToggle() == null) {
            output.appendText("Error: You must select an option: Checking, Savings, or Money Market!\n");
            return true;
        }
        return false;
    }
    
    /**
     * Checks for simple errors in the GUI for numeric, alphabetic, or button/radio selection.
     *
     * @return Returns true or false as the return type for the function.
     */
    public boolean errorsClose() {
        if (notAlpha(firstName.getText())) {
            output.appendText("Error: First name must be alphabetic and not null!\n");
            return true;
        }
        if (notAlpha(lastName.getText())) {
            output.appendText("Error: Last Name must be alphabetic and not null!\n");
            return true;
        }
        if (radioB.getSelectedToggle() == null) {
            output.appendText("Error: You must select an option: Checking, Savings, or Money Market!\n");
            return true;
        }
        return false;
    }

    /**
     * Checks if the string is alphabetic or not and returns true or false based on that.
     *
     * @param data Is a string variable for account.
     * @return Is the boolean variable for if string is alphabetic or not.
     */
    public boolean notAlpha(String data) {
        return !(!data.equals("") && data.matches("^[a-zA-Z]*$"));
    }

    /**
     * Checks if the string is numeric or not and returns true or false based on that.
     *
     * @param data Is a string variable for account.
     * @return Is the boolean variable for if string is numeric or not.
     */
    public boolean notNumeric(String data) {
        return !(!data.equals("") && data.matches("^[0-9]*$"));
    }

	
}
