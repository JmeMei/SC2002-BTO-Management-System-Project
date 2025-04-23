package BTOManagementSystem.View;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.WithdrawalRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class HDBManagerWithdrawalRequestView {

    private static Scanner scanner = new Scanner(System.in);

    public void ShowRequests(ArrayList<WithdrawalRequest> requests, String[] headers) {

        for(int i = 0; i < headers.length - 1; i++){

            System.out.print(String.format("%-30s", headers[i] ));
        }


        System.out.print("\n");

        for (WithdrawalRequest request : requests){

            System.out.print(String.format("%-30s", request.getName()));
            System.out.print(String.format("%-30s", request.getNric()) );
            System.out.print(String.format("%-30s", request.getProjectName()));

            System.out.print("\n");
        }

    }

    public String[] PromptForWithdrawal() {

        String[] data = {"" , ""};
        System.out.print("Enter Applicant NRIC to withdraw: ");
        data[0] = scanner.nextLine();

        System.out.print("Enter corresponding Project Name: ");
        data[1] = scanner.nextLine();

        return data;
    }

    public void SuccessMessage() {
        System.out.print("Sucessfully withdrawn!");
    }

    public void FailMessage() {
        System.out.print("Record does not exist!");
    }

}
