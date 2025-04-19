package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;

public class ApplicationProjectStatusDAO {
    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantProjectStatus.csv";

     public static boolean applyForProject(User user, String projectName, FlatType flatType) {
        List<String[]> fileContent = new ArrayList<>();
        /*
        0 Name,
        1 NRIC,
        2 Age,
        3 Marital Status,
        4 Password,
        5 role,
        6 Project Name,
        7 FlatType,
        8 Application Status,
        9 Enquiry,
        10 Reply
        */
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine(); // Read header
            fileContent.add(header.split(",")); // Store header

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                // Check if application status is already PENDING
                if (values[8].trim().equalsIgnoreCase("PENDING")) {
                    System.out.println("You already applied before.");
                    return false; // Exit early, don't modify or write back
                }

                // If NRIC matches, update the project ID and application status
                if (values[1].trim().equalsIgnoreCase(user.getNric())) {
                    values[6] = projectName; //Project name
                    values[7] = flatType.name(); // Application Status
                    values[8] = "PENDING";
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

    public static boolean viewMyApplication(User user) {
        List<String[]> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine(); // Read header
            fileContent.add(header.split(",")); // Store header

            /*
            0 Name,
            1 NRIC,
            2 Age,
            3 Marital Status,
            4 Password,
            5 role,
            6 Project Name,
            7 FlatType,
            8 Application Status,
            9 Enquiry,
            10 Reply
            */

            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                // Get the applicant's NRIC
                //System.out.println("[DEBUG] Comparing: " + values[1].trim() + " vs " + user.getNric());
                if (values[1].trim().equalsIgnoreCase(user.getNric())) {
                    // Extract data
                    String name = values[0].trim();
                    String nric = values[1].trim();
                    int age = Integer.parseInt(values[2].trim());
                    String maritalStatus = values[3].trim();
                    //String password = values[4].trim();
                    String role = values[5].trim();
                    String projectName = values[6].trim();
                    FlatType flatType = FlatType.valueOf(values[7].trim().toUpperCase());
                    ApplicationStatus applicationStatus = ApplicationStatus.valueOf(values[8].trim().toUpperCase());
                    String enquiry = values[9].trim();
                    String reply = values[10].trim();

                    if (projectName == "NA") {
                        System.out.println("No application record found for NRIC: " + user.getNric());
                        return false;
                    }

                    // Display in a user-friendly format
                    System.out.println("\n=== Your Application Details ===");
                    System.out.println("Name: " + name);
                    System.out.println("NRIC: " + nric);
                    System.out.println("Age: " + age);
                    System.out.println("Marital Status: " + maritalStatus);
                    System.out.println("Role: " + role);
                    System.out.println("Project Name: " + projectName);
                    System.out.println("Flat Type: " + flatType);
                    System.out.println("Application Status: " + applicationStatus);
                    System.out.println("Enquiry: " + (enquiry.isEmpty() ? "None" : enquiry));
                    System.out.println("Reply: " + (reply.isEmpty() ? "None" : reply));
                    return true; // Exit after first match
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the ApplicantProjectStatus.csv: " + e.getMessage());
            return false;
        }
        return false;
    }
}
