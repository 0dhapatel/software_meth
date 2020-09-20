import java.util.Scanner;
import java.text.DecimalFormat;

public class GroceryItem {
  private String name;
  private double price;
  private boolean taxable;
  
  // Constructor
  public GroceryItem(String name, double price, boolean taxable){
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
    return this.toString().equals(obj.toString());
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
  
  public static void main(String []args){
      
         GroceryItem test1 = new GroceryItem("milk", 4.99, false);
         GroceryItem test2 = new GroceryItem("milk", 4.99, false);
         GroceryItem test3 = new GroceryItem("water", 2.99, true);
         
         System.out.println("Constructor");
         System.out.println(test1.name+""+test1.price+""+test1.taxable);
         
         System.out.println("Observer");
         System.out.println(test1.get_name()+""+test1.get_price()+""+test1.get_taxable());
         
         System.out.println("equals()");
         System.out.println(test1.equals(test2));
         System.out.println(test1.equals(test3));
         System.out.println(test3.equals(test3));
         
        System.out.println("toString()");
        GroceryItem test4= new GroceryItem("laptop", 504.99222, true);
        System.out.println(test1.toString());
        System.out.println(test4.toString());

     }
}
