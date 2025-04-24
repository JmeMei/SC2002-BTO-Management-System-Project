package BTOManagementSystem.Model;
/**
 * Represents a request made by an applicant to withdraw from a BTO project application.
 * <p>
 * The request includes the applicant's name, NRIC, the project name, and the processing status.
 */
public class WithdrawalRequest {

    private String Name;
    private String Nric;
    private String ProjectName;
    private String processed;

    /**
     * Constructs a new {@code WithdrawalRequest} object with the provided details.
     *
     * @param name        the name of the applicant
     * @param nric        the NRIC of the applicant
     * @param projectName the name of the project the applicant wants to withdraw from
     * @param processed   the processing status (e.g., "0" for unprocessed, "1" for processed)
     */
    public WithdrawalRequest(String name, String nric, String projectName, String processed) {

        this.Name = name;
        this.Nric = nric;
        this.ProjectName = projectName;
        this.processed = processed;

    }

    /**
     * @return the name of the applicant
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the applicant's name.
     *
     * @param Name the full name of the applicant
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the NRIC of the applicant
     */
    public String getNric() {
        return Nric;
    }

    /**
     * Sets the NRIC of the applicant.
     *
     * @param Nric the NRIC to set
     */
    public void setNric(String Nric) {
        this.Nric = Nric;
    }

    /**
     * @return the name of the BTO project for which the withdrawal request was made
     */
    public String getProjectName() {
        return ProjectName;
    }

    /**
     * Sets the name of the project.
     *
     * @param ProjectName the project name to set
     */
    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    /**
     * @return the processing status as an integer (0 = unprocessed, 1 = processed)
     */
    public int getProcessed() {
        return Integer.parseInt(processed);
    }

    /**
     * Sets the processing status.
     *
     * @param processed 0 for unprocessed, 1 for processed
     */
    public void setProcessed(int processed) {
        this.processed = String.valueOf(processed);
    }



}
