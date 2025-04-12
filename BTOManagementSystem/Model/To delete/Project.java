package BTOManagementSystem.Model;

import java.util.Date;

/*
PROJECTLIST HEADER
Project Name,Neighborhood,Type 1,Number of units for Type 1,
Selling price for Type 1,Type 2,Number of units for Type 2,
Selling price for Type 2,Application opening date,
Application closing date,Manager,Officer Slot,Officer, visbility
*/
public class Project {
    private String projectName;
    private String neighborhood;
    private String type1;
    private int numUnitsType1;
    private double priceType1;
    private String type2;
    private int numUnitsType2;
    private double priceType2;
    private Date openingDate;
    private Date closingDate;
    private String manager;
    private int officerSlot;
    private String officers;
    private int visibility;
    
    // Constructor
    public Project(String name, String neighborhood, String type1, int numUnitsType1, double priceType1,
                  String type2, int numUnitsType2, double priceType2, Date openingDate, Date closingDate,
                  String manager, int officerSlot, String officers, int visibility) {
        this.projectName = name;
        this.neighborhood = neighborhood;
        this.type1 = type1;
        this.numUnitsType1 = numUnitsType1;
        this.priceType1 = priceType1;
        this.type2 = type2;
        this.numUnitsType2 = numUnitsType2;
        this.priceType2 = priceType2;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.manager = manager;
        this.officerSlot = officerSlot;
        this.officers = officers;
        this.visibility = visibility;
    }
    
    // Getters
    public String getprojectName() { return projectName; }
    public String getNeighborhood() { return neighborhood; }
    public String getType1() { return type1; }
    public int getNumUnitsType1() { return numUnitsType1; }
    public double getPriceType1() { return priceType1; }
    public String getType2() { return type2; }
    public int getNumUnitsType2() { return numUnitsType2; }
    public double getPriceType2() { return priceType2; }
    public Date getOpeningDate() { return openingDate; }
    public Date getClosingDate() { return closingDate; }
    public String getManager() { return manager; }
    public int getOfficerSlot() { return officerSlot; }
    public String getOfficers() { return officers; }
    public int getVisibility() { return visibility; }
    
    // Setters (if needed)
    public void setprojectName(String name) { this.projectName = name; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }
    public void setType1(String type1) { this.type1 = type1; }
    public void setNumUnitsType1(int numUnitsType1) { this.numUnitsType1 = numUnitsType1; }
    public void setPriceType1(double priceType1) { this.priceType1 = priceType1; }
    public void setType2(String type2) { this.type2 = type2; }
    public void setNumUnitsType2(int numUnitsType2) { this.numUnitsType2 = numUnitsType2; }
    public void setPriceType2(double priceType2) { this.priceType2 = priceType2; }
    public void setOpeningDate(Date openingDate) { this.openingDate = openingDate; }
    public void setClosingDate(Date closingDate) { this.closingDate = closingDate; }
    public void setManager(String manager) { this.manager = manager; }
    public void setOfficerSlot(int officerSlot) { this.officerSlot = officerSlot; }
    public void setOfficers(String officers) { this.officers = officers; }
    public void setVisibility(int visibility) { this.visibility = visibility; }
}