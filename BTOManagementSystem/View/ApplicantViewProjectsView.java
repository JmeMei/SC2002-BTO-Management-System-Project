package BTOManagementSystem.View;

import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.Applicant;

import java.util.List;

public class ApplicantViewProjectsView {

    public void DisplayProjects(List<Project> projects,boolean eligibleTwoRoom, boolean eligibleThreeRoom) {

        System.out.println("Available Projects");
        for (Project p : projects) {
            System.out.println("--------------------------------------------------");
            System.out.println("Project Name   : " + p.getName());
            System.out.println("Neighborhood   : " + p.getNeighbourhood());

            if (eligibleTwoRoom) {
                if (p.getType1().equalsIgnoreCase(FlatType.TWO_ROOM.getDisplayName()) && p.getType1_numofunits() > 0) {
                    System.out.println("2-Room Units   : " + p.getType1_numofunits() +
                            " (Price: $" + (int) p.getType1_sellingprice() + ")");
                }
                if (p.getType2().equalsIgnoreCase(FlatType.TWO_ROOM.getDisplayName()) && p.getType2_numofunits() > 0) {
                    System.out.println("2-Room Units   : " + p.getType2_numofunits() +
                            " (Price: $" + (int) p.getType2_sellling_price() + ")");
                }
            }

            if (eligibleThreeRoom) {
                if (p.getType1().equalsIgnoreCase(FlatType.THREE_ROOM.getDisplayName()) && p.getType1_numofunits() > 0) {
                    System.out.println("3-Room Units   : " + p.getType1_numofunits() +
                            " (Price: $" + (int) p.getType1_sellingprice() + ")");
                }
                if (p.getType2().equalsIgnoreCase(FlatType.THREE_ROOM.getDisplayName()) && p.getType2_numofunits() > 0) {
                    System.out.println("3-Room Units   : " + p.getType2_numofunits() +
                            " (Price: $" + (int) p.getType2_sellling_price() + ")");
                }
            }

            System.out.println("Opening Date   : " + p.getOpeningDateAsString());
            System.out.println("Closing Date   : " + p.getClosingDateAsString());
            System.out.println();
        }

    }



    public void NotEligibleForProjectsMessage(){
        System.out.println("You are not eligible for any flat types at this time.");
        System.out.println("Singles must be 35 years or older to apply for 2-Room flats.");
        System.out.println("Married applicants must be 21 years or older to apply for 2-Room or 3-Room flats.");
    }
}