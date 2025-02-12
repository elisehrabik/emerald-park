package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Visitor;
import edu.kirkwood.emeraldpark.model.VisitorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value="/visitors")
public class AdminVisitors extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Visitor> visitors = VisitorDAO.getVisitorsAdmin();
        req.setAttribute("visitors", visitors);
        req.getRequestDispatcher("WEB-INF/emeraldpark/admin-visitors.jsp").forward(req, resp);
    }
}