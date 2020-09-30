public class AccountDatabase {
    
    private Account[] accounts;
    
    private int size;
    
    private int defaultAccountSize = 5;
    
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
            this.accounts[this.size]=NULL;
      }
      return true; 
    } 
    
    public boolean deposit(Account account, double amount) { }
    
    //return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist
    public int withdrawal(Account account, double amount) { }
    
    private void sortByDateOpen() { } //sort in ascending order
    
    private void sortByLastName() { } //sort in ascending order
    
    public void printByDateOpen() { 
        sortByDateOpen();
    }
    
    public void printByLastName() {
        sortByLastName();
    }
    
    public void printAccounts() { }
}
