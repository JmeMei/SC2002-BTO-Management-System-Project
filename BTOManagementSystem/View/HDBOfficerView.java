package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.Model.Room;

import java.util.List;
import java.util.Scanner;

public class HDBOfficerView {
     private static final Scanner scanner = new Scanner(System.in);

    public static List<Room> roomsAvailable; //Global static variable to store the available rooms

    public static void showMenu(HDBOfficer officer) {
        ApplicationController applicationController = new ApplicationController();
        int option = 0;

        while (option != 6) {
            System.out.println("\n=== HDB Officer Dashboard ===");
            // Applicant Capabilities
            System.out.println("1. View Available Projects");
            System.out.println("2. Apply for a Project");
            System.out.println("3. View My Application");
            System.out.println("4. Withdraw My Application");
            System.out.println("5. Enquiry Management");

            // HDB Officer Capabilities
            System.out.println("6. Register to handle a Project");
            System.out.println("7. View Project Registration Status");
            System.out.println("8. Project Management");

            // User Capabilities
            System.out.println("9. Change Password");
            System.out.println("10. Logout");
            System.out.println("11. Exit");

            System.out.print("Enter your option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("[DEBUG] Viewing Available Projects...");
                    roomsAvailable = applicationController.displayAvailableProjects(officer);
                    break;
                case 2:
                    System.out.println("[DEBUG] Applying for a Project");
                    break;
                case 3:
                    System.out.println("[DEBUG] Viewing My Application");
                    break;
                case 4:
                    System.out.println("[DEBUG] Withdrawing My Application");
                    break;
                case 5:
                    System.out.println("[DEBUG] Enquiry Management System");
                    return;
                case 6:
                    System.out.println("[DEBUG] Register to handle a Project");
                    break;
                case 7:
                    System.out.println("[DEBUG] View Project Registration Status");
                    break;
                case 8:
                    System.out.println("[DEBUG] Project Management");
                    break;
                case 9:
                    System.out.println("[DEBUG] Change Password");
                    break;
                case 10:
                    System.out.println("[DEBUG] Logout");
                    System.out.println(officer.getName() + " logging out...");
                    App.main(null);
                    break;
                case 11:
                    System.out.println("[DEBUG] Exit");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
