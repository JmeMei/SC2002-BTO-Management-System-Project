package BTOManagementSystem.View;

/**
 * Interface representing a view for managing enquiries in the BTO Management System.
 * <p>
 * This interface is implemented by different roles (e.g., Applicant, Officer, Manager)
 * to provide role-specific behavior for interacting with enquiry-related functionality.
 */
import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.User;
import java.util.List;

public interface EnquiryView{
    /**
     * Displays the enquiry menu for the current user and handles their enquiry-related actions.
     *
     * @param user the current user interacting with the enquiry menu
     */
    void showEnquiryMenu(User user);

    /**
     * Prints a formatted list of enquiries for the user to view.
     *
     * @param enquiries a list of {@link Enquiry} objects to be displayed
     */
    void printEnquiryList(List<Enquiry> enquiries);
}