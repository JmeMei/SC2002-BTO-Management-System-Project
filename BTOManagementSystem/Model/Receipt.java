package BTOManagementSystem.Model;

/**
 * Represents a receipt issued to an applicant after a successful BTO flat booking.
 * <p>
 * This model captures relevant information including the applicant’s personal
 * and application details, such as name, NRIC, flat type, project, and age.
 */
public class Receipt {

    private String applicantName;
    private String applicantNRIC;
    private String maritalStatus;
    private String flatType;
    private int Age;
    private String projectName;

    /**
     * Constructs a new {@code Receipt} object with all necessary information.
     *
     * @param applicantName  the name of the applicant
     * @param applicantNRIC  the NRIC of the applicant
     * @param maritalStatus  the marital status of the applicant
     * @param flatType       the type of flat booked
     * @param Age            the age of the applicant
     * @param projectName    the name of the project the flat was booked from
     */
    public Receipt(String applicantName, String applicantNRIC, String maritalStatus, String flatType, int Age, String projectName) {
        this.applicantName = applicantName;
        this.applicantNRIC = applicantNRIC;
        this.maritalStatus = maritalStatus;
        this.flatType = flatType;
        this.Age = Age;
        this.projectName = projectName;
    }

    // Getter and Setter for applicantName
    /**
     * @return the full name of the applicant
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * Sets the applicant’s name.
     *
     * @param applicantName the full name to set
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     * @return the NRIC of the applicant
     */
    public String getApplicantNRIC() {
        return applicantNRIC;
    }

    /**
     * Sets the NRIC of the applicant.
     *
     * @param applicantNRIC the NRIC to set
     */
    public void setApplicantNRIC(String applicantNRIC) {
        this.applicantNRIC = applicantNRIC;
    }

    /**
     * @return the marital status of the applicant
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the marital status of the applicant.
     *
     * @param maritalStatus the marital status to set
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return the type of flat booked by the applicant
     */
    public String getFlatType() {
        return flatType;
    }

    /**
     * Sets the type of flat booked.
     *
     * @param flatType the flat type (e.g., "2-Room", "3-Room")
     */
    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    /**
     * @return the age of the applicant
     */
    public int getAge() {
        return Age;
    }

    /**
     * Sets the age of the applicant.
     *
     * @param age the age to set
     */
    public void setAge(int age) {
        this.Age = age;
    }

    /**
     * @return the name of the project where the flat was booked
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the name of the project.
     *
     * @param projectName the name of the project
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
