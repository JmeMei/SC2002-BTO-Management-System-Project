package BTOManagementSystem.View;

import java.util.*;

import BTOManagementSystem.Controller.AuthController;
import BTOManagementSystem.Controller.ViewController;
import BTOManagementSystem.View.*;

public class LoginPromptView {

    public static void display(){
        System.out.print("Enter your NRIC: ");
        Scanner scanner = new Scanner(System.in);
        String NRIC = scanner.nextLine().toUpperCase();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().toUpperCase();
        
        User user = AuthController.Authenticate(NRIC,  password);
    }

    public static void AuthenticationSucess(){

        System.out.println("Login Successful!");
        ViewController.handleMenuOptions();

    }
}
