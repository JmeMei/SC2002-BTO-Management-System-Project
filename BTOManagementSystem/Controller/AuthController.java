package BTOManagementSystem.Controller;

import java.util.HashMap;
import java.util.Map;

import BTOManagementSystem.Applicant;
import BTOManagementSystem.HDBManager;
import BTOManagementSystem.HDBOfficer;
import BTOManagementSystem.Model.User;

//	Handles login, password changes, loading users
public class AuthController {
    private Map<String, User> users;

    public AuthController() {
        users = new HashMap<>();
    }

    public void loadSampleUsers() {
        users.put("S1234567A", new Applicant("S1234567A", 36, "Single"));
        users.put("S2345678B", new Applicant("S2345678B", 29, "Married"));
        users.put("T3456789C", new HDBOfficer("T3456789C", 32, "Married"));
        users.put("T4567890D", new HDBManager("T4567890D", 45, "Married"));
    }

    public User login(String nric, String password) {
        User user = users.get(nric.toUpperCase());
        if (user != null && user.login(password)) return user;
        return null;
    }

    public void changePassword(User user, String newPwd) {
        user.changePassword(newPwd);
    }

    public Map<String, User> getAllUsers() {
        return users;
    }
}
