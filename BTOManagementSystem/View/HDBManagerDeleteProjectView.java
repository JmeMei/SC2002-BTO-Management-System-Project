package BTOManagementSystem.View;

import java.security.PublicKey;
import java.util.Scanner;

/**
 * View class responsible for interacting with HDB Managers during the project deletion process.
 * <p>
 * Provides prompts to receive project name input and displays confirmation or error messages
 * based on the deletion outcome.
 */
public class HDBManagerDeleteProjectView {

    private Scanner scanner = new Scanner(System.in);


    /**
     * Prompts the HDB Manager to enter the name of the project they want to delete.
     *
     * @return the name of the project to be deleted, as entered by the manager.
     */
    public String promptProjectNameToDelete() {

        System.out.print("Enter the Project Name you want to delete: ");


        return scanner.nextLine();

    }

    /**
     * Displays a message indicating the project was successfully deleted.
     */
    public void SucesssMessage(){

        System.out.println("Project Successfully Deleted!");
    }

    /**
     * Displays a message indicating the project was not found and therefore could not be deleted.
     */
    public void FailedMessage(){

        System.out.println("Project Not Found!");
    }

}
