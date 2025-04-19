package BTOManagementSystem.Services;
import BTOManagementSystem.App.*;
import BTOManagementSystem.Model.DAO.UserDAO;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;


public class SessionManagerService {

    public void InstantiateSession(String NRIC){

        UserDAO DAO = new UserDAO();
        String userType = DAO.getUserType(NRIC);
        String[] details = DAO.getAllUserDetails(NRIC, userType);



        if(userType.compareTo("applicant") == 0){
            Applicant newUser = new Applicant(details[0],details[1],Integer.parseInt(details[2]),details[4],details[3]);
            App.userSession = newUser;
        }

        else if (userType.compareTo("officer") == 0){
            HDBOfficer newUser = new HDBOfficer(details[0],details[1],Integer.parseInt(details[2]),details[4],details[3]);
            App.userSession = newUser;
        }
        else if (userType.compareTo("manager") == 0){
            HDBManager newUser = new HDBManager(details[0],details[1],Integer.parseInt(details[2]),details[4],details[3]);
            App.userSession = newUser;
        }


    }

}
