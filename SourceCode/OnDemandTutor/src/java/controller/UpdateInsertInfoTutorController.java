/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import user.TutorError;
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
public class UpdateInsertInfoTutorController extends HttpServlet {

    private static final String TUTOR_SAVE_DIR = "D:\\swp\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\tutor";
    private static final String CERTIFICATE_SAVE_DIR = "D:\\swp\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\certificate";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa UTF-8 cho yêu cầu
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("accountId") == null) {
                throw new Exception("Account ID không tìm thấy trong session.");
            }
            Integer accountId = (Integer) session.getAttribute("accountId");
            System.out.println("Account ID update: " + accountId);  // Log accountId để kiểm tra

            UserDAO userDAO = new UserDAO();
            UserDTO tutor = userDAO.getTutorInfoByAccountId(accountId);
            if (tutor == null) {
                throw new Exception("Không tìm thấy gia sư cho Account ID: " + accountId);
            }

            // Xử lý form submission
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String active = request.getParameter("active");
                String phoneNumber = request.getParameter("phoneNumber");
                String yobString = request.getParameter("yob");
                String personalId = request.getParameter("personalId");
                String gender = request.getParameter("gender");
                String[] subjects = request.getParameterValues("subject[]");
                String gradeString = request.getParameter("grade");
                String location = request.getParameter("location");
                String url = request.getParameter("url");
                String content = request.getParameter("content");
                String experienceString = request.getParameter("experience");
                Part picturePart = request.getPart("profile");
                Part certificatePart = request.getPart("certificate");
                //hiện lỗi
                boolean hasError = false;
                if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
                    request.setAttribute("phoneNumberError", "Số điện thoại phải là 10 chữ số.");
                    hasError = true;
                }
                if (yobString == null || yobString.isEmpty()) {
                    request.setAttribute("yobError", "Năm sinh không được để trống.");
                    hasError = true;
                }
                if (personalId == null || !personalId.matches("\\d{12}")) {
                    request.setAttribute("personalIdError", "CCCD phải là 12 chữ số.");
                    hasError = true;
                }
                if (subjects == null || subjects.length == 0) {
                    request.setAttribute("subjectError", "Phải chọn ít nhất một môn học.");
                    hasError = true;
                }
                if (gradeString == null || gradeString.isEmpty()) {
                    request.setAttribute("gradeError", "Vui lòng chọn trình độ giảng dạy.");
                    hasError = true;
                }
                if (location == null || location.isEmpty()) {
                    request.setAttribute("locationError", "Địa chỉ không được để trống.");
                    hasError = true;
                }
                if (url == null || !url.matches("https://www.youtube.com/.*")) {
                    request.setAttribute("urlError", "URL phải bắt đầu bằng https://www.youtube.com/");
                    hasError = true;
                }
                if (content == null || content.isEmpty()) {
                    request.setAttribute("contentError", "Bio không được để trống.");
                    hasError = true;
                }
                if (experienceString == null || experienceString.isEmpty()) {
                    request.setAttribute("experienceError", "Vui lòng chọn số năm kinh nghiệm.");
                    hasError = true;
                }
                //
                if (hasError) {
                    request.setAttribute("tutor", tutor);
                    request.getRequestDispatcher("updateInfo.jsp").forward(request, response);
                    return;
                }

                try {
                    int yob = Integer.parseInt(yobString);
                    int grade = Integer.parseInt(gradeString);
                    int experience = Integer.parseInt(experienceString);

                    // Cập nhật tutor và xử lý file uploads
                    userDAO.updateTutor(tutor.getTutorId(), active);

                    // Cập nhật vào bảng CV
                    int modId = 1; // ID moderator ví dụ
                    UserDTO updateTutor = new UserDTO(tutor.getTutorId(), modId, phoneNumber, yob, location, personalId, gender, experience, grade, content, url);
                    userDAO.updateCV(updateTutor);

                    // Cập nhật vào bảng TutorSubject
                    List<Integer> subjectIds = new ArrayList<>();
                    for (String subject : subjects) {
                        subjectIds.add(Integer.parseInt(subject));
                    }
                    userDAO.updateTutorSubject(tutor.getTutorId(), subjectIds);

                    // Xử lý file uploads
                    if (picturePart != null && picturePart.getSize() > 0) {
                        String picturePath = saveAndConvertFile(picturePart, tutor.getTutorId(), TUTOR_SAVE_DIR, "jpg", "tutor");
                        System.out.println("Picture saved at: " + picturePath);
                    }
                    if (certificatePart != null && certificatePart.getSize() > 0) {
                        String certificatePath = saveAndConvertFile(certificatePart, tutor.getTutorId(), CERTIFICATE_SAVE_DIR, "png", "certificate");
                        System.out.println("Certificate saved at: " + certificatePath);
                    }

                    // trả về
                    response.sendRedirect("tutor_homepage.jsp");
                    return;

                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Định dạng đầu vào không hợp lệ: " + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    request.setAttribute("errorMessage", "Lỗi xử lý file: " + e.getMessage());
                    e.printStackTrace();
                } catch (Exception e) {
                    request.setAttribute("errorMessage", "Lỗi: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            // Hiển thị thông tin gia sư
            String profileImage = "img/tutor/" + tutor.getTutorId() + ".jpg";
            String certificateImage = "img/certificate/" + tutor.getTutorId() + ".png";
            request.setAttribute("tutor", tutor);
            request.setAttribute("profileImage", profileImage);
            request.setAttribute("certificateImage", certificateImage);

            request.getRequestDispatcher("updateInfo.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Định dạng đầu vào không hợp lệ: " + e.getMessage());
            e.printStackTrace();
            if (!response.isCommitted()) {
                request.getRequestDispatcher("error11.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            if (!response.isCommitted()) {
                request.getRequestDispatcher("error11.jsp").forward(request, response);
            }
        }
    }

    private String saveAndConvertFile(Part filePart, int tutorId, String saveDir, String targetFormat, String fileType) throws IOException {
        if (filePart != null && filePart.getSize() > 0) {
            BufferedImage image = ImageIO.read(filePart.getInputStream());
            if (image == null) {
                throw new IOException("Failed to read image file: " + filePart.getSubmittedFileName());
            }

            String newFileName = tutorId + "." + targetFormat;
            String filePath = saveDir + File.separator + newFileName;

            // Xóa tệp hình cũ nếu nó tồn tại
            File oldFile = new File(filePath);
            if (oldFile.exists()) {
                boolean deleted = oldFile.delete();
                if (!deleted) {
                    throw new IOException("Failed to delete old file: " + filePath);
                }
            }

            File uploadDir = new File(saveDir);
            if (!uploadDir.exists()) {
                boolean dirCreated = uploadDir.mkdirs();
                if (!dirCreated) {
                    throw new IOException("Failed to create directory: " + saveDir);
                }
            }

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(targetFormat);
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
