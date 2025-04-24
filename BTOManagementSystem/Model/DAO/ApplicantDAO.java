package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Roles.Applicant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DAO (Data Access Object) class responsible for managing data related to Applicants.
 * <p>
 * Handles operations such as loading from CSV, retrieving users, updating profile details,
 * and persisting changes back to file.
 */
public class ApplicantDAO {

    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantList.csv"; // um what is the point of this line
    private static List<Applicant> applicants = new ArrayList<Applicant>();


    public ApplicantDAO(){
        applicants.clear();
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

    /**
     * Retrieves all applicants loaded from the CSV file.
     *
     * @return a list of all {@link Applicant} objects
     */
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

    /**
     * Checks if an applicant with the given NRIC exists.
     *
     * @param NRIC the NRIC to check
     * @return {@code true} if the NRIC exists, {@code false} otherwise
     */
    public String getPasswordOfUser(String NRIC){

        for (Applicant applicant : applicants){
            if(applicant.getNric().equals(NRIC)){
               return applicant.getPassword();
            }
        }

        return null;
    }

    /**
     * Retrieves the password of a user given their NRIC.
     *
     * @param NRIC the NRIC of the applicant
     * @return the password as a {@code String}, or {@code null} if not found
     */
    public Applicant getUser(String NRIC){
        for (Applicant applicant : applicants){
            if(applicant.getNric().equals(NRIC)){
                return applicant;
            }
        }

        return null;
    }

    /**
     * Persists all current applicants in memory back to the CSV file.
     *
     * @return {@code true} if the update was successful, {@code false} if an error occurred
     */
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
