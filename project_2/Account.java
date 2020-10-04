public abstract class Account {
	private Profile holder;
	private double balance;
	private Date dateOpen;
	
	public Account(Profile holder, double balance,Date dateOpen ) {
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
	}
	 
	public double getBalance() {
		return this.balance;
		
	}
	
	public String getLname(){
		return holder.get_lname;
	}
	
	public String getfname(){
		return holder.get_fname;
	}
	
	public void debit(double amount) {
		balance -= amount;
		
	} //decrease the balance by amount
	
	public void credit(double amount) {
		balance += amount;
		
	} //increase the balance by amount
	
	
	public String toString() { 
		return holder.get_fname + " " + holder.get_lname + "* $" + this.balance + "*" + this.dateOpen.toString() + "*";
	}
	
	
	public abstract double monthlyInterest();
	public abstract double monthlyFee();

}
