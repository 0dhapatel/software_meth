public class Profile {
    private String fname;
    private String lname;
    
    public Profile(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
    }   
        
    public String get_fname(){
        return this.fname;
    }
    
    public String get_lname(){
        return this.lname;
    }
}
