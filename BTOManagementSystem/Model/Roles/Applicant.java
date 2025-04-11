package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.User;

public class Applicant extends User{
    private String projectApplied;
    private String applicationStatus;
    private String enquiry;
    private String reply;

    public Applicant(String name, String nric, int age, String password, String maritalStatus) {
        super(name, nric, age, password, maritalStatus, "Applicant");

        // Default values if not yet applied
        this.projectApplied = "";
        this.applicationStatus = "";
        this.enquiry = "";
        this.reply = "";
    }

    // === Application Info ===

    //Project Applied
    public String getProjectApplied() {
        return projectApplied;
    }

    public void setProjectApplied(String projectApplied) {
        this.projectApplied = projectApplied;
    }

    //Application Status
    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    // Enquiry
    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }

    //Reply
    public String getReply() {
        return reply;
    }

    //Applicant should not be able to reply
    // public void setReply(String reply) {
    //     this.reply = reply;
    // }

    // === Helper ===
    public boolean hasApplied() {
        return projectApplied != null && !projectApplied.isEmpty();
    }

}
