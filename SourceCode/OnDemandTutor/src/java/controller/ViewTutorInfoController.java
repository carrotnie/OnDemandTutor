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
import tutor.TutorDAO;
import tutor.TutorDTO;

/**
 *
 * @author Lam Le
 */
public class ViewTutorInfoController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(ViewTutorInfoController.class.getName());

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
            out.println("<title>Servlet ViewTutorInfoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewTutorInfoController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp"); // Chuyển hướng đến trang đăng nhập nếu không có accountId trong session
            return;
        }

        try {
            TutorDAO tutorDAO = new TutorDAO();
            TutorDTO tutor = tutorDAO.getTutorByAccountId(0);
            if (tutor != null) {
                LOGGER.log(Level.INFO, "Tutor: {0}", tutor.toString());
//                String photoPath = "/img/tutor/s" + accountId + ".jpg"; // Tạo đường dẫn ảnh dựa trên accountId
//                request.setAttribute("photoPath", request.getContextPath() + photoPath);
                request.setAttribute("name", tutor.getName());
                request.setAttribute("phonenumber", tutor.getPhoneNumber());
                request.setAttribute("yob", tutor.getYob());
                request.setAttribute("location", tutor.getLocation());

            } else {
                LOGGER.log(Level.WARNING, "Tutor not found for accountId: {0}", accountId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error at ViewStuInfoController: " + e.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewTutorInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/tutor_infor.jsp").forward(request, response);
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
