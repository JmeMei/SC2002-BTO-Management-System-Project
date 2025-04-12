package BTOManagementSystem.Model;

public class Project {
    private String projectName;
    private String neighborhood;
    private String type1;
    private int unitsType1;
    private double priceType1;
    private String type2;
    private int unitsType2;
    private double priceType2;
    private String appOpenDate;
    private String appCloseDate;
    private String manager;
    private int officerSlot;
    private String officer;
    private int visibility;  // 1 indicates visible, 0 indicates hidden

    public Project(String projectName, String neighborhood, String type1, int unitsType1, double priceType1,
                   String type2, int unitsType2, double priceType2, String appOpenDate, String appCloseDate,
                   String manager, int officerSlot, String officer, int visibility) {
        this.projectName = projectName;
        this.neighborhood = neighborhood;
        this.type1 = type1;
        this.unitsType1 = unitsType1;
        this.priceType1 = priceType1;
        this.type2 = type2;
        this.unitsType2 = unitsType2;
        this.priceType2 = priceType2;
        this.appOpenDate = appOpenDate;
        this.appCloseDate = appCloseDate;
        this.manager = manager;
        this.officerSlot = officerSlot;
        this.officer = officer;
        this.visibility = visibility;
    }
    
    public int getVisibility() {
        return visibility;
    }
    
    @Override
    public String toString() {
        return "Project: " + projectName + ", Location: " + neighborhood + 
               ", " + type1 + " (" + unitsType1 + " units at $" + priceType1 + "), " + 
               type2 + " (" + unitsType2 + " units at $" + priceType2 + ")" +
               ", Application Period: " + appOpenDate + " to " + appCloseDate +
               ", Manager: " + manager + ", Officers: " + officer;
    }
}
