package BTOManagementSystem.Model;

public class Receipt {

    private String applicantName;
    private String applicantNRIC;
    private String maritalStatus;
    private String flatType;
    private int Age;
    private String projectName;

    public Receipt(String applicantName, String applicantNRIC, String maritalStatus, String flatType, int Age, String projectName) {
        this.applicantName = applicantName;
        this.applicantNRIC = applicantNRIC;
        this.maritalStatus = maritalStatus;
        this.flatType = flatType;
        this.Age = Age;
        this.projectName = projectName;
    }

    // Getter and Setter for applicantName
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    // Getter and Setter for applicantNRIC
    public String getApplicantNRIC() {
        return applicantNRIC;
    }

    public void setApplicantNRIC(String applicantNRIC) {
        this.applicantNRIC = applicantNRIC;
    }

    // Getter and Setter for maritalStatus
    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    // Getter and Setter for flatType
    public String getFlatType() {
        return flatType;
    }

    public int getAge() {
        return Age;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    // Getter and Setter for projectName
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setAge(int age) {
        Age = age;
    }
}
