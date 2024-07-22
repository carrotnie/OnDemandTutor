package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registerClassSlotTutor.ClassDAO;
import registerClassSlotTutor.SlotDAO;
import registerClassSlotTutor.SlotDTO;

/**
 *
 * @author Long Dinh
 */
public class InsertSlotServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String action = request.getParameter("action");
        if ("RegisterSlot".equals(action)) {
            handleRegisterSlot(request, response);
        } else {
            // Handle other actions
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void handleRegisterSlot(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        Integer tutorId = (Integer) session.getAttribute("accountId");
        if (tutorId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        SlotDAO slotDAO = new SlotDAO();
        ClassDAO classDAO = new ClassDAO();
        int classId = slotDAO.getClassIdByTutorAccountId(tutorId);
        int currentSlotCount = slotDAO.countSlotsByClassId(classId);
        int amountOfSlot = classDAO.getAmountOfSlotByClassId(classId);

        int totalNewSlots = 0;
        String[] days = request.getParameterValues("dayOfSlot[]");
        String[] startTimes = request.getParameterValues("startTime[]");
        String[] endTimes = request.getParameterValues("endTime[]");

        if (days != null && startTimes != null && endTimes != null) {
            totalNewSlots = days.length;
        }

        if (currentSlotCount + totalNewSlots > amountOfSlot) {
            request.setAttribute("errorMessage", "Số lượng Slot đã đạt tối đa.");
            RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
            rd.forward(request, response);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        for (int i = 0; i < days.length; i++) {
            String day = new String(days[i].getBytes("ISO-8859-1"), "UTF-8");
            String startTimeStr = startTimes[i];
            String endTimeStr = endTimes[i];

            try {
                Time startTime = new Time(dateFormat.parse(startTimeStr).getTime());
                Time endTime = new Time(dateFormat.parse(endTimeStr).getTime());

                long duration = (endTime.getTime() - startTime.getTime()) / (1000 * 60);

                if (duration > 120) {
                    request.setAttribute("errorMessage", "Thời gian của mỗi Slot không được vượt quá 2 tiếng.");
                    RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
                    rd.forward(request, response);
                    return;
                }

                if (slotDAO.isSlotDuplicate(tutorId, day, startTime, endTime)) {
                    request.setAttribute("errorMessage", "Slot đã tồn tại.");
                    RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
                    rd.forward(request, response);
                    return;
                }

                SlotDTO slotDTO = new SlotDTO(classId, day, startTime, endTime);

                try {
                    slotDAO.addSlot(slotDTO);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm Slot: " + e.getMessage());
                    RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
                    rd.forward(request, response);
                    return;
                }

            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Định dạng thời gian không hợp lệ: " + e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
                rd.forward(request, response);
                return;
            }
        }
        response.sendRedirect("successSlot.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertSlotServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InsertSlotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
