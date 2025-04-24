package BTOManagementSystem.View;

import BTOManagementSystem.Controller.EnquiryController;
import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.User;
import java.util.List;
import java.util.Scanner;

public class HDBManagerEnquiryView implements EnquiryView {
    
    @Override
    public void showEnquiryMenu(User user){
        EnquiryController enquiryController = new EnquiryController();
        Scanner scanner = new Scanner(System.in);
        
        int choice = 0;
        do { 
            System.out.println("\n=== Enquiry Management for Managers ===");
            System.out.println("1. View All Enquiries");
            System.out.println("2. View Unanswered Enquiries for Managed Projects");
            System.out.println("3. Reply to Unanswered Enquiries for Managed Projects");
            System.out.println("4. Back to Main Menu");
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
                    List<Enquiry> allEnquiries = enquiryController.getAllEnquiries();
                    if(allEnquiries.isEmpty()){
                        System.out.println("There are no enquiries.");
                        break;
                    }
                    printEnquiryList(allEnquiries);
                }
                case 2 -> {
                    List<Enquiry> unansweredEnquiries =  enquiryController.getUnansweredEnquiries(user.getNric());
                    
                    if(unansweredEnquiries.isEmpty()){
                        System.out.println("You have no unanswered enquiries.");
                        break;
                    }
                    System.out.println("\n--- Unanswered Enquiries ---");
                    printEnquiryList(unansweredEnquiries);
                }
                case 3 -> {
                    List<Enquiry> unansweredEnquiries =  enquiryController.getUnansweredEnquiries(user.getNric());
                    
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
                case 4 -> {

                    System.out.println("Returning to main menu.");
                }
                default-> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
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
