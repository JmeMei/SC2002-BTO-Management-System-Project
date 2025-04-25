package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.WithdrawalRequestDAO;
import BTOManagementSystem.View.HDBManagerView;
import BTOManagementSystem.View.HDBManagerWithdrawalRequestView;

import java.util.ArrayList;
/**
 * Controller class responsible for handling applicant withdrawal requests.
 * <p>
 * This class allows HDB managers to view, process, and approve or reject withdrawal
 * requests made by applicants for their project applications.
 */

public class WithdrawalRequestController {

    /**
     * Handles the processing of withdrawal requests by the HDB Manager.
     * <p>
     * The method retrieves all unhandled withdrawal requests and displays them.
     * Then, it prompts the manager to select a request to process. If successful,
     * the applicant’s application status is marked as unsuccessful.
     *
     * @param menuView the view interface for navigating the manager menu
     * @param RequestView the view interface for interacting with withdrawal requests
     */
    public void HandleWithdrawalRequest(HDBManagerView menuView, HDBManagerWithdrawalRequestView RequestView){

        WithdrawalRequestDAO dao = new WithdrawalRequestDAO();
        ApplicationProjectStatusDAO apsDAO = new ApplicationProjectStatusDAO();

        ArrayList<WithdrawalRequest> requests = new ArrayList<>();

        requests = dao.getUnhandledWithdrawalRequests();

        RequestView.ShowRequests(requests,dao.getHeaders());

        String[] data = RequestView.PromptForWithdrawal();

        if (dao.UpdateWithdrawalRequest(data[0],data[1]) == 1){

            apsDAO.mark_unsuccessful(data[0],data[1]);
            RequestView.SuccessMessage();

        }else{
            RequestView.FailMessage();
        }

        menuView.showMenu();

    }

}
