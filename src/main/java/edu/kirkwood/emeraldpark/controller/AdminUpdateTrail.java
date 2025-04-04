package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import edu.kirkwood.emeraldpark.model.TrailDifficulty;
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

@WebServlet(value = "/update-trail")
public class AdminUpdateTrail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null ||
                !"active".equals(userFromSession.getStatus()) ||
                !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String trailId = req.getParameter("trail_id");
        if (trailId == null || trailId.trim().isEmpty() || !trailId.matches("\\d+")) {
            req.setAttribute("trailUpdatedMessage", "Invalid trail ID: must be an integer.");
            req.getRequestDispatcher("WEB-INF/update-trail.jsp").forward(req, resp);
            return;
        }

        Trail trail = TrailDAO.getTrail(trailId);
        if (trail == null) {
            req.setAttribute("trailUpdatedMessage", "Trail not found!");
            req.getRequestDispatcher("WEB-INF/update-trail.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("id", trailId);
        req.setAttribute("trail", trail);
        req.setAttribute("trailName", trail.getTrail_name());
        req.setAttribute("trailDistance", trail.getTrail_distance());
        req.setAttribute("trailDifficulty", trail.getTrail_difficulty().toString());
        req.setAttribute("trailTime", trail.getTrail_time().toString());
        req.setAttribute("trailDescription", trail.getTrail_description());
        req.setAttribute("trailImage", trail.getTrail_image());
        req.setAttribute("category_id", trail.getCategoryId());
        req.setAttribute("pageTitle", "Edit Trail");
        req.getRequestDispatcher("WEB-INF/update-trail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User) session.getAttribute("activeUser");
        if (userFromSession == null ||
                !"active".equals(userFromSession.getStatus()) ||
                !"admin".equals(userFromSession.getPrivileges())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String trailId = req.getParameter("trail_id");
        if (trailId == null || trailId.trim().isEmpty() || !trailId.matches("\\d+")) {
            req.setAttribute("trailUpdated", false);
            req.setAttribute("trailUpdatedMessage", "Invalid trail ID format: must be an integer.");
            req.getRequestDispatcher("WEB-INF/update-trail.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("id", trailId);
        Trail originalTrail = TrailDAO.getTrail(trailId);
        if (originalTrail == null) {
            req.setAttribute("trailUpdated", false);
            req.setAttribute("trailUpdatedMessage", "Trail not found!");
            req.getRequestDispatcher("WEB-INF/update-trail.jsp").forward(req, resp);
            return;
        }

        Trail updatedTrail = new Trail();
        updatedTrail.setTrail_id(Integer.parseInt(trailId));
        boolean validationError = false;

        String trailName = req.getParameter("trailName");
        if (trailName == null || trailName.trim().isEmpty()) {
            req.setAttribute("trailNameError", true);
            req.setAttribute("trailNameMessage", "Trail name cannot be empty.");
            validationError = true;
        } else {
            updatedTrail.setTrail_name(trailName);
            req.setAttribute("trailName", trailName);
        }

        String trailDistanceStr = req.getParameter("trailDistance");
        if (trailDistanceStr == null || trailDistanceStr.trim().isEmpty()) {
            req.setAttribute("trailDistanceError", true);
            req.setAttribute("trailDistanceMessage", "Trail distance cannot be empty.");
            validationError = true;
        } else {
            try {
                double trailDistance = Double.parseDouble(trailDistanceStr);
                if (trailDistance < 0) throw new NumberFormatException();
                updatedTrail.setTrail_distance(trailDistance);
                req.setAttribute("trailDistance", trailDistanceStr);
            } catch (NumberFormatException e) {
                req.setAttribute("trailDistanceError", true);
                req.setAttribute("trailDistanceMessage", "Invalid trail distance: must be a positive number.");
                validationError = true;
            }
        }

        String trailDifficulty = req.getParameter("trailDifficulty");
        if (trailDifficulty == null || trailDifficulty.trim().isEmpty()) {
            req.setAttribute("trailDifficultyError", true);
            req.setAttribute("trailDifficultyMessage", "Trail difficulty must be selected.");
            validationError = true;
        } else {
            try {
                updatedTrail.setTrail_difficulty(TrailDifficulty.valueOf(trailDifficulty.toUpperCase()));
            } catch (IllegalArgumentException e) {
                req.setAttribute("trailDifficultyError", true);
                req.setAttribute("trailDifficultyMessage", "Invalid difficulty level.");
                validationError = true;
            }
        }

        String trailTimeStr = req.getParameter("trailTime");
        if (trailTimeStr == null || trailTimeStr.trim().isEmpty()) {
            req.setAttribute("trailTimeError", true);
            req.setAttribute("trailTimeMessage", "Trail time is required.");
            validationError = true;
        } else {
            try {
                updatedTrail.setTrail_time(LocalTime.parse(trailTimeStr));
                req.setAttribute("trailTime", trailTimeStr);
            } catch (Exception e) {
                req.setAttribute("trailTimeError", true);
                req.setAttribute("trailTimeMessage", "Invalid time format. Use HH:mm.");
                validationError = true;
            }
        }

        String trailDescription = req.getParameter("trailDescription");
        if (trailDescription == null || trailDescription.trim().isEmpty()) {
            req.setAttribute("trailDescriptionError", true);
            req.setAttribute("trailDescriptionMessage", "Trail description is required.");
            validationError = true;
        } else {
            updatedTrail.setTrail_description(trailDescription);
            req.setAttribute("trailDescription", trailDescription);
        }

        String trailImage = req.getParameter("trailImage");
        if (trailImage == null || trailImage.trim().isEmpty() || !Validators.isValidImage(trailImage)) {
            req.setAttribute("trailImageError", true);
            req.setAttribute("trailImageMessage", "Trail image path must be a valid url.");
            validationError = true;
        } else {
            updatedTrail.setTrail_image(trailImage);
            req.setAttribute("trailImage", trailImage);
        }


        String categoryIdStr = req.getParameter("category_id");
        if (categoryIdStr != null && categoryIdStr.matches("\\d+")) {
            updatedTrail.setCategoryId(Integer.parseInt(categoryIdStr));
            req.setAttribute("category_id", categoryIdStr);
        } else {
            req.setAttribute("categoryIdError", true);
            req.setAttribute("categoryIdMessage", "Invalid category ID.");
            validationError = true;
        }


        if (validationError) {
            req.setAttribute("trailUpdated", false);
            req.setAttribute("trailUpdatedMessage", "Please correct the errors below.");
            req.getRequestDispatcher("WEB-INF/update-trail.jsp").forward(req, resp);
            return;
        }

        boolean success = TrailDAO.updateTrail(originalTrail, updatedTrail);

        req.setAttribute("trailUpdated", success);
        req.setAttribute("pageTitle", "Edit Trail");
        req.setAttribute("trailUpdatedMessage", success ? "Trail updated successfully." : "Failed to update trail.");
        req.getRequestDispatcher("WEB-INF/update-trail.jsp").forward(req, resp);
    }
}
