/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registerClassSlotTutor.ClassDAO;
import registerClassSlotTutor.ClassDTO;

/**
 *
 * @author Long Dinh
 */
public class InsertClassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            Integer tutorId = (Integer) session.getAttribute("accountId");

            if (tutorId == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Ensure parameters are correctly named
            String subjectIdParam = request.getParameter("subjectId");
            String amountOfSlotParam = request.getParameter("amountOfSlot");
            String startDayParam = request.getParameter("startDay");
            String endDayParam = request.getParameter("endDay");

            // Log the parameter values
            Logger.getLogger(InsertClassServlet.class.getName()).log(Level.INFO, "subjectId: " + subjectIdParam);
            Logger.getLogger(InsertClassServlet.class.getName()).log(Level.INFO, "amountOfSlot: " + amountOfSlotParam);
            Logger.getLogger(InsertClassServlet.class.getName()).log(Level.INFO, "startDay: " + startDayParam);
            Logger.getLogger(InsertClassServlet.class.getName()).log(Level.INFO, "endDay: " + endDayParam);

            // Validate parameters
            if (subjectIdParam == null || amountOfSlotParam == null || startDayParam == null || endDayParam == null) {
                throw new ServletException("Missing required parameters.");
            }

            int subjectId = Integer.parseInt(subjectIdParam);
            int amountOfSlot = Integer.parseInt(amountOfSlotParam);
            Date startDay = Date.valueOf(startDayParam);
            Date endDay = Date.valueOf(endDayParam);

            ClassDTO classDTO = new ClassDTO();
            classDTO.setTutorId(tutorId);
            classDTO.setSubjectId(subjectId);
            classDTO.setAmountOfSlot(amountOfSlot);
            classDTO.setStartDay(startDay);
            classDTO.setEndDay(endDay);

            ClassDAO classDAO = new ClassDAO();
            try {
                classDAO.addClass(classDTO);
                response.sendRedirect("successClass.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("errorClass.jsp");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InsertClassServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
