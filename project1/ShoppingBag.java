public class ShoppingBag {
  private GroceryItem[] bag; // array-based implementation of the bag
  private int size; // number of items currently in the bag
  private int capacity; // current capacity
  public ShoppingBag() { }
  private int find(GroceryItem item) { } // helper method to find an item
  private void grow() { } // helper method to grow the capacity
  public void add(GroceryItem item) { }
  public boolean remove(GroceryItem item) { }
  public double salesPrice() { }
  public double salesTax() { }
  public void print() { }
}
