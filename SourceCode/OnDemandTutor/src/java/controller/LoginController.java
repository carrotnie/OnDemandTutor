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
 * Servlet implementation class LoginController
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String AD = "admin";
    private static final String MOD = "moderator";
    private static final String ST = "student";
    private static final String TU = "tutor";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String MOD_PAGE = "moderator.html";
    private static final String ST_PAGE = "student.html";
    private static final String TU_PAGE = "tutor.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(username, password);
            if (loginUser == null) {
                request.setAttribute("ERROR", "Sai tài khoản hoặc mật khẩu. Vui lòng nhập lại !!!");
            } else {
                String role = loginUser.getRole();
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                if (AD.equals(role)) {
                    url = ADMIN_PAGE;
                } else if (MOD.equals(role)) {
                    url = MOD_PAGE;
                } else if (ST.equals(role)) {
                    // Lấy Id từ UserDTO
                    int accountId = loginUser.getId();
                    session.setAttribute("accountId", accountId); // Lưu accountId trong session
                    url = ST_PAGE;
                } else if (TU.equals(role)) {
                    url = TU_PAGE;
                }
            }
        } catch (Exception e) {
            log("Error at LoginController" + e.toString());
        } finally {
            response.sendRedirect(url); // Sử dụng sendRedirect thay vì forward để chuyển hướng đúng cách
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
