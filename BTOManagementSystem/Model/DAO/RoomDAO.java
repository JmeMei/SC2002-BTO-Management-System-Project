package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import BTOManagementSystem.Model.Room;
import BTOManagementSystem.Model.DAO.Enum.*;

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
                // Use regex to avoid splitting commas that are inside quotes.
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                //String[] values = line.split(",");
                
                // Parse required columns (assuming columns are as described above)
                int ID = Integer.parseInt(values[0].trim());
                String projectName  = values[1].trim();
                String neighborhood = values[2].trim();
                int numberOfUnits   = Integer.parseInt(values[3].trim()); // <= might throw NumberFormatException if invalid
                double price        = Double.parseDouble(values[4].trim());
                // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                // Date openingDate = sdf.parse(values[4].trim());
                // Date closingDate = sdf.parse(values[5].trim());
                String openingDate = values[5].trim();
                String closingDate = values[6].trim();
                String manager      = values[7].trim();
                int officerSlot     = values[8].trim().isEmpty() ? 0 : Integer.parseInt(values[8].trim());
                String rawOfficerList  = values[9].replaceAll("^\"|\"$", "").trim(); // "Daniel,Emily" => Daniel,Emily
                List<String> officer = Arrays.stream(rawOfficerList.split(","))
                             .map(String::trim)
                             .collect(Collectors.toList());
                int visibility      = Integer.parseInt(values[10].trim());
                FlatType flatType = (values[11].equals("3-Room")) ? FlatType.THREEROOM : FlatType.TWOROOM;
                
                //***FOR DEBUGGING, YOU CAN PRINT EVERYTHING FIRST***//
                //System.out.println(Arrays.toString(values));

                if(numberOfUnits != 0 && visibility == 1 && flatType == FlatType.TWOROOM) {
                    Room record = new Room(ID,projectName, neighborhood, numberOfUnits, price,
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

                // Use regex to avoid splitting commas that are inside quotes.
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                //String[] values = line.split(",");

                // Parse required columns (assuming columns are as described above)
                int ID = Integer.parseInt(values[0].trim());
                String projectName  = values[1].trim();
                String neighborhood = values[2].trim();
                int numberOfUnits   = Integer.parseInt(values[3].trim()); // <= might throw NumberFormatException if invalid
                double price        = Double.parseDouble(values[4].trim());
                // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                // Date openingDate = sdf.parse(values[4].trim());
                // Date closingDate = sdf.parse(values[5].trim());
                String openingDate = values[5].trim();
                String closingDate = values[6].trim();
                String manager      = values[7].trim();
                int officerSlot     = values[8].trim().isEmpty() ? 0 : Integer.parseInt(values[8].trim());
                String rawOfficerList  = values[9].replaceAll("^\"|\"$", "").trim(); // "Daniel,Emily" => Daniel,Emily
                List<String> officer = Arrays.stream(rawOfficerList.split(","))
                             .map(String::trim)
                             .collect(Collectors.toList());
                int visibility      = Integer.parseInt(values[10].trim());
                FlatType flatType = (values[11].equals("3-Room")) ? FlatType.THREEROOM : FlatType.TWOROOM;

                //***FOR DEBUGGING, YOU CAN PRINT EVERYTHING FIRST***//
                //System.out.println(Arrays.toString(values));

                if(numberOfUnits != 0 && visibility == 1 && flatType == FlatType.THREEROOM) {
                    Room record = new Room(ID,projectName, neighborhood, numberOfUnits, price,
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
