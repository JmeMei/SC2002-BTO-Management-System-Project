package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.Enquiry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EnquiryDAO {
    private static final String FILE_PATH = "BTOManagementSystem/Data/Enquiries.csv";

    // Save a new enquiry
    public void saveEnquiry(Enquiry enquiry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(enquiry.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving enquiry: " + e.getMessage());
        }
    }

    // Load all enquiries
    public List<Enquiry> loadAllEnquiries() {
        List<Enquiry> enquiries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (fields.length >= 6) {
                    Enquiry enquiry = new Enquiry(
                        fields[0],
                        fields[1],
                        fields[2],
                        fields[3],
                        fields[4]
                    );
                    enquiries.add(enquiry);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading enquiries: " + e.getMessage());
        }
        return enquiries;
    }

    // Get enquiries by officer NRIC
    public List<Enquiry> getEnquiriesByOfficer(String officerNRIC) {
        List<Enquiry> all = loadAllEnquiries();
        List<Enquiry> result = new ArrayList<>();
        for (Enquiry e : all) {
            if (e.getOfficerNRIC().equals(officerNRIC)) {
                result.add(e);
            }
        }
        return result;
    }

    // Get enquiries by applicant NRIC
    public List<Enquiry> getEnquiriesByApplicant(String applicantNRIC) {
        List<Enquiry> all = loadAllEnquiries();
        List<Enquiry> result = new ArrayList<>();
        for (Enquiry e : all) {
            if (e.getApplicantNRIC().equals(applicantNRIC)) {
                result.add(e);
            }
        }
        return result;
    }

    // Update an enquiry (e.g., add answer) and rewrite the whole file
    public void updateEnquiry(Enquiry updatedEnquiry) {
        List<Enquiry> all = loadAllEnquiries();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Enquiry e : all) {
                if (e.getEnquiryID().equals(updatedEnquiry.getEnquiryID())) {
                    writer.write(updatedEnquiry.toString());
                } else {
                    writer.write(e.toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating enquiry: " + e.getMessage());
        }
    }

    // Auto-generate an enquiry ID
    public String generateNewEnquiryID() {
        List<Enquiry> all = loadAllEnquiries();
        int max = 0;
        for (Enquiry e : all) {
            try {
                int id = Integer.parseInt(e.getEnquiryID().replaceAll("[^0-9]", ""));
                if (id > max) max = id;
            } catch (NumberFormatException ignored) {}
        }
        return "EQ" + (max + 1);
    }

    public boolean deleteEnquiry(String enquiryID) {
        List<Enquiry> allEnquiries = loadAllEnquiries();
        boolean removed = allEnquiries.removeIf(e -> e.getEnquiryID().equalsIgnoreCase(enquiryID));
    
        if (removed) {
            saveAllEnquiries(allEnquiries); // Rewrites the file without the deleted enquiry
        }
    
        return removed;
    }

    private void saveAllEnquiries(List<Enquiry> enquiries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("EnquiryID,ProjectName,ApplicantNRIC,Question,OfficerNRIC,Answer");
            writer.newLine();
            for (Enquiry e : enquiries) {
                writer.write(String.join(",",
                        e.getEnquiryID(),
                        e.getProjectName(),
                        e.getApplicantNRIC(),
                        e.getQuestion(),
                        e.getOfficerNRIC(),
                        e.getAnswer()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving enquiries: " + e.getMessage());
        }
    }

    public Enquiry getEnquiryById(String enquiryID) {
        List<Enquiry> allEnquiries = loadAllEnquiries();
        for (Enquiry enquiry : allEnquiries) {
            if (enquiry.getEnquiryID().equalsIgnoreCase(enquiryID)) {
                return enquiry;
            }
        }
        return null; // Return null if no enquiry is found with the given ID
    }
    
    
}
