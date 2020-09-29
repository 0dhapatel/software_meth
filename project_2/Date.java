public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    
    public Date date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    
    
    public int compareTo(Date date) { } //return 0, 1, or -1
    public String toString() { } //in the format mm/dd/yyyy
    public boolean isValid() { }
}
