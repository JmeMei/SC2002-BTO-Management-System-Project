package BTOManagementSystem.Services;

import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.View.*;
import BTOManagementSystem.App.*;


import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HDB_Manager_ActionHandler {

    private HDBManagerView ManagerView;
    private static final Scanner scanner = new Scanner(System.in);


    public void HandleAction(int option){

        switch (option) {
            case 1:

                createNewProject();
                //System.out.println("[DEBUG] Creating a new project...");

                break;
            case 2:
                System.out.println("[DEBUG] Editing or deleting a project...");
                break;
            case 3:
                System.out.println("[DEBUG] Approving officer registrations...");
                break;
            case 4:
                System.out.println("[DEBUG] Viewing applications...");
                break;
            case 5:
                System.out.println("Logging out...");
                return;
            case 6:
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void createNewProject(){


        System.out.print("Enter Project Name: ");
        String ProjectName = scanner.nextLine();

        System.out.print("Enter Neighbourhood: ");
        String Neighbourhood = scanner.nextLine();

        System.out.print("Enter Room Type 1: ");
        String RoomType1 = scanner.nextLine();

        System.out.print("Enter Number of units for Type 1: ");
        int NumberOfUnitsType1 = scanner.nextInt();

        System.out.print("Enter Selling price for Type 1: ");
        double sellingPriceType1 = scanner.nextDouble();

        scanner.nextLine();

        System.out.print("Enter Room Type 2: ");
        String RoomType2 = scanner.nextLine();

        System.out.print("Enter Number of units for Type 2: ");
        int NumberOfUnitsType2 = scanner.nextInt();

        System.out.print("Enter Selling price for Type 2: ");
        double sellingPriceType2 = scanner.nextDouble();

        scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.print("Enter opening date (dd-MM-yyyy): ");
        String openingDateString = scanner.nextLine();
        LocalDate OpeningDateObj =  LocalDate.parse(openingDateString, formatter);

        System.out.print("Enter closing date (dd-MM-yyyy): ");
        String closingDateString = scanner.nextLine();

        LocalDate closingDateObj = LocalDate.parse(closingDateString, formatter);


        System.out.print("Enter number of officer slots: ");
        int OfficerSlots = scanner.nextInt();


        Project newProject = new Project(ProjectName,Neighbourhood,RoomType1,
                NumberOfUnitsType1,sellingPriceType1, RoomType2, NumberOfUnitsType2 , sellingPriceType2, OpeningDateObj,
                closingDateObj, /*((HDBManager)App.userSession).getName()*/ "tim", OfficerSlots);


        ProjectListDAO dao = new ProjectListDAO();
        dao.writeANewProjectEntry(newProject);

    }


    public static void main(String[] args) {
        HDB_Manager_ActionHandler action = new HDB_Manager_ActionHandler();
        action.createNewProject();


    }





}
