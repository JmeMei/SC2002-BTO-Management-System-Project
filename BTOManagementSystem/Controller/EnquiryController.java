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

    // viewing unanswered enquiries for managed project (officer / manager)
    public List<Enquiry> getUnansweredEnquiries(String personIC){
        return enquiryDAO.getUnansweredEnquiries(personIC);
    }

    public List<Enquiry> getAllEnquiries(){
        return enquiryDAO.getAllEnquiries();
    }

    // delete enquiry by enquiryID
    public String deleteEnquiry(String enquiryID, String applicantNRIC) {
        Enquiry enquiry = enquiryDAO.getEnquiryById(enquiryID);
        if (enquiry == null || !enquiry.getApplicantNRIC().equals(applicantNRIC)) {
            return "Unsuccessful, please check Enquiry ID."; // Enquiry not found or doesn't belong to applicant
        }
    
        if (!enquiry.getAnswer().trim().isEmpty()) {
            return "Enquiry already has been answered."; // Cannot delete an enquiry that has already been answered
        }

        enquiryDAO.deleteEnquiry(enquiryID);
        return "Enquiry successfully deleted.";
    }

    // edit enquiries method
    public String editEnquiry(String applicantNRIC, String enquiryID, String newQuestion) {
        Enquiry enquiry = enquiryDAO.getEnquiryById(enquiryID);
    
        // edit enquiry only if it belongs to applicant and no answer
        if (enquiry.getAnswer().trim().isEmpty() && enquiry.getApplicantNRIC().equals(applicantNRIC)) {
            enquiryDAO.editEnquiry(enquiry, newQuestion);
            return "Enquiry edited successfully.";
        }

        return "Unsuccessful, please check Enquiry ID.";
    }

    // answer enquiry
    public String replyEnquiry(String personIC, String enquiryID, String answer) {
        Enquiry enquiry = enquiryDAO.getEnquiryById(enquiryID);
        if(enquiry == null){
            return "Unsuccessful, please check Enquiry ID.";
        }

        // only answer enquiry if ID is the same and not answered
        if (enquiryID.equals(enquiry.getEnquiryID()) && enquiry.getAnswer().trim().isEmpty()) {
            enquiryDAO.replyEnquiry(enquiry, answer);
            return "Successfully replied to enquiry"; 
        }
        return "Unsuccessful, please check Enquiry ID.";
    }

    public List<String> availableProjectList() {
    List<String> projectNames = projectDAO.getProjectNames();

    if (projectNames.isEmpty()) {
        return null;
    }

    return projectNames;
    }
}
