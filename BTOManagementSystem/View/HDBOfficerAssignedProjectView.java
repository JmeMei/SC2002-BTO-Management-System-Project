package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.*;
import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.HDBOfficer;

import java.util.Scanner;

public class HDBOfficerAssignedProjectView {

    private Scanner scanner = new Scanner(System.in);


    public void showMenu(HDBOfficer officer, String projectName) {
        // Init controllers
        ProjectListController projectListController = new ProjectListController();
        ApplicationController applicationController = new ApplicationController();
        BookingController bookingController = new BookingController();
        ReceiptController receiptController = new ReceiptController();

        int option = 0;
        do {
            System.out.println("\n=== Assigned Project Management ===");
            System.out.println("1. View Project Details");
            System.out.println("2. View Applicant Status by NRIC");
            System.out.println("3. Book Flat for Applicant");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (option) {
                case 1:
                    // View project details
                    Project project = projectListController.getProjectDetails(projectName);
                    this.showProjectDetails(project);
                    break;
                case 2:
                    // View Applicant Status by NRIC
                    System.out.print("Enter Applicant NRIC:");
                    String applicantNRIC = scanner.nextLine();

                    // get Status
                    ApplicantProjectStatus status = applicationController.getApplicantProjectStatus(applicantNRIC,projectName);

                    // display status
                    this.DisplayApplicationStatus(status);
                    break;
                case 3:
                    // Get the Applicant's application
                    System.out.print("Enter Applicant NRIC for Flat Booking: ");
                    String aNRIC = scanner.nextLine();

                    // Book Flat for Applicant
                    boolean booked = bookingController.bookFlatForApplicant(this, aNRIC, projectName);

                    if (booked) {
                        System.out.println("Applicant's flat was successfully booked.");

                        // Generate Receipt
                        receiptController.generateReceipt(aNRIC,projectName);
                        System.out.println("Receipt for flat booking was successfully generated.");
                    } else {
                        System.out.println("Flat booking failed.");
                    }
                    break;
                case 4:
                    // Go back to Officer View
                    HDBOfficerView officerView = new HDBOfficerView();
                    officerView.showMenu(officer);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 6);
    }

    public void showProjectDetails(Project project) {
        System.out.println("========== Project Details ==========");
        System.out.println("Project Name      : " + project.getName());
        System.out.println("Neighborhood      : " + project.getNeighbourhood());
        System.out.println("Application Period: " + project.getOpeningDate() + " to " + project.getClosingDate());

        System.out.println(project.getType1() + " Units      : " + project.getType1_numofunits() + " remaining");
        System.out.println(project.getType2() + " Units      : " + project.getType2_numofunits() + " remaining");

        System.out.println("Assigned Manager   : " + project.getManager());
        System.out.println("Assigned Officers : " + project.get_officers().toString());
        System.out.println("Officer Slots : " + project.getOfficerslots());
        System.out.println("Visibility    : " + (project.getVisibility() == 1 ? "Visible" : "Not Visible"));
    }

    public void DisplayApplicationStatus(ApplicantProjectStatus status) {
        System.out.println("\n=== Application Details for Applicant ===");
        System.out.println("Name: " + status.getName());
        System.out.println("NRIC: " + status.getNric());
        System.out.println("Project Name: " + status.getProjectName());
        System.out.println("Flat Type: " + status.getFlatType().getDisplayName());
        System.out.println("Application Status: " + status.getApplicationStatus());
    }

    public void AssignedProjectNotFoundMessage(){
        System.out.println("You don't have an approved project assigned.");
    }

    public void ApplicationNotFoundMessage(){
        System.out.println("No applications found for this applicant");
    }

    public void AssignedApplicantNotFoundMessage(){
        System.out.println("No applications found for this applicant in this project");
    }

    public void BookingNotAllowedMessage(String status){
        System.out.println("Booking not allowed. The applicant's application status is " + status + ".");
    }

    public void BookingFailedMessage(){
        System.out.println("An error occurred while trying to update the application status of an applicant");
    }

    public void NoUnitsAvailable(){
        System.out.println("There are no units available for this flat type in this project");
    }

    public boolean PromptBookingConfirmation() {
        String confirm;
        do {
            System.out.print("Would you like to book this flat for the applicant? (Y/N): ");
            confirm = scanner.nextLine().trim();

            if (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
        }
        while (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N"));

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Booking was cancelled.");
            return false;
        }

        return true;
    }


}