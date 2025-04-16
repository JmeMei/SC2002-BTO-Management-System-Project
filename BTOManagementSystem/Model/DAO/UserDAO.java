package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class UserDAO {

    private String csvFile = "BTOManagementSystem/Data/ApplicantList.csv";

    public boolean NRIC_exist(String NRIC){


        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].trim().equalsIgnoreCase(NRIC)) {

                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public String getPasswordOfUser(String NRIC){

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].trim().equalsIgnoreCase(NRIC)) {
                    System.out.println(values[1]);
                    System.out.println(values[4]);
                    return values[4];
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
