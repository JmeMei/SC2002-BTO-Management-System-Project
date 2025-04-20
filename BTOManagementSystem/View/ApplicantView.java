package BTOManagementSystem.View;

import java.util.List;
import java.util.Scanner;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ApplicationController;
import BTOManagementSystem.Controller.PasswordController;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.Applicant;

public class ApplicantView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<Project> projectsAvailable; //Global static variable to store the available rooms

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
                   applicationController.displayAvailableProjects(user);
                    break;
                case 2:
                    applicationController.displayAvailableProjects(user);
                    System.out.println("Enter the Project Name: ");
                    // Read User Input
                    String projectName = scanner.nextLine();
                    boolean projectFound = false;

                    if (applicationController.project_Exist(projectName, user)) {
                        System.out.println("Project found: " + projectName);

                        // Get available flat types for that project
                        List<FlatType> availableTypes = applicationController.getAvailableFlatTypes(projectName, user);

                        System.out.println("Available flat types for project " + projectName);
                        for (FlatType type : availableTypes) {
                            System.out.println("- " + type.getDisplayName());
                        }

                        // Loop Until Chosen Flat Type has been input
                        FlatType chosenType = null;
                        while (chosenType == null) {
                            System.out.print("Choose flat type (e.g., 2-Room or 3-Room): ");

                            // Get User Input for the Flat Type to Apply for
                            String roomTypeInput = scanner.nextLine();
                            FlatType inputType = FlatType.fromString(roomTypeInput);

                            if (inputType != null && availableTypes.contains(inputType)) {
                                chosenType = inputType;
                            } else {
                                System.out.println("Invalid flat type. Please choose from the available types.");
                            }
                        }

                        System.out.println("You selected: " + chosenType.getDisplayName());
                        applicationController.applyForProject(user, projectName, chosenType);
                    } else {
                        System.out.println("No project found with that name.");
                    }
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
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


}