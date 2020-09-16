import java.util.Scanner;

public class Shopping {

	public void run() {
		 
		ShoppingBag bag = new ShoppingBag();
		
		Scanner keyboard = new Scanner(System.in); 
		 char userImput = keyboard.next().charAt(0);
		 
		 //System.out.println("Let's start shopping!");  //do not need
		 while(userImput != 'Q') {
		 
		 switch (userImput) {
	          
		  	  case 1:  userImput = 'A';
		  	GroceryItem item = new GroceryItem(keyboard.nextLine(), keyboard.nextDouble(), keyboard.nextBoolean());
		  	  bag.add(item);
	                   break;
	          case 2:  userImput = 'R';
	
	          bag.remove();
	                   break;
	          case 3:  userImput = 'P';
	                   break;
	          case 4:  userImput = 'C';
	                   break;
	          case 5:  userImput = 'Q';
				 System.out.println("Thanks for shopping with us!");
	                   break;
	          default: System.out.println("Invalid command!"); 
	                   break;
      }
		 
		 }
	}
}

