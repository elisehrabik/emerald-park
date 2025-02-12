package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Review;
import edu.kirkwood.emeraldpark.model.ReviewDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/reviews")
public class AdminReviews extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Review> reviews = ReviewDAO.getReviewsAdmin();
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("WEB-INF/emeraldpark/admin-reviews.jsp").forward(req, resp);
    }
}