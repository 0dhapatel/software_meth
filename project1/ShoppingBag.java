public class ShoppingBag {
  private GroceryItem[] bag; // array-based implementation of the bag
  private int size; // number of items currently in the bag
  private int capacity; // current capacity
  
  
  public ShoppingBag() { }
  
  // helper method to find an item
  private int find(GroceryItem item) {
  
      C //the for loop runs through the array of items
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
      if(size+1>capacity){ //uses helper method grow to add more sapce is need in the array 
      grow();
      }
      bag[size].name = item.name; //add item name to bag
      bag[size].prize = item.prize; //add item prize to bag
      bag[size].taxable = item.taxable; //add item taxable to bag
      size+=1; //add size by 1
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
          bag[key].prize = bag[size].prize;
          bag[key].taxable = bag[size].prize;
          
          //setting last item to null
          bag[size].name=NULL;
          bag[size].prize=NULL;
          bag[size].taxable=NULL; 
      }
      return 1;     
  }

  public double salesPrice(){
  
  
   }
  
  public double salesTax() { }
  
  public void print() {
  
   }
  
}
