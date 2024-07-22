package controller;

import database.DBUtils;
import payment.PaymentDAO;

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
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdatePaymentInfoController", urlPatterns = {"/UpdatePaymentInfoController"})
public class UpdatePaymentInfoController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdatePaymentInfoController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            LOGGER.severe("No accountId found in session");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No accountId found in session");
            return;
        }

        String bankAccountNumber = request.getParameter("bankAccountNumber");
        String bank = request.getParameter("bank");

        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            Integer tutorId = paymentDAO.getTutorIdByAccountId(accountId);

            if (tutorId == null) {
                LOGGER.severe("No tutorId found for accountId: " + accountId);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No tutorId found for accountId");
                return;
            }

            boolean isUpdated = paymentDAO.updatePaymentInfo(tutorId, bankAccountNumber, bank);

            if (isUpdated) {
                LOGGER.info("Payment info updated for tutorId: " + tutorId);
                session.setAttribute("updateSuccess", "Thông tin đã được cập nhật thành công.");
                response.sendRedirect("ViewPaymentInfoController");
            } else {
                LOGGER.warning("Failed to update payment info for tutorId: " + tutorId);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update payment info");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error updating payment info", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating payment info");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error closing connection", ex);
                }
            }
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
        return "UpdatePaymentInfoController";
    }
}
