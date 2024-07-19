/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import email.EmailUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Lam Le
 */
public class RequestOtpController extends HttpServlet {

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
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        UserDAO userDAO = new UserDAO();
        try {
            UserDTO user = userDAO.getUserByUsername(username); // Tìm người dùng qua username
            if (user != null) {
                // Tạo mã OTP
                String otp = userDAO.generateOtp();
                // Cập nhật OTP vào cơ sở dữ liệu
                userDAO.updateOtp(username, otp);
                // Gửi OTP qua email
                EmailUtility.sendEmail(email, "Your OTP Code", "Your OTP code is: " + otp);
                // Lưu username vào session để sử dụng sau này
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                // Chuyển hướng tới trang nhập OTP
                request.getRequestDispatcher("display_otp.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Tên người dùng không tồn tại.");
                request.getRequestDispatcher("request_otp.jsp").forward(request, response);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi trong quá trình gửi email.");
            request.getRequestDispatcher("request_otp.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi trong quá trình yêu cầu OTP.");
            request.getRequestDispatcher("request_otp.jsp").forward(request, response);
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
