package BTOManagementSystem.Controller;

import java.util.List;

import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.RoomDAO;
import BTOManagementSystem.View.ProjectAvailableView.ProjectAvailableView;
import BTOManagementSystem.Model.Room;

public class ApplicationController {
    //ApplicationController “has a” TwoRoomDAO
    //ApplicationController “has a” ProjectAvailableView
    private RoomDAO roomDAO;
    private ProjectAvailableView projectAvailableView;
    
    public ApplicationController(){
        this.roomDAO = new RoomDAO();
        this.projectAvailableView = new ProjectAvailableView();
    }
    
    public void displayAvailableProjects(User user) {

        // Display user eligibility information
        System.out.println("\n=== Available Projects ===");
        
        // Determine eligibility
        boolean canApplyForTwoRoom = (user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single"))
                                    || (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21);
        boolean canApplyForThreeRoom = user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21;
        
        System.out.println("\nYou are eligible for:");
        if (canApplyForTwoRoom && !canApplyForThreeRoom) {
            System.out.println("- 2-Room flats only");
            List<Room> rooms = roomDAO.loadAvailableTwoRooms();
            projectAvailableView.fullDisplay(rooms);
        } else if (canApplyForTwoRoom && canApplyForThreeRoom) {
            System.out.println("- 2-Room flats");
            List<Room> tworooms = roomDAO.loadAvailableTwoRooms();
            projectAvailableView.fullDisplay(tworooms);
            System.out.println("\n- 3-Room flats");
            List<Room> threerooms = roomDAO.loadAvailableThreeRooms();
            projectAvailableView.fullDisplay(threerooms);
        }
        
        if (!canApplyForTwoRoom && !canApplyForThreeRoom) {
            System.out.println("You are not eligible for any flat types at this time.");
            System.out.println("Singles must be 35 years or older to apply for 2-Room flats.");
            System.out.println("Married applicants must be 21 years or older to apply for 2-Room or 3-Room flats.");
            return;
        }
    }
        
}