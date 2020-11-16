/**
 * 
 */
package application;

import java.text.DecimalFormat;

/**
 * @author Jei Mota and Dhaval Patel
 *
 */
public class OrderLine {
	private int lineNumber; //a serial number created when a sandwich is added to the order
	private Sandwich sandwich;
	private double price;
	
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	public OrderLine(Sandwich sandwich){
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
	}
	
	public void setSandwich (Sandwich sandwich) {
		this.sandwich = sandwich;
	}
	
	public Sandwich getSandwich() {
		return this.sandwich;
	}
	
	public String getBasicIngredients() {
		return sandwich.basicIngredient();
	}

	public String allOrder() {
		return this.lineNumber + " " + this.sandwich.toString() + "$" + df2.format(sandwich.price());
	}
}
