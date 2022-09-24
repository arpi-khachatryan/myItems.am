package servlet;

import manager.CategoryManager;
import manager.ItemManager;
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

    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catId = req.getParameter("id");

        List<Item> items;
        if (catId == null) {
            User user = (User) req.getSession().getAttribute("user");
            items = itemManager.getAllByUserId(user.getId());
        } else {
            int categoryId = Integer.parseInt(catId);
            Category category = categoryManager.getById(categoryId);
//            if (category == null) {
//                resp.sendRedirect("/main.jsp");
//            }
            if (categoryId == 0) {
                items = itemManager.getAll();
            } else {
                items = itemManager.getAllByCategoryId(categoryId);
            }
        }
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/item.jsp").forward(req, resp);
    }
}
