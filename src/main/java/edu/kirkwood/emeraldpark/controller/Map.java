package edu.kirkwood.emeraldpark.controller;

import com.google.gson.Gson;
import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value= "/map")
public class Map extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("trail-info".equals(action)) {
            String idParam = req.getParameter("id");

            if (idParam == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("application/json");
                resp.getWriter().write("{\"error\": \"Missing trail ID\"}");
                return;
            }

            try {
                int trail_id = Integer.parseInt(idParam);
                System.out.println("Requested trail ID: " + trail_id); // optional debug
                Trail trail = TrailDAO.getTrail(String.valueOf(trail_id));

                if (trail != null) {
                    String json = new Gson().toJson(trail);
                    resp.setContentType("application/json");
                    resp.getWriter().write(json);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.setContentType("application/json");
                    resp.getWriter().write("{\"error\": \"Trail not found\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("application/json");
                resp.getWriter().write("{\"error\": \"Invalid trail ID\"}");
            }

            return;
        }

        // Default: load map view
        req.setAttribute("pageTitle", "Map of Emerald Park");
        req.getRequestDispatcher("WEB-INF/map.jsp").forward(req, resp);
    }
}
