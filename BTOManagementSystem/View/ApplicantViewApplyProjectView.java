package BTOManagementSystem.View;

import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Project;

import java.util.List;
import java.util.Scanner;
/**
 * View class that handles user interaction when an applicant is applying for a BTO project.
 * <p>
 * Provides input prompts, messages, and flat type information to guide the applicant
 * through the application process.
 */
public class ApplicantViewApplyProjectView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the applicant to enter the project name they wish to apply for.
     *
     * @return the project name entered by the applicant
     */
    public String promptApplicantChooseProject() {
        System.out.print("Enter the Project Name: ");
        String projectName = scanner.nextLine();
        return projectName;
    }

    /**
     * Prompts the user to input the desired flat type.
     *
     * @return the flat type input (e.g., "2-room", "3-room", or "c" for cancel)
     */
    public String PromptUserInputFlatType() {
        System.out.print("Choose flat type (e.g., 2-Room, 3-Room or 'C' to cancel): ");
        String roomTypeInput = scanner.nextLine().trim().toLowerCase();
        return roomTypeInput;
    }

    /**
     * Displays the available flat types for a given project that the user is eligible to apply for.
     *
     * @param project        the project being applied for
     * @param availableTypes the list of eligible {@link FlatType}s for the applicant
     */
    public void DisplayAvailableFlatTypesforProject(Project project, List<FlatType> availableTypes) {
        System.out.println("You are eligible for the following flat types for this project:");
        for (FlatType type : availableTypes) {
            if (FlatType.fromString(project.getType1()) == type) {
                System.out.println(project.getType1() + " units: " + project.getType1_numofunits() +
                        " (Price: $" + (int) project.getType1_sellingprice() + ")");
            } else if (FlatType.fromString(project.getType2()) == type) {
                System.out.println(project.getType2() + " units: " + project.getType2_numofunits() +
                        " (Price: $" + (int) project.getType2_selling_price() + ")");
            }
        }
    }

    /**
     * Displays a generic invalid input message.
     */
    public void UserInputInvalidMessage(){
        System.out.println("Invalid input. Please try again.");
    }

    /**
     * Displays an error message when the flat type entered by the user is not valid.
     */
    public void UserInputFlatTypeInvalidMessage(){
        System.out.println("Invalid flat type. Please select from the available types.");
    }

    /**
     * Informs the applicant that they cannot apply because they are an HDB officer for the project.
     */
    public void CannotApplyIfHDBOfficerMessage(){
        System.out.println("Application denied: HDB officers assigned to this project are not permitted to apply for it as applicants.");
    }

    /**
     * Informs the applicant that they cannot apply because they have a pending officer registration for the project.
     */
    public void CannotApplyIfPendingHDBOfficerMessage(){
        System.out.println("Application denied: HDB officers pending to be assigned to this project are not permitted to apply for it as applicants.");
    }

    /**
     * Displays a message indicating the user has canceled their application.
     */
    public void CancelApplyProjectMessage(){
        System.out.println("You have canceled applying for this project.");
    }

    /**
     * Displays a message indicating that the applicant has already applied for a project.
     */
    public void AppliedBeforeMessage(){
        System.out.println("You have already applied for a project before and it is being processed.");
    }

    /**
     * Confirms that the application submission was successful and is now pending approval.
     */
    public void ApplySuccessMessage(){
        System.out.println("Successfully applied. Now pending.");
    }

    /**
     * Displays a message indicating there are no available flats, even though the applicant is eligible.
     */
    public void NoAvailableFlatsMessage(){
        System.out.println("You are eligible, but there are no available flats.");
    }

    /**
     * Informs the applicant that they have already applied to this project and shows the current status.
     *
     * @param status the application status (e.g., PENDING, SUCCESSFUL)
     */
    public void ApplicationInProcessMessage(String status){
        System.out.println("You have already applied to this project and it is now currently " + status + ".");
        System.out.println("You are not able to apply to this project at this time.");
    }

    /**
     * Displays a message explaining why the user is not eligible for any flat types.
     */
    public void NotEligibleForProjectsMessage(){
        System.out.println("You are not eligible for any flat types at this time.");
        System.out.println("Singles must be 35 years or older to apply for 2-Room flats.");
        System.out.println("Married applicants must be 21 years or older to apply for 2-Room or 3-Room flats.");
    }

    /**
     * Displays a message when the project entered by the applicant is not found.
     */
    public void ProjectNotFoundMessage() {
        System.out.println("No project found with that name.");
    }
}