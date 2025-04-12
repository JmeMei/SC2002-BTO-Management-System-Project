package BTOManagementSystem.Model;

import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;

import java.util.List;
import java.util.Map;

public class Project {
    protected String name;
    protected String location;
    protected Map<String, Integer> flatType;
    protected int numofunits;
    protected String applicationPeriod;
    protected boolean visibilityPeriod;
    protected HDBManager manager;
    protected List<HDBOfficer> officerSlots;

    public Project(String name, String location, Map<String, Integer> flatType,int numofunits, String applicationPeriod,boolean visibilityPeriod, HDBManager manager, List<HDBOfficer> officerSlots) {
        this.name = name;
        this.location = location;
        this.flatType = flatType;
        this.numofunits = numofunits;
        this.applicationPeriod = applicationPeriod;
        this.visibilityPeriod = visibilityPeriod;
        this.manager = manager;
        this.officerSlots = officerSlots;
    }

    public String getName(){
        return name;
    }

    // public String getPassword(String NRIC){
    //     return password;
    // }

}
