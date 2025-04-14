package BTOManagementSystem.View;

import BTOManagementSystem.Model.Roles.HDBManager;
import java.util.Scanner;

public class HDBManagerView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(HDBManager manager) {
        int option = 0;

        while (option != 6) {
            System.out.println("\n=== HDB Manager Dashboard ===");
            System.out.println("Welcome, " + manager.getNric());
            System.out.println("1. Create Project");
            System.out.println("2. Edit/Delete Project");
            System.out.println("3. Approve Officer Registrations");
            System.out.println("4. View Applications");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Enter your option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("[DEBUG] Creating a new project...");
                    break;
                case 2:
                    System.out.println("[DEBUG] Editing or deleting a project...");
                    break;
                case 3:
                    System.out.println("[DEBUG] Approving officer registrations...");
                    break;
                case 4:
                    System.out.println("[DEBUG] Viewing applications...");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                case 6:
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
