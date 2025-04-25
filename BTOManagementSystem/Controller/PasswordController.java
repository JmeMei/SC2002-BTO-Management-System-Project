package BTOManagementSystem.Controller;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.DAO.ApplicantDAO;
import BTOManagementSystem.Model.DAO.HDBManagerDAO;
import BTOManagementSystem.Model.DAO.HDBOfficerDAO;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.Model.User;

import java.io.*;
import java.util.*;
/**
 * Controller class responsible for handling user password changes in the system.
 */
public class PasswordController {

    /**
     * Changes the password for the currently logged-in user.
     * <p>This method performs the following steps:</p>
     * <ul>
     *     <li>Updates the password in the user session</li>
     *     <li>Retrieves the appropriate DAO based on the user's role</li>
     *     <li>Updates the password in memory and persists it back to the corresponding user List and CSV file</li>
     * </ul>
     *
     *
     * @param nric the NRIC of the user requesting the password change
     * @param newPassword the new password to be set
     * @return {@code true} if the password was successfully updated, {@code false} otherwise
     */
    public static boolean changePassword(String nric, String newPassword) {

        //Set new password in session
        User user = App.userSession;
        user.setPassword(newPassword);
        App.userSession = user;

        //Init all DAOs
        ApplicantDAO ApplicantDAO = new ApplicantDAO();
        HDBOfficerDAO OfficerDAO = new HDBOfficerDAO();
        HDBManagerDAO ManagerDAO = new HDBManagerDAO();

        boolean updated = false;

        String role = user.getRole();

        if (role.equals("Applicant")) {
            List<Applicant> applicants = ApplicantDAO.getAllUsers();
            for (Applicant a : applicants) {
                if (a.getNric().equals(user.getNric())) {
                    a.setPassword(newPassword);
                    updated = ApplicantDAO.updateDB();
                    break;
                }
            }
        } else if (role.equals("Officer")) {
            List<HDBOfficer> officers = OfficerDAO.getAllUsers();
            for (HDBOfficer o : officers) {
                if (o.getNric().equals(user.getNric())) {
                    o.setPassword(newPassword);
                    updated = OfficerDAO.updateDB();
                    break;
                }
            }
        } else if (role.equals("Manager")) {
            List<HDBManager> managers = ManagerDAO.getAllUsers();
            for (HDBManager m : managers) {
                if (m.getNric().equals(user.getNric())) {
                    m.setPassword(newPassword);
                    updated = ManagerDAO.updateDB();
                    break;
                }
            }
        }

        return updated;

    }//END of changePassword
}
