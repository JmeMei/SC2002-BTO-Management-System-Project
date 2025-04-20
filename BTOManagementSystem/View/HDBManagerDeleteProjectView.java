package BTOManagementSystem.View;

import java.security.PublicKey;
import java.util.Scanner;

public class HDBManagerDeleteProjectView {

    private Scanner scanner = new Scanner(System.in);

    public String promptProjectNameToDelete() {

        System.out.print("Enter the Project Name you want to delete: ");


        return scanner.nextLine();

    }

    public void SucesssMessage(){

        System.out.println("Project Successfully Deleted!");
    }

    public void FailedMessage(){

        System.out.println("Project Not Found!");
    }

}
