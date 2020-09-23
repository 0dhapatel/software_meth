import java.util.Scanner;
import java.text.DecimalFormat;

public class Shopping {

	private static DecimalFormat df2 = new DecimalFormat("#.##"); //helps format the number to 2 decimal places as string
	
	private GroceryItem iteminfo (String[] cmdArr){
         	String name = cmdArr[1];
         	double price = Double.parseDouble(cmdArr[2]);
         	boolean tax = Boolean.parseBoolean(cmdArr[3]);
         	return new GroceryItem(name, price, tax);
     	}
	
	public void run() {
		
		ShoppingBag bag = new ShoppingBag();
		
		Scanner keyboard = new Scanner(System.in); 
		 char userImput = keyboard.next().charAt(0);
		 
		 System.out.println("Let's start shopping!"); 
		 while(userImput != 'Q') {
		 
		 switch (userImput) {
	          
		  	  case 1:  userImput = 'A';
		  	GroceryItem addItem = new GroceryItem(keyboard.nextLine(), keyboard.nextDouble(), keyboard.nextBoolean());
		  	  bag.add(addItem);
	                   break;
	          case 2:  userImput = 'R';
	          GroceryItem removeItem = new GroceryItem(keyboard.nextLine(), keyboard.nextDouble(), keyboard.nextBoolean());
	          //bag.remove(removeItem); do not need
		
		if(bag.remove(removeItem)){
			//this is true
		}else{
			//this is false
		}
				 
				 
	                   break;
	          case 3:  userImput = 'P';
	          bag.print();
	                   break;
	          case 4:  userImput = 'C';
	          
	          if(bag.size() == 0) {
	        	  System.out.println("Unable to check out, the bag is empty!");
	          }
	          else {
	        	  System.out.println("**Checking out"+ bag.size() + "item(s):");
	        	  bag.print();
	        	  System.out.println("*Sales total:" + bag.salesPrice());
	        	  System.out.println("*Sales tax:" + bag.salesTax());
	        	  System.out.println("*Total amount paid:" +  bag.salesPrice() + bag.salesTax() );
	        	  
	          }
	                   break;
	          case 5:  userImput = 'Q';
	          if(bag.size() == 0) {
	          System.out.println("Thanks for shopping with us!");
	          System.exit(1);
	          }
	          else {
	        	  System.out.println("**Checking out"+ bag.size() + "item(s):");
	        	  bag.print();
	        	  System.out.println("*Sales total:" + bag.salesPrice());
	        	  System.out.println("*Sales tax:" + bag.salesTax());
	        	  System.out.println("*Total amount paid:" +  bag.salesPrice() + bag.salesTax() );
	        	  System.out.println("Thanks for shopping with us!");
	          }
	                   break;
	          default: System.out.println("Invalid command!"); 
	                   break;
      }
		 
		 }
	}
}
