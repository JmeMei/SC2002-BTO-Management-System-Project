package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;

public class ApplicationProjectStatusDAO {
    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantProjectStatus.csv";
    private ArrayList<ApplicantProjectStatus> statusList = new ArrayList<>();

    public ApplicationProjectStatusDAO() {
        //init Applicants from CSV
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            // Skip Header
            br.readLine();

            //Iterate through CSV
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ApplicantProjectStatus applicantProjectStatus = new ApplicantProjectStatus(
                        values[0].trim(),                                     // Name
                        values[1].trim(),                                     // NRIC
                        Integer.parseInt(values[2].trim()),                   // Age
                        values[3].trim(),                                     // Marital Status
                        values[4].trim(),                                     // Role
                        values[5].trim(),                                     // Project Name
                        FlatType.fromString(values[6].trim()),                // Flat Type
                        ApplicationStatus.valueOf(values[7].trim())          // Application Status
                        //values[8].trim(),                                     // Enquiry
                        //values[9].trim()                                     // Reply

                );
                statusList.add(applicantProjectStatus);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean applyForProject(User user, String projectName, FlatType flatType) {
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(user.getNric())) {
                return false;
            }
        }

        ApplicantProjectStatus newApplication = new ApplicantProjectStatus(
                user.getName(),
                user.getNric(),
                user.getAge(),
                user.getMaritalStatus(),
                user.getRole(),
                projectName,
                flatType,
                ApplicationStatus.PENDING
        );
        statusList.add(newApplication);

