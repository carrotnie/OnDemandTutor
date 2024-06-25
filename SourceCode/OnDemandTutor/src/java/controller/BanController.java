package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserDAO;

@WebServlet(name = "BanController", urlPatterns = {"/BanController"})
public class BanController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int accountId = Integer.parseInt(request.getParameter("AccountId"));
            String action = request.getParameter("actionType");
            UserDAO dao = new UserDAO();
            boolean check = false;
            
            if ("Ban".equals(action)) {
                check = dao.updateAccountStatus(accountId);
            } else if ("UnBan".equals(action)) {
                check = dao.updateAccountStatusToActive(accountId);
            }

            if (check) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at BanController: " + e.toString());
        } finally {
            // Lưu lại từ khóa tìm kiếm
            String search = request.getParameter("search");
            if (search != null) {
                request.setAttribute("search", search);
            }
            request.getRequestDispatcher(url).forward(request, response);
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
