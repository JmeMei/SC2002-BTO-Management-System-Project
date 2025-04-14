package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;

//APPLICANT IS CONNECTED TO APPLICANTPROJECTSTATS.CSV
//Name,NRIC,Age,Marital Status,Password,role,ProjectID, Application Status, Enquiry, Reply
public class Applicant extends User{
    public int ProjectID;
    public ApplicationStatus applicationStatus;
    public String Enquiry;
    public String Reply;

    public Applicant(String name, String nric, int age, String password, String maritalStatus, String role,
            int projectID, ApplicationStatus applicationStatus, String enquiry, String reply) {
        super(name, nric, age, password, maritalStatus, role);
        ProjectID = projectID;
        this.applicationStatus = applicationStatus;
        Enquiry = enquiry;
        Reply = reply;
    }

    //If there are missing parameters, it assigned default
    //this if for AuthController.
    public Applicant(String name, String nric, int age, String password, String maritalStatus) {
        // Default role is "Applicant"
        super(name, nric, age, password, maritalStatus, "Applicant");
        // Set defaults
        this.ProjectID = -1;
        this.applicationStatus = ApplicationStatus.NA;
        this.Enquiry = "";
        this.Reply = "";
    }
    
    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        ProjectID = projectID;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

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
