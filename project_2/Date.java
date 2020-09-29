import java.text.DecimalFormat;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    
    private static DecimalFormat twoPlaces = new DecimalFormat("00");
    private static DecimalFormat fourPlaces = new DecimalFormat("0000");
    
    
    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public int compareTo(Date date) { //return 0, 1, or -1
        return this.toString().equals(date.toString());
    } 
    
    public String toString() { //in the format mm/dd/yyyy
        String dayFormat = twoPlaces.format(this.day);
        String monthFormat = twoPlaces.format(this.month);
        String yearFormat = fourPlaces.format(this.year);
        String mergedString = monthFormat + "/" + dayFormat + "/" + yearFormat;
        return mergedString;
    } 
    
    public boolean isValid() { }
}
