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
		boolean directDeposit = Boolean.parseBoolean(cmdArr[5]);
		Date dateOpen = new Date(0, 0, 0);;
		return new Checking(directDeposit, holder, balance, dateOpen);
	}
	
	private Savings savingsInfo(String[] cmdArr) {
		String fname = cmdArr[2];
		String lname = cmdArr[3];
		Profile holder = new Profile(fname, lname);
		double balance = Double.parseDouble(cmdArr[4]);
		boolean isLoyal = Boolean.parseBoolean(cmdArr[5]);
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
					if(database.add(checkingAccount)){
					System.out.println("Account opened and added to the database.");
					}
					else {
						System.out.println("Account is already in the database.");
					}
				}
				else if(cmdArray[1].charAt(1) == 'S') {
					Savings savingsAccount = savingsInfo(cmdArray);
					if(database.add(savingsAccount)) {
					System.out.println("Account opened and added to the database.");
					}
					else {
						System.out.println("Account is already in the database.");
					}
					
				}
				else if(cmdArray[1].charAt(1) == 'M') {
					MoneyMarket moneyMarketAccount = moneyMarketInfo(cmdArray);
					if(database.add(moneyMarketAccount)) {
						System.out.println("Account opened and added to the database.");
					}
					else {
						System.out.println("Account is already in the database.");
					}
					
				}
				
				break;
			// if the client inputs a 'R' followed by the name, price, and tax of a
			// GroceryItem and the item is in the bag, the item will be remove
			case 'C':
				if(cmdArray[1].charAt(1) == 'C') {
					Checking checkingAccount = checkingInfo(cmdArray);
					if(database.remove(checkingAccount)){
					System.out.println("Account closed and removed from the database.");
					}
					else {
						System.out.println("Account does not exist.");
					}
				}
				else if(cmdArray[1].charAt(1) == 'S') {
					Savings savingsAccount = savingsInfo(cmdArray);
					if(database.remove(savingsAccount)) {
					System.out.println("Account closed and removed from the database.");
					}
					else {
						System.out.println("Account does not exist.");
					}
					
				}
				else if(cmdArray[1].charAt(1) == 'M') {
					MoneyMarket moneyMarketAccount = moneyMarketInfo(cmdArray);
					if(database.remove(moneyMarketAccount)) {
						System.out.println("Account closed and removed from the database.");
					}
					else {
						System.out.println("Account does not exist.");
					}
					
				}

				break;
			// if the client inputs a 'P' and the bag is not empty, the bag's item are going
			// to be printed.
			case 'D':
				if(cmdArray[1].charAt(1) == 'C') {
					Checking checkingAccount = checkingInfo(cmdArray);
					double amount =  Double.parseDouble(cmdArray[4]);
					if(database.deposit(checkingAccount, amount)){
					System.out.println(amount + " deposited to account. ");
					}
					else {
						System.out.println("Account does not exist.");
					}
				}
				else if(cmdArray[1].charAt(1) == 'S') {
					Savings savingsAccount = savingsInfo(cmdArray);
					double amount =  Double.parseDouble(cmdArray[4]);
					if(database.deposit(savingsAccount, amount)){
					System.out.println(amount + " deposited to account. ");
					}
					else {
						System.out.println("Account does not exist.");
					}
					
				}
				else if(cmdArray[1].charAt(1) == 'M') {
					MoneyMarket moneyMarketAccount = moneyMarketInfo(cmdArray);
					double amount =  Double.parseDouble(cmdArray[4]);
					if(database.deposit(moneyMarketAccount, amount)){
						System.out.println(amount + " deposited to account. ");
					}
					else {
						System.out.println("Account does not exist.");
					}
					
				}
				break;
			// If the user inputs a 'C' and the bag is empty, the number of items and
			// their sales, tax, and amount paid are going to be printed
			case 'W':
				if(cmdArray[1].charAt(1) == 'C') {
					Checking checkingAccount = checkingInfo(cmdArray);
					double amount =  Double.parseDouble(cmdArray[4]);
					if(database.withdrawal(checkingAccount, amount) == 0){
					System.out.println(amount + "  withdrawn from account. ");
					}
					else if(database.withdrawal(checkingAccount, amount) == 1){
						System.out.println("Insufficient funds.");
					}
					else {
						System.out.println("Account does not exist.");
					}
				}
				else if(cmdArray[1].charAt(1) == 'S') {
					Savings savingsAccount = savingsInfo(cmdArray);
					double amount =  Double.parseDouble(cmdArray[4]);
					if(database.withdrawal(savingsAccount, amount)== 0){
					System.out.println(amount + "  withdrawn from account. ");
					}
					else if(database.withdrawal(savingsAccount, amount) == 1){
						System.out.println("Insufficient funds.");
					}
					else {
						System.out.println("Account does not exist.");
					}
				}
				else if(cmdArray[1].charAt(1) == 'M') {
					MoneyMarket moneyMarketAccount = moneyMarketInfo(cmdArray);
					double amount =  Double.parseDouble(cmdArray[4]);
					if(database.withdrawal(moneyMarketAccount, amount) == 0){
						System.out.println(amount + "  withdrawn from account. ");
					}
					else if(database.withdrawal(moneyMarketAccount, amount) == 1){
						System.out.println("Insufficient funds.");
					}
					else {
						System.out.println("Account does not exist.");
					}
				}

				break;

			// If the client inputs a 'Q' and the bag is empty the program will end,
			// otherwise, the number of items and their sales, tax, and amount paid are
			// going to be printed
			case 'P':
				if(cmdArray[1].charAt(1) == 'A') {}
				else if(cmdArray[1].charAt(1) == 'D') {}
				else if(cmdArray[1].charAt(1) == 'N') {}
				
				
				break;
			case 'Q':
				System.out.println("Transaction processing completed");
				inSession = false;
				break;
			default:
				System.out.println("Invalid command!");
				break;
			}

		}
	}
}
