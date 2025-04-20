package BTOManagementSystem.View;

import java.util.List;
import java.util.Scanner;

import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Controller.EnquiryController;
import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.User;

public class OfficerEnquiryView {

        public static void showEnquiryMenu(User user) {
            
        EnquiryController enquiryController = new EnquiryController();
        ApplicationController applicationController = new ApplicationController();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            // first 4 options is similar to applicant
            System.out.println("\n=== Enquiry Management ===");
            System.out.println("1. View Your Enquiries");
            System.out.println("2. Submit a New Enquiry");
            System.out.println("3. Edit Your Enquiry");
            System.out.println("4. Delete Own Enquiry");
            System.out.println("5. Answer Enquiry of Managed Projects");
            System.out.println("6. Back to Dashboard");
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
                    System.out.println("\n--- Your submitted enquiries ---");
                    for (int i = 0; i < enquiries.size(); i++) {
                        Enquiry e = enquiries.get(i);
                        System.out.printf("[%d] %s\n   Question: %s\n   Answer: %s\n", 
                            i + 1, 
                            e.getProjectName(), 
                            e.getQuestion(), 
                            e.getAnswer().isEmpty() ? "Unanswered" : e.getAnswer());
                    }

                }
                case 2 -> {
                    // ask for which project
                    applicationController.displayAvailableProjects(user);
                    System.out.println("Enter the project name to ask about?");

                    String projectName = scanner.nextLine(); // TODO fill in with logic to get the name from the project list file
                    
                    // get question from user
                    System.out.println("What do you want to ask about this project?");
                    String question = scanner.nextLine();
                    enquiryController.submitEnquiry(user.getNric(), projectName, question, "");// dont know officerNRIC for now 
                    System.out.println("Enquiry submitted successfully!");}

                case 3 -> {
                    // lets user edit their enquiry
                    System.out.println("to be implemented");}

                case 4 -> {
                    List<Enquiry> deletableEnquiries = enquiryController.getEditableEnquiries(user.getNric());

                    if (deletableEnquiries.isEmpty()) {
                        System.out.println("You have no deletable (unanswered) enquiries.");
                        return;
                    }
                
                    System.out.println("\n--- Deletable Enquiries ---");
                    for (int i = 0; i < deletableEnquiries.size(); i++) {
                        Enquiry e = deletableEnquiries.get(i);
                        System.out.printf("[%d] %s\n   Question: %s\n", i + 1, e.getProjectName(), e.getQuestion());
                    }
                
                    System.out.print("Select the enquiry number to delete (or 0 to cancel): ");
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                        return;
                    }
                
                    if (choice <= 0 || choice > deletableEnquiries.size()) {
                        System.out.println("Cancelled.");
                        return;
                    }
                
                    Enquiry selected = deletableEnquiries.get(choice - 1);
                    boolean success = enquiryController.deleteEnquiry(selected.getEnquiryID(), user.getNric());
                
                    if (success) {
                        System.out.println("Enquiry deleted successfully.");
                    } else {
                        System.out.println("Failed to delete enquiry.");
                    };
                }
                case 5 -> System.out.println("TODO, implement answering enquiries");
                case 6 -> System.out.println("Returning to dashboard...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
    
}
