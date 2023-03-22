package dev.edmond;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

import org.h2.bnf.context.DbColumn;
import org.h2.engine.User;

import dev.edmond.DB.DBConnection;
import dev.edmond.dao.DBUserDao;

public class App {

    public static void main(String[] args) {
        // try {
        //     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_hr",
        //             "root", "Oskar2015");

        //     System.out.println("Connection valid/no: " + conn.isValid(5));

        //     String querySelectAll = "SELECT * FROM sql_hr.employees";
        //     Statement statementSelectAll = conn.createStatement();
        //     ResultSet rs = statementSelectAll.executeQuery(querySelectAll);

        //     while (rs.next()) {
        //         System.out.print(rs.getString("employee_id") + " ");
        //         System.out.print(rs.getString("first_name") + " ");
        //         System.out.print(rs.getString("last_name"));
        //         System.out.print(org.apache.commons.lang3.StringUtils.repeat(' ',
        //                 20 - (rs.getString("first_name").length()
        //                         + rs.getString("last_name").length())));
        //         System.out.print(rs.getString("job_title"));
        //         System.out.println();
        //     }

        //     System.out.println("-------------------------------");
        //     System.out.println("Enter first leter to list all names starting with this letter: ");
        //     Scanner input = new Scanner(System.in);
        //     String in = input.nextLine();

        //     String querySelectWithGivenFirstLetter =
        //             "SELECT * FROM sql_hr.employees WHERE first_name LIKE '" + in + "%';";
        //     Statement statementSelectWithGivenFirstLetter = conn.createStatement();
        //     rs = statementSelectWithGivenFirstLetter.executeQuery(querySelectWithGivenFirstLetter);

        //     if (!rs.next()) {
        //         System.out.println("No such Employees!");
        //     }

        //     while (rs.next()) {
        //         System.out.print(rs.getString("employee_id") + " ");
        //         System.out.print(rs.getString("first_name") + " ");
        //         System.out.print(rs.getString("last_name"));
        //         System.out.print(org.apache.commons.lang3.StringUtils.repeat(' ',
        //                 20 - (rs.getString("first_name").length()
        //                         + rs.getString("last_name").length())));
        //         System.out.print(rs.getString("job_title"));
        //         System.out.println();
        //     }

        //     conn.close();
        // } catch (SQLException e) {
        //     System.out.println(e.getMessage());
        // }

        // ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        // for (String bean : context.getBeanDefinitionNames()) {
        //     System.out.println(bean);
        // }

        DBUserDao test = new DBUserDao();

        try  {
            Connection conn = DBConnection.getInstance();

            System.out.println(test.getAll());

            test.insert(new dev.edmond.dao.User("hans", 2445, "hani", "tomov"));

            System.out.println(test.getAll());


           conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
