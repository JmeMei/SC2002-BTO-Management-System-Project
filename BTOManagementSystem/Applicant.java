package BTOManagementSystem;

import java.util.*;

import BTOManagementSystem.Model.User;

public class Applicant extends User {
    private Application application;
    private List<Enquiry> enquiries;

    public Applicant(String nric, int age, String maritalStatus) {
        super(nric, age, maritalStatus);
        enquiries = new ArrayList<>();
    }

    //Controller
    /*
     *     
     *  public boolean canApply(Project project) {
        if (application != null && !application.isWithdrawn()) return false;
        if (maritalStatus.equals("Single") && age >= 35 && project.hasFlatType("2-Room")) return true;
        if (maritalStatus.equals("Married") && age >= 21) return true;
        return false;
    }
     * 
     */


    public void apply(Project project, String flatType) {
        application = new Application(this, project, flatType);
    }

    public void withdrawApplication() {
        if (application != null) application.withdraw();
    }

    public void submitEnquiry(String content) {
        enquiries.add(new Enquiry(this, content));
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    public Application getApplication() {
        return application;
    }
}
