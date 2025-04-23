package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HDBManagerDAO {

    private static final String FILE_PATH = "BTOManagementSystem/Data/ManagerList.csv";
    private static List<HDBManager> managers = new ArrayList<HDBManager>();

    public HDBManagerDAO(){
        //init Managers from CSV
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

    public boolean NRIC_exist(String NRIC){

        for (HDBManager manager : managers) {
            if(manager.getNric().equals(NRIC)){
                return true;
            }
        }

        return false;
    }

    public List<HDBManager> getAllUsers(){
        return managers;
    }

    public String getPasswordOfUser(String NRIC){

        for (HDBManager manager : managers) {
            if(manager.getNric().equals(NRIC)){
                return manager.getPassword();
            }
        }

        return null;
    }

    public HDBManager getUser(String NRIC){
        for (HDBManager manager : managers) {
            if(manager.getNric().equals(NRIC)){
               return manager;
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
