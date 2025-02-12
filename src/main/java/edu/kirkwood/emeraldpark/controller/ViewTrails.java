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

@WebServlet(value="/view-trails")
public class ViewTrails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Trail> trails = TrailDAO.getTrails();
        req.setAttribute("trails", trails);
        req.getRequestDispatcher("WEB-INF/emeraldpark/view-trails.jsp").forward(req, resp);
        // forwarding to jsp, inside of web inf because customers should not be able to put in the link, they must access the servlet
    }
}

