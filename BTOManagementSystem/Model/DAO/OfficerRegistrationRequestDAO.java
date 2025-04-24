package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;

import java.io.*;
import java.util.ArrayList;
/**
 * DAO (Data Access Object) class for managing officer registration requests.
 * <p>
 * Handles creation, retrieval, filtering, updating, and persistence of registration
 * requests for HDB officers who wish to be assigned to BTO projects.
 */
public class OfficerRegistrationRequestDAO {


    private String filePath = "BTOManagementSystem/Data/OfficerRegistrationRequests.csv";
    private static ArrayList<OfficerRegistrationRequest> officerRegistrationRequests = new ArrayList<>();
    private String[] Headers;

    /**
     * Constructs the DAO and loads all registration requests from file.
     */
    public OfficerRegistrationRequestDAO() {
        this.LoadAllOfficerRegistrationRequests();
    }

    /**
     * Reloads the request list from the CSV file.
     */
    public void ReloadFromFile() {
        this.LoadAllOfficerRegistrationRequests();
    }

    /**
     * Loads all officer registration requests from the CSV file into memory.
     */
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

    /**
     * Gets the CSV header row for Officer Registration Requests.
     *
     * @return an array of column headers
     */
    public String[] getHeaders() {
        return Headers;
    }

    /**
     * Loads officer registration requests based on filter choice.
     *
     * @param choice filter option: 1 = Approved, 2 = Pending, 3 = All
     * @return a filtered list of {@link OfficerRegistrationRequest}
     */
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

    /**
     * Updates a registration request's status to "Approved".
     *
     * @param Name        the name of the officer
     * @param ProjectName the project they requested
     * @return {@code true} if successful, {@code false} otherwise
     */
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

    /**
     * Checks if a registration request record exists.
     *
     * @param Name        the officer's name
     * @param ProjectName the project name
     * @return {@code true} if found, otherwise {@code false}
     */
    public boolean RecordExists(String Name, String ProjectName){

        for (OfficerRegistrationRequest request : this.officerRegistrationRequests){

            if (request.getOfficerName().equalsIgnoreCase(Name) && request.getProjectName().equalsIgnoreCase(ProjectName)){

                return true;
            }

        }
        return false;
    }

    /**
     * Gets a registration request by officer NRIC and project name.
     *
     * @param NRIC        the NRIC of the officer
     * @param projectName the name of the project
     * @return matching {@link OfficerRegistrationRequest}, or {@code null} if not found
     */
    public OfficerRegistrationRequest GetOfficerRegRequest(String NRIC,String projectName){
        for (OfficerRegistrationRequest request : this.officerRegistrationRequests){
            if (request.getNRIC().equals(NRIC) && request.getProjectName().equals(projectName)){
                return request;
            }
        }
        return null;
    }

    /**
     * Gets a registration request by officer NRIC.
     *
     * @param NRIC the NRIC of the officer
     * @return matching {@link OfficerRegistrationRequest}, or {@code null} if not found
     */
    public OfficerRegistrationRequest GetOfficerRegRequestByNRIC(String NRIC){
        for (OfficerRegistrationRequest request : this.officerRegistrationRequests){
            if (request.getNRIC().equals(NRIC)){
                return request;
            }
        }
        return null;
    }

    /**
     * Creates a new officer registration request and saves it.
     *
     * @param OfficerRegistrationRequest the request to add
     */
    public void CreateOfficerRegistrationRequest(OfficerRegistrationRequest OfficerRegistrationRequest){
        this.officerRegistrationRequests.add(OfficerRegistrationRequest);
        updateDB();
    }

    /**
     * Saves all registration requests to the CSV file.
     */
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

    /**
     * Returns all registration request rows in a list of string lists.
     *
     * @return list of rows from the CSV file
     */
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

    /**
     * Parses a CSV line into an array of strings, accounting for quoted commas.
     *
     * @param line the CSV line
     * @return parsed values as a list
     */
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
