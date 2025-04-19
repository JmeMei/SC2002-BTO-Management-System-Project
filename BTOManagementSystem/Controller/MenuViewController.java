package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.*;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.Roles.HDBManager;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.Services.HDB_Manager_ActionHandler;
import BTOManagementSystem.View.ApplicantView;
import BTOManagementSystem.View.HDBManagerView;
import BTOManagementSystem.View.HDBOfficerView;

public class MenuViewController {


    public void handleMenuOptions(User user) {

        int option = 0;

        if (user instanceof HDBOfficer) {
            HDBOfficerView officerView = new HDBOfficerView();
            HDBOfficerView.showMenu((HDBOfficer)user);
        } else if (user instanceof HDBManager) {
            HDBManagerView managerView = new HDBManagerView();

            while (option != -1){
                option = managerView.showMenu((HDBManager)user);
                HDB_Manager_ActionHandler actionHandler = new HDB_Manager_ActionHandler();
                actionHandler.HandleAction(option);
            }



        } else if (user instanceof Applicant) {
            ApplicantView view = new ApplicantView();
            view.showMenu((Applicant)user);
        }

    }
    

}

//      old menu view
//        switch(user.getRole()){
//            case "Applicant":
//                ApplicantView view = new ApplicantView();
//                view.showMenu(user);
//                break;
//            case "Manager":
//                HDBManagerView managerView = new HDBManagerView();
//                managerView.showMenu((HDBManager)user);
//               break;
//            case "Officer":
//                HDBOfficerView officerView = new HDBOfficerView();
//                officerView.showMenu((HDBOfficer)user);
//                break;
//            default:
//                System.out.println("Unknown user role");
//                break;
//       }

//     // Path to the CSV 
//     private static final String FILE_PATH = "BTOManagementSystem/Data/userlogin.csv"

//     public user login(String NRIC, String password){
//         List<String[]> userDatabase = new ArrayList<>();
//         BufferReader reader = new BufferedReader(new FileReader(FILE_PATH)){
//             //read the header and dont care
//             String header = reader.readLine();
//             userDatabase.add(header.split(",")); //dont care about the header. Can remove this part

//             String line;
//             while((line == reader.readLine()) != null){
//                 //Name,NRIC,Age,Marital Status,Password,role
//                 String[] values = line.split(",");
//                 String[] userName = values[0].trim(); 

//                 String[] values = line.split(",");
//                 String[] userNRIC = values[1].trim(); 

//                 String[] values = line.split(",");
//                 String[] userAge = values[2].trim(); 

//                 String[] values = line.split(",");
//                 String[] userMaritalStatus = values[3].trim(); 

//                 String[] values = line.split(",");
//                 String[] userPassword = values[4].trim(); 

//                 String[] values = line.split(",");
//                 String[] userRole = values[5].trim(); 

//                 if(userNRIC.equals(NRIC) && userPassword.equals(password)){
//                     System.out.println("login successful, Welcome back" + userName);
//                     // Return the authenticated user object

//                     switch (userRole) {
//                         //Name,NRIC,Age,Marital Status,Password
//                         case : Applicant
//                             return new Applicant(userName, NRIC, Age, MaritalStatus, , age);
//                         case :
//                             return new Doctor(userName, password, userType, name, gender, age);
//                         case PATIENT:
//                             return new Patient(hosID, password, userType, name, gender, age);
//                         case :
//                             return new Pharmacist(hosID, password, userType, name, gender, age);
//                         default:
//                             throw new IllegalArgumentException("Invalid user type: " + userType);
//                 }
//             }            

//         }
//     }

//     //CHANGING PASSWORD
//      public static void changePassword(String NRIC, String newPassword) {
//         List<String[]> fileContent = new ArrayList<>();
//         BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH)) {
//             //read the header and dont care
//             String header = reader.readLine();
//             userDatabase.add(header.split(",")); //dont care about the header. Can remove this part

//             String line;
//             while ((line = reader.readLine()) != null) {
//                 String[] values = line.split(",");

//                 // Update the password for the matching hospital ID
//                 if (values[1].trim().equalsIgnoreCase(hospID)) {
//                     values[4] = newPassword;
//                 }

//                 fileContent.add(values);
//             }
//         }

//         // Write the updated content back to the file
// BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH)){
//             for (String[] values : fileContent) {
//                 writer.write(String.join(",", values));
//                 writer.newLine();
//             }
//             System.out.println("Password successfully changed.");
//         } 
//     }

