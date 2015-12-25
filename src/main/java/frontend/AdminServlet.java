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

public class AdminServlet extends HttpServlet {
    private AccountService accountService;

    public AdminServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<String,Object>();

        pageVariables.put("users", accountService.getAllUsers());
        response.getWriter().println(PageGenerator.getPage("admin.html", pageVariables));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if(method.equals("deleteUser")){
            String login = request.getParameter("login");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(accountService.DelUsers(login));
        }else{
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}