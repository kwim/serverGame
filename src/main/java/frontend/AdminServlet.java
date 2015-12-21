package frontend;

import servlets.AccountService;
import servlets.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class AdminServlet extends HttpServlet {
    private AccountService accountService;

    public AdminServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<String,Object>();

        List<Map> users = new ArrayList<Map>();
        for(Integer i=0; i < 5; i++){
            Map<String, String> user = new HashMap<String,String>();
            user.put("login","login");
            user.put("password","password");

            users.add(user);
        }

        pageVariables.put("users", users);
        response.getWriter().println(PageGenerator.getPage("admin.html", pageVariables));
    }

//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        response.setStatus(HttpServletResponse.SC_OK);
//        Map<String, Object> pageVariables = new HashMap<String, Object>();
//        UserProfile profile = accountService.getUser(login);
//
//        if (profile != null && profile.getPassword().equals(password)) {
//            pageVariables.put("loginStatus", "You have successfully logged");
//        } else {
//            pageVariables.put("loginStatus", "Wrong login/password");
//        }
//        response.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));
//    }
}