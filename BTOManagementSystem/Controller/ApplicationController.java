package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.*;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.View.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  Controller class responsible for handling Applicant applications
 */
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

    /**
     * Constructs a new {@code ApplicationController} and initializes the required DAOs.
     */
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

    /**
     * returning users to their respective main menu views
     * based on their role (Applicant, HDB Officer, or HDB Manager).
     * @param user the user whose role is being checked
     */
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


    /**
     * Determines the list of flat types that a given user is eligible for based on
     * their age and marital status.
     * @param user the user whose eligibility for flat types is being checked
     * @return a list of {@link FlatType} values the user is eligible for
     */
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

    /**
     * Returns a list of flat types available to the specified user for the given project,
     * based on the user's eligibility and the availability of flat units in the project.
     * <p>
     * Eligibility Rules:
     * <ul>
     *   <li>Single applicants aged 35 and above are eligible only for 2-Room flats.</li>
     *   <li>Married applicants aged 21 and above are eligible for both 2-Room and 3-Room flats.</li>
     * </ul>
     * <p>
     * A flat type is added to the available list only if the user is eligible for it
     * and the project has available units of that flat type.
     *
     * @param project the BTO project to check availability from
     * @param user the user applying for the flat
     * @return a list of available {@link FlatType} options the user can apply for
     */
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

    /**
     * Get the eligible projects for the user based on the eligible flat types.
     * Load the available Projects (either 2-Room or 3-Room)
     * @param user the user to check eligble types for
     * @return
     */
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

    /***
     * Displays all the available projects to be seen by the applicant
     * based on applicant's eligible FlatTypes and Eligible Projects
     * @param applicantView the applicantView to return to the View
     * @param user to check eligible flat types and eligible projects
     */
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

    /***
     * Handles the process for a user to apply for a housing project.
     * <p>
     * This method guides the applicant through selecting an eligible project,
     * validating their eligibility based on project and flat type availability,
     * and submitting an application if all conditions are met. It also handles
     * special conditions such as if the user is an HDB officer of the project
     * or has a pending officer registration request for the same project.
     * </p>
     *
     * <p>Key Steps:</p>
     * <ul>
     *   <li>Prompt user to choose a project</li>
     *   <li>Check if the selected project is valid and user is not a related HDB officer</li>
     *   <li>Display available flat types for that project</li>
     *   <li>Prompt user to select a flat type</li>
     *   <li>Submit application and notify based on application status</li>
     * </ul>
     * @param applicantView used to return to the applicant view
     * @param applyView used to prompt the user to prompt and show applyView Messages
     * @param user user to verify eligible projects
     */
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
                if (officerNRIC != null) {
                    if (officerNRIC.contains(user.getNric())) {
                        applyView.CannotApplyIfHDBOfficerMessage();
                        returntoMenu(user);
                    }
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

    /**
     * Displays all housing project applications submitted by the current user.
     * <p>
     * This method retrieves the list of applications associated with the user's NRIC
     * and displays their statuses using the provided view. If no applications are found,
     * a corresponding message is shown. Afterwards, the user is returned to the main menu.
     * </p>
     *
     * @param applicantView used to return to the applicantView
     * @param manageView used to display the application status
     * @param user the user whose applications are being retrieved and displayed
     */
    public void viewMyApplications(ApplicantView applicantView, ApplicantManageApplicationView manageView, User user) {
        List<ApplicantProjectStatus> statusList = applicantProjectStatusDAO.getApplicationsByNRIC(user.getNric());
        if (!statusList.isEmpty()) {
            manageView.DisplayApplicationStatus(statusList);
        } else {
            manageView.ApplicationNotFoundMessage();
        }

        returntoMenu(user);
    }

    /**
     * Allows the user to submit a withdrawal request for a pending project application.
     * <p>
     * This method checks if the user has any pending applications. If such an application
     * exists, it is displayed to the user for confirmation. Upon confirmation, a withdrawal
     * request is created. Appropriate success or error messages are shown based on whether
     * a request has already been made or not. After the process, the user is returned to the main menu.
     * </p>
     *
     * @param applicantView the main view interface used for general applicant navigation
     * @param manageView the view responsible for managing and displaying application statuses
     * @param user the user requesting to withdraw their pending housing application
     */
    public void withdrawApplication(ApplicantView applicantView, ApplicantManageApplicationView manageView, User user) {
        // Get current Application that is PENDING
        List<ApplicantProjectStatus> statusList = applicantProjectStatusDAO.getApplicationsByNRIC(user.getNric());

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

    /**
     * Retrieves and displays the application statuses for a specific project.
     * <p>
     * This method filters all applications to find those that match the specified project name
     * and displays them to the assigned HDB officer. If no applications are found, an appropriate
     * message is shown. After displaying the information (or message), the officer is returned
     * to the project menu.
     * </p>
     *
     * @param assignedProjectView the view used to display application statuses and navigate officer options
     * @param projectName the name of the project whose applications are to be retrieved
     * @param officer the HDB officer assigned to the project, used for view navigation
     */
    public void getApplicantProjectStatus(HDBOfficerAssignedProjectView assignedProjectView,String projectName,HDBOfficer officer) {
        List<ApplicantProjectStatus> allstatusList = applicantProjectStatusDAO.getApplications();
        List<ApplicantProjectStatus> projectstatusList = new ArrayList<>();
        if (!allstatusList.isEmpty()) {
            for (ApplicantProjectStatus s : allstatusList) {
                if (s.getProjectName().equalsIgnoreCase(projectName)) {
                   projectstatusList.add(s);
                }
            }
        } else {
            assignedProjectView.ApplicationNotFoundMessage();
        }

        if(!projectstatusList.isEmpty()){
            assignedProjectView.DisplayAllApplicationStatus(projectstatusList);
        }else{
            assignedProjectView.ApplicationNotFoundMessage();
        }
        assignedProjectView.showMenu(officer,projectName);
    }

}