public class ShoppingBag {
  private GroceryItem[] bag; // array-based implementation of the bag
  private int size; // number of items currently in the bag
  private int capacity; // current capacity
  
  
  public ShoppingBag() { }
  
  // helper method to find an item
  private int find(GroceryItem item) {
  
      for(int i = 0; i < size; i++ ) //the for loop runs through the array of items
      {
          if(item.name.equals(bag[i].name)){
                  if(item.price==bag[i].price&&item.taxable == bag[i].taxable)
                      return i; /**nested  if statements to check if the bag array contains an item 
                                 with the same name, price, and*/ }
      }
      return -1;      
      
   }
  
  private void grow() { } // helper method to grow the capacity
  
  /* This methos adds item object into the bag array. 
  The object consists of name, price, and taxable. 
  At the end the @size is incremented by 1. */
  
  public void add(GroceryItem item) {
  
      if(size+1>capacity){ //if capicity is smaller, use helper method grow to add more sapce 
      grow();
      }
      
      bag[size].name = item.name; 
      bag[size].price = item.price; 
      bag[size].taxable = item.taxable; 
      size+=1; 
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
          size-=1; //remove size by 1
          //taking the last item and replacing to thebeing removed
          bag[key].name = bag[size].name; 
          bag[key].price = bag[size].price;
          bag[key].taxable = bag[size].taxable;
          
          //setting last item to null
          bag[size].name=NULL;
          bag[size].price=NULL;
          bag[size].taxable=NULL; 
      }
      return 1;     
  }

  public double salesPrice(){
  
  double salesPrice;
  
  for(int i = 0; i < size; i++){
      
      salesPrice += bag[i].price;
  
  }
      return salesPrice; 
  
   }
  
  public double salesTax() {
  
  double salesTax;
  
  for(int i = 0; i < size; i++){
      
     if(bag[i].taxable){
     salesTax += bag[i].price*0.06625;
     }
  
  }
      return salesTax; 
   }
  
  public void print(double salesPrice, double SalesTax) {
  
  System.out.printf("%2d);
  
  
   }
  
}
