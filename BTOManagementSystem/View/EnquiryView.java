package BTOManagementSystem.View;

import BTOManagementSystem.Model.Enquiry;
import BTOManagementSystem.Model.User;
import java.util.List;

public interface EnquiryView{
    void showEnquiryMenu(User user);
    void printEnquiryList(List<Enquiry> enquiries);
}