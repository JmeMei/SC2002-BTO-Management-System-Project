package BTOManagementSystem.Model;

import java.util.Date;

public class ApplicantProjectStatus {
    private String applicantNric;
    private String projectName;
    private String flatType;
    private Date applicationDate;
    
    public ApplicantProjectStatus(String applicantNric, String projectName, String flatType, Date applicationDate) {
        this.applicantNric = applicantNric;
        this.projectName = projectName;
        this.flatType = flatType;
        this.applicationDate = applicationDate;
    }
    
    // Getters
    public String getApplicantNric() { return applicantNric; }
    public String getProjectName() { return projectName; }
    public String getFlatType() { return flatType; }
    public Date getApplicationDate() { return applicationDate; }
    
    // Setters
    public void setApplicantNric(String applicantNric) { this.applicantNric = applicantNric; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public void setFlatType(String flatType) { this.flatType = flatType; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }
}