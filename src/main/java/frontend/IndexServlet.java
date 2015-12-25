package frontend;

import servlets.AccountService;
import servlets.UserProfile;
import templater.PageGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IndexServlet extends HttpServlet {
    private AccountService accountService;

    public IndexServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        HttpSession session = request.getSession();

        if (accountService.getSession(session.getId()) == null){
            response.getWriter().println(PageGenerator.getPage("index.html", new HashMap<String, Object>()));
        }
        else {
            response.sendRedirect("/signin");
        }

        return;
    }
}