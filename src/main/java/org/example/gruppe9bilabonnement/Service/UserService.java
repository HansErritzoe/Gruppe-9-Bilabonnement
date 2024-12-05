package org.example.gruppe9bilabonnement.Service;


import org.example.gruppe9bilabonnement.Model.User;
import org.example.gruppe9bilabonnement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Checks if username exist in DB via repository class
     * @param username - username to be checked if exists (String)
     * @return true if exists, false if not
     * Author - Hans Erritzøe - Taken partially from another project i've made
     */
    public boolean doesUsernameExist(String username) {
        return userRepository.doesUsernameExist(username);
    }

    /**
     * Checks if a username matches the password for a row in the "bruger" table in DB via UserRepository class
     * @param username username of the user attempting to login
     * @param password password of the user attempting to login
     * @return true if matches, false if not
     * Author - Hans Erritzøe - Taken partially from another project i've made
     */
    public boolean doesUsernameMatchPassword(String username, String password) {
        return userRepository.doesUsernameMatchPassword(username,password);
    }

    /**
     * Calls UserRepository class to return User object from DB using username
     * @param username - username of user attempting to login
     * @return User object containing user information from DB
     * Author - Hans Erritzøe - Taken partially from another project i've made
     */
    public User retrieveUserByUsername(String username) {
        return userRepository.retrieveUserByUsername(username);
    }
}
