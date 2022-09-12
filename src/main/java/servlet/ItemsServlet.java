package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items")
public class ItemsServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        if (categoryId == 0) {
            List<Item> items = itemManager.getAll();
            req.setAttribute("items", items);
            req.getRequestDispatcher("/WEB-INF/item.jsp").forward(req, resp);
        } else {
            Category category = categoryManager.getById(categoryId);

            if (category == null) {
                resp.sendRedirect("/main.jsp");
            } else {
                List<Item> item = itemManager.getAllByCategoryId(categoryId);
                req.setAttribute("items", item);
                req.getRequestDispatcher("/WEB-INF/item.jsp").forward(req, resp);
            }
        }
    }
}
