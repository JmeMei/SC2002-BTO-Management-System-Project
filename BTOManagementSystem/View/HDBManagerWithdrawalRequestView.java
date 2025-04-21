package BTOManagementSystem.View;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.WithdrawalRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class HDBManagerWithdrawalRequestView {

    private static Scanner scanner = new Scanner(System.in);

    void ShowRequests(ArrayList<WithdrawalRequest> requests, String[] headers) {

        for (String header : headers){
            System.out.print(String.format("%-30s", header ));
        }

        System.out.print("\n");

        for (WithdrawalRequest request : requests){

            System.out.print(String.format("%-30s", request.getName()));
            System.out.print(String.format("%-30s", request.getNric()) );
            System.out.print(String.format("%-30s", request.getProjectName()));
            System.out.print("\n");
        }

    }

    public String PromptForWithdrawal() {

        System.out.print("Enter Applicant Name to withdraw: ");
        String data = scanner.nextLine();

        return data;
    }

    public void SuccessMessage() {
        System.out.print("Sucessfully withdrawn!");
    }

    public void FailMessage() {
        System.out.print("Record does not exist!");
    }

}
