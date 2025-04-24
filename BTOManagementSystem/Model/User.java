package BTOManagementSystem.Model;

/**
 * Represents a generic user in the BTO Management System.
 * <p>
 * This class serves as a superclass for specific user roles such as
 * Applicant, HDB Officer, and HDB Manager. It contains basic user
 * information including name, NRIC, age, marital status, password, and role.
 */
public class User {
    protected String name;
    protected String nric;
    protected String password;
    protected int age;
    protected String maritalStatus;
    protected String role;

    /**
     * Constructs a new {@code User} with the given details.
     *
     * @param name          the full name of the user
     * @param nric          the NRIC of the user
     * @param age           the user's age
     * @param maritalStatus the user's marital status
     * @param password      the user's password
     * @param role          the role of the user (e.g., "Applicant", "Officer", "Manager")
     */
    public User(String name, String nric, int age, String maritalStatus, String password, String role) {
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password; // default
        this.role = role;

    }

    /**
     * @return the user's full name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the user's NRIC
     */
    public String getNric() {
        return nric;
    }

    /**
     * @return the user's age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the user's marital status
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @return the role of the user (e.g., "Applicant", "Manager", "Officer")
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets a new password for the user.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
