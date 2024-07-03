package controller;

import database.DBUtils;
import timetable.TimeTableDAO;
import timetable.TimeTableDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TimeTableController")
public class TimeTableController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng chọn tuần để xem thời khóa biểu.");
            request.setAttribute("timeTables", new ArrayList<TimeTableDTO>());
            request.getRequestDispatcher("/student_timetable.jsp").forward(request, response);
            return;
        }

        Date startDate;
        Date endDate;

        try {
            startDate = Date.valueOf(startDateStr);
            endDate = Date.valueOf(endDateStr);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Định dạng ngày không hợp lệ.");
            request.setAttribute("timeTables", new ArrayList<TimeTableDTO>());
            request.getRequestDispatcher("/student_timetable.jsp").forward(request, response);
            return;
        }

        List<TimeTableDTO> timeTables = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection()) {
            TimeTableDAO timeTableDAO = new TimeTableDAO(connection);
            Integer studentId = timeTableDAO.getStudentIdByAccountId(accountId);

            if (studentId != null) {
                timeTables = timeTableDAO.getTimeTablesByStudentId(studentId);
                timeTables = filterTimeTablesByDateRange(timeTables, startDate, endDate);
                if (timeTables.isEmpty()) {
                    request.setAttribute("errorMessage", "Không tìm thấy thời khóa biểu cho tuần được chọn.");
                }
            } else {
                request.setAttribute("errorMessage", "Không tìm thấy học sinh cho tài khoản này.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("timeTables", timeTables);
        request.getRequestDispatcher("/student_timetable.jsp").forward(request, response);
    }

    private List<TimeTableDTO> filterTimeTablesByDateRange(List<TimeTableDTO> timeTables, Date startDate, Date endDate) {
        List<TimeTableDTO> filteredTimeTables = new ArrayList<>();
        for (TimeTableDTO timeTable : timeTables) {
            if (timeTable.getStartDay().before(endDate) && timeTable.getEndDay().after(startDate)) {
                filteredTimeTables.add(timeTable);
            }
        }
        return filteredTimeTables;
    }
}
