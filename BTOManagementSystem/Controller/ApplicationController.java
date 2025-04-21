package BTOManagementSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.Applicant;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Services.ApplicantActionHandler;
import BTOManagementSystem.View.ApplicantView;
import BTOManagementSystem.View.ApplicantViewApplyProjectView;
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

    public void displayAvailableProjects(ApplicantView applicantView,User user) {

        boolean eligibleTwoRoom = (user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single"))
                || (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21);
        boolean eligibleThreeRoom = user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21;

        List<Project> eligibleProjects = getEligibleProjects(user);

        if (eligibleProjects.isEmpty()) {
            viewProjectsView.NotEligibleForProjectsMessage();
        } else {
            viewProjectsView.DisplayProjects(eligibleProjects,eligibleTwoRoom,eligibleThreeRoom);
        }

        applicantView.showMenu(user);
    }

    public void applyProject(ApplicantView applicantView, ApplicantViewApplyProjectView applyView,User user) {

        // Prompt user view to return name of project to apply for
        String projectName = applyView.promptApplicantChooseProject();

        //get project using projectName
        Project project = getProject(projectName, user);

        if(project != null){
            // Show available flat types for this project
            List<FlatType> availableTypes = getAvailableFlatTypes(project, user);
            FlatType flatType =  applyView.PromptAvailableFlatTypesforProject(project, availableTypes);

            // Apply for Project
            boolean success = applicantProjectStatusDAO.applyForProject(user, projectName, flatType);
            if (success){
                applyView.ApplySuccessMessage();
            }else{
                applyView.AppliedBeforeMessage();
            }
        }
        else{
            applyView.ProjectNotFoundMessage();
        }
        applicantView.showMenu(user);
    }

    public void viewMyApplication(ApplicantView applicantView,User user) {
        boolean success = applicantProjectStatusDAO.viewMyApplication(user);
        if (success){
            System.out.print("Application successfully viewed");
        }
        else{
            System.out.print("Unable to view application");
        }

        applicantView.showMenu(user);
    }

    public void withdrawApplication(ApplicantView applicantView,User user){
        boolean success = applicantProjectStatusDAO.requestApplicationWithdrawal(user);
        if (success){
            System.out.print("Application Withdrawal was successfully requested");
        }else{
            System.out.print("Unable to request application withdrawal");
        }
    }

    public Project getProject(String projectName,User user){
        List<Project> projectAvailable = getEligibleProjects(user);

        for (Project p : projectAvailable){
            if(p.getName().equalsIgnoreCase(projectName)){
                return p;
            }
        }
        return null;
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



    public List<FlatType> getAvailableFlatTypes(Project project, User user){

        boolean eligibleTwoRoom = (user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single"))
                || (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21);
        boolean eligibleThreeRoom = user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21;

        List<FlatType> availableFlatTypes = new ArrayList<>();
        FlatType flatType1 = FlatType.fromString(project.getType1());
        FlatType flatType2 = FlatType.fromString(project.getType2());

        if (eligibleTwoRoom) {
            if (flatType1 == FlatType.TWO_ROOM && project.getType1_numofunits() > 0) {
                availableFlatTypes.add(FlatType.TWO_ROOM);
            }
            if (flatType2 == FlatType.TWO_ROOM && project.getType2_numofunits() > 0) {
                availableFlatTypes.add(FlatType.TWO_ROOM);
            }
        }

        if (eligibleThreeRoom) {
            if (flatType1 == FlatType.THREE_ROOM && project.getType1_numofunits() > 0) {
                availableFlatTypes.add(FlatType.THREE_ROOM);
            }
            if (flatType2 == FlatType.THREE_ROOM && project.getType2_numofunits() > 0) {
                availableFlatTypes.add(FlatType.THREE_ROOM);
            }
        }
        return availableFlatTypes;
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