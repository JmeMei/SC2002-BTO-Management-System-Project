package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Controller.ProjectListController;
import BTOManagementSystem.Controller.ReceiptController;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import java.util.Scanner;

public class HDBOfficerView extends ApplicantView{
     private static final Scanner scanner = new Scanner(System.in);

    public void showMenu(HDBOfficer officer) {

        ApplicationController applicationController = new ApplicationController();
        ProjectListController projectListController = new ProjectListController();
        HDBOfficerEnquiryView officerEnquiryView = new HDBOfficerEnquiryView();

        int option = 0;

        while (option != 9) {
            System.out.println("\n=== HDB Officer Dashboard ===");
            System.out.println("Welcome, Officer " + officer.getName());
            System.out.println("1. View Available Projects");
            System.out.println("2. Apply for a Project");
            System.out.println("3. View My Application");
            System.out.println("4. Withdraw My Application");
            System.out.println("5. Assigned Project Management");
            System.out.println("6. Enquiry Management");
            System.out.println("7. View Bookings");
            System.out.println("8. Logout");
            System.out.println("9. Exit");
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

            //Controller
            ReceiptController receiptController = new ReceiptController();

            switch (option) {
                case 1: // display projects
                    applicationController.displayAvailableProjects(this,officer);
                    break;
                case 2: // apply for projects
                    applicationController.applyProject(this,applyView,officer);
                    break;
                case 3: // view application
                    applicationController.viewMyApplication(this,manageApplicationView,officer);
                    break;
                case 4: // withdraw application
                    applicationController.withdrawApplication(this,manageApplicationView,officer);
                    break;
                case 5: // manage assigned project
                    // Check if Officer is assigned to a project
                    String projectName = projectListController.getApprovedProjectName(officer);
                    if(projectName != null){
                        assignedProjectView.showMenu(officer,projectName);
                    }else{
                        System.out.println("You are not assigned to any project.");
                    }
                    break;
                case 6: // enquiry management
                    officerEnquiryView.showEnquiryMenu(officer);
                    break;
                case 7:
                    receiptController.OfficerViewReciept(this, receiptsView);
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
