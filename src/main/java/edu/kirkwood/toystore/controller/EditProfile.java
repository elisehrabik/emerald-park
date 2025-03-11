package edu.kirkwood.toystore.controller;

import edu.kirkwood.toystore.model.User;
import edu.kirkwood.toystore.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/edit-profile")
public class EditProfile extends HttpServlet {

    public static void main(String[] args) {
        java.util.Arrays.asList(java.util.TimeZone.getAvailableIDs()).forEach(System.out::println);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");
        if (user == null) {
            session.setAttribute("flashMessageWarning", "You must be logged in to edit your profile.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/login?redirect=edit-profile"));
            return;
        } else if (user != null && !user.getStatus().equals("active")) {
            session.setAttribute("flashMessageDanger", "Your account is locked or inactive.");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
            return;
        }
        req.setAttribute("pageTitle", "Edit Profile");
        req.getRequestDispatcher("WEB-INF/edit-profile.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String language = req.getParameter("language");
        String pronouns = req.getParameter("pronouns");
        String birthday = req.getParameter("birthday");
        String avatar = req.getParameter("avatar");
        req.setAttribute("email", email);
        req.setAttribute("phone", phone);


        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("activeUser");
        boolean errorFound = false;
        if(firstName != null && !firstName.equals(user.getFirstName())) {
            user.setFirstName(firstName);
        }
        if(lastName != null && !lastName.equals(user.getLastName())) {
            user.setLastName(lastName);
        }
        String originalEmail = user.getEmail();
        if(email != null && !email.equals("") && !email.equals(user.getEmail()) && UserDAO.get(email) != null) {
            errorFound = true;
            req.setAttribute("emailError", "A user with that email already exists.");
        } else {
            // The user entered an email address that doesn't exist.
            try {
                user.setEmail(email);
            } catch (IllegalArgumentException e) {
                errorFound = true;
                req.setAttribute("emailError", e.getMessage());
            }
        }


        try {
            if(phone != null && !phone.equals(user.getPhone())) {
                user.setPhone(phone);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("phoneError", e.getMessage());
        }

        try {
            if(!language.equals(user.getLanguage())) {
                user.setLanguage(language);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("languageError", e.getMessage());
        }

        try {
            if(!pronouns.equals(user.getPronouns())) {
                user.setPronouns(pronouns);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("pronounsError", e.getMessage());
        }

        try {
            if(!birthday.equals(user.getBirthday())) {
                user.setBirthday(LocalDate.parse(birthday));
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("birthdayError", e.getMessage());
        }

        try {
            if(!avatar.equals(user.getAvatar())) {
                user.setAvatar(avatar);
            }
        } catch(IllegalArgumentException e) {
            errorFound = true;
            req.setAttribute("avatarError", e.getMessage());
        }

        if(!errorFound) {
            boolean userUpdated = false;
            try {
                userUpdated = UserDAO.update(originalEmail, user);
            } catch(RuntimeException e) {
                session.setAttribute("flashMessageDanger", e.getMessage()); // Change to a message like "Your profile was not updated"
            }
            if(userUpdated) {
                session.setAttribute("activeUser", user);
                session.setAttribute("flashMessageSuccess", "Your profile was updated");
            }
        }

        req.setAttribute("pageTitle", "Edit Profile");
        req.getRequestDispatcher("WEB-INF/edit-profile.jsp").forward(req, resp);
    }
}
