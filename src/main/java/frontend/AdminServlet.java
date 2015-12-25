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

        switch (method){

        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("123");
        //Map<String, Object> pageVariables = new HashMap<String, Object>();
        //UserProfile profile = accountService.getUser(login);

//        if (profile != null && profile.getPassword().equals(password)) {
  //          pageVariables.put("loginStatus", "You have successfully logged");
    //    } else {
      //      pageVariables.put("loginStatus", "Wrong login/password");
        //}
        //response.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));
    }
}