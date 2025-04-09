package BTOManagementSystem.View;

import java.util.*;

import BTOManagementSystem.Controller.AuthController;
import BTOManagementSystem.Controller.ViewController;


public class LoginPromptView {

    public static void display(){
        System.out.print("Enter your NRIC: ");
        Scanner scanner = new Scanner(System.in);
        String nric = scanner.nextLine().toUpperCase();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().toUpperCase();
        AuthController.Authenticate("s", "s");
    }

    public static void AuthenticationSucess(){

        System.out.println("Login Successful!");
        ViewController.handleMenuOptions();

    }
}
