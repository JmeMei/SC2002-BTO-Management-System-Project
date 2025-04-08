package BTOManagementSystem;
import java.util.*;

import BTOManagementSystem.Model.User;

public class HDBManager extends User {
    private List<Project> createdProjects;

    public HDBManager(String nric, int age, String maritalStatus) {
        super(nric, age, maritalStatus);
        createdProjects = new ArrayList<>();
    }

    public Project createProject(String name, String location) {
        Project p = new Project(name, location, this);
        createdProjects.add(p);
        return p;
    }

    public void approveApplication(Application app) {
        app.setStatus(ApplicationStatus.SUCCESSFUL);
    }

    public void rejectApplication(Application app) {
        app.setStatus(ApplicationStatus.UNSUCCESSFUL);
    }

    public void replyToEnquiry(Enquiry e, String reply) {
        e.setReply(reply);
    }

    public List<Project> getCreatedProjects() {
        return createdProjects;
    }
}
