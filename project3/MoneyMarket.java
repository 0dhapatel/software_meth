package application;
/**
 * This class extends Account and implements the methods montlyInterest and
 * montlhyFee. It also overrides the toString method to print the MoneyMarket
 * account information.
 * 
 * @author Jei Mota, Dhaval Patel
 *
 */
public class MoneyMarket extends Account {

	/**
	 * Number of account withdrawals
	 */
	private int withdrawals;

	/**
	 * Annual Interest Rate of the account.
	 */
	private double annualInterestRate = 0.0065;

	/**
	 * Monthly fee of the account.
	 */
	private int monthlyFee = 12;

	/**
	 * Monthly fee is waived if the balance is grater or higher than this amount.
	 */
	private final int waivedIfBalanceIs = 2500;

	/**
	 * The public constructor instantiates the fields withdrawals, holder, balance,
	 * dateOpen, and accountType.
	 * 
	 * @param withdrawals Number of account withdrawals
	 * @param holder      Instance of the Profile class
	 * @param balance     Balance of the Account class
	 * @param dateOpen    Instance of the Date class
	 * @param accountType This variable identifies the account type
	 */
	public MoneyMarket(int withdrawals, Profile holder, double balance, Date dateOpen, char accountType) {
		super(holder, balance, dateOpen, accountType);
		this.withdrawals = withdrawals;
	}

	
	/**
	 * This method calculates the monthly interest
	 * 
	 * @return super.getBalance() * annualInterestRate) / 12
	 */
	@Override
	public double monthlyInterest() {
		return (super.getBalance() * annualInterestRate) / 12;

	}

	/**
	 * This method increments withdrawals
	 * 
	 * @param amount which is the amount withdraw
	 */
	@Override
	public void debit(double amount) {
		super.debit(amount);
		withdrawals++;
	}

	/**
	 * This method calculates the monthly fee
	 * 
	 * @return monthlyFee
	 */
	@Override
	public double monthlyFee() {
		if (super.getBalance() == waivedIfBalanceIs && withdrawals > 6) {
			monthlyFee = 0;
		}
		return monthlyFee;

	}

	/**
	 * This method creates a string with the account's withdrawals string.
	 * 
	 * @return "*Money Market*" + super.toString() + withdrawalsString
	 */
	@Override
	public String toString() {
		String withdrawalsString = "";
		if (withdrawals == 1) {
			withdrawalsString = "*1 withdrawl*";
		} else {
			withdrawalsString = "*" + withdrawals + " withdrawals*";
		}
		return "*Money Market*" + super.toString() + withdrawalsString;
	}


	@Override
	public String getExtra() {
		// TODO Auto-generated method stub
		return this.withdrawals + "";
	}
}
