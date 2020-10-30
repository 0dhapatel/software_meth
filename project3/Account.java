package application;
import java.text.DecimalFormat;

/**
 * 
 */

/**
 * This abstract class contains methods to perform debit and credit actions, a
 * toString method to print the account's information, and the abstract methods
 * monthlyInterest and monthly fee, which have to be implemented in the classes:
 * Checking, Savings, and MoneyMarket.
 * 
 * @author Jei Mota, Dhaval Patel
 */
public abstract class Account {

	/**
	 * Instance of the Profile class
	 */
	private Profile holder;

	/**
	 * Balance of the Account class
	 */
	private double balance;

	/**
	 * Instance of the Date class
	 */
	private Date dateOpen;

	/**
	 * Formats a double parameter to two decimal places of precision
	 */
	private static DecimalFormat df2 = new DecimalFormat("0.00");

	/**
	 * This variable identifies the account type
	 */
	private char accountType;

	/**
	 * The public constructor instantiates the fields holder, balance, dateOpen, and
	 * accountType.
	 * 
	 * @param holder      Instance of the Profile class
	 * @param balance     Balance of the Account class
	 * @param dateOpen    Instance of the Date class
	 * @param accountType This variable identifies the account type
	 */

	public Account(Profile holder, double balance, Date dateOpen, char accountType) {
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
		this.accountType = accountType;
	}

	/**
	 * Observer Method for accountType
	 * 
	 * @return this.accountType which is the type of the Account
	 */

	public char getAccountType() {
		return this.accountType;
	}

	/**
	 * Observer Method for balance
	 * 
	 * @return this.balance which is the balance of the Account
	 */

	public double getBalance() {
		return this.balance;

	}

	/**
	 * Observer Method for Profile holder
	 * 
	 * @return this.holder which is the holder of the Account
	 */

	public String getProfile() {
		String mergeString = this.holder.get_fname().toLowerCase() + " " + this.holder.get_lname().toLowerCase();
		return mergeString;
	}

	/**
	 * Observer Method for holder last name
	 * 
	 * @return this.holder.get_lname() which is the holder's last name of the
	 *         Account
	 */

	public String getLname() {
		return this.holder.get_lname();
	}

	/**
	 * Observer Method for holder first name
	 * 
	 * @return this.holder.get_fname() which is the holder's first name of the
	 *         Account
	 */

	public String getFname() {
		return this.holder.get_fname();
	}

	/**
	 * Observer Method for dateOpen
	 * 
	 * @return this.dateOpen which is the date the Account was opened
	 */

	public Date getDate() {
		return this.dateOpen;
	}

	/**
	 * Observer Method for dateCompare
	 * 
	 * @param date is the date given
	 * @return this.dateOpen.compareTo(date) which compares dates
	 */

	public int dateCompare(Date date) {
		return this.dateOpen.compareTo(date);
	}

	/**
	 * This method subtract the debit amount from balance
	 * 
	 * @param amount is the amount given
	 */

	public void debit(double amount) {
		balance -= amount;

	} // decrease the balance by amount

	/**
	 * This method adds the credit amount from balance
	 * 
	 * @param amount is the amount given
	 */

	public void credit(double amount) {
		balance += amount;

	} // increase the balance by amount

	/**
	 * This method creates a string with the holder's first and last name, and the
	 * account's balance and dateOpen
	 * 
	 * @return holder.get_fname() + " " + holder.get_lname() + "* $" +
	 *         df2.format(this.balance) + "*" + this.dateOpen.toString()
	 */
	public String toString() {
		return holder.get_fname() + " " + holder.get_lname() + "* $" + df2.format(this.balance) + "*"
				+ this.dateOpen.toString();

	}

	/**
	 * Abstract method that needs to be implemented on classes that extend Account
	 * @return monthly interest
	 */
	public abstract double monthlyInterest();

	/**
	 * Abstract method that needs to be implemented on classes that extend Account
	 * @return monthly fee
	 */
	public abstract double monthlyFee();
	
	/**
	 * Abstract method that needs to be implemented on classes that extend Account
	 * @return monthly fee
	 */
	public abstract String getExtra();

}
