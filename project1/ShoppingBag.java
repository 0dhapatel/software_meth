import java.text.DecimalFormat;

public class ShoppingBag {
  private GroceryItem[] bag; // array-based implementation of the bag
  private int size; // number of items currently in the bag
  private int capacity; // current capacity
  
  public int DEFAULT_ITEM_SIZE = 5; // defult bag size 
  private static DecimalFormat df2 = new DecimalFormat("#.##"); // formats double paramater to two decimal place
  
  /**
  * The public constructor method initiates bag, size, and capacity
  * @param this.bag array of GroceryItem object 
  * @param this.size integer of items in the bag
  * @param this.capacity integer of items that can fit in the bag
  */
  public ShoppingBag() {
    this.bag = new GroceryItem[DEFAULT_ITEM_SIZE];
    this.size = 0;
    this.capacity = 5;
  }
  
  
  /**
  * This is a helper method to find a first item in the bag
  @param i integer pointer to help go through the bag
  * @return -1 if item not found in the bag, else i if the array key is found
  */
  private int find(GroceryItem item) {
  
      for(int i = 0; i < this.size; i++ ) 
      {
          if(item.equals(this.bag[i])){
              return i; 
          }
      }
      return -1;      
      
   }
  
  /**
  * This is a helper method to grow the capacity of the bag by 5
  @param i integer pointer to help go through the bag
  */
  private void grow() { 
    this.capacity = this.capacity + 5;
    GroceryItem[] temp_bag = new GroceryItem[this.capacity];
    for (int i = 0; i < this.size; i++){
      temp_bag[i] = this.bag[i];
    }
    this.bag = new GroceryItem[this.capacity];
    this.bag = temp_bag;
  } 
  
  /**
  * This methos adds item object into the bag array. 
  * if the size is equal to capicity the bag has to grow().
  * The object consists of name, price, and taxable. 
  * At the end the size is incremented by 1. 
  */
  public void add(GroceryItem item) {
  
      if(this.size==this.capacity){ //if capicity is smaller, use helper method grow to add more sapce 
        grow();
      }
      
      this.bag[this.size] = item;
      this.size = this.size + 1; 
  }
  
  /**
  * This methos removes item object from the bag array. Finds the item from the bag using find(). Swaps the last item with the item to be removed.
  * The last item is set as null. The object consists of name, price, and taxable. At the end the size is decremented by 1. 
  * @param key helps to check if item is found
  * @return true if the item was found and removed successfully, else false if item not found
  */
  public boolean remove(GroceryItem item) {
      //key is to check in find() if item is in bag
      int key = find(item);
      //item not found
      if (key == -1){
          return false;
      }
      
      //item found and removed successfully
      else{
          this.size = this.size - 1; //remove size by 1
          //taking the last item and replacing to thebeing removed
          this.bag[key] = this.bag[this.size]; 
          
          //setting last item to null
          this.bag[this.size]=NULL;
      }
      return true;     
  }
  
  /**
  * Calculates the total price that are in the bag without considering tax.
  * @param salesPrice total price of the bag
  */
  public double salesPrice(){
  
  double salesPrice;
  
  for(int i = 0; i < this.size; i++){
      
      salesPrice += this.bag[i].get_price();
  
  }
      return salesPrice; 
  
   }
  
  /**
  * Calculates the total tax that are in the bag.
  * @param salesTax total tax of the bag
  */
  public double salesTax() {
  
  double salesTax;
  
  for(int i = 0; i < this.size; i++){
      
     if(this.bag[i].get_taxable()){
     salesTax += this.bag[i].get_price()*0.06625;
     }
  
  }
      return salesTax; 
   }
  
  /**
  * This method helps to print out all of the items that are currently in the bag in a list format.
  */
  public void print() {
   
    for(int i = 0; i < this.size; i++){
      System.out.println("\u2022"+bag[i].toString());
  }
   }
  
}
