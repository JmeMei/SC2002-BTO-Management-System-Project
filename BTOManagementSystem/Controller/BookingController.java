package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.ApplicantDAO;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.DAO.ReceiptsDAO;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.View.HDBOfficerAssignedProjectView;
/**
 * Controller class responsible for handling the booking of flats for applicants
 * who have been successfully approved for a BTO project.
 */
public class BookingController {
    private ApplicationProjectStatusDAO applicantProjectStatusDAO;
    private ProjectListDAO projectListDAO;
    private ApplicantDAO applicantDAO;

    /**
     * Constructs a new {@code BookingController} and initializes the required DAOs.
     */
    public BookingController() {
        this.applicantProjectStatusDAO = new ApplicationProjectStatusDAO();
        this.projectListDAO = new ProjectListDAO();
        this.applicantDAO = new ApplicantDAO();
    }

    /**
     * Processes the flat booking for a specific applicant under a specific project.
     * <p>The method performs the following steps:</p>
     * <ul>
     *     <li>Validates if the applicant has an existing application for the project.</li>
     *     <li>Checks if the application status is {@code SUCCESSFUL}.</li>
     *     <li>Displays application details and prompts the officer for booking confirmation.</li>
     *     <li>Checks flat availability based on flat type and project data.</li>
     *     <li>If confirmed and available, updates the application status and reduces the unit count.</li>
     * </ul>
     *
     *
     * @param projectView   the view used by the HDB officer to interact with booking options
     * @param applicantNRIC the NRIC of the applicant booking the flat
     * @param projectName   the name of the project the applicant is booking under
     * @return {@code true} if booking was successful, {@code false} otherwise
     */
    public boolean bookFlatForApplicant(HDBOfficerAssignedProjectView projectView, String applicantNRIC, String projectName) {

        // Check if application exists
        ApplicantProjectStatus status = applicantProjectStatusDAO.getAnApplication(applicantNRIC,projectName);
        if (status == null) {
            projectView.ApplicationNotFoundMessage();
            return false;
        }

        // Check if application status is SUCCESSFUL
        if (!status.getApplicationStatus().equals(ApplicationStatus.SUCCESSFUL)) {
            projectView.BookingNotAllowedMessage(status.getApplicationStatus().name());
            return false;
        }

        // Display Summary Before Booking
        projectView.DisplayApplicationStatus(status);

        // Officer to confirm the booking
        boolean confirm = projectView.PromptBookingConfirmation();

        if (confirm) {
            // check flat still available
            Project currproject = projectListDAO.getProjectFromStringName(projectName);
            if(currproject.getType1().equals(status.getFlatType().getDisplayName()) && currproject.getType1_numofunits() == 0){
                projectView.NoUnitsAvailable();
                return false;
            }else if(currproject.getType2().equals(status.getFlatType().getDisplayName()) && currproject.getType2_numofunits() == 0){
                projectView.NoUnitsAvailable();
                return false;
            }

            // update application status
            boolean success = applicantProjectStatusDAO.updateApplicationStatus(applicantNRIC);
            if (success) {
                // Update Status List in DAO
                applicantProjectStatusDAO.reloadFromFile();

                // decrease flat unit count in project
                projectListDAO.decreaseFlatUnits(currproject, status.getFlatType());

                // Set Project Details in Applicant Profile
                //applicantDAO.UpdateApplicantProfile(applicantNRIC,status.getProjectName(),status.getApplicationStatus(),status.getFlatType());
            } else {
                return false;
            }
        }
        return true;
    }
}
