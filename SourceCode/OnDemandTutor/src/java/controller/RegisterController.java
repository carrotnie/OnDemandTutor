/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserDAO;
import user.UserDTO;
import user.UserError;

/**
 *
 * @author MSI
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            UserDAO dao = new UserDAO();
            boolean checkValidation = true;
            String Name = request.getParameter("Name");
            String Username = request.getParameter("Username");
            String Password = request.getParameter("Password");
            String Confirm = request.getParameter("Confirm");
            String Role = request.getParameter("Role");

            if (Username.length() < 5 || Username.length() > 20) {
                userError.setUsernameError("Tên tài khoản có độ dài từ 5 đến 20 !!!");
                checkValidation = false;
            }
            boolean checkDuplicate = dao.checkDuplicate(Username);
            if (checkDuplicate) {
                userError.setNameError("Tên tài khoản này đã được đăng ký rồi!!!");
                checkValidation = false;
            }
            if (Password.length() < 6 || Password.length() > 15) {
                userError.setPasswordError("Mật khẩu phải có độ dài từ 6 đến 15 ký tự.");
                checkValidation = false;
            }
            if (!isValidPassword(Password)) {
                userError.setPasswordError("Mật khẩu phải có ít nhất 1 chữ cái viết hoa, 1 chữ số và không có khoảng trắng.");
                checkValidation = false;
            }
            if (!Password.equals(Confirm)) {
                userError.setConfirmError("Hai mật khẩu không giống nhau !!!");
                checkValidation = false;
            }
            if (checkValidation) {
                UserDTO user = new UserDTO(Name, Username, Password, Role);
                boolean checkInsert = dao.insert(user);
                if (checkInsert) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("USER_ERROR", "Unknow error!");
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController" + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setNameError("Trùng khóa chính rồi kìa trời ơi !!!");
                request.setAttribute("USER_ERROR", userError);
            }

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

     private boolean isValidPassword(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*\\d)[^\\s]{6,15}$";
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
