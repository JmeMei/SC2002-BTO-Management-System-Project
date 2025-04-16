package BTOManagementSystem.App;

import BTOManagementSystem.Controller.AuthController;
import BTOManagementSystem.Controller.MenuViewController;
import BTOManagementSystem.Services.AuthenticatorService;
import BTOManagementSystem.View.LoginPromptView;

/**
 * Entry point for the BTO Management System application.
 * Provides login, password management, and menu routing based on user roles.
 */
public class App {
    
    public static void main(String[] args) {
         // Using the interface type ILoginView
         LoginPromptView loginView = new LoginPromptView();
         AuthenticatorService authService = new AuthenticatorService();
         AuthController authController = new AuthController();


         authController.startAuthenticateProcess(loginView,authService);




    }
}

