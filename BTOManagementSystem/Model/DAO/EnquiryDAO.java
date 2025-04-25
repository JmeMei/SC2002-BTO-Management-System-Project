package BTOManagementSystem.Model.DAO;

import BTOManagementSystem.Model.Enquiry;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object (DAO) for managing {@link Enquiry} objects.
 * <p>
 * This class handles all file operations related to enquiries, including creating,
 * reading, updating, and deleting enquiry records from a CSV file.
 */
public class EnquiryDAO {
    private static final String FILE_PATH = "BTOManagementSystem/Data/Enquiries.csv";

    // Save a new enquiry
    /**
     * Saves a new enquiry by appending it to the CSV file.
     *
     * @param enquiry the enquiry object to be saved
     * @return {@code true} if the enquiry is saved successfully, {@code false} otherwise
     */
    public boolean saveEnquiry(Enquiry enquiry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(enquiry.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving enquiry: " + e.getMessage());
        }
        return false;
    }

    // Load all enquiries
    /**
     * Loads all enquiries from the CSV file.
     *
     * @return a list of all {@link Enquiry} objects
     */
    public List<Enquiry> loadAllEnquiries() {
        List<Enquiry> enquiries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line; // skip header
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (fields.length >= 6) {
                    Enquiry enquiry = new Enquiry(
                        fields[0],
                        fields[1],
                        fields[2],
                        fields[3],
                        fields[4],
                        fields[5],
                        fields[6]
                    );
                    enquiries.add(enquiry);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading enquiries: " + e.getMessage());
        }
        return enquiries;
    }

    // get all enquiries
    /**
     * Returns all enquiries stored in the system.
     *
     * @return a list of {@link Enquiry} objects
     */
    public List<Enquiry> getAllEnquiries(){
        return loadAllEnquiries();
    }

    // get enquiries where user is in charge of (officer / manager)
    /**
     * Retrieves all unanswered enquiries assigned to a specific officer or manager.
     *
     * @param personIC the NRIC of the officer or manager
     * @return a list of unanswered {@link Enquiry} objects
     */
    public List<Enquiry> getUnansweredEnquiries(String personIC) {
        List<Enquiry> all = loadAllEnquiries();
        List<Enquiry> result = new ArrayList<>();
        for (Enquiry e : all) {
            if ((e.getofficerIC().equals(personIC) || e.getManagerIC().equals(personIC))
            && e.getAnswer().trim().isEmpty()) {
                result.add(e);
            }
        }
        return result;
    }

    // Get enquiries by applicant NRIC
    /**
     * Retrieves all enquiries submitted by a specific applicant.
     *
     * @param applicantNRIC the NRIC of the applicant
     * @return a list of {@link Enquiry} objects submitted by the applicant
     */
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

    // edit enquiry QUESTION
    /**
     * Updates an enquiry's question.
     *
     * @param enquiry     the enquiry to update
     * @param newQuestion the new question text
     * @return {@code true} if the enquiry was successfully updated, {@code false} otherwise
     */
    public boolean editEnquiry(Enquiry enquiry, String newQuestion) {
        List<Enquiry> enquiries = loadAllEnquiries();
        boolean updated = false;
    
        for (Enquiry e : enquiries) {
            if (e.getEnquiryID().equals(enquiry.getEnquiryID())) {
                e.setQuestion(newQuestion);
                updated = true;
                break;
            }
        } 
        if (updated) {
            saveAllEnquiries(enquiries);
        }
    
        return updated;
    }

    // answer enquiry
    /**
     * Updates an enquiry with a response (reply).
     *
     * @param enquiry the enquiry to be answered
     * @param answer  the reply text
     * @return {@code true} if the reply was successfully saved, {@code false} otherwise
     */
    public boolean replyEnquiry(Enquiry enquiry, String answer) {
        List<Enquiry> enquiries = loadAllEnquiries();
        boolean updated = false;
    
        for (Enquiry e : enquiries) {
            if (e.getEnquiryID().equals(enquiry.getEnquiryID())){
                e.setAnswer(answer);
                updated = true;
                break;
            }
        }
        if (updated) {
            saveAllEnquiries(enquiries);
        }
    
        return updated;
    }

    // Auto-generate an enquiry ID
    /**
     * Generates a new, unique enquiry ID based on existing entries.
     *
     * @return a new enquiry ID in the format "EQ#" (e.g., "EQ12")
     */
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

    /**
     * Deletes an enquiry by its ID.
     *
     * @param enquiryID the ID of the enquiry to delete
     * @return {@code true} if the enquiry was successfully deleted, {@code false} otherwise
     */
    public boolean deleteEnquiry(String enquiryID) {
        List<Enquiry> allEnquiries = loadAllEnquiries();
        boolean removed = allEnquiries.removeIf(e -> e.getEnquiryID().equalsIgnoreCase(enquiryID));
    
        if (removed) {
            saveAllEnquiries(allEnquiries); // Rewrites the file without the deleted enquiry
        }
    
        return removed;
    }

    // write to the csv
    /**
     * Writes the entire list of enquiries to the CSV file, overwriting previous data.
     *
     * @param enquiries the list of enquiries to be saved
     */
    private void saveAllEnquiries(List<Enquiry> enquiries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Enquiry e : enquiries) {
                writer.write(String.join(",",
                        e.getEnquiryID(),
                        e.getProjectName(),
                        e.getApplicantNRIC(),
                        e.getQuestion(),
                        e.getAnswer(),
                        e.getofficerIC(),
                        e.getManagerIC()));
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
