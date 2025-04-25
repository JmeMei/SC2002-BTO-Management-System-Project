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

public class HDBManagerCreateProjectView {


    Scanner sc = new Scanner(System.in);


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

        /*
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
                closingDateObj,((HDBManager) App.userSession).getName(), OfficerSlots);


        ProjectListDAO dao = new ProjectListDAO();
        dao.writeANewProjectEntry(newProject);


         */

    }

    public void AlreadyManagingProjectMessage(){
        System.out.println("You are already currently managing a project! Unable to create!");
    }



}
