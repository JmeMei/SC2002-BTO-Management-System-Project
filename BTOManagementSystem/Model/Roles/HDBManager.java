package BTOManagementSystem.Model.Roles;

import BTOManagementSystem.Model.User;

public class HDBManager extends User {

    public HDBManager(String name, String nric, int age, String password, String maritalStatus, String role) {
        super(name, nric, age, password, maritalStatus, role);
    }

    public String getnric(){
      return nric;
    }
}
