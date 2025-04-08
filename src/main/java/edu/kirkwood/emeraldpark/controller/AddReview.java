package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

import edu.kirkwood.emeraldpark.model.Review;
import edu.kirkwood.emeraldpark.model.ReviewDAO;

@WebServlet("/add-review")
public class AddReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null) {
            session.setAttribute("flashMessageWarning", "Please log in to leave a review.");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String trailId = req.getParameter("id");

        if (trailId == null || trailId.isEmpty()) {
            session.setAttribute("flashMessageDanger", "Missing trail ID.");
            resp.sendRedirect(req.getContextPath() + "/view-trails");
            return;
        }

        req.setAttribute("trailId", trailId);
        req.setAttribute("pageTitle", "Add Review");
        req.getRequestDispatcher("WEB-INF/add-review.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null) {
            session.setAttribute("flashMessageWarning", "You must be logged in to submit a review.");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String ratingStr = req.getParameter("rating");
        String reviewNotes = req.getParameter("review_notes");
        String trailIdStr = req.getParameter("trail_id");
        req.setAttribute("reviewNotes", reviewNotes);
        req.setAttribute("rating", ratingStr);
        req.setAttribute("trailId", trailIdStr);

        try {
            int trailId = Integer.parseInt(trailIdStr);

            if (ratingStr == null || ratingStr.isEmpty()) {
                req.setAttribute("ratingError", true);
                req.setAttribute("ratingMessage", "Rating is required.");
                req.setAttribute("pageTitle", "Add Review");
                req.getRequestDispatcher("WEB-INF/add-review.jsp").forward(req, resp);
                return;
            }

            int rating = Integer.parseInt(ratingStr);
            if (rating < 1 || rating > 5) {
                req.setAttribute("ratingError", true);
                req.setAttribute("ratingMessage", "Rating must be between 1 and 5.");
                req.setAttribute("pageTitle", "Add Review");
                req.getRequestDispatcher("WEB-INF/add-review.jsp").forward(req, resp);
                return;
            }

            Review newReview = new Review();
            newReview.setTrail_id(trailId);
            newReview.setUser_id(user.getUserId());
            newReview.setRating(rating);
            newReview.setReview_notes(reviewNotes);
            newReview.setReview_date(LocalDate.now());

            boolean success = ReviewDAO.addReview(newReview);

            session.setAttribute("flashMessageSuccess", success
                    ? "Review submitted!"
                    : "Something went wrong. Try again.");

            resp.sendRedirect(req.getContextPath() + "/view-trail?id=" + trailId);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("ratingError", true);
            req.setAttribute("ratingMessage", "Something went wrong.");
            req.setAttribute("pageTitle", "Add Review");
            req.getRequestDispatcher("WEB-INF/add-review.jsp").forward(req, resp);
        }
    }
}
