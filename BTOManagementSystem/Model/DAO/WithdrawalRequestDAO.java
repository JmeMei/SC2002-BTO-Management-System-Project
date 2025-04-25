package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.WithdrawalRequest;

import java.io.*;
import java.util.ArrayList;

/**
 * DAO (Data Access Object) class responsible for managing BTO withdrawal requests.
 * <p>
 * Provides methods to load, create, retrieve, and update {@link WithdrawalRequest} objects from a CSV file.
 */
public class WithdrawalRequestDAO {


    private static ArrayList<WithdrawalRequest> withdrawalRequests = new ArrayList<WithdrawalRequest>();


    private String filePath = "BTOManagementSystem/Data/WithdrawalRequests.csv";
    private String[] Headers;

    /**
     * Constructs the DAO and loads withdrawal requests from the CSV file into withdrawalRequests List
     */
    public WithdrawalRequestDAO() {
       withdrawalRequests.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            line = br.readLine();
            this.Headers = line.split(",");

            while ((line = br.readLine()) != null) {

                ArrayList<String> values = CSV_data_Parse(line);

                WithdrawalRequest withdrawalRequest = new WithdrawalRequest(values.get(0),values.get(1),values.get(2),values.get(3));
                this.withdrawalRequests.add(withdrawalRequest);

            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

    }

    /**
     * Creates a new withdrawal request for the user if one doesn't already exist.
     *
     * @param user        the user submitting the withdrawal
     * @param projectName the project to withdraw from
     * @return {@code true} if created successfully, {@code false} if already exists
     */
    public boolean CreateWithdrawalRequest(User user, String projectName) {
        // Check if Request was already made
        for (WithdrawalRequest wReq : withdrawalRequests) {
            if(wReq.getNric().equals(user.getNric()) && wReq.getProjectName().equals(projectName)) {
                return false;
            }
        }

        // Create new withdrawal Request for Applicant
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest(user.getName(), user.getNric(), projectName, "0");
        withdrawalRequests.add(withdrawalRequest);
        this.UpdateDB();
        return true;
    }


    /**
     * Parses a line of CSV data while considering quoted fields.
     *
     * @param line the line from the CSV file
     * @return a list of parsed strings
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

    /**
     * Returns all withdrawal requests that are not yet processed.
     *
     * @return list of unprocessed {@link WithdrawalRequest}
     */
    public ArrayList<WithdrawalRequest> getUnhandledWithdrawalRequests() {

        ArrayList<WithdrawalRequest> requests = new ArrayList<>();

        for (WithdrawalRequest request : withdrawalRequests) {

            if(request.getProcessed() == 0){
                requests.add(request);
            }

        }

        return requests;
    }

    /**
     * Gets the headers from the Withdrawal Request CSV file.
     *
     * @return array of header names
     */
    public String[] getHeaders() {
        return Headers;
    }

    /**
     * Updates the CSV file with all current withdrawal requests.
     */
    public void UpdateDB(){


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write(String.join(",", Headers));
            writer.newLine();

            // Write records
            for (WithdrawalRequest w : this.withdrawalRequests) {
                writer.write(String.format("%s,%s,%s,%s",

                                w.getName(),
                                w.getNric(),
                                w.getProjectName(),
                                w.getProcessed()
                        )
                );
                writer.newLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * adds a new {@link WithdrawalRequest} and updates the database (csv).
     *
     * @param request the request to add
     */
    public void CreateNewWithdrawalRequest(WithdrawalRequest request){

        this.withdrawalRequests.add(request);
        this.UpdateDB();

    }

    /**
     * Updates a withdrawal request to mark it as processed (status = 1).
     *
     * @param ApplicantNRIC the NRIC of the applicant
     * @param ProjectName   the name of the project to withdraw from
     * @return {@code 1} if updated, {@code 0} if not found
     */
    public int UpdateWithdrawalRequest(String ApplicantNRIC,  String ProjectName){

        for (WithdrawalRequest wReq : withdrawalRequests) {

            if(wReq.getNric().equalsIgnoreCase(ApplicantNRIC)){

                if(wReq.getProjectName().equalsIgnoreCase(ProjectName)){

                    wReq.setProcessed(1);
                    this.UpdateDB();
                    return 1;
                }

            }

        }
        return 0;
    }
}
