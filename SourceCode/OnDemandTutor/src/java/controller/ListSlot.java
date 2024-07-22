package controller;

import slot.SlotDAO;
import slot.SlotDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListSlot", urlPatterns = {"/ListSlot"})
public class ListSlot extends HttpServlet {

    private static final String SLOTS_PAGE = "slots.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String cId = request.getParameter("cId");
            request.setAttribute("classId", cId);
            SlotDAO slotDAO = new SlotDAO();
            List<SlotDTO> slots = slotDAO.getAll(cId);
            request.setAttribute("sl", slots);
            request.getRequestDispatcher(SLOTS_PAGE).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Có lỗi xảy ra, vui lòng thử lại sau.");
            request.getRequestDispatcher("error_page.jsp").forward(request, response);
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
}
