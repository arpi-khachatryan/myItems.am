package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class CategoriesServlet extends HttpServlet {

    private CategoryManager categoryManager = new CategoryManager();
    private ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoriesList = categoryManager.getAll();
        req.setAttribute("categories", categoriesList);
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}
