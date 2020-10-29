

package application;

/**
 * This class which implements the interface Comparable is used by Account to
 * create an instance of Date, which contains the fields month, day and year.
 * 
 * @author Jei Mota, Dhaval Patel
 *
 */
public class Date implements Comparable<Date> {

	/**
	 * Date's year
	 */
	private int year;

	/**
	 * Date's month
	 */
	private int month;

	/**
	 * Date's day
	 */
	private int day;

	/**
	 * The public constructor instantiates the fields month, day, and year
	 * 
	 * @param month Date's month
	 * @param day   Date's day
	 * @param year  Date's year
	 */

	public Date(int month, int day, int year) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * Observer Method for year
	 * 
	 * @return this.year which is Date's year
	 */

	public int getYear() {
		return this.year;
	}

	/**
	 * Observer Method for month
	 * 
	 * @return this.month which is Date's month
	 */

	public int getMonth() {
		return this.month;
	}

	/**
	 * Observer Method for day
	 * 
	 * @return this.day which is Date's day
	 */

	public int getDay() {
		return this.day;
	}

	/**
	 * This method compares if the given dates are the same
	 * 
	 * @return 1 if it's greater. -1 if it's lesser. 0 if the dates are the same
	 */

	public int compareTo(Date date) { // return 0, 1, or -1
		if (this.year > date.year) {
			return 1;
		}
		if (this.year < date.year) {
			return -1;
		}
		if (this.month > date.month) {
			return 1;
		}
		if (this.month < date.month) {
			return -1;
		}
		if (this.day > date.day) {
			return 1;
		}
		if (this.day < date.day) {
			return -1;
		}
		return 0;
	}

	/**
	 * This method creates a string with month, day, and year in the format
	 * mm/dd/yyyy
	 * 
	 * @return mergedString
	 */

	@Override
	public String toString() { // in the format mm/dd/yyyy
		String mergedString = this.month + "/" + this.day + "/" + this.year;
		return mergedString;
	}

	/**
	 * This method checks if the given date is valid.
	 * 
	 * @return true if the month is between the parameters. true if the day is
	 *         between the parameters. false if the month or day are not between the
	 *         parameters
	 */
	public boolean isValid() {
		if (this.month > 0 && this.month < 13) {

			// Checks February
			if (this.month == 2) {
				if (this.year % 4 == 0) {
					if (this.year % 100 == 0) {
						if (this.year % 400 == 0) {

							if (this.day > 0 && this.day <= 29) {
								return true;
							}
						}
					}
				} else {
					if (this.day > 0 && this.day <= 28) {
						return true;
					}
				}
				// Checks April, June, September, and November
			} else if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
				if (this.day > 0 && this.day <= 30) {
					return true;
				}
				// Checks remaining months.
			} else {
				if (this.day > 0 && this.day <= 31) {
					return true;
				}
			}
		}
		return false;
	}

}
