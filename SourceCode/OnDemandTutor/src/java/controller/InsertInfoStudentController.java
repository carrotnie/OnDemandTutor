/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import user.StudentError;
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
            String name = request.getParameter("Name");
            String gender = request.getParameter("gender");
            String yobString = request.getParameter("yob");
            String location = request.getParameter("location");
            String phoneNumber = request.getParameter("phoneNumber");
            String gradeString = request.getParameter("grade");

            StudentError errors = new StudentError();

            if (name == null || name.trim().isEmpty()) {
                errors.setNameError("Tên không được để trống.");
            } else {
                errors.setNameError(""); // Đảm bảo lỗi không phải null
            }
            errors.checkGender(gender);
            errors.checkYob(yobString);
            errors.checkLocation(location);
            errors.checkPhoneNumber(phoneNumber);
            errors.checkGrade(gradeString);

            Part filePart = request.getPart("picture");
            if (filePart == null || filePart.getSize() == 0) {
                errors.setPictureError("Vui lòng chọn ảnh đại diện.");
            } else {
                errors.checkPicture(filePart.getSubmittedFileName());
            }

            if (!errors.isValid()) {
                request.setAttribute("nameError", errors.getNameError());
                request.setAttribute("genderError", errors.getGenderError());
                request.setAttribute("yobError", errors.getYobError());
                request.setAttribute("locationError", errors.getLocationError());
                request.setAttribute("phoneNumberError", errors.getPhoneNumberError());
                request.setAttribute("gradeError", errors.getGradeError());
                request.setAttribute("pictureError", errors.getPictureError());
                request.getRequestDispatcher("register_info_student.jsp").forward(request, response);
            } else {
                int yob = Integer.parseInt(yobString);
                int grade = Integer.parseInt(gradeString);

                // Cập nhật tên 
                UserDAO userDAO = new UserDAO();
                userDAO.updateAccountName(accountId, name);

                // chèn thông tin 
                UserDTO student = new UserDTO(accountId, gender, yob, location, phoneNumber, grade);
                int studentId = userDAO.insertStudent(student);

                // Xử lý việc upload tệp
                if (filePart != null && filePart.getSize() > 0) {
                    String picturePath = saveAndConvertFile(filePart, studentId, SAVE_DIR, "jpg", "student");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error: " + e.getClass().getName() + ": " + e.getMessage();
            forwardPage = "error11.jsp"; 
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher(forwardPage).forward(request, response);
        }
    }

    private String saveAndConvertFile(Part filePart, int studentId, String saveDir, String targetFormat, String fileType) throws IOException {
        if (filePart != null && filePart.getSize() > 0) {
            BufferedImage image = ImageIO.read(filePart.getInputStream());
            if (image == null) {
                throw new IOException("Failed to read image file: " + filePart.getSubmittedFileName());
            }

            String newFileName = "s" + studentId + "." + targetFormat;  // Sử dụng Id của student làm tên file
            String filePath = saveDir + File.separator + newFileName;

            File uploadDir = new File(saveDir);
            if (!uploadDir.exists()) {
                boolean dirCreated = uploadDir.mkdirs();
                if (!dirCreated) {
                    throw new IOException("Failed to create directory: " + saveDir);
                }
            }

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(targetFormat);
            if (!writers.hasNext()) {
                throw new IOException("No writer found for format: " + targetFormat);
            }
            ImageWriter writer = writers.next();
            try (ImageOutputStream ios = ImageIO.createImageOutputStream(new File(filePath))) {
                writer.setOutput(ios);
                writer.write(image);
            }
            return "img/" + fileType + "/" + newFileName;
        }
        return null;
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
