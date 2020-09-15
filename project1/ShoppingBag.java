public class ShoppingBag {
  private GroceryItem[] bag; // array-based implementation of the bag
  private int size; // number of items currently in the bag
  private int capacity; // current capacity
  
  public int DEFAULT_ITEM_SIZE = 5;
  
  
  public ShoppingBag() {
    this.bag = new GroceryItem[DEFAULT_ITEM_SIZE];
    this.size = 0;
    this.capacity = 5;
  }
  
  // helper method to find an item
  private int find(GroceryItem item) {
  
      for(int i = 0; i < this.size; i++ ) //the for loop runs through the array of items
      {
          if(item.equals(this.bag[i])){
              return i; 
          }
      }
      return -1;      
      
   }
  
  private void grow() { } // helper method to grow the capacity
  
  /* This methos adds item object into the bag array. 
  The object consists of name, price, and taxable. 
  At the end the @size is incremented by 1. */
  
  public void add(GroceryItem item) {
  
      if(this.size+1>this.capacity){ //if capicity is smaller, use helper method grow to add more sapce 
      grow();
      }
      
      this.bag[this.size] = item;
      this.size = this.size + 1; 
  }
  
  public boolean remove(GroceryItem item) {
      //key is to check in find() if item is in bag
      int key = find(item);
      //item not found
      if (key == -1){
          return 0;
      }
      
      //item found and removed successfully
      else{
          this.size = this.size - 1; //remove size by 1
          //taking the last item and replacing to thebeing removed
          this.bag[key] = this.bag[this.size]; 
          
          //setting last item to null
          this.bag[this.size]=NULL;
      }
      return 1;     
  }

  public double salesPrice(){
  
  double salesPrice;
  
  for(int i = 0; i < this.size; i++){
      
      salesPrice += this.bag[i].get_price;
  
  }
      return salesPrice; 
  
   }
  
  public double salesTax() {
  
  double salesTax;
  
  for(int i = 0; i < this.size; i++){
      
     if(this.bag[i].get_taxable){
     salesTax += this.bag[i].get_price*0.06625;
     }
  
  }
      return salesTax; 
   }
  
  public void print(double salesPrice, double SalesTax) {
  
  System.out.printf("%2d);
  
  
   }
  
}
