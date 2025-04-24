package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.*;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import java.util.Scanner;
/**
 * The {@code HDBOfficerView} class represents the main dashboard and interface
 * for an HDB Officer within the BTO Management System.
 * <p>
 * HDBOfficerView extends {@link ApplicantView} to inherit basic applicant-related
 * features, and provides additional functionality specific to HDB Officers such as:
 * <ul>
 *   <li>Project registration and approval</li>
 *   <li>Assigned project management</li>
 *   <li>Viewing and replying to enquiries</li>
 *   <li>Viewing bookings and managing passwords</li>
 * </ul>
 */
public class HDBOfficerView extends ApplicantView{
     private static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the menu for the HDB Officer and handles user input for different actions.
     *
     * @param officer The currently logged-in HDB Officer.
     */
    public void showOfficerMenu(HDBOfficer officer) {
        int option = 0;

        while (option != 9) {
            System.out.println("\n=== HDB Officer Dashboard ===");
            System.out.println("Welcome, Officer " + officer.getName() +  "[" + officer.getAge()  + "," + officer.getMaritalStatus() +"]");
            System.out.println("1. View Available Projects");
            System.out.println("2. Apply for a Project");
            System.out.println("3. View My Application");
            System.out.println("4. Withdraw My Application");
            System.out.println("5. Register to handle a Project");
            System.out.println("6. View Project Registration Status");
            System.out.println("7. Assigned Project Management");
            System.out.println("8. Enquiry Management");
            System.out.println("9. View Bookings");
            System.out.println("10. Change Password");
            System.out.println("11. Logout");
            System.out.println("12. Exit");
            System.out.print("Enter your option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            //views
            ApplicantViewApplyProjectView applyView = new ApplicantViewApplyProjectView();
            ApplicantManageApplicationView manageApplicationView = new ApplicantManageApplicationView();
            HDBOfficerAssignedProjectView assignedProjectView = new HDBOfficerAssignedProjectView();
            ReceiptsView receiptsView = new ReceiptsView();
            HDBOfficerProjectRegistrationView registrationView = new HDBOfficerProjectRegistrationView();

            //Controller
            ReceiptController receiptController = new ReceiptController();
            ApplicationController applicationController = new ApplicationController();
            OfficerRegistrationController registrationController = new OfficerRegistrationController();
            ProjectListController projectListController = new ProjectListController();
            HDBOfficerEnquiryView officerEnquiryView= new HDBOfficerEnquiryView();

            switch (option) {
                case 1: // display projects
                    applicationController.displayAvailableProjects(this,officer);
                    break;
                case 2: // apply for projects
                    applicationController.applyProject(this,applyView,officer);
                    break;
                case 3: // view application
                    applicationController.viewMyApplications(this,manageApplicationView,officer);
                    break;
                case 4: // withdraw application
                    applicationController.withdrawApplication(this,manageApplicationView,officer);
                    break;
                case 5: // Register to handle project
                    registrationController.CreateARequest(this,registrationView,officer);
                case 6: // View Registration Status
                    registrationController.ViewOfficerRequest(this,registrationView,officer);
                case 7: // manage assigned project
                    // Check if Officer is assigned to a project
                    String projectName = projectListController.getApprovedProjectName(officer);
                    if(projectName != null){
                        assignedProjectView.showMenu(officer,projectName);
                    }else{
                        System.out.println("You are not assigned to any project.");
                    }
                    break;
                case 8: // enquiry management
                    officerEnquiryView.showEnquiryMenu(officer);
                    break;
                case 9:
                    receiptController.OfficerViewReciept(this, receiptsView);
                    break;
                case 10:
                    System.out.print("Enter the new password: ");
                    String newPassword = scanner.nextLine();
                    boolean success = PasswordController.changePassword(App.userSession.getNric(),newPassword);

                    if (success) {
                        System.out.print("Password updated. Please log in again.\n");
                        App.main(null);
                        return; // Exit menu to force re-login
                    } else {
                        System.out.println("Failed to update password."); //May happen if the FILE_PATH is wrong
                    }
                    break;
                case 11: // logout
                    System.out.println(officer.getName() + " logging out...");
                    App.main(null);
                    return;
                case 12: // exit program
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
