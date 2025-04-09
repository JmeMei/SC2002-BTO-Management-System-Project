package BTOManagementSystem.View;

import java.util.*;

import BTOManagementSystem.Controller.AuthController;
import BTOManagementSystem.Controller.ViewController;
import BTOManagementSystem.Model.User;


public class LoginPromptView {

    public static void display(){
        System.out.print("Enter your NRIC: ");
        Scanner scanner = new Scanner(System.in);
        String NRIC = scanner.nextLine().toUpperCase();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User authenticatedUser = AuthController.Authenticate(NRIC, password);

        if (authenticatedUser != null) {
            AuthenticationSuccess(authenticatedUser);
        } else {
            // Optional: Add retry logic here
            display(); // Recursive call to retry login
        }

    }

    public static void AuthenticationSuccess(User user){

        System.out.println("Login Successful!");
        ViewController.handleMenuOptions(user);

    }
}
