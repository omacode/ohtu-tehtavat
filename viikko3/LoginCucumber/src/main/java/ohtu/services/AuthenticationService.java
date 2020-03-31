package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        return invalidUsername(username) || invalidPassword(password);
    }
    
    private boolean invalidUsername(String username) {        
        return username.length() < 3 || !username.matches("^[a-z]*$");
    }
    
    private boolean invalidPassword(String password) {
        int length = password.length();            
            
        if (length < 8) {
            return true;
        }

        boolean onlyLetters = true;
        for (int i = 0; i < length; i++) {
            if ( !(Character.isLetter( password.charAt(i) )) ) {
                onlyLetters = false;
            }
        }
        
        return onlyLetters;
    }
}
