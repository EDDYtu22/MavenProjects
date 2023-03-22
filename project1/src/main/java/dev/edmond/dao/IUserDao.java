package dev.edmond.dao;

import java.util.List;

import com.mysql.cj.callback.UsernameCallback;

public interface IUserDao {
    
    List<User> getAll();
    // obtain db connection
    // execute Select *
    // iterate over result set
    // map each result to user object
    //insert each user in a list
    // return the list of users

    int update (User user);

    int delete (User user);

    int delete (int id);

    int insert (User user);

}
