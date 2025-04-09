package BTOManagementSystem.Controller;
import java.util.*;

import BTOManagementSystem.Model.*;
import BTOManagementSystem.View.LoginPromptView;

public class AuthController {

    public static void Authenticate(String username, String password){

        Session.updateSession("T1234567A");
        LoginPromptView.AuthenticationSucess();

        if (password.compareTo(User.getPasswordForUser(username)) == 0){


           

        }

    }
    
}
