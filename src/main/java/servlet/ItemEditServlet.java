package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/items/edit")
public class ItemEditServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Item item = itemManager.getById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("item", item);
        req.setAttribute("categories", categoryManager.getAll());
        req.getRequestDispatcher("/WEB-INF/editItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int itemId = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        String picUrl = req.getParameter("pic_url");

        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getById(Integer.parseInt(req.getParameter("category_id"))))
                .picUrl(picUrl)
                .id(itemId)
                .build();

        itemManager.edit(item);

        resp.sendRedirect("/item");
    }
}
