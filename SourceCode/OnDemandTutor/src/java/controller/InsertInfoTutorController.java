/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;
import feedback.Moderator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import user.TutorError;
import static user.UserDAO.getUserNameById;

/**
 *
 * @author ASUS
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class InsertInfoTutorController extends HttpServlet {

    private static final String TUTOR_SAVE_DIR = "D:\\swp\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\tutor";
    private static final String CERTIFICATE_SAVE_DIR = "D:\\swp\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\certificate";
    private static final String CERTIFICATE_SAVE_DIR_2 = "D:\\swp\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\certificate2";
    private static final String CERTIFICATE_SAVE_DIR_3 = "D:\\swp\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\certificate3";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Thiết lập mã hóa UTF-8 cho yêu cầu
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo session mới nếu không tồn tại
            if (session == null || session.getAttribute("accountId") == null) {
                throw new Exception("Account ID not found in session.");
            }
            Integer accountId = (Integer) session.getAttribute("accountId");
            String active = request.getParameter("active");

            String name = getUserNameById(accountId);
            request.setAttribute("userName", name);
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

            TutorError errors = new TutorError();
            errors.checkName(name);
            errors.checkPhoneNumber(phoneNumber);
            errors.checkYob(yobString);
            errors.checkPersonalId(personalId);
            errors.checkGender(gender);
            errors.checkSubjects(subjects);
            errors.checkGrade(gradeString);
            errors.checkLocation(location);
            errors.checkUrl(url);
            errors.checkContent(content);
            errors.checkExperience(experienceString);

//            Part picturePart = request.getPart("picture");
//            Part certificatePart = request.getPart("certificate");
//
//            if (picturePart == null || picturePart.getSize() == 0) {
//                errors.setPictureError("Vui lòng chọn ảnh đại diện.");
//            } else {
//                errors.checkPicture(picturePart.getSubmittedFileName());
//            }
//
//            if (certificatePart == null || certificatePart.getSize() == 0) {
//                errors.setCertificateError("Vui lòng chọn chứng chỉ.");
//            } else {
//                errors.checkCertificate(certificatePart.getSubmittedFileName());
//            }

            if (!errors.isValid()) {
                request.setAttribute("nameError", errors.getNameError());
                request.setAttribute("phoneNumberError", errors.getPhoneNumberError());
                request.setAttribute("yobError", errors.getYobError());
                request.setAttribute("personalIdError", errors.getPersonalIdError());
                request.setAttribute("genderError", errors.getGenderError());
                request.setAttribute("subjectError", errors.getSubjectError());
                request.setAttribute("gradeError", errors.getGradeError());
                request.setAttribute("locationError", errors.getLocationError());
                request.setAttribute("urlError", errors.getUrlError());
                request.setAttribute("contentError", errors.getContentError());
                request.setAttribute("experienceError", errors.getExperienceError());
                request.setAttribute("pictureError", errors.getPictureError());
                request.setAttribute("certificateError", errors.getCertificateError());
                request.getRequestDispatcher("register_insertInfo_tutor.jsp").forward(request, response);
                return;
            }

            Part picturePart = request.getPart("picture");
            Part certificatePart = request.getPart("certificate");
            Part certificatePart2 = request.getPart("certificate2");
            Part certificatePart3 = request.getPart("certificate3");

            if (picturePart == null || picturePart.getSize() == 0) {
                errors.setPictureError("Vui lòng chọn ảnh đại diện.");
            } else {
                errors.checkPicture(picturePart.getSubmittedFileName());
            }

            if (certificatePart == null || certificatePart.getSize() == 0) {
                errors.setCertificateError("Vui lòng chọn chứng chỉ.");
            } else {
                errors.checkCertificate(certificatePart.getSubmittedFileName());
            }

            int yob = Integer.parseInt(yobString);
            int grade = Integer.parseInt(gradeString);
            int experience = Integer.parseInt(experienceString);

            UserDAO userDAO = new UserDAO();
            userDAO.updateAccountName(accountId, name); // Cập nhật tên trong bảng Account

            int tutorId = userDAO.insertTutor(accountId, active);

            // Handle file uploads
            String picturePath = saveAndConvertFile(picturePart, tutorId, TUTOR_SAVE_DIR, "jpg", "tutor");
            String certificatePath = saveAndConvertFile(certificatePart, tutorId, CERTIFICATE_SAVE_DIR, "png", "certificate");
            String certificatePath2 = saveAndConvertFile(certificatePart2, tutorId, CERTIFICATE_SAVE_DIR_2, "png", "certificate2");
            String certificatePath3 = saveAndConvertFile(certificatePart3, tutorId, CERTIFICATE_SAVE_DIR_3, "png", "certificate3");

            // Get a random moderator ID
            int modId = 1;

            UserDTO tutor = new UserDTO(tutorId, modId, phoneNumber, yob, location, personalId, gender, experience, grade, content, url);
            userDAO.insertCV(tutor); // Insert into CV table

            for (String subject : subjects) {
                int subjectId = Integer.parseInt(subject);
                userDAO.insertTutorSubject(tutorId, subjectId); // Insert into TutorSubject table
            }

            response.sendRedirect("tutor_homepage.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input format: " + e.getMessage());
            request.getRequestDispatcher("error11.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error11.jsp").forward(request, response);
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
