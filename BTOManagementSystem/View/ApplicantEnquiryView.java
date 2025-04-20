package BTOManagementSystem.View;

import java.io.*;
import java.util.*;

import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Controller.EnquiryController;
import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.ProjectListDAO;

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
        EnquiryController enquiryController = new EnquiryController();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n=== Enquiry Management ===");
            System.out.println("1. View Your Enquiries");
            System.out.println("2. Submit a New Enquiry");
            System.out.println("3. Edit Your Enquiry");
            System.out.println("4. Delete Enquiry");
            System.out.println("5. Back to Dashboard");
            System.out.print("Enter your choice: ");
            
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {

                    List<Enquiry> enquiries = enquiryController.viewEnquiriesForApplicant(user.getNric());
                    if(enquiries.isEmpty()){
                        System.out.println("You have not submitted any enquiries");
                        break;
                    }
                    for (int i = 0; i < enquiries.size(); i++) {
                        Enquiry e = enquiries.get(i);
                        System.out.printf("==Enquiry ID: %s==\nProject Name: %s\n Question: %s\n Answer: %s\n", 
                            e.getEnquiryID(), 
                            e.getProjectName(), 
                            e.getQuestion(), 
                            e.getAnswer().isEmpty() ? "Unanswered" : e.getAnswer());
                    }

                }
                case 2 -> {
                    // ask for which project
                    ProjectListDAO projectDAO = new ProjectListDAO();

                    List<String> availableProjects = enquiryController.availableProjectList(projectDAO);

                    // if no available projects
                    if (availableProjects == null || availableProjects.isEmpty()) {
                        System.out.println("No available projects.");
                        return;
                    }
            
                    System.out.println("Select a project to ask a question about:");
                    for (int i = 0; i < availableProjects.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, availableProjects.get(i));
                    }
                    System.out.print("Enter your choice (1-" + availableProjects.size() + "): ");
                    choice = scanner.nextInt();

                    // if valid choice of project
                    if (choice >= 1 && choice <= availableProjects.size()) {
                        String selectedProject = availableProjects.get(choice - 1);
                        System.out.println("You selected: " + selectedProject);
            
                        // ask the user for question
                        scanner.nextLine();
                        System.out.print("Enter your question: ");
                        String question = scanner.nextLine();

                    // get question from user
                    enquiryController.submitEnquiry(user.getNric(), selectedProject, question, "");// dont know officerNRIC for now 
                    System.out.println("Enquiry submitted successfully!");
                    }
                }

                case 3 -> {

                    // show all the enquiries by the user
                    List<Enquiry> enquiries = enquiryController.viewEnquiriesForApplicant(user.getNric());
                    if(enquiries.isEmpty()){
                        System.out.println("You have not submitted any enquiries");
                        break;
                    }
                    System.out.println("\n--- Your submitted enquiries ---");
                    for (int i = 0; i < enquiries.size(); i++) {
                        Enquiry e = enquiries.get(i);
                        System.out.printf("==Enquiry ID: %s==\nProject Name: %s\n Question: %s\n Answer: %s\n", 
                            e.getEnquiryID(), 
                            e.getProjectName(), 
                            e.getQuestion(), 
                            e.getAnswer().isEmpty() ? "Unanswered" : e.getAnswer());
                    }

                    // edit enquiry by ID
                    System.out.print("Enter Enquiry ID to edit: ");
                    String enquiryID = scanner.nextLine();

                    System.out.print("Enter new question: ");
                    String newQuestion = scanner.nextLine();

                    boolean success = enquiryController.editEnquiry(user.getNric(), enquiryID, newQuestion);

                    if (success) {
                        System.out.println("Enquiry updated successfully.");
                    } else {
                        System.out.println("Failed to update enquiry. It might be already answered or not belong to you.");
                    };
                }

                case 4 -> {
                    List<Enquiry> deletableEnquiries = enquiryController.viewEnquiriesForApplicant(user.getNric());

                    if (deletableEnquiries.isEmpty()) {
                        System.out.println("You have no deletable (unanswered) enquiries.");
                        return;
                    }
                
                    System.out.println("\n--- Deletable Enquiries ---");
                    for (int i = 0; i < deletableEnquiries.size(); i++) {
                        Enquiry e = deletableEnquiries.get(i);
                        System.out.printf("==Enquiry ID: %s==\nProject Name: %s\n Question: %s\n Answer: %s\n", 
                            e.getEnquiryID(), 
                            e.getProjectName(), 
                            e.getQuestion(), 
                            e.getAnswer().isEmpty() ? "Unanswered" : e.getAnswer());
                    }

                    System.out.print("Enter Enquiry ID to Delete: ");
                    String enquiryID = scanner.nextLine();
                
                    boolean success = enquiryController.deleteEnquiry(enquiryID, user.getNric());
                
                    if (success) {
                        System.out.println("Enquiry deleted successfully.");
                    } else {
                        System.out.println("Failed to delete enquiry.");
                    };
                }
                case 5 -> System.out.println("Returning to dashboard...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

}
