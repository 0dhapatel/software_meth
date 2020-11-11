/**
 * 
 */
package application;

/**
 * @author Jei Mota and Dhaval Patel
 *
 */
public class OrderLine {
	private int lineNumber; //a serial number created when a sandwich is added to the order
	private Sandwich sandwich;
	private double price;
	
	public OrderLine(Sandwich sandwich){
		this.lineNumber = Order.lineNumber;
		this.sandwich = sandwich;
		this.price = sandwich.price();
	}
	
	public void setLineNum (int lineNumber) {
		 this.lineNumber = lineNumber;
	}
	
	public int getLineNum () {
		 return this.lineNumber;
	}
	
	public double getPrice () {
		 return this.price;
	}
	
	public void setPrice (double price) {
		 this.price = price;
		 this.price = sandwich.price();
	}
	
	public void setSandwich (Sandwich sandwich) {
		this.sandwich = sandwich;
	}
	
	public String getSandwich() {
		return this.sandwich.toString();
	}
	
	public String getBasicIngredients() {
		return sandwich.basicIngredient();
	}

	public String allIngredients() {
		return sandwich.toString();
	}
}
