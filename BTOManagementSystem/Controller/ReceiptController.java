package BTOManagementSystem.Controller;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.ReceiptsDAO;
import BTOManagementSystem.Model.Receipt;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.View.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for managing receipt-related operations,
 * including generation and viewing of booking receipts based on filters.
 */
public class ReceiptController {

    ReceiptsDAO receiptsDAO = new ReceiptsDAO();
    ApplicationProjectStatusDAO statusDAO = new ApplicationProjectStatusDAO();

    /**
     * Allows an HDB Manager to view filtered booking receipts.
     * <p>
     * Filters can include marital status, flat type, age range, project name, or no filter.
     * Results are displayed through the {@link ReceiptsView}.
     *
     * @param managerView the view interface for the HDB Manager
     * @param receiptsView the view interface used to interact with receipt data
     */
    public void ViewReceipts(HDBManagerView managerView, ReceiptsView receiptsView) {

        int filter_option = receiptsView.PromptFilters();
        String filter_value = null;


        while (filter_value == null) {

            switch (filter_option) {

                case 1 ->
                        filter_value = receiptsView.promptFilterValueForMartialStatus();

                case 2-> filter_value = receiptsView.promptFilterValueForFlatType();

                case 3-> filter_value = receiptsView.promptFilterValueForAgeRange();

                case 4 -> filter_value = receiptsView.promptFilterValueForProjectName();

                case 5 -> filter_value = "None";

                default -> {
                    receiptsView.invalid_input_message();
                    filter_option = receiptsView.PromptFilters();
                }

            }
        }

        ArrayList<Receipt> filtered_receipts_list = receiptsDAO.LoadReciepts(filter_option,filter_value);
        receiptsView.DisplayReceipts(filtered_receipts_list, receiptsDAO.getHeaders());

        managerView.showMenu();

    }

    /**
     * Allows an HDB Officer to view filtered booking receipts.
     * <p>
     * Similar to manager functionality but returns to the officer menu after displaying results.
     *
     * @param officerView the view interface for the HDB Officer
     * @param receiptsView the view interface used to interact with receipt data
     */
    public void OfficerViewReciept(HDBOfficerView officerView, ReceiptsView receiptsView){


        int filter_option = receiptsView.PromptFilters();
        String filter_value = null;


        while (filter_value == null) {

            switch (filter_option) {

                case 1 ->
                        filter_value = receiptsView.promptFilterValueForMartialStatus();

                case 2-> filter_value = receiptsView.promptFilterValueForFlatType();

                case 3-> filter_value = receiptsView.promptFilterValueForAgeRange();

                case 4 -> filter_value = receiptsView.promptFilterValueForProjectName();

                case 5 -> filter_value = "None";

                default ->{
                        receiptsView.invalid_input_message();
                        filter_option = receiptsView.PromptFilters();
                }

            }
        }

        ArrayList<Receipt> filtered_receipts_list = receiptsDAO.LoadReciepts(filter_option,filter_value);
        receiptsView.DisplayReceipts(filtered_receipts_list, receiptsDAO.getHeaders());

        officerView.showOfficerMenu((HDBOfficer)App.userSession);

    }

    /**
     * Generates a booking receipt for a successfully booked application.
     * <p>
     * Retrieves application details based on NRIC and project name,
     * and creates a corresponding {@link Receipt} object which is saved to the system.
     *
     * @param applicantNRIC the NRIC of the applicant
     * @param projectName the name of the project the receipt is for
     */
    public void generateReceipt(String applicantNRIC, String projectName) {
        // Get Status
        ApplicantProjectStatus status = statusDAO.getAnApplication(applicantNRIC,projectName);

        // Create the receipt
        Receipt receipt = new Receipt(
                status.getName(),
                status.getNric(),
                status.getMaritalStatus(),
                status.getFlatType().getDisplayName(),
                status.getAge(),
                status.getProjectName()
        );

        receiptsDAO.AddReceipt(receipt);
    }

}
