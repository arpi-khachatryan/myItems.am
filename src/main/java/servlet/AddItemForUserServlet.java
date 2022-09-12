package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/items/add/user")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddItemForUserServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();
    private static final String imagePath = "/Users/annakhachatryan/Library/Application Support/JetBrains/myItems.am/projectImages/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        User user = userManager.getById(userId);
        req.setAttribute("user", user);
        List<Category> categories = categoryManager.getAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/addItemForUser.jsp").forward(req, resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        Part profilePicPart = req.getPart("picUrl");
        int userId = Integer.parseInt(req.getParameter("user_id"));
        String fileName = null;
        if (profilePicPart.getSize() != 0) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(imagePath + fileName);
        }
        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getById(categoryId))
                .picUrl(fileName)
                .user(userManager.getById(userId))
                .build();
        itemManager.add(item);
        resp.sendRedirect("/item");
    }
}

