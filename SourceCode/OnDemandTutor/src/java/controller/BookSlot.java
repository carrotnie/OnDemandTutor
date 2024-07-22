package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import slot.SlotDAO;
import slot.SlotDTO;
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
                String slotId = request.getParameter("slotId");
                String classId = request.getParameter("classId");

                if (slotId == null || slotId.isEmpty()) {
                    request.setAttribute("errorMessage", "Slot ID không hợp lệ.");
                    request.getRequestDispatcher("error_page.jsp").forward(request, response);
                    return;
                }

                int slotIdInt = Integer.parseInt(slotId);
                SlotDAO slotDAO = new SlotDAO();
                List<SlotDTO> slots = slotDAO.getAll(classId);

                SlotDTO slotToBook = null;
                for (SlotDTO slot : slots) {
                    if (slot.getId() == slotIdInt) {
                        slotToBook = slot;
                        break;
                    }
                }

                if (slotToBook == null) {
                    request.setAttribute("errorMessage", "Slot không tồn tại.");
                    request.getRequestDispatcher("error_page.jsp").forward(request, response);
                    return;
                }

                if (slotDAO.checkSlotConflict(slotId, loginUser.getId())) {
                    request.setAttribute("errorMessage", "Lịch học bị trùng, không thể đăng ký.");
                    request.getRequestDispatcher("error_page.jsp").forward(request, response);
                } else if (!slotDAO.checkBalance(loginUser.getId(), slotToBook.getPrice())) {
                    request.setAttribute("errorMessage", "Số dư trong ví của bạn không đủ để đăng ký, vui lòng nạp thêm.");
                    request.getRequestDispatcher("error_page.jsp").forward(request, response);
                } else {
                    slotDAO.bookingSlot(slotId, loginUser.getId());
                    response.sendRedirect("booking_slot_successful.html");
                }
            }
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
