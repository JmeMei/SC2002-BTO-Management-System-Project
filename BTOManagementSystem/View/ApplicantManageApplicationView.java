package BTOManagementSystem.View;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.WithdrawalRequest;

import java.util.List;
import java.util.Scanner;

public class ApplicantManageApplicationView {

    private Scanner scanner = new Scanner(System.in);


    public void DisplayApplicationStatus(List<ApplicantProjectStatus> statusList) {
        System.out.println("\n=== Your Application Details ===");

        for (ApplicantProjectStatus status : statusList) {
            System.out.println("--------------------------------------------------");
            System.out.println("Name: " + status.getName());
            System.out.println("NRIC: " + status.getNric());
            System.out.println("Project Name: " + status.getProjectName());
            System.out.println("Flat Type: " + status.getFlatType().getDisplayName());
            System.out.println("Application Status: " + status.getApplicationStatus());
        }
    }

    public void ApplicationNotFoundMessage(){
        System.out.println("You have not applied for a project yet.");
    }

    public void ReqWithdrawalSuccessMessage() {
        System.out.println("Your withdrawal request has been submitted successfully.");
    }

    public void ApplicationsToWithdrawNotFoundMessage(){
        System.out.println("You do not have any projects to withdraw from.");
    }

    public void RequestedBeforeMessage() {
        System.out.println("Your withdrawal request is currently being processed. Please wait for approval.");
    }

    public boolean PromptWithdrawConfirmation() {
        String confirm;
        do {
            System.out.print("Would you like to withdraw from this project? (Y/N): ");
            confirm = scanner.nextLine().trim();

            if (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
        }
        while (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N"));

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Application withdrawal was cancelled.");
            return false;
        }

        return true;
    }
}