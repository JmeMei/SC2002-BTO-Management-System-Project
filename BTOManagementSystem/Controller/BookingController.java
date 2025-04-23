package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.DAO.ReceiptsDAO;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.View.HDBOfficerAssignedProjectView;

public class BookingController {
    private ApplicationProjectStatusDAO applicantProjectStatusDAO;
    private ProjectListDAO projectListDAO;

    public BookingController() {
        this.applicantProjectStatusDAO = new ApplicationProjectStatusDAO();
        this.projectListDAO = new ProjectListDAO();
    }

    public boolean bookFlatForApplicant(HDBOfficerAssignedProjectView projectView, String applicantNRIC, String projectName) {

        // Check if application exists
        ApplicantProjectStatus status = applicantProjectStatusDAO.getApplication(applicantNRIC);
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
            } else {
                return false;
            }
        }
        return true;
    }
}
