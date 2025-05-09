package BTOManagementSystem.View;

import BTOManagementSystem.Controller.EnquiryController;
import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.User;
import java.util.*;

/**
 * Represents the applicant view for managing enquiries.
 * <p>
 * Provides a menu interface for applicants to:
 * <ul>
 *     <li>View submitted enquiries</li>
 *     <li>Submit a new enquiry</li>
 *     <li>Edit an existing enquiry</li>
 *     <li>Delete an enquiry</li>
 * </ul>
 * This class interacts with the {@code EnquiryController} to perform backend operations.
 */
public class ApplicantEnquiryView implements EnquiryView {

    @Override
    public void showEnquiryMenu(User user) {
        EnquiryController enquiryController = new EnquiryController();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("\n=== Enquiry Management for Applicants ===");
            System.out.println("1. View Your Enquiries");
            System.out.println("2. Submit a New Enquiry");
            System.out.println("3. Edit Your Enquiry");
            System.out.println("4. Delete Enquiry");
            System.out.println("5. Back to Main Menu");
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
                    // view all enquiries submitted by user 
                    // can consider App.userSession.getNric() if necessary
                    List<Enquiry> enquiries = enquiryController.viewEnquiriesForApplicant(user.getNric());
                    if(enquiries.isEmpty()){
                        System.out.println("You have not submitted any enquiries");
                        break;
                    }
                    printEnquiryList(enquiries);

                }
                case 2 -> {
                    // ask for which project
                    List<String> availableProjects = enquiryController.availableProjectList();

                    // if no available projects
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

                    // submit enquiry for user
                    String result = enquiryController.submitEnquiry(user.getNric(), selectedProject, question);// let controller get officer name
                    System.out.println(result);
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
                    printEnquiryList(enquiries);

                    // edit enquiry by ID
                    System.out.print("Enter Enquiry ID to edit: ");
                    String enquiryID = scanner.nextLine();

                    System.out.print("Enter new question: ");
                    String newQuestion = scanner.nextLine();

                    String result = enquiryController.editEnquiry(user.getNric(), enquiryID, newQuestion);
                    System.out.println(result);
                }

                case 4 -> {
                    List<Enquiry> deletableEnquiries = enquiryController.viewEnquiriesForApplicant(user.getNric());

                    if (deletableEnquiries.isEmpty()) {
                        System.out.println("You have no deletable enquiries.");
                        break;
                    }
                
                    System.out.println("\n--- Deletable Enquiries ---");
                    printEnquiryList(deletableEnquiries);

                    System.out.print("Enter Enquiry ID to Delete: ");
                    String enquiryID = scanner.nextLine();
                
                    String result = enquiryController.deleteEnquiry(enquiryID, user.getNric());
                    System.out.println(result);
                }
                case 5 -> {
                    System.out.println("Returning to main menu.");
                    ApplicantView applicantView = new ApplicantView();
                    applicantView.showApplicantMenu(user);
                }

                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    @Override
    public void printEnquiryList(List<Enquiry> enquiries){
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
