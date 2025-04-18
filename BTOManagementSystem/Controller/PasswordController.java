package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.User;

import java.io.*;
import java.util.*;

public class PasswordController {

    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantList.csv"; // adjust path as needed

    public static boolean changePassword(String NRIC, String newPassword) {
        List<String[]> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            
            String header = reader.readLine(); //read the header
            fileContent.add(header.split(",")); //store the header

            String line;
            while((line = reader.readLine()) != null){
                String[] values = line.split(",");

                //If the NRIC match, change the password
                //Name,NRIC,Age,Marital Status,Password,role
                if(values[1].trim().equalsIgnoreCase(NRIC)) values[4] = newPassword;

                fileContent.add(values);
            }

        }catch (IOException e){
            System.out.println("Error reading the userlogin.csv " + e.getMessage());
            return false;
        }

        //Write the updated content back to the file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            for(String[] values : fileContent){
                writer.write(String.join(",", values));
                writer.newLine();
            }
            return true;
        } catch (IOException e){
            System.out.println("Error writing back to the userlogin.csv " + e.getMessage());
        }
        return false;
    }//END of changePassword
}
