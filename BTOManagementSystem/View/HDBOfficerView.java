package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Model.Roles.HDBOfficer;

import java.util.Scanner;

public class HDBOfficerView {
     private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(HDBOfficer officer) {

        ApplicationController applicationController = new ApplicationController();

        int option = 0;

        while (option != 9) {
            System.out.println("\n=== HDB Officer Dashboard ===");
            System.out.println("Welcome, Officer " + officer.getName());
            System.out.println("1. View Available Projects");
            System.out.println("2. Apply for a Project");
            System.out.println("3. View My Application");
            System.out.println("4. Withdraw My Application");
            System.out.println("5. Enquiry Management");
            System.out.println("6. View Applicants");
            System.out.println("7. Book Flat for Applicant");
            System.out.println("8. Logout");
            System.out.println("9. Exit");
            System.out.print("Enter your option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (option) {
                case 1: // display projects
                    applicationController.displayAvailableProjects(officer);
                    break;
                case 2: // apply for projects
                    applicationController.displayAvailableProjects(officer);
                    applicationController.applyForProject(officer, null); // to implement
                    break;
                case 3: // view application
                    applicationController.viewMyApplication(officer);
                    break;
                case 4: // withdraw application
                    applicationController.withdrawApplication(officer);
                    break;
                case 5: // enquiry management
                    //enquiryController.viewEnquiry; to implement
                    break;
                case 6: // view applicants for managed project
                    //projectController.viewProject(officer);
                    break;
                case 7: // book flat for applicant
                    break;
                case 8: // logout
                    System.out.println(officer.getName() + " logging out...");
                    App.main(null);
                    return;
                case 9: // exit program
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
