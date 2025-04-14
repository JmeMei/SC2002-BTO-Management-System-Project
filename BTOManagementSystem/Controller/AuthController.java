package BTOManagementSystem.Controller;
import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
                    //return back to LoginPromptView

                    //return proper object to LoginPromptView
                    switch (userRole) {
                    case "Manager" -> {
                        return new HDBManager(userName, userNRIC, userAge, userPassword, userMaritalStatus);
                        }
                    case "Applicant" -> {
                        System.out.print("is it here");
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
}