package BTOManagementSystem.App;

import BTOManagementSystem.Controller.AuthController;
import BTOManagementSystem.Controller.MenuViewController;
import BTOManagementSystem.Services.AuthenticatorService;
import BTOManagementSystem.Services.SessionManagerService;
import BTOManagementSystem.View.LoginPromptView;

import BTOManagementSystem.Model.*;

/**
 * Entry point for the BTO Management System application.
 * Provides login, password management, and menu routing based on user roles.
 */
public class App {

    public static User userSession = null;
    
    public static void main(String[] args) {

         LoginPromptView loginView = new LoginPromptView();

         AuthenticatorService authService = new AuthenticatorService();
         AuthController authController = new AuthController();

         SessionManagerService sessionManager = new SessionManagerService();
         MenuViewController menuController = new MenuViewController();


         authController.startAuthenticateProcess(loginView,authService,sessionManager);
         menuController.handleMenuOptions(userSession);

    }


}

