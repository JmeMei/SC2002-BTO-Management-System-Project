package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class UserDAO {

    private final List<String> csvFiles = Arrays.asList(
            "BTOManagementSystem/Data/ApplicantList.csv",
            "BTOManagementSystem/Data/OfficerList.csv",
            "BTOManagementSystem/Data/ManagerList.csv"
    );

    public boolean NRIC_exist(String NRIC){

        for (String csvFile : csvFiles) {
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
        }

        return false;
    }


    public String getPasswordOfUser(String NRIC){

        for (String csvFile : csvFiles) {
            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
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

    private String inferRoleFromFile(String filePath) {
        if (filePath.toLowerCase().contains("applicant")) return "Applicant";
        if (filePath.toLowerCase().contains("officer")) return "HDBOfficer";
        if (filePath.toLowerCase().contains("manager")) return "HDBManager";
        return "Unknown";
    }

    public String[] getAllUserDetails(String NRIC){

        for (String csvFile : csvFiles) {
            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values[1].trim().equalsIgnoreCase(NRIC)) {

                        // Get role based on which csv was accessed
                        String role = inferRoleFromFile(csvFile);

                        // Add role to the existing string[] values
                        String[] valuesWithRole = Arrays.copyOf(values, values.length + 1);
                        valuesWithRole[values.length] = role;

                        return valuesWithRole;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}
