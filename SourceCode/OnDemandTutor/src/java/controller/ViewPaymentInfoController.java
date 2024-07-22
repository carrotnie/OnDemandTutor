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
import javax.servlet.http.HttpSession;

@WebServlet(name = "ViewPaymentInfoController", urlPatterns = {"/ViewPaymentInfoController"})
public class ViewPaymentInfoController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewPaymentInfoController.class.getName());

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

        String updateSuccess = (String) session.getAttribute("updateSuccess");
        session.removeAttribute("updateSuccess");

        LOGGER.info("Processing request for accountId: " + accountId);

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

            PaymentInfoDTO paymentInfo = paymentDAO.getPaymentDetailsByTutorId(tutorId);

            if (paymentInfo != null) {
                LOGGER.info("Retrieved payment info: " + paymentInfo);

                request.setAttribute("tutorName", paymentInfo.getTutorName());
                request.setAttribute("bankAccountNumber", paymentInfo.getBankAccountNumber());
                request.setAttribute("bank", paymentInfo.getBank());
                if (updateSuccess != null) {
                    request.setAttribute("updateSuccess", updateSuccess);
                }
                request.getRequestDispatcher("payment_information.jsp").forward(request, response);
            } else {
                LOGGER.warning("Payment info not found for tutorId: " + tutorId);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Payment info not found");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving payment info", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving payment info");
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
        return "ViewPaymentInfoController";
    }
}
