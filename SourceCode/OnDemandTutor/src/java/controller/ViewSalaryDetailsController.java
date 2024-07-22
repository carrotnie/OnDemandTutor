package controller;

import database.DBUtils;
import payment.PaymentDAO;
import payment.PaymentInfoDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ViewSalaryDetailsController", urlPatterns = {"/ViewSalaryDetailsController"})
public class ViewSalaryDetailsController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewSalaryDetailsController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String tutorIdParam = request.getParameter("tutorId");
        if (tutorIdParam == null || tutorIdParam.isEmpty() || tutorIdParam.equals("0")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or empty tutorId parameter");
            return;
        }

        int tutorId;
        try {
            tutorId = Integer.parseInt(tutorIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tutorId parameter");
            return;
        }

        try (Connection connection = DBUtils.getConnection()) {
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            PaymentInfoDTO paymentInfo = paymentDAO.getPaymentDetailsByTutorId(tutorId);

            if (paymentInfo != null) {
                request.setAttribute("tutorName", paymentInfo.getTutorName());
                request.setAttribute("bankAccountNumber", paymentInfo.getBankAccountNumber());
                request.setAttribute("bank", paymentInfo.getBank());
                request.getRequestDispatcher("salary_information.jsp").forward(request, response);
            } else {
                LOGGER.warning("Payment info not found for tutorId: " + tutorId);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Payment info not found");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving payment info", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving payment info");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ViewSalaryDetailsController handles fetching and displaying salary details";
    }
}
