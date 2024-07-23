package controller;

import database.DBUtils;
import java.io.File;
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

@WebServlet(name = "ViewCvDetailsController", urlPatterns = {"/ViewCvDetailsController", "/approveTutor", "/rejectTutor"})
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
                String certificatePath2 = "/img/certificate2/" + cv.getTutorId() + ".png";
                request.setAttribute("certificatePath2", new File(request.getServletContext().getRealPath(certificatePath2)).exists() ? request.getContextPath() + certificatePath2 : null);
                String certificatePath3 = "/img/certificate3/" + cv.getTutorId() + ".png";
                request.setAttribute("certificatePath3", new File(request.getServletContext().getRealPath(certificatePath3)).exists() ? request.getContextPath() + certificatePath3 : null);
                request.setAttribute("cvId", cvId); // Add cvId to the request
                request.setAttribute("cvActive", cv.getActive());
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
        String action = request.getServletPath();
        String cvId = request.getParameter("cvId");

        if (cvId != null) {
            try (Connection connection = DBUtils.getConnection()) {
                ModDAO modDAO = new ModDAO(connection);
                boolean result = false;
                String message = null;

                switch (action) {
                    case "/approveTutor":
                        result = modDAO.approveTutor(Integer.parseInt(cvId));
                        message = result ? "CV này đã được duyệt thành công" : "Duyệt CV thất bại";
                        break;
                    case "/rejectTutor":
                        String reason = new String(request.getParameter("rejectionReason").getBytes("ISO-8859-1"), ("UTF-8"));
                        if (reason == null || reason.trim().isEmpty()) {
                            message = "Rejection reason is required.";
                        } else {
                            result = modDAO.rejectTutor(Integer.parseInt(cvId), reason);
                            message = result ? "CV này đã được từ chối thành công" : "Từ chối CV thất bại";
                        }
                        break;
                }

                if (result) {
                    request.setAttribute("message", message);
                } else {
                    request.setAttribute("errorMessage", message);
                }

            } catch (ClassNotFoundException | SQLException e) {
                request.setAttribute("errorMessage", "An error occurred while processing the CV: " + e.getMessage());
            }
        } else {
            request.setAttribute("errorMessage", "CV ID is missing.");
        }

        doGet(request, response); // Refresh the page with the updated information
    }

    @Override
    public String getServletInfo() {
        return "Servlet that retrieves and displays CV details, and handles approval/rejection actions";
    }
}
