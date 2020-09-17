import java.util.Scanner;
import java.text.DecimalFormat;

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
  public boolean get_taxable(){
    return this.taxable;
  }
  
  private static DecimalFormat df2 = new DecimalFormat("#.##");
  
  public boolean equals(Object obj){
    if(this.name.equals(obj.name){
       if(this.price == obj.price && this.taxable == obj.taxable){
         return 1;
       }
    }
    return 0; 
  }
  public String toString() { 
    String price_string = df2.format(this.price);
    String tax_string = "";
    if(this.taxable){
      tax_string = "is taxable";
    }else{
      tax_string = "tax free";
    }
    String merged_string = this.name + ": $" + price_string + " : " + tax_string;
    return merged_string;
  }
  
}
