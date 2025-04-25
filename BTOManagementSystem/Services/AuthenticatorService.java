package BTOManagementSystem.Services;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.DAO.*;
import BTOManagementSystem.Model.User;

/**
 * Provides authentication services for users attempting to log in to the BTO Management System.
 * <p>
 * This class checks whether a given NRIC (used as username) exists in the system and verifies
 * the corresponding password against the records in the respective DAO classes.
 */
public class AuthenticatorService {

    /**
     * Authenticates a user based on their NRIC and password.
     * <p>
     * The method checks each user category (Applicant, Officer, Manager) and validates the password.
     *
     * @param username the NRIC of the user attempting to log in
     * @param password the password provided by the user
     * @return {@code true} if authentication is successful, {@code false} otherwise
     */
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
