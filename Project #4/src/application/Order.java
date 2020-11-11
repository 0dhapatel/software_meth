/**
 * 
 */
package application;

import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * @author Jei Mota and Dhaval Patel
 *
 */
public class Order implements Customizable{
	public static int lineNumber; //reset for each new order;
	private ArrayList<OrderLine> orderlines;
	
	/**
	 * Formats a double parameter to two decimal places of precision
	 */
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	public Order(){
		lineNumber = 1 ;
		this.orderlines = new ArrayList<OrderLine>();
	}
	
	@Override
	public boolean add(Object obj) {
		if (obj instanceof OrderLine) {
			orderlines.add((OrderLine)obj);
			lineNumber = lineNumber + 1;
			return true;
		}
		return false;
	}
	
	private void reorderLine(int index) {
		for(int i = index; i<orderlines.size(); i++) {
			OrderLine temp = orderlines.get(index);
			int lineNum = temp.getLineNum();
			temp.setLineNum(lineNum - 1);
			orderlines.set(index, temp);
		}
	}

	@Override
	public boolean remove(Object obj) {
		if(orderlines.size() == 0) {
			return false;
		}
		if (obj instanceof OrderLine) {
			int index = orderlines.indexOf((OrderLine)obj);
			orderlines.remove((OrderLine)obj);
			reorderLine(index);
			return true;
		}
		return false;
	}
	
	public void clearOrder() {
		orderlines.clear();
		lineNumber = 1;
	}
	
	public String printOrder() {
		return null;
	}
}
