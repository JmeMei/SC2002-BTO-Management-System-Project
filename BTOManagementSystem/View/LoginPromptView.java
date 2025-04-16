package BTOManagementSystem.View;


import BTOManagementSystem.Services.AuthenticatorService;

import java.util.*;


public class LoginPromptView {


    public String[] getLoginData(){

        System.out.print("Enter your NRIC: ");
        Scanner scanner = new Scanner(System.in);
        String NRIC = scanner.nextLine().toUpperCase();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String[] Data = {NRIC,password};

        return Data;
    }

    public void LoginSucessOrFail(boolean status){

        if(status){
            System.out.println("Login Success!");
        }else{
            System.out.println("Invalid Credentials!");
        }

    }



}
