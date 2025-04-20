package BTOManagementSystem.Model;

public class OfficerRegistrationRequest {

    String officerName;
    String NRIC;
    String ProjectName;
    String Status;

    public OfficerRegistrationRequest(String officerName, String NRIC, String ProjectName, String Status) {

        this.officerName = officerName;
        this.NRIC = NRIC;
        this.ProjectName = ProjectName;
        this.Status = Status;

    }

    public String getOfficerName() {
        return officerName;

    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;

    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}
