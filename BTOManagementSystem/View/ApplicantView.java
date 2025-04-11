package BTOManagementSystem.View;

import java.util.Scanner;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.User;

public class ApplicantView {
    private static final Scanner scanner = new Scanner(System.in);

    public void showMenu(User user) { //Upcasting
        int option = 0;

        while (option != 6) {
            System.out.println("\n=== Applicant Dashboard ===");
            System.out.println("Welcome, " + user.getName());
            System.out.println("1. View Available Projects");
            System.out.println("2. Apply for a Project");
            System.out.println("3. View My Application");
            System.out.println("4. Withdraw My Application");
            System.out.println("5. Enquiry Management");
            System.out.println("6. Logout");
            System.out.print("Enter your option: ");

            String input = scanner.nextLine(); // safer than nextInt()
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (option) {
                case 1:
                    viewAvailableProjects(user);
                    break;
                case 2:
                    applyForProject(user);
                    break;
                case 3:
                    viewMyApplication(user);
                    break;
                case 4:
                    withdrawApplication(user);
                    break;
                case 5:
                    manageEnquiries(user);
                    break;
                case 6:
                    App.main(null);
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Dummy implementations to be replaced with real logic
    private static void viewAvailableProjects(User user) {
        System.out.println("[DEBUG] Viewing available projects...");
        // Call controller to fetch and display projects
    }

    private static void applyForProject(User user) {
        System.out.println("[DEBUG] Applying for project...");
        // Prompt for project and flat type → pass to controller
    }

    private static void viewMyApplication(User user) {
        System.out.println("[DEBUG] Viewing your application...");
        // Use user.getApplication()
    }

    private static void withdrawApplication(User user) {
        System.out.println("[DEBUG] Withdrawing application...");
        // Call controller to withdraw
    }

    private static void manageEnquiries(User user) {
        System.out.println("[DEBUG] Managing enquiries...");
        // Submenu for add/view/delete enquiry
    }
}
