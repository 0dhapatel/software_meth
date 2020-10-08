import java.text.DecimalFormat;

/**
 * @author Jei Mota
 *
 */
public class AccountDatabase {

	private Account[] accounts;

	private int size;

	private int defaultAccountSize = 5;

	private static DecimalFormat df2 = new DecimalFormat("0.00");

	public int getSize() {
		return this.size;
	}

	public AccountDatabase() {
		this.accounts = new Account[defaultAccountSize];
		int size = 0;
	}

	private int find(Account account) {
		for (int i = 0; i < this.size; i++) {
			if (account.equals(this.accounts[i])) {
				return i;
			}
		}
		return -1;
	}

	private void grow() {
		Account[] tempAccount = new Account[this.size + defaultAccountSize];
		for (int i = 0; i < this.size; i++) {
			tempAccount[i] = this.accounts[i];
		}
		this.accounts = new Account[this.size + defaultAccountSize];
		this.accounts = tempAccount;
	}

	// return false if account exists
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

	// return false if account doesn’t exist
	public boolean remove(Account account) {
		int key = find(account);
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

	public boolean deposit(Account account, double amount) {
		int key = find(account);
		if (key == -1) {
			return false;
		}
		this.accounts[key].credit(amount);
		return true;
	}

	// return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t
	// exist
	public int withdrawal(Account account, double amount) {
		int key = find(account);
		if (key == -1) {
			return -1;
		}
		if (this.accounts[key].getBalance() < amount) {
			return 1;
		}
		this.accounts[key].debit(amount);
		return 0;
	}

	// sort in ascending order
	private void sortByDateOpen() { 
        Account temp;
        for(int i=0; i<size; i++)
        {
            for(int j=1; j<size; j++)
            {
                if(accounts[j-1].dateCompare(accounts[j].getDate()) == 1)
                {
                    temp=accounts[j-1];
                    accounts[j-1]=accounts[j];
                    accounts[j]=temp;
                }
            }
        }
	}
	
	// sort in ascending order
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
	} 

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

	public void printAccounts() {
    	for (int i = 0; i < size; i++) {
            System.out.println(accounts[i].toString());
    	}
    }
}
