package edu.kirkwood.emeraldpark.controller;

import com.google.gson.Gson;
import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/trail-popup")
public class TrailPopup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        int trailId;

        try {
            trailId = Integer.parseInt(idStr);
        } catch (NumberFormatException | NullPointerException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid Trail ID.\"}");
            return;
        }

        Trail trail = TrailDAO.getTrail(String.valueOf(trailId));
        if (trail == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"error\": \"Trail not found.\"}");
            return;
        }

        // Convert to DTO to avoid LocalTime Gson issue
        TrailPopupDTO dto = new TrailPopupDTO(trail);

        String json = new Gson().toJson(dto);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
