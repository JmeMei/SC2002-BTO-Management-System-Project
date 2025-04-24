package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBOfficer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) class responsible for managing data related to HDB Officers.
 * <p>
 * Provides functionality to load, retrieve, verify, and persist officer information stored in a CSV file.
 */
public class HDBOfficerDAO {

    private static final String FILE_PATH = "BTOManagementSystem/Data/OfficerList.csv"; // um what is the point of this line
    private static List<HDBOfficer> officers = new ArrayList<HDBOfficer>();
    /**
     * Constructs an {@code HDBOfficerDAO} and initializes the list of officers by loading from the CSV file.
     */
    public HDBOfficerDAO(){
        //init officers from CSV
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            // Skip Header
            br.readLine();

            //Iterate through CSV
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                HDBOfficer newOfficer = new HDBOfficer(values[0],values[1],Integer.parseInt(values[2]),values[3],values[4]);
                officers.add(newOfficer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieves the full list of HDB officers.
     *
     * @return a list of {@link HDBOfficer} objects
     */
    public List<HDBOfficer> getAllUsers(){
        return officers;
    }

    /**
     * Checks whether an officer with the given NRIC exists.
     *
     * @param NRIC the NRIC to check
     * @return {@code true} if the NRIC exists, {@code false} otherwise
     */
    public boolean NRIC_exist(String NRIC){

        for (HDBOfficer officer : officers) {
            if(officer.getNric().equals(NRIC)){
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves the password of a specific officer by NRIC.
     *
     * @param NRIC the NRIC of the officer
     * @return the password as a {@code String}, or {@code null} if not found
     */
    public String getPasswordOfUser(String NRIC){

        for (HDBOfficer officer : officers) {
            if(officer.getNric().equals(NRIC)){
               return officer.getPassword();
            }
        }

        return null;
    }

    /**
     * Retrieves a specific HDB officer by NRIC.
     *
     * @param NRIC the NRIC of the officer
     * @return the matching {@link HDBOfficer} object, or {@code null} if not found
     */
    public HDBOfficer getUser(String NRIC){
        for (HDBOfficer officer : officers) {
            if(officer.getNric().equals(NRIC)){
                return officer;
            }
        }

        return null;
    }

    /**
     * Writes all current officer data in memory back to the CSV file.
     *
     * @return {@code true} if the file was successfully updated, {@code false} otherwise
     */
    public boolean updateDB() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write the header
            bw.write("Name,NRIC,Age,Marital Status,Password");
            bw.newLine();

            // Write each applicant
            for (HDBOfficer o : officers) {
                String line = String.join(",", o.getName(), o.getNric(), String.valueOf(o.getAge()), o.getMaritalStatus(), o.getPassword());
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //for enquiry use
    /**
     * Retrieves the NRIC of an officer given their name.
     * <p>
     * Mainly used for associating officers with enquiries.
     *
     * @param officerName the name of the officer
     * @return the NRIC of the officer, or {@code null} if not found
     */
    public String officerNametoNRIC(String officerName){
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line = br.readLine(); // skip the header
            while((line = br.readLine()) != null){
                String[] fields = line.split(",",-1);
                if(fields[0].equalsIgnoreCase(officerName)){
                    return fields[1];
                }
            }
        } catch(IOException e){
            System.out.println("Error reading Manager File" + e.getMessage());
        }
        return null;
    }
}
