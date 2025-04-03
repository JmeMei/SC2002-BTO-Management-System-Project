package BTOManagementSystem;
import java.util.*;

public class Main {
    private static Map<String, User> userMap = new HashMap<>(); // NRIC → User
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== Welcome to BTO Management System =====");

        loadSampleUsers(); // or load from file

        User currentUser = null;
        while (currentUser == null) {
            currentUser = login();
        }

        if (currentUser instanceof HDBManager) {
            managerMenu((HDBManager) currentUser);
        } else if (currentUser instanceof HDBOfficer) {
            officerMenu((HDBOfficer) currentUser);
        } else if (currentUser instanceof Applicant) {
            applicantMenu((Applicant) currentUser);
        }

        System.out.println("Thank you for using the BTO System!");
    }

    private static void loadSampleUsers() {
        // For testing/demo purposes – replace with file reading later
        userMap.put("S1234567A", new Applicant("S1234567A", 36, "Single"));
        userMap.put("S2345678B", new Applicant("S2345678B", 29, "Married"));
        userMap.put("T3456789C", new HDBOfficer("T3456789C", 32, "Married"));
        userMap.put("T4567890D", new HDBManager("T4567890D", 45, "Married"));
    }

    private static User login() {
        System.out.print("Enter NRIC: ");
        String nric = scanner.nextLine().toUpperCase();
        if (!userMap.containsKey(nric)) {
            System.out.println("User not found.");
            return null;
        }

        System.out.print("Enter password: ");
        String pwd = scanner.nextLine();

        User user = userMap.get(nric);
        if (user.login(pwd)) {
            System.out.println("Login successful. Welcome!");
            return user;
        } else {
            System.out.println("Incorrect password.");
            return null;
        }
    }

    private static void applicantMenu(Applicant applicant) {
        while (true) {
            System.out.println("\n--- Applicant Menu ---");
            System.out.println("1. View Application");
            System.out.println("2. Change Password");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Application app = applicant.getApplication();
                    if (app != null) {
                        System.out.println("Application status: " + app.getStatus());
                    } else {
                        System.out.println("No application found.");
                    }
                    break;
                case 2:
                    changePassword(applicant);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void officerMenu(HDBOfficer officer) {
        while (true) {
            System.out.println("\n--- HDB Officer Menu ---");
            System.out.println("1. View Application");
            System.out.println("2. Book Flat for Applicant");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Application app = officer.getApplication();
                    System.out.println(app != null ? app.getStatus() : "No application.");
                    break;
                case 2:
                    System.out.print("Enter Applicant NRIC: ");
                    String targetNRIC = scanner.nextLine().toUpperCase();
                    if (userMap.containsKey(targetNRIC) && userMap.get(targetNRIC) instanceof Applicant) {
                        officer.bookFlat((Applicant) userMap.get(targetNRIC));
                        System.out.println("Flat booked.");
                    } else {
                        System.out.println("Applicant not found.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void managerMenu(HDBManager manager) {
        while (true) {
            System.out.println("\n--- HDB Manager Menu ---");
            System.out.println("1. View My Projects");
            System.out.println("2. Change Password");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    List<Project> projects = manager.getCreatedProjects();
                    if (projects.isEmpty()) {
                        System.out.println("No projects created.");
                    } else {
                        for (Project p : projects) {
                            System.out.println("- " + p.getName());
                        }
                    }
                    break;
                case 2:
                    changePassword(manager);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void changePassword(User user) {
        System.out.print("Enter new password: ");
        String newPwd = scanner.nextLine();
        user.changePassword(newPwd);
        System.out.println("Password updated. Please log in again.");
        System.exit(0); // Optional: force restart for demo
    }
}
