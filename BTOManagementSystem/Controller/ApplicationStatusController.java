package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.View.HDBManagerApproveBTOApplicationView;
import BTOManagementSystem.View.HDBManagerView;

public class ApplicationStatusController {

    /**
     * Displays filtered BTO (Build-To-Order) application data to the HDB manager.
     * <p>
     * This method prompts the user for filter criteria, retrieves the matching applications
     * using the DAO, and displays them using the approvedBTOApplication view. After viewing, it returns
     * the user to the main manager menu.
     * </p>
     *
     * @param managerView used to return to the manager Home View
     * @param approveView the view where the BTO applications would be shown
     */
    public void ViewApplication(HDBManagerView managerView, HDBManagerApproveBTOApplicationView approveView){

        ApplicationProjectStatusDAO dao = new ApplicationProjectStatusDAO();

        String[] filter_Data = approveView.PromptFilters();

        approveView.ViewBTOApplications(dao.LoadApplications(filter_Data));

        managerView.showMenu();

    }

    /**
     * Handles the approval process for a BTO application by the HDB manager.
     * <p>
     * This method prompts the manager for input values (such as the applicant's NRIC and the project name),
     * and attempts to mark the corresponding application as successful using the Project Status DAO. It then provides
     * feedback to the user via the view, indicating whether the update was successful or not.
     * Finally, the manager is returned to the main menu.
     * </p>
     *
     * @param managerView  used to return to the manager Home View
     * @param approveView the view used to prompt for application approval input and display status messages
     */
    public void approveApplication(HDBManagerView managerView, HDBManagerApproveBTOApplicationView approveView){

        ApplicationProjectStatusDAO dao = new ApplicationProjectStatusDAO();
        String[] data = approveView.PromptApproveBTOApplicationValue();


        if (dao.mark_successful(data[0],data[1]) == 1){

            approveView.SuccessfulUpdate();

        }else{
            approveView.FailedUpdate();

        }
        managerView.showMenu();
    }

}
