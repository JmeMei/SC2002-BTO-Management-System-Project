package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.User;

//Name,NRIC,Age,Marital Status,Password,Project Name,Type, Application Status, Enquiry, Reply

public class Applicant extends User{
    private String projectName;
    private String Type;
    private String applicationStatus;
    private String enquiry;
    private String reply;

    public Applicant(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password, "Applicant");

        // Default values if not yet applied
        this.projectName = "";
        this.applicationStatus = "";
        this.enquiry = "";
        this.reply = "";
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    // === Helper ===
    //public boolean hasApplied() {return projectApplied != null && !projectApplied.isEmpty();}

}
