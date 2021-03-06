package servlets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by TwistedPersonality on 20.12.2015.
 */
public class SimpleUsers
{
    private SimpleDB NiceDB = null;

    public SimpleUsers()
    {
        NiceDB = new SimpleDB("jdbc:mysql://eu-cdbr-azure-west-d.cloudapp.net",
                                "NiceDB",
                                "b7669db42d93ae",
                                "b8876401");
    }

    public Optional<UserProfile> FindUserByLogin(String login)
    {
        ResultSet rs = NiceDB.ExecSelect("select * from Accounts where Login = '" + login +"'");

        try
        {
            if (rs != null && rs.next())
                return Optional.of(new UserProfile (rs.getString("Login"), rs.getString("Pass"), ""));
            else
                return Optional.of(new UserProfile (null, null, null));
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return Optional.empty();
        }
    }

    public List<UserProfile> FindAllUsers()
    {
        List<UserProfile> result = new ArrayList<UserProfile>();
        ResultSet rs = NiceDB.ExecSelect("select * from Accounts");

        if (rs == null)
            return result;

        try
        {
            while(rs.next())
            {
                result.add(new UserProfile(rs.getString("Login"), rs.getString("Pass"), ""));
            }
            return result;
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public Optional<Integer> AddUser(UserProfile uprof)
    {
        Optional<Integer> result = NiceDB.ExecUpdate("insert into Accounts (Login, Pass) values" +
                                                     "('" + uprof.getLogin() + "', '" + uprof.getPassword() + "');");
        return result;
    }

    public Optional<Integer> DelUsers(String login)
    {
        if (login.isEmpty())
            return Optional.empty();

        Optional<Integer> result = NiceDB.ExecUpdate("delete from Accounts where Login in ('" + String.join("', '", login) + "')");
        return result;
    }

    public Optional<Integer> ChangePass(String login, String newPass)
    {
        Optional<Integer> result = NiceDB.ExecUpdate("update Accounts set Pass='" + newPass + "' where Login='" + login + "'");
        return result;
    }
}
