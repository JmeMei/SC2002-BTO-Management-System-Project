package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.Enum.ApplicationStatus;
import BTOManagementSystem.Model.DAO.Enum.FlatType;

public class ApplicationProjectStatusDAO {
    private static final String FILE_PATH = "BTOManagementSystem/Data/ApplicantProjectStatus.csv";
    private static ArrayList<ApplicantProjectStatus> statusList = new ArrayList<>();

    public ApplicationProjectStatusDAO() {
        this.LoadAllApplicantProjectStatuses();
    }

    public void reloadFromFile() {
        this.LoadAllApplicantProjectStatuses();
    }

    public void LoadAllApplicantProjectStatuses(){
       statusList.clear();
        //init Applicants from CSV
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            // Skip Header
            br.readLine();

            //Iterate through CSV
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ApplicantProjectStatus applicantProjectStatus = new ApplicantProjectStatus(
                        values[0].trim(),                                     // Name
                        values[1].trim(),                                     // NRIC
                        Integer.parseInt(values[2].trim()),                   // Age
                        values[3].trim(),                                     // Marital Status
                        values[4].trim(),                                     // Role
                        values[5].trim(),                                     // Project Name
                        FlatType.fromString(values[6].trim()),                // Flat Type
                        ApplicationStatus.valueOf(values[7].trim())          // Application Status
                );
                statusList.add(applicantProjectStatus);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean applyForProject(User user, String projectName, FlatType flatType) {
        for (ApplicantProjectStatus status : statusList) {
            // If current project exists or past project is either PENDING, SUCCESSFUL, BOOKED
            if (status.getNric().equals(user.getNric()) && status.getProjectName().equals(projectName) ||
                    status.getNric().equals(user.getNric()) && status.getApplicationStatus() != ApplicationStatus.UNSUCCESSFUL) {
                return false;
            }
        }

        ApplicantProjectStatus newApplication = new ApplicantProjectStatus(
                user.getName(),
                user.getNric(),
                user.getAge(),
                user.getMaritalStatus(),
                user.getRole(),
                projectName,
                flatType,
                ApplicationStatus.PENDING
        );
        statusList.add(newApplication);

        // subtract from existing no of flats
        this.updateDB();
        return true;
    }

    public ApplicationStatus getApplicationStatus(String applicantNRIC, String projectName, FlatType flatType) {
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(applicantNRIC) && status.getProjectName().equals(projectName) && flatType.equals(status.getFlatType())) {
                return status.getApplicationStatus();
            }
        }
        return null;
    }

    public String getProjectNameforApplicant(User user) {
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(user.getNric())) {
                return status.getProjectName();
            }
        }
        return null;
    }

    public List<ApplicantProjectStatus> getApplicationsByNRIC(String applicantNRIC) {
        List<ApplicantProjectStatus> applicantstatusList = new ArrayList<>();
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(applicantNRIC)) {
                applicantstatusList.add(status);
            }
        }
        return applicantstatusList;
    }

    public List<ApplicantProjectStatus> getApplications() {
        return statusList;
    }

    public ApplicantProjectStatus getAnApplication(String applicantNRIC,String projectName) {
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(applicantNRIC) && status.getProjectName().equals(projectName)) {
               return status;
            }
        }
        return null;
    }

    public boolean updateApplicationStatus(String applicantNRIC){
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(applicantNRIC) && status.getApplicationStatus().equals(ApplicationStatus.SUCCESSFUL)) {
                status.setApplicationStatus(ApplicationStatus.BOOKED);
                this.updateDB();
                return true;
            }
        }
        return false;
    }

    public boolean updateDB() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write the header
            bw.write("Name,NRIC,Age,Marital Status,role,Project Name,FlatType,Application Status");
            bw.newLine();

            // Write each status
            for (ApplicantProjectStatus s : statusList) {
                String line = String.join(",",
                        s.getName(),
                        s.getNric(),
                        String.valueOf(s.getAge()),
                        s.getMaritalStatus(),
                        s.getRole(),
                        s.getProjectName(),
                        s.getFlatType().getDisplayName(),
                        s.getApplicationStatus().name()
                );
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //HDB manager

    public void mark_unsuccessful(String nric, String projectName) {

        for(ApplicantProjectStatus s : statusList){

            if (s.getNric().equalsIgnoreCase(nric)){

                if (s.getProjectName().equalsIgnoreCase(projectName)){

                    s.setApplicationStatus(ApplicationStatus.UNSUCCESSFUL);
                    updateDB();
                    return;
                }
            }
        }

    }

    public int mark_successful(String nric, String projectName) {

        for(ApplicantProjectStatus s : statusList){

            if (s.getNric().equalsIgnoreCase(nric)){

                if (s.getProjectName().equalsIgnoreCase(projectName)){

                    s.setApplicationStatus(ApplicationStatus.SUCCESSFUL);
                    updateDB();
                    return 1;
                }
            }
        }

        return 0;
    }

    public ArrayList<ApplicantProjectStatus> LoadApplications(String[] FilterData){

        String ProjectName = FilterData[0];
        String AgeGroup = FilterData[1];
        int status = Integer.parseInt(FilterData[2]);

        int[] Formatted_AgeGroup = {};
        ArrayList<ApplicantProjectStatus> filteredList = new ArrayList<>();

        if (!AgeGroup.equalsIgnoreCase("N")){

            Formatted_AgeGroup = Arrays
                    .stream(AgeGroup.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (ApplicantProjectStatus s : statusList) {

            if(status != 4){
                if(! (s.getApplicationStatus().ordinal() == status) ){
                    continue;
                }
            }


            if (!ProjectName.equalsIgnoreCase("N")){
                if (!s.getProjectName().equalsIgnoreCase(ProjectName)){
                    continue;
                }
            }

            if (!AgeGroup.equalsIgnoreCase("N")){

                if (! (s.getAge() <= Formatted_AgeGroup[1] &&  s.getAge() >= Formatted_AgeGroup[0] )){
                    continue;
                }

            }

            //System.out.println("HELLOOOOOO");
            filteredList.add(s);


        }
        return filteredList;
    }
}