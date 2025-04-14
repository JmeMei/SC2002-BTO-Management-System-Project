package BTOManagementSystem.View.ProjectAvailableView;

import BTOManagementSystem.Model.Room;

import java.util.List;

public class ProjectAvailableView {
    /*
     *   private String projectName;
     private String neighborhood;
     private int numUnitsType2;
     private double priceType2;
     private Date openingDate;
     private Date closingDate;
     private String manager;
     private int officerSlot;
     private String officers;
     private int visibility;
     */

    public void fullDisplay(List<Room> rooms){
        // headers for data
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                "Project Name", "Neighborhood", "Price", "Opening Date", "Closing Date", "Available");

        // Seperator
        System.out.println("=".repeat(120));

        // iterate through two rooms
        for(Room room : rooms) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                    room.getProjectName(), room.getNeighborhood(), room.getPriceType1(), room.getOpeningDate(), room.getClosingDate(), room.getNumUnitsType());
        }
    }
}
