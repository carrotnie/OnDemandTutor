package controller;

import database.DBUtils;
import mod.FeedbackDTO;
import mod.ModDAO;

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

@WebServlet("/ViewFeedbackController")
public class ViewFeedbackController extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String deleteFeedbackId = request.getParameter("deleteFeedbackId");
        if (deleteFeedbackId != null) {
            handleDeleteFeedback(request, response, Integer.parseInt(deleteFeedbackId));
            return;
        }

        List<FeedbackDTO> feedbackList;

        try (Connection connection = DBUtils.getConnection()) {
            ModDAO modDAO = new ModDAO(connection);
            Integer modId = modDAO.getModIdByAccountId(accountId); // Lấy ModId từ accountId

            if (modId != null) {
                feedbackList = modDAO.getFeedbackByModId(modId);

                if (feedbackList.isEmpty()) {
                    request.setAttribute("errorMessage", "No feedback found for the moderator.");
                }

                request.setAttribute("FEEDBACK_LIST", feedbackList);
            } else {
                request.setAttribute("errorMessage", "Moderator ID not found for this account.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            request.setAttribute("errorMessage", "An error occurred while retrieving feedback: " + e.getMessage());
        }

        request.getRequestDispatcher("/view_feedback.jsp").forward(request, response);
    }

    private void handleDeleteFeedback(HttpServletRequest request, HttpServletResponse response, int feedbackId)
            throws ServletException, IOException {
        try (Connection connection = DBUtils.getConnection()) {
            ModDAO modDAO = new ModDAO(connection);
            boolean success = modDAO.deleteFeedback(feedbackId);
            if (success) {
                request.setAttribute("successMessage", "Feedback deleted successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to delete feedback.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("errorMessage", "An error occurred while deleting feedback: " + e.getMessage());
        }
        response.sendRedirect("ViewFeedbackController"); // Redirect to refresh the feedback list
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
