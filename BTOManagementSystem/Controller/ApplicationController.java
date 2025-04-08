package BTOManagementSystem.Controller;
import java.util.*;

import BTOManagementSystem.Applicant;
import BTOManagementSystem.ApplicationStatus;
import BTOManagementSystem.Project;

//Application submission, withdrawal, approval

public class ApplicationController {
    public boolean submitApplication(Applicant applicant, Project project, String flatType) {
        if (!applicant.canApply(project)) return false;

        applicant.apply(project, flatType);
        return true;
    }

    public void withdrawApplication(Applicant applicant) {
        if (applicant.getApplication() != null) {
            applicant.withdrawApplication();
        }
    }

    public void approve(Application app) {
        app.setStatus(ApplicationStatus.SUCCESSFUL);
    }

    public void reject(Application app) {
        app.setStatus(ApplicationStatus.UNSUCCESSFUL);
    }

    public void book(Application app) {
        if (app.getStatus() == ApplicationStatus.SUCCESSFUL) {
            app.setStatus(ApplicationStatus.BOOKED);
        }
    }
}
