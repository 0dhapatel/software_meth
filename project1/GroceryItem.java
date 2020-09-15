public class GroceryItem {
  private String name;
  private double price;
  private boolean taxable;
  
  // Constructor
  public GroceryItem(String name, double prize, boolean taxable){
    this.name=name;
    this.price=price;
    this.taxable=taxable;
  }
  
  //Observer Method for each Variable
  public String get_name(){
    return this.name;
  }
  public double get_price(){
    return this.price;
  }
  public boolean taxable(){
    return this.taxable;
  }
  
  public boolean equals(Object obj){
    if(this.name.equals(obj.name);
       if(this.price == obj.price && this.taxable == obj.taxable){
         return 1;
       }
      }
    return 0; 
  }
  public String toString() { 
    String merged_string = this.name + this.price + this.taxable; //not done right have to fix based on criteria
    return merged_string;
  }
  
}
