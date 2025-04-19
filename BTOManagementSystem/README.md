#SC2002-OODP-Group Project

##Project Structure
The project is organized into multiple packages as follows:

1. App
- App.java : The main class to run the application

2. Authentication

- SessionManager.java : Handles account-related operation

3. Controller
Controllers handle the logic and act as the handlers between the model and view.
Key controllers include:

- AuthController.java : Handles validating the user's login info to login to the system.
- MenuViewController.java : Handles showing the correct view to the user based on their role when logging in.
- ApplicationController.java : Manages all the logic dealing with applying to a BTO project, such as viewing and applying for a project.
- PasswordController.java : Handles changing password for any of the users.

4. Data
CSV files that serve as the database for the application

- ApplicantList.csv : Holds a list of users who are Applicants.
- OfficerList.csv : Holds a list of users who are HDB Officers.
- ManagerList.csv : Holds a list of users who are HDB Managers.
- OfficerRegistrationRequests.csv : List of people who are trying to join a BTO project as a Officer.
- ProjectList.csv : Holds a list of BTO projects that is available.

5. DataAccess
DAO classes for managing CRUD operations with CSV files:

- UserDAO.java : 

6. Model
Model classes that represent the data structure:

- User.java : A overall class that the different roles inherit from
- Applicant.java : Represents the Applicant role
- HDBOfficer.java : Represents the HDB Officer role
- HDBManager.java : Represents the HDB Manager role

7. View
View classes handles the user interface, includes displaying data and taking inputs from user.

- LoginPromptView.java : Displays login view and takes info for the user to log in
- ApplicantView.java : Displays the menu for the Applicant user
- HDBOfficerView.java : Displays the menu for the Officer user
- HDBManagerView.java : Displays the menu for the Manager user
- ApplicantEnquiryview.java : Displays the enquiry menu for a user who is an Applicant