package timebackup;

import java.sql.*;

public class Dbm {

    private static boolean inUse = false;

    private static synchronized void setUse(Boolean use) {
        if (use) {
            inUse = true;
        } else {
            inUse = false;
        }
    }

    private static synchronized boolean getIfInUse() {
        return inUse;
    }
    //We want to use the connection througout the whole class so it is
    //provided as a class level private variable
    private Connection c = null;

    //This constructor openes or creates the database provided by the arguement
    //NameOfDatabase
    public Dbm(String NameOfDatabase) {
        while (getIfInUse() == true) {
            //do nothing, just wait till it becomes available
        }
        //now that its available set it to in use
        setUse(true);
        try {
            //Database is checked for in project folder, if doesnt exist then creates
            //it
            c = DriverManager.getConnection("jdbc:sqlite:" + NameOfDatabase);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void CloseDB() {
        try {
            c.close();
            System.out.println("Closed Database Successfull");
        } catch (Exception e) {
            System.out.println("Failed to close Database due to error: " + e.getMessage());
        } finally {
            setUse(false);
        }
    }

    public void ExecuteNoreturnQuery(String SqlCommand) {

        //creates a statment to execute the query
        try {
            Statement stmt = null;
            stmt = c.createStatement();
            stmt.executeUpdate(SqlCommand);
            stmt.close();
            System.out.println("Sql query executed successfull");
        } catch (Exception e) {
            System.out.println("Failed to execute query due to error: " + e.getMessage());
            System.out.println(SqlCommand);

        } finally {

        }
    }

    // this method returns a ResultSet for a query which can be iterated through
    public ResultSet ExecuteSqlQueryWithReturn(String SqlCommand) {

        try {
            //create the statement
            Statement stmt = null;

            //use a statement to attach c to a statement
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SqlCommand);
            return rs;
        } catch (Exception e) {
            System.out.println("An Error has ocured while executing this query, please check the syntax and try again" + e.getMessage());
        } finally {

        }
        return null;
    }
}
