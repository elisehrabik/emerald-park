package edu.kirkwood.toystore.controller;

import edu.kirkwood.emeraldpark.model.Trail;
import edu.kirkwood.emeraldpark.model.TrailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Home");
        List<Trail> trails = TrailDAO.getTrails(100, 0, "");
        req.setAttribute("trails", trails);
        req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
    }
}
