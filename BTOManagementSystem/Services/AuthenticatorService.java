package BTOManagementSystem.Services;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.DAO.*;
import BTOManagementSystem.Model.User;


public class AuthenticatorService {

    public boolean authenticate(String username, String password){

        ApplicantDAO ApplicantDAO = new ApplicantDAO();
        HDBOfficerDAO OfficerDAO = new HDBOfficerDAO();
        HDBManagerDAO ManagerDAO = new HDBManagerDAO();

        // True if password is correct
        if (ApplicantDAO.NRIC_exist(username)){
            return ApplicantDAO.getPasswordOfUser(username).compareTo(password) == 0;
        }
        else if (OfficerDAO.NRIC_exist(username)){
            return OfficerDAO.getPasswordOfUser(username).compareTo(password) == 0;
        }
        else if (ManagerDAO.NRIC_exist(username)){
            return ManagerDAO.getPasswordOfUser(username).compareTo(password) == 0;
        }

        return false;
    }

}
