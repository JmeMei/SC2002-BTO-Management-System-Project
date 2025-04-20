package BTOManagementSystem.Controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.DAO.EnquiryDAO;

// This class handles all logic to do with enquiries
// This includes the applicant who can create an enquiry for the project, and the officer who can respond to these enquiries under the officer

public class EnquiryController {

    EnquiryDAO enquiryDAO = new EnquiryDAO();
    
    // creating an enquiry
    public void submitEnquiry(String applicantNRIC, String projectName, String question, String officerNRIC){

        String enquiryID = enquiryDAO.generateNewEnquiryID();
        Enquiry enquiry = new Enquiry(enquiryID, projectName, applicantNRIC, question, officerNRIC);

        enquiryDAO.saveEnquiry(enquiry);
    }

    public List<Enquiry> viewEnquiriesForApplicant(String applicantNRIC){
        return enquiryDAO.getEnquiriesByApplicant(applicantNRIC);
    }

    // viewing an enquiry (officer)
    public List<Enquiry> viewEnquiriesForOfficer(String officerNRIC){
        return enquiryDAO.getEnquiriesByOfficer(officerNRIC);
    }

    // officers can answer enquiry
    public boolean answerEnquiry(Enquiry enquiry, String answer) {
        if (!enquiry.getAnswer().isEmpty()) {
            return false; // Already answered
        }
        enquiry.setAnswer(answer);
        enquiryDAO.updateEnquiry(enquiry);
        return true;
    }

    public boolean deleteEnquiry(String enquiryID, String applicantNRIC) {
        Enquiry enquiry = enquiryDAO.getEnquiryById(enquiryID);
        if (enquiry == null) return false;

        // Only delete if applicant owns it and it's unanswered
        if (enquiry.getApplicantNRIC().equalsIgnoreCase(applicantNRIC) && enquiry.getAnswer().isEmpty()) {
            return enquiryDAO.deleteEnquiry(enquiryID);
        }
        return false;
    }

    public List<Enquiry> getEditableEnquiries(String applicantNRIC) {
        List<Enquiry> all = enquiryDAO.getEnquiriesByApplicant(applicantNRIC);
        return all.stream()
                  .filter(e -> e.getAnswer().isEmpty())
                  .collect(Collectors.toList());
    }
}
