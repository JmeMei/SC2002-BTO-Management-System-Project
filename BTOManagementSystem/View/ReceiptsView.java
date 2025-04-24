package BTOManagementSystem.View;

import BTOManagementSystem.Model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ReceiptsView {

    private static Scanner sc = new Scanner(System.in);

    public int PromptFilters(){

        System.out.print(
                "Select filter you wish to use:" +
                "\n1. Martial Status"
            + "\n2. Flat Type"
        + "\n3. Age Range"
        + "\n4. Project Name"
                        + "\n5. Show ALL"
        + "\nEnter choice:");

        int option = sc.nextInt();
        sc.nextLine();

        return option;
    }

    public String promptFilterValueForMartialStatus() {

        System.out.print("1. Married\n" +
                "2. Single\n"
        + "Enter choice:");

        int option = sc.nextInt();

        if (option == 1){
            return "Married";

        }else if (option == 2){
            return "Single";
        }

        return null;
    }

    public String promptFilterValueForFlatType() {

        System.out.print("1. Two-Room\n"
        + "2. Three-Room\n"
        +  "3.Select Option: ");

        int option = sc.nextInt();

        if (option == 1){
            return "2-Room";

        }else if (option == 2){
            return "3-Room";
        }

        return null;
    }

    public  String promptFilterValueForAgeRange() {

        System.out.print("Enter LowerBound: ");
        String lowerBound = sc.nextLine();

        System.out.print("Enter UpperBound: ");
        String upperBound = sc.nextLine();

        return lowerBound + upperBound;
    }

    public String promptFilterValueForProjectName() {
        System.out.print("Enter Project Name: ");
        String projectName = sc.nextLine();
        return projectName;
    }

    public void DisplayReceipts(ArrayList<Receipt> receipts, String[] headers) {

        for (String header : headers){
            System.out.print(String.format("%-30s", header ).toUpperCase());
        }

        System.out.print("\n");
        System.out.println("=".repeat(30*6));
        for (Receipt r : receipts){

            System.out.print(String.format("%-30s", r.getApplicantName() ));
            System.out.print(String.format("%-30s", r.getApplicantNRIC() ));
            System.out.print(String.format("%-30s", r.getMaritalStatus() ));
            System.out.print(String.format("%-30s", r.getFlatType() ));
            System.out.print(String.format("%-30s", r.getAge() ));
            System.out.print(String.format("%-30s", r.getProjectName() ));
            System.out.print("\n");
        }

    }

    public void invalid_input_message(){
        System.out.print("Invalid option! ");
    }


}
