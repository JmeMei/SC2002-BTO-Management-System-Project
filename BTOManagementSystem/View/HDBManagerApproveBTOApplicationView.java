package BTOManagementSystem.View;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * View class for HDB Managers to approve BTO applications.
 * <p>
 * Provides options to filter applications, approve selected ones, and view details.
 */
public class HDBManagerApproveBTOApplicationView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user (manager) to input filters for viewing BTO applications.
     *
     * @return a String array containing:
     *         [0] project name filter ("N" if none),
     *         [1] age range filter ("N" or "lower,upper"),
     *         [2] application status (1: Pending, 2: Successful, 3: Unsuccessful, 4: All)
     */
    public String[] PromptFilters(){

        System.out.println("Enter Project Name Filter (N = No filter): ");
        String projectName = scanner.nextLine();

        System.out.println("Enter Age Group Filter: (N = No filter, format = lowerbound,upperbound): ");
        String ageGroup = scanner.nextLine();

        int status = 0;


        while(status < 1 || status > 4){
            System.out.print("Select Status Type: \n"
                    + "1. Pending\n"
                    + "2. Successful\n"
                    + "3. Unsuccessful\n"
                    + "4. ALL\n"
                    + "Enter Choice:");
            status = scanner.nextInt();
            scanner.nextLine();

        }

        String[] data = {projectName, ageGroup, String.valueOf(status)};

        return data;
    }

    /**
     * Prompts the manager to input the applicant NRIC and project name
     * for approving an application.
     *
     * @return a String array containing:
     *         [0] applicant NRIC,
     *         [1] project name
     */
    public String[] PromptApproveBTOApplicationValue(){

        System.out.println("Enter Applicant NRIC to approve: ");
        String applicantNRIC = scanner.nextLine();

        System.out.println("Enter corresponding project Name: ");
        String projectName = scanner.nextLine();

        String[] data = {applicantNRIC, projectName};

        return data;
    }

    /**
     * Displays a list of BTO applications with details like applicant info,
     * project name, flat type, and application status.
     *
     * @param applicantProjectStatusList the list of application records to display
     */
    public void ViewBTOApplications(ArrayList<ApplicantProjectStatus> applicantProjectStatusList) {

        String[] Headers = {"Applicant Name", "Applicant NRIC", "Age", "Role" ,"Project Name" ,"Flat Type" ,"Application Status"};

        for(String s : Headers){

            System.out.print(String.format("%-30s", s));
        }

        System.out.println();

        //Name,NRIC,Age,Marital Status,role,Project Name,FlatType,Application Status

        for (ApplicantProjectStatus aps : applicantProjectStatusList) {

            System.out.print(String.format("%-30s", aps.getName()));
            System.out.print(String.format("%-30s", aps.getNric()));
            System.out.print(String.format("%-30s", aps.getAge()));
            System.out.print(String.format("%-30s", aps.getRole()));
            System.out.print(String.format("%-30s", aps.getProjectName()));
            System.out.print(String.format("%-30s", aps.getFlatType()));
            System.out.print(String.format("%-30s", aps.getApplicationStatus()));
            System.out.println();

        }

    }

    /**
     * Displays a success message when the application has been successfully updated.
     */
    public void SuccessfulUpdate(){

        System.out.println("Application Successfully Updated");

    }

    /**
     * Displays an error message when the application record does not exist.
     */
    public void FailedUpdate(){
        System.out.println("Record does not exist!");
    }

}
