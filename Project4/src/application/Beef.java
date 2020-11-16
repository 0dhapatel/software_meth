/**
 * 
 */
package application;

/**
 * This class extends the class Sandwich and Overrides the methods price,
 * basicIngredient, toString, add, remove and Type.
 * 
 * @author Jei Mota and Dhaval Patel
 *
 */
public class Beef extends Sandwich{
	/**
	 * Sandwich price
	 */
	private double prices = 10.99;
	private String basicIngredients = "Roast Beef, Provolone Cheese, Mustard";

	public Beef() {
		super();
	}
	
	@Override
	public double price() {
		return (prices + (extras.size()*PER_EXTRA));
	}

	@Override
	public String basicIngredient() {
		return basicIngredients;
	}
	
	@Override
	public String toString() {
		return type() + "; " +super.toString();
	}
	
	@Override
	public boolean add(Object obj) {
		super.add(obj);
		return false;
	}
	
	@Override
	public boolean remove(Object obj) {
		super.remove(obj);
		return false;
	}

	@Override
	public String type() {
		return "Beef";
	}

}
