package BTOManagementSystem.App;

import BTOManagementSystem.Controller.AuthController;
import BTOManagementSystem.Controller.MenuViewController;
import BTOManagementSystem.Model.*;
import BTOManagementSystem.Services.AuthenticatorService;
import BTOManagementSystem.Services.SessionManagerService;
import BTOManagementSystem.View.LoginPromptView;

/**
 * Entry point for the BTO Management System application.
 *
 * <p>This class handles the initialization of services and controllers required to
 * authenticate a user, start a session, and display the appropriate user menu
 * based on their role (Applicant, Officer, or Manager).</p>
 *
 * <p>The application flow is as follows:</p>
 * <ol>
 *     <li>Display login prompt and collect user credentials.</li>
 *     <li>Authenticate credentials using {@link AuthenticatorService}.</li>
 *     <li>If successful, initialize the session using {@link SessionManagerService}.</li>
 *     <li>Route user to the appropriate main menu using {@link MenuViewController}.</li>
 * </ol>
 *
 * <p>The user currently logged in is stored in the static {@code userSession} field.</p>
 */
public class App {

    /**
     * Holds the currently logged-in user's session.
     * This is used globally to retrieve the user's identity and role.
     */
    public static User userSession = null;

    /**
     * Main method that launches the BTO Management System.
     * Initializes and coordinates services and controllers.
     */
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

