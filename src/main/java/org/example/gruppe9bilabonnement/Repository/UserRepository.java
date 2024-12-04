package org.example.gruppe9bilabonnement.Repository;

import org.example.gruppe9bilabonnement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    /**
     * Checks if username exists in mysql database
     * @param username - the username to be checked if exist (String)
     * @return true if user exists, false if not
     * Author - Hans Erritzøe - Taken from a previous project i've made
     */
    public boolean doesUsernameExist(String username) {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        Integer count = template.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    /**
     * Checks if username matches password and return true if so
     * @param username username of the user attempting to login
     * @param password password of the user attempting to login
     * @return true if username matches password in DB
     * Author - Hans Erritzøe - taken partially from another project i've made
     */
    public boolean doesUsernameMatchPassword(String username, String password){
        String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";
        Integer count = template.queryForObject(sql, Integer.class, username, password);
        return count != null && count > 0;
    }

    /**
     * Returns a user object based on the given username, remember to check if user exists before running
     * @param username username of the user attempting to login
     * @return User object containing all the information of the user
     * Author - Hans Erritzøe - taken from another project i've made
     */
    public User retrieveUserByUsername(String username){
        String sql = "SELECT * FROM user WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User p = template.queryForObject(sql, rowMapper, username);
        return p;
    }

}
