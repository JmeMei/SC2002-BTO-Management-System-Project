package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.OfficerRegistrationController;
import BTOManagementSystem.Controller.ProjectListController;
import BTOManagementSystem.Model.Roles.HDBManager;
import java.util.Scanner;

public class HDBManagerView {
    private static final Scanner scanner = new Scanner(System.in);



    public void showMenu() {

        System.out.println("\n=== HDB Manager Dashboard ===");
        System.out.println("Welcome, " + App.userSession.getNric());
        System.out.println("1. Create Project");
        System.out.println("2. Edit");
        System.out.println("3. Delete a Project");
        System.out.println("4. View Projects");
        System.out.println("5. View officer ALL registration requests");
        System.out.println("6. Approve Officer Registrations");


        System.out.print("Enter your option: ");
        int option = scanner.nextInt();

        ProjectListController controller = new ProjectListController();
        OfficerRegistrationController officerRegistrationController = new OfficerRegistrationController();


        //views
        HDBManagerCreateProjectView createView = new HDBManagerCreateProjectView();

        HDBManagerEditProjectView editView = new HDBManagerEditProjectView();

        HDBManagerDeleteProjectView deleteView = new HDBManagerDeleteProjectView();

        HDBManagerViewProjectsView viewProjectsView = new HDBManagerViewProjectsView();

        HDBManagerApproveOfficerView approveOfficerView = new HDBManagerApproveOfficerView();
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
