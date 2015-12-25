package frontend;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import servlets.UserProfile;
import servlets.AccountService;
import templater.PageGenerator;
/**
 * Created by zak on 14.11.2015.
 */
public class SignInServlet  extends HttpServlet {
    private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String login = accountService.getSession(session.getId());
        String password;
        UserProfile profile;

        boolean isSuccess = true;

        if (login == null){
            login = request.getParameter("login");
            password = request.getParameter("password");
            profile = accountService.getUser(login);
            if (profile != null && profile.getPassword().equals(password)) {
                accountService.addSession(session.getId(), login);
            }
            else {
                isSuccess = false;
            }
        }
        else {
            profile = accountService.getUser(login);
        }

        if (isSuccess) {
            pageVariables.put("loginStatus", "You have successfully logged");
        } else {
            pageVariables.put("loginStatus", "Wrong login/password");
        }
        response.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));
    }
}