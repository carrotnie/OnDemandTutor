/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            Integer tutorId = (Integer) session.getAttribute("accountId");

            if (tutorId == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String dayOfSlotParam = request.getParameter("dayOfSlot");
            String startTimeParam = request.getParameter("startTime");
            String endTimeParam = request.getParameter("endTime");

            // Validate parameters
            if (dayOfSlotParam == null || startTimeParam == null || endTimeParam == null) {
                throw new ServletException("Missing required parameters.");
            }

            String dayOfSlot = new String(dayOfSlotParam.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            String startTime = startTimeParam;
            String endTime = endTimeParam;

            // Kiểm tra ràng buộc thời gian
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            java.util.Date start = format.parse(startTime);
            java.util.Date end = format.parse(endTime);

            long differenceInMilliSeconds = end.getTime() - start.getTime();
            long differenceInHours = differenceInMilliSeconds / (60 * 60 * 1000);

            if (differenceInHours != 2) {
                request.setAttribute("errorMessage", "Thời gian kết thúc phải cách thời gian bắt đầu đúng 2 giờ.");
                RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
                rd.forward(request, response);
                return;
            }

            // Kiểm tra số lượng Slot hiện tại
            SlotDAO slotDAO = new SlotDAO();
            ClassDAO classDAO = new ClassDAO();
            int classId = slotDAO.getClassIdByTutorAccountId(tutorId);
            int currentSlotCount = slotDAO.countSlotsByClassId(classId);
            int amountOfSlot = classDAO.getAmountOfSlotByClassId(classId);

            if (currentSlotCount >= amountOfSlot) {
                request.setAttribute("errorMessage", "Số lượng Slot đã đạt tối đa.");
                RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
                rd.forward(request, response);
                return;
            }

            SlotDTO slotDTO = new SlotDTO();
            slotDTO.setClassId(classId);
            slotDTO.setDayOfSlot(dayOfSlot);
            slotDTO.setStartTime(startTime);
            slotDTO.setEndTime(endTime);

            try {
                slotDAO.addSlot(slotDTO);
                response.sendRedirect("successSlot.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm Slot.");
                RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
                rd.forward(request, response);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Thời gian không hợp lệ.");
            RequestDispatcher rd = request.getRequestDispatcher("registerSlot.jsp");
            rd.forward(request, response);
        } finally {
            // Close resources if needed
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertSlotServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InsertSlotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
