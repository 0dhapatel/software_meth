package application;

/**
 * This class extends Account and implements the methods montlyInterest and montlhyFee.
 * It also overrides the toString method to print the Checking account information.
 * @author Jei Mota, Dhaval Patel
 *
 */
public class Checking  extends Account  {
	
	/**
	 * It's true if the Checking account has direct deposit 
	 */
	private boolean directDeposit; 
	
	/**
	 * Annual Interest Rate of the account.
	 */
	private final double annualInterestRate = 0.0005;
	
	/**
	 * Monthly fee of the account.
	 */
	private int monthlyFee = 25; 
	
	/**
	 * Monthly fee is waived if the balance is grater or higher than this amount.
	 */
	private final int waivedIfBalanceIs = 1500; 
	
	/**
	 * The public constructor instantiates the fields directDeposit, holder, balance, dateOpen, and
	 * accountType.
	 * @param directDeposit It's true if the Checking account has direct deposit 
	 * @param holder        Instance of the Profile class
	 * @param balance        Balance of the Account class
	 * @param dateOpen       Instance of the Date class
	 * @param accountType    This variable identifies the account type
	 */
	public Checking(boolean directDeposit, Profile holder, double balance, Date dateOpen, char accountType  ) {
		super(holder, balance, dateOpen, accountType);
		this.directDeposit = directDeposit;
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
	 * @return  monthlyFee
	 */
	@Override
	public double monthlyFee(){
		if(directDeposit == true || super.getBalance() == waivedIfBalanceIs ) {
			monthlyFee = 0;
		}
		return monthlyFee;
	
	}
	
	/**
	 * This method creates a string with the account's direct Deposit string.
	 * 
	 * @return "*Checking*" + super.toString() + directDepostString
	 */
	@Override
	public String toString(){
		String directDepostString = "";
		if(this.directDeposit){
			directDepostString = "*direct deposit account*";
		}
		return "*Checking*" + super.toString() + directDepostString;
	}
	

			
	}





