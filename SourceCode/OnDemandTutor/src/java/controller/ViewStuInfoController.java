package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import student.StudentDAO;
import student.StudentDTO;

/**
 * Servlet implementation class ViewStuInfoController
 */
@WebServlet("/ViewStuInfoController")
public class ViewStuInfoController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ViewStuInfoController.class.getName());

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
            StudentDAO studentDAO = new StudentDAO();
            StudentDTO student = studentDAO.getStudentByAccountId(accountId);
            if (student != null) {
                LOGGER.log(Level.INFO, "Student: {0}", student.toString());
                String photoPath = "/img/student/s" + student.getId() + ".jpg"; // Tạo đường dẫn ảnh dựa trên accountId
                request.setAttribute("photoPath", request.getContextPath() + photoPath);
                request.setAttribute("name", student.getName());
                request.setAttribute("gender", student.getGender());
                request.setAttribute("birthYear", student.getYob());
                request.setAttribute("address", student.getLocation());
                request.setAttribute("phone", student.getPhoneNumber()); 
                request.setAttribute("class", student.getGrade());
            } else {
                LOGGER.log(Level.WARNING, "Student not found for accountId: {0}", accountId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error at ViewStuInfoController: " + e.toString());
        }
        request.getRequestDispatcher("/student_information.jsp").forward(request, response);
    }
}
