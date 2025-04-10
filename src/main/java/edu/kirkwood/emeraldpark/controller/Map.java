package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value= "/map")
public class Map extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Map of Emerald Park");
        req.getRequestDispatcher("WEB-INF/map.jsp").forward(req, resp);
    }
}
