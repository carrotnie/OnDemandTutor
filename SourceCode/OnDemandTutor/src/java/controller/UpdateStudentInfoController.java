package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import student.StudentDAO;

public class UpdateStudentInfoController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set character encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        try {
            // Retrieve form parameters
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String studentClass = request.getParameter("class");

            // Debug statements to check for null values
            System.out.println("Address: " + address);
            System.out.println("Phone: " + phone);
            System.out.println("Class: " + studentClass);

            // Retrieve accountId from session
            Integer accountId = (Integer) request.getSession().getAttribute("accountId");
            if (accountId == null) {
                throw new ServletException("User is not logged in or session has expired.");
            }

            // Update the student information in the database
            StudentDAO studentDAO = new StudentDAO();
            Integer classNumber = null;
            if (studentClass != null && !studentClass.isEmpty()) {
                try {
                    classNumber = Integer.parseInt(studentClass);
                } catch (NumberFormatException e) {
                    throw new ServletException("Invalid class number format.");
                }
            }
            studentDAO.updateStudentInfo(accountId, address, phone, classNumber);

            // Redirect to success page
            response.sendRedirect("success_update_stu_info.html");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error updating student information", e);
        }
    }
}
