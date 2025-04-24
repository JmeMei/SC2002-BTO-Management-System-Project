package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.*;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBOfficer;
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
    private OfficerRegistrationRequestDAO requestDAO;
    private HDBOfficerDAO hdbOfficerDAO;
    private ApplicantViewProjectsView viewProjectsView;
    private HDBOfficerAssignedProjectView assignedProjectView;
    private ApplicantView applicantView;
    private HDBOfficerView officerView;
    private HDBManagerView managerView;

    public ApplicationController() {
        this.applicantProjectStatusDAO = new ApplicationProjectStatusDAO();
        this.projectListDAO = new ProjectListDAO();
        this.withdrawalRequestDAO = new WithdrawalRequestDAO();
        this.requestDAO = new OfficerRegistrationRequestDAO();
        this.hdbOfficerDAO = new HDBOfficerDAO();
        this.viewProjectsView = new ApplicantViewProjectsView();
        this.assignedProjectView = new HDBOfficerAssignedProjectView();
        this.applicantView = new ApplicantView();
        this.officerView = new HDBOfficerView();
        this.managerView = new HDBManagerView();
    }

    public void returntoMenu(User user) {
        switch (user.getRole().toLowerCase()) {
            case "applicant":
                applicantView.showApplicantMenu(user);
                break;
            case "officer":
                officerView.showOfficerMenu((HDBOfficer) user);
                break;
            case "manager":
                managerView.showMenu();
                break;
        }
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
            eligibleProjects = projectListDAO.loadAvailableTwoRooms(user);
        }
        else if (eligibleFlatTypes.contains(FlatType.THREE_ROOM)) {
            eligibleProjects = projectListDAO.loadAvailableThreeRooms(user);
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

       returntoMenu(user);
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
            // Check if applicant is the HDBOfficer of the project

            for (String officerName : project.get_officers()){
                String officerNRIC = hdbOfficerDAO.officerNametoNRIC(officerName);
                if(officerNRIC.contains(user.getNric())){
                    applyView.CannotApplyIfHDBOfficerMessage();
                    returntoMenu(user);
                }
            }

            // Check if applicant is pending approval to handle this project
            OfficerRegistrationRequest req = requestDAO.GetOfficerRegRequest(user.getNric(), project.getName());
            if(req != null){
                applyView.CannotApplyIfPendingHDBOfficerMessage();
                returntoMenu(user);
            }

            // Show available flat types for this project
            List<FlatType> availableTypes = this.getAvailableFlatTypes(project, user);

            // Check the available flat types for project based on user eligibility
            if (!availableTypes.isEmpty()) {

                applyView.DisplayAvailableFlatTypesforProject(project, availableTypes);

                String normInput = null;
                FlatType inputType = null;

                do {
                    String roomTypeInput = applyView.PromptUserInputFlatType();

                    // Normalize common variants for flat type
                    if (roomTypeInput.equals("2room") || roomTypeInput.equals("2-room")) {
                        normInput = "2-Room";
                    } else if (roomTypeInput.equals("3room") || roomTypeInput.equals("3-room")) {
                        normInput = "3-Room";
                    } else if (roomTypeInput.equals("c")) {
                        normInput = "Cancel";
                        break;
                    } else {
                       applyView.UserInputInvalidMessage();
                        continue;
                    }

                    // Validate against available flat types
                    boolean isValidFlatType = false;
                    for (FlatType ft : availableTypes) {
                        if(ft.getDisplayName().equalsIgnoreCase(normInput)){
                            isValidFlatType = true;
                            inputType = FlatType.fromString(normInput);
                        }
                    }

                    if (!isValidFlatType) {
                       applyView.UserInputFlatTypeInvalidMessage();
                        normInput = null; // reset to loop again
                    }

                } while (normInput == null);

                if ("Cancel".equalsIgnoreCase(normInput)) {
                    applyView.CancelApplyProjectMessage();
                    returntoMenu(user);
                }

                // Apply for Project
                boolean success = applicantProjectStatusDAO.applyForProject(user, project.getName(), inputType);
                if (success) {
                    applicantProjectStatusDAO.reloadFromFile();
                    applyView.ApplySuccessMessage();
                } else {
                    // Already applied to this project
                    ApplicationStatus status = applicantProjectStatusDAO.getApplicationStatus(user.getNric(), project.getName(), inputType);
                    if(status != null) {
                        // Current project Status
                        applyView.ApplicationInProcessMessage(status.name());
                    }else{
                        // Past project in Process
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

        returntoMenu(user);
    }

    public void viewMyApplications(ApplicantView applicantView, ApplicantManageApplicationView manageView, User user) {
        List<ApplicantProjectStatus> statusList = applicantProjectStatusDAO.getApplications(user.getNric());
        if (!statusList.isEmpty()) {
            manageView.DisplayApplicationStatus(statusList);
        } else {
            manageView.ApplicationNotFoundMessage();
        }

        returntoMenu(user);
    }

    public void withdrawApplication(ApplicantView applicantView, ApplicantManageApplicationView manageView, User user) {
        // Get current Application that is PENDING
        List<ApplicantProjectStatus> statusList = applicantProjectStatusDAO.getApplications(user.getNric());

        if(statusList.isEmpty()) {
            manageView.ApplicationsToWithdrawNotFoundMessage();
            returntoMenu(user);
        }

        ApplicantProjectStatus statusToWithdraw = null;

        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(user.getNric()) && status.getApplicationStatus().equals(ApplicationStatus.PENDING)) {
                statusToWithdraw = status;
            }
        }
        List<ApplicantProjectStatus> statusListDisplay = new ArrayList<>();
        statusListDisplay.add(statusToWithdraw);

        manageView.DisplayApplicationStatus(statusListDisplay);
        boolean confirm = manageView.PromptWithdrawConfirmation();

        if (!confirm){
            returntoMenu(user);
        }

        boolean success = withdrawalRequestDAO.CreateWithdrawalRequest(user, statusToWithdraw.getProjectName());
        if (success) {
            // Requested for Withdrawal Success
            manageView.ReqWithdrawalSuccessMessage();
        } else {
            // Already Requested
            manageView.RequestedBeforeMessage();
        }

        returntoMenu(user);
    }

//    public Project getProject(String projectName, User user) {
//        List<Project> projectAvailable = getEligibleProjects(user);
//
//        for (Project p : projectAvailable) {
//            if (p.getName().equalsIgnoreCase(projectName)) {
//                return p;
//            }
//        }
//        return null;
//    }

    public void getApplicantProjectStatus(String applicantNRIC, String projectName,User user) {
        List<ApplicantProjectStatus> statusList = applicantProjectStatusDAO.getApplications(applicantNRIC);

        if (!statusList.isEmpty()) {
            for (ApplicantProjectStatus s : statusList) {
                if (s.getProjectName().equalsIgnoreCase(projectName)) {
                    assignedProjectView.DisplayApplicationStatus(s);
                } else {
                    assignedProjectView.AssignedApplicantNotFoundMessage();
                }
            }
        } else {
            assignedProjectView.ApplicationNotFoundMessage();
        }
        returntoMenu(user);
    }

}