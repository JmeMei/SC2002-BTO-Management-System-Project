package BTOManagementSystem.View;

import java.io.Serial;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Controller.PasswordController;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Room;
import BTOManagementSystem.Model.User;

public class ApplicantView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<Room> roomsAvailable; //Global static variable to store the available rooms

    public void showMenu(Applicant user)  { //Applicant view has a user
        ApplicationController applicationController = new ApplicationController();
        int option = 0;

        while (option != 8) {
            //System.out.println("Welcome, " + user.getName());
            System.out.println("\n=== Applicant Dashboard ===");
            System.out.println("1. View Available Projects");
            System.out.println("2. Apply for a Project");
            System.out.println("3. View My Application");
            System.out.println("4. Withdraw My Application");
            System.out.println("5. Enquiry Management");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.println("8. Exit");
            System.out.print("Enter your option: ");

            String input = scanner.nextLine(); // safer than nextInt()
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (option) {
                case 1:
                    applicationController.displayAvailableProjects(user);
                    break;
                case 2:
                    applicationController.displayAvailableProjects(user);
                    applyForProject(user); // TODO shift the applyproject to ApplicationController
                    break;
                case 3:
                    applicationController.viewMyApplication(user);
                    break;
                case 4:
                    applicationController.withdrawApplication(user); // add the call to the applicationController
                    break;
                case 5:
                    manageEnquiries(user);
                    break;
                case 6:
                    System.out.print("Enter the new password: ");
                    String newPassword = scanner.nextLine();
                    boolean success = PasswordController.changePassword(user.getNric(), newPassword);

                    if (success) {
                        System.out.print("Password updated. Please log in again.\n");
                        App.main(null);
                        return; // Exit menu to force re-login
                    } else {
                        System.out.println("Failed to update password."); //May happen if the FILE_PATH is wrong
                        //showMenu();
                    }
                    break;
                case 7:
                    System.out.println(user.getName() + " logging out...");
                    App.main(null);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private static void applyForProject(User user)  {
        System.out.println("Enter the projectID: ");
        /* 
        ApplicationController applicationController = new ApplicationController();
        
        String input = scanner.nextLine(); // Read as String
        int projectID;
        try {
            projectID = Integer.parseInt(input); // Parse to int
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return; // or use continue inside a loop
        }

        boolean projectFound = false;

        // Iterate through the roomsAvailable list
        while(!projectFound){
            // Make sure the user enters an integer
            
            roomsAvailable = applicationController.displayAvailableProjects(user);
            //roomAvailable is a global that gets declared in viewAvailableProject
            for (Room room : roomsAvailable) { 
                if (room.getProjectID() == projectID) {
                    System.out.println("Project found: " + room.getProjectName());
                    projectFound = true;
                    
                    applicationController.applyForProject(user, projectID);
                    // You can now proceed with applying logic
                    break;
                }
            }
            if (!projectFound) {
                System.out.println("No project found with that ID.");
                return;
            }
        }
        // Prompt for project and flat type → pass to controller
        */
    }


    private static void manageEnquiries(User user) {
        System.out.println("[DEBUG] Managing enquiries...");
        // Submenu for add/view/delete enquiry

         ApplicantEnquiryView applicantEnquiryView = new ApplicantEnquiryView();
         applicantEnquiryView.showEnquiryMenu(user);
    }
}