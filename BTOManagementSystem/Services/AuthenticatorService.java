package BTOManagementSystem.Services;

import BTOManagementSystem.Model.DAO.*;


public class AuthenticatorService {

    public boolean authenticate(String username, String password){

        UserDAO DAO = new UserDAO();

        if (DAO.NRIC_exist(username)){

            if (DAO.getPasswordOfUser(username).compareTo(password) == 0){

                return true;
            }

        }

        return false;
    }

}
