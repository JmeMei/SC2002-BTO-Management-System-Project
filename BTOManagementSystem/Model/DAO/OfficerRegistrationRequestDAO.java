package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;

import java.io.*;
import java.util.ArrayList;

public class OfficerRegistrationRequestDAO {


    private String filePath = "BTOManagementSystem/Data/OfficerRegistrationRequests.csv";
    private static ArrayList<OfficerRegistrationRequest> officerRegistrationRequests = new ArrayList<>();
    private String[] Headers;

    public OfficerRegistrationRequestDAO() {
        this.LoadAllOfficerRegistrationRequests();
    }

    public void ReloadFromFile() {
        this.LoadAllOfficerRegistrationRequests();
    }

    public void LoadAllOfficerRegistrationRequests() {
        officerRegistrationRequests.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            line = br.readLine();
            this.Headers = line.split(",");

            while ((line = br.readLine()) != null) {

                ArrayList<String> values = CSV_data_Parse(line);

                OfficerRegistrationRequest request = new OfficerRegistrationRequest(
                        values.get(0),
                        values.get(1),
                        values.get(2),
                        values.get(3)
                );

                officerRegistrationRequests.add(request);

            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
    }

    public String[] getHeaders() {
        return Headers;
    }

    public ArrayList<OfficerRegistrationRequest> LoadOfficerRegistrationRequests(int choice){

            String filter_Status = "";
            ArrayList<OfficerRegistrationRequest> FilteredRequests = new ArrayList<>();

            if (choice == 3) {
                return this.officerRegistrationRequests;
            }

            if (choice == 1){
                filter_Status = "Approved";
            } else if (choice == 2){
                filter_Status = "Pending";
            }

            for(OfficerRegistrationRequest request : this.officerRegistrationRequests){

                if (request.getStatus().equals(filter_Status)){
                    FilteredRequests.add(request);
                }

            }

            return FilteredRequests;
    }

    public boolean UpdateApprovalStatus(String Name, String ProjectName){

        for (OfficerRegistrationRequest request : this.officerRegistrationRequests){

            if (request.getOfficerName().equalsIgnoreCase(Name) && request.getProjectName().equalsIgnoreCase(ProjectName)){

                request.setStatus("Approved");
                updateDB();
                return true;
            }

        }

        return false;
    }

    public boolean RecordExists(String Name, String ProjectName){

        for (OfficerRegistrationRequest request : this.officerRegistrationRequests){

            if (request.getOfficerName().equalsIgnoreCase(Name) && request.getProjectName().equalsIgnoreCase(ProjectName)){

                return true;
            }

        }
        return false;
    }

    public OfficerRegistrationRequest GetOfficerRegRequest(String NRIC,String projectName){
        for (OfficerRegistrationRequest request : this.officerRegistrationRequests){
            if (request.getNRIC().equals(NRIC) && request.getProjectName().equals(projectName)){
                return request;
            }
        }
        return null;
    }

    public OfficerRegistrationRequest GetOfficerRegRequestByNRIC(String NRIC){
        for (OfficerRegistrationRequest request : this.officerRegistrationRequests){
            if (request.getNRIC().equals(NRIC)){
                return request;
            }
        }
        return null;
    }

    public void CreateOfficerRegistrationRequest(OfficerRegistrationRequest OfficerRegistrationRequest){
        this.officerRegistrationRequests.add(OfficerRegistrationRequest);
        updateDB();
    }


    public void updateDB(){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write(String.join(",",Headers));
            writer.newLine();

            // Write records
            for (OfficerRegistrationRequest request : this.officerRegistrationRequests)
            {
                writer.write(String.format("%s,%s,%s,%s",

                        request.getOfficerName(),
                        request.getNRIC(),
                        request.getProjectName(),
                        request.getStatus()
                        )
                );
                writer.newLine();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public ArrayList<ArrayList<String>> ViewRequests(){

        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> values = CSV_data_Parse(line);

                rows.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

        return rows;
    }

    private ArrayList<String> CSV_data_Parse(String line){

        ArrayList<String> formatted = new ArrayList<>();

        boolean inQuote = false;
        StringBuilder aData = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c == '"'){

                if (!inQuote){

                    inQuote = true;

                }else{
                    inQuote = false;
                }

            }
            if (c == ','){



                if (!inQuote){
                    formatted.add(aData.toString());

                    aData.setLength(0);

                }else{
                    aData.append(c);
                }

            }else{
                aData.append(c);
            }


        }

        formatted.add(aData.toString());

        return formatted;

    }


}
