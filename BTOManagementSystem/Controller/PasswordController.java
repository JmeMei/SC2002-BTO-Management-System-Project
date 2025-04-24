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

public class PasswordController {

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
