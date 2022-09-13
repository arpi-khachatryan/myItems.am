package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        List<Item> items = itemManager.getAllByUserId(userId);
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/item.jsp").forward(req, resp);
    }
}
