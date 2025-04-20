package BTOManagementSystem.View;

import BTOManagementSystem.Model.Project;

import java.util.List;

public class ApplicantViewProjectsView {

    public void DisplayProjects(List<Project> projects){

        // headers for data
        System.out.printf(
                "%-25s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-12s %-12s\n",
                "Project Name",
                "Neighborhood",
                "Flat T-1",
                "Units T-1",
                "Price T-1",
                "Flat T-2",
                "Flat T-2",
                "Price T-2",
                "Open Date",
                "Close Date");

        // Seperator
        System.out.println("=".repeat(140));

        // iterate through two rooms
        for(Project p : projects) {
            System.out.printf(
                    "%-25s %-15s %-10s %-10d %-10.1f %-10s %-10d %-10.1f %-12s %-12s\n",
                    p.getName(),
                    p.getNeighbourhood(),
                    p.getType1(),
                    p.getType1_numofunits(),
                    p.getType1_sellingprice(),
                    p.getType2(),
                    p.getType2_numofunits(),
                    p.getType2_sellling_price(),
                    p.getOpeningDateAsString(),
                    p.getClosingDateAsString());
        }

    }

    public void NotEligibleForProjectsMessage(){
        System.out.println("You are not eligible for any flat types at this time.");
        System.out.println("Singles must be 35 years or older to apply for 2-Room flats.");
        System.out.println("Married applicants must be 21 years or older to apply for 2-Room or 3-Room flats.");
    }
}