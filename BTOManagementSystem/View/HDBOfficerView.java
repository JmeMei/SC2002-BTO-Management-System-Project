package BTOManagementSystem.View;

import java.util.Scanner;

public class HDBOfficerView {
     private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(HDBOfficer officer) {
        int option = 0;

        while (option != 6) {
            System.out.println("\n=== HDB Officer Dashboard ===");
            System.out.println("Welcome, " + officer.getNric());
            System.out.println("1. View Applicants");
            System.out.println("2. View Enquiries");
            System.out.println("3. Reply to Enquiries");
            System.out.println("4. Book Flat for Applicant");
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
                    System.out.println("[DEBUG] Viewing applicant list...");
                    break;
                case 2:
                    System.out.println("[DEBUG] Viewing enquiries...");
                    break;
                case 3:
                    System.out.println("[DEBUG] Replying to enquiry...");
                    break;
                case 4:
                    System.out.println("[DEBUG] Booking flat...");
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
