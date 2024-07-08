package controller;

import database.DBUtils;
import mod.CvDTO;
import mod.ModDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ViewCvController")
public class ViewCvController extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        StringBuilder errorMessage = new StringBuilder();

        try (Connection connection = DBUtils.getConnection()) {
            ModDAO modDAO = new ModDAO(connection);
            Integer modId = modDAO.getModIdByAccountId(accountId);

            if (modId != null) {
                List<CvDTO> cvList = modDAO.getTutorDataByActiveStatusAndModId("chưa kiểm duyệt", modId);

                if (cvList.isEmpty()) {
                    errorMessage.append("No CVs found for the moderator.");
                } else {
                    request.setAttribute("CV_LIST", cvList);
                }
            } else {
                errorMessage.append("Moderator ID not found for this account.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            errorMessage.append("An error occurred while retrieving CVs: ").append(e.getMessage());
        }

        if (errorMessage.length() > 0) {
            request.setAttribute("errorMessage", errorMessage.toString());
        }

        request.getRequestDispatcher("/view_cv.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet that retrieves and displays CVs for a moderator";
    }
}
