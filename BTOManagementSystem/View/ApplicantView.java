package BTOManagementSystem.View;

import java.util.List;
import java.util.Scanner;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Controller.PasswordController;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.User;

public class ApplicantView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<Project> projectsAvailable; //Global static variable to store the available rooms

    public void showApplicantMenu(User user)  { //Applicant view has a user

        ApplicationController applicationController = new ApplicationController();
        ApplicantEnquiryView enquiryView = new ApplicantEnquiryView();
        int option = 0;

        while (option != 8) {
            //System.out.println("Welcome, " + user.getName());
            System.out.println("\n=== Applicant Dashboard ===");
            System.out.println("Welcome, " + App.userSession.getName() + "[" + App.userSession.getAge()  + "," + App.userSession.getMaritalStatus() +"]");
            System.out.println("1. View Available Projects");
            System.out.println("2. Apply for a Project");
            System.out.println("3. View My Applications");
            System.out.println("4. Withdraw An Application");
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

            //views
            ApplicantViewApplyProjectView applyView = new ApplicantViewApplyProjectView();
            ApplicantManageApplicationView manageApplicationView = new ApplicantManageApplicationView();

            switch (option) {
                case 1:
                    applicationController.displayAvailableProjects(this,user);
                    break;
                case 2:
                    applicationController.applyProject(this,applyView,user);
                    break;
                case 3:
                    applicationController.viewMyApplications(this,manageApplicationView, user);
                    break;
                case 4:
                    applicationController.withdrawApplication(this,manageApplicationView,user); // add the call to the applicationController
                    break;
                case 5:
                    enquiryView.showEnquiryMenu(user);
                    break;
                case 6:
                    System.out.print("Enter the new password: ");
                    String newPassword = scanner.nextLine();
                    boolean success = PasswordController.changePassword(user.getNric(),newPassword);

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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}