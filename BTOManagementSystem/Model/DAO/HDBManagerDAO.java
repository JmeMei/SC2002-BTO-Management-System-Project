package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) class responsible for managing data related to HDB Managers.
 * <p>
 * Provides functionality for loading, retrieving, updating, and verifying manager data stored in a CSV file.
 */
public class HDBManagerDAO {

    private static final String FILE_PATH = "BTOManagementSystem/Data/ManagerList.csv";
    private static List<HDBManager> managers = new ArrayList<HDBManager>();

    /**
     * Constructs an {@code HDBManagerDAO} and initializes manager data from the CSV file.
     */
    public HDBManagerDAO(){
        //init Managers from CSV
        managers.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            // Skip Header
            br.readLine();

            //Iterate through CSV
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                HDBManager newManager = new HDBManager(values[0],values[1],Integer.parseInt(values[2]),values[3],values[4]);
                managers.add(newManager);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks if an HDB Manager with the given NRIC exists.
     *
     * @param NRIC the NRIC to check
     * @return {@code true} if a manager with the NRIC exists, {@code false} otherwise
     */
    public boolean NRIC_exist(String NRIC){

        for (HDBManager manager : managers) {
            if(manager.getNric().equals(NRIC)){
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves the list of all HDB Managers loaded from the CSV file.
     *
     * @return a list of {@link HDBManager} objects
     */
    public List<HDBManager> getAllUsers(){
        return managers;
    }

    /**
     * Retrieves the password associated with a specific NRIC.
     *
     * @param NRIC the NRIC of the manager
     * @return the password as a {@code String}, or {@code null} if not found
     */
    public String getPasswordOfUser(String NRIC){

        for (HDBManager manager : managers) {
            if(manager.getNric().equals(NRIC)){
                return manager.getPassword();
            }
        }

        return null;
    }

    /**
     * Retrieves an HDB Manager object by NRIC.
     *
     * @param NRIC the NRIC of the manager
     * @return the corresponding {@link HDBManager} object, or {@code null} if not found
     */
    public HDBManager getUser(String NRIC){
        for (HDBManager manager : managers) {
            if(manager.getNric().equals(NRIC)){
               return manager;
            }
        }

        return null;
    }

    /**
     * Updates the CSV file with the current in-memory list of managers.
     *
     * @return {@code true} if the update was successful, {@code false} otherwise
     */
    public boolean updateDB() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write the header
            bw.write("Name,NRIC,Age,Marital Status,Password");
            bw.newLine();

            // Write each applicant
            for (HDBManager m : managers) {
                String line = String.join(",", m.getName(), m.getNric(), String.valueOf(m.getAge()), m.getMaritalStatus(), m.getPassword());
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // used in enquiry
    /**
     * Converts a manager's name to their corresponding NRIC.
     * <p>
     * Used in the enquiry system to associate a manager's name with their identity.
     *
     * @param managerName the full name of the manager
     * @return the NRIC of the manager if found, {@code null} otherwise
     */
    public String managerNametoNRIC(String managerName){
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line = br.readLine(); // skip the header
            while((line = br.readLine()) != null){
                String[] fields = line.split(",",-1);
                if(fields[0].equals(managerName)){
                    return fields[1];
                }
            }
        } catch(IOException e){
            System.out.println("Error reading Manager File" + e.getMessage());
        }
        return null;
    }
}
