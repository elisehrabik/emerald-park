package edu.kirkwood.emeraldpark.controller;

import com.google.gson.Gson;
import edu.kirkwood.emeraldpark.model.*;
import edu.kirkwood.toystore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/map")
public class Map extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Trail> trails = TrailDAO.getTrails(1000, 0, "", null);
        req.setAttribute("pageTitle", "Map");
        req.setAttribute("trails", trails);
        req.getRequestDispatcher("WEB-INF/map.jsp").forward(req, resp);
    }
}

