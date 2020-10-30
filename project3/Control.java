package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;;

public class Controller {
	
	private AccountDatabase database = new AccountDatabase();
	
	public MenuItem printAccount;
	public MenuItem printAccountByDate;
	public MenuItem printAccountByName;
	
	public TextArea output;
	
	/**
	 * This method splits the user's input into an array.
	 * 
	 * @return new Checking object
	 * @param cmdArr is an array that holds the user's input
	 */
	private Account checkingInfo(String[] cmdArr) {
		char accountType = 'C';
		String fname = cmdArr[1];
		String lname = cmdArr[2];
		Profile holder = new Profile(fname, lname);
		double balance = Double.parseDouble(cmdArr[3]);
		boolean directDeposit = Boolean.parseBoolean(cmdArr[7]);
		Date dateOpen = new Date(Integer.parseInt(cmdArr[4]), Integer.parseInt(cmdArr[5]), Integer.parseInt(cmdArr[6]));
		Account checkingAccount = new Checking(directDeposit, holder, balance, dateOpen, accountType);
		return checkingAccount;
	}
	
	/**
	 * This method splits the user's input into an array.
	 * 
	 * @return new Savings object
	 * @param cmdArr is an array that holds the user's input
	 */
	private Account savingsInfo(String[] cmdArr) {
		char accountType = 'S';
		String fname = cmdArr[1];
		String lname = cmdArr[2];
		Profile holder = new Profile(fname, lname);
		double balance = Double.parseDouble(cmdArr[3]);
		boolean isLoyal = Boolean.parseBoolean(cmdArr[7]);
		Date dateOpen = new Date(Integer.parseInt(cmdArr[4]), Integer.parseInt(cmdArr[5]), Integer.parseInt(cmdArr[6]));
		return new Savings(isLoyal, holder, balance, dateOpen, accountType);
	}
	
	/**
	 * This method splits the user's input into an array.
	 * 
	 * @return new MoneyMarket object
	 * @param cmdArr is an array that holds the user's input
	 */
	private Account moneyMarketInfo(String[] cmdArr) {
		char accountType = 'M';
		String fname = cmdArr[1];
		String lname = cmdArr[2];
		Profile holder = new Profile(fname, lname);
		double balance = Double.parseDouble(cmdArr[3]);
		int withdrawals = Integer.parseInt(cmdArr[7]);
		Date dateOpen = new Date(Integer.parseInt(cmdArr[4]), Integer.parseInt(cmdArr[5]), Integer.parseInt(cmdArr[6]));
		return new MoneyMarket(withdrawals, holder, balance, dateOpen, accountType);
	}
	
	/**
	 * If the client inputs an 'O', and it's followed by either a 'C', 'S', or 'M',
	 * a checking, savings, or money market account are going to be opened
	 * respectively.
	 * @param cmdArray is an array that holds the user's input
	 */
	private void open(String[] cmdArray) {
		try {
			if (cmdArray[0].equals("C")) {
				Account checkingAccount = checkingInfo(cmdArray);
				if (database.add(checkingAccount)) {
					output.appendText("Account opened and added to the database.\n");
				} else {
					output.appendText("Account is already in the database.\n");
				}
			} else if (cmdArray[0].equals("S")) {
				Account savingsAccount = savingsInfo(cmdArray);
				if (database.add(savingsAccount)) {
					output.appendText("Account opened and added to the database.\n");
				} else {
					output.appendText("Account is already in the database.\n");
				}

			} else if (cmdArray[0].equals("M")) {
				Account moneyMarketAccount = moneyMarketInfo(cmdArray);
				if (database.add(moneyMarketAccount)) {
					output.appendText("Account opened and added to the database.\n");
				} else {
					output.appendText("Account is already in the database.\n");
				}

			}
		} catch (Exception e) {
			output.appendText("Input data type mismatch.\n");

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
				output.appendText(mergedAccount);
				writer.write(mergedAccount);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			output.appendText("File cannot be written.");
		}
    }
	
}
