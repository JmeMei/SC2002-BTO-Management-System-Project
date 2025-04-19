package BTOManagementSystem.View;

import java.io.*;
import java.util.*;

import BTOManagementSystem.Model.User;

public class ApplicantEnquiryView {
    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantProjectStatus.csv";
    private static final Scanner scanner = new Scanner(System.in);

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

    public static void showEnquiryMenu(User user) {
        int choice = 0;
        do {
            System.out.println("\n=== Enquiry Management ===");
            System.out.println("1. View Enquiry");
            System.out.println("2. Submit Enquiry");
            System.out.println("3. Delete Enquiry");
            System.out.println("4. Back to Dashboard");
            System.out.print("Enter your choice: ");
            
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> viewEnquiry(user);
                case 2 -> submitEnquiry(user);
                case 3 -> deleteEnquiry(user);
                case 4 -> System.out.println("Returning to dashboard...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }

    private static void viewEnquiry(User user) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

            if (values[1].trim().equalsIgnoreCase(user.getNric())) {
                String enquiry = values[9].trim();
                String reply = values[10].trim();
                //System.out.println("Enquiry: " + (enquiry.isEmpty() ? "None" : enquiry)); //Enquiry will never be empty (will be NA if no enquiry yet)
                System.out.println("Enquiry: " + enquiry); 
                //System.out.println("Reply: " + (reply.isEmpty() ? "No reply yet" : reply)); //Reply will never be empty (will be NA if no reply yet)
                System.out.println("Reply: " + reply); 
                return;
            }
            }
            System.out.println("No record found for your NRIC.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void submitEnquiry(User user) {
        System.out.print("Enter your enquiry: ");
        String newEnquiry = scanner.nextLine();

        updateEnquiry(user, newEnquiry);
    }

    private static void deleteEnquiry(User user) {
        updateEnquiry(user, "NA");
        System.out.println("Enquiry deleted.");
    }

    private static void updateEnquiry(User user, String newEnquiry) {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String header = reader.readLine();
            records.add(header.split(","));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = Arrays.stream(line.split(","))
                                        .map(String::trim)
                                        .toArray(String[]::new);
                if (values[1].equalsIgnoreCase(user.getNric())) {
                    values[9] = newEnquiry; // Enquiry
                    values[10] = "NA"; // Reset reply
                }
                records.add(values);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] values : records) {
                writer.write(String.join(",", values));
                writer.newLine();
            }
            System.out.println("Enquiry updated.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
