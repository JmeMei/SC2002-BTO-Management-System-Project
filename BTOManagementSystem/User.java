package BTOManagementSystem;
public abstract class User {
    protected String nric;
    protected String password;
    protected int age;
    protected String maritalStatus;

    public User(String nric, int age, String maritalStatus) {
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = "password"; // default
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
}
