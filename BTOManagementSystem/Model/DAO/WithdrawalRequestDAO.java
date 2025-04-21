package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.WithdrawalRequest;

import java.io.*;
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

                ArrayList<String> values = CSV_data_Parse(line);

                WithdrawalRequest withdrawalRequest = new WithdrawalRequest(values.get(0),values.get(1),values.get(2),values.get(3));
                this.withdrawalRequests.add(withdrawalRequest);

            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }

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

    public ArrayList<WithdrawalRequest> getUnhandledWithdrawalRequests() {

        ArrayList<WithdrawalRequest> requests = new ArrayList<>();

        for (WithdrawalRequest request : withdrawalRequests) {

            if(request.getProcessed() == 0){
                requests.add(request);
            }

        }

        return requests;
    }


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

    public void CreateNewWithdrawalRequest(WithdrawalRequest request){

        this.withdrawalRequests.add(request);
        this.UpdateDB();

    }

    public static void main(String[] args) {

        WithdrawalRequest r = new WithdrawalRequest("John","S1234567A","Acacia Breeze","0");
        WithdrawalRequestDAO dao = new WithdrawalRequestDAO();
        dao.CreateNewWithdrawalRequest(r);


    }



}
