package dev.edmond;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sql_hr", "root", "Oskar2015");

            System.out.println("Connection valid/no: " + conn.isValid(5));

            String querySelectAll = "SELECT * FROM sql_hr.employees";
            Statement statementSelectAll = conn.createStatement();
            ResultSet rs =  statementSelectAll.executeQuery(querySelectAll);

            while (rs.next()) {
                System.out.print(rs.getString("employee_id") + "  ");
                System.out.print(rs.getString("first_name") + " ");
                System.out.print(rs.getString("last_name"));
                System.out.print(org.apache.commons.lang3.StringUtils.repeat(' ',
                        20 - (rs.getString("first_name").length() + rs.getString("last_name").length())));
                System.out.print(rs.getString("job_title"));
                System.out.println();
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
