package application;
import java.text.DecimalFormat;

/**
 * This class uses an array-based implementation of Account to perform certain
 * actions like finding, adding, and removing Account objects. There is also a
 * testbed main to check if the class is working effectively.
 * 
 * @author Jei Mota, Dhaval Patel
 *
 */
public class AccountDatabase {

	/**
	 * Array-based implementation of Account
	 */

	private Account[] accounts;

	/**
	 * Number of Accounts currently in the accounts array
	 */

	private int size;

	/**
	 * Default accounts array size
	 */

	private int defaultAccountSize = 5;

	/**
	 * This object is used to format double variables to two points of precision.
	 */

	private static DecimalFormat df2 = new DecimalFormat("0.00");

	/**
	 * Observer method for size
	 * 
	 * @return size
	 */

	public int getSize() {
		return this.size;
	}

	/**
	 * The public constructor method initiates accounts array, and size
	 */
	public AccountDatabase() {
		this.accounts = new Account[defaultAccountSize];
		int size = 0;
	}

	/**
	 * This is a helper method to find an account in the accounts array
	 * 
	 * @return -1 if the account is not in accounts array, else returns i if the
	 *         array key is found
	 * @param account is the account to be searched
	 */

	private int find(Account account) {
		for (int i = 0; i < this.size; i++) {
			if (account.toString().equals(this.accounts[i].toString())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This is a helper method to find the holder of an account in the accounts
	 * array
	 * 
	 * @return -1 if the account's holder is not in accounts array, else returns i
	 *         if the array key is found
	 * @param account is the account to be searched
	 */

	private int findHolder(Account account) {
		for (int i = 0; i < this.size; i++) {
			if (account.getProfile().equals(this.accounts[i].getProfile())
					&& account.getAccountType() == this.accounts[i].getAccountType()) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This is a helper method to grow the capacity of the bag by 5
	 */
	private void grow() {
		Account[] tempAccount = new Account[this.size + defaultAccountSize];
		for (int i = 0; i < this.size; i++) {
			tempAccount[i] = this.accounts[i];
		}
		this.accounts = new Account[this.size + defaultAccountSize];
		this.accounts = tempAccount;
	}

	/**
	 * This method adds an Account object into the accounts array. if the size is
	 * equal to capacity the bag has to grow(). At the end the size is incremented
	 * by 1.
	 * 
	 * @param account is the account to be added
	 * @return false if the account is already in the database otherwise true if the
	 *         account was added
	 */
	public boolean add(Account account) {

		// already in the database
		if (find(account) != -1) {
			return false;
		}

		// if capicity is smaller, use helper method grow to add more sapce
		if (this.size == this.accounts.length) {
			grow();
		}

		this.accounts[this.size] = account;
		this.size = this.size + 1;
		return true;
	}

	/**
	 * This method removes an Account object into the accounts array. At the end the
	 * size is decremented by 1.
	 * 
	 * @param account is the account to be removed
	 * @return false if the account is not in the database otherwise true if the
	 *         account was removed
	 */
	public boolean remove(Account account) {
		int key = findHolder(account);
		// item not found
		if (key == -1) {
			return false;
		}
		// item found and removed successfully
		else {
			this.size = this.size - 1;
			this.accounts[key] = this.accounts[this.size];
			this.accounts[this.size] = null;
		}
		return true;
	}

	/**
	 * This method deposits an amount into an Account object
	 * 
	 * @param account is the account in which the amount is going to be deposited
	 * @param amount  is the amount is going to be deposited
	 * @return false if the account is not in the database otherwise true if the
	 *         account was removed
	 */
	public boolean deposit(Account account, double amount) {
		int key = findHolder(account);
		if (key == -1) {
			return false;
		}
		this.accounts[key].credit(amount);
		return true;
	}

	/**
	 * This method withdrawals an amount from an Account object
	 * 
	 * @param account is the account from which the amount is going to be withdraw
	 * @param amount is the amount is going to be withdraw
	 * @return -1 if the account is not in the database. 1 if the balance is less
	 *         than the amount. 0 if the amount is withdraw from the account
	 * 
	 */
	public int withdrawal(Account account, double amount) {
		int key = findHolder(account);
		if (key == -1) {
			return -1;
		}
		if (this.accounts[key].getBalance() < amount) {
			return 1;
		}
		this.accounts[key].debit(amount);
		return 0;
	}

	/**
	 * This is a helper method to sort the array by date in ascending order
	 */
	private void sortByDateOpen() {
		Account temp;
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if (accounts[j - 1].dateCompare(accounts[j].getDate()) == 1) {
					temp = accounts[j - 1];
					accounts[j - 1] = accounts[j];
					accounts[j] = temp;
				}
			}
		}
	}

	/**
	 * This is a helper method to sort the array by Last Name in ascending order
	 */
	private void sortByLastName() {
		Account temp;

		for (int i = 0; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if (accounts[j - 1].getFname().compareTo(accounts[j].getFname()) > 0) {
					temp = accounts[j - 1];
					accounts[j - 1] = accounts[j];
					accounts[j] = temp;
				}
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if (accounts[j - 1].getLname().compareTo(accounts[j].getLname()) > 0) {
					temp = accounts[j - 1];
					accounts[j - 1] = accounts[j];
					accounts[j] = temp;
				}
			}
		}
	} // sort in ascending order

	/**
	 * This method prints the array by Date Open with the balance and fees
	 */
	public void printByDateOpen() {
		sortByDateOpen();
		for (int i = 0; i < size; i++) {
			System.out.println(accounts[i].toString());
			String interestString = df2.format(accounts[i].monthlyInterest());
			System.out.println("-interest: $ " + interestString);
			String feeString = df2.format(accounts[i].monthlyFee());
			System.out.println("-fee: $ " + feeString);
			Double balanceString = accounts[i].getBalance() + accounts[i].monthlyInterest() - accounts[i].monthlyFee();
			System.out.println("-new balance: $ " + df2.format(balanceString));
			System.out.println();
		}

	}

	/**
	 * This method prints the array by Last Name with the balance and fees
	 */
	public void printByLastName() {
		sortByLastName();
		for (int i = 0; i < size; i++) {
			System.out.println(accounts[i].toString());
			String interestString = df2.format(accounts[i].monthlyInterest());
			System.out.println("-interest: $ " + interestString);
			String feeString = df2.format(accounts[i].monthlyFee());
			System.out.println("-fee: $ " + feeString);
			Double balanceString = accounts[i].getBalance() + accounts[i].monthlyInterest() - accounts[i].monthlyFee();
			System.out.println("-new balance: $ " + df2.format(balanceString));
			System.out.println();
		}
	}

	/**
	 * This method prints the accounts array
	 */
	public void printAccounts() {
		for (int i = 0; i < size; i++) {
			System.out.println(accounts[i].toString());
		}
	}

	/**
	 * This method works as our testbed main to test if the class is working
	 * properly
	 * 
	 * @param args command line input.
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
