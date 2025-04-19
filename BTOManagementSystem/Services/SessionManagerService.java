package BTOManagementSystem.Services;
import BTOManagementSystem.App.*;
import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.DAO.UserDAO;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.Model.Roles.HDBManager;
import com.sun.jdi.Value;

public class SessionManagerService {

    public void InstantiateSession(String NRIC){

        UserDAO DAO = new UserDAO();
        String[] details = DAO.getAllUserDetails(NRIC);

        String role = details[details.length - 1];
        User newUser = null;
        if (role.equals("Applicant")) {
            newUser = new Applicant(details[0], details[1], Integer.parseInt(details[2]), details[4], details[3]);
        }
        else if (role.equals("HDBOfficer")) {
            newUser = new HDBOfficer(details[0], details[1], Integer.parseInt(details[2]), details[4], details[3]);
        }
        else if (role.equals("HDBManager")) {
            newUser = new HDBManager(details[0], details[1], Integer.parseInt(details[2]), details[4], details[3]);
        }

        App.userSession = newUser;

    }

}
