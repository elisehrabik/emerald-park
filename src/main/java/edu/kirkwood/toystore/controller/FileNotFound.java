package edu.kirkwood.toystore.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static jakarta.servlet.RequestDispatcher.*;
import java.io.IOException;

@WebServlet("/fileNotFound")
public class FileNotFound extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("pageTitle", "File Not Found");
        req.getRequestDispatcher("WEB-INF/fileNotFound.jsp").forward(req, resp);
    }
}