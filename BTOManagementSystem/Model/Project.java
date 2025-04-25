package BTOManagementSystem.Model;

import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Represents a Build-To-Order (BTO) project within the HDB system.
 * <p>
 * Each project includes attributes like name, location (neighbourhood),
 * available flat types with units and prices, application window,
 * manager in charge, officer slots, assigned officers, and visibility status.
 */
public class Project {

    private String name;
    private String Neighbourhood;
    private String type1;
    private int Type1_numofunits;
    double Type1_sellingprice;

    String Type2;
    int Type2_numofunits;
    double type2_selling_price;
    LocalDate openingDate;
    LocalDate closingDate;
    String manager;
    int officerslots;

    private ArrayList<String> officers;
    private int visibility = 0;

    /**
     * Constructs a new Project with all required details.
     *
     * @param name                the project name
     * @param Neighbourhood       the location of the project
     * @param type1               the first flat type (e.g., 2-Room)
     * @param Type1_numofunits    number of units for type1
     * @param Type1_sellingprice  selling price for type1
     * @param Type2               the second flat type (e.g., 3-Room)
     * @param Type2_numofunits    number of units for type2
     * @param type2_selling_price selling price for type2
     * @param openingDate         application opening date (format: dd-MM-yyyy)
     * @param closingDate         application closing date (format: dd-MM-yyyy)
     * @param manager             manager's name
     * @param officerslots        available officer slots
     * @param officers            comma-separated list of officer names
     * @param visibility          1 = visible, 0 = hidden
     */
    public Project(String name, String Neighbourhood, String type1 ,int Type1_numofunits, double Type1_sellingprice,
                   String Type2, int Type2_numofunits, double type2_selling_price,
                   String openingDate, String closingDate, String manager, int officerslots , String officers ,int visibility) {

        this.name = name;
        this.Neighbourhood = Neighbourhood;
        this.type1 = type1;
        this.Type1_numofunits = Type1_numofunits;
        this.Type1_sellingprice = Type1_sellingprice;

        this.Type2 = Type2;
        this.Type2_numofunits = Type2_numofunits;
        this.type2_selling_price = type2_selling_price;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        this.openingDate = LocalDate.parse(openingDate,formatter);
        this.closingDate = LocalDate.parse(closingDate,formatter);

        this.manager = manager;
        this.officerslots = officerslots;

        String[] officer_arr = officers.split(",");

        if (officer_arr[0].matches("[a-zA-Z]+")){
            this.officers = new ArrayList<>(Arrays.asList(officer_arr));}
        else{
            this.officers = new ArrayList<>();
        }

        this.visibility = visibility;
    }

    /** @return the project name */
    public String getName() {
        return name;
    }

    /** @param name the project name to set */
    public void setName(String name) {
        this.name = name;
    }

    /** @return the neighbourhood the project is located in */
    public String getNeighbourhood() {
        return Neighbourhood;
    }

    /** @param Neighbourhood the neighbourhood name to set */
    public void setNeighbourhood(String Neighbourhood) {
        this.Neighbourhood = Neighbourhood;
    }

    /** @return the first flat type (e.g., "2-Room") */
    public String getType1() {
        return type1;
    }

    /** @param type1 set the first flat type */
    public void setType1(String type1) {
        this.type1 = type1;
    }

    /** @return the number of units available for the first flat type */
    public int getType1_numofunits() {
        return Type1_numofunits;
    }

    /** @param Type1_numofunits number of units to set for the first flat type */
    public void setType1_numofunits(int Type1_numofunits) {
        this.Type1_numofunits = Type1_numofunits;
    }

    /** @return selling price of the first flat type */
    public double getType1_sellingprice() {
        return Type1_sellingprice;
    }

    /** @param Type1_sellingprice the price to set for type1 */
    public void setType1_sellingprice(double Type1_sellingprice) {
        this.Type1_sellingprice = Type1_sellingprice;
    }

    /** @return the second flat type */
    public String getType2() {
        return Type2;
    }

    /** @param Type2 the second flat type to set */
    public void setType2(String Type2) {
        this.Type2 = Type2;
    }

    /** @return the number of units available for type2 */
    public int getType2_numofunits() {
        return Type2_numofunits;
    }

    /** @param Type2_numofunits the unit count to set for type2 */
    public void setType2_numofunits(int Type2_numofunits) {
        this.Type2_numofunits = Type2_numofunits;
    }

    /** @return selling price of the second flat type */
    public double getType2_selling_price() {
        return type2_selling_price;
    }

    /** @param type2_selling_price the price to set for type2 */
    public void setType2_selling_price(double type2_selling_price) {
        this.type2_selling_price = type2_selling_price;
    }

    /** @return the project’s application opening date */
    public LocalDate getOpeningDate() {
        return openingDate;
    }

    /** @return formatted opening date string (dd-MM-yyyy) */
    public String getOpeningDateAsString() {
        return openingDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /** @param openingDate the opening date to set (in dd-MM-yyyy format) */
    public void setOpeningDate(String openingDate) {
        this.openingDate = LocalDate.parse(openingDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /** @return the project’s application closing date */
    public LocalDate getClosingDate() {
        return closingDate;
    }

    /** @return formatted closing date string (dd-MM-yyyy) */
    public String getClosingDateAsString() {
        return closingDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /** @param closingDate the closing date to set (in dd-MM-yyyy format) */
    public void setClosingDate(String closingDate) {
        this.closingDate = LocalDate.parse(closingDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /** @return the manager in charge of the project */
    public String getManager() {
        return manager;
    }

    /** @param manager set the manager's name */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /** @return number of officer slots still available */
    public int getOfficerslots() {
        return officerslots;
    }

    /** @param officerslots set the officer slot count */
    public void setOfficerslots(int officerslots) {
        this.officerslots = officerslots;
    }

    /** @return the list of officers assigned to the project */
    public List<String> get_officers() {
        return this.officers;
    }

    /**
     * Converts the list of officers into a comma-separated string wrapped in quotes
     * for safe CSV storage.
     *
     * @return a string suitable for CSV
     */
    public String get_officers_as_string_for_csv() {
        StringBuilder sb = new StringBuilder();
        sb.append('"');

        for (int i = 0; i < officers.size(); i++) {
            sb.append(officers.get(i));
            if (i != officers.size() - 1) sb.append(",");
        }

        sb.append('"');
        return sb.toString();
    }

    /**
     * Adds a new officer to the list if the name is not empty.
     *
     * @param officer the officer's name to add
     */
    public void add_Officer(String officer) {
        if (!officer.equalsIgnoreCase("")) {
            this.officers.add(officer);
        }
    }

    /** @return current remaining officer slots */
    public int getNumberofCurrentOfficers() {
        return this.officerslots;
    }

    /** @return visibility status (1 = visible, 0 = hidden) */
    public int getVisibility() {
        return visibility;
    }

    /** @param visibility sets the visibility status (1 = visible, 0 = hidden) */
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

}
