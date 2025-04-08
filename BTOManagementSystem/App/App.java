package BTOManagementSystem.App;

import Authentication.AccountManager;
import Model.Shared.User;
import Model.MenuFactory;
import View.UserMainView;
import Util.ExcelLoader;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the BTO Management System application.
 * Provides login, password management, and menu routing based on user roles.
 */
public class App {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        // Load users and project data from Excel
        ExcelLoader loader = new ExcelLoader();
        List<User> userList = loader.loadUsers("ApplicantList.xlsx", "OfficerList.xlsx", "ManagerList.xlsx");
        AccountManager accountManager = new AccountManager(userList);

        while (option != 3) {
            System.out.println("===== Welcome to BTO Management System =====");
            System.out.println("1. Login with NRIC");
            System.out.println("2. Change Password");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // clear newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter your NRIC: ");
                    String nric = scanner.nextLine().toUpperCase();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    User user = accountManager.login(nric, password);

                    if (user != null) {
                        UserMainView mainMenu = MenuFactory.getMainView(user);
                        mainMenu.menu(nric);
                    } else {
                        System.out.println("Invalid NRIC or password. Try again.");
                    }
                    break;

                case 2:
                    System.out.print("Enter your NRIC: ");
                    String nricToChange = scanner.nextLine().toUpperCase();

                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.nextLine();

                    boolean changeP = AccountManager.changePassword(nricToChange, newPassword);
                    if (changeP) {
                        System.out.println("Password changed successfully.");
                    } else {
                        System.out.println("NRIC not found. Please try again.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the BTO Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please select between 1 and 3.");
                    break;
            }
        }

        scanner.close();
    }
}

