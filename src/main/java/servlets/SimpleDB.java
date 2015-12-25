package servlets;

import java.sql.*;
import java.util.Optional;

/**
 * Created by TwistedPersonality on 20.12.2015.
 */
public class SimpleDB
{
    private SimpleStatement stmt = null;

    public SimpleDB(String host, String DB, String login, String pass)
    {
        stmt = new SimpleStatement(host, DB, login, pass);
    }

    public ResultSet ExecSelect(String query)
    {
        try
        {
            return stmt.getStmt().executeQuery(query);
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
            return Optional.of(stmt.getStmt().executeUpdate(query));
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

    private String mHost, mDB, mUser, mPass;

    public SimpleStatement(String host, String DB, String user, String pass)
    {
        mHost = host; mDB = DB; mUser = user; mPass = pass;
    }

    public Statement getStmt()
    {
        try
        {
            if (stmt == null || stmt.isClosed())
            {
                Connection conn = DriverManager.getConnection(mHost + '/' + mDB, mUser, mPass);
                stmt = conn.createStatement();
            }
        }
        catch(SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return  stmt;
    }
}