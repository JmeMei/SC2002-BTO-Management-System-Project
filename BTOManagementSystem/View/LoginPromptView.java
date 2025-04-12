package BTOManagementSystem.View;

import java.util.*;

import BTOManagementSystem.Controller.AuthController;
import BTOManagementSystem.Controller.ViewController;
import BTOManagementSystem.Model.User;


public class LoginPromptView implements ILoginView{

    public void displayLogInMenu(){
        System.out.print("Enter your NRIC: ");
        Scanner scanner = new Scanner(System.in);
        String NRIC = scanner.nextLine().toUpperCase();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        //AuthController will return the User authenticated
        User authenticatedUser = AuthController.Authenticate(NRIC, password);

        if (authenticatedUser != null) {
            ViewController.handleMenuOptions(authenticatedUser); //go to their respective views
        } else {
            displayLogInMenu(); // Recursive call to retry login
        }

    }
}
