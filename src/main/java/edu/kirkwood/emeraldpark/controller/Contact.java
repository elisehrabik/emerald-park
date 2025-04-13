package edu.kirkwood.emeraldpark.controller;


import edu.kirkwood.emeraldpark.controller.EmailThread;
import edu.kirkwood.shared.Validators;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(value="/contact")
public class Contact extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Contact");
        req.getRequestDispatcher("/WEB-INF/contact.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("🟢 Reached doPost");

        String toEmailAddress = req.getParameter("toEmailAddress");
        String subject = req.getParameter("subject");
        String bodyHTML = req.getParameter("bodyHTML");
        String replyTo = toEmailAddress;

        req.setAttribute("toEmailAddress", toEmailAddress);
        req.setAttribute("subject", subject);
        req.setAttribute("bodyHTML", bodyHTML);
        req.setAttribute("replyTo", replyTo);
        req.setAttribute("pageTitle", "Contact");


        boolean error = false;
        String emailError = "";
        String subjectError = "";
        String messageBodyError = "";


        if(toEmailAddress == null || !Validators.isValidEmail(toEmailAddress)) {
            // set error attribute
            emailError = "Invalid email address: " + toEmailAddress;
            error = true;
        }

        if(subject == null || subject.isEmpty()) {
            // set error attribute
            subjectError = "Subject is required";
            error = true;
        }

        if(bodyHTML == null || bodyHTML.isEmpty()) {
            // set error attribute
            messageBodyError = "Body is required";
            error = true;
        }

        if (error) {

            req.setAttribute("emailError", emailError);
            req.setAttribute("subjectError", subjectError);
            req.setAttribute("messageBodyError", messageBodyError);
            req.getRequestDispatcher("/WEB-INF/contact.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("toEmailAddress", null);
        req.setAttribute("subject", null);
        req.setAttribute("bodyHTML", null);

        EmailThread emailThread = new EmailThread("elise.hrabik@gmail.com", subject, bodyHTML, replyTo);
        emailThread.start();

        try {
            emailThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String errorMessage = emailThread.getErrorMessage();

        if (errorMessage.isEmpty()) {
            req.setAttribute("messageSuccess", "Message sent!");
        } else {
            req.setAttribute("emailError", "Message not sent. " + errorMessage);
        }

        System.out.println("Forwarding to contact.jsp");
        // Forward req/resp to a JSP
        req.getRequestDispatcher("/WEB-INF/contact.jsp").forward(req, resp);
    }
}


