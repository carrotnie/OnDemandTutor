package controller;

import database.DBUtils;
import mod.CvDTO;
import mod.ModDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/ViewCvDetailsController")
public class ViewCvDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cvId = request.getParameter("cvId");
        if (cvId == null) {
            response.sendRedirect("view_cv.jsp");
            return;
        }

        StringBuilder errorMessage = new StringBuilder();

        try (Connection connection = DBUtils.getConnection()) {
            ModDAO modDAO = new ModDAO(connection);
            CvDTO cv = modDAO.getCvById(Integer.parseInt(cvId));

            if (cv != null) {
                request.setAttribute("name", cv.getTutorName());
                request.setAttribute("phonenumber", cv.getPhoneNumber());
                request.setAttribute("location", cv.getLocation());
                request.setAttribute("yob", cv.getYob());
                request.setAttribute("personalId", cv.getPersonalId());
                request.setAttribute("gender", cv.getGender());
                request.setAttribute("experience", cv.getExperience());
                request.setAttribute("grade", cv.getGrade());
                request.setAttribute("url", cv.getUrl());
                String photoPath = "/img/tutor/" + cv.getTutorId() + ".jpg";
                request.setAttribute("photoPath", request.getContextPath() + photoPath);
                String certificatePath = "/img/certificate/" + cv.getTutorId() + ".png";
                request.setAttribute("certificatePath", request.getContextPath() + certificatePath);
                request.setAttribute("cvId", cvId); // Add cvId to the request
            } else {
                errorMessage.append("CV not found.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            errorMessage.append("An error occurred while retrieving the CV: ").append(e.getMessage());
        }

        if (errorMessage.length() > 0) {
            request.setAttribute("errorMessage", errorMessage.toString());
        }

        request.getRequestDispatcher("/view_cv_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cvId = request.getParameter("cvId");

        if (cvId != null) {
            try (Connection connection = DBUtils.getConnection()) {
                ModDAO modDAO = new ModDAO(connection);
                boolean isApproved = modDAO.approveTutor(Integer.parseInt(cvId));
                if (isApproved) {
                    request.setAttribute("message", "CV has been approved successfully.");
                } else {
                    request.setAttribute("errorMessage", "Failed to approve CV.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                request.setAttribute("errorMessage", "An error occurred while approving the CV: " + e.getMessage());
            }
        } else {
            request.setAttribute("errorMessage", "CV ID is missing.");
        }

        doGet(request, response); // Refresh the page with the updated information
    }

    @Override
    public String getServletInfo() {
        return "Servlet that retrieves and displays CV details";
    }
}
