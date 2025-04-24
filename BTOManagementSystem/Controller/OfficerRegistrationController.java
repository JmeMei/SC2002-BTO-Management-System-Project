package BTOManagementSystem.Controller;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.OfficerRegistrationRequestDAO;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.View.*;

import java.util.List;

/**
 * Controller class responsible for handling officer registration requests to projects.
 * <p>
 * This includes creating new registration requests, viewing the request status (officer),
 * and managing/approving these requests (manager).
 * </p>
 */
public class OfficerRegistrationController {

    OfficerRegistrationRequestDAO dao = new OfficerRegistrationRequestDAO();
    ProjectListDAO projectListDAO = new ProjectListDAO();
    ApplicationProjectStatusDAO statusDAO = new ApplicationProjectStatusDAO();

    /**
     * Allows an HDB officer to create a registration request to manage a project.
     * <p>Performs checks to ensure:</p>
     * <ul>
     *     <li>No existing request already exists</li>
     *     <li>The project name is valid</li>
     *     <li>The officer has not already applied for the project</li>
     * </ul>
     * If all checks pass, the request is saved and a success message is displayed.
     *
     * @param officerView the officer's main view to return to the main menu
     * @param regView     the view used to prompt and display registration messages
     * @param officer     the currently logged-in HDB officer
     */
    public void CreateARequest(HDBOfficerView officerView, HDBOfficerProjectRegistrationView regView, HDBOfficer officer) {

        // Check that request does not already exist
        OfficerRegistrationRequest req = dao.GetOfficerRegRequestByNRIC(officer.getNric());
        if (req != null) {
            // Reg already exists
            regView.RegistationAlreadyExistsMessage();
            officerView.showOfficerMenu(officer);
        }

        // User input project name
        String projectName = regView.promptOfficerChooseProject();

        List<String> projectNamesList = projectListDAO.getProjectNames();

        String project = null;

        for (String pNameList : projectNamesList) {
            if (pNameList.trim().equalsIgnoreCase(projectName.trim())) {
                project = pNameList;
            }
        }
        // Project does not exist
        if (project == null) {
            regView.ProjectNotFoundMessage();
            officerView.showOfficerMenu(officer);
        }

        // Check that officer has not already applied for this project
        ApplicantProjectStatus appProjStatus = statusDAO.getAnApplication(officer.getNric(), project);
        if (appProjStatus != null) {
            regView.AppliedForProjectBeforeMessage();
            officerView.showOfficerMenu(officer);
        }

        // Successful
        OfficerRegistrationRequest registerRequest = new OfficerRegistrationRequest(officer.getName(), officer.getNric(), project, "Pending");

        // Call To DB
        dao.CreateOfficerRegistrationRequest(registerRequest);

        // Reload RegList
        dao.ReloadFromFile();

        regView.RegistationSuccessMessage();

        officerView.showOfficerMenu(officer);
    }

    /**
     * Allows an HDB officer to view their current project registration request status.
     *
     * @param officerView the view interface for the officer menu
     * @param regView     the view interface for officer project registration
     * @param officer     the currently logged-in HDB officer
     */

    public void ViewOfficerRequest(HDBOfficerView officerView, HDBOfficerProjectRegistrationView regView, HDBOfficer officer) {
        OfficerRegistrationRequest req = dao.GetOfficerRegRequestByNRIC(officer.getNric());
        if (req == null) {
            // Reg does not exist
            regView.RegistationNotFoundMessage();
            officerView.showOfficerMenu(officer);
        }

        regView.displayRegistrationStatus(req);
        officerView.showOfficerMenu(officer);
    }

    /**
     * Allows an HDB manager to view all officer registration requests filtered by status.
     *
     * @param managerView        the view interface for the manager menu
     * @param approveOfficerView the view interface for managing officer approvals
     */
    public void ViewApproveRequests(HDBManagerView managerView, HDBManagerApproveOfficerView approveOfficerView) {

        int filter = approveOfficerView.Prompt();

        approveOfficerView.DisplayRequests(dao.LoadOfficerRegistrationRequests(filter), dao.getHeaders());
        managerView.showMenu();

    }

    /**
     * Allows an HDB manager to approve a specific officer's request to be assigned to a project.
     * <p>
     * The manager must be assigned to the project to approve the request. Also checks
     * if the project has capacity for additional officers.
     *
     * @param managerView        the view interface for the manager menu
     * @param approveOfficerView the view interface for managing officer approvals
     */
    public void ApproveARequest(HDBManagerView managerView, HDBManagerApproveOfficerView approveOfficerView) {

        ProjectListDAO projectListDAO = new ProjectListDAO();

        String[] Data = approveOfficerView.PromptNameOfOfficerToApprove(); // gets corresponding ProjName as well

        boolean recordExists = dao.RecordExists(Data[0], Data[1]);
        if (recordExists) {

            if (projectListDAO.IsManaging(Data[1], App.userSession.getName())) {

                int AddSuccess = projectListDAO.AddOfficerToProject(Data[0], Data[1]);

                if (AddSuccess == -1) {
                    approveOfficerView.SlotFullErrorMessage();
                } else if (AddSuccess == 1) {

                    dao.UpdateApprovalStatus(Data[0], Data[1]);
                    approveOfficerView.SuccessMessage();
                }

            } else {
                approveOfficerView.NotManagerErrorMessage();
            }
        } else {
            approveOfficerView.RequestDoesNotExistMessage();
        }

        managerView.showMenu();
    }

}
