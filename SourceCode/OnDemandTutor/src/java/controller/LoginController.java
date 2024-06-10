/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author MSI
 */
@WebServlet(name= "LoginController",urlPatterns={"/LoginController"})
public class LoginController extends HttpServlet{
    private static final String LOGIN_PAGE="login.jsp";
    private static final String AD="admin";
    private static final String MOD="moderator";
    private static final String ST="student";
    private static final String TU="tutor";
    private static final String ADMIN_PAGE="admin.jsp";
    private static final String MOD_PAGE="moderator.html";
    private static final String ST_PAGE="student.html";
    private static final String TU_PAGE="tutor.html";
    protected void processRequest ( HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=LOGIN_PAGE;
        try{
            String Username=request.getParameter("Username");
            String Password=request.getParameter("Password");
            UserDAO dao=new UserDAO();
            UserDTO loginUser=dao.checkLogin(Username,Password);
            if(loginUser==null){
                request.setAttribute("ERROR", "Sai tài khoản hoặc mật khẩu. Vui lòng nhập lại !!!");
            }else{
                String Role=loginUser.getRole();
                HttpSession session=request.getSession();
                if (AD.equals(Role)){
                    session.setAttribute("LOGIN_USER", loginUser);
                    url=ADMIN_PAGE;
                }else if (MOD.equals(Role)){
                    session.setAttribute("LOGIN_USER", loginUser);
                    url=MOD_PAGE;
                }else if(ST.equals(Role)){
                    session.setAttribute("LOGIN_USER", loginUser);
                    url=ST_PAGE;
                }else if (TU.equals(Role)){
                    session.setAttribute("LOGIN_USER", loginUser);
                    url=TU_PAGE;
                } 
            }
        }catch (Exception e){
            log("Error at LoginController"+e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
