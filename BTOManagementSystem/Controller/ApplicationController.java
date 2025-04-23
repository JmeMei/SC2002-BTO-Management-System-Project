package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.DAO.WithdrawalRequestDAO;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Services.ApplicantActionHandler;
import BTOManagementSystem.View.*;

import java.util.ArrayList;
import java.util.List;


public class ApplicationController {
    //ApplicationController “has a” projectListDAO
    //ApplicationController “has a” ProjectAvailableView
    private ProjectListDAO projectListDAO;
    private ApplicationProjectStatusDAO applicantProjectStatusDAO;
    private WithdrawalRequestDAO withdrawalRequestDAO;
    private ApplicantViewProjectsView viewProjectsView;
    private HDBOfficerAssignedProjectView assignedProjectView;

    public ApplicationController() {
        this.applicantProjectStatusDAO = new ApplicationProjectStatusDAO();
        this.projectListDAO = new ProjectListDAO();
        this.withdrawalRequestDAO = new WithdrawalRequestDAO();
        this.viewProjectsView = new ApplicantViewProjectsView();
        this.assignedProjectView = new HDBOfficerAssignedProjectView();
    }

    public List<FlatType> getEligibleFlatTypes(User user) {
        List<FlatType> eligible = new ArrayList<>();

        if ((user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single")) ||
                (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21)) {
            eligible.add(FlatType.TWO_ROOM);
        }

        if (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21) {
            eligible.add(FlatType.THREE_ROOM);
        }

        return eligible;
    }

    public List<FlatType> getAvailableFlatTypes(Project project, User user) {

        boolean eligibleTwoRoom = (user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single"))
                || (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21);
        boolean eligibleThreeRoom = user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21;

        List<FlatType> availableFlatTypes = new ArrayList<>();
        FlatType flatType1 = FlatType.fromString(project.getType1());
        FlatType flatType2 = FlatType.fromString(project.getType2());

        if (eligibleTwoRoom) {
            if (flatType1 == FlatType.TWO_ROOM && project.getType1_numofunits() > 0) {
                availableFlatTypes.add(FlatType.TWO_ROOM);
            }
            if (flatType2 == FlatType.TWO_ROOM && project.getType2_numofunits() > 0) {
                availableFlatTypes.add(FlatType.TWO_ROOM);
            }
        }

        if (eligibleThreeRoom) {
            if (flatType1 == FlatType.THREE_ROOM && project.getType1_numofunits() > 0) {
                availableFlatTypes.add(FlatType.THREE_ROOM);
            }
            if (flatType2 == FlatType.THREE_ROOM && project.getType2_numofunits() > 0) {
                availableFlatTypes.add(FlatType.THREE_ROOM);
            }
        }

        return availableFlatTypes;
    }

    public List<Project> getEligibleProjects(User user) {
        List<Project> eligibleProjects = new ArrayList<>();
        List<FlatType> eligibleFlatTypes = getEligibleFlatTypes(user);

        if (eligibleFlatTypes.contains(FlatType.TWO_ROOM)) {
            eligibleProjects.addAll(projectListDAO.loadAvailableTwoRooms(user));
        }

        if (eligibleFlatTypes.contains(FlatType.THREE_ROOM)) {
            eligibleProjects.addAll(projectListDAO.loadAvailableThreeRooms(user));
        }

        return eligibleProjects;
    }

    public void displayAvailableProjects(ApplicantView applicantView, User user) {

        List<FlatType> eligibleFlatTypes = getEligibleFlatTypes(user);
        List<Project> eligibleProjects = getEligibleProjects(user);

        if (eligibleFlatTypes.isEmpty() || eligibleProjects.isEmpty()) {
            viewProjectsView.NotEligibleForProjectsMessage();
        } else {
            viewProjectsView.DisplayProjects(eligibleProjects, eligibleFlatTypes);
        }

        applicantView.showMenu(user);
    }

    public void applyProject(ApplicantView applicantView, ApplicantViewApplyProjectView applyView, User user) {

        // Prompt user view to return name of project to apply for
        String projectName = applyView.promptApplicantChooseProject().trim().toLowerCase();

        //get project
        Project project = null;
        List<Project> projects = this.getEligibleProjects(user);
        for (Project p : projects) {
            if (p.getName().trim().toLowerCase().equalsIgnoreCase(projectName)) {
                project = p;
                break;
            }
        }

        if (project != null) {
            // Show available flat types for this project
            List<FlatType> availableTypes = this.getAvailableFlatTypes(project, user);

            // Check the available flat types for project based on user eligibility
            if (!availableTypes.isEmpty()) {
                String userinput = applyView.PromptAvailableFlatTypesforProject(project, availableTypes);

                if(userinput.equals("Cancel")){
                    applyView.CancelApplyProjectMessage();
                    applicantView.showMenu(user);
                }

                FlatType inputType = FlatType.fromString(userinput);

                // Apply for Project
                boolean success = applicantProjectStatusDAO.applyForProject(user, projectName, inputType);
                if (success) {
                    applicantProjectStatusDAO.reloadFromFile();
                    applyView.ApplySuccessMessage();
                } else {
                    // Already applied
                    ApplicationStatus status = applicantProjectStatusDAO.getApplicationStatus(user.getNric(), projectName, inputType);
                    if (status != null) {
                        applyView.ApplicationInProcessMessage(status.name());
                    }else{
                        applyView.AppliedBeforeMessage();
                    }
                }
            } else {
                // All flats occupied
                applyView.NoAvailableFlatsMessage();
            }
        } else {
            // Project Does not Exist
            applyView.ProjectNotFoundMessage();
        }

        applicantView.showMenu(user);
    }

    public void viewMyApplication(ApplicantView applicantView, ApplicantManageApplicationView manageView, User user) {
        ApplicantProjectStatus status = applicantProjectStatusDAO.getApplication(user.getNric());
        if (status != null) {
            manageView.DisplayApplicationStatus(status);
        } else {
            manageView.ApplicationNotFoundMessage();
        }

        applicantView.showMenu(user);
    }

    public void withdrawApplication(ApplicantView applicantView, ApplicantManageApplicationView manageView, User user) {
        // Get Project Name from Existing Application
        String projectName = applicantProjectStatusDAO.getProjectNameforApplicant(user);

        if (projectName != null) {
            boolean success = withdrawalRequestDAO.CreateWithdrawalRequest(user, projectName);
            if (success) {
                // Requested for Withdrawal Success
                manageView.ReqWithdrawalSuccessMessage();
            } else {
                // Already Requested
                manageView.RequestedBeforeMessage();
            }
        } else {
            // Application does not exist
            manageView.ApplicationNotFoundMessage();
        }

        applicantView.showMenu(user);
    }

    public Project getProject(String projectName, User user) {
        List<Project> projectAvailable = getEligibleProjects(user);

        for (Project p : projectAvailable) {
            if (p.getName().equalsIgnoreCase(projectName)) {
                return p;
            }
        }
        return null;
    }

    public ApplicantProjectStatus getApplicantProjectStatus(String applicantNRIC, String projectName) {
        ApplicantProjectStatus status = applicantProjectStatusDAO.getApplication(applicantNRIC);
        if (status != null) {
            if (status.getProjectName().equalsIgnoreCase(projectName)) {
                return status;
            } else {
                assignedProjectView.AssignedApplicantNotFoundMessage();
            }
        } else {
            assignedProjectView.ApplicationNotFoundMessage();
        }
        return null;
    }

}