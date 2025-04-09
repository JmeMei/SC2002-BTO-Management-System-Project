package BTOManagementSystem.Controller;
import java.util.*;

import BTOManagementSystem.Model.*;
import BTOManagementSystem.View.LoginPromptView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AuthController {
    private static final String FILE_PATH = "BTOManagementSystem/Data/userlogin.csv"; //must be declared outside the method

    public static User Authenticate(String NRIC, String password){

        //Session.updateSession("T1234567A");
        //LoginPromptView.AuthenticationSucess();

        List<String[]> userDatabase = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            //read the header and dont care
            String header = reader.readLine();
            //userDatabase.add(header.split(",")); //dont care about the header. Can remove this part

            String line;
            while( (line == reader.readLine()  != null){
                //Name,NRIC,Age,Marital Status,Password,role
                String[] values = line.split(",");
                String[] userName = values[0].trim(); 

                String[] values = line.split(",");
                String[] userNRIC = values[1].trim(); 

                String[] values = line.split(",");
                String[] userAge = values[2].trim(); 

                String[] values = line.split(",");
                String[] userMaritalStatus = values[3].trim(); 

                String[] values = line.split(",");
                String[] userPassword = values[4].trim(); 

                String[] values = line.split(",");
                String[] userRole = values[5].trim(); 

                if(userNRIC.equals(NRIC) && userPassword.equals(password)){
                    System.out.println("login successful, Welcome back" + userName);
                    // Return the authenticated user object
                    return User(userName, userAge, userMaritalStatus, userPassword, userRole);
                }
            }
            catch (IOException e) {
        System.out.println("Error reading the CSV database: " + e.getMessage());
             }
        return null;

    }
    
}