public class Checking  extends Account  {
	private boolean directDeposit; 
	private final double annualInterestRate = 0.0005;
	private int monthlyFee = 25; 
	private final int waivedIfBalanceIs = 1500; 
	
	
	public Checking(boolean directDeposit, Profile holder, double balance, Date dateOpen ) {
		super(holder, balance, dateOpen);
		this.directDeposit = directDeposit;
		}
	
	//override
	public String toString(){
		String directDepostString = "";
		if(this.directDeposit){
			directDepostString = "direct deposit account*";
		}
		return "*Checking*" + super.toString + directDepostString;
	}
	
	public double monthlyInterest() {
	return (super.getBalance() * annualInterestRate) / 12;
		
	}
	
	
	public double monthlyFee(){
		if(directDeposit == true || super.getBalance() == waivedIfBalanceIs ) {
			monthlyFee = 0;
		}
		return monthlyFee;
	
	}

			
	}


