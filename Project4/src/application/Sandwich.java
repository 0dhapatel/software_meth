/**
 * 
 */
package application;

import java.util.ArrayList;
/**
 * @author Jei Mota and Dhaval Patel
 *
 */
public abstract class Sandwich  implements Customizable{
	static final int MAX_EXTRAS = 6;
	static final double PER_EXTRA = 1.99;
	protected ArrayList<Extra> extras;
	
	public abstract double price();
	public abstract String basicIngredient();
	public abstract String type();
	
	public Sandwich() {
		extras = new ArrayList<Extra>();
	}
	
	@Override
	public String toString() {
		String extra = "";
	    for (Extra ingred : extras) {
	        extra += ", " + ingred.getIngredient();
	    }
	    if(extra.length()>3) {
	    	extra = extra.substring(2) + "; ";
	    }
		return basicIngredient() + "; " + extra;
	}
	
	@Override
	public boolean add(Object obj) {
		if(extras.size() >= MAX_EXTRAS) {
			return false;
		}
		if (obj instanceof Extra) {
			extras.add((Extra)obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		if(extras.size() == 0) {
			return false;
		}
		if (obj instanceof Extra) {
			extras.remove((Extra)obj);
			return true;
		}
		return false;
	}
}
