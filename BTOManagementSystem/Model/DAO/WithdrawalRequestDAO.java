package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.WithdrawalRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WithdrawalRequestDAO {

    ArrayList<WithdrawalRequest> withdrawalRequests = new ArrayList<WithdrawalRequest>();


    private String filePath = "BTOManagementSystem/Data/WithdrawalRequests.csv";
    private String[] Headers;

    public WithdrawalRequestDAO() {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            line = br.readLine();
            this.Headers = line.split(",");

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                this.withdrawalRequests.add(new WithdrawalRequest(values[0], values[1], values[2], values[3]));

            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
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
