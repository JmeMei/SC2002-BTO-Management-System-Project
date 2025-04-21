package BTOManagementSystem.Model.DAO;


import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class ProjectListDAO {

    private String[] headers = {"Project Name",
            "Neighborhood",
            "Type 1",
            "Number of units for Type 1",
            "Selling price for Type 1",
            "Type 2",
            "Number of units for Type 2",
            "Selling price for Type 2",
            "Application opening date",
            "Application closing date",
            "Manager",
            "Officer Slot",
            "Officer",
            "visibility"};

    private String filePath = "BTOManagementSystem/Data/ProjectList.csv";
    ArrayList<Project> ProjectsList = new ArrayList<>();

    public ProjectListDAO() {



        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                ArrayList<String> values = CSV_data_Parse(line);

                String name = values.get(0);
                String Neighbourhood = values.get(1);
                String type1 =  values.get(2);

                int Type1_numofunits = Integer.parseInt(values.get(3));

                double Type1_sellingprice =  Double.parseDouble(values.get(4));

                String Type2 =  values.get(5);
                int Type2_numofunits =  Integer.parseInt(values.get(6));
                double type2_sellling_price = Double.parseDouble(values.get(7));



                String openingDate = values.get(8);
                String closingDate = values.get(9);


                String manager = values.get(10);
                int officerslots =  Integer.parseInt(values.get(11));

                String officers = values.get(12).substring(1, values.get(12).length()-1);

                int visibility = Integer.parseInt(values.get(13));

                Project newProject = new Project(name,Neighbourhood,type1, Type1_numofunits, Type1_sellingprice,
                        Type2,Type2_numofunits,type2_sellling_price,openingDate,closingDate,manager,officerslots,officers,visibility);

                ProjectsList.add(newProject);

            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }


    }



    public void CreateNewProject(ArrayList<String> Data){

        Project newProject = new Project(Data.get(0),Data.get(1),Data.get(2),Integer.parseInt(Data.get(3)),
                Double.parseDouble(Data.get(4)), Data.get(5), Integer.parseInt(Data.get(6)) , Double.parseDouble(Data.get(7)), Data.get(8), Data.get(9), App.userSession.getName() , Integer.parseInt(Data.get(10)), "", 0);

        this.ProjectsList.add(newProject);
        this.UpdateDB();

    }

    public void UpdateDB(){


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write("Project Name,Neighborhood,Type 1,Number of units for Type 1,Selling price for Type 1,Type 2,Number of units for Type 2,Selling price for Type 2,Application opening date,Application closing date,Manager,Officer Slot,Officer,visibility");
            writer.newLine();

            // Write records
            for (Project p :ProjectsList) {
                writer.write(String.format("%s,%s,%s,%d,%f,%s,%d,%f,%s,%s,%s,%d,%s,%d",

                        p.getName(),
                        p.getNeighbourhood(),
                        p.getType1(),
                        p.getType1_numofunits(),
                        p.getType1_sellingprice(),
                        p.getType2(),
                        p.getType2_numofunits(),
                        p.getType2_sellling_price(),
                        p.getOpeningDateAsString(),
                        p.getOpeningDateAsString(),
                        p.getManager(),
                        p.getOfficerslots(),
                        p.get_officers_as_string_for_csv(),
                        p.getVisibility()
                        )
                );
                writer.newLine();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void editProjectDetails(Project project, int attribute, String value){

        switch (attribute){

            case 1:

                project.setName(value);
                break;

            case 2:
                project.setNeighbourhood(value);
                break;

            case 3:
                project.setType1(value);
                break;
            case 4:
                project.setType1_numofunits(Integer.parseInt(value));
                break;

            case 5:
                project.setType1_sellingprice(Double.parseDouble(value));
                break;

            case 6:
                project.setType2(value);
                break;
            case 7:
                project.setType2_numofunits(Integer.parseInt(value));
                break;
            case 8:
                project.setType2_sellling_price(Double.parseDouble(value));
                break;

            case 9:
                project.setOpeningDate(value);
                break;
            case 10:
                project.setClosingDate(value);
                break;

            case 11:
                project.setOfficerslots(Integer.parseInt(value));
                break;

            case 12:
                project.setVisibility(Integer.parseInt(value));
                break;
        }

        this.UpdateDB();

    }

    public Project getProjectFromStringName(String ProjectName){

        for (Project p : ProjectsList) {

            if (p.getName().equals(ProjectName)) {

                return p;
            }

        }

        return null;
    }

    public int AddOfficerToProject(String officerName, String ProjectName){

        for (Project p : ProjectsList) {

            if (p.getName().equals(ProjectName)) {

                if (p.getNumberofCurrentOfficers() > 0 ){
                    p.add_Officer(officerName);
                    p.setOfficerslots(p.getOfficerslots() - 1);
                    UpdateDB();
                    return 1;


                }
                else{
                    return -1;
                }

            }

        }

        return 0;

    }

    public boolean IsManaging(String ProjectName, String MangerName){

        for (Project p : ProjectsList) {

            if (p.getName().equals(ProjectName)) {

                if (p.getManager().equals(MangerName)) {

                    return true;
                }
                else{
                    return false;
                }
            }

        }

        return false;
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

        for(Project p : ProjectsList){

            if (p.getName().equals(projectName)){

                ProjectsList.remove(p);
                this.UpdateDB();
                return true;

            }

        }

        return false;
    }

    public String[] getHeaders(){
        return this.headers;
    }

    public ArrayList<Project> LoadProjects (int filter){

        ArrayList<Project> projectsToReturn = new ArrayList<>();
        if (filter == 1){

            for (Project p : ProjectsList) {

                if (p.getManager().equals(App.userSession.getName())){

                    projectsToReturn.add(p);

                }

            }

            return projectsToReturn;


        }else{
            return this.ProjectsList;
        }

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

    // get list of projectNames(enq)
    public List<String> getProjectNames() {
        List<String> projectNames = new ArrayList<>();
        for (Project project : ProjectsList) {
            projectNames.add(project.getName());
        }
        return projectNames;
    }

    // get officers in charge of the project(enq) 1 officer for now
    public String getOfficerIC(String projectName){
        for (Project p: ProjectsList){
            if(p.getName().equalsIgnoreCase(projectName)){
                List<String> all_officers = p.get_offficers();
                if(all_officers.isEmpty()){
                    return "";
                }
                else{
                    return all_officers.get(0);
                }
            }
        }
        return null;
    }

    // get manager in charge of the project(enq)
    public String getManagerbyProject(String projectName){
        for (Project p : ProjectsList) {              
            if (p.getName().equalsIgnoreCase(projectName)) {
                return p.getManager();                
            }
        }
        return null;
    }

    /////FOR APPLICANT/////
    public List<Project> loadAvailableTwoRooms(User user){

        List<Project> availableTwoRooms = new ArrayList<>();
        List<String> projectNameAvailable = new ArrayList<>();

        for (Project p : ProjectsList) {
            // Check Flat Type
            FlatType flatType1 = FlatType.fromString(p.getType1());
            FlatType flatType2 = FlatType.fromString(p.getType2());
            if(flatType1 == FlatType.TWO_ROOM|| flatType2 == FlatType.TWO_ROOM && p.getVisibility() == 1){
                availableTwoRooms.add(p);
            }
        }

        return availableTwoRooms;
    }

    public List<Project> loadAvailableThreeRooms(User user){

        List<Project> availableThreeRooms = new ArrayList<>();
        List<String> projectNameAvailable = new ArrayList<>();

        for (Project p : ProjectsList) {
            FlatType flatType1 = FlatType.fromString(p.getType1());
            FlatType flatType2 = FlatType.fromString(p.getType2());
            if(flatType1 == FlatType.THREE_ROOM || flatType2 == FlatType.THREE_ROOM && p.getVisibility() == 1){
                availableThreeRooms.add(p);
            }
        }

        return availableThreeRooms;
    }

    public void getRoomType(String projectName) {

    }


//    public List<Room> loadAvailableThreeRooms(User user){
//        //final String FILE_PATH = "BTOManagementSystem/Data/ProjectList.csv";
//
//        List<Room> records = new ArrayList<>();
//        List<String> projectNameAvailable = new ArrayList<>();
//
//        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
//            String line;
//            boolean isHeader = true; //skip the header
//
//            while((line = br.readLine()) != null){
//                if(isHeader){
//                    isHeader = false;
//                    continue;
//                }
//                // Use regex to avoid splitting commas that are inside quotes.
//                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                //String[] values = line.split(",");
//
//                /*
//                *
//                    0 private String name;
//                    1 private String Neighbourhood;
//                    2 private String type1;
//                    3 private int Type1_numofunits;
//                    4 double Type1_sellingprice;
//
//                    5 String Type2;
//                    6 int Type2_numofunits;
//                    7 double type2_sellling_price;
//
//                    8 LocalDate openingDate;
//                    9 LocalDate closingDate;
//                    10 String manager;
//                    11 int officerslots;
//                    12 private List<String> officers;
//                    13 private int visibility = 0;
//                */
//
//                // Parse required columns (assuming columns are as described above)
//                String projectName  = values[0].trim();
//                String neighborhood = values[1].trim();
//                String Type1 = values[2].trim();
//                int Type1_numofunits = Integer.parseInt(values[3].trim());
//                double Type1_sellingprice = Double.parseDouble(values[4].trim());
//
//                String Type2 = values[5].trim();
//                int Type2_numofunits = Integer.parseInt(values[6].trim());
//                double Type2_sellingprice = Double.parseDouble(values[7].trim());
//
//                LocalDate openingDate = null;
//                LocalDate closingDate = null;
//
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                try {
//                    openingDate = LocalDate.parse(values[8].trim(), formatter);
//                    closingDate = LocalDate.parse(values[9].trim(), formatter);
//
//                    // You can now use openingDate and closingDate as LocalDate objects
//                } catch (DateTimeParseException e) {
//                    System.out.println("Invalid date format in CSV: " + e.getMessage());
//                    continue; // Skip this row or handle differently
//                }
//
//                String manager = values[10].trim();
//                int officerSlot = Integer.parseInt(values[11].trim());
//                String rawOfficerList = values[12].replaceAll("^\"|\"$", "").trim(); // remove surrounding quotes
//                List<String> officers = Arrays.stream(rawOfficerList.split(","))
//                                    .map(String::trim)
//                                    .collect(Collectors.toList());
//                int visibility = Integer.parseInt(values[13].trim());
//
//                //***FOR DEBUGGING, YOU CAN PRINT EVERYTHING FIRST***//
//                //System.out.println(Arrays.toString(values));
//
//                /*
//                private String projectName;
//                private String neighborhood;
//                private int numUnitsType;
//                private double priceType;
//                private LocalDate openingDate;
//                private LocalDate closingDate;
//                private FlatType flatType;
//                */
//                if(Type2_numofunits != 0 && visibility == 1 && !officers.contains(user.getName())) {
//                    Room record = new Room(projectName, neighborhood, Type1_numofunits, Type1_sellingprice,
//                                            //Type2, Type2_numofunits, Type2_sellingprice,
//                                            openingDate, closingDate, FlatType.TWOROOM);
//                    records.add(record);
//                    projectNameAvailable.add(projectName);
//                }
//            }
//        } catch(IOException e){
//            System.err.println("Error loading Room.csv: " + e.getMessage());
//        }
//        return records; //return null i the tworrom csv is not found.
//    }



}
