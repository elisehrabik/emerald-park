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
@WebServlet(value="/view-trail")
public class ViewTrail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get trail id
        String trailIdStr = req.getParameter("id");
        HttpSession session = req.getSession();

        if (trailIdStr == null || trailIdStr.isEmpty()) {
            session.setAttribute("flashMessageWarning", "Trail ID is required.");
            resp.sendRedirect(req.getContextPath() + "/trails");
            return;
        }

        int trail_id = 0;
        try {
            trail_id = Integer.parseInt(trailIdStr);
        } catch (NumberFormatException e) {
            session.setAttribute("flashMessageDanger", "Invalid Trail ID.");
            resp.sendRedirect(req.getContextPath() + "/trails");
            return;
        }

        Trail trail = TrailDAO.getTrail(String.valueOf(trail_id));
        if (trail == null) {
            session.setAttribute("flashMessageDanger", "Trail not found.");
            resp.sendRedirect(req.getContextPath() + "/trails");
            return;
        }

        req.setAttribute("trail", trail);

        List<TrailCategory> trailCategories = TrailDAO.getAllCategories();
        req.setAttribute("trailCategories", trailCategories);

        req.setAttribute("pageTitle", trail.getTrail_name() + "  Trail");

        req.getRequestDispatcher("WEB-INF/view-trail.jsp").forward(req, resp);
    }
}

