package controller;

import database.DBUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mod.ModDAO;
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

                    // Get SlotId and TutorId from Complaint
                    int slotId = 0;
                    int tutorId = 0;
                    String getSlotAndTutorQuery = "SELECT SlotId, TutorId FROM Complaint WHERE Id = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(getSlotAndTutorQuery)) {
                        stmt.setInt(1, complaintId);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                slotId = rs.getInt("SlotId");
                                tutorId = rs.getInt("TutorId");
                            }
                        }
                    }

                    LOGGER.info("Retrieved slotId: " + slotId + ", tutorId: " + tutorId);

                    // Update Schedule status
                    modDAO.updateScheduleStatus(slotId, "thất bại");
                    LOGGER.info("Updated schedule status to 'thất bại' for slotId: " + slotId);

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
                    request.setAttribute("successMessage", "Report confirmed successfully.");
                } else if ("deny".equals(action)) {
                    LOGGER.info("Denying report with complaintId: " + complaintId);
                    // Handle deny action (optional, if you want to implement)
                    modDAO.updateComplaintStatus(complaintId, "thất bại");
                    connection.commit();
                    LOGGER.info("Transaction committed successfully for denying report");
                    request.setAttribute("successMessage", "Report denied successfully.");
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
