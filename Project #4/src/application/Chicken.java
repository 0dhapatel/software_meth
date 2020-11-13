/**
 * 
 */
package application;

/**
 * @author Jei Mota and Dhaval Patel
 *
 */
public class Chicken extends Sandwich {

	private double prices = 8.99;
	private String basicIngredients = "Fried Chicken, Spicy Sauce, Pickles";
	

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
		return "Chicken";
	}

}
