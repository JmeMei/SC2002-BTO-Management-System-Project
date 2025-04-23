package BTOManagementSystem.Model.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
        statusList = new ArrayList<>();
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
            if (status.getNric().equals(user.getNric())) {
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

    public ApplicantProjectStatus getApplication(String applicantNRIC) {
        for (ApplicantProjectStatus status : statusList) {
            if (status.getNric().equals(applicantNRIC)) {
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
}