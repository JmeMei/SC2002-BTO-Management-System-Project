package BTOManagementSystem.View;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Controller.ProjectListController;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.HDBManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * View class that handles the user interface for HDB Managers to create a new BTO project.
 * <p>
 * Provides prompts to gather necessary project details from the manager and displays messages related to project creation.
 */
public class HDBManagerCreateProjectView {


    Scanner sc = new Scanner(System.in);

    /**
     * Prompts the HDB Manager to enter all required project creation details.
     * <p>
     * The prompts include:
     * <ul>
     *     <li>Project name</li>
     *     <li>Neighbourhood</li>
     *     <li>Room type 1 and its unit count and price</li>
     *     <li>Room type 2 and its unit count and price</li>
     *     <li>Application opening and closing dates</li>
     *     <li>Number of officer slots</li>
     * </ul>
     *
     * @return an {@link ArrayList} of strings containing the project data in the order prompted.
     */
    public ArrayList<String> Prompt(){

        ArrayList<String> Data = new ArrayList<>();


        String[] Prompts = {
                "Enter Project Name: ",
                "Enter Neighbourhood: ",
                "Enter Room Type 1: ",
                "Enter Number of units for Type 1: ",
                "Enter Selling price for Type 1: ",
                "Enter Room Type 2: ",
                "Enter Number of units for Type 2: ",
                "Enter Selling price for Type 2: ",

                "Enter opening date (dd-MM-yyyy): ",
                "Enter closing date (dd-MM-yyyy): ",

                "Enter number of officer slots: "
        }  ;


        for (String Prompt : Prompts) {

            System.out.print(Prompt);
            Data.add(sc.nextLine());

        }

        return Data;

    }

    /**
     * Displays a message to indicate that the manager is already managing an active project
     * and therefore cannot create another.
     */
    public void AlreadyManagingProjectMessage(){
        System.out.println("You are already currently managing a project! Unable to create!");
    }



}
