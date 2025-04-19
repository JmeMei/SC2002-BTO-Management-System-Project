package BTOManagementSystem.Model;

import java.time.LocalDate;
import java.util.List;

import BTOManagementSystem.Model.DAO.Enum.*;

public class Room {
    private String projectName;
    private String neighborhood;
    private int numUnitsType;
    private double price;
    private LocalDate openingDate;
    private LocalDate closingDate;
    //private String manager;
    //private int officerSlot;
    //private List<String> officers;
    //private int visibility;
    private FlatType flatType;
    
    public Room(String projectName, String neighborhood, int numUnitsType, double price, LocalDate openingDate,
            LocalDate closingDate /*, List<String> officers*/, FlatType flatType) {
        this.projectName = projectName;
        this.neighborhood = neighborhood;
        this.numUnitsType = numUnitsType;
        this.price = price;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        //this.officers = officers;
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

    public void setNumUnitsType(int numUnitsType) {
        this.numUnitsType = numUnitsType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double priceType) {
        this.price = priceType;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    // public List<String> getOfficers() {
    //     return officers;
    // }

    // public void setOfficers(List<String> officers) {
    //     this.officers = officers;
    // }

    public FlatType getFlatType() {
        return flatType;
    }

    public void setFlatType(FlatType flatType) {
        this.flatType = flatType;
    }
}