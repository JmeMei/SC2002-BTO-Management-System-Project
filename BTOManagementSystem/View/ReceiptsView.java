package BTOManagementSystem.View;

import BTOManagementSystem.Model.*;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * The {@code ReceiptsView} class handles the presentation layer for viewing
 * and filtering receipt records in the BTO Management System.
 * <p>
 * It provides methods for prompting the user for various filter options
 * and displaying formatted receipt data.
 */
public class ReceiptsView {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user to select a filter type for viewing receipts.
     *
     * @return an integer representing the user's selected filter option.
     */
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

    /**
     * Prompts the user to select a marital status filter.
     *
     * @return the selected marital status as a string ("Married" or "Single").
     */
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

    /**
     * Prompts the user to select a flat type filter.
     *
     * @return the selected flat type as a string ("2-Room" or "3-Room").
     */
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

    /**
     * Prompts the user to enter an age range filter.
     *
     * @return a string representing the concatenated lower and upper bounds.
     */
    public  String promptFilterValueForAgeRange() {

        System.out.print("Enter LowerBound: ");
        String lowerBound = sc.nextLine();

        System.out.print("Enter UpperBound: ");
        String upperBound = sc.nextLine();

        return lowerBound + upperBound;
    }

    /**
     * Prompts the user to enter a project name filter.
     *
     * @return the entered project name as a string.
     */
    public String promptFilterValueForProjectName() {
        System.out.print("Enter Project Name: ");
        String projectName = sc.nextLine();
        return projectName;
    }

    /**
     * Displays a list of receipts in a formatted table view.
     *
     * @param receipts the list of receipts to display.
     * @param headers the headers to use for table columns.
     */
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

    /**
     * Displays an error message for invalid input.
     */
    public void invalid_input_message(){
        System.out.print("Invalid option! ");
    }


}
