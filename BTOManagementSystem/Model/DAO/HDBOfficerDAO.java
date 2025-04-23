package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBOfficer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HDBOfficerDAO {

    private static final String FILE_PATH = "BTOManagementSystem/Data/OfficerList.csv"; // um what is the point of this line
    private static List<HDBOfficer> officers = new ArrayList<HDBOfficer>();

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

    public List<HDBOfficer> getAllUsers(){
        return officers;
    }

    public boolean NRIC_exist(String NRIC){

        for (HDBOfficer officer : officers) {
            if(officer.getNric().equals(NRIC)){
                return true;
            }
        }

        return false;
    }


    public String getPasswordOfUser(String NRIC){

        for (HDBOfficer officer : officers) {
            if(officer.getNric().equals(NRIC)){
               return officer.getPassword();
            }
        }

        return null;
    }

    public HDBOfficer getUser(String NRIC){
        for (HDBOfficer officer : officers) {
            if(officer.getNric().equals(NRIC)){
                return officer;
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
    public String officerNametoNRIC(String officerName){
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line = br.readLine(); // skip the header
            while((line = br.readLine()) != null){
                String[] fields = line.split(",",-1);
                if(fields[0].equals(officerName)){
                    return fields[1];
                }
            }
        } catch(IOException e){
            System.out.println("Error reading Manager File" + e.getMessage());
        }
        return null;
    }



}
