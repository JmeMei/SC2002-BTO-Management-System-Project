package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.User;

/**
 * Represents an HDB Manager in the BTO Management System.
 * HDB Managers are responsible for creating and managing BTO projects,
 * assigning officers, handling enquiries, and approving applications.
 *
 * Inherits from {@link User}.
 */
public class HDBManager extends User {

    /**
     * Constructs an HDB Manager with the provided user details.
     *
     * @param name          The full name of the manager.
     * @param nric          The NRIC of the manager.
     * @param age           The age of the manager.
     * @param password      The password associated with the manager's account.
     * @param maritalStatus The marital status of the manager.
     */
    public HDBManager(String name, String nric, int age, String password, String maritalStatus) {
        super(name, nric, age, password, maritalStatus, "Manager");
    }

    /**
     * Retrieves the NRIC of the manager.
     *
     * @return The NRIC of the manager.
     */
    public String getnric(){
      return nric;
    }
}
