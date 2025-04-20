package BTOManagementSystem.Services;
import BTOManagementSystem.App.*;
import BTOManagementSystem.Model.DAO.ApplicantDAO;
import BTOManagementSystem.Model.DAO.HDBManagerDAO;
import BTOManagementSystem.Model.DAO.HDBOfficerDAO;
import BTOManagementSystem.Model.DAO.UserDAO;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.Model.User;


public class SessionManagerService {

    public void InstantiateSession(String NRIC){

        ApplicantDAO ApplicantDAO = new ApplicantDAO();
        HDBOfficerDAO OfficerDAO = new HDBOfficerDAO();
        HDBManagerDAO ManagerDAO = new HDBManagerDAO();

        User newUser = null;

        if (ApplicantDAO.NRIC_exist(NRIC)){
           newUser = ApplicantDAO.getUser(NRIC);
        }
        else if (OfficerDAO.NRIC_exist(NRIC)){
            newUser = OfficerDAO.getUser(NRIC);
        }
        else if (ManagerDAO.NRIC_exist(NRIC)){
            newUser = ManagerDAO.getUser(NRIC);
        }

        App.userSession = newUser;


    }

}
