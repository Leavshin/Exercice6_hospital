package org.example.exercice6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Validation des identifiants
        if ("admin".equals(username) && "root".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            resp.sendRedirect(req.getContextPath() + "/patient/add");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
