package servlets;

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

    public UserProfile getUser(String userName) {
        return users.FindUserByLogin(userName);
    }

    public List<UserProfile> getAllUsers() {
        return users.FindAllUsers();
    }

    public void addSession(String sessionID, String login) {
        sessions.put(sessionID, login);
    }

    public String getSession(String sessionID) {
        return sessions.get(sessionID);
    }
}