package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.*;

import java.util.Scanner;

public class HDBManagerView {
    private static final Scanner scanner = new Scanner(System.in);


    // need HDBManager manager for enquiry use
    public void showMenu() {

        System.out.println("\n=== HDB Manager Dashboard ===");
        System.out.println("Welcome, " + App.userSession.getName());
        System.out.println("1. Create Project");
        System.out.println("2. Edit");
        System.out.println("3. Delete a Project");
        System.out.println("4. View Projects");
        System.out.println("5. View officer ALL registration requests");
        System.out.println("6. Approve Officer Registrations");
        System.out.println("7. Enquiry Management"); // for testing
        System.out.println("8. View Receipts");
        System.out.println("9. Handle Withdrawal Requests");
        System.out.println("10. View BTO applications");
        System.out.println("11. Approve BTO application");
        System.out.println("12. Change Password");
        System.out.println("13. Logout");

        System.out.print("Enter your option: ");
        int option = scanner.nextInt();

        ProjectListController controller = new ProjectListController();
        OfficerRegistrationController officerRegistrationController = new OfficerRegistrationController();
        ReceiptController receiptController = new ReceiptController();
        WithdrawalRequestController  withdrawalRequestController = new WithdrawalRequestController();
        ApplicationStatusController applicationStatusController = new ApplicationStatusController();

        //views
        HDBManagerCreateProjectView createView = new HDBManagerCreateProjectView();

        HDBManagerEditProjectView editView = new HDBManagerEditProjectView();

        HDBManagerDeleteProjectView deleteView = new HDBManagerDeleteProjectView();

        HDBManagerViewProjectsView viewProjectsView = new HDBManagerViewProjectsView();

        HDBManagerApproveOfficerView approveOfficerView = new HDBManagerApproveOfficerView();

        HDBManagerEnquiryView enquiryView = new HDBManagerEnquiryView();

        ReceiptsView receiptsView = new ReceiptsView();

        HDBManagerWithdrawalRequestView withdrawalRequestView = new HDBManagerWithdrawalRequestView();

        HDBManagerApproveBTOApplicationView approveBTOApplicationView = new HDBManagerApproveBTOApplicationView();


        switch (option) {

            case 1:

                controller.CreateNewProject(this, createView );
                break;

            case 2:

                controller.EditProject(this,editView);
                break;

            case 3:
                controller.DeleteProject(this, deleteView);
                break;

            case 4:
                controller.ViewProjects(this, viewProjectsView);
                break;
            case 5:
                officerRegistrationController.ViewApproveRequests(this, approveOfficerView);
                break;

            case 6:
                officerRegistrationController.ApproveARequest(this, approveOfficerView);
                break;
            
            case 7:
                enquiryView.showEnquiryMenu(App.userSession);

            case 8:
                receiptController.ViewReceipts(this, receiptsView);

            case 9:
                withdrawalRequestController.HandleWithdrawalRequest(this, withdrawalRequestView);

            case 10:
                applicationStatusController.ViewApplication(this, approveBTOApplicationView);

            case 11:
                applicationStatusController.approveApplication(this, approveBTOApplicationView);

            case 12:
                System.out.print("Enter the new password: ");
                String newPassword = scanner.nextLine();
                boolean success = PasswordController.changePassword(App.userSession.getNric(),newPassword);

                if (success) {
                    System.out.print("Password updated. Please log in again.\n");
                    App.main(null);
                    return; // Exit menu to force re-login
                } else {
                    System.out.println("Failed to update password."); //May happen if the FILE_PATH is wrong
                    //showMenu();
                }
                break;
            case 13:
                System.out.println("logging out...");
                App.main(null);
        }















        /*
        int option = 0;
        while (option > 6 || option < 1) {

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");

            }

        }
        return option; */
    }

}
