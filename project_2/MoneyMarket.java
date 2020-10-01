public class MoneyMarket extends Account{
	private int withdrawals;
	private double annualInterestRate = 0.0065;
	private int monthlyFee = 12; 
	private final int waivedIfBalanceIs = 2500;
	
	public MoneyMarket(int withdrawals, Profile holder, double balance, Date dateOpen ) {
		super(holder, balance, dateOpen);
		this.withdrawals = withdrawals;
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
