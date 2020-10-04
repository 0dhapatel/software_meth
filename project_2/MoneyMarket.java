public class MoneyMarket extends Account{
	private int withdrawals;
	private double annualInterestRate = 0.0065;
	private int monthlyFee = 12; 
	private final int waivedIfBalanceIs = 2500;
	
	public MoneyMarket(int withdrawals, Profile holder, double balance, Date dateOpen ) {
		super(holder, balance, dateOpen);
		this.withdrawals = withdrawals;
	}
	
	//override
	public String toString(){
		String withdrawalsString = "";
		if(withdrawals == 1){
			withdrawalsString = "1 withdrawl*";
		}
		else{
			withdrawalsString = withdrawals +" withdrawals*";
		}
		return "*Money Market*" + super.toString + withdrawalsString;
	}

	public double monthlyInterest() {
		return (super.getBalance() * annualInterestRate) / 12;
			
		}
		
		

	public double monthlyFee(){
		 if(super.getBalance() == waivedIfBalanceIs && withdrawals > 6 ) {
			monthlyFee = 0;
		}
		 return monthlyFee;
	
	}
}
