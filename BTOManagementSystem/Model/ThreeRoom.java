package BTOManagementSystem.Model;

import java.util.Date;

public class ThreeRoom {

     private String projectName;
     private String neighborhood;
     private int numUnitsType2;
     private double priceType2;
     private Date openingDate;
     private Date closingDate;
     private String manager;
     private int officerSlot;
     private String officers;
     private int visibility;

     public ThreeRoom(String projectName, String neighborhood, int numUnitsType2, double priceType2, Date openingDate,
     Date closingDate, String manager, int officerSlot, String officers, int visibility) {
        this.projectName = projectName;
        this.neighborhood = neighborhood;
        this.numUnitsType2 = numUnitsType2;
        this.priceType2 = priceType2;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.manager = manager;
        this.officerSlot = officerSlot;
        this.officers = officers;
        this.visibility = visibility;
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
     public int getNumUnitsType2() {
         return numUnitsType2;
     }
     public void setNumUnitsType2(int numUnitsType2) {
         this.numUnitsType2 = numUnitsType2;
     }
     public double getPriceType2() {
         return priceType2;
     }
     public void setPriceType2(double priceType2) {
         this.priceType2 = priceType2;
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
