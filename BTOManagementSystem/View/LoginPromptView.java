package BTOManagementSystem.View;


import java.util.*;

/**
 * The {@code LoginPromptView} class handles user interaction for the login screen
 * of the BTO Management System.
 * <p>
 * This class provides methods for prompting the user to input their NRIC and password,
 * and displays messages related to login success, failure, or invalid input format.
 */
public class LoginPromptView {


    public String[] getLoginData(){

        System.out.println("Welcome to the BTO Management System!");
        System.out.print("Enter your NRIC: ");
        Scanner scanner = new Scanner(System.in);
        String NRIC = scanner.nextLine().toUpperCase();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String[] Data = {NRIC,password};

        return Data;
    }

    /**
     * Displays a message indicating the NRIC format is invalid.
     */
    public void IncorrectNRICFormat(){
        System.out.println("Invalid NRIC format. Please enter a valid NRIC (e.g., T1234567A).");
    }

    /**
     * Displays a message indicating the result of the login attempt.
     *
     * @param status {@code true} if login was successful, {@code false} otherwise.
     */
    public void LoginSucessOrFail(boolean status){

        if(status){
            System.out.println("Login Success!");
        }else{
            System.out.println("Invalid Credentials!");
        }

    }

}
