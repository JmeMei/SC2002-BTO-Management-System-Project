package BTOManagementSystem.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.TwoRoomDAO;
import BTOManagementSystem.View.ProjectAvailableView.ProjectAvailableView;
import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.TwoRoom;

public class ApplicationController {
    private TwoRoomDAO twoRoomDAO;
    private ProjectAvailableView projectAvailableView; 
    private TwoRoom twoRoom;
    
    public ApplicationController(){
        this.twoRoomDAO = new TwoRoomDAO();
        this.projectAvailableView = new ProjectAvailableView();
        this.twoRoom = new TwoRoom();
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
            twoRoom = twoRoomDAO.loadAvailable2TwoRooms();
            projectAvailableView.fullDisplay(twoRoom);
        } else if (canApplyForTwoRoom && canApplyForThreeRoom) {
            System.out.println("- 2-Room flats");
            System.out.println("- 3-Room flats");
        }
        
        if (!canApplyForTwoRoom && !canApplyForThreeRoom) {
            System.out.println("You are not eligible for any flat types at this time.");
            System.out.println("Singles must be 35 years or older to apply for 2-Room flats.");
            System.out.println("Married applicants must be 21 years or older to apply for 2-Room or 3-Room flats.");
            return;
        }
    }
        
}