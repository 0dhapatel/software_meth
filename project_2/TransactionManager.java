import java.text.DecimalFormat;
import java.util.Scanner;

import application.Account;
import application.AccountDatabase;
import application.Checking;
import application.Date;
import application.MoneyMarket;
import application.Profile;
import application.Savings;

/**
 * This class manages the user's input to take actions like adding, removing,
 * and displaying the accounts of the AccountDatabase. By using a while loop and
 * switch cases it is possible for the user to perform certain actions regarding
 * the AccountDatabase class.
 * 
 * @author Jei Mota, Dhaval Patel
 */
public class TransactionManager {
	/**
	 * This is an instance of a AccountDatabase class.
	 */
	private AccountDatabase database;

	/**
	 * This object is used to format double variables to two points of precision.
	 */
	private static DecimalFormat df2 = new DecimalFormat("0.00");

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
		boolean directDeposit = Boolean.parseBoolean(cmdArr[5]);
		Date dateOpen = splitDate(cmdArr[4]);
		Account checkingAccount = new Checking(directDeposit, holder, balance, dateOpen, accountType);
		return checkingAccount;
	}

	/**
	 * This method splits the user's input into an array.
	 * 
	 * @return new Checking object
	 * @param cmdArr is an array that holds the user's input
	 */
	private Account checkInfo(String[] cmdArr) {
		char accountType = 'C';
		String fname = cmdArr[1];
		String lname = cmdArr[2];
		Profile holder = new Profile(fname, lname);
		return new Checking(false, holder, 0, null, accountType);
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
		boolean isLoyal = Boolean.parseBoolean(cmdArr[5]);
		Date dateOpen = splitDate(cmdArr[4]);
		return new Savings(isLoyal, holder, balance, dateOpen, accountType);
	}

	/**
	 * This method splits the user's input into an array.
	 * 
	 * @return new Savings object
	 * @param cmdArr is an array that holds the user's input
	 */
	private Account saveInfo(String[] cmdArr) {
		char accountType = 'S';
		String fname = cmdArr[1];
		String lname = cmdArr[2];
		Profile holder = new Profile(fname, lname);
		return new Savings(false, holder, 0, null, accountType);
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
		int withdrawals = 0;
		Date dateOpen = splitDate(cmdArr[4]);
		return new MoneyMarket(withdrawals, holder, balance, dateOpen, accountType);
	}

	/**
	 * This method splits the user's input into an array.
	 * 
	 * @return new MoneyMarket object
	 * @param cmdArr is an array that holds the user's input
	 */
	private Account MarketInfo(String[] cmdArr) {
		char accountType = 'M';
		String fname = cmdArr[1];
		String lname = cmdArr[2];
		Profile holder = new Profile(fname, lname);
		return new MoneyMarket(0, holder, 0, null, accountType);
	}

	/**
	 * This method splits the date by using the delimiter "/"
	 * 
	 * @param date is the date given
	 * @return open is the date returned
	 */
	private Date splitDate(String date) {
		String[] dateSplit = date.split("/");
		Date open = new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
				Integer.parseInt(dateSplit[2]));
		return open;
	}

	/**
	 * If the client inputs an 'O', and it's followed by either a 'C', 'S', or 'M',
	 * a checking, savings, or money market account are going to be opened
	 * respectively.
	 * @param cmdArray is an array that holds the user's input
	 */
	private void open(String[] cmdArray) {

		try {
			if (cmdArray[0].charAt(1) == 'C') {
				Account checkingAccount = checkingInfo(cmdArray);
				boolean checkAdd = database.add(checkingAccount);
				if (checkAdd) {
					System.out.println("Account opened and added to the database.");
				} else {
					System.out.println("Account is already in the database.");
				}
			} else if (cmdArray[0].charAt(1) == 'S') {
				Account savingsAccount = savingsInfo(cmdArray);
				if (database.add(savingsAccount)) {
					System.out.println("Account opened and added to the database.");
				} else {
					System.out.println("Account is already in the database.");
				}

			} else if (cmdArray[0].charAt(1) == 'M') {
				Account moneyMarketAccount = moneyMarketInfo(cmdArray);
				if (database.add(moneyMarketAccount)) {
					System.out.println("Account opened and added to the database.");
				} else {
					System.out.println("Account is already in the database.");
				}

			}
		} catch (Exception e) {
			System.out.println("Input data type mismatch.");

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
			if (cmdArray[0].charAt(1) == 'C') {
				Account checkingAccount = checkInfo(cmdArray);
				if (database.remove(checkingAccount)) {
					System.out.println("Account closed and removed from the database.");
				} else {
					System.out.println("Account does not exist.");
				}
			} else if (cmdArray[0].charAt(1) == 'S') {
				Account savingsAccount = saveInfo(cmdArray);
				if (database.remove(savingsAccount)) {
					System.out.println("Account closed and removed from the database.");
				} else {
					System.out.println("Account does not exist.");
				}

			} else if (cmdArray[0].charAt(1) == 'M') {
				Account moneyMarketAccount = MarketInfo(cmdArray);
				if (database.remove(moneyMarketAccount)) {
					System.out.println("Account closed and removed from the database.");
				} else {
					System.out.println("Account does not exist.");
				}

			}

		} catch (Exception e) {
			System.out.println("Input data type mismatch.");

		}
	}

	/**
	 * if the client inputs a 'D', and it's followed by either a 'C', 'S', or 'M',
	 * an amount is going to be deposited into checking, savings, or money market
	 * account respectively.
	 * 
	 * @param cmdArray is an array that holds the user's input
	 */
	private void deposit(String[] cmdArray) {

		try {
			if (cmdArray[0].charAt(1) == 'C') {
				Account checkingAccount = checkInfo(cmdArray);
				double amount = Double.parseDouble(cmdArray[3]);
				if (database.deposit(checkingAccount, amount)) {
					System.out.println(amount + " deposited to account. ");
				} else {
					System.out.println("Account does not exist.");
				}
			} else if (cmdArray[0].charAt(1) == 'S') {
				Account savingsAccount = saveInfo(cmdArray);
				double amount = Double.parseDouble(cmdArray[3]);
				if (database.deposit(savingsAccount, amount)) {
					System.out.println(amount + " deposited to account. ");
				} else {
					System.out.println("Account does not exist.");
				}

			} else if (cmdArray[0].charAt(1) == 'M') {
				Account moneyMarketAccount = MarketInfo(cmdArray);
				double amount = Double.parseDouble(cmdArray[3]);
				if (database.deposit(moneyMarketAccount, amount)) {
					System.out.println(amount + " deposited to account. ");
				} else {
					System.out.println("Account does not exist.");
				}

			}

		} catch (Exception e) {
			System.out.println("Input data type mismatch.");

		}
	}

	/**
	 * if the client inputs a 'W', and it's followed by either a 'C', 'S', or 'M',
	 * an amount is going to be withdraw from checking, savings, or money market
	 * account respectively.
	 * 
	 * @param cmdArray is an array that holds the user's input
	 */
	private void withdrawl(String[] cmdArray) {

		try {
			if (cmdArray[0].charAt(1) == 'C') {
				Account checkingAccount = checkInfo(cmdArray);
				double amount = Double.parseDouble(cmdArray[3]);
				if (database.withdrawal(checkingAccount, amount) == 0) {
					System.out.println(amount + "  withdrawn from account. ");
				} else if (database.withdrawal(checkingAccount, amount) == 1) {
					System.out.println("Insufficient funds.");
				} else {
					System.out.println("Account does not exist.");
				}
			} else if (cmdArray[0].charAt(1) == 'S') {
				Account savingsAccount = saveInfo(cmdArray);
				double amount = Double.parseDouble(cmdArray[3]);
				if (database.withdrawal(savingsAccount, amount) == 0) {
					System.out.println(amount + "  withdrawn from account. ");
				} else if (database.withdrawal(savingsAccount, amount) == 1) {
					System.out.println("Insufficient funds.");
				} else {
					System.out.println("Account does not exist.");
				}
			} else if (cmdArray[0].charAt(1) == 'M') {
				Account moneyMarketAccount = MarketInfo(cmdArray);
				double amount = Double.parseDouble(cmdArray[3]);
				if (database.withdrawal(moneyMarketAccount, amount) == 0) {
					System.out.println(amount + "  withdrawn from account. ");
				} else if (database.withdrawal(moneyMarketAccount, amount) == 1) {
					System.out.println("Insufficient funds.");
				} else {
					System.out.println("Account does not exist.");
				}
			}

		} catch (Exception e) {
			System.out.println("Input data type mismatch.");

		}
	}
	
	/**
	 * This method checks if the date is valid
	 * @param dateOpen is the date given
	 * @return true if the date is valid, or false if the date is not valid
	 */
	private boolean date(String dateOpen) {
		Date open = splitDate(dateOpen);
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
		return "true".equals(check) || "false".equals(check);

	}

	/**
	 * This method runs the user interface, by using a while loop and switch cases,
	 * in which the user has certain options regarding a AccountDatabase object.
	 * 
	 * 
	 */
	public void run() {
		database = new AccountDatabase();
		// char userImput = ' ';
		boolean inSession = true;
		System.out.println("Transaction processing starts.....");
		Scanner keyboard = new Scanner(System.in);

		// this is a loop that stops when the client types the character 'Q'
		while (inSession) {
			String inputCmd = keyboard.nextLine();
			String[] cmdArray = inputCmd.split(" ");
			char userImput = cmdArray[0].charAt(0);
			// This switch goes trough different cases depending on the user's input.
			switch (userImput) {

			case 'O':
				try {
					if (cmdArray[0].length() < 2) {
						System.out.println("Command '" + cmdArray[0] + "' not supported!");
					} else {
						if (!date(cmdArray[4])) {
							System.out.println(cmdArray[4] + " is not a valid date!");

						} else if (cmdArray[0].charAt(1) == 'C' || cmdArray[0].charAt(1) == 'S') {
							if (!bool(cmdArray[5])) {
								System.out.println("Input data type mismatch.");
							}
						} else {
							open(cmdArray);
						}
					}
				} catch (Exception e) {
					System.out.println("Input data type mismatch.");

				}

				break;
			case 'C':
				if (cmdArray[0].length() < 2) {
					System.out.println("Command '" + cmdArray[0] + "' not supported!");
				} else {
					close(cmdArray);
				}
				break;
			case 'D':
				if (cmdArray[0].length() < 2) {
					System.out.println("Command '" + cmdArray[0] + "' not supported!");
				} else {
					deposit(cmdArray);
				}

				break;
			case 'W':
				if (cmdArray[0].length() < 2) {
					System.out.println("Command '" + cmdArray[0] + "' not supported!");
				} else {
					withdrawl(cmdArray);
				}
				break;

			// If the client inputs a 'P', and it's followed by an 'A', 'D', or 'N', the
			// accounts array is going to be printed by either date opened or by last Name
			case 'P':
				if (cmdArray[0].length() < 2) {
					System.out.println("Command '" + cmdArray[0] + "' not supported!");
				} else {
					if (cmdArray[0].charAt(1) == 'A') {
						if (database.getSize() == 0) {
							System.out.println("Database is empty.");

						} else {
							System.out.println("--Listing accounts in the database--");
							database.printAccounts();
							System.out.println("--end of listing--");
						}
					} else if (cmdArray[0].charAt(1) == 'D') {
						if (database.getSize() == 0) {
							System.out.println("Database is empty.");

						} else {
							System.out.println("--Printing statements by date opened--");
							System.out.println();
							database.printByDateOpen();
							System.out.println("--end of printing--");
						}
					} else if (cmdArray[0].charAt(1) == 'N') {
						if (database.getSize() == 0) {
							System.out.println("Database is empty.");

						} else {
							System.out.println("--Printing statements by  last name--");
							System.out.println();
							database.printByLastName();
							System.out.println("--end of printing--");

						}
					}
				}

				break;
			// if the client inputs a 'Q' the program is terminated
			case 'Q':
				System.out.println("Transaction processing completed");
				inSession = false;
				break;
			default:
				System.out.println("Command '" + cmdArray[0] + "' not supported!");
				break;
			}

		}

	}

	/**
	 * This method works as our testbed main to test if the class is working
	 * properly
	 * 
	 * @param args is the command line input.
	 */
	public static void main(String[] args) {
		AccountDatabase AD = new AccountDatabase();
		Profile holder = new Profile("Dhaval", "Patel");
		Date dateOpen = new Date(10, 7, 2020);
		Savings acs = new Savings(false, holder, 900, dateOpen, 'S');
		Checking acc = new Checking(false, holder, 800, dateOpen, 'C');
		System.out.println(AD.add(acc));
		System.out.println(AD.add(acc));
		System.out.println(AD.add(acs));
		AD.printAccounts();

	}
}
