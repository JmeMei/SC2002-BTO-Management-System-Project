package BTOManagementSystem.View;

import java.io.Serial;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Controller.PasswordController;
import BTOManagementSystem.Model.Room;
import BTOManagementSystem.Model.User;

public class ApplicantView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<Room> roomsAvailable; //Global static variable to store the available rooms

    public void showMenu(User user) throws ParseException { //Applicant view has a user
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
                    viewAvailableProjects(user);
                    break;
                case 2:
                    viewAvailableProjects(user);
                    applyForProject(user);
                    break;
                case 3:
                    viewMyApplication(user);
                    break;
                case 4:
                    withdrawApplication(user);
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

    // Dummy implementations to be replaced with real logic
    private static void viewAvailableProjects(User user) throws ParseException {
        System.out.println("Based on your profile: ");
        System.out.println("Your age is: " + user.getAge());
        System.out.println("Your marital status is: " + user.getMaritalStatus());
        ApplicationController applicationController = new ApplicationController();
        roomsAvailable = applicationController.displayAvailableProjects(user);
        // Call controller to fetch and display projects
    }

    private static void applyForProject(User user) throws ParseException {
        System.out.println("Enter the projectID: ");
        
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
            
            //roomAvailable is a global that gets declared in viewAvailableProject
            for (Room room : roomsAvailable) { 
                if (room.getProjectID() == projectID) {
                    System.out.println("Project found: " + room.getProjectName());
                    projectFound = true;
                    ApplicationController applicationController = new ApplicationController();
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
    }

    private static void viewMyApplication(User user) {
       // System.out.println("[DEBUG] Viewing your application...");
        ApplicationController applicationController = new ApplicationController();
        applicationController.viewMyApplication(user);
        
    }

    private static void withdrawApplication(User user) {
        System.out.println("[DEBUG] Withdrawing application...");
        // Call controller to withdraw
    }

    private static void manageEnquiries(User user) {
        System.out.println("[DEBUG] Managing enquiries...");
        // Submenu for add/view/delete enquiry
    }
}
