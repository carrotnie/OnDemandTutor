package controller;

import database.DBUtils;
import payment.PaymentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ViewBalanceController", urlPatterns = {"/ViewBalanceController"})
public class ViewBalanceController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewBalanceController.class.getName());

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
            BigDecimal totalBalance = paymentDAO.getTotalBalanceByAccountId(accountId);

            LOGGER.info("Retrieved total balance: " + totalBalance);

            request.setAttribute("balance", totalBalance);
            request.getRequestDispatcher("wallet.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving student balance", ex);
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
        return "ViewBalanceController";
    }
}
