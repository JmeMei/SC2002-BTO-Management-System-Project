package BTOManagementSystem.View;

import java.util.List;
import java.util.Scanner;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Controller.PasswordController;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Room;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.Enum.FlatType;

public class ApplicantView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<Room> roomsAvailable; //Global static variable to store the available rooms

    public void showMenu(Applicant user)  { //Applicant view has a user

        ApplicationController applicationController = new ApplicationController();
        ApplicantEnquiryView enquiryView = new ApplicantEnquiryView();
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
                   roomsAvailable = applicationController.displayAvailableProjects(user);
                    break;
                case 2:
                    roomsAvailable = applicationController.displayAvailableProjects(user);
                    applyForProject(user); // TODO shift the applyproject to ApplicationController
                    break;
                case 3:
                    applicationController.viewMyApplication(user);
                    break;
                case 4:
                    applicationController.withdrawApplication(user); // add the call to the applicationController
                    break;
                case 5:
                    enquiryView.showEnquiryMenu(user);
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

        System.out.println("Enter the Project Name: ");
        String projectName = scanner.nextLine(); // Read as String
        boolean projectFound = false;

        while(!projectFound){
            // Make sure the user enters an integer
            //roomAvailable is a global that gets declared in viewAvailableProject
            for (Room room : roomsAvailable) { 
                if (room.getProjectName().equalsIgnoreCase(projectName)) {
                    System.out.println("Project found: " + room.getProjectName());
                    projectFound = true;
                    ApplicationController applicationController = new ApplicationController();
                    applicationController.applyForProject(user, projectName);
                    // You can now proceed with applying logic
                    break;
                }
            }
            if (!projectFound) {
                System.out.println("No project found with that name.");
                return;
            }
        }
        
    }

    public static FlatType chooseRoom() {
        while (true) {
            System.out.println("Choose room type: ");
            System.out.println("1. 2-Room");
            System.out.println("2. 3-Room");
            System.out.print("Enter your choice (1 or 2): ");
    
            String input = scanner.nextLine();
    
            switch (input.trim()) {
                case "1":
                    return FlatType.TWOROOM;
                case "2":
                    return FlatType.THREEROOM;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}