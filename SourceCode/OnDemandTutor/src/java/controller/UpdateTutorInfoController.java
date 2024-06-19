/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tutor.TutorDAO;
import tutor.TutorDTO;

/**
 *
 * @author Lam Le
 */
public class UpdateTutorInfoController extends HttpServlet {
    private TutorDAO tutorDAO;
    
    public void init() {
        tutorDAO = new TutorDAO();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateTutorInfoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateTutorInfoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract tutor information from request parameters
        String phoneNumber = request.getParameter("phonenumber");
        String location = request.getParameter("location");
        String yobStr = request.getParameter("yob");
        String name = request.getParameter("name");

        try {
            int yob = Integer.parseInt(yobStr);

            // Create TutorDTO object
            TutorDTO tutor = new TutorDTO();
            tutor.setPhoneNumber(phoneNumber);
            tutor.setLocation(location);
            tutor.setYob(yob);
            tutor.setName(name);

            // Update tutor
            boolean result = tutorDAO.updateTutor(tutor);

            if (result) {
                response.sendRedirect("success_update_tutor_info.html"); // Redirect or display success message
            } else {
                response.sendRedirect("failed_update_tutor_info.html"); // Redirect or display failure message
            }
        } catch (Exception e) {
            throw new ServletException("Error updating tutor", e);
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
