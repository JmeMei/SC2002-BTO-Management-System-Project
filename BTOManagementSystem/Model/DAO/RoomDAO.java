package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import BTOManagementSystem.Model.Room;

public class RoomDAO {
    private static final String FILE_PATH = "BTOManagementSystem/Data/Room.csv";

    public List<Room> loadAvailableTwoRooms(){ //for Date
        List<Room> records = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            boolean isHeader = true; //skip the header

            while((line = br.readLine()) != null){
                if(isHeader){
                    isHeader = false;
                    continue;
                }
                //String[] values = line.split(",", -1);
                // Use regex to avoid splitting commas that are inside quotes.
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // if (values.length < 10) {
                //     System.out.println("Skipping line with insufficient data: " + line);
                //     continue;
                // }

                // Parse required columns (assuming columns are as described above)
                String projectName  = values[0].trim();
                String neighborhood = values[1].trim();
                int numberOfUnits   = Integer.parseInt(values[2].trim()); // <= might throw NumberFormatException if invalid
                double price        = Double.parseDouble(values[3].trim());
                // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                // Date openingDate = sdf.parse(values[4].trim());
                // Date closingDate = sdf.parse(values[5].trim());
                String openingDate = values[4].trim();
                String closingDate = values[5].trim();
                String manager      = values[6].trim();
                int officerSlot     = values[7].trim().isEmpty() ? 0 : Integer.parseInt(values[7].trim());
                String officer      = values[8].trim();
                int visibility      = Integer.parseInt(values[9].trim());
                FlatType flatType = (values[10].equals("3-Room")) ? FlatType.THREEROOM : FlatType.TWOROOM;

            //             private String projectName;
            // private String neighborhood;
            // private int numUnitsType1;
            // private double priceType1;
            // private Date openingDate;
            // private Date closingDate;
            // private String manager;
            // private int officerSlot;
            // private String officers;
            // private int visibility;

                if(numberOfUnits != 0 && visibility == 1 && flatType == FlatType.TWOROOM) {
                    Room record = new Room(projectName, neighborhood, numberOfUnits, price,
                            openingDate, closingDate, manager,
                            officerSlot, officer, visibility, flatType);
                    records.add(record);
                }
            }
        } catch(IOException e){
            System.err.println("Error loading Room.csv: " + e.getMessage());
        }
        return records; //return null i the tworrom csv is not found.
    }

    public List<Room> loadAvailableThreeRooms(){ //for Date
        List<Room> records = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            boolean isHeader = true; //skip the header

            while((line = br.readLine()) != null){
                if(isHeader){
                    isHeader = false;
                    continue;
                }
                //String[] values = line.split(",", -1);
                // Use regex to avoid splitting commas that are inside quotes.
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // if (values.length < 10) {
                //     System.out.println("Skipping line with insufficient data: " + line);
                //     continue;
                // }

                // Parse required columns (assuming columns are as described above)
                String projectName  = values[0].trim();
                String neighborhood = values[1].trim();
                int numberOfUnits   = Integer.parseInt(values[2].trim()); // <= might throw NumberFormatException if invalid
                double price        = Double.parseDouble(values[3].trim());
                // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                // Date openingDate = sdf.parse(values[4].trim());
                // Date closingDate = sdf.parse(values[5].trim());
                String openingDate = values[4].trim();
                String closingDate = values[5].trim();
                String manager      = values[6].trim();
                int officerSlot     = values[7].trim().isEmpty() ? 0 : Integer.parseInt(values[7].trim());
                String officer      = values[8].trim();
                int visibility      = Integer.parseInt(values[9].trim());
                FlatType flatType = (values[10].equals("3-Room")) ? FlatType.THREEROOM : FlatType.TWOROOM;

                //             private String projectName;
                // private String neighborhood;
                // private int numUnitsType1;
                // private double priceType1;
                // private Date openingDate;
                // private Date closingDate;
                // private String manager;
                // private int officerSlot;
                // private String officers;
                // private int visibility;

                if(numberOfUnits != 0 && visibility == 1 && flatType == FlatType.THREEROOM) {
                    Room record = new Room(projectName, neighborhood, numberOfUnits, price,
                            openingDate, closingDate, manager,
                            officerSlot, officer, visibility,flatType);
                    records.add(record);
                }
            }
        } catch(IOException e){
            System.err.println("Error loading Room.csv: " + e.getMessage());
        }
        return records; //return null i the tworrom csv is not found.
    }
}
