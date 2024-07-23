package controller;

import database.DBUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mod.ModDAO;
import mod.ReportDTO;
import java.util.logging.Logger;

public class ConfirmReportController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConfirmReportController.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int complaintId = Integer.parseInt(request.getParameter("complaintId"));
        String action = request.getParameter("action");

        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);  // Start transaction
            ModDAO modDAO = new ModDAO(connection);

            try {
                if ("confirm".equals(action)) {
                    LOGGER.info("Confirming report with complaintId: " + complaintId);

                    // Update Complaint status
                    modDAO.updateComplaintStatus(complaintId, "thành công");
                    LOGGER.info("Updated complaint status to 'thành công' for complaintId: " + complaintId);

                    // Get details for Complaint
                    ReportDTO reportDetails = modDAO.getReportDetailsByComplaintId(complaintId);
                    if (reportDetails != null) {
                        int slotId = reportDetails.getSlotId();
                        int tutorId = reportDetails.getTutorId();
                        int studentId = reportDetails.getStudentId();
                        BigDecimal price = reportDetails.getPrice();

                        LOGGER.info("Retrieved slotId: " + slotId + ", tutorId: " + tutorId + ", studentId: " + studentId + ", price: " + price);

                        // Update Schedule status
                        modDAO.updateScheduleStatus(slotId, "thành công");
                        LOGGER.info("Updated schedule status to 'thành công' for slotId: " + slotId);

                        // Insert money into Wallet
                        modDAO.insertMoneyIntoWallet(studentId, price.doubleValue());
                        LOGGER.info("Inserted money into wallet for studentId: " + studentId);

                        // Delete Payment
                        modDAO.deletePayment(slotId, tutorId);
                        LOGGER.info("Deleted payment for slotId: " + slotId + " and tutorId: " + tutorId);

                        // Update BanAccount
                        try {
                            modDAO.incrementTutorReportCount(tutorId);
                            LOGGER.info("Incremented tutor report count for tutorId: " + tutorId);
                        } catch (SQLException e) {
                            // Handle constraint violation for AmountOfReport
                            LOGGER.warning("Failed to increment tutor report count due to constraint violation: " + e.getMessage());
                        }

                        connection.commit();  // Commit transaction
                        LOGGER.info("Transaction committed successfully");
                        request.setAttribute("successMessage", "Report được duyệt thành công");
                    } else {
                        connection.rollback();
                        LOGGER.severe("Failed to retrieve complaint details.");
                        request.setAttribute("errorMessage", "Failed to retrieve complaint details.");
                    }
                } else if ("deny".equals(action)) {
                    LOGGER.info("Denying report with complaintId: " + complaintId);
                    // Handle deny action
                    modDAO.updateComplaintStatus(complaintId, "thất bại");
                    connection.commit();
                    LOGGER.info("Transaction committed successfully for denying report");
                    request.setAttribute("successMessage", "Report được từ chối thành công");
                }
            } catch (SQLException e) {
                connection.rollback();  // Rollback transaction on error
                LOGGER.severe("SQL Exception: " + e.getMessage());
                request.setAttribute("errorMessage", "An error occurred while processing report: " + e.getMessage());
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            request.setAttribute("errorMessage", "An error occurred while processing report: " + e.getMessage());
        }

        request.getRequestDispatcher("ViewReportController").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}