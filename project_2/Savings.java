package application;

/**
 * This class extends Account and implements the methods montlyInterest and montlhyFee.
 * It also overrides the toString method to print the Savings account information.
 * @author Jei Mota, Dhaval Patel
 *
 */
public class Savings  extends Account{
	
	/**
	 * It's true if the customer is loyal
	 */
	private boolean isLoyal;
	
	/**
	 * Annual Interest Rate of the account.
	 */
	private double annualInterestRate = 0.0025;
	
	/**
	 * Monthly fee of the account.
	 */
	private int monthlyFee = 5; 
	
	/**
	 * Monthly fee is waived if the balance is grater or higher than this amount.
	 */
	private final int waivedIfBalanceIs = 300;
	
	/**
	 * The public constructor instantiates the fields isLoyal, holder, balance, dateOpen, and
	 * accountType.
	 * @param isLoyal       It's true if the customer is loyal
	 * @param holder        Instance of the Profile class
	 * @param balance        Balance of the Account class
	 * @param dateOpen       Instance of the Date class
	 * @param accountType    This variable identifies the account type
	 */
	public Savings(boolean isLoyal, Profile holder, double balance, Date dateOpen, char accountType ) {
		super(holder, balance, dateOpen, accountType);
		this.isLoyal = isLoyal;
		}
	
	/**
	 * This method calculates the monthly interest
	 * @return super.getBalance() * annualInterestRate) / 12
	 */
	@Override
	public double monthlyInterest() {
		return (super.getBalance() * annualInterestRate) / 12;
			
		}
		
		
	/**
	 * This method calculates the monthly fee
	 * @return  annualInterestRate
	 */
	@Override
	public double monthlyFee(){
		if(isLoyal == true) {
			annualInterestRate = 0.0035;
		}
		else if(super.getBalance() == waivedIfBalanceIs ) {
			monthlyFee = 0;
		}
		return annualInterestRate;
		
	
	}
	
	/**
	 * This method creates a string with the account's is Loyal String.
	 * 
	 * @return "*Savings*" + super.toString() + isLoyalString;
	 */
	@Override
		public String toString(){
			String isLoyalString = "";
			if(this.isLoyal){
				isLoyalString = "*special Savings account*";
			}
			return "*Savings*" + super.toString() + isLoyalString;
		}
}
