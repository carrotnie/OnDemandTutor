/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import student.StudentDAO;
import student.StudentDTO;
import tutor.TutorDAO;
import tutor.TutorDTO;
import user.UserDTO;

/**
 *
 * @author Long Dinh
 */
public class ViewTutorInfoController extends HttpServlet {

        private static final Logger LOGGER = Logger.getLogger(ViewTutorInfoController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp"); // Chuyển hướng đến trang đăng nhập nếu không có accountId trong session
            return;
        }

        try {
            TutorDAO tutorDAO = new TutorDAO();
            TutorDTO tutor = tutorDAO.getTutorByAccountId(accountId);
            if (tutor != null) {
                LOGGER.log(Level.INFO, "Tutor: {0}", tutor.toString());
                String photoPath = "/img/tutor/ " + accountId + ".jpg"; // Tạo đường dẫn ảnh dựa trên accountId
                request.setAttribute("photoPath", request.getContextPath() + photoPath);
                request.setAttribute("name", tutor.getName());
                request.setAttribute("phonenumber", tutor.getPhoneNumber());
                request.setAttribute("location", tutor.getLocation());
                request.setAttribute("yob", tutor.getYob());
                request.setAttribute("personalId", tutor.getPersonalId());
                request.setAttribute("gender", tutor.getGender());
                request.setAttribute("experience", tutor.getExperience());
                request.setAttribute("grade", tutor.getGrade());
            } else {
                LOGGER.log(Level.WARNING, "Tutor not found for accountId: {0}", accountId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error at ViewTutorInfoController: " + e.toString());
        }
        request.getRequestDispatcher("/register_info_tutor.jsp").forward(request, response);
    }

}
