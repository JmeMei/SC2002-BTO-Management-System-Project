package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.DAO.OfficerRegistrationRequestDAO;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.View.HDBManagerApproveOfficerView;
import BTOManagementSystem.View.HDBManagerView;

public class OfficerRegistrationController {

    OfficerRegistrationRequestDAO dao = new OfficerRegistrationRequestDAO();

    public void ViewApproveRequests(HDBManagerView managerView, HDBManagerApproveOfficerView approveOfficerView ){

        int filter = approveOfficerView.Prompt();
        approveOfficerView.DisplayRequests(dao.LoadOfficerRegistrationRequests(filter), dao.getHeaders());
        managerView.showMenu();
    }

    public void ApproveARequest(HDBManagerView managerView, HDBManagerApproveOfficerView approveOfficerView ){

        ProjectListDAO projectListDAO = new ProjectListDAO();


        String[] Data = approveOfficerView.PromptNameOfOfficerToApprove(); // gets corresponding ProjName as well


        boolean recordExists = dao.RecordExists(Data[0], Data[1]);
        if (recordExists){

            int AddSuccess = projectListDAO.AddOfficerToProject(Data[0],Data[1]);
            if  (AddSuccess == -1){
                approveOfficerView.SlotFullErrorMessage();
            }
            else if (AddSuccess == 1){

                dao.UpdateApprovalStatus(Data[0], Data[1]);
                approveOfficerView.SuccessMessage();
            }

        }else{
            approveOfficerView.RequestDoesNotExistMessage();
        }


        managerView.showMenu();
    }

}
