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

import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.Application;

public class ApplicationController {
    // Update this path to your actual CSV file location
    private static final String PROJECTS_FILE_PATH = "src/BTOManagementSystem/Data/projects.csv"; 
    private static List<Project> projects = null;
    // Map to store user applications (NRIC -> Application)
    private static Map<String, Application> userApplications = new HashMap<>();
    
    public static void displayAvailableProjects(User user) {
        // Load projects if not already loaded
        if (projects == null) {
            loadProjects();
        }
        
        // Check if user already has an application
        if (userApplications.containsKey(user.getNric())) {
            System.out.println("\nYou already have an active application. You cannot apply for multiple projects.");
            System.out.println("Please withdraw your current application if you wish to apply for a new project.");
            return;
        }
        
        // Display user eligibility information
        System.out.println("\n=== Available Projects ===");
        System.out.println("Based on your profile:");
        System.out.println("- Age: " + user.getAge());
        System.out.println("- Marital Status: " + user.getMaritalStatus());
        
        // Determine eligibility
        boolean canApplyForTwoRoom = (user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single"))
                                    || (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21);
        boolean canApplyForThreeRoom = user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21;
        
        System.out.println("\nYou are eligible for:");
        if (canApplyForTwoRoom && !canApplyForThreeRoom) {
            System.out.println("- 2-Room flats only");
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
        
        // Display available projects based on eligibility
        System.out.println("\nAvailable Projects:");
        System.out.printf("%-5s %-25s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", 
                "ID", "Project Name", "Neighborhood", "2-Room Units", "2-Room Price", 
                "3-Room Units", "3-Room Price", "Opening Date", "Closing Date");
        System.out.println("-".repeat(130));
        
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        int projectCount = 0;
        
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            
            // Assuming there's a visibility field in your Project class
            // Skip if project is not visible or application period has ended
            if (project.getVisibility() != 1) {
                continue;
            }
            
            // Assuming first type is 2-Room and second type is 3-Room based on your CSV
            boolean hasTwoRoom = canApplyForTwoRoom;
            boolean hasThreeRoom = canApplyForThreeRoom;
            
            // Only display if the project has at least one flat type the user is eligible for
            if (hasTwoRoom || hasThreeRoom) {
                projectCount++;
                
                // Get project details - adjust these to match your Project class methods
                String name = project.getName();
                String neighborhood = project.getNeighborhood();
                String twoRoomUnits = hasTwoRoom ? String.valueOf(project.getNumUnitsType1()) : "Not Eligible";
                String twoRoomPrice = hasTwoRoom ? "$" + project.getNumUnitsType1() : "N/A";
                String threeRoomUnits = hasThreeRoom ? String.valueOf(project.getNumUnitsType2()) : "Not Eligible";
                String threeRoomPrice = hasThreeRoom ? "$" + project.getPriceType2() : "N/A";
                Date openingDate = project.getOpeningDate();
                Date closingDate = project.getClosingDate();
                
                System.out.printf("%-5d %-25s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", 
                        i + 1, 
                        name, 
                        neighborhood,
                        twoRoomUnits,
                        twoRoomPrice,
                        threeRoomUnits,
                        threeRoomPrice,
                        openingDate,
                        closingDate);
            }
        }
        
        if (projectCount == 0) {
            System.out.println("No projects currently available for your eligibility criteria.");
        }
    }
    
    private static void loadProjects() {
        projects = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(PROJECTS_FILE_PATH))) {
            String line = br.readLine(); // Skip header
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                
                try {
                    // Parse data from CSV - adjust according to your Project constructor
                    String name = data[0];
                    String neighborhood = data[1];
                    String type1 = data[2];
                    int type1Units = Integer.parseInt(data[3]);
                    double type1Price = Double.parseDouble(data[4]);
                    String type2 = data[5];
                    int type2Units = Integer.parseInt(data[6]);
                    double type2Price = Double.parseDouble(data[7]);
                    String openingDate = data[8];
                    String closingDate = data[9];
                    String manager = data[10];
                    int officerSlot = Integer.parseInt(data[11]);
                    String officers = data[12];
                    int visibility = Integer.parseInt(data[13]);
                    
                    Project project = new Project(name, neighborhood, type1, type1Units, type1Price,
                            type2, type2Units, type2Price, openingDate, closingDate,
                            manager, officerSlot, officers, visibility);
                    
                            /*
                            PROJECTLIST HEADER
                            Project Name,Neighborhood,Type 1,Number of units for Type 1,
                            Selling price for Type 1,Type 2,Number of units for Type 2,
                            Selling price for Type 2,Application opening date,
                            Application closing date,Manager,Officer Slot,Officer, visbility
                            */
                    
                    projects.add(project);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error parsing project data: " + e.getMessage());
                    System.out.println("Problematic line: " + line);
                    // Continue with next project
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading projects file: " + e.getMessage());
        }
        
        System.out.println("Loaded " + projects.size() + " projects successfully.");
    }
}