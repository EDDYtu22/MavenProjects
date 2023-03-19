package dev.edmond;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Connection conn = DriverManager
            .getConnection( "jdbc:h2:~/students", "edmond", "123456");

            System.out.println("Connection valid/no: " + conn.isValid(5));

            String querySelectAll = "SELECT * FROM Students";
            Statement statementSelectAll  = conn.createStatement();
            ResultSet rs = statementSelectAll.executeQuery(querySelectAll);

            while(rs.next()){
                System.out.print(rs.getString("ID") + " ");
                System.out.print(rs.getString("FirstName") + " ");
                System.out.print(rs.getString("LastName"));
                System.out.println();
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
