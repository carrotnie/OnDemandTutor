package controller;

import database.DBUtils;
import payment.PaymentDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CompleteCourseController")
public class CompleteCourseController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String salaryId = request.getParameter("salaryId");

        if (salaryId == null || salaryId.isEmpty()) {
            response.sendRedirect("adminPayments.jsp");
            return;
        }

        try (Connection connection = DBUtils.getConnection()) {
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            boolean success = paymentDAO.updateCourseStatus(Integer.parseInt(salaryId));

            if (success) {
                response.getWriter().write("Success");
            } else {
                response.getWriter().write("Failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Exception: " + e.getMessage());
        }

        response.sendRedirect("SalaryController");
    }

    @Override
    public String getServletInfo() {
        return "CompleteCourseController handles marking course as completed";
    }
}
