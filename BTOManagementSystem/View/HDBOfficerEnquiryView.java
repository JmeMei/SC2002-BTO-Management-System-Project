package BTOManagementSystem.View;

import BTOManagementSystem.Controller.EnquiryController;
import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.User;
import java.util.List;
import java.util.Scanner;

public class HDBOfficerEnquiryView {

        public static void showEnquiryMenu(User user) {
            
        EnquiryController enquiryController = new EnquiryController();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n=== Enquiry Management for Officers ===");
            System.out.println("1. View Your Enquiries (Applicant)");
            System.out.println("2. Submit a New Enquiry (Applicant)");
            System.out.println("3. Edit Your Enquiry (Applicant)");
            System.out.println("4. Delete Own Enquiry (Applicant)");
            System.out.println("5. Reply to enquiries of your Managed Projects");
            System.out.println("6. Back to Main Menu");
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
                    printEnquiryList(enquiries);
                }
                case 2 -> {
                    List<String> availableProjects = enquiryController.availableProjectList();

                    if (availableProjects == null || availableProjects.isEmpty()) {
                        System.out.println("No available projects.");
                        break;
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
                    enquiryController.submitEnquiry(user.getNric(), selectedProject, question);
                    System.out.println("Enquiry submitted successfully!");
                    }
                }

                case 3 -> {
                    List<Enquiry> enquiries = enquiryController.viewEnquiriesForApplicant(user.getNric());
                    if(enquiries.isEmpty()){
                        System.out.println("You have not submitted any enquiries");
                        break;
                    }
                    System.out.println("\n--- Your submitted enquiries ---");
                    printEnquiryList(enquiries);

                    System.out.print("Enter Enquiry ID to edit: ");
                    String enquiryID = scanner.nextLine();

                    System.out.print("Enter answer: ");
                    String newQuestion = scanner.nextLine();

                    String result = enquiryController.editEnquiry(user.getNric(), enquiryID, newQuestion);
                    System.out.println(result);

                }

                case 4 -> {
                    List<Enquiry> deletableEnquiries = enquiryController.viewEnquiriesForApplicant(user.getNric());

                    if (deletableEnquiries.isEmpty()) {
                        System.out.println("You have no deletable enquiries.");
                        return;
                    }
                
                    System.out.println("\n--- Deletable Enquiries ---");
                    printEnquiryList(deletableEnquiries);

                    System.out.print("Enter Enquiry ID to Delete: ");
                    String enquiryID = scanner.nextLine();
                
                    String result = enquiryController.deleteEnquiry(enquiryID, user.getNric());
                    System.out.println(result);

                }
                case 5 -> {
                    List<Enquiry> unansweredEnquiries = enquiryController.getUnansweredEnquiries(user.getNric());

                    if(unansweredEnquiries.isEmpty()){
                        System.out.println("You have no unanswered enquiries.");
                        break;
                    }
                    System.out.println("\n--- Unanswered Enquiries ---");
                    printEnquiryList(unansweredEnquiries);

                    System.out.print("Enter enquiry ID to answer: ");
                    String enquiryID = scanner.nextLine();

                    System.out.print("Enter answer to enquiry: ");
                    String answer = scanner.nextLine();

                    String result = enquiryController.replyEnquiry(user.getNric(), enquiryID, answer);
                    System.out.println(result);
                }
                case 6 -> {
                    scanner.close();
                    System.out.println("Returning to main menu.");
                }
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 6);
    }

    public static void printEnquiryList(List<Enquiry> enquiries){
        for (int i = 0; i < enquiries.size(); i++) {
            Enquiry e = enquiries.get(i);
            System.out.printf("\n== Enquiry ID: %s ==\nProject Name: %s\nQuestion: %s\nAnswer: %s\n", 
                e.getEnquiryID(), 
                e.getProjectName(), 
                e.getQuestion(), 
                e.getAnswer().isEmpty() ? "No answer yet" : e.getAnswer());
        }

    }
}
