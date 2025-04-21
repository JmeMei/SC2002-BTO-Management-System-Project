package BTOManagementSystem.Model;

public class Enquiry {
    private String enquiryID;
    private String projectName;
    private String applicantNRIC;
    private String question;
    private String officerNRIC;
    private String answer;
    private String managerIC;

    // constructor without answer
    public Enquiry(String enquiryID, String projectName, String applicantNRIC, String question, String officerNRIC, String managerIC) {
        this.enquiryID = enquiryID;
        this.projectName = projectName;
        this.applicantNRIC = applicantNRIC;
        this.question = question;
        this.officerNRIC = officerNRIC;
        this.answer = "";
        this.managerIC = managerIC;
    }

    // constructor for answer

    public String getEnquiryID() {
        return enquiryID;
    }

    public void setEnquiryID(String enquiryID) {
        this.enquiryID = enquiryID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplicantNRIC() {
        return applicantNRIC;
    }

    public void setApplicantNRIC(String applicantNRIC) {
        this.applicantNRIC = applicantNRIC;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOfficerNRIC() {
        return officerNRIC;
    }

    public void setOfficerNRIC(String officerNRIC) {
        this.officerNRIC = officerNRIC;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getManagerIC(){
        return managerIC;
    }

    public void setManagerIC(){
        this.managerIC =  managerIC;
    }

    @Override
    public String toString() {
        return String.join(",",
            enquiryID,
            projectName,
            applicantNRIC,
            question,
            answer,
            officerNRIC,
            managerIC
        );
    }

}
