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
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Lam Le
 */
public class ResetPasswordController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String otp = request.getParameter("otp");
        String newPassword = request.getParameter("newPassword");

        UserDAO userDAO = new UserDAO();
        try {
            UserDTO user = userDAO.getUserByUsername(username);
            if (user != null) {
                if (user.getOtp().trim().equals(otp.trim())) {
                    if (newPassword.length() < 6 || newPassword.length() > 15) {
                        request.setAttribute("error", "Mật khẩu phải có độ dài từ 6 đến 15 ký tự.");
                    } else if (!isValidPassword(newPassword)) {
                        request.setAttribute("error", "Mật khẩu phải có ít nhất 1 chữ cái viết hoa, 1 chữ số, 1 ký tự đặc biệt và không có khoảng trắng.");
                    } else {
                        userDAO.updatePassword(username, newPassword);
                        request.setAttribute("message", "Mật khẩu của bạn đã được đặt lại thành công.");
                    }
                } else {
                    request.setAttribute("otpError", "OTP không hợp lệ.");
                }
            } else {
                request.setAttribute("otpError", "Người dùng không tồn tại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("otpError", "Đã xảy ra lỗi trong quá trình đặt lại mật khẩu.");
        }
        request.getRequestDispatcher("display_otp.jsp").forward(request, response);
    }

    private boolean isValidPassword(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,15}$";
        return password.matches(pattern);
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
