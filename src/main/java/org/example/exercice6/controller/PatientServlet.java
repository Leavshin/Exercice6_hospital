package org.example.exercice6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.example.exercice6.service.PatientService;
import org.example.exercice6.model.Patient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@WebServlet(name = "patientServlet", value = "/patient/add")
@MultipartConfig
public class PatientServlet extends HttpServlet {

    private PatientService patientService;

    @Override
    public void init() throws ServletException {
        patientService = new PatientService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/patient/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthDateString = req.getParameter("birthDate");
        Part photoPart = req.getPart("photo");

        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("birthDateString: " + birthDateString);

        if (firstName == null || lastName == null || birthDateString == null || firstName.isEmpty() || lastName.isEmpty() || birthDateString.isEmpty()) {
            // Gérer le cas où l'un des champs requis est vide ou nul
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tous les champs sont obligatoires.");
            return;
        }

        LocalDate birthDate = LocalDate.parse(birthDateString);

        String photoPath = null;
        if (photoPart != null && photoPart.getSize() > 0) {
            photoPath = savePhoto(photoPart);
        }

        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setBirthDate(birthDate);
        patient.setPhotoPath(photoPath);

        patientService.save(patient);

        resp.sendRedirect(req.getContextPath() + "/patients");
    }

    private String savePhoto(Part photoPart) throws IOException {
        String fileName = Paths.get(photoPart.getSubmittedFileName()).getFileName().toString();
        Path uploadPath = Paths.get(getServletContext().getRealPath("/uploads"));
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream input = photoPart.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        }
    }
}
