package BTOManagementSystem.Model.DAO;


import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.DAO.Enum.FlatType;

import java.io.FileWriter;
import java.io.IOException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ProjectListDAO {

    private String filePath = "BTOManagementSystem/Data/ProjectList.csv";

    public void writeANewProjectEntry(Project project){



        try (FileWriter writer = new FileWriter(filePath,true)) {

            writer.append(project.getName());
            writer.append(",");
            writer.append(project.getNeighbourhood());
            writer.append(",");
            writer.append(project.getType1());
            writer.append(",");
            writer.append(String.valueOf(project.getType1_numofunits()));
            writer.append(",");
            writer.append(String.valueOf(project.getType1_sellingprice()));
            writer.append(",");
            writer.append(project.getType2());
            writer.append(",");
            writer.append(String.valueOf(project.getType2_numofunits()));
            writer.append(",");
            writer.append(String.valueOf(project.getType2_sellling_price()));
            writer.append(",");
            writer.append(project.getOpeningDateAsString());
            writer.append(",");
            writer.append(project.getClosingDateAsString());
            writer.append(",");
            writer.append(project.getManager());
            writer.append(",");
            writer.append(String.valueOf(project.getOfficerslots()));
            writer.append(",");

            String joinedNames = String.join(",", project.get_offficers());

            joinedNames = "\"" + joinedNames + "\"";
            writer.append(joinedNames);

            writer.append(",");
            writer.append(String.valueOf(project.getVisibility()));

            writer.append("\n");

            System.out.println("New Project added successfully!");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }



    }

    public void editAProject(String projectName, int atrributeIndex, String NewValue){

        List<String[]> rows = new ArrayList<>();

        //Reading
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] aRow = line.split(",");
                if (aRow[0].compareTo(projectName) == 0){

                    String old_value = "";
                    if (atrributeIndex <= 10){

                        old_value = aRow[atrributeIndex - 1];
                        aRow[atrributeIndex - 1] = NewValue;

                    } else{

                        if (atrributeIndex == 11){
                            old_value = aRow[11];
                            aRow[11] = NewValue;
                        }

                        else if (atrributeIndex == 12){
                            old_value = aRow[13];
                            aRow[13] = NewValue;

                        }
                    }
                    System.out.println("data changed from " + old_value + " -> " + NewValue);

                }

                rows.add(aRow);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        try (FileWriter fw = new FileWriter(filePath)) {
            for (String[] row : rows) {
                fw.write(String.join(",", row) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public boolean DeleteProject(String projectName){

        List<String[]> rows = new ArrayList<>();

        //Reading
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] aRow = line.split(",");
                if (aRow[0].compareTo(projectName) != 0){


                    rows.add(aRow);

                }else{
                    found = true;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();

        }


        try (FileWriter fw = new FileWriter(filePath)) {
            for (String[] row : rows) {
                fw.write(String.join(",", row) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return found;

    }


    public ArrayList<ArrayList<String>> get_all_Projects(){

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

    public ArrayList<ArrayList<String>> get_filtered_Projects(String Name){

        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> values = CSV_data_Parse(line);

                if(values.get(10).equals(Name)){
                    rows.add(values);
                }

            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }



        return rows;
    }


    /////FOR APPLICANT/////
    public List<Room> loadAvailableTwoRooms(User user){ 
        //final String FILE_PATH = "BTOManagementSystem/Data/ProjectList.csv";

        List<Room> records = new ArrayList<>();
        List<String> projectNameAvailable = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
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
                
                /*
                 * 
                    0 private String name;
                    1 private String Neighbourhood;
                    2 private String type1;
                    3 private int Type1_numofunits;
                    4 double Type1_sellingprice;

                    5 String Type2;
                    6 int Type2_numofunits;
                    7 double type2_sellling_price;

                    8 LocalDate openingDate;
                    9 LocalDate closingDate;
                    10 String manager;
                    11 int officerslots;
                    12 private List<String> officers;
                    13 private int visibility = 0;
                 */

                // Parse required columns (assuming columns are as described above)
                String projectName  = values[0].trim();
                String neighborhood = values[1].trim();
                String Type1 = values[2].trim();
                int Type1_numofunits = Integer.parseInt(values[3].trim());
                double Type1_sellingprice = Double.parseDouble(values[4].trim());

                String Type2 = values[5].trim();
                int Type2_numofunits = Integer.parseInt(values[6].trim());
                double Type2_sellingprice = Double.parseDouble(values[7].trim()); 

                LocalDate openingDate = null;
                LocalDate closingDate = null;
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
                try {
                     openingDate = LocalDate.parse(values[8].trim(), formatter);
                     closingDate = LocalDate.parse(values[9].trim(), formatter);
                    
                    // You can now use openingDate and closingDate as LocalDate objects
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format in CSV: " + e.getMessage());
                    continue; // Skip this row or handle differently
                }

                String manager = values[10].trim();
                int officerSlot = Integer.parseInt(values[11].trim());
                String rawOfficerList = values[12].replaceAll("^\"|\"$", "").trim(); // remove surrounding quotes
                List<String> officers = Arrays.stream(rawOfficerList.split(","))
                                     .map(String::trim)
                                     .collect(Collectors.toList());
                int visibility = Integer.parseInt(values[13].trim());

                //***FOR DEBUGGING, YOU CAN PRINT EVERYTHING FIRST***//
                //System.out.println(Arrays.toString(values));

                /*
                 private String projectName;
                private String neighborhood;
                private int numUnitsType;
                private double priceType;
                private LocalDate openingDate;
                private LocalDate closingDate;
                private FlatType flatType;
                 */
                if(Type1_numofunits != 0 && visibility == 1 && !officers.contains(user.getName())) {
                    Room record = new Room(projectName, neighborhood, Type1_numofunits, Type1_sellingprice,
                                            //Type2, Type2_numofunits, Type2_sellingprice,
                                            openingDate, closingDate, FlatType.TWOROOM);
                    records.add(record);
                    projectNameAvailable.add(projectName);
                }
            }
        } catch(IOException e){
            System.err.println("Error loading Room.csv: " + e.getMessage());
        }
        return records; //return null i the tworrom csv is not found.
    }


    public List<Room> loadAvailableThreeRooms(User user){ 
        //final String FILE_PATH = "BTOManagementSystem/Data/ProjectList.csv";

        List<Room> records = new ArrayList<>();
        List<String> projectNameAvailable = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
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
                
                /*
                * 
                    0 private String name;
                    1 private String Neighbourhood;
                    2 private String type1;
                    3 private int Type1_numofunits;
                    4 double Type1_sellingprice;

                    5 String Type2;
                    6 int Type2_numofunits;
                    7 double type2_sellling_price;

                    8 LocalDate openingDate;
                    9 LocalDate closingDate;
                    10 String manager;
                    11 int officerslots;
                    12 private List<String> officers;
                    13 private int visibility = 0;
                */

                // Parse required columns (assuming columns are as described above)
                String projectName  = values[0].trim();
                String neighborhood = values[1].trim();
                String Type1 = values[2].trim();
                int Type1_numofunits = Integer.parseInt(values[3].trim());
                double Type1_sellingprice = Double.parseDouble(values[4].trim());

                String Type2 = values[5].trim();
                int Type2_numofunits = Integer.parseInt(values[6].trim());
                double Type2_sellingprice = Double.parseDouble(values[7].trim()); 

                LocalDate openingDate = null;
                LocalDate closingDate = null;
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
                try {
                    openingDate = LocalDate.parse(values[8].trim(), formatter);
                    closingDate = LocalDate.parse(values[9].trim(), formatter);
                    
                    // You can now use openingDate and closingDate as LocalDate objects
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format in CSV: " + e.getMessage());
                    continue; // Skip this row or handle differently
                }

                String manager = values[10].trim();
                int officerSlot = Integer.parseInt(values[11].trim());
                String rawOfficerList = values[12].replaceAll("^\"|\"$", "").trim(); // remove surrounding quotes
                List<String> officers = Arrays.stream(rawOfficerList.split(","))
                                    .map(String::trim)
                                    .collect(Collectors.toList());
                int visibility = Integer.parseInt(values[13].trim());

                //***FOR DEBUGGING, YOU CAN PRINT EVERYTHING FIRST***//
                //System.out.println(Arrays.toString(values));

                /*
                private String projectName;
                private String neighborhood;
                private int numUnitsType;
                private double priceType;
                private LocalDate openingDate;
                private LocalDate closingDate;
                private FlatType flatType;
                */
                if(Type2_numofunits != 0 && visibility == 1 && !officers.contains(user.getName())) {
                    Room record = new Room(projectName, neighborhood, Type1_numofunits, Type1_sellingprice,
                                            //Type2, Type2_numofunits, Type2_sellingprice,
                                            openingDate, closingDate, FlatType.TWOROOM);
                    records.add(record);
                    projectNameAvailable.add(projectName);
                }
            }
        } catch(IOException e){
            System.err.println("Error loading Room.csv: " + e.getMessage());
        }
        return records; //return null i the tworrom csv is not found.
    }



}
