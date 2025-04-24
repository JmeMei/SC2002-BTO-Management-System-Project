#SC2002-OODP-Group Project

## Project Structure
The project is organized into multiple packages as follows:

### 1. App
- **App.java**### 2. Authentication
- **SessionManagerService.java**: Handles account-related operations.
- **AuthenticatorService.java**: Handles user authentication using NRIC and password credentials.

### 3. Controller
Controllers handle the logic and act as the handlers between the model and view.
Key controllers include:

- **AuthController.java**: Handles validating the user's login info to login to the system.
- **MenuViewController.java**: Handles showing the correct view to the user based on their role when logging in.
- **ApplicationController.java**: Manages all the logic dealing with applying to a BTO project, such as viewing and applying for a project.
- **PasswordController.java**: Handles changing password for any of the users.
- **OfficerRegistrationController.java**: Manages officer registration requests.
- **ProjectListController.java**: Handles project creation, editing, and deletion.
- **ReceiptController.java**: Manages flat booking receipts.
- **WithdrawalRequestController.java**: Handles withdrawal requests.
- **ApplicationStatusController.java**: Approves and views application statuses.
- **EnquiryController.java**: Handles enquiries from applicants, officers, and managers.
- **BookingController.java**: Processes flat bookings.

### 4. Data
CSV files that serve as the database for the application:

- **ApplicantList.csv**: Holds a list of users who are Applicants.
- **OfficerList.csv**: Holds a list of users who are HDB Officers.
- **ManagerList.csv**: Holds a list of users who are HDB Managers.
- **OfficerRegistrationRequests.csv**: List of people who are trying to join a BTO project as a Officer.
- **ProjectList.csv**: Holds a list of BTO projects that is available.
- **Receipts.csv**: Booking receipts.
- **Enquiries.csv**: Enquiries submitted by users.
- **WithdrawalRequests.csv**: Applications to withdraw from projects.

### 5. DataAccess
DAO classes for managing CRUD operations with CSV files:

- **ApplicantDAO.java**: Extracts the list of users who are Applicants from csv into an ArrayList of Applicants for CRUD Operations.
- **HDBOfficerDAO.java**: Extracts the list of users who are HDB Officers from csv into an ArrayList of HDB Officers for CRUD Operations.
- **HDBManagerDAO.java**: Extracts the list of users who are HDB Managers from csv into an ArrayList of HDB Managers for CRUD Operations.
- **OfficerRegistrationRequestDAO.java**: Extracts the list of Registration Requests from csv into an ArrayList of Registration Requests for CRUD Operations.
- **ProjectListDAO.java**: Extracts the list of Projects from csv into an ArrayList of Projects for CRUD Operations.
- **ReceiptsDAO.java**: Extracts the list of Receipts from csv into an ArrayList of Receipts for CRUD Operations.
- **EnquiryDAO.java**: Extracts the list of Enquiries from csv into an ArrayList of Enquiries for CRUD Operations.
- **WithdrawalRequestDAO.java**: Extracts the list of Withdrawal Requests from csv into an ArrayList of Withdrawal Requests for CRUD Operations.

### 6. Model
Model classes that represent the data structure:

- **User.java**: A overall class that the different roles inherit from
- **Applicant.java**: Represents the Applicant role
- **HDBOfficer.java**: Represents the HDB Officer role
- **HDBManager.java**: Represents the HDB Manager role
- **Project.java**: BTO project model.
- **Receipt.java**: Represents a flat booking receipt.
- **Enquiry.java**: Represents an enquiry.
- **WithdrawalRequest.java**: Withdrawal request for BTO project.
- **OfficerRegistrationRequest.java**: Officer's project registration request.
- **ApplicantProjectStatus.java**: Combined view of user + application data.
- **FlatType, ApplicationStatus**: Enums for project and application data.

### 7. View
View classes that handles the user interface, includes displaying data and taking inputs from user:

### Main Menu Views:
- **LoginPromptView.java**: Displays login view and takes info for the user to log in
- **ApplicantView.java**: Displays the menu for the Applicant user
- **HDBOfficerView.java**: Displays the menu for the Officer user
- **HDBManagerView.java**: Displays the menu for the Manager user

### Applicant Views:
- **ApplicantViewApplyProjectView.java**: Displays the menu for project applications.
- **ApplicantManageApplicationView.java**: Displays the menu for managing applications. 
- **ApplicantEnquiryView.java**: Displays the menu for Enquiry management for applicants.
- **ApplicantViewProjectsView.java**: Displays the available projects for eligible flat types.

### Officer Views:
- **HDBOfficerAssignedProjectView.java**: Dashboard for handling assigned project tasks.
- **HDBOfficerEnquiryView.java**: Officer’s view for submitting and responding to enquiries.
- **HDBOfficerProjectRegistrationView.java**: Officer’s interface for project registration.

### Manager Views:
- **HDBManagerCreateProjectView.java**: Displays the menu for creating a new BTO project.
- **HDBManagerEditProjectView.java**: Displays the menu for editing existing project attributes.
- **HDBManagerDeleteProjectView.java**: Displays the menu for managers to delete a project.
- **HDBManagerViewProjectsView.java**: Displays the menu for View and filter existing projects.
- **HDBManagerApproveOfficerView.java**: Displays the menu to approve officer registration.
- **HDBManagerApproveBTOApplicationView.java**: Displays the menu to Approve/decline BTO applications.
- **HDBManagerEnquiryView.java**: Displays the menu to View and respond to project-related enquiries.
- **HDBManagerWithdrawalRequestView.java**: Displays the menu to Handle withdrawal requests.

### Shared Views:
- **ReceiptsView.java**: Displays the menu to Filter and view booking receipts.
- **EnquiryView.java**: Displays the menu for enquiry views.