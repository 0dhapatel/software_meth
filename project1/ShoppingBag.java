public class ShoppingBag {
  private GroceryItem[] bag; // array-based implementation of the bag
  private int size; // number of items currently in the bag
  private int capacity; // current capacity
  
  
  public ShoppingBag() { }
  
  // helper method to find an item
  private int find(GroceryItem item) {
  
      for(int i = 0; i < size; i++) //the for loop runs through the array of items
      {
          if(item.name.equals(bag[i].name)){
                  if(item.prize==bag[i].prize&&item.taxable == bag[i].taxable)
                      return i; /**nested  if statements to check if the bag array contains an item 
                                 with the same name, price, and*/ }
      }
      return -1;      
      
   }
  
  private void grow() { } // helper method to grow the capacity
  
  public void add(GroceryItem item) {
      if(size+1>capacity){
      grow();
      }
      bag[size].name = item.name;
      bag[size].prize = item.prize;
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
          size-=1;
          bag[key].name = bag[size].name;
          bag[key].prize = bag[size].prize;
          bag[key].taxable = bag[size].prize;
          bag[size].name=NULL;
          bag[size].prize=NULL;
          bag[size].taxable=NULL; 
      }
      return 1;     
  }

  public double salesPrice() {
  
   }
  
  public double salesTax() { }
  
  public void print() {
  
   }
  
}
