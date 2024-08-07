package controller;

import database.DBUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mod.ModDAO;
import mod.ReportDTO;

public class ViewReportController extends HttpServlet {

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

        List<ReportDTO> reportList;
        String status = request.getParameter("status"); // Lấy giá trị của tham số status

        try (Connection connection = DBUtils.getConnection()) {
            ModDAO modDAO = new ModDAO(connection);
            Integer modId = modDAO.getModIdByAccountId(accountId);

            if (modId != null) {
                // Lấy báo cáo dựa trên ModId và Status
                if (status == null || status.isEmpty() || (!status.equals("thành công") && !status.equals("thất bại") && !status.equals("đang xử lý"))) {
                    reportList = modDAO.getReportByModId(modId);
                } else {
                    reportList = modDAO.getReportByModIdAndStatus(modId, status);
                }

                if (reportList.isEmpty()) {
                    request.setAttribute("errorMessage", "No report found for the moderator.");
                }

                request.setAttribute("REPORT_LIST", reportList);
            } else {
                request.setAttribute("errorMessage", "Moderator ID not found for this account.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            request.setAttribute("errorMessage", "An error occurred while retrieving report: " + e.getMessage());
        }

        request.getRequestDispatcher("/view_report.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
