package BTOManagementSystem.Controller;
import java.util.*;

import BTOManagementSystem.Model.*;
import BTOManagementSystem.View.LoginPromptView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AuthController {

    private static final String FILE_PATH = "BTOManagementSystem/Data/userlogin.csv";

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
                    return new User(userName, userNRIC, userAge, userPassword, userMaritalStatus,  userRole);
                    // Create and return a new User object here if needed
                    // return new User(userName, userNRIC, userAge, userMaritalStatus, userPassword, userRole);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the CSV database: " + e.getMessage());
        }

        return null;
    }
}
