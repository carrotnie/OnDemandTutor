/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import feedback.FeedbackDAO;
import feedback.FeedbackDTO;
import feedback.Moderator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class submitFeedbackController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
             // Lấy dữ liệu từ form
            int tutorId = Integer.parseInt(request.getParameter("tutorId"));
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            int slotId = Integer.parseInt(request.getParameter("slotId"));
            int modId = Moderator.getRandomModeratorId();
            String feedbackText = request.getParameter("feedback");

            // Tạo đối tượng FeedbackDTO và gọi phương thức DAO để chèn dữ liệu vào cơ sở dữ liệu
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            feedbackDTO.setTutorId(tutorId);
            feedbackDTO.setStudentId(studentId);
            feedbackDTO.setSubjectId(subjectId);
            feedbackDTO.setRating(rating);
            feedbackDTO.setSlotId(slotId);
            feedbackDTO.setModId(modId);
            feedbackDTO.setFeedback(feedbackText);

            FeedbackDAO.insertRating(feedbackDTO);
            FeedbackDAO.insertFeedback(feedbackDTO); 
        } catch (Exception e) {
            // Xử lý lỗi và hiển thị thông báo lỗi
            e.printStackTrace();
            response.sendRedirect("error.jsp");

        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher("thankYou.jsp");
            dispatcher.forward(request, response);
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
