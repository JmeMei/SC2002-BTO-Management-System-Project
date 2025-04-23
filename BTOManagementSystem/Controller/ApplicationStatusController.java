package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.View.HDBManagerApproveBTOApplicationView;
import BTOManagementSystem.View.HDBManagerView;

public class ApplicationStatusController {


    public void ViewApplication(HDBManagerView managerView, HDBManagerApproveBTOApplicationView approveView){

        ApplicationProjectStatusDAO dao = new ApplicationProjectStatusDAO();

        String[] filter_Data = approveView.PromptFilters();

        approveView.ViewBTOApplications(dao.LoadApplications(filter_Data));

        managerView.showMenu();

    }

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
