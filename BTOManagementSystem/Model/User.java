package BTOManagementSystem.Model;

public class User {
    protected String name;
    protected String nric;
    protected String password;
    protected int age;
    protected String maritalStatus;
    protected String role;

    public User(String name, String nric, int age, String password,String maritalStatus, String role) {
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password; // default
        this.role = role;

    }

    public String name(){
        return name;
    }
    
    public String getNric() {
        return nric;
    }

    public int getAge() {
        return age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getRole(){
        return role;
    }

    public String getPassword(String NRIC){
        return password;
    }

}
