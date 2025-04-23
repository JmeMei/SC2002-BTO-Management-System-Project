package BTOManagementSystem.View;

import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.Applicant;

import java.util.List;

public class ApplicantViewProjectsView {

    public void DisplayProjects(List<Project> projects,List<FlatType> availableTypes) {

        System.out.println("Available Projects");
        for (Project p : projects) {
            System.out.println("--------------------------------------------------");
            System.out.println("Project Name   : " + p.getName());
            System.out.println("Neighborhood   : " + p.getNeighbourhood());

            FlatType type1 = FlatType.fromString(p.getType1());
            FlatType type2 = FlatType.fromString(p.getType2());

            if (availableTypes.contains(type1)) {
                System.out.print(type1.getDisplayName() + " Units   : " +
                        p.getType1_numofunits() + " (Price: $" + (int) p.getType1_sellingprice() + ")");
                if(p.getType1_numofunits() == 0) {
                    System.out.print(" (No Units Available)");
                }
                System.out.print("\n");
            }

            if (availableTypes.contains(type2)) {
                System.out.print(type2.getDisplayName() + " Units   : " +
                        p.getType2_numofunits() + " (Price: $" + (int) p.getType2_selling_price() + ")");

                if(p.getType2_numofunits() == 0) {
                    System.out.print(" (No Units Available)");
                }
                System.out.print("\n");
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