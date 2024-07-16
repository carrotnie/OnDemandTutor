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
import javax.servlet.http.HttpSession;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        int accountId = (int) session.getAttribute("accountId");

        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phonenumber");
        String location = request.getParameter("location");
        String personalId = request.getParameter("personalId");
        String gender = request.getParameter("gender");
        String yobStr = request.getParameter("yob");
        String experienceStr = request.getParameter("experience");
        String gradeStr = request.getParameter("grade");

        String errorMsg = null;

        if (name == null || name.trim().isEmpty()) {
            errorMsg = "Tên không được để trống.";
        } else if (!phoneNumber.matches("^\\d{10}$")) {
            errorMsg = "Số điện thoại phải là một số có 10 chữ số.";
        } else if (yobStr == null || !yobStr.matches("^\\d+$")) {
            errorMsg = "Năm sinh phải là một số.";
        } else if (location == null || location.trim().isEmpty()) {
            errorMsg = "Địa chỉ không được để trống.";
        } else if (!personalId.matches("^\\d{12}$")) {
            errorMsg = "Personal Id phải là một số có 12 chữ số.";
        } else if (!gender.equals("Nam") && !gender.equals("Nữ")) {
            errorMsg = "Giới tính phải là Nam hoặc Nữ.";
        } else if (experienceStr == null || !experienceStr.matches("^\\d+$") || Integer.parseInt(experienceStr) < 0) {
            errorMsg = "Kinh nghiệm phải là một số nguyên không âm.";
        } else if (gradeStr == null || !gradeStr.matches("^\\d+$") || Integer.parseInt(gradeStr) < 1 || Integer.parseInt(gradeStr) > 12) {
            errorMsg = "Lớp phải là một số từ 1 đến 12.";
        }

        if (errorMsg != null) {
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("register_info_tutor.jsp").forward(request, response);
        } else {
            int yob = Integer.parseInt(yobStr);
            int experience = Integer.parseInt(experienceStr);
            int grade = Integer.parseInt(gradeStr);

            boolean result = tutorDAO.updateTutorInfo(name, phoneNumber, yob, location, personalId, gender, experience, grade, accountId);

            if (result) {
                response.sendRedirect("success_update_tutor_info.html");
            } else {
                response.sendRedirect("failed_update_tutor_info.html");
            }
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
