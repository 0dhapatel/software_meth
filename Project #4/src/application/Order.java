/**
 * 
 */
package application;

import java.util.ArrayList;

/**
 * @author Jei Mota and Dhaval Patel
 *
 */
public class Order implements Customizable{
	public static int lineNumber; //reset for each new order;
	private ArrayList<OrderLine> orderlines;
	
	public Order(){
		lineNumber = 0;
		this.orderlines = new ArrayList<OrderLine>();
	}
	
	public int lineNum() {
		return orderlines.size();
	}
	
	public OrderLine startOrder() {
		return orderlines.get(lineNumber-1);
	}
	
	@Override
	public boolean add(Object obj) {
		if (obj instanceof OrderLine) {
			orderlines.add((OrderLine)obj);
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
	
	public ArrayList<String> printOrder() {
		ArrayList<String> print = new ArrayList<String>();
		for(int i = 0; i<orderlines.size(); i++) {
			print.add(orderlines.get(i).allOrder());
		}
		return print;
	}
	
	public double totalAmount() {
		double total = 0;
		for(int i = 0; i<orderlines.size(); i++) {
			total = total + orderlines.get(i).getPrice();
		}
		return total;
	}
}
