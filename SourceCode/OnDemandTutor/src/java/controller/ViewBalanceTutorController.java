package controller;

import database.DBUtils;
import payment.PaymentDAO;
import payment.SalaryDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ViewBalanceTutorController", urlPatterns = {"/ViewBalanceTutorController"})
public class ViewBalanceTutorController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewBalanceTutorController.class.getName());

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

        LOGGER.info("Processing request for accountId: " + accountId);

        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            Integer tutorId = paymentDAO.getTutorIdByAccountId(accountId);

            if (tutorId == null) {
                LOGGER.severe("No tutorId found for accountId: " + accountId);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No tutorId found for accountId");
                return;
            }

            List<SalaryDTO> salaries = paymentDAO.getBalancesByTutorId(tutorId);

            LOGGER.info("Retrieved balances for tutorId: " + tutorId);

            request.setAttribute("salaries", salaries);
            request.getRequestDispatcher("balance_tutor.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving tutor balances", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving tutor balances");
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
        return "ViewBalanceTutorController";
    }
}
