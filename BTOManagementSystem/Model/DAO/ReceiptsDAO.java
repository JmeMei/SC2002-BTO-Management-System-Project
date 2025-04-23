package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.OfficerRegistrationRequest;
import BTOManagementSystem.Model.Receipt;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReceiptsDAO {

    private String filePath = "BTOManagementSystem/Data/Receipts.csv";
    private String[] Headers;

    private static ArrayList<Receipt> receipts = new ArrayList<>();

    public ReceiptsDAO() {
        receipts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            line = br.readLine();
            this.Headers = line.split(",");

            while ((line = br.readLine()) != null) {

                ArrayList<String> values = CSV_data_Parse(line);

                Receipt r = new Receipt(
                        values.get(0),
                        values.get(1),
                        values.get(2),
                        values.get(3),
                        Integer.parseInt(values.get(4)),
                        values.get(5)
                );

                receipts.add(r);

            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }


    }

    public String[] getHeaders() {
        return Headers;
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

    public ArrayList<Receipt> LoadReciepts(int filter, String value){

        ArrayList<Receipt> filtered = new ArrayList<>();
        switch (filter){

            case 1 ->
                    filtered = receipts.stream().filter( r -> value.equalsIgnoreCase(r.getMaritalStatus())).collect(Collectors.toCollection(ArrayList::new));

            case 2 ->
                    filtered = receipts.stream().filter( r -> value.equalsIgnoreCase(r.getFlatType())).collect(Collectors.toCollection(ArrayList::new));

            case 3 -> {

                int data = Integer.parseInt(value);
                int upperbound = data % 100;
                int lowerbound =  Math.floorDiv(data, 100);

                filtered = receipts.stream().filter( r -> r.getAge() >= lowerbound && r.getAge() <= upperbound ).collect(Collectors.toCollection(ArrayList::new));

            }

            case  4 ->  filtered = receipts.stream().filter( r -> value.equalsIgnoreCase(r.getProjectName())).collect(Collectors.toCollection(ArrayList::new));

            default -> filtered = receipts;
        }

        return filtered;
    }

    public void AddReceipt(Receipt receipt){
        receipts.add(receipt);
        updateDb();
    }

    private void updateDb(){


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write(String.join(",",Headers));
            writer.newLine();

            // Write records
            for (Receipt receipt : receipts)
            {
                writer.write(String.format("%s,%s,%s,%s,%d,%s",

                                receipt.getApplicantName(),
                                receipt.getApplicantNRIC(),
                                receipt.getMaritalStatus(),
                                receipt.getFlatType(),
                                receipt.getAge(),
                                receipt.getProjectName()
                        )
                );
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
