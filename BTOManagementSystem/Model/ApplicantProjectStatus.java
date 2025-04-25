package BTOManagementSystem.Model;

import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;

/**
 * Represents the application status of an applicant for a specific BTO project.
 * <p>
 * This class contains relevant user and application details such as name, NRIC,
 * age, marital status, applied project, flat type, and the status of the application.
 */
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
    /**
     * Constructs an {@code ApplicantProjectStatus} instance with essential information.
     *
     * @param name              the applicant's full name
     * @param nric              the applicant's NRIC
     * @param age               the applicant's age
     * @param maritalStatus     the applicant's marital status
     * @param role              the role of the user (e.g., "Applicant")
     * @param projectName       the name of the applied BTO project
     * @param flatType          the type of flat applied for
     * @param applicationStatus the current status of the application
     */
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

    // Setters and Getters

    /**
     * @return the full name of the applicant
     */
    public String getName() {
        return name;
    }

    /**
     * @return the NRIC of the applicant
     */
    public String getNric() {
        return nric;
    }

    /**
     * @return the age of the applicant
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the marital status of the applicant
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @return the user role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the name of the BTO project applied to
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @return the flat type applied for
     */
    public FlatType getFlatType() {
        return flatType;
    }

    /**
     * @return the application status
     */
    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    // Setters

    /**
     * Sets the project name.
     *
     * @param projectName the name of the project
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Sets the flat type.
     *
     * @param flatType the new flat type
     */
    public void setFlatType(FlatType flatType) {
        this.flatType = flatType;
    }

    /**
     * Sets the application status.
     *
     * @param applicationStatus the new application status
     */
    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}

