package controller;

import database.DBUtils;
import schedule.ScheduleDAO;
import schedule.ScheduleDTO;

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

@WebServlet("/ScheduleController")
public class ScheduleController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ScheduleController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login page if accountId is not in session
            return;
        }

        try (Connection connection = DBUtils.getConnection()) {
            ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
            Integer studentId = scheduleDAO.getStudentIdByAccountId(accountId);

            if (studentId != null) {
                List<ScheduleDTO> schedules = scheduleDAO.getSchedulesByStudentId(studentId);
                request.setAttribute("schedules", schedules);
            } else {
                LOGGER.log(Level.WARNING, "No studentId found for accountId: {0}", accountId);
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "Error at ScheduleController: {0}", e.toString());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.getRequestDispatcher("/status_student.jsp").forward(request, response);
    }
}
