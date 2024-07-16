package org.example.exercice6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exercice6.model.Patient;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "listServlet", value = "/patients")
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");

        List<Patient> patients = search != null ? searchPatients(search) : getAllPatients();

        req.setAttribute("patients", patients);
        req.getRequestDispatcher("/WEB-INF/patient/list.jsp").forward(req, resp);
    }

    private List<Patient> getAllPatients() {
        return null;
    }

    private List<Patient> searchPatients(String search) {
        return null;
    }
}
