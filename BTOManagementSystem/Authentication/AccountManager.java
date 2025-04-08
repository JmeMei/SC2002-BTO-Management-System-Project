package BTOManagementSystem.Authentication;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    // Path to the CSV 
    private static final String FILE_PATH = "BTOManagementSystem/Data/userlogin.csv"

    public user login(String NRIC, String password){
        List<String[]> userDatabase = new ArrayList<>();
        BufferReader reader = new BufferedReader(new FileReader(FILE_PATH)){
            //read the header and dont care
            String header = reader.readLine();
            userDatabase.add(header.split(",")); //dont care about the header. Can remove this part

            String line;
            while((line == reader.readLine()) != null){
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
                }
            }
            
            

        }
    }
}
