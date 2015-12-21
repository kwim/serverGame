package servlets;

import java.sql.*;
import java.util.Optional;

/**
 * Created by TwistedPersonality on 20.12.2015.
 */
public class SimpleDB
{
    private Statement stmt = null;

    public SimpleDB(String host, String DB, String login, String pass)
    {
        SimpleStatement s = new SimpleStatement(host, DB, login, pass);
        stmt = s.getStmt();
    }

    public boolean isClosed(){
        return stmt == null;
    }

    public ResultSet ExecSelect(String query)
    {
        try
        {
            return stmt.executeQuery(query);
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
        catch (NullPointerException ex)
        {
            System.out.println("NullPointerException: No connection to the database.");
            return null;
        }
    }

    public Optional<Integer> ExecUpdate(String query)
    {
        try
        {
            return Optional.of(stmt.executeUpdate(query));
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return Optional.empty();
        }
        catch (NullPointerException ex)
        {
            System.out.println("NullPointerException: No connection to the database.");
            return Optional.empty();
        }
    }
}

class SimpleStatement
{
    private Statement stmt = null;

    public SimpleStatement(String host, String DB, String user, String pass)
    {
        try
        {
            Connection conn = DriverManager.getConnection(host + '/' + DB, user, pass);
            stmt = conn.createStatement();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public Statement getStmt()
    {
        return  stmt;
    }
}