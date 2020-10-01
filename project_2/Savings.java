public class Savings  extends Account{
	private boolean isLoyal;
	private double annualInterestRate = 0.0025;
	private int monthlyFee = 5; 
	private final int waivedIfBalanceIs = 300;
	
	public Savings(boolean isLoyal, Profile holder, double balance, Date dateOpen ) {
		super(holder, balance, dateOpen);
		this.isLoyal = isLoyal;
		}

	public double monthlyInterest() {
		return (super.getBalance() * annualInterestRate) / 12;
			
		}
		
		

	public double monthlyFee(){
		if(isLoyal == true) {
			annualInterestRate = 0.0035;
		}
		else if(super.getBalance() == waivedIfBalanceIs ) {
			monthlyFee = 0;
		}
		return annualInterestRate;
		
	
	}
}
