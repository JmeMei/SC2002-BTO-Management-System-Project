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

/**
 * Controller class that handles all enquiry-related operations within the BTO Management System.
 * <p>
 * Supports creation, viewing, editing, deleting, and replying to enquiries. Behavior differs
 * slightly depending on the role of the user (Applicant, Officer, or Manager).
 * </p>
 * <ul>
 *   <li>Applicants: Submit, view, edit, and delete their enquiries</li>
 *   <li>Officers and Managers: View and reply to enquiries for projects they manage</li>
 * </ul>
 */
public class EnquiryController {

    EnquiryDAO enquiryDAO = new EnquiryDAO();
    ProjectListDAO projectDAO = new ProjectListDAO();
    HDBManagerDAO managerDAO = new HDBManagerDAO();
    HDBOfficerDAO officerDAO = new HDBOfficerDAO();
    
    // creating an enquiry
    /**
     * Submits a new enquiry on behalf of an applicant for a given project.
     *
     * @param applicantNRIC the NRIC of the applicant submitting the enquiry
     * @param projectName the name of the project the enquiry is related to
     * @param question the enquiry question being submitted
     * @return a message indicating success or failure of submission
     */
    public String submitEnquiry(String applicantNRIC, String projectName, String question){
        String officerIC = officerDAO
        .officerNametoNRIC(projectDAO.getOfficerIC(projectName));
        String managerIC = managerDAO
        .managerNametoNRIC(projectDAO.getManagerbyProject(projectName));

        String enquiryID = enquiryDAO.generateNewEnquiryID();
        Enquiry enquiry = new Enquiry(enquiryID, projectName, applicantNRIC, question, officerIC, managerIC);

        if(enquiryDAO.saveEnquiry(enquiry)){
            return "Enquiry successfully submitted";
        }
        return "Failed to submit enquiry";
        
    }

    // view all enquiries for applicant
    /**
     * Retrieves all enquiries submitted by a specific applicant.
     *
     * @param applicantNRIC the NRIC of the applicant
     * @return a list of enquiries submitted by the applicant
     */
    public List<Enquiry> viewEnquiriesForApplicant(String applicantNRIC){
        return enquiryDAO.getEnquiriesByApplicant(applicantNRIC);
    }

    // viewing unanswered enquiries for managed project (officer / manager)
    /**
     * Retrieves all unanswered enquiries assigned to a specific officer or manager.
     *
     * @param personIC the NRIC of the officer or manager
     * @return a list of unanswered enquiries directed to the person
     */
    public List<Enquiry> getUnansweredEnquiries(String personIC){
        return enquiryDAO.getUnansweredEnquiries(personIC);
    }

    /**
     * Retrieves all enquiries in the system.
     *
     * @return a list of all enquiries
     */
    public List<Enquiry> getAllEnquiries(){
        return enquiryDAO.getAllEnquiries();
    }

    // delete enquiry by enquiryID
    /**
     * Deletes an enquiry submitted by an applicant if it has not yet been answered.
     *
     * @param enquiryID the ID of the enquiry to delete
     * @param applicantNRIC the NRIC of the applicant requesting the deletion
     * @return a message indicating the result of the deletion attempt
     */
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
    /**
     * Allows an applicant to edit a previously submitted enquiry, provided it hasn't been answered yet.
     *
     * @param applicantNRIC the NRIC of the applicant
     * @param enquiryID the ID of the enquiry to edit
     * @param newQuestion the new enquiry content to update
     * @return a message indicating whether the edit was successful
     */
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
    /**
     * Allows an officer or manager to reply to an enquiry directed to them.
     *
     * @param personIC the NRIC of the officer or manager replying
     * @param enquiryID the ID of the enquiry to reply to
     * @param answer the reply content
     * @return a message indicating success or failure of the reply
     */
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

    /**
     * Retrieves a list of all project names available in the system.
     *
     * @return a list of project names, or {@code null} if no projects exist
     */
    public List<String> availableProjectList() {
    List<String> projectNames = projectDAO.getProjectNames();

    if (projectNames.isEmpty()) {
        return null;
    }

    return projectNames;
    }
}
