package BTOManagementSystem.Services;

import BTOManagementSystem.Model.User;
import BTOManagementSystem.Model.DAO.Enum.FlatType;
import BTOManagementSystem.View.ApplicantView;

public class ApplicantActionHandler {
    public FlatType getRoomType(User user){ //True  applicable for 2 and 3 rooms, False, aplicable for 2 room only

        // Determine eligibility
        boolean canApplyForTwoRoom = (user.getAge() >= 35 && user.getMaritalStatus().equalsIgnoreCase("Single"))
                                    || (user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21);
        boolean canApplyForThreeRoom = user.getMaritalStatus().equalsIgnoreCase("Married") && user.getAge() >= 21;
        
        //System.out.println("\nYou are eligible for:");
        if (canApplyForTwoRoom && !canApplyForThreeRoom) {
            //System.out.println("- 2-Room flats only");
            //return FlatType.TWOROOM;
        } else if (canApplyForTwoRoom && canApplyForThreeRoom) {
            //User will choose whether he/she wants TWOROOM or THREEROOM
            //return ApplicantView.chooseRoom();
        }
        
        if (!canApplyForTwoRoom && !canApplyForThreeRoom) {
            return null; //cannot apply for any
        }
        return null;
    }
}
