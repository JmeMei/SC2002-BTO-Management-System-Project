package BTOManagementSystem.View;

import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;

import java.util.List;
import java.util.Scanner;

/**
 * View class for handling officer registration-related interactions within the BTO Management System.
 * <p>
 * This view allows HDB Officers to:
 * <ul>
 *     <li>Register for projects</li>
 *     <li>Check registration status</li>
 *     <li>View messages about registration success, failure, or constraints</li>
 * </ul>
 */
public class HDBOfficerProjectRegistrationView {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the officer to input the name of the project they want to register for.
     *
     * @return The name of the project entered by the user.
     */
    public String promptOfficerChooseProject() {
        System.out.print("Enter the Project Name: ");
        String projectName = scanner.nextLine();
        return projectName;
    }

    /**
     * Displays a message when the specified project is not found.
     */
    public void ProjectNotFoundMessage(){
        System.out.println("The project was not found.");
    }

    /**
     * Displays the status of the officer's registration request for handling a project.
     *
     * @param registrationRequest The {@link OfficerRegistrationRequest} object containing registration details.
     */
    public void displayRegistrationStatus(OfficerRegistrationRequest registrationRequest) {
            System.out.println("\n=== Your Officer Registration Status ===");
            System.out.println("Name         : " + registrationRequest.getOfficerName());
            System.out.println("NRIC         : " + registrationRequest.getNRIC());
            System.out.println("Project Name : " + registrationRequest.getProjectName());
            System.out.println("Status       : " + registrationRequest.getStatus());
    }

    /**
     * Displays a message indicating the officer has previously applied for the same project as an applicant,
     * and thus cannot register as an officer.
     */
    public void AppliedForProjectBeforeMessage(){
        System.out.println("Registration Denied: You have already applied for this project as an applicant.");
    }

    /**
     * Displays a message indicating the officer has not yet registered for any project.
     */
    public void RegistationNotFoundMessage(){
        System.out.println("You have not registered to handle any projects yet.");
    }

    /**
     * Displays a success message when the officer's registration request was successful.
     */
    public void RegistationSuccessMessage(){
        System.out.println("The registration was successful. Please wait for approval.");
    }

    /**
     * Displays a message indicating the officer has already submitted a registration request for a project.
     */
    public void RegistationAlreadyExistsMessage(){
        System.out.println("You have already registered to handle a project.");
    }
}