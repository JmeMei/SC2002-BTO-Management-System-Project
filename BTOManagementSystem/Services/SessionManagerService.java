package BTOManagementSystem.Services;
import BTOManagementSystem.App.*;
import BTOManagementSystem.Model.DAO.ApplicantDAO;
import BTOManagementSystem.Model.DAO.HDBManagerDAO;
import BTOManagementSystem.Model.DAO.HDBOfficerDAO;
import BTOManagementSystem.Model.User;

/**
 * Handles the creation and management of user sessions in the BTO Management System.
 * <p>
 * This service determines the user's role based on their NRIC and assigns the corresponding
 * user object to the application's global session.
 */
public class SessionManagerService {

    /**
     * Instantiates a user session by identifying the user based on the provided NRIC.
     * <p>
     * Checks each category (Applicant, Officer, Manager) to determine the correct user object
     * and sets it as the current active session in {@code App.userSession}.
     *
     * @param NRIC the NRIC of the user who has logged in
     */
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
