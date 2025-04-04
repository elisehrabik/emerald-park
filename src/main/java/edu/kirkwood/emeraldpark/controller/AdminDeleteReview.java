package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Review;
import edu.kirkwood.emeraldpark.model.ReviewDAO;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/delete-review")
public class AdminDeleteReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String review_id = req.getParameter("review_id");

        req.setAttribute("pageTitle", "Delete Review");

        if (review_id == null || review_id.trim().isEmpty() || !review_id.matches("\\d+")) {
            req.setAttribute("reviewDeletedMessage", "Invalid review ID: must be an integer.");
            req.getRequestDispatcher("WEB-INF/delete-review.jsp").forward(req, resp);
            return;
        }

        Review review = ReviewDAO.getReviewById(Integer.parseInt(review_id));
        if (review == null) {
            req.setAttribute("reviewDeletedMessage", "Review not found!");
            req.getRequestDispatcher("WEB-INF/delete-review.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("review", review);
        req.setAttribute("id", review_id);

        req.getRequestDispatcher("WEB-INF/delete-review.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");

        if (userFromSession == null || !"active".equals(userFromSession.getStatus()) || !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String review_id = req.getParameter("review_id");

        if (review_id == null || review_id.trim().isEmpty() || !review_id.matches("\\d+")) {
            req.setAttribute("reviewDeletedMessage", "Invalid review ID format: must be an integer.");
            req.getRequestDispatcher("WEB-INF/delete-review.jsp").forward(req, resp);
            return;
        }

        boolean success = ReviewDAO.deleteReview(Integer.parseInt(review_id));
        if (success) {
            req.setAttribute("reviewDeletedMessage", "Review deleted successfully.");
        } else {
            req.setAttribute("reviewDeletedMessage", "Failed to delete review.");
        }

        req.setAttribute("pageTitle", "Delete Review");
        req.getRequestDispatcher("WEB-INF/delete-review.jsp").forward(req, resp);
    }
}
