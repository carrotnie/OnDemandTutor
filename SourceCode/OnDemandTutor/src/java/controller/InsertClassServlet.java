package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registerClassSlotTutor.ClassDAO;
import registerClassSlotTutor.ClassDTO;
import registerClassSlotTutor.SubjectDAO;
import registerClassSlotTutor.SubjectDTO;

/**
 *
 * @author Long Dinh
 */
public class InsertClassServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<SubjectDTO> subjects = new ArrayList<>();
        SubjectDAO subjectDAO = new SubjectDAO();

        try {
            subjects = subjectDAO.getSubjectsByAccountId(accountId);
            for (SubjectDTO subject : subjects) {
                System.out.println("Subject ID: " + subject.getId() + ", Name: " + subject.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("subjects", subjects);
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerClass.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String[] subjectIds = request.getParameterValues("subjectId");
        String amountOfSlotParam = request.getParameter("amountOfSlot");
        String startDayParam = request.getParameter("startDay");
        String endDayParam = request.getParameter("endDay");

        List<SubjectDTO> subjects = new ArrayList<>();
        SubjectDAO subjectDAO = new SubjectDAO();

        try {
            subjects = subjectDAO.getSubjectsByAccountId(accountId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (subjectIds == null || amountOfSlotParam == null || startDayParam == null || endDayParam == null) {
            request.setAttribute("errorMessage", "Missing required parameters.");
            request.setAttribute("subjects", subjects);
            RequestDispatcher rd = request.getRequestDispatcher("registerClass.jsp");
            rd.forward(request, response);
            return;
        }

        int amountOfSlot;
        Date startDay;
        Date endDay;
        try {
            amountOfSlot = Integer.parseInt(amountOfSlotParam);
            startDay = Date.valueOf(startDayParam);
            endDay = Date.valueOf(endDayParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input format.");
            request.setAttribute("subjects", subjects);
            RequestDispatcher rd = request.getRequestDispatcher("registerClass.jsp");
            rd.forward(request, response);
            return;
        }

        // Kiểm tra ràng buộc số lượng slot
        if (amountOfSlot > 10) {
            request.setAttribute("errorMessage", "Amount of slot không được vượt quá 10.");
            request.setAttribute("subjects", subjects);
            RequestDispatcher rd = request.getRequestDispatcher("registerClass.jsp");
            rd.forward(request, response);
            return;
        }

        // Kiểm tra ngày bắt đầu và ngày kết thúc không được ở quá khứ
        Date currentDate = new Date(System.currentTimeMillis());
        if (startDay.before(currentDate) || endDay.before(currentDate)) {
            request.setAttribute("errorMessage", "Không được chọn ngày quá khứ.");
            request.setAttribute("subjects", subjects);
            RequestDispatcher rd = request.getRequestDispatcher("registerClass.jsp");
            rd.forward(request, response);
            return;
        }

        // Kiểm tra ràng buộc ngày kết thúc phải cách ngày bắt đầu đúng 2 tháng
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDay);
        cal.add(Calendar.MONTH, 2);
        Date expectedEndDay = new Date(cal.getTimeInMillis());

        if (!endDay.equals(expectedEndDay)) {
            request.setAttribute("errorMessage", "End day must be exactly 2 months after start day.");
            request.setAttribute("subjects", subjects);
            RequestDispatcher rd = request.getRequestDispatcher("registerClass.jsp");
            rd.forward(request, response);
            return;
        }

        ClassDAO classDAO = new ClassDAO();
        for (String subjectId : subjectIds) {
            ClassDTO classDTO = new ClassDTO();
            classDTO.setTutorId(accountId);
            classDTO.setSubjectId(Integer.parseInt(subjectId));
            classDTO.setAmountOfSlot(amountOfSlot);
            classDTO.setStartDay(startDay);
            classDTO.setEndDay(endDay);

            try {
                classDAO.addClass(classDTO);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("errorClass.jsp");
                return;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InsertClassServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        response.sendRedirect("successClass.jsp");
    }

}
