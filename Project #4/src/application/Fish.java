/**
 * 
 */
package application;

/**
 * @author Jei Mota and Dhaval Patel
 *
 */
public class Fish extends Sandwich {

	private double prices = 12.99;
	private String basicIngredients = "Grilled Snapper, Cilantro, Lime";

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
		return "Fish";
	}

}
