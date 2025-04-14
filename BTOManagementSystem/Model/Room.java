package BTOManagementSystem.Model;

import BTOManagementSystem.Model.DAO.Enum.*;

public class Room {
    private String projectName;
    private String neighborhood;
    private int numUnitsType;
    private double priceType;
    private String openingDate;
    private String closingDate;
    private String manager;
    private int officerSlot;
    private String officers;
    private int visibility;
    private FlatType flatType;

    public Room(String projectName2, String neighborhood2, int numberOfUnits, double price, String openingDate2,
                String closingDate2, String manager2, int officerSlot2, String officer, int visibility2, FlatType flatType) {
        this.projectName = projectName2;
        this.neighborhood = neighborhood2;
        this.numUnitsType = numberOfUnits;
        this.priceType = price;
        this.openingDate = openingDate2;
        this.closingDate = closingDate2;
        this.manager = manager2;
        this.officerSlot = officerSlot2;
        this.officers = officer;
        this.visibility = visibility2;
        this.flatType = flatType;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getNeighborhood() {
        return neighborhood;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    public int getNumUnitsType() {
        return numUnitsType;
    }
    public void setNumUnitsType(int numUnitsType1) {
        this.numUnitsType = numUnitsType1;
    }
    public double getPriceType1() {
        return priceType;
    }
    public void setPriceType(double priceType1) {
        this.priceType = priceType1;
    }
    public String getOpeningDate() {
        return openingDate;
    }
    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }
    public String getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    public int getOfficerSlot() {
        return officerSlot;
    }
    public void setOfficerSlot(int officerSlot) {
        this.officerSlot = officerSlot;
    }
    public String getOfficers() {
        return officers;
    }
    public void setOfficers(String officers) {
        this.officers = officers;
    }
    public int getVisibility() {
        return visibility;
    }
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    public FlatType getFlatType() {
        return flatType;
    }
    public void setFlatType( FlatType flatType) {
        this.visibility = visibility;
    }
    
}
