package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class UserDAO {

    private String csvFile = "BTOManagementSystem/Data/ApplicantProjectList.csv"; // um what is the point of this line
    private List<String> csvFiles = Arrays.asList("BTOManagementSystem/Data/ApplicantList.csv","BTOManagementSystem/Data/OfficerList.csv","BTOManagementSystem/Data/ManagerList.csv");

    public boolean NRIC_exist(String NRIC){

        for (String csvfile : csvFiles){

            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(csvfile))) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values[1].trim().equalsIgnoreCase(NRIC)) {

                        return true;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return false;
    }


    public String getPasswordOfUser(String NRIC){



        for (String csvfile : csvFiles){

            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(csvfile))) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values[1].trim().equalsIgnoreCase(NRIC)) {

                        return values[4];
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String[] getAllUserDetails(String NRIC, String usertype){

        String csv =  "";
        if (usertype.compareTo("applicant") == 0){
            csv = csvFiles.get(0);
        }else if (usertype.compareTo("officer") == 0){
            csv = csvFiles.get(1);
        }else if (usertype.compareTo("manager") == 0){
            csv = csvFiles.get(2);
        }

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].trim().equalsIgnoreCase(NRIC)) {
                    return values;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getUserType(String NRIC){


        for (String csvfile : csvFiles){

            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(csvfile))) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values[1].trim().equalsIgnoreCase(NRIC)) {


                        if (csvfile.compareTo(csvFiles.get(0)) == 0 ){

                            return "applicant";

                        }

                        else if (csvfile.compareTo(csvFiles.get(1)) == 0 ){

                            return "officer";

                        }

                        else if (csvfile.compareTo(csvFiles.get(2)) == 0 ){

                            return "manager";

                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }





}
