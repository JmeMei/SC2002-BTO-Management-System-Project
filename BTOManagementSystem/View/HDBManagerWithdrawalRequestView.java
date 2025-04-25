package BTOManagementSystem.View;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.WithdrawalRequest;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * View class for HDB Managers to manage withdrawal requests from applicants.
 * <p>
 * Provides methods to:
 * <ul>
 *     <li>Display a list of withdrawal requests</li>
 *     <li>Prompt the manager to process a withdrawal request</li>
 *     <li>Display success or failure messages after processing</li>
 * </ul>
 */
public class HDBManagerWithdrawalRequestView {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Displays a formatted table of withdrawal requests, excluding the "processed" status column.
     *
     * @param requests A list of {@link WithdrawalRequest} objects to be displayed.
     * @param headers  An array of column headers to label the request data.
     */
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

    /**
     * Prompts the user (manager) to input the NRIC and project name for a withdrawal request to process.
     *
     * @return A String array containing the applicant's NRIC and the project name.
     */
    public String[] PromptForWithdrawal() {

        String[] data = {"" , ""};
        System.out.print("Enter Applicant NRIC to withdraw: ");
        data[0] = scanner.nextLine();

        System.out.print("Enter corresponding Project Name: ");
        data[1] = scanner.nextLine();

        return data;
    }

    /**
     * Displays a success message when a withdrawal is successfully processed.
     */
    public void SuccessMessage() {
        System.out.print("Sucessfully withdrawn!");
    }

    /**
     * Displays a failure message when a withdrawal request record does not exist or fails to process.
     */
    public void FailMessage() {
        System.out.print("Record does not exist!");
    }

}
