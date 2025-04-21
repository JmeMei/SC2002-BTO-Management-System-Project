package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.WithdrawalRequest;

import java.io.*;
import java.util.ArrayList;

public class WithdrawalRequestDAO {

    ArrayList<WithdrawalRequest> withdrawalRequests = new ArrayList<WithdrawalRequest>();


    private static final String FILE_PATH = "BTOManagementSystem/Data/WithdrawalRequests.csv";
    private String[] Headers;

    public WithdrawalRequestDAO() {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;
            line = br.readLine();
            this.Headers = line.split(",");

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                this.withdrawalRequests.add(new WithdrawalRequest(values[0], values[1], values[2], values[3]));

            }
        } catch (Exception e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

    }

    public boolean CreateWithdrawalRequest(User user, String projectName) {
        // Check if Request was already made
        for (WithdrawalRequest wReq : withdrawalRequests) {
            if(wReq.getNric().equals(user.getNric())) {
                return false;
            }
        }

        // Create new withdrawal Request for Applicant
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest(user.getName(), user.getNric(), projectName, "0");
        withdrawalRequests.add(withdrawalRequest);
        this.updateDB();
        return true;
    }

    public boolean updateDB() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write the header
            bw.write("Name,NRIC,Age,Marital Status,Password");
            bw.newLine();

            // Write each applicant
            for (WithdrawalRequest wReq : withdrawalRequests) {
                String line = String.join(",", wReq.getName(), wReq.getNric(), wReq.getProjectName(), Integer.toString(wReq.getProcessed()));
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<WithdrawalRequest> getUnhandledWithdrawalRequests() {

        ArrayList<WithdrawalRequest> requests = new ArrayList<>();

        for (WithdrawalRequest request : withdrawalRequests) {

            if(request.getProcessed() == 0){
                requests.add(request);
            }

        }

        return requests;
    }




}
