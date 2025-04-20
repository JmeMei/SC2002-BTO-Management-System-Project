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

public class Project {

    private String name;
    private String Neighbourhood;
    private String type1;
    private int Type1_numofunits;
    double Type1_sellingprice;

    String Type2;
    int Type2_numofunits;
    double type2_sellling_price;
    LocalDate openingDate;
    LocalDate closingDate;
    String manager;
    int officerslots;

    private ArrayList<String> officers;
    private int visibility = 0;

    public Project(String name, String Neighbourhood, String type1 ,int Type1_numofunits, double Type1_sellingprice,
                   String Type2, int Type2_numofunits, double type2_sellling_price,
                   String openingDate, String closingDate, String manager, int officerslots , String officers ,int visibility) {

        this.name = name;
        this.Neighbourhood = Neighbourhood;
        this.type1 = type1;
        this.Type1_numofunits = Type1_numofunits;
        this.Type1_sellingprice = Type1_sellingprice;

        this.Type2 = Type2;
        this.Type2_numofunits = Type2_numofunits;
        this.type2_sellling_price = type2_sellling_price;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        this.openingDate = LocalDate.parse(openingDate,formatter);
        this.closingDate = LocalDate.parse(closingDate,formatter);


        this.manager = manager;
        this.officerslots = officerslots;


        String[] officer_arr = officers.split(",");
        this.officers = new ArrayList<>(Arrays.asList(officer_arr));


        this.visibility = visibility;


    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighbourhood() {
        return Neighbourhood;
    }

    public void setNeighbourhood(String Neighbourhood) {
        this.Neighbourhood = Neighbourhood;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public int getType1_numofunits() {
        return Type1_numofunits;
    }

    public void setType1_numofunits(int Type1_numofunits) {
        this.Type1_numofunits = Type1_numofunits;
    }

    public double getType1_sellingprice() {
        return Type1_sellingprice;
    }

    public void setType1_sellingprice(double Type1_sellingprice) {
        this.Type1_sellingprice = Type1_sellingprice;
    }

    public String getType2() {
        return Type2;
    }

    public void setType2(String Type2) {
        this.Type2 = Type2;
    }

    public int getType2_numofunits() {
        return Type2_numofunits;
    }

    public void setType2_numofunits(int Type2_numofunits) {
        this.Type2_numofunits = Type2_numofunits;
    }

    public double getType2_sellling_price() {
        return type2_sellling_price;
    }

    public void setType2_sellling_price(double type2_sellling_price) {
        this.type2_sellling_price = type2_sellling_price;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public String getOpeningDateAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = this.getOpeningDate().format(formatter);

        return formattedDate;
    }

    public void setOpeningDate(String openingDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.openingDate = LocalDate.parse(openingDate);
    }



    public LocalDate getClosingDate() {

        return closingDate;
    }

    public String getClosingDateAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = this.getClosingDate().format(formatter);

        return formattedDate;
    }

    public void setClosingDate(String closingDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.closingDate = LocalDate.parse(closingDate);

    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getOfficerslots() {
        return officerslots;
    }

    public void setOfficerslots(int officerslots) {
        this.officerslots = officerslots;
    }

    public List<String> get_offficers(){
        return this.officers;
    }

    public String get_officers_as_string_for_csv(){

        StringBuilder sb = new StringBuilder();
        sb.append('"');
        sb.append(String.join(",",officers));
        sb.append('"');

        return sb.toString();
    }

    public void add_Officer(String officer){

        this.officers.add(officer);

    }

    public int getVisibility() {
        return visibility;
    }


    public  void setVisibility(int visibility) {
        this.visibility = visibility;
    }

}