        // subtract from existing no of flats
        this.updateDB();
        return true;
    }

    public ApplicationStatus getApplicationStatus(User user, String projectName, FlatType flatType) {
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(user.getNric()) && status.getProjectName().equals(projectName) && flatType.equals(status.getFlatType())) {
                return status.getApplicationStatus();
            }
        }
        return null;
    }

    public String getProjectNameforApplicant(User user) {
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(user.getNric())) {
                return status.getProjectName();
            }
        }
        return null;
    }

    public boolean viewMyApplication(User user) {
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(user.getNric())) {
                // Display in a user-friendly format
                System.out.println("\n=== Your Application Details ===");
                System.out.println("Name: " + status.getName());
                System.out.println("NRIC: " + status.getNric());
                System.out.println("Project Name: " + status.getProjectName());
                System.out.println("Flat Type: " + status.getFlatType());
                System.out.println("Application Status: " + status.getApplicationStatus());

                return true; // Exit after first match
            }
        }
        return false;
    }

    public boolean updateDB() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write the header
            bw.write("Name,NRIC,Age,Marital Status,role,Project Name,FlatType,Application Status");
            bw.newLine();

            // Write each status
            for (ApplicantProjectStatus s : statusList) {
                String line = String.join(",",
                        s.getName(),
                        s.getNric(),
                        String.valueOf(s.getAge()),
                        s.getMaritalStatus(),
                        s.getRole(),
                        s.getProjectName(),
                        s.getFlatType().getDisplayName(),
                        s.getApplicationStatus().name()
                );
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

//    public static boolean requestApplicationWithdrawal(User user) {
//        List<String[]> fileContent = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
//            String header = reader.readLine(); // Read header
//            fileContent.add(header.split(",")); // Store header
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(",");
//
//                // If NRIC matches, update the project ID and application status
//                if (values[1].trim().equalsIgnoreCase(user.getNric())) {
//                    WithdrawalStatus withdrawalStatus = WithdrawalStatus.valueOf(values[11].trim().toUpperCase());
//                    if (withdrawalStatus == WithdrawalStatus.REQUESTED) {
//                        System.out.println("You have already requested withdrawal status of this project.");
//                        return false;
//                    }
//                    values[11] = "REQUESTED";
//                }
//
//                fileContent.add(values);
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading the ApplicantProjectStatus.csv: " + e.getMessage());
//            return false;
//        }
//
//        // Write updated content back to the CSV
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
//            for (String[] values : fileContent) {
//                writer.write(String.join(",", values));
//                writer.newLine();
//            }
//            return true;
//        } catch (IOException e) {
//            System.out.println("Error writing to ApplicantProjectStatus.csv: " + e.getMessage());
//        }
//
//        return false;
//    }


//     public static boolean applyForProject(User user, String projectName, FlatType flatType) {
//        List<String[]> fileContent = new ArrayList<>();
//        /*
//        0 Name,
//        1 NRIC,
//        2 Age,
//        3 Marital Status,
//        4 Password,
//        5 role,
//        6 Project Name,
//        7 FlatType,
//        8 Application Status,
//        9 Enquiry,
//        10 Reply
//        */
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
//            String header = reader.readLine(); // Read header
//            fileContent.add(header.split(",")); // Store header
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(",");
//
//                // Check if application status is already PENDING
//                if (values[8].trim().equalsIgnoreCase("PENDING")) {
//                    //To-Do add logic to pass whether application is already pending
//                    //System.out.println("You already applied before.");
//                    return false; // Exit early, don't modify or write back
//                }
//
//                // If NRIC matches, update the project ID and application status
//                if (values[1].trim().equalsIgnoreCase(user.getNric())) {
//                    values[6] = projectName; //Project name
//                    values[7] = flatType.getDisplayName(); // Application Status
//                    values[8] = "PENDING";
//                }
//
//                fileContent.add(values);
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading the ApplicantProjectStatus.csv: " + e.getMessage());
//            return false;
//        }
//
//        // Write updated content back to the CSV
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
//            for (String[] values : fileContent) {
//                writer.write(String.join(",", values));
//                writer.newLine();
//            }
//            return true;
//        } catch (IOException e) {
//            System.out.println("Error writing to ApplicantProjectStatus.csv: " + e.getMessage());
//        }
//
//        return false;
//    }

//  public static boolean viewMyApplication(User user) {
//        List<String[]> fileContent = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
//            String header = reader.readLine(); // Read header
//            fileContent.add(header.split(",")); // Store header
//
//            /*
//            0 Name,
//            1 NRIC,
//            2 Age,
//            3 Marital Status,
//            4 Password,
//            5 role,
//            6 Project Name,
//            7 FlatType,
//            8 Application Status,
//            9 Enquiry,
//            10 Reply
//            */
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(",");
//
//                // Get the applicant's NRIC
//                //System.out.println("[DEBUG] Comparing: " + values[1].trim() + " vs " + user.getNric());
//                if (values[1].trim().equalsIgnoreCase(user.getNric())) {
//                    // Extract data
//                    String name = values[0].trim();
//                    String nric = values[1].trim();
//                    int age = Integer.parseInt(values[2].trim());
//                    String maritalStatus = values[3].trim();
//                    //String password = values[4].trim();
//                    String role = values[5].trim();
//                    String projectName = values[6].trim();
//                    FlatType flatType = FlatType.fromString(values[7].trim());
//                    ApplicationStatus applicationStatus = ApplicationStatus.valueOf(values[8].trim().toUpperCase());
//                    String enquiry = values[9].trim();
//                    String reply = values[10].trim();
//                    WithdrawalStatus withdrawalStatus = WithdrawalStatus.valueOf(values[11].trim().toUpperCase());
//
//                    if (projectName.equalsIgnoreCase("NA")) {
//                        System.out.println("No application record found for NRIC: " + user.getNric());
//                        return false;
//                    }
//
//                    // Display in a user-friendly format
//                    System.out.println("\n=== Your Application Details ===");
//                    System.out.println("Name: " + name);
//                    System.out.println("NRIC: " + nric);
//                    System.out.println("Age: " + age);
//                    System.out.println("Marital Status: " + maritalStatus);
//                    System.out.println("Role: " + role);
//                    System.out.println("Project Name: " + projectName);
//                    System.out.println("Flat Type: " + flatType);
//                    System.out.println("Application Status: " + applicationStatus);
//                    System.out.println("Enquiry: " + (enquiry.isEmpty() ? "None" : enquiry));
//                    System.out.println("Reply: " + (reply.isEmpty() ? "None" : reply));
//                    System.out.println("WithdrawalStatus: " + withdrawalStatus);
//                    return true; // Exit after first match
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading the ApplicantProjectStatus.csv: " + e.getMessage());
//            return false;
//        }
//        return false;
//    }
