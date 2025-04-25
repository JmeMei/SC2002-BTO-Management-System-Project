package BTOManagementSystem.View;

import BTOManagementSystem.Model.OfficerRegistrationRequest;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * View class used by HDB Managers to handle officer registration requests.
 * <p>
 * Allows viewing, filtering, and approving officer project registration requests.
 */
public class HDBManagerApproveOfficerView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to select a filter option for viewing officer registration requests.
     * <p>The options include:</p>
     * <ul>
     *   <li>1 - View Approved Requests</li>
     *   <li>2 - View Pending Requests</li>
     *   <li>3 - View All Requests</li>
     * </ul>
     *
     * @return an integer representing the user's chosen filter option.
     */
    public int Prompt(){


        System.out.println("1. View Approved Requests");
        System.out.println("2. View Pending Requests");
        System.out.println("3. View ALL Requests");
        System.out.print("Enter your choice : ");

        int filter = scanner.nextInt();

        return filter;

    }

    /**
     * Displays a list of officer registration requests in a tabular format.
     *
     * @param requests the list of {@link OfficerRegistrationRequest} to display
     * @param headers  the column headers for the display
     */
    public void DisplayRequests(ArrayList<OfficerRegistrationRequest> requests, String[] headers){

        for (String header : headers){
            System.out.print(String.format("%-30s", header ));
        }

        System.out.print("\n");

         for (OfficerRegistrationRequest  request : requests){

             System.out.print(String.format("%-30s", request.getOfficerName() ));
             System.out.print(String.format("%-30s", request.getNRIC()) );
             System.out.print(String.format("%-30s", request.getProjectName() ));
             System.out.print(String.format("%-30s", request.getStatus() ));
             System.out.print("\n");
         }


    }

    /**
     * Prompts the manager to enter the officer name and corresponding project name
     * for approval of a registration request.
     *
     * @return a String array containing:
     *         <ul>
     *             <li>[0] Officer name</li>
     *             <li>[1] Project name</li>
     *         </ul>
     */
    public String[] PromptNameOfOfficerToApprove(){

        System.out.println("Enter Name of officer to approve: ");
        String Name = scanner.nextLine();
        System.out.println("Enter Corresponding Project Name: ");
        String ProjectName = scanner.nextLine();
        String[] Data = {Name,ProjectName};

        return Data;
    }

    /**
     * Displays a message indicating that the registration request was not found.
     */
    public void RequestDoesNotExistMessage(){
        System.out.println("Error! Record does not exist! ");
    }

    /**
     * Displays a success message after a successful approval.
     */
    public void SuccessMessage(){
        System.out.println("Successfully Approved! ");
    }

    /**
     * Displays an error message indicating that officer slots for the project are full.
     */
    public void SlotFullErrorMessage(){
        System.out.println("Officer Slots are full!");
    }

    /**
     * Displays a message indicating that the user is not the manager for the project,
     * hence cannot approve the registration.
     */
    public void NotManagerErrorMessage(){
        System.out.println("You are not the manager for the project! unable to approve");
    }
}
