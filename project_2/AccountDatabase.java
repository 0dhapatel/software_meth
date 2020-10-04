public class AccountDatabase {
    
    private Account[] accounts;
    
    private int size;
    
    private int defaultAccountSize = 5;
    
    public int getSize(){
        return this.size;
    }
    
    public AccountDatabase(){
        this.accounts = new Account[defaultAccountSize];
        int size = 0;
    }
    
    private int find(Account account) {
        for(int i = 0; i < this.size; i++ ) 
      {
          if(item.equals(this.accounts[i])){
              return i; 
          }
      }
      return -1;
    }
    
    private void grow() { 
        GroceryItem[] tempAccount = new GroceryItem[this.size+defaultAccountSize];
        for (int i = 0; i < this.size; i++){
            tempAccount[i] = this.accounts[i];
        }
        this.accounts = new Account[this.size+defaultAccountSize];
        this.accounts = tempAccount;
    }
    
    //return false if account exists
    public boolean add(Account account) { 
        
        // already in the database
        if(find(account) != -1){
            return false;
        }
            
        //if capicity is smaller, use helper method grow to add more sapce 
        if(this.size==this.accounts.length){ 
            grow();
        }
        
        this.accounts[this.size] = account;
        this.size = this.size + 1;
        return true;
    } 
    
    //return false if account doesn’t exist
    public boolean remove(Account account) {
        int key = find(account);
        //item not found
        if(key == -1){
          return false;
        }
        //item found and removed successfully
        else{
            this.size = this.size - 1; 
            this.accounts[key] = this.accounts[this.size]; 
            this.accounts[this.size]=null;
      }
      return true; 
    } 
    
    public boolean deposit(Account account, double amount) {
        int key = find(account);
        if (key==-1){
            return false;
        }
        this.accounts[key].credit(amount);
        return true;
    }
    
    //return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist
    public int withdrawal(Account account, double amount) {
        int key = find(account);
        if (key==-1){
            return -1;
        }
        if (this.accounts[key].getBalance < amount){
            return 1;
        }
        this.accounts[key].debit(amount);
        return 0;
    }
    
    //sort in ascending order
    private void sortByDateOpen() { 
        for(int i=0; i<size; i++)
        {
            for(int j=1; j<size; j++)
            {
                if(accounts[j-1].getDay().compareTo(accounts[j].getDay())>0)
                {
                    temp=accounts[j-1];
                    accounts[j-1]=accounts[j];
                    accounts[j]=temp;
                }
            }
        }
        for(int i=0; i<size; i++)
        {
            for(int j=1; j<size; j++)
            {
                if(accounts[j-1].getMonth().compareTo(accounts[j].getMonth())>0)
                {
                    temp=accounts[j-1];
                    accounts[j-1]=accounts[j];
                    accounts[j]=temp;
                }
            }
        }
        for(int i=0; i<size; i++)
        {
            for(int j=1; j<size; j++)
            {
                if(accounts[j-1].getYear().compareTo(accounts[j].getYear())>0)
                {
                    temp=accounts[j-1];
                    accounts[j-1]=accounts[j];
                    accounts[j]=temp;
                }
            }
        }
    } 
    
    //sort in ascending order
    private void sortByLastName() { 
        for(int i=0; i<size; i++)
        {
            for(int j=1; j<size; j++)
            {
                if(accounts[j-1].getFname().compareTo(accounts[j].getFname())>0)
                {
                    temp=accounts[j-1];
                    accounts[j-1]=accounts[j];
                    accounts[j]=temp;
                }
            }
        }
        for(int i=0; i<size; i++)
        {
            for(int j=1; j<size; j++)
            {
                if(accounts[j-1].getLname().compareTo(accounts[j].getLname())>0)
                {
                    temp=accounts[j-1];
                    accounts[j-1]=accounts[j];
                    accounts[j]=temp;
                }
            }
        }
    } 
    
    public void printByDateOpen() { 
        sortByDateOpen();
        
    }
    
    public void printByLastName() {
        sortByLastName();
    }
    
    public void printAccounts() {
        for (int i = 0; i < size; i++) {
            System.out.println(accounts[i].toString());
        }
    }
}
