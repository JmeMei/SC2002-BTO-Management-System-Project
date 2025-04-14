package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import BTOManagementSystem.Model.Room;

public class ApplicantProjectStatusDAO {
    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantProjectStatus.csv";

    public static void applyForProject(List<Room> roomsAvailable, String projectName) {
        List<String[]> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            
            String header = reader.readLine(); //read the header
            fileContent.add(header.split(",")); //store the header

            String line;
            while((line = reader.readLine()) != null){
                String[] values = line.split(",");

                //If the ProjectName match, change the password
                //Name,NRIC,Age,Marital Status,Password,role
                if(values[1].trim().equalsIgnoreCase(NRIC)) values[4] = newPassword;

                fileContent.add(values);
            }

        }catch (IOException e){
            System.out.println("Error reading the userlogin.csv " + e.getMessage());
            return false;
        }

        //Write the updated content back to the file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            for(String[] values : fileContent){
                writer.write(String.join(",", values));
                writer.newLine();
            }
            return true;
        } catch (IOException e){
            System.out.println("Error writing back to the userlogin.csv " + e.getMessage());
        }
        return false;
    }//END of applyForProject

    // public static void applyForProject(List<Room> roomsAvailable, String projectName){
    //     try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
    //         String line;
    //         boolean isHeader = true; //skip the header

    //         while((line = br.readLine()) != null){
    //             if(isHeader){
    //                 isHeader = false;
    //                 continue;
    //             }
    //             //String[] values = line.split(",", -1);
    //             // Use regex to avoid splitting commas that are inside quotes.
    //             String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

    //             // if (values.length < 10) {
    //             //     System.out.println("Skipping line with insufficient data: " + line);
    //             //     continue;
    //             // }

    //             //Name,NRIC,Age,Marital Status,Project Name,Type, Application Status, Enquiry, Reply

    //             // Parse required columns (assuming columns are as described above)
    //             String Name  = values[0].trim();
    //             String NRIC = values[1].trim();
    //             int Age   = Integer.parseInt(values[2].trim()); // <= might throw NumberFormatException if invalid
    //             // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    //             // Date openingDate = sdf.parse(values[4].trim());
    //             // Date closingDate = sdf.parse(values[5].trim());
    //             String MaritalStatus = values[3].trim();
    //             String ProjectName = values[4].trim();
    //             String Type      = values[5].trim();
    //             ApplicationStatus ApplicationStatus = ApplicationStatus.valueOf(values[6].trim().toUpperCase());
    //             String Enquiry      = values[7].trim();
    //             String Reply      = values[8].trim();

    //             if(numberOfUnits != 0 && visibility == 1 && flatType == FlatType.TWOROOM) {
    //                 Room record = new Room(projectName, neighborhood, numberOfUnits, price,
    //                         openingDate, closingDate, manager,
    //                         officerSlot, officer, visibility, flatType);
    //                 records.add(record);
    //             }
    //         }
    //     } catch(IOException e){
    //         System.err.println("Error loading Room.csv: " + e.getMessage());
    //     }

    // }

}
