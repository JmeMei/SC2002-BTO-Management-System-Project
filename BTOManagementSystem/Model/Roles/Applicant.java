package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;

//APPLICANT IS CONNECTED TO APPLICANTPROJECTSTATS.CSV

//Name,NRIC,Age,Marital Status,Password,role,ProjectID, Application Status, Enquiry, Reply
/**
 * Represents an Applicant user in the BTO Management System.
 * Applicants can apply to BTO projects, submit enquiries, and receive replies.
 * This class extends the {@link User} class and includes fields specific to application and enquiry processes.
 * Fields in ApplicantProjectStatus.csv:
 * Name, NRIC, Age, Marital Status, Password, Role, ProjectID, Application Status, Enquiry, Reply
 */
public class Applicant extends User {
    public int ProjectID;
    public String Enquiry;
    public String Reply;
    public FlatType FlatType;

    // Applicant-specific
    private String AppliedProject;        // Only one project
    private ApplicationStatus ApplicationStatus;     // "Pending", "Successful", "Unsuccessful", "Booked"
    private FlatType BookedFlatType;

    public Applicant(String name, String nric, int age, String password, String maritalStatus, String role,
                     String appliedProject, ApplicationStatus applicationStatus, FlatType bookedFlatType) {
        super(name, nric, age, password, maritalStatus, role);
        this.AppliedProject = appliedProject;
        this.ApplicationStatus = applicationStatus;
        this.BookedFlatType = bookedFlatType;
    }

    //If there are missing parameters, it assigned default
    //this if for AuthController.
    /**
     * Constructs an Applicant with only the user information.
     * Initializes application-related fields to default values.
     *
     * @param name          Applicant's name
     * @param nric          NRIC
     * @param age           Age
     * @param password      Password
     * @param maritalStatus Marital status
     */
    public Applicant(String name, String nric, int age, String password, String maritalStatus) {
        // Default role is "Applicant"
        super(name, nric, age, password, maritalStatus, "Applicant");
        // Set defaults
        this.AppliedProject = null;
        this.ApplicationStatus = ApplicationStatus.NA;
        this.Enquiry = "";
        this.Reply = "";
    }

    public String getAppliedProject() { return AppliedProject; }

    public void setAppliedProject(String appliedProject) { this.AppliedProject = AppliedProject; }

    public ApplicationStatus getApplicationStatus() {
        return ApplicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.ApplicationStatus = applicationStatus;
    }

    public FlatType getBookedFlatType() { return BookedFlatType; }

    public void setBookedFlatType(FlatType bookedFlatType) { this.BookedFlatType = bookedFlatType; }

    public String getEnquiry() {
        return Enquiry;
    }

    public void setEnquiry(String enquiry) {
        Enquiry = enquiry;
    }

    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }
}
