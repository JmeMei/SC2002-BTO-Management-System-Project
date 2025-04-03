package BTOManagementSystem;

public class Receipt {
    Private String applicantNRIC;
    private int age;
    private String maritalStatus;
    private String flatType;
    private String projectName;

    public Receipt(String nric, int age, String maritalStatus, String flatType, String projectName) {
        this.applicantNRIC = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.flatType = flatType;
        this.projectName = projectName;
    }

    public void printReceipt() {
        System.out.println("===== BTO Flat Booking Receipt =====");
        System.out.println("NRIC: " + applicantNRIC);
        System.out.println("Age: " + age);
        System.out.println("Marital Status: " + maritalStatus);
        System.out.println("Flat Type: " + flatType);
        System.out.println("Project Name: " + projectName);
        System.out.println("====================================");
    }
}
