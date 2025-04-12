package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.User;

//Name,NRIC,Age,Marital Status,Password,Project Name,Type, Application Status, Enquiry, Reply

public class HDBOfficer extends Applicant{
    private String projectName;
    private String Type;
    private String applicationStatus;
    private String enquiry;
    private String reply;

    public HDBOfficer(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus,"password");

        // Default values if not yet applied
        this.projectName = "";
        this.applicationStatus = "";
        this.enquiry = "";
        this.reply = "";
    }
    public String getProjectName() {
        return projectName;
    }

    public void registertoJoinProject(Project p) {
        // Register to Join a Project
    }

    public void editProject(Project p) {
        // edit a Project
    }


    // === Helper ===
    //public boolean hasApplied() {return projectApplied != null && !projectApplied.isEmpty();}

}
