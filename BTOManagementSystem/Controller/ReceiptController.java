package BTOManagementSystem.Controller;

import BTOManagementSystem.Model.ApplicantProjectStatus;
import BTOManagementSystem.Model.DAO.ApplicationProjectStatusDAO;
import BTOManagementSystem.Model.DAO.ReceiptsDAO;
import BTOManagementSystem.Model.Receipt;
import BTOManagementSystem.View.*;

import java.util.ArrayList;

public class ReceiptController {

    ReceiptsDAO receiptsDAO = new ReceiptsDAO();
    ApplicationProjectStatusDAO statusDAO = new ApplicationProjectStatusDAO();

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

                default -> receiptsView.invalid_input_message();

            }
        }




        ArrayList<Receipt> filtered_receipts_list = receiptsDAO.LoadReciepts(filter_option,filter_value);
        receiptsView.DisplayReceipts(filtered_receipts_list, receiptsDAO.getHeaders());

        managerView.showMenu();

    }

    public void generateReceipt(String applicantNRIC, String projectName) {
        ApplicantProjectStatus status = statusDAO.getApplication(applicantNRIC);

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
