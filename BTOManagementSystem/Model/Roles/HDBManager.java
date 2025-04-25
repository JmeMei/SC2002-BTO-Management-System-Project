package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.User;

public class HDBManager extends User {

    public HDBManager(String name, String nric, int age, String password, String maritalStatus) {
        super(name, nric, age, password, maritalStatus, "Manager");
    }

    public String getnric(){
      return nric;
    }
}
