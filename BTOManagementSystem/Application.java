package BTOManagementSystem;

import BTOManagementSystem.Applicant.Applicant;

public class Application {
    private Applicant applicant;
    private Project project;
    private String flatType;
    private ApplicationStatus status;
    private boolean withdrawn;

    public Application(Applicant applicant, Project project, String flatType) {
        this.applicant = applicant;
        this.project = project;
        this.flatType = flatType;
        this.status = ApplicationStatus.PENDING;
        this.withdrawn = false;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public Project getProject() {
        return project;
    }

    public String getFlatType() {
        return flatType;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void withdraw() {
        this.withdrawn = true;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }
}
