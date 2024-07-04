package controller;

import timetable.TimeTableDAO;
import timetable.TimeTableDTO;
import timetable.Moderator;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitComplaintController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Retrieve form parameters
            String tutorIdStr = request.getParameter("tutorId");
            String studentIdStr = request.getParameter("studentId");
            String slotIdStr = request.getParameter("slotId");
            String content = request.getParameter("content");

            // Check if parameters are valid
            StringBuilder missingParams = new StringBuilder();

            if (tutorIdStr == null || tutorIdStr.isEmpty()) {
                missingParams.append("tutorId ");
            }
            if (studentIdStr == null || studentIdStr.isEmpty()) {
                missingParams.append("studentId ");
            }
            if (slotIdStr == null || slotIdStr.isEmpty()) {
                missingParams.append("slotId ");
            }
            if (content == null || content.isEmpty()) {
                missingParams.append("content ");
            }

            if (missingParams.length() > 0) {
                throw new IllegalArgumentException("Missing or empty parameters: " + missingParams.toString().trim());
            }

            int tutorId = Integer.parseInt(tutorIdStr);
            int studentId = Integer.parseInt(studentIdStr);
            int slotId = Integer.parseInt(slotIdStr);

            // Get a random moderator ID
            int modId = Moderator.getRandomModeratorId();

            // Create DTO and set properties
            TimeTableDTO complaintDTO = new TimeTableDTO();
            complaintDTO.setTutorId(tutorId);
            complaintDTO.setStudentId(studentId);
            complaintDTO.setSlotId(slotId);
            complaintDTO.setModId(modId);
            complaintDTO.setContent(content);

            // Insert complaint into database
            TimeTableDAO.insertComplaint(complaintDTO);

            // Redirect to thank you page
            RequestDispatcher dispatcher = request.getRequestDispatcher("thankYou.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
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
        return "Submit Complaint Controller";
    }
}
