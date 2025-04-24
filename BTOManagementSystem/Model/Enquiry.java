package BTOManagementSystem.Model;

/**
 * Represents an enquiry submitted by an applicant for a BTO project.
 * <p>
 * An enquiry includes the project it refers to, the applicant's NRIC,
 * the question content, the officer and manager handling it, and the reply if available.
 */
public class Enquiry {
    private String enquiryID;
    private String projectName;
    private String applicantNRIC;
    private String question;
    private String officerIC;
    private String answer;
    private String managerIC;

    // constructor without answer
    /**
     * Constructs an enquiry without an answer (used when creating a new enquiry).
     *
     * @param enquiryID     the unique ID of the enquiry
     * @param projectName   the name of the project related to the enquiry
     * @param applicantNRIC the NRIC of the applicant
     * @param question      the question content
     * @param officerIC     the NRIC of the officer assigned to respond
     * @param managerIC     the NRIC of the manager overseeing the project
     */
    public Enquiry(String enquiryID, String projectName, String applicantNRIC, String question, String officerIC, String managerIC) {
        this.enquiryID = enquiryID;
        this.projectName = projectName;
        this.applicantNRIC = applicantNRIC;
        this.question = question;
        this.officerIC = officerIC;
        this.answer = "";
        this.managerIC = managerIC;
    }

    // constructor for answer (load and save enquiries)

    /**
     * Constructs an enquiry with an answer (used when loading from storage).
     *
     * @param enquiryID     the unique ID of the enquiry
     * @param projectName   the name of the project related to the enquiry
     * @param applicantNRIC the NRIC of the applicant
     * @param question      the question content
     * @param answer        the answer provided to the enquiry
     * @param officerIC     the NRIC of the officer assigned to respond
     * @param managerIC     the NRIC of the manager overseeing the project
     */
    public Enquiry(String enquiryID, String projectName, String applicantNRIC, String question,String answer, String officerIC, String managerIC) {
        this.enquiryID = enquiryID;
        this.projectName = projectName;
        this.applicantNRIC = applicantNRIC;
        this.question = question;
        this.officerIC = officerIC;
        this.answer = answer;
        this.managerIC = managerIC;
    }

    // Getters and Setters

    /**
     * @return the unique enquiry ID
     */
    public String getEnquiryID() {
        return enquiryID;
    }

    /**
     * Sets the enquiry ID.
     *
     * @param enquiryID the enquiry ID to set
     */
    public void setEnquiryID(String enquiryID) {
        this.enquiryID = enquiryID;
    }

    /**
     * @return the name of the project the enquiry is related to
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the project name.
     *
     * @param projectName the name of the project
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the NRIC of the applicant who submitted the enquiry
     */
    public String getApplicantNRIC() {
        return applicantNRIC;
    }

    /**
     * Sets the applicant's NRIC.
     *
     * @param applicantNRIC the NRIC to set
     */
    public void setApplicantNRIC(String applicantNRIC) {
        this.applicantNRIC = applicantNRIC;
    }

    /**
     * @return the enquiry question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the question text.
     *
     * @param question the question content
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the NRIC of the officer assigned to this enquiry
     */
    public String getofficerIC() {
        return officerIC;
    }

    /**
     * Sets the officer's NRIC.
     *
     * @param officerIC the officer's NRIC
     */
    public void setofficerIC(String officerIC) {
        this.officerIC = officerIC;
    }

    /**
     * @return the answer to the enquiry
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the answer to the enquiry.
     *
     * @param answer the response text
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the NRIC of the manager overseeing the project
     */
    public String getManagerIC() {
        return managerIC;
    }

    /**
     * Sets the manager's NRIC.
     *
     * @param managerIC the manager's NRIC
     */
    public void setManagerIC(String managerIC) {
        this.managerIC = managerIC;
    }

    /**
     * Returns a CSV-formatted string of this enquiry's data.
     *
     * @return a single-line, comma-separated representation of the enquiry
     */
    @Override
    public String toString() {
        return String.join(",",
                enquiryID,
                projectName,
                applicantNRIC,
                question,
                answer,
                officerIC,
                managerIC
        );
    }

}
