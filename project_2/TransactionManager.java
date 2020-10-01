import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Jei Mota
 *
 */
public class TransactionManager {
	/**
	 *This is an instance of a ShoppingBag class.
	 */
	private AccountDatabase database; 
	
	/**
	 * This object is used to format double variables to two points of precision.
	 */
	private static DecimalFormat df2 = new DecimalFormat("0.00"); 

	/**
	 * This method splits the user's input into an array.
	 * 
	 * @return new GroceryItem object
	 * @param cmdArr is an array that holds the user's input
	 */
	private Checking checkingInfo(String[] cmdArr) {
		String fname = cmdArr[2];
		String lname = cmdArr[3];
		Profile holder = new Profile(fname, lname);
		double balance = Double.parseDouble(cmdArr[4]);
		boolean directDeposit = true;
		Date dateOpen = new Date(0, 0, 0);;
		return new Checking(directDeposit, holder, balance, dateOpen);
	}
	
	private Savings savingsInfo(String[] cmdArr) {
		String fname = cmdArr[2];
		String lname = cmdArr[3];
		Profile holder = new Profile(fname, lname);
		double balance = Double.parseDouble(cmdArr[4]);
		boolean isLoyal = true;
		Date dateOpen = new Date(0, 0, 0);;
		return new Savings(isLoyal, holder, balance, dateOpen);
	}
	
	private MoneyMarket moneyMarketInfo(String[] cmdArr) {
		String fname = cmdArr[2];
		String lname = cmdArr[3];
		Profile holder = new Profile(fname, lname);
		double balance = Double.parseDouble(cmdArr[4]);
		int withdrawals = 0;
		Date dateOpen = new Date(0, 0, 0);;
		return new MoneyMarket(withdrawals, holder, balance, dateOpen);
	}

	/**
	 * The public constructor creates a instance of a ShoppingBag object
	 */
	public TransactionManager() {
		database = new AccountDatabase();

	}

	/**
	 * This method runs the user interface, by using a while loop and switch cases,
	 * in which the user has certain options regarding a ShoppingBag object.
	 * 
	 * 
	 */
	public void run() {

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
			// if the client inputs an 'A' followed by the name, price, and tax of a
			// GroceryItem, the object will be added to the bag
			case 'O':
				if(cmdArray[1].charAt(1) == 'C') {
					Checking checkingAccount = checkingInfo(cmdArray);
					database.add(checkingAccount);
					System.out.println("Account opened and added to the database.");
					
				}
				else if(cmdArray[1].charAt(1) == 'S') {
					Savings savingsAccount = savingsInfo(cmdArray);
					database.add(savingsAccount);
					System.out.println("Account opened and added to the database.");
					
					
				}
				else if(cmdArray[1].charAt(1) == 'M') {
					MoneyMarket moneyMarketAccount = moneyMarketInfo(cmdArray);
					database.add(moneyMarketAccount);
					System.out.println("Account opened and added to the database.");
					
				}
				
