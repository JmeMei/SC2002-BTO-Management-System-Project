package BTOManagementSystem.Model.DAO;


import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * DAO (Data Access Object) for managing BTO Project data.
 * <p>
 * Handles loading, filtering, updating, and storing project details for
 * managers, officers, and applicants. Data is persisted in a CSV file.
 */
public class ProjectListDAO {

    private String[] headers;

    private String filePath = "BTOManagementSystem/Data/ProjectList.csv";
    private static ArrayList<Project> ProjectsList = new ArrayList<>();

    /**
     * Initializes and loads all projects from the CSV file into a Project List.
     */
    public ProjectListDAO() {
        ProjectsList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            line = br.readLine();
            this.headers = line.split(",");

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

                String officers = values.get(12).replaceAll("^\"+|\"+$", "");

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

    /**
     * Creates a new BTO project using the provided data and adds it to the system.
     *
     * @param Data a list of project attributes in the expected order
     */
    public void CreateNewProject(ArrayList<String> Data){

        Project newProject = new Project(Data.get(0),Data.get(1),Data.get(2),Integer.parseInt(Data.get(3)),
                Double.parseDouble(Data.get(4)), Data.get(5), Integer.parseInt(Data.get(6)) , Double.parseDouble(Data.get(7)), Data.get(8), Data.get(9), App.userSession.getName() , Integer.parseInt(Data.get(10)), "", 0);

        this.ProjectsList.add(newProject);
        this.UpdateDB();

    }

    /**
     * Filters a given list of projects by neighbourhood.
     *
     * @param projects the list of {@link Project} objects to filter
     * @param value    the neighbourhood name to filter by
     * @return a filtered list of projects located in the specified neighbourhood
     */
    public ArrayList<Project> filterByNeighbourhood(ArrayList<Project> projects ,String value){
        return projects.stream().filter( r -> value.equalsIgnoreCase(r.getNeighbourhood())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Updates the CSV file with current project data in Project List
     */
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
                        p.getType2_selling_price(),
                        p.getOpeningDateAsString(),
                        p.getClosingDateAsString(),
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

    /**
     * Decreases the number of available flat units of a given type in a project.
     *
     * @param project     the project to update
     * @param appliedType the flat type whose unit count will be decreased
     */
    public void decreaseFlatUnits(Project project, FlatType appliedType) {
        // Check Type 1 if 2 or 3 Room
        if (FlatType.fromString(project.getType1()) == appliedType) {
            project.setType1_numofunits(project.getType1_numofunits() - 1);
        }
        // Check Type 2 if 2 or 3 Room
        else if (FlatType.fromString(project.getType2()) == appliedType) {
            project.setType2_numofunits(project.getType2_numofunits() - 1);
        }
        this.UpdateDB();
    }

    /**
     * Edits a specific attribute of a project and updates the storage.
     *
     * @param project   the project to modify
     * @param attribute the attribute index (1-based)
     * @param value     the new value to assign
     */
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
                project.setType2_selling_price(Double.parseDouble(value));
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

    /**
     * Retrieves a project object by its name.
     *
     * @param ProjectName the name of the project
     * @return the matching {@link Project}, or {@code null} if not found
     */
    public Project getProjectFromStringName(String ProjectName){

        for (Project p : ProjectsList) {

            if (p.getName().equalsIgnoreCase(ProjectName)) {

                return p;
            }

        }

        return null;
    }

    /**
     * Adds an officer to a project if slots are available.
     *
     * @param officerName the name of the officer
     * @param ProjectName the name of the project
     * @return 1 if success, -1 if full, 0 if project not found
     */
    public int AddOfficerToProject(String officerName, String ProjectName){

        for (Project p : ProjectsList) {

            if (p.getName().equalsIgnoreCase(ProjectName)) {

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

    /**
     * Checks whether the given manager is managing the specified project.
     *
     * @param ProjectName the project name
     * @param ManagerName the manager's name
     * @return {@code true} if managing, otherwise {@code false}
     */
    public boolean IsManaging(String ProjectName, String ManagerName){

        for (Project p : ProjectsList) {

            if (p.getName().equalsIgnoreCase(ProjectName)) {

                if (p.getManager().equalsIgnoreCase(ManagerName)) {

                    return true;
                }
                else{
                    return false;
                }
            }

        }

        return false;
    }

    /**
     * Edits a project in the CSV file using the attribute index and a new value.
     *
     * @param projectName     the name of the project
     * @param attributeIndex  the attribute to update
     * @param NewValue        the new value to set
     */
    public void editAProject(String projectName, int attributeIndex, String NewValue){

        List<String[]> rows = new ArrayList<>();

        //Reading
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] aRow = line.split(",");
                if (aRow[0].compareTo(projectName) == 0){

                    String old_value = "";
                    if (attributeIndex <= 10){

                        old_value = aRow[attributeIndex - 1];
                        aRow[attributeIndex - 1] = NewValue;

                    } else{

                        if (attributeIndex == 11){
                            old_value = aRow[11];
                            aRow[11] = NewValue;
                        }

                        else if (attributeIndex == 12){
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

    /**
     * Deletes a project by name from the system and updates the CSV.
     *
     * @param projectName the name of the project
     * @return {@code true} if deleted, otherwise {@code false}
     */
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

    /**
     * Retrieves the CSV headers for project data.
     *
     * @return an array of column headers
     */
    public String[] getHeaders(){
        return this.headers;
    }

    /**
     * Loads and optionally filters the list of projects for a manager or all.
     *
     * @param filter 1 = Manager's projects, else all
     * @return a list of filtered {@link Project} objects
     */
    public ArrayList<Project> LoadProjects (int filter){

        ArrayList<Project> projectsToReturn = new ArrayList<>();


        if (filter == 1){

            for (Project p : ProjectsList) {

                if (p.getManager().equals(App.userSession.getName())){

                    projectsToReturn.add(p);

                }

            }


        } else{

            for (Project p : ProjectsList) {
                    projectsToReturn.add(p);
            }
        }

        projectsToReturn = projectsToReturn.stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toCollection(ArrayList::new));

        return projectsToReturn;
    }

    /**
     * Parses and filters a project based on manager/neighborhood/type filters.
     */
    private boolean filterCheck(String code, Project p, String filterValue){

        if(code.charAt(0) == '1'){

            if(!p.getManager().equals(App.userSession.getName())){

                return false;
            }

        }

        if(code.charAt(1) == '1'){

            if (!p.getNeighbourhood().equals(filterValue)){
                return false;
            }

        }

        if (code.charAt(1) == '2'){

            if (!p.getType1().equals(filterValue) && !p.getType2().equals(filterValue)){
                return false;
            }

        }

        return true;
   }

    /**
     * Returns all projects as raw row data (used for CSV/table views).
     *
     * @return a list of project rows
     */
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

    /**
     * Parses a CSV line while handling quoted fields.
     *
     * @param line the CSV line
     * @return a parsed list of column values
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
     * Gets all project rows where the given name is the manager.
     *
     * @param Name the manager's name
     * @return list of project rows
     */
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
    /**
     * Returns a list of all project names currently loaded in project List
     *
     * @return list of project names
     */
    public List<String> getProjectNames() {
        List<String> projectNames = new ArrayList<>();
        for (Project project : ProjectsList) {
            projectNames.add(project.getName());
        }
        return projectNames;
    }

    /**
     * Checks if the manager has any active (not closed) projects.
     *
     * @param ManagerName the manager's name
     * @return {@code 1} if yes, {@code 0} if none
     */
    public int ManagerHasActiveProject(String ManagerName){

        for (Project p : ProjectsList) {

            if (p.getManager().equals(ManagerName)){

                if(p.getClosingDate().isAfter(LocalDate.now())){
                    return 1;
                }

            }

        }
        return 0;

    }

    /**
     * Gets the first officer's NRIC for a project.
     *
     * @param projectName the project name
     * @return NRIC of the first officer, or {@code null} if not found
     */
    public String getOfficerIC(String projectName){
        for (Project p: ProjectsList){
            if(p.getName().equals(projectName)){
                return p.get_officers().get(0);
            }
        }
        return null;
    }

    /**
     * Returns the project name assigned to the given officer.
     *
     * @param officerName the name of the officer
     * @return the project name, or {@code null} if not assigned
     */
    // get officers in charge of the project(enq) 1 officer for now
    public String getProjectNamefromOfficerName(String officerName){
        for (Project p: ProjectsList){
            for (String officer : p.get_officers()){
                if (officer.equalsIgnoreCase(officerName)){
                    return p.getName();
                }
            }

            }
        return null;
    }


    // get manager in charge of the project(enq)
    /**
     * Gets the name of the manager assigned to a specific project.
     *
     * @param projectName the name of the project
     * @return the manager's name, or {@code null} if not found
     */
    public String getManagerbyProject(String projectName){
        for (Project p : ProjectsList) {              
            if (p.getName().equalsIgnoreCase(projectName)) {
                return p.getManager();                
            }
        }
        return null;
    }


    /**
     * Loads all projects that offer 2-Room flats and are visible.
     *
     * @param user the user requesting available projects
     * @return list of 2-Room {@link Project} objects
     */
    public List<Project> loadAvailableTwoRooms(User user){

        List<Project> availableTwoRooms = new ArrayList<>();
        List<String> projectNameAvailable = new ArrayList<>();

        for (Project p : ProjectsList) {
            // Check Flat Type
            FlatType flatType1 = FlatType.fromString(p.getType1());
            FlatType flatType2 = FlatType.fromString(p.getType2());
            if(flatType1 == FlatType.TWO_ROOM && p.getVisibility() == 1 || flatType2 == FlatType.TWO_ROOM && p.getVisibility() == 1){
                availableTwoRooms.add(p);
            }
        }

        return availableTwoRooms;
    }

    /**
     * Loads all projects that offer 3-Room flats and are visible.
     *
     * @param user the user requesting available projects
     * @return list of 3-Room {@link Project} objects
     */
    public List<Project> loadAvailableThreeRooms(User user){

        List<Project> availableThreeRooms = new ArrayList<>();
        List<String> projectNameAvailable = new ArrayList<>();

        for (Project p : ProjectsList) {
            FlatType flatType1 = FlatType.fromString(p.getType1());
            FlatType flatType2 = FlatType.fromString(p.getType2());
            if(flatType1 == FlatType.THREE_ROOM && p.getVisibility() == 1 || flatType2 == FlatType.THREE_ROOM && p.getVisibility() == 1){
                availableThreeRooms.add(p);
            }
        }

        return availableThreeRooms;
    }
}
