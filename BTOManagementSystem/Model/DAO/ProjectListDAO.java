package BTOManagementSystem.Model.DAO;


import BTOManagementSystem.Model.*;


import java.io.FileWriter;
import java.io.IOException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ProjectListDAO {

    public void writeANewProjectEntry(Project project){

        String filePath = "BTOManagementSystem/Data/ProjectList.csv";

        try (FileWriter writer = new FileWriter(filePath,true)) {

            writer.append(project.getName());
            writer.append(",");
            writer.append(project.getNeighbourhood());
            writer.append(",");
            writer.append(project.getType1());
            writer.append(",");
            writer.append(String.valueOf(project.getType1_numofunits()));
            writer.append(",");
            writer.append(String.valueOf(project.getType1_sellingprice()));
            writer.append(",");
            writer.append(project.getType2());
            writer.append(",");
            writer.append(String.valueOf(project.getType2_numofunits()));
            writer.append(",");
            writer.append(String.valueOf(project.getType2_sellling_price()));
            writer.append(",");
            writer.append(project.getOpeningDateAsString());
            writer.append(",");
            writer.append(project.getClosingDateAsString());
            writer.append(",");
            writer.append(project.getManager());
            writer.append(",");
            writer.append(String.valueOf(project.getOfficerslots()));
            writer.append(",");

            String joinedNames = String.join(",", project.get_offficers());

            joinedNames = "\"" + joinedNames + "\"";
            writer.append(joinedNames);

            writer.append(",");
            writer.append(String.valueOf(project.getVisibility()));

            writer.append("\n");

            System.out.println("New Project added successfully!");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }


    }


}
