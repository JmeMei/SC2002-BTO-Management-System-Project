package BTOManagementSystem.Model;

import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;

public class ApplicantProjectStatus {
    private String name;
    private String nric;
    private int age;
    private String maritalStatus;
    private String password;
    private String role;

    private String projectName;
    private FlatType flatType;
    private ApplicationStatus applicationStatus;

    private String enquiry;
    private String reply;

    // Constructor
    public ApplicantProjectStatus(String name, String nric, int age, String maritalStatus, String role,
                                  String projectName, FlatType flatType, ApplicationStatus applicationStatus) {
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.role = role;
        this.projectName = projectName;
        this.flatType = flatType;
        this.applicationStatus = applicationStatus;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getNric() { return nric; }
    public int getAge() { return age; }
    public String getMaritalStatus() { return maritalStatus; }
    public String getRole() { return role; }

    public String getProjectName() { return projectName; }
    public FlatType getFlatType() { return flatType; }
    public ApplicationStatus getApplicationStatus() { return applicationStatus; }

    public void setProjectName(String projectName) { this.projectName = projectName; }
    public void setFlatType(FlatType flatType) { this.flatType = flatType; }
    public void setApplicationStatus(ApplicationStatus applicationStatus) { this.applicationStatus = applicationStatus; }
}

