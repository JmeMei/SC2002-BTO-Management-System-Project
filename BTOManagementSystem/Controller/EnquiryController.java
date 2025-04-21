package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.DAO.EnquiryDAO;
import BTOManagementSystem.Model.DAO.HDBManagerDAO;
import BTOManagementSystem.Model.DAO.HDBOfficerDAO;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.Enquiry;
import java.util.List;

// This class handles all logic to do with enquiries
// This includes:
// 1. Applicant (View, Submit, Edit, Delete)
// 2. Officer Same as Applicant^ + (View, Reply to managed projects)
// 3. Manager (View, Reply to managed projects, view ALL PROJECTS)

public class EnquiryController {

    EnquiryDAO enquiryDAO = new EnquiryDAO();
    ProjectListDAO projectDAO = new ProjectListDAO();
    HDBManagerDAO managerDAO = new HDBManagerDAO();
    HDBOfficerDAO officerDAO = new HDBOfficerDAO();
    
    // creating an enquiry
    public void submitEnquiry(String applicantNRIC, String projectName, String question){
        String officerIC = officerDAO
        .officerNametoNRIC(projectDAO.getOfficerIC(projectName));
        String managerIC = managerDAO
        .managerNametoNRIC(projectDAO.getManagerbyProject(projectName));

        String enquiryID = enquiryDAO.generateNewEnquiryID();
        Enquiry enquiry = new Enquiry(enquiryID, projectName, applicantNRIC, question, officerIC, managerIC);

        enquiryDAO.saveEnquiry(enquiry);
    }

    // view all enquiries for applicant
    public List<Enquiry> viewEnquiriesForApplicant(String applicantNRIC){
        return enquiryDAO.getEnquiriesByApplicant(applicantNRIC);
    }

    // viewing an enquiries for managed project (officer / manager)
    // make one for unanswered enquiries
    public List<Enquiry> viewEnquiriesForInCharge(String personIC){
        return enquiryDAO.getEnquiriesForInCharge(personIC);
    }

    // delete enquiry by enquiryID
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
    
        // edit enquiry only if it belongs to applicant and no answer
        if (enquiry.getAnswer().trim().isEmpty() && enquiry.getApplicantNRIC().equals(applicantNRIC)) {
            enquiryDAO.editEnquiry(enquiry, newQuestion);
            return true;
        }

        return false;
    }

    // answer enquiry
    public boolean replyEnquiry(String personIC, String enquiryID, String answer) {
        Enquiry enquiry = enquiryDAO.getEnquiryById(enquiryID);

        // only answer enquiry if ID is the same and not answered
        if (enquiryID.equals(enquiry.getEnquiryID()) && enquiry.getAnswer().trim().isEmpty()) {
            enquiryDAO.replyEnquiry(enquiry, answer);
            return true; 
        }
        return false;
    }

    public List<String> availableProjectList() {
    List<String> projectNames = projectDAO.getProjectNames();

    if (projectNames.isEmpty()) {
        return null;
    }

    return projectNames;
    }
}
