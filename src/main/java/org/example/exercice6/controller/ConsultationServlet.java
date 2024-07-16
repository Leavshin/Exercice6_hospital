package org.example.exercice6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "consultationServlet", value = "/consultation/*")
public class ConsultationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo().substring(1);
        switch (action) {
            case "add":
                showConsultationForm(req, resp);
                break;
            case "detail":
                showConsultationDetail(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void showConsultationForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/consultation/form.jsp").forward(req, resp);
    }

    private void showConsultationDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int consultationId = Integer.parseInt(req.getParameter("id"));
        req.getRequestDispatcher("/WEB-INF/consultation/detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo().substring(1);
        switch (action) {
            case "add":
                addConsultation(req, resp);
                break;
            case "addPrescription":
                addPrescription(req, resp);
                break;
            case "addFicheDeSoin":
                addFicheDeSoin(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void addConsultation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int patientId = Integer.parseInt(req.getParameter("patientId"));
        String doctorName = req.getParameter("doctorName");
        LocalDate consultationDate = LocalDate.parse(req.getParameter("consultationDate"));
        resp.sendRedirect(req.getContextPath() + "/patient/detail?id=" + patientId);
    }

    private void addPrescription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int consultationId = Integer.parseInt(req.getParameter("consultationId"));
        String prescriptionContent = req.getParameter("prescriptionContent");
        resp.sendRedirect(req.getContextPath() + "/consultation/detail?id=" + consultationId);
    }

    private void addFicheDeSoin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int consultationId = Integer.parseInt(req.getParameter("consultationId"));
        String ficheDeSoinContent = req.getParameter("ficheDeSoinContent");
        resp.sendRedirect(req.getContextPath() + "/consultation/detail?id=" + consultationId);
    }
}
