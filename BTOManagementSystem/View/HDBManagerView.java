package BTOManagementSystem.View;

import BTOManagementSystem.Model.Roles.HDBManager;
import java.util.Scanner;

public class HDBManagerView {
    private static final Scanner scanner = new Scanner(System.in);



    public int showMenu(HDBManager manager) {

        System.out.println("\n=== HDB Manager Dashboard ===");
        System.out.println("Welcome, " + manager.getNric());
        System.out.println("1. Create Project");
        System.out.println("2. Edit/Delete Project");
        System.out.println("3. Approve Officer Registrations");
        System.out.println("4. View Applications");
        System.out.println("5. Logout");
        System.out.println("6. Exit");


        System.out.print("Enter your option: ");
        int option = 0;
        while (option > 6 || option < 1) {

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");

            }

        }
        return option;
    }

}
