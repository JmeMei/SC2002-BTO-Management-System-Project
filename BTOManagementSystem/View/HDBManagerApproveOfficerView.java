package BTOManagementSystem.View;

import BTOManagementSystem.Model.OfficerRegistrationRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class HDBManagerApproveOfficerView {

    private Scanner scanner = new Scanner(System.in);

    public int Prompt(){


        System.out.println("1. View Approved Requests");
        System.out.println("2. View Pending Requests");
        System.out.println("3. View ALL Requests");
        System.out.print("Enter your choice : ");

        int filter = scanner.nextInt();

        return filter;

    }

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

    public String[] PromptNameOfOfficerToApprove(){

        System.out.println("Enter Name of officer to approve: ");
        String Name = scanner.nextLine();
        System.out.println("Enter Corresponding Project Name: ");
        String ProjectName = scanner.nextLine();
        String[] Data = {Name,ProjectName};

        return Data;
    }

    public void RequestDoesNotExistMessage(){
        System.out.println("Error! Record does not exist! ");
    }

    public void SuccessMessage(){
        System.out.println("Successfully Approved! ");
    }

    public void SlotFullErrorMessage(){
        System.out.println("Officer Slots are full!");
    }

    public void NotManagerErrorMessage(){
        System.out.println("You are not the manager for the project! unable to approve");
    }
}
