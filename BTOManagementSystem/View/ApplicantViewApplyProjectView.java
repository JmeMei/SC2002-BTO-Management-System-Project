package BTOManagementSystem.View;

import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Project;

import java.util.List;
import java.util.Scanner;

public class ApplicantViewApplyProjectView {

    private Scanner scanner = new Scanner(System.in);

    public String promptApplicantChooseProject() {
        System.out.println("Enter the Project Name: ");
        String projectName = scanner.nextLine();
        return projectName;
    }

    public FlatType PromptAvailableFlatTypesforProject(Project project, List<FlatType> availableTypes) {
        System.out.println("You are eligible for the following flat types for this project:");
        for (FlatType type : availableTypes) {
            if (FlatType.fromString(project.getType1()) == type) {
                System.out.println(project.getType1() + " units: " + project.getType1_numofunits() +
                        " (Price: $" + (int) project.getType1_sellingprice() + ")");
            } else if (FlatType.fromString(project.getType2()) == type) {
                System.out.println(project.getType2() + " units: " + project.getType2_numofunits() +
                        " (Price: $" + (int) project.getType2_sellling_price() + ")");
            }
        }

        FlatType chosenType = null;
        while (chosenType == null) {
            System.out.print("Choose flat type (e.g., 2-Room or 3-Room): ");
            String roomTypeInput = scanner.nextLine().trim();
            FlatType inputType = FlatType.fromString(roomTypeInput);

            if (inputType != null && availableTypes.contains(inputType)) {
                chosenType = inputType;
            } else {
                System.out.println("Invalid flat type. Please choose from the available types.");
            }

        }
        return chosenType;
    }


    public void ApplySuccessMessage(){
        System.out.print("Successfully applied. Now pending.");
    }

    public void AppliedBeforeMessage(){
        System.out.print("You have already applied for this project before.");
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