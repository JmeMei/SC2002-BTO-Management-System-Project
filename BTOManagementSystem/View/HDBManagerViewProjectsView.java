package BTOManagementSystem.View;

import BTOManagementSystem.Model.Project;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
/**
 * View class responsible for displaying project-related information
 * and filtering options for the HDB Manager.
 * <p>
 * Provides functionality for:
 * <ul>
 *     <li>Filtering projects by ownership</li>
 *     <li>Filtering by neighbourhood</li>
 *     <li>Displaying project details in a tabular format</li>
 * </ul>
 */
public class HDBManagerViewProjectsView {

    private Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user to choose whether to filter projects by ownership.
     *
     * @return 1 if user wants to see only their own projects, 0 otherwise
     */
    public int prompt(){

        System.out.print("Do you only want to see projects you created? (Y/N): ");

        String answer = sc.nextLine().toUpperCase();

        switch (answer){

            case "Y":
                return 1;
            case "N":
                return 0;

            default:
                System.out.print("Enter Y or N only! ");
                return this.prompt();
        }
    }

    /**
     * Prompts the user to choose whether to filter projects by neighbourhood.
     *
     * @return 1 if user wants to filter by neighbourhood, 0 otherwise
     */
    public int prompt_wantneightbourhoodfilter(){

        System.out.print("Filter by neighbourhood? (Y/N): ");

        String answer = sc.nextLine().toUpperCase();

        switch (answer){

            case "Y":
                return 1;
            case "N":
                return 0;

            default:
                System.out.print("Enter Y or N only! ");
                return this.prompt_wantneightbourhoodfilter();
        }



    }

    /**
     * Prompts the user to enter the neighbourhood name for filtering.
     *
     * @return The neighbourhood name entered by the user
     */
    public String PromptNeightbourhoodfilterValue(){

        System.out.print("Enter Neighbourhood: ");
        String answer = sc.nextLine().toUpperCase();
        return answer;

    }

    /**
     * Displays a list of projects in a formatted table along with their attributes.
     *
     * @param headers  Array of header titles for the columns
     * @param Projects List of Project objects to be displayed
     */
    public void DisplayProjects(String[] headers, ArrayList<Project> Projects){

        for(String s : headers){

            System.out.print(String.format("%-30s", s));
        }

        System.out.print("\n");

        for(Project p : Projects){

            System.out.print(String.format("%-30s", p.getName()));
            System.out.print(String.format("%-30s", p.getNeighbourhood()));

            System.out.print(String.format("%-30s", p.getType1()));
            System.out.print(String.format("%-30d", p.getType1_numofunits()));
            System.out.print(String.format("%-30f", p.getType1_sellingprice()));

            System.out.print(String.format("%-30s", p.getType2()));
            System.out.print(String.format("%-30d", p.getType2_numofunits()));
            System.out.print(String.format("%-30f", p.getType2_selling_price()));

            System.out.print(String.format("%-30s", p.getOpeningDateAsString()));
            System.out.print(String.format("%-30s", p.getClosingDateAsString()));

            System.out.print(String.format("%-30s", p.getManager()));
            System.out.print(String.format("%-30d", p.getOfficerslots()));
            System.out.print(String.format("%-30s", p.get_officers() ));
            System.out.print(String.format("%-30d", p.getVisibility()));

            System.out.print("\n");

        }


    }


}
