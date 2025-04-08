package BTOManagementSystem;

import com.apple.eawt.Application;

public class HDBOfficer {
    private Project handledProject;

    public HDBOfficer(String nric, int age, String maritalStatus) {
        super(nric, age, maritalStatus);
    }

    public void registerToHandle(Project project) {
        this.handledProject = project;
    }

    public void bookFlat(Applicant applicant) {
        if (applicant.getApplication().getStatus() == ApplicationStatus.SUCCESSFUL) {
            applicant.getApplication().setStatus(ApplicationStatus.BOOKED);
            handledProject.reduceFlatCount(applicant.getApplication().getFlatType());
        }
    }

    public Receipt generateReceipt(Applicant applicant) {
        Application app = applicant.getApplication();
        return new Receipt(applicant.getNric(), applicant.getAge(), applicant.getMaritalStatus(), app.getFlatType(), app.getProject().getName());
    }

    public void replyToEnquiry(Enquiry enquiry, String reply) {
        enquiry.setReply(reply);
    }
}
