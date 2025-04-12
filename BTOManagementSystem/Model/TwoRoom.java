package BTOManagementSystem.Model;

import java.util.Date;

public class TwoRoom {
    private String projectName;
    private String neighborhood;
    private int numUnitsType1;
    private double priceType1;
    private Date openingDate;
    private Date closingDate;
    private String manager;
    private int officerSlot;
    private String officers;
    private int visibility;

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
    public int getNumUnitsType1() {
        return numUnitsType1;
    }
    public void setNumUnitsType1(int numUnitsType1) {
        this.numUnitsType1 = numUnitsType1;
    }
    public double getPriceType1() {
        return priceType1;
    }
    public void setPriceType1(double priceType1) {
        this.priceType1 = priceType1;
    }
    public Date getOpeningDate() {
        return openingDate;
    }
    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
    public Date getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(Date closingDate) {
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

    
}
