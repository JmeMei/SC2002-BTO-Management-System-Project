package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.User;

//Name,NRIC,Age,Marital Status,Password,Project Name,Type, Application Status, Enquiry, Reply
/**
 * Represents an HDB Officer in the BTO Management System.
 * HDB Officers can apply for BTO projects as applicants and
 * also register to manage a project. They can respond to enquiries
 * related to their assigned projects.
 * Inherits from {@link User}.
 * Expected CSV format:
 * Name, NRIC, Age, Marital Status, Password, Project Name, Type, Application Status, Enquiry, Reply
 */
public class HDBOfficer extends User {
    private String projectName;
    private String Type;
    private String applicationStatus;
    private String enquiry;
    private String reply;

    /**
     * Constructs an HDB Officer with the provided user details.
     *
     * @param name          The name of the officer.
     * @param nric          The NRIC of the officer.
     * @param age           The age of the officer.
     * @param maritalStatus The marital status of the officer.
     * @param password      The password associated with the officer's account.
     */
    public HDBOfficer(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password,"Officer");

        // Default values if not yet applied
        this.projectName = "";
        this.applicationStatus = "";
        this.enquiry = "";
        this.reply = "";
    }

    /**
     * Gets the name of the project the officer is currently assigned to.
     *
     * @return The project name.
     */
    public String getProjectName() {
        return projectName;
    }

}
