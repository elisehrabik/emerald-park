package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailCategory;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/view-trails")
public class ViewTrails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limitStr = req.getParameter("limit");
        int limit = 10;
        try {
            limit = Integer.parseInt(limitStr);
        } catch (NumberFormatException e) {
            if (limit < 0) {
                limit = 10;
            }
        }
        int offset = 0;
        String[] categoriesArr = req.getParameterValues("categories");
        String categories = "";
        if (categoriesArr != null && categoriesArr.length > 0) {
            categories = String.join(",", categoriesArr);
        }
        req.setAttribute("categories", categories);
        req.setAttribute("limit", limit);
        List<Trail> trails = TrailDAO.getTrails(limit, offset, categories);
        req.setAttribute("trails", trails);
        List<TrailCategory> trailCategories = TrailDAO.getAllCategories();
        req.setAttribute("trailCategories", trailCategories);
        req.getRequestDispatcher("WEB-INF/view-trails.jsp").forward(req, resp);
    }
}
