package BTOManagementSystem.View;

import BTOManagementSystem.Controller.ProjectListController;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.Project;

import java.util.Scanner;

public class HDBManagerEditProjectView {

    Scanner sc = new Scanner(System.in);

    public String PromptProjectName(){


        Project project = null;



        System.out.print("Enter Project Name to edit: ");

        String projectName = sc.nextLine();


        return projectName;

    }

    public void ProjectDoesNotExistErrorMessage(){
        System.out.println("Error! Project doesn't exist! ");
    }

    public void ProjectUpdateSuccessMessage(){
        System.out.println("Project updated successfully! ");
    }


    public int PromptAttribute(){

        System.out.println("Select the data column you want to edit:");
        System.out.println("1. Project Name");
        System.out.println("2. Neighborhood");
        System.out.println("3. Type 1");
        System.out.println("4. Number of units for Type 1");
        System.out.println("5. Selling price for Type 1");
        System.out.println("6. Type 2");
        System.out.println("7. Number of units for Type 2");
        System.out.println("8. Selling price for Type 2");
        System.out.println("9. Application opening date");
        System.out.println("10. Application closing date");
        System.out.println("11. Officer Slot");
        System.out.println("12. Visibility");

        System.out.print("Select attribute to change: ");
        int attribute =  sc.nextInt();
        sc.nextLine();

        return attribute;

    }


    public String PromptNewValue(){

        System.out.print("Enter New Value: ");
        String value =  sc.nextLine();

        return value;
    }




}
