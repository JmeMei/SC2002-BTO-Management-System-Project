package BTOManagementSystem.Model.DAO;


import BTOManagementSystem.Model.*;


import java.io.FileWriter;
import java.io.IOException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ProjectListDAO {

    private String filePath = "BTOManagementSystem/Data/ProjectList.csv";

    public void writeANewProjectEntry(Project project){



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

    public void editAProject(String projectName, int atrributeIndex, String NewValue){

        List<String[]> rows = new ArrayList<>();

        //Reading
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] aRow = line.split(",");
                if (aRow[0].compareTo(projectName) == 0){

                    String old_value = "";
                    if (atrributeIndex <= 10){

                        old_value = aRow[atrributeIndex - 1];
                        aRow[atrributeIndex - 1] = NewValue;

                    } else{

                        if (atrributeIndex == 11){
                            old_value = aRow[11];
                            aRow[11] = NewValue;
                        }

                        else if (atrributeIndex == 12){
                            old_value = aRow[13];
                            aRow[13] = NewValue;

                        }
                    }
                    System.out.println("data changed from " + old_value + " -> " + NewValue);

                }

                rows.add(aRow);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        try (FileWriter fw = new FileWriter(filePath)) {
            for (String[] row : rows) {
                fw.write(String.join(",", row) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
