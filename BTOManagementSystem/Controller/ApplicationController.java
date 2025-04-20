package BTOManagementSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Services.ApplicantActionHandler;
import BTOManagementSystem.View.ApplicantViewProjectsView;


public class ApplicationController {
    //ApplicationController “has a” projectListDao
    //ApplicationController “has a” ProjectAvailableView
    private ProjectListDAO projectListDao;
    private ApplicationProjectStatusDAO applicantProjectStatusDAO;
    private ApplicantActionHandler applicantActionHandler;
    private ApplicantViewProjectsView viewProjectsView;

    public ApplicationController(){
        this.applicantProjectStatusDAO = new ApplicationProjectStatusDAO();
        this.projectListDao = new ProjectListDAO();
        this.applicantActionHandler = new ApplicantActionHandler();

        this.viewProjectsView = new ApplicantViewProjectsView();
    }

    public List<Project> getEligibleProjects(User user) {
        List<Project> eligibleProjects = new ArrayList<>();
        boolean canApplyForTwoRoom = (user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single"))
                || (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21);
        boolean canApplyForThreeRoom = user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21;

        if (canApplyForTwoRoom) {
            List<Project> twoRoomProjects = projectListDao.loadAvailableTwoRooms(user);
            eligibleProjects.addAll(twoRoomProjects);
        }

        if (canApplyForThreeRoom) {
            List<Project> threeRoomProjects = projectListDao.loadAvailableTwoRooms(user);
            eligibleProjects.addAll(projectListDao.loadAvailableThreeRooms(user));
        }

        return eligibleProjects;
    }
    
    public void displayAvailableProjects(User user) {

        List<Project> eligibleProjects = getEligibleProjects(user);

        if (eligibleProjects.isEmpty()) {
            viewProjectsView.NotEligibleForProjectsMessage();
        } else {
            viewProjectsView.DisplayProjects(eligibleProjects);
        }
    }

    public boolean project_Exist(String projectName,User user){
        List<Project> projectAvailable = getEligibleProjects(user);

        for (Project p : projectAvailable){
            if(p.getName().equalsIgnoreCase(projectName)){
                return true;
            }
        }
        return false;
    }

    public List<FlatType> getAvailableFlatTypes(String projectName, User user){
        List<Project> projectAvailable = getEligibleProjects(user);

        List<FlatType> availableFlatTypes = new ArrayList<>();
        for (Project p : projectAvailable){
            if(p.getName().equalsIgnoreCase(projectName)){
                FlatType flatType1 = FlatType.fromString(p.getType1());
                FlatType flatType2 = FlatType.fromString(p.getType2());

                availableFlatTypes.add(flatType1);
                availableFlatTypes.add(flatType2);
            }
        }
        return availableFlatTypes;
    }

    public void applyForProject(User user, String projectName,FlatType flatType) {
        //Need to update ApplicantProjectStatus from NA -> PENDING

        //public static boolean applyForProject(User user, String projectName, FlatType flatType)
        boolean success = applicantProjectStatusDAO.applyForProject(user, projectName, flatType);
        if (success){
            System.out.print("Successfully applied. Now pending.");
        }
    }

    public void viewMyApplication(User user) {
        boolean success = applicantProjectStatusDAO.viewMyApplication(user);
        if (success){
            System.out.print("Application successfully viewed");
        }
        else{
            System.out.print("Unable to view application");
        }
    }

    public void withdrawApplication(User user){
        System.out.println("[TODO] To implement this feature");
    }
        
        
}



// public class ApplicationController {
//     //ApplicationController “has a” TwoRoomDAO
//     //ApplicationController “has a” ProjectAvailableView
//     private RoomDAO roomDAO;
//     private ApplicationProjectStatusDAO applicantProjectStatusDAO;

//     private ProjectAvailableView projectAvailableView;
    
//     public ApplicationController(){
//         this.roomDAO = new RoomDAO();
//         this.applicantProjectStatusDAO = new ApplicationProjectStatusDAO();

//         this.projectAvailableView = new ProjectAvailableView();
//     }
    
//     public List<Room> displayAvailableProjects(User user) {

//         // Display user eligibility information
//         System.out.println("\n=== Available Projects ===");
        
//         // Determine eligibility
//         boolean canApplyForTwoRoom = (user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single"))
//                                     || (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21);
//         boolean canApplyForThreeRoom = user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21;
        
//         System.out.println("\nYou are eligible for:");
//         if (canApplyForTwoRoom && !canApplyForThreeRoom) {
//             System.out.println("- 2-Room flats only");
//             List<Room> rooms = roomDAO.loadAvailableTwoRooms(user);
//             projectAvailableView.fullDisplay(rooms);
//             return rooms;

//         } else if (canApplyForTwoRoom && canApplyForThreeRoom) {
//             System.out.println("- 2-Room flats");
//             List<Room> tworooms = roomDAO.loadAvailableTwoRooms(user);
//             projectAvailableView.fullDisplay(tworooms);
//             System.out.println("\n- 3-Room flats");
//             List<Room> threerooms = roomDAO.loadAvailableThreeRooms(user);
//             projectAvailableView.fullDisplay(threerooms);

//             //Return both rooms
//             List<Room> allRooms = new ArrayList<>();
//             allRooms.addAll(tworooms);
//             allRooms.addAll(threerooms);
//             return allRooms;
//         }
        
//         if (!canApplyForTwoRoom && !canApplyForThreeRoom) {
//             System.out.println("You are not eligible for any flat types at this time.");
//             System.out.println("Singles must be 35 years or older to apply for 2-Room flats.");
//             System.out.println("Married applicants must be 21 years or older to apply for 2-Room or 3-Room flats.");
//             return new ArrayList<>(); //return empty ArrayList because he is not elgible at all
//         }
//         return new ArrayList<>();
//     }

//     public void applyForProject(User user, int projectID) {
//         //Need to update ApplicantProjectStatus from NA -> PENDING
//         boolean success = applicantProjectStatusDAO.applyForProject(user, projectID);
//         if (success){
//             System.out.print("Successfully applied. Now pending.");
//         }
//     }

//     public void viewMyApplication(User user) {
//         boolean success = applicantProjectStatusDAO.viewMyApplication(user);
//         if (success){
//             System.out.print("Application successfully viewed");
//         }
//         else{
//             System.out.print("Unable to view application");
//         }
//     }

//     public void withdrawApplication(User user){
//         System.out.println("[TODO] To implement this feature");
//     }
        
        
// }