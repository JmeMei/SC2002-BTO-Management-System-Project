package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.*;
import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.HDBOfficer;

import java.util.List;
import java.util.Scanner;
/**
 * View class for HDB Officers to manage operations related to their assigned project.
 * <p>
 * Features include:
 * <ul>
 *     <li>Viewing project details</li>
 *     <li>Viewing all applicant statuses for the project</li>
 *     <li>Booking flats for applicants and generating receipts</li>
 * </ul>
 */
public class HDBOfficerAssignedProjectView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the main menu for the officer's assigned project and handles user interactions.
     *
     * @param officer     The logged-in HDB Officer
     * @param projectName The name of the project assigned to the officer
     */
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
            System.out.println("2. View All Applicant Status");
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
                    // View All Applicant Status
                    applicationController.getApplicantProjectStatus(this,projectName,officer);
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
                    officerView.showOfficerMenu(officer);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 6);
    }

    /**
     * Displays detailed information about a specific project.
     *
     * @param project The {@link Project} to display details of.
     */
    public void showProjectDetails(Project project) {
        System.out.println("========== Project Details ==========");
        System.out.println("Project Name       : " + project.getName());
        System.out.println("Neighborhood       : " + project.getNeighbourhood());
        System.out.println("Application Period : " + project.getOpeningDate() + " to " + project.getClosingDate());

        System.out.println(project.getType1() + " Units       : " + project.getType1_numofunits() + " total (Price: $" + project.getType1_sellingprice() + ")");
        System.out.println(project.getType2() + " Units       : " + project.getType2_numofunits() + " total (Price: $" + project.getType2_selling_price() + ")");
        System.out.println("Assigned Manager   : " + project.getManager());
        System.out.println("Assigned Officers  : " + project.get_officers().toString());
        System.out.println("Officer Slots      : " + project.getOfficerslots());
        System.out.println("Visibility         : " + (project.getVisibility() == 1 ? "Visible" : "Not Visible"));
    }

    /**
     * Displays a single applicant's application status.
     *
     * @param status The {@link ApplicantProjectStatus} of the applicant.
     */
    public void DisplayApplicationStatus(ApplicantProjectStatus status) {
        System.out.println("\n=== Application Details for Applicant ===");
        System.out.println("Name: " + status.getName());
        System.out.println("NRIC: " + status.getNric());
        System.out.println("Project Name: " + status.getProjectName());
        System.out.println("Flat Type: " + status.getFlatType().getDisplayName());
        System.out.println("Application Status: " + status.getApplicationStatus());
    }

    /**
     * Displays the list of all application statuses for the assigned project.
     *
     * @param statusList List of {@link ApplicantProjectStatus} objects.
     */
    public void DisplayAllApplicationStatus(List<ApplicantProjectStatus> statusList) {
        System.out.println("\n=== All Application Details for Project ===");

        for (ApplicantProjectStatus status : statusList) {
            System.out.println("--------------------------------------------------");
            System.out.println("Name: " + status.getName());
            System.out.println("NRIC: " + status.getNric());
            System.out.println("Project Name: " + status.getProjectName());
            System.out.println("Flat Type: " + status.getFlatType().getDisplayName());
            System.out.println("Application Status: " + status.getApplicationStatus());
        }
    }
    /** Displays a message when the assigned project cannot be found. */
    public void AssignedProjectNotFoundMessage(){
        System.out.println("You don't have an approved project assigned.");
    }

    /** Displays a message when no applications are found for a project. */
    public void ApplicationNotFoundMessage(){
        System.out.println("No applications found for this project.");
    }

    /** Displays a message when no applications are found for an applicant. */
    public void AssignedApplicantNotFoundMessage(){
        System.out.println("No applications found for this applicant in this project.");
    }

    /**
     * Displays a message when booking is not allowed due to an application status.
     *
     * @param status Current application status that prevents booking.
     */
    public void BookingNotAllowedMessage(String status){
        System.out.println("Booking not allowed. The applicant's application status is " + status + ".");
    }

    /** Displays a message when a booking update fails. */
    public void BookingFailedMessage(){
        System.out.println("An error occurred while trying to update the application status of an applicant");
    }

    /** Displays a message when there are no units available for a given flat type. */
    public void NoUnitsAvailable(){
        System.out.println("There are no units available for this flat type in this project");
    }

    /**
     * Prompts the officer to confirm the booking of a flat for an applicant.
     *
     * @return {@code true} if confirmed, {@code false} otherwise.
     */
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