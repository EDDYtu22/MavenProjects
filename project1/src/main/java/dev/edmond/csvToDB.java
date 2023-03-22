package dev.edmond;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.print.DocFlavor.INPUT_STREAM;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import dev.edmond.DB.DBConnection;

public class csvToDB {

    public static void main(String[] args) {

        try {
            Connection conn = DBConnection.getInstance();

            // crate table if not exists
            String createDBQuery = "CREATE TABLE IF NOT EXISTS USERNAMES (USERNAME VARCHAR(255), IDENTIFIER INT NOT NULL, FIRST_NAME VARCHAR(255), LAST_NAME VARCHAR(255), PRIMARY KEY(IDENTIFIER))";
            Statement statement = conn.createStatement();
            statement.execute(createDBQuery);

            String truncateTable = "TRUNCATE TABLE USERNAMES";
            Statement truncStatement = conn.createStatement();
            truncStatement.execute(truncateTable);

            conn.setAutoCommit(false);

            String inserValuesQuery = "INSERT INTO USERNAMES (USERNAME, IDENTIFIER, FIRST_NAME, LAST_NAME) VALUES(?,?,?,?)";
            PreparedStatement inserValuesStatement = conn.prepareStatement(inserValuesQuery);
            // iterate over csv file and for each record insert in DB
            Reader in = new FileReader("/Users/edko/Main/MavenProjects/project1/src/main/resourses/username.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withDelimiter(';').parse(in);
            for (CSVRecord record : records) {
                String username = record.get("Username");
                Integer identifier = Integer.valueOf(record.get("Identifier"));
                String firstName = record.get("First name");
                String lastName = record.get("Last name");
                
                inserValuesStatement.setString(1, username);
                inserValuesStatement.setInt(2, identifier);
                inserValuesStatement.setString(3, firstName);
                inserValuesStatement.setString(4, lastName);

                inserValuesStatement.addBatch();
                inserValuesStatement.clearParameters();

            }
            inserValuesStatement.executeBatch();
            conn.commit();
            statement.close();
            inserValuesStatement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
