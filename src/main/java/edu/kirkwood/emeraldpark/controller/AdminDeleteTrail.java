package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/delete-trail")
public class AdminDeleteTrail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null) {
            System.out.println("DEBUG: User not logged in. Redirecting to 404.");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (!"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            System.out.println("DEBUG: Unauthorized user access. Redirecting to 404.");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String trailId = req.getParameter("trail_id");
        System.out.println("DEBUG: Received trailId = " + trailId);

        req.setAttribute("pageTitle", "Delete Trail");

        if (trailId == null || trailId.trim().isEmpty() || !trailId.matches("\\d+")) {
            System.out.println("DEBUG: Invalid trailId provided.");
            req.setAttribute("trailDeletedMessage", "Invalid trail ID: must be an integer.");
            req.getRequestDispatcher("WEB-INF/delete-trail.jsp").forward(req, resp);
            return;
        }

        Trail trail = TrailDAO.getTrail(trailId);
        if (trail == null) {
            System.out.println("DEBUG: Trail not found for trailId = " + trailId);
            req.setAttribute("trailDeletedMessage", "Trail not found!");
            req.getRequestDispatcher("WEB-INF/delete-trail.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("trail", trail);
        req.setAttribute("id", trailId);
        System.out.println("DEBUG: Trail retrieved successfully: " + trail);

        req.getRequestDispatcher("WEB-INF/delete-trail.jsp").forward(req, resp);
        System.out.println("DEBUG: Forwarding to delete-trail.jsp completed.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null) {
            System.out.println("DEBUG: User not logged in. Redirecting to 404.");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (!"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            System.out.println("DEBUG: Unauthorized user access. Redirecting to 404.");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String trailId = req.getParameter("trail_id");
        System.out.println("DEBUG: Received trailId = " + trailId);

        if (trailId == null || trailId.trim().isEmpty() || !trailId.matches("\\d+")) {
            System.out.println("DEBUG: Invalid trail ID format in doPost.");
            req.setAttribute("trailDeletedMessage", "Invalid trail ID format: must be an integer.");
            req.getRequestDispatcher("WEB-INF/delete-trail.jsp").forward(req, resp);
            return;
        }

        boolean success = TrailDAO.deleteTrail(Integer.parseInt(trailId));

        if (success) {
            System.out.println("DEBUG: Trail deleted successfully. Redirecting to admin-trails.jsp.");
            session.setAttribute("flashMessageSuccess", "Trail deleted successfully.");
            resp.sendRedirect(req.getContextPath() + "/trails");
            return;
        } else {
            System.out.println("DEBUG: Failed to delete trail.");
            req.setAttribute("trailDeletedMessage", "Failed to delete trail.");
            req.setAttribute("pageTitle", "Delete Trail");
            req.getRequestDispatcher("WEB-INF/delete-trail.jsp").forward(req, resp);
        }

    }
}
