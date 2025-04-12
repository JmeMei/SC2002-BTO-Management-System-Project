package BTOManagementSystem.View.ProjectAvailableView;

import BTOManagementSystem.Model.TwoRoom;

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

    public void fullDisplay(TwoRoom twoRoom){
        System.out.println("ProjectName/Neighbourhood/Price/OpeningDate/ClosingDate");
        System.out.println(twoRoom.getProjectName());
        System.out.println(twoRoom.getNeighborhood());
        System.out.println(twoRoom.getPriceType1());
        System.out.println(twoRoom.getOpeningDate());
        System.out.println(twoRoom.getClosingDate());
    }
}
