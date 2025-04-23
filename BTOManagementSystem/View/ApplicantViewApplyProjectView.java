package BTOManagementSystem.View;

import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Project;

import java.util.List;
import java.util.Scanner;

public class ApplicantViewApplyProjectView {

    private Scanner scanner = new Scanner(System.in);

    public String promptApplicantChooseProject() {
        System.out.print("Enter the Project Name: ");
        String projectName = scanner.nextLine();
        return projectName;
    }

    public String PromptAvailableFlatTypesforProject(Project project, List<FlatType> availableTypes) {
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

        System.out.print("Choose flat type (e.g., 2-Room, 3-Room or 'C' to cancel): ");
        String roomTypeInput = scanner.nextLine().trim().toLowerCase();
        String normInput = null;

        do {
            // Normalize common variants for flat type
            if (roomTypeInput.equals("2room") || roomTypeInput.equals("2-room")) {
                normInput = "2-Room";
            } else if (roomTypeInput.equals("3room") || roomTypeInput.equals("3-room")) {
                normInput = "3-Room";
            } else if (roomTypeInput.equals("c")) {
                normInput = "Cancel";
                break;
            } else {
                normInput = null;
                System.out.println("Invalid input. Please try again.");
            }
        } while (normInput == null);

        return normInput;
    }

    public void CannotApplyIfHDBOfficerMessage(){
        System.out.println("Application denied: HDB officers assigned to this project are not permitted to apply for it as applicants.");
    }

    public void CannotApplyIfPendingHDBOfficerMessage(){
        System.out.println("Application denied: HDB officers pending to be assigned to this project are not permitted to apply for it as applicants.");
    }

    public void CancelApplyProjectMessage(){
        System.out.println("You have canceled applying for this project.");
    }

    public void AppliedBeforeMessage(){
        System.out.println("You have already applied for a project before and it is being processed.");
    }

    public void ApplySuccessMessage(){
        System.out.println("Successfully applied. Now pending.");
    }

    public void NoAvailableFlatsMessage(){
        System.out.println("You are eligible, but there are no available flats.");
    }

    public void ApplicationInProcessMessage(String status){
        System.out.println("You have already applied to this project and it is now currently " + status + ".");
        System.out.println("You are not able to apply to this project at this time.");
    }

    public void NotEligibleForProjectsMessage(){
        System.out.println("You are not eligible for any flat types at this time.");
        System.out.println("Singles must be 35 years or older to apply for 2-Room flats.");
        System.out.println("Married applicants must be 21 years or older to apply for 2-Room or 3-Room flats.");
    }
    public void ProjectNotFoundMessage() {
        System.out.println("No project found with that name.");
    }
}