package controller;

import classes.ClassesDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import slot.SlotDAO;
import user.UserDTO;

@WebServlet(name = "BookSlot", urlPatterns = {"/BookSlot"})
public class BookSlot extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("LOGIN_USER") == null) {
                response.sendRedirect("login.html");
            } else {
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                String slotId = request.getParameter("slotId") == null ? "" : request.getParameter("slotId");
                new SlotDAO().bookingSlot(slotId, loginUser.getId());
                response.sendRedirect("booking_slot_successful.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Optional: you can add error handling logic here if needed
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
