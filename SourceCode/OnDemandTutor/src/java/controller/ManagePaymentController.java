package controller;

import database.DBUtils;
import payment.PaymentDAO;
import user.UserDTO;

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
import java.util.logging.Logger;
import payment.PaymentDTO;

@WebServlet("/ManagePaymentController")
public class ManagePaymentController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ManagePaymentController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

        if (loginUser == null || !"admin".equals(loginUser.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        String walletIdStr = request.getParameter("walletId");
        String statusFilter = request.getParameter("status");
        int accountId = loginUser.getId();

        try (Connection connection = DBUtils.getConnection()) {
            PaymentDAO paymentDAO = new PaymentDAO(connection);

            if (walletIdStr != null && action != null) {
                int walletId = Integer.parseInt(walletIdStr);
                boolean success = false;

                if ("approve".equals(action)) {
                    success = paymentDAO.updateWalletStatus(walletId);
                } else if ("reject".equals(action)) {
                    success = paymentDAO.updateWalletStatusToFailed(walletId);
                }

                if (success) {
                    response.getWriter().write("Success");
                } else {
                    response.getWriter().write("Failed");
                }
                return; // Exit after processing the action
            }

            Integer adminId = paymentDAO.getAdminIdByAccountId(accountId);

            if (adminId != null) {
                List<PaymentDTO> payments;
                if (statusFilter == null || statusFilter.isEmpty()) {
                    payments = paymentDAO.getPaymentsByAdminId(adminId);
                } else {
                    payments = paymentDAO.getPaymentsByAdminIdAndStatus(adminId, statusFilter);
                }
                request.setAttribute("payments", payments);
            } else {
                request.setAttribute("error", "Admin ID not found for the given account ID.");
            }

            request.getRequestDispatcher("admin_payment_student.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            request.setAttribute("errorMessage", "An error occurred while processing payments: " + e.getMessage());
            request.getRequestDispatcher("admin_payment_student.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "ManagePaymentController handles payment approvals and rejections";
    }
}
