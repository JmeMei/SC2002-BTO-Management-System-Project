package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import BTOManagementSystem.Model.User;

public class ApplicationProjectStatusDAO {
    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantProjectStatus.csv";

     public static boolean applyForProject(User user, int projectID) {
        List<String[]> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine(); // Read header
            fileContent.add(header.split(",")); // Store header

            //Name,NRIC,Age,Marital Status,Password,role,ProjectID, Application Status, Enquiry, Reply

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                // Check if application status is already PENDING
                if (values[7].trim().equalsIgnoreCase("PENDING")) {
                    System.out.println("You already applied before.");
                    return false; // Exit early, don't modify or write back
                }

                // If NRIC matches, update the project ID and application status
                if (values[1].trim().equalsIgnoreCase(user.getNric())) {
                    values[6] = String.valueOf(projectID); // ProjectID
                    values[7] = "PENDING"; // Application Status
                }

                fileContent.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading the ApplicantProjectStatus.csv: " + e.getMessage());
            return false;
        }

        // Write updated content back to the CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] values : fileContent) {
                writer.write(String.join(",", values));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to ApplicantProjectStatus.csv: " + e.getMessage());
        }

        return false;
    }
}
