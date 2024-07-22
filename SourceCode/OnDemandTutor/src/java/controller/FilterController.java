/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tutor.TutorDAO;
import tutor.TutorDTO;

/**
 *
 * @author ASUS
 */
public class FilterController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<TutorDTO> tutors = new ArrayList<>();
        
        try {
            String subject = request.getParameter("subject");
            String district = request.getParameter("district");
            String grade = request.getParameter("grade");
            String rating = request.getParameter("rating");

            System.out.println("Subject: " + subject);
            System.out.println("District: " + district);
            System.out.println("Grade: " + grade);
            System.out.println("Rating: " + rating);

            TutorDAO tutorDAO = new TutorDAO();
            tutors = tutorDAO.filterTutors(subject, district, grade, rating);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("filteredTutors", tutors);
            request.getRequestDispatcher("student_homepage.jsp?action=filter").forward(request, response);
            request.getRequestDispatcher("searchTutors.jsp").forward(request, response);
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
