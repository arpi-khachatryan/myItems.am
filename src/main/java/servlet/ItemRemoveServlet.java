package servlet;

import manager.ItemManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/items/remove")
public class ItemRemoveServlet extends HttpServlet {

    private final ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemManager.deleteItemById(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/item");
    }
}
