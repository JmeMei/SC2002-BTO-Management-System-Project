package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.Roles.Applicant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ApplicantDAO {

    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantList.csv"; // um what is the point of this line
    private static List<Applicant> applicants = new ArrayList<Applicant>();

    public ApplicantDAO(){
        //init Applicants from CSV
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            // Skip Header
            br.readLine();

            //Iterate through CSV
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Applicant newApplicant = new Applicant(values[0],values[1],Integer.parseInt(values[2]),values[3],values[4]);
                applicants.add(newApplicant);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Applicant> getAllUsers(){
        return applicants;
    }

    public boolean NRIC_exist(String NRIC){
        for (Applicant applicant : applicants){
            if(applicant.getNric().equals(NRIC)){
                return true;
            }
        }

        return false;
    }


    public String getPasswordOfUser(String NRIC){

        for (Applicant applicant : applicants){
            if(applicant.getNric().equals(NRIC)){
               return applicant.getPassword();
            }
        }

        return null;
    }

    public Applicant getUser(String NRIC){
        for (Applicant applicant : applicants){
            if(applicant.getNric().equals(NRIC)){
                return applicant;
            }
        }

        return null;
    }

    public boolean updateDB() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write the header
            bw.write("Name,NRIC,Age,Marital Status,Password");
            bw.newLine();

            // Write each applicant
            for (Applicant a : applicants) {
                String line = String.join(",", a.getName(), a.getNric(), String.valueOf(a.getAge()), a.getMaritalStatus(), a.getPassword());
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
