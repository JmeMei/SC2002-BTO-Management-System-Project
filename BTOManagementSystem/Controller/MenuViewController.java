package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.View.ApplicantView;
import BTOManagementSystem.View.HDBManagerView;
import BTOManagementSystem.View.HDBOfficerView;
/**
 * Controller class responsible for directing users to their respective main menu views
 * based on their role (Applicant, HDB Officer, or HDB Manager).
 */

public class MenuViewController {

    /**
     * Routes the logged-in user to the appropriate menu view depending on their role.
     * <ul>
     *     <li>If the user is an {@link HDBOfficer}, the HDB Officer menu is shown.</li>
     *     <li>If the user is an {@link HDBManager}, the HDB Manager menu is shown.</li>
     *     <li>If the user is an {@link Applicant}, the Applicant menu is shown.</li>
     * </ul>
     *
     * @param user the currently authenticated user
     */
    public void handleMenuOptions(User user) {

        if (user instanceof HDBOfficer) {
            HDBOfficerView officerView = new HDBOfficerView();
            officerView.showOfficerMenu((HDBOfficer)user);
        } else if (user instanceof HDBManager) {
            HDBManagerView managerView = new HDBManagerView();
            managerView.showMenu();
        } else if (user instanceof Applicant) {
            ApplicantView applicantView = new ApplicantView();
            applicantView.showApplicantMenu((Applicant)user);
        }

    }
}

