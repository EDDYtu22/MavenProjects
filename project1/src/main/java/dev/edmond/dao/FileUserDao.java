package dev.edmond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import dev.edmond.DB.DBConnection;
import dev.edmond.models.User;



public class FileUserDao implements IUserDao {


    FileUserDao() {
        System.out.println("FileUserDao constructor executed!");
    }

    @Override
    public List<User> getAll() {

        List<User> userList = new ArrayList<>();
        try {

            Connection conn = DBConnection.getInstance();
            Statement selectAllStatement = conn.createStatement();

            String querySelectAll = "SELECT * FROM USERNAMES";
            ResultSet rs = selectAllStatement.executeQuery(querySelectAll);

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("USERNAME"));
                user.setIdentifier(rs.getInt("IDENTIFIER"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));

                userList.add(user);

            }

            rs.close();
            selectAllStatement.close();

            return userList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public int update(User user) {

        try {

            Connection conn = DBConnection.getInstance();
            PreparedStatement statementUpdate = conn.prepareStatement(
                    "UPDATE USERNAMES SET USERNAME = ?, FIRST_NAME = ?, LAST_NAME = ? WHERE IDENTIFIER = ?;");

            statementUpdate.setString(1, user.getUsername());
            statementUpdate.setString(2, user.getFirstName());
            statementUpdate.setString(3, user.getLastName());
            statementUpdate.setInt(4, user.getIdentifier());

            int rs = statementUpdate.executeUpdate();

            if (rs == 0) {
                System.out.println("User not found -> update impossible!");
            }
            if (rs == 1) {
                System.out.println("Update successful!");
            }

            statementUpdate.close();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(User user) {

        try {
            Connection conn = DBConnection.getInstance();
            PreparedStatement statementDelete = conn.prepareStatement(
                    "DELETE FROM USERNAMES WHERE USERNAME = ? AND IDENTIFIER = ? AND FIRST_NAME = ? AND LAST_NAME = ?;");

            statementDelete.setString(1, user.getUsername());
            statementDelete.setInt(2, user.getIdentifier());
            statementDelete.setString(3, user.getFirstName());
            statementDelete.setString(4, user.getLastName());


            int rs = statementDelete.executeUpdate();

            if (rs == 0) {
                System.out.println("User not found -> delete impossible!");
            }
            if (rs == 1) {
                System.out.println("Delete successful!");
            }

            statementDelete.close();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }

    @Override
    public int delete(Integer id) {
        try {
            Connection conn = DBConnection.getInstance();
            PreparedStatement statementDelete =
                    conn.prepareStatement("DELETE FROM USERNAMES WHERE IDENTIFIER = ?;");

            statementDelete.setInt(1, id);

            int rs = statementDelete.executeUpdate();

            if (rs == 0) {
                System.out.println("User not found -> delete impossible!");
            }
            if (rs == 1) {
                System.out.println("Delete successful!");
            }

            statementDelete.close();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int insert(User user) {

        try {
            Connection conn  = DBConnection.getInstance();
            PreparedStatement insertUser = conn.prepareStatement("INSERT INTO USERNAMES (USERNAME, IDENTIFIER, FIRST_NAME, LAST_NAME) VALUES (?,?,?,?)");

            insertUser.setString(1, user.getUsername());
            insertUser.setInt(2, user.getIdentifier());
            insertUser.setString(3, user.getFirstName());
            insertUser.setString(4, user.getLastName());

            int rs = insertUser.executeUpdate();

            return 1;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }

}
