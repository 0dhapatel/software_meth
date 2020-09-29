

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    
    
    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public int compareTo(Date date) { //return 0, 1, or -1
        if(this.year > date.year){
            return 1;
        }
        if(this.year < date.year){
            return -1;
        }
        if(this.month > date.month){
            return 1;
        }
        if(this.month < date.month){
            return -1;
        }
        if(this.day > date.day){
            return 1;
        }
        if(this.day < date.day){
            return -1;
        }
        return 0;
    } 
    
    public String toString() { //in the format mm/dd/yyyy
        String mergedString = this.month + "/" + this.day + "/" + this.year;
        return mergedString;
    } 
    
    public boolean isValid() { 
        if (this.month > 0 && this.month < 13){
            if(this.month == 2){
                if(this.year % 4 == 0){
                    if(this.day > 0 && this.day 29){
                        return true;
                    }
                }
                else{
                    if(this.day > 0 && this.day 28){
                        return true;
                    }
                }
            }
            elseif(this.month == 4 && this.month == 6 && this.month == 9 && this.month == 11){
                if(this.day > 0 && this.day 30){
                    return true;
                }
            }
            else{
                if(this.day > 0 && this.day 31){
                    return true;
                }
            }
        }
        return false;
    }
}
