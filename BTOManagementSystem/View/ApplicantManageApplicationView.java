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


    public void DisplayApplicationStatus(ApplicantProjectStatus status) {
        System.out.println("\n=== Your Application Details ===");
        System.out.println("Name: " + status.getName());
        System.out.println("NRIC: " + status.getNric());
        System.out.println("Project Name: " + status.getProjectName());
        System.out.println("Flat Type: " + status.getFlatType().getDisplayName());
        System.out.println("Application Status: " + status.getApplicationStatus());
    }

    public void ApplicationNotFoundMessage(){
        System.out.println("You have not applied for a project yet.");
    }

    public void ReqWithdrawalSuccessMessage() {
        System.out.println("Your withdrawal request has been submitted successfully.");
    }

    public void RequestedBeforeMessage() {
        System.out.println("Your withdrawal request is being processed. Please wait for approval.");
    }
}