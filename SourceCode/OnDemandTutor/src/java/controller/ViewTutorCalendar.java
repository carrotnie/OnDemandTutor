/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tutor.ScheduleDTO;

import tutor.TutorDAO;

/**
 *
 * @author Long Dinh
 */
public class ViewTutorCalendar extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ViewTutorCalendar.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if no accountId in session
            return;
        }

        try {
            TutorDAO tutorDAO = new TutorDAO();
            List<ScheduleDTO> schedules = tutorDAO.getSchedulesByAccountId(accountId);

            if (schedules != null && !schedules.isEmpty()) {
                request.setAttribute("status", "thành công");
                request.setAttribute("schedules", schedules);
            } else {
                request.setAttribute("status", "không có lịch dạy");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Error at Schedule processing: " + e.toString());
            request.setAttribute("status", "thất bại");
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Class not found error at Schedule processing: " + ex.toString());
            request.setAttribute("status", "thất bại");
        }

        request.getRequestDispatcher("tutor_calendar.jsp").forward(request, response);
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
