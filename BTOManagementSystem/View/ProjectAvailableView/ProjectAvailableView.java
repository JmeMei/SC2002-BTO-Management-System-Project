package BTOManagementSystem.View.ProjectAvailableView;

import BTOManagementSystem.Model.TwoRoom;

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

    public void fullDisplay(List<TwoRoom> twoRooms){
        // headers for data
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                "Project Name", "Neighborhood", "Price", "Opening Date", "Closing Date");

        // Seperator
        System.out.println("=".repeat(96));

        // iterate through two rooms
        for(TwoRoom twoRoom : twoRooms) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                    twoRoom.getProjectName(), twoRoom.getNeighborhood(), twoRoom.getPriceType1(), twoRoom.getOpeningDate(), twoRoom.getClosingDate());
        }
    }
}
