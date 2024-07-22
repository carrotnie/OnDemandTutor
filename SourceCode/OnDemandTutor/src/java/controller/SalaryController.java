package controller;

import database.DBUtils;
import payment.PaymentDAO;
import payment.SalaryDTO;
import user.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/SalaryController")
@MultipartConfig
public class SalaryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SalaryController.class.getName());
    private static final String SALARY_SAVE_DIR = "D:\\SWP\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\salary"; // Đường dẫn tuyệt đối

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("transfer".equals(action)) {
            handleTransfer(request, response);
        } else {
            handleDisplaySalaries(request, response);
        }
    }

    private void handleDisplaySalaries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

        if (loginUser == null || !"admin".equals(loginUser.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        int accountId = loginUser.getId();

        try (Connection connection = DBUtils.getConnection()) {
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            Integer adminId = paymentDAO.getAdminIdByAccountId(accountId);

            if (adminId != null) {
                List<SalaryDTO> salaries = paymentDAO.getSalariesByAdminId(adminId);
                request.setAttribute("salaries", salaries);
                request.getRequestDispatcher("admin_payment_tutor.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Admin ID not found for the given account ID.");
                request.getRequestDispatcher("adminPayments.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            request.setAttribute("errorMessage", "An error occurred while processing salary details: " + e.getMessage());
            request.getRequestDispatcher("adminPayments.jsp").forward(request, response);
        }
    }

    private void handleTransfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part imagePart = request.getPart("imageFile");
        int salaryId = Integer.parseInt(request.getParameter("salaryId"));

        if (imagePart == null || imagePart.getSize() == 0) {
            LOGGER.severe("No image part found in the request");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No image uploaded");
            return;
        }

        try {
            String picturePath = saveAndConvertFile(imagePart, salaryId, SALARY_SAVE_DIR, "jpg");
            LOGGER.info("File saved successfully at: " + picturePath);
            
            // Update the salary status to "hoàn thành"
            try (Connection connection = DBUtils.getConnection()) {
                PaymentDAO paymentDAO = new PaymentDAO(connection);
                boolean updateSuccess = paymentDAO.updateSalaryStatusToComplete(salaryId);
                if (updateSuccess) {
                    LOGGER.info("Salary status updated to 'hoàn thành' for SalaryId: " + salaryId);
                } else {
                    LOGGER.warning("Failed to update salary status for SalaryId: " + salaryId);
                }
            }

            response.sendRedirect("SalaryController");
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            LOGGER.severe("Error processing transfer: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing transfer");
        }
    }

    private String saveAndConvertFile(Part part, int salaryId, String saveDir, String extension) throws IOException {
        String fileName = salaryId + "." + extension;
        String filePath = saveDir + File.separator + fileName;
        File file = new File(filePath);
        LOGGER.info("Attempting to save file to: " + file.getAbsolutePath());
        if (!file.getParentFile().exists()) {
            boolean dirCreated = file.getParentFile().mkdirs();
            if (!dirCreated) {
                LOGGER.severe("Failed to create directory: " + saveDir);
                throw new IOException("Failed to create directory: " + saveDir);
            }
        }
        LOGGER.info("Saving file to: " + file.getAbsolutePath());
        try (InputStream input = part.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("File saved successfully: " + file.getAbsolutePath());
        } catch (IOException ex) {
            LOGGER.severe("Error saving file: " + ex.getMessage());
            throw ex;
        }
        return filePath;
    }

    @Override
    public String getServletInfo() {
        return "SalaryController handles fetching, displaying salary details and uploading transfer images.";
    }
}
