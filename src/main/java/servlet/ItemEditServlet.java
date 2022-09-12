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

@WebServlet(urlPatterns = "/items/edit")
public class ItemEditServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("id"));
        Item item = itemManager.getById(itemId);
        req.setAttribute("items", item);
        req.setAttribute("categories", categoryManager.getAll());
        req.setAttribute("users", userManager.getAll());
        req.getRequestDispatcher("/WEB-INF/editItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        String picUrl = req.getParameter("pic_url");
        Category category = categoryManager.getById(categoryId);
//        int userId = Integer.parseInt(req.getParameter("user_id"));
//        User user = userManager.getById(userId);

        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(category)
                .picUrl(picUrl)
//                .user(user)
                .id(itemId)
                .build();
        itemManager.edit(item);
        resp.sendRedirect("/item");
    }
}
