package BTOManagementSystem.Model;

/**
 * Represents a registration request made by an HDB Officer to be assigned to a BTO project.
 * <p>
 * This model includes the officer's name, NRIC, the project they want to join,
 * and the current status of their request (e.g., "Pending", "Approved").
 */
public class OfficerRegistrationRequest {

    String officerName;
    String NRIC;
    String ProjectName;
    String Status;

    /**
     * Constructs a new {@code OfficerRegistrationRequest}.
     *
     * @param officerName the name of the officer
     * @param NRIC        the NRIC of the officer
     * @param ProjectName the name of the project the officer is registering for
     * @param Status      the current status of the registration (e.g., "Pending", "Approved")
     */
    public OfficerRegistrationRequest(String officerName, String NRIC, String ProjectName, String Status) {

        this.officerName = officerName;
        this.NRIC = NRIC;
        this.ProjectName = ProjectName;
        this.Status = Status;

    }

    /**
     * @return the name of the officer
     */
    public String getOfficerName() {
        return officerName;
    }

    /**
     * Sets the officer's name.
     *
     * @param officerName the officer's full name
     */
    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    /**
     * @return the NRIC of the officer
     */
    public String getNRIC() {
        return NRIC;
    }

    /**
     * Sets the NRIC of the officer.
     *
     * @param NRIC the officer's NRIC
     */
    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    /**
     * @return the name of the project the officer is applying to
     */
    public String getProjectName() {
        return ProjectName;
    }

    /**
     * Sets the name of the project.
     *
     * @param ProjectName the project name
     */
    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    /**
     * @return the current status of the registration request
     */
    public String getStatus() {
        return Status;
    }

    /**
     * Sets the status of the registration request.
     *
     * @param Status the request status (e.g., "Pending", "Approved")
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

}
