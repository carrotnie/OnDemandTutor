/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author ASUS
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class InsertInfoStudentController extends HttpServlet {

    private static final String SAVE_DIR = "D:\\semester 5\\SWP\\ccccccccccc\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\student";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa UTF-8 cho yêu cầu
    response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String message = null;
        String forwardPage = "login.jsp"; // Trang mặc định chuyển tiếp

        try {
            HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo session mới nếu không tồn tại
            if (session == null || session.getAttribute("USER_ID") == null) {
                throw new Exception("Account ID not found in session.");
            }
            Integer accountId = (Integer) session.getAttribute("USER_ID");
            String gender = request.getParameter("gender");
            int yob = Integer.parseInt(request.getParameter("yob"));
            String location = request.getParameter("location");
            String phoneNumber = request.getParameter("phoneNumber");
            int grade = Integer.parseInt(request.getParameter("grade"));

            // Xử lý việc upload tệp
            Part filePart = request.getPart("picture");
            if (filePart == null || filePart.getSize() == 0) {
                throw new Exception("File upload failed. No file received.");
            }
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Sửa lỗi cho MSIE.

            // Đường dẫn tuyệt đối đến thư mục lưu trữ tệp
            String savePath = SAVE_DIR;

            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                boolean dirCreated = fileSaveDir.mkdirs();
                if (!dirCreated) {
                    throw new IOException("Failed to create directory: " + savePath);
                }
            }
            String filePath = savePath + File.separator + fileName;

            try (InputStream fileContent = filePart.getInputStream();
                    FileOutputStream outputStream = new FileOutputStream(filePath)) {
                int read;
                final byte[] bytes = new byte[1024];
                while ((read = fileContent.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            }

            UserDTO student = new UserDTO(accountId, gender, yob, location, phoneNumber, grade);
            UserDAO userDAO = new UserDAO();
            userDAO.insertStudent(student);

        } catch (Exception e) {
            e.printStackTrace();
            message = "Error: " + e.getClass().getName() + ": " + e.getMessage();
            forwardPage = "error11.jsp"; // Trang lỗi chuyển tiếp
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher(forwardPage).forward(request, response);
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
