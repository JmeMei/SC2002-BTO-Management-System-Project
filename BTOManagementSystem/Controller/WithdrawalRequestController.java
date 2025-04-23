package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.WithdrawalRequestDAO;
import BTOManagementSystem.View.HDBManagerView;
import BTOManagementSystem.View.HDBManagerWithdrawalRequestView;

import java.util.ArrayList;

public class WithdrawalRequestController {

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
