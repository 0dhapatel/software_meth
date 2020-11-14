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
	
	public OrderLine sameOrder(int index) {
		return orderlines.get(index);
	}
	
	@Override
	public boolean add(Object obj) {
		if (obj instanceof OrderLine) {
			lineNumber = lineNumber + 1;
			orderlines.add((OrderLine)obj);
			orderlines.get(lineNumber-1).setLineNum(lineNumber);
			return true;
		}
		return false;
	}
	
	private void reorderLine(int index) {
		for(int i = index; i<orderlines.size(); i++) {
			OrderLine temp = orderlines.get(i);
			int lineNum = temp.getLineNum();
			temp.setLineNum(lineNum - 1);
			orderlines.set(i, temp);
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
			lineNumber = lineNumber -1;
			return true;
		}
		return false;
	}
	
	public void clearOrder() {
		orderlines.clear();
		lineNumber = 0;
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
