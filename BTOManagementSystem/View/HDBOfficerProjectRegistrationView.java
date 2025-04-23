package BTOManagementSystem.View;

import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;

import java.util.List;
import java.util.Scanner;

public class HDBOfficerProjectRegistrationView {

    private Scanner scanner = new Scanner(System.in);

    public String promptOfficerChooseProject() {
        System.out.print("Enter the Project Name: ");
        String projectName = scanner.nextLine();
        return projectName;
    }

    public void ProjectNotFoundMessage(){
        System.out.println("The project was not found.");
    }

    public void displayRegistrationStatus(OfficerRegistrationRequest registrationRequest) {
            System.out.println("\n=== Your Officer Registration Status ===");
            System.out.println("Name         : " + registrationRequest.getOfficerName());
            System.out.println("NRIC         : " + registrationRequest.getNRIC());
            System.out.println("Project Name : " + registrationRequest.getProjectName());
            System.out.println("Status       : " + registrationRequest.getStatus());
    }

    public void AppliedForProjectBeforeMessage(){
        System.out.println("Registration Denied: You have already applied for this project as an applicant.");
    }

    public void RegistationNotFoundMessage(){
        System.out.println("You have not registered to handle any projects yet.");
    }

    public void RegistationSuccessMessage(){
        System.out.println("The registration was successful. Please wait for approval.");
    }

    public void RegistationAlreadyExistsMessage(){
        System.out.println("You have already registered to handle a project.");
    }
}