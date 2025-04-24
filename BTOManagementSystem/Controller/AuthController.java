package BTOManagementSystem.Controller;
import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.Services.AuthenticatorService;
import BTOManagementSystem.Services.SessionManagerService;
import BTOManagementSystem.View.LoginPromptView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Controller class responsible for handling user authentication logic.
 * <p>
 * It verifies user credentials against the stored data in a CSV file and returns
 * the corresponding user object based on their role. It also manages the login prompt flow
 * and session initialization.
 * </p>
 */
public class AuthController {

    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantProjectList.csv";

    /**
     * Authenticates a user by comparing their NRIC and password with records in the CSV file.
     * <p>
     * If authentication is successful, it returns a corresponding user object (e.g., {@link HDBManager},
     * {@link Applicant}, or {@link HDBOfficer}) based on the role in the database. If credentials are invalid,
     * it returns null;
     * </p>
     *
     * @param NRIC the NRIC of the user attempting to log in
     * @param password the password associated with the NRIC
     * @return a User object if credentials are valid, else null
     */
    public static User Authenticate(String NRIC, String password) {

        List<String[]> userDatabase = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String userName = values[0].trim();
                String userNRIC = values[1].trim();
                int userAge = Integer.parseInt(values[2].trim());
                String userMaritalStatus = values[3].trim();
                String userPassword = values[4].trim();
                String userRole = values[5].trim();

                if (userNRIC.equals(NRIC) && userPassword.equals(password)) {
                    System.out.println("Login successful, welcome back " + userName);
                    //return back to LoginPromptView

                    //return proper object to LoginPromptView
                    switch (userRole) {
                        case "Manager" -> {
                            return new HDBManager(userName, userNRIC, userAge, userPassword, userMaritalStatus);
                        }
                        case "Applicant" -> {
                            return new Applicant(userName, userNRIC, userAge, userPassword, userMaritalStatus);
                        }
                        case "Officer" -> {
                            return new HDBOfficer(userName, userNRIC, userAge, userPassword, userMaritalStatus);
                        }
                        default -> {
                            return new User(userName, userNRIC, userAge, userPassword, userMaritalStatus, userRole);
                        }
                    }
                }
            } 
            } catch (IOException e) {
            System.out.println("Error reading the CSV database: " + e.getMessage());
        }

        return null;
    }

    /**
     * Starts the login and authentication process using the provided login view,
     * authenticator service, and session manager.
     * <p>This method performs the following steps:</p>
     * <ul>
     *   <li>Prompts the user for NRIC and password</li>
     *   <li>Validates the NRIC format using a regex</li>
     *   <li>Authenticates credentials via the {@link AuthenticatorService}</li>
     *   <li>Displays success or failure messages through the view</li>
     *   <li>If successful, initializes a session using the {@link SessionManagerService}</li>
     * </ul>
     *
     * @param LoginView the view responsible for prompting login input and displaying messages
     * @param AuthService the service used to authenticate user credentials
     * @param sessionManager the service used to create a session upon successful login
     */
    public void startAuthenticateProcess(LoginPromptView LoginView, AuthenticatorService AuthService, SessionManagerService sessionManager){

        String[] loginData;
        Boolean LoggedInStatus = false;


        do{
            loginData = LoginView.getLoginData();
            String userInputNRIC = loginData[0];

            // Regex: Start with S, T, F, or G; followed by 7 digits; end with a capital letter
            String regex = "^[STFG]\\d{7}[A-Z]$";

            if (userInputNRIC != null && userInputNRIC.matches(regex)) {
                LoggedInStatus = AuthService.authenticate(loginData[0], loginData[1]);
                LoginView.LoginSucessOrFail(LoggedInStatus);
            }else{
                LoginView.IncorrectNRICFormat();
            }
        }while (!LoggedInStatus);

        sessionManager.InstantiateSession(loginData[0]);

    }
}