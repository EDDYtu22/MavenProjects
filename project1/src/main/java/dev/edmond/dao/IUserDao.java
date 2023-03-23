package dev.edmond.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import dev.edmond.DB.DBConnection;
import dev.edmond.models.User;

public interface IUserDao {

    List<User> getAll();
    // obtain db connection
    // execute Select *
    // iterate over result set
    // map each result to user object
    // insert each user in a list
    // return the list of users

    int update(User user);

    int delete(User user);

    int delete(Integer id);

    int insert(User user);

}
