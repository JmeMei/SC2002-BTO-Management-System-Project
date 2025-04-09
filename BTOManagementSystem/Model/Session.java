package BTOManagementSystem.Model;


public class Session {

    private static User currentUser = null;


    public static void updateSession(String NRIC){

        
        //currentUser = new User("John doe","T1234567A",20,"Married","Applicant");

    }

    public static User getSessionUser(){
        return currentUser;
    }

}
