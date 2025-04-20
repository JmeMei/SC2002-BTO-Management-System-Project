package BTOManagementSystem.Controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.DAO.EnquiryDAO;
import BTOManagementSystem.Model.DAO.ProjectListDAO;

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

    // view all enquiries for applicant
    public List<Enquiry> viewEnquiriesForApplicant(String applicantNRIC){
        return enquiryDAO.getEnquiriesByApplicant(applicantNRIC);
    }

    // viewing an enquiry (officer)
    public List<Enquiry> viewEnquiriesForOfficer(String officerNRIC){
        return enquiryDAO.getEnquiriesByOfficer(officerNRIC);
    }

    public boolean deleteEnquiry(String enquiryID, String applicantNRIC) {
        Enquiry enquiry = enquiryDAO.getEnquiryById(enquiryID);
        if (enquiry == null || !enquiry.getApplicantNRIC().equals(applicantNRIC)) {
            return false; // Enquiry not found or doesn't belong to applicant
        }
    
        if (!enquiry.getAnswer().trim().isEmpty()) {
            return false; // Cannot delete an enquiry that has already been answered
        }
        
        return enquiryDAO.deleteEnquiry(enquiryID);
    }

    // edit enquiries method
    public boolean editEnquiry(String applicantNRIC, String enquiryID, String newQuestion) {
        Enquiry enquiry = enquiryDAO.getEnquiryById(enquiryID);
    
        if (enquiry == null || !enquiry.getApplicantNRIC().equals(applicantNRIC)) {
            return false; // Enquiry not found or doesn't belong to applicant
        }
    
        if (!enquiry.getAnswer().trim().isEmpty()) {
            return false; // Cannot edit an enquiry that has already been answered
        }

        enquiryDAO.editEnquiry(enquiry, applicantNRIC, newQuestion);
        return true;
    }

    public List<String> availableProjectList(ProjectListDAO projectDAO) {
    List<String> projectNames = projectDAO.getProjectNames();

    if (projectNames.isEmpty()) {
        return null;
    }

    return projectNames;
}
}
