package BTOManagementSystem;
import java.util.*;

public class Project {
    private String name;
    private String location;
    private Map<String, Integer> flatUnits; // "2-Room" or "3-Room" → remaining count
    private Date applicationStartDate;
    private Date applicationEndDate;
    private boolean visible;
    private HDBManager manager;
    private List<HDBOfficer> officers;

    public Project(String name, String location, HDBManager manager) {
        this.name = name;
        this.location = location;
        this.manager = manager;
        this.flatUnits = new HashMap<>();
        this.officers = new ArrayList<>();
        this.visible = true;
    }

    public void setFlatUnits(String flatType, int count) {
        flatUnits.put(flatType, count);
    }

    public boolean hasFlatType(String flatType) {
        return flatUnits.containsKey(flatType) && flatUnits.get(flatType) > 0;
    }

    public void reduceFlatCount(String flatType) {
        if (flatUnits.containsKey(flatType)) {
            flatUnits.put(flatType, flatUnits.get(flatType) - 1);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void toggleVisibility() {
        visible = !visible;
    }

    public HDBManager getManager() {
        return manager;
    }

    public List<HDBOfficer> getOfficers() {
        return officers;
    }

    public void addOfficer(HDBOfficer officer) {
        officers.add(officer);
    }
}
