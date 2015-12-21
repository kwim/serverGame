package servlets;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Created by zak on 14.11.2015.
 */
public class AccountService {

    private SimpleUsers users = new SimpleUsers();
    private Map<String,UserProfile> sessions = new HashMap<String,UserProfile>();

    public boolean addUser(UserProfile userProfile)
    {
        Optional<Integer> res = users.AddUser(userProfile);

        if (res.isPresent() && res.get() > 0)
            return true;
        return false;
    }

    public void addSession(String sessionID, UserProfile userProfile) {
        sessions.put(sessionID,userProfile);
    }

    public UserProfile getUser(String userName) {
        return users.FindUserByLogin(userName);
    }

    public UserProfile getSession(String sessionID) {
        return sessions.get(sessionID);
    }
}