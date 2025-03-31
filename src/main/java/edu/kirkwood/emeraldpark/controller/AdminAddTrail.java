package edu.kirkwood.emeraldpark.controller;


import edu.kirkwood.emeraldpark.model.*;
import edu.kirkwood.shared.Validators;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/admin-add-trail")
public class AdminAddTrail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");
        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute("pageTitle", "Add Trail");

        req.getRequestDispatcher("WEB-INF/admin-add-trail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // String trailIdParam = req.getParameter("trailId");
        String trailName = req.getParameter("trailName");
        String trailDistanceParam = req.getParameter("trailDistance");
        String trailDifficulty = req.getParameter("trailDifficulty");
        String trailTime = req.getParameter("trailTime");
        String trailDescription = req.getParameter("trailDescription");
        String trailImage = req.getParameter("trailImage");
        String categoryIdParam = req.getParameter("categoryId");

        boolean validationError = false;
        Trail trail = new Trail();

        if (trailName == null || trailName.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("trailNameError", true);
            req.setAttribute("trailNameMessage", "Trail name cannot be empty.");
        } else {
            trail.setTrail_name(trailName);
            req.setAttribute("trailNameError", false);
            req.setAttribute("trailNameMessage", "Looks good!");
        }
        req.setAttribute("trailName", trailName);


        if (trailDistanceParam == null || trailDistanceParam.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("trailDistanceError", true);
            req.setAttribute("trailDistanceMessage", "Trail distance cannot be empty.");
        } else {
            try {
                double trailDistance = Double.parseDouble(trailDistanceParam);
                if (trailDistance < 0) {
                    validationError = true;
                    req.setAttribute("trailDistanceError", true);
                    req.setAttribute("trailDistanceMessage", "Trail distance cannot be negative.");
                } else {
                    trail.setTrail_distance(trailDistance);
                    req.setAttribute("trailDistanceError", false);
                    req.setAttribute("trailDistanceMessage", "Looks good!");
                }
            } catch (NumberFormatException e) {
                validationError = true;
                req.setAttribute("trailDistanceError", true);
                req.setAttribute("trailDistanceMessage", "Invalid Trail Distance.");
            }
        }
        req.setAttribute("trailDistance", trailDistanceParam);

        try {
            TrailDifficulty difficulty = TrailDifficulty.fromString(trailDifficulty);
            trail.setTrail_difficulty(difficulty);
            req.setAttribute("trailDifficultyError", false);
            req.setAttribute("trailDifficultyMessage", "Looks good!");
        } catch (IllegalArgumentException e) {
            validationError = true;
            req.setAttribute("trailDifficultyError", true);
            req.setAttribute("trailDifficultyMessage", "Invalid Trail Difficulty.");
        }
        req.setAttribute("trailDifficulty", trailDifficulty);

        try {
            LocalTime time = LocalTime.parse(trailTime);
            trail.setTrail_time(time);
            req.setAttribute("trailTimeError", false);
            req.setAttribute("trailTimeMessage", "Looks good!");
        } catch (Exception e) {
            validationError = true;
            req.setAttribute("trailTimeError", true);
            req.setAttribute("trailTimeMessage", "Invalid Trail Time. Please use HH:mm format.");
        }
        req.setAttribute("trailTime", trailTime);

        if (trailDescription == null || trailDescription.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("trailDescriptionError", true);
            req.setAttribute("trailDescriptionMessage", "Trail description cannot be empty.");
        } else {
            trail.setTrail_description(trailDescription);
            req.setAttribute("trailDescriptionError", false);
            req.setAttribute("trailDescriptionMessage", "Looks good!");
        }
        req.setAttribute("trailDescription", trailDescription);

        if (trailImage == null || trailImage.trim().isEmpty() || !Validators.isValidImage(trailImage)) {
            validationError = true;
            req.setAttribute("trailImageError", true);
            req.setAttribute("trailImageMessage", "Trail image path must be a valid url.");
        } else {
            trail.setTrail_image(trailImage);
            req.setAttribute("trailImageError", false);
            req.setAttribute("trailImageMessage", "Looks good!");
        }
        req.setAttribute("trailImage", trailImage);

        if (categoryIdParam == null || categoryIdParam.trim().isEmpty()) {
            validationError = true;
            req.setAttribute("categoryIdError", true);
            req.setAttribute("categoryIdMessage", "Category must be selected.");
        } else {
            try {
                int categoryId = Integer.parseInt(categoryIdParam);
                trail.setCategoryId(categoryId);
                req.setAttribute("categoryIdError", false);
            } catch (NumberFormatException e) {
                validationError = true;
                req.setAttribute("categoryIdError", true);
                req.setAttribute("categoryIdMessage", "Invalid Category ID.");
            }
        }
        req.setAttribute("categoryId", categoryIdParam);

        if (!validationError) {
            boolean trailAdded = TrailDAO.addTrail(trail);
            req.setAttribute("trailAdded", trailAdded);
            if (trailAdded) {
                req.setAttribute("trailAddedMessage", "Successfully added trail!");
            } else {
                req.setAttribute("trailAddedMessage", "Error adding trail.");
            }
        }

        req.setAttribute("pageTitle", "Add Trail");
        req.getRequestDispatcher("WEB-INF/admin-add-trail.jsp").forward(req, resp);

    }
}
