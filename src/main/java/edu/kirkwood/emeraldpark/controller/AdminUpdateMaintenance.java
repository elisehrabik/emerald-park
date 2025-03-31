package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.*;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

    @WebServlet("/edit-maintenance")
    public class AdminUpdateMaintenance extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            User userFromSession = (User) session.getAttribute("activeUser");
            if (userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            String maintenanceIdParam = req.getParameter("maintenance_id");
            if (maintenanceIdParam == null || maintenanceIdParam.trim().isEmpty()) {
                resp.sendRedirect("admin-maintenance");
                return;
            }

            try {
                int maintenanceId = Integer.parseInt(maintenanceIdParam);
                Maintenance maintenance = MaintenanceDAO.getMaintenanceById(maintenanceId);
                if (maintenance == null) {
                    resp.sendRedirect("admin-maintenance");
                    return;
                }

                req.setAttribute("maintenance", maintenance);
            } catch (NumberFormatException e) {
                resp.sendRedirect("admin-maintenance");
            }

            req.getRequestDispatcher("WEB-INF/edit-maintenance.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            User userFromSession = (User) session.getAttribute("activeUser");
            if (userFromSession == null) {
                resp.sendRedirect("login");
                return;
            }

            String maintenanceIdParam = req.getParameter("maintenance_id");
            String completionDateParam = req.getParameter("completion_date");
            String maintenanceCompleteParam = req.getParameter("maintenance_complete");
            String maintenanceNotes = req.getParameter("maintenance_notes");

            boolean validationError = false;
            int maintenanceId = -1;

            try {
                maintenanceId = Integer.parseInt(maintenanceIdParam);
            } catch (NumberFormatException e) {
                req.setAttribute("maintenanceUpdated", false);
                req.setAttribute("maintenanceUpdatedMessage", "Invalid maintenance ID format.");
                req.getRequestDispatcher("WEB-INF/edit-maintenance.jsp").forward(req, resp);
                return;
            }

            Maintenance originalMaintenance = MaintenanceDAO.getMaintenanceById(maintenanceId);
            if (originalMaintenance == null) {
                req.setAttribute("maintenanceUpdated", false);
                req.setAttribute("maintenanceUpdatedMessage", "Maintenance record not found!");
                req.getRequestDispatcher("WEB-INF/edit-maintenance.jsp").forward(req, resp);
                return;
            }

            Maintenance updatedMaintenance = new Maintenance();
            updatedMaintenance.setMaintenance_id(maintenanceId);

            // Completion Date Validation
            if (completionDateParam == null || completionDateParam.trim().isEmpty()) {
                validationError = true;
                req.setAttribute("completionDateError", true);
                req.setAttribute("completionDateMessage", "Completion date is required.");
            } else {
                try {
                    LocalDate completionDate = LocalDate.parse(completionDateParam);
                    if (completionDate.isAfter(LocalDate.now())) {
                        validationError = true;
                        req.setAttribute("completionDateError", true);
                        req.setAttribute("completionDateMessage", "Completion date cannot be in the future.");
                    } else {
                        updatedMaintenance.setCompletion_date(completionDate);
                    }
                } catch (Exception e) {
                    validationError = true;
                    req.setAttribute("completionDateError", true);
                    req.setAttribute("completionDateMessage", "Invalid completion date format.");
                }
            }

            // Maintenance Complete Validation
            if (maintenanceCompleteParam == null || !maintenanceCompleteParam.equals("1")) {
                validationError = true;
                req.setAttribute("maintenanceCompleteError", true);
                req.setAttribute("maintenanceCompleteMessage", "Maintenance completion status must be marked as complete to submit.");
            } else {
                updatedMaintenance.setMaintenance_complete("1".equals(maintenanceCompleteParam));
            }

            // Maintenance Notes Validation
            if (maintenanceNotes == null || maintenanceNotes.trim().isEmpty()) {
                validationError = true;
                req.setAttribute("maintenanceNotesError", true);
                req.setAttribute("maintenanceNotesMessage", "Maintenance notes are required.");
            } else {
                updatedMaintenance.setMaintenance_notes(maintenanceNotes);
            }

            if (validationError) {
                req.setAttribute("maintenanceUpdated", false);
                req.setAttribute("maintenanceUpdatedMessage", "Please correct the errors below.");
                req.setAttribute("maintenance", updatedMaintenance);

                req.setAttribute("completion_date", completionDateParam);
                req.setAttribute("maintenance_complete", maintenanceCompleteParam);
                req.setAttribute("maintenance_notes", maintenanceNotes);

                req.getRequestDispatcher("WEB-INF/edit-maintenance.jsp").forward(req, resp);
                return;
            }

            boolean maintenanceUpdated = MaintenanceDAO.updateMaintenance(originalMaintenance, updatedMaintenance);
            req.setAttribute("maintenanceUpdated", maintenanceUpdated);
            req.setAttribute("maintenanceUpdatedMessage", maintenanceUpdated ? "Successfully updated maintenance request!" : "Error updating maintenance request.");

            req.setAttribute("maintenance", updatedMaintenance);
            req.getRequestDispatcher("WEB-INF/edit-maintenance.jsp").forward(req, resp);
        }
    }