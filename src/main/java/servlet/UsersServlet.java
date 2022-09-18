package servlet;

import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet {

    private final UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        if (userId == null) {
            req.setAttribute("users", userManager.getAll());
            req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
        } else {
            userManager.deleteUserById(Integer.parseInt(userId));
            resp.sendRedirect("/users");
        }
    }
}
