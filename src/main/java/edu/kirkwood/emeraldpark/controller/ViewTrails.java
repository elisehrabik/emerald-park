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
import java.util.Arrays;
import java.util.List;

@WebServlet(value="/view-trails")
public class ViewTrails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the limit
        String limitStr = req.getParameter("limit");
        int limit = 6;
        try {
            limit = Integer.parseInt(limitStr);
        } catch (NumberFormatException e) {
            if (limit < 0) {
                limit = 6;
            }
        }
        req.setAttribute("limit", limit);

        // Get categories
        String[] categoriesArr = req.getParameterValues("categories");
        String categories = "";
        if (categoriesArr != null && categoriesArr.length > 0) {
            categories = String.join(",", categoriesArr);
        }
        req.setAttribute("categoriesArr", categoriesArr);

        // Difficulties
        String[] selectedDifficulties = req.getParameterValues("difficulty");
        req.setAttribute("selectedDifficulties", selectedDifficulties);

        // Get the total trail count
        int totalTrails = TrailDAO.getTrailCount(categories, selectedDifficulties);
        int totalPages = totalTrails / limit;
        if(totalTrails % limit != 0) {
            totalPages++;
        }
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalTrails", totalTrails);

        // Get the page number
        String pageStr = req.getParameter("page");
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {

        }
        if(page > totalPages) {
            page = totalPages;
        } else if (page < 1) {
            page = 1;
        }
        req.setAttribute("page", page);
        int offset = (page - 1) * limit;

        // Calculate begin and end page links
        int pageLinks = 3;
        int beginPage = page / pageLinks * pageLinks > 0 ? page / pageLinks * pageLinks : 1;
        int endPage = beginPage + pageLinks - 1 > totalPages ? totalPages : beginPage + pageLinks - 1;
        req.setAttribute("beginPage", beginPage);
        req.setAttribute("endPage", endPage);

        // Determine first and last trails shown
        int firstTrailShown = 1 + (page - 1) * limit;
        req.setAttribute("firstTrailShown", firstTrailShown);
        int lastTrailShown = limit + (page - 1) * limit;
        if(lastTrailShown > totalTrails) {
            lastTrailShown = totalTrails;
        }
        req.setAttribute("lastTrailShown", lastTrailShown);


        List<Trail> trails = TrailDAO.getTrails(limit, offset, categories, selectedDifficulties);
        List<TrailCategory> trailCategories = TrailDAO.getAllCategories();
        req.setAttribute("trails", trails);
        req.setAttribute("trailCategories", trailCategories);
        req.setAttribute("selectedDifficulties", selectedDifficulties != null ? Arrays.asList(selectedDifficulties) : List.of());
        req.setAttribute("pageTitle", "All Trails");
        req.getRequestDispatcher("WEB-INF/view-trails.jsp").forward(req, resp);
    }
}
