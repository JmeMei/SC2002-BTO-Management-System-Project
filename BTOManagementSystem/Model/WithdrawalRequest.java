package BTOManagementSystem.Model;

public class WithdrawalRequest {

    private String Name;
    private String Nric;
    private String ProjectName;
    private String processed;

    public WithdrawalRequest(String name, String nric, String projectName, String processed) {

        this.Name = name;
        this.Nric = nric;
        this.ProjectName = projectName;
        this.processed = processed;

    }

    public String getName() {
        return Name;
    }
    public String getNric() {
        return Nric;
    }
    public String getProjectName() {
        return ProjectName;
    }

    public int getProcessed() {
        return Integer.parseInt(processed);
    }

    public void setProcessed(int processed) {
        this.processed = String.valueOf(processed);
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setNric(String Nric) {
        this.Nric = Nric;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

}
