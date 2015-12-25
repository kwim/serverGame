package servlets;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Created by zak on 14.11.2015.
 */
public class AccountService {

    private SimpleUsers users = new SimpleUsers();
    private Map<String,String> sessions = new HashMap<String,String>();

    public boolean addUser(UserProfile userProfile)
    {
        Optional<Integer> res = users.AddUser(userProfile);

        if (res.isPresent() && res.get() > 0)
            return true;
        return false;
    }

    public UserProfile getUser(String userName)
    {
        return users.FindUserByLogin(userName);
    }

    public boolean DelUsers(String login)
    {
        Optional<Integer> res = users.DelUsers(login);

        if (res.isPresent() && res.get() > 0){
            return true;
        }
        return false;
    }

    public List<UserProfile> getAllUsers()
    {
        return users.FindAllUsers();
    }

    public boolean addSession(String sessionID, String login)
    {
        Jedis red = new Jedis("localhost");
        red.connect();

        if(red.isConnected() && red.hset("SStorage", sessionID, login) > 0)
            return true;
        return false;
    }

    public String getSession(String sessionID)
    {
        Jedis red = new Jedis("localhost");
        red.connect();

        if(red.isConnected())
            return red.hget("SStorage", sessionID);
        return null;
    }
}