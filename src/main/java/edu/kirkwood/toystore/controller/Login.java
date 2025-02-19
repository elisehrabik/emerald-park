package edu.kirkwood.toystore.controller;

import edu.kirkwood.toystore.model.User;
import edu.kirkwood.toystore.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Login");
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String[] rememberMe = req.getParameterValues("rememberMe");
        req.setAttribute("email", email);
        req.setAttribute("password", password);
        req.setAttribute("rememberMe", (rememberMe != null && rememberMe[0].equals("true")) ? "true" : "");

        User user = null;
        try {
            user = UserDAO.get(email);
        } catch (RuntimeException e) {
            req.setAttribute("loginFail", "An error occurred."); // Use e.getMessage() to see the SQLException
        }

        if (user == null) {
            // No user found that matches the email
            req.setAttribute("loginFail", "No user found with that email address. <a href=\"signup\">Sign-up</a>"); // For security, it might be better to just say "No user found".
        } else {
            boolean passwordMatches = false;
            try {
                passwordMatches = BCrypt.checkpw(password, String.valueOf(user.getPassword()));
            } catch (Exception e) {
                req.setAttribute("loginFail", "An error occurred."); // Use e.getMessage() to see the NoSuchAlgorithmException or InvalidKeySpecException
            }
            // No user found that matches the password
            if (!passwordMatches) {
                req.setAttribute("loginFail",  "The password you entered is incorrect."); // For security, it might be better to just say "No user found".
            } else {
                if(!user.getStatus().equals("active")) {
                    // The user's account is not active
                    req.setAttribute("loginFail",  "Your account is locked or inactive. Please reset your password.");
                    req.setAttribute("pageTitle", "Login");
                    req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
                    return;
                }

                // Successful login
                user.setPassword(null); // Remove the password before setting the User object as a session attribute

                HttpSession session = req.getSession(); // Get existing HttSession object
                session.invalidate(); // Remove any existing session attributes
                session = req.getSession(); // Create new HttpSession
                // session.removeAttribute("activeUser"); // Instead of destroying all attributes, remove only the ones necessary
                if(rememberMe != null && rememberMe[0].equals("true")) {
                    session.setMaxInactiveInterval(30 * 24 * 60 * 60); // represented in seconds
                }
                session.setAttribute("activeUser", user);
                session.setAttribute("flashMessageSuccess", String.format("Welcome back%s!", (user.getFirstName() != null && !user.getFirstName().equals("") ? " " + user.getFirstName() : "")));

                resp.sendRedirect(req.getContextPath()); // Redirects to the home page
                return;
            }
        }

        req.setAttribute("pageTitle", "Login");
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }
}











