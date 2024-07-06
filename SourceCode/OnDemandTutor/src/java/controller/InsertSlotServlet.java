/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registerClassSlotTutor.SlotDAO;
import registerClassSlotTutor.SlotDTO;

/**
 *
 * @author Long Dinh
 */
public class InsertSlotServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
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

            Logger.getLogger(InsertSlotServlet.class.getName()).log(Level.INFO, "dayOfSlot: " + dayOfSlotParam);
            Logger.getLogger(InsertSlotServlet.class.getName()).log(Level.INFO, "startTime: " + startTimeParam);
            Logger.getLogger(InsertSlotServlet.class.getName()).log(Level.INFO, "endTime: " + endTimeParam);

            // Validate parameters
            if (dayOfSlotParam == null || startTimeParam == null || endTimeParam == null) {
                throw new ServletException("Missing required parameters.");
            }

            String dayOfSlot = dayOfSlotParam;
            String startTime = startTimeParam;
            String endTime = endTimeParam;

            SlotDTO slotDTO = new SlotDTO();
            slotDTO.setClassId(tutorId);
            slotDTO.setDayOfSlot(dayOfSlot);
            slotDTO.setStartTime(startTime);
            slotDTO.setEndTime(endTime);

            SlotDAO slotDAO = new SlotDAO();
            try {
                slotDAO.addSlot(slotDTO);
                response.sendRedirect("tutor_homepage.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("errorSlot.jsp");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InsertSlotServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
