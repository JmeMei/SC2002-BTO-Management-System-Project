package BTOManagementSystem.View;


import java.util.*;


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

    public void IncorrectNRICFormat(){
        System.out.println("Invalid NRIC format. Please enter a valid NRIC (e.g., T1234567A).");
    }

    public void LoginSucessOrFail(boolean status){

        if(status){
            System.out.println("Login Success!");
        }else{
            System.out.println("Invalid Credentials!");
        }

    }

}
