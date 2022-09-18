package servlet;

import manager.CategoryManager;
import manager.ItemManager;
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
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100)
public class AddItemServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();
    private final CategoryManager categoryManager = new CategoryManager();
    private static final String IMAGE_PATH = "/Users/annakhachatryan/Library/Application Support/JetBrains/myItems.am/projectImages/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        User user = (User) req.getSession().getAttribute("user");

        saveItem(title, price, categoryId, profilePicPart, user);

        resp.sendRedirect("/main.jsp");
    }

    private void saveItem(String title, double price, int categoryId, Part profilePicPart, User user) throws IOException {
        String fileName = null;
        if (profilePicPart.getName().contains(".jpeg") || profilePicPart.getName().contains(".png")) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(IMAGE_PATH + fileName);
        }
        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getById(categoryId))
                .picUrl(fileName)
//                .user(userManager.getById(userId))
                .user(user)
                .build();
        itemManager.add(item);
    }
}

