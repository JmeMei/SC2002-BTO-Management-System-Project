package BTOManagementSystem.Model;

public class User {
    protected String name;
    protected String nric;
    protected String password;
    protected int age;
    protected String maritalStatus;
    protected String role;

    public User(String name, String nric, int age, String maritalStatus, String password, String role) {
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password; // default
        this.role = role;

    }

    public String getName(){
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

    public String getPassword(){return password;}

    public void setPassword(String password)  {
        this.password = password;
    }

}
