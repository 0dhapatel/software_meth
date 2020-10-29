package application;

/**
 * This class is used by Account to create an instance of Profile, which
 * contains a first name and last name
 * 
 * @author Jei Mota, Dhaval Patel
 *
 */

public class Profile {
	
	/**
	 * Profile's first name
	 */
	private String fname;
	
	/**
	 * Profile's last name
	 */
	private String lname;
	
	/**
	 * The public constructor instantiates the fields first name and last name
	 * 
	 * @param fname    Profile's first name
	 * @param lname    Profile's last name
	 */

	public Profile(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	
	/**
	 * Observer Method for first name
	 * 
	 * @return this.fname which is the Profile's first name
	 */

	public String get_fname() {
		return this.fname;
	}
	
	/**
	 * Observer Method for last name
	 * 
	 * @return this.lname which is the Profile's last name
	 */

	public String get_lname() {
		return this.lname;
	}
}
