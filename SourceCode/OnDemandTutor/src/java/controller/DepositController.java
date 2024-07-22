package controller;
import database.DBUtils;
import payment.PaymentDAO;

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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DepositController", urlPatterns = {"/DepositController"})
@MultipartConfig
public class DepositController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DepositController.class.getName());
    private static final String PAYMENT_SAVE_DIR = "D:\\SWP\\OnDemandTutor\\SourceCode\\OnDemandTutor\\web\\img\\payment"; // Đường dẫn tuyệt đối

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            LOGGER.severe("No accountId found in session");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No accountId found in session");
            return;
        }

        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        Part imagePart = request.getPart("image");

        if (imagePart == null || imagePart.getSize() == 0) {
            LOGGER.severe("No image part found in the request");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No image uploaded");
            return;
        }

        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            int studentId = paymentDAO.getStudentIdByAccountId(accountId);

            // Insert wallet entry and get walletId
            int walletId = paymentDAO.insertWalletEntry(accountId, amount);

            // Đường dẫn để lưu trữ hình ảnh
            String picturePath = saveAndConvertFile(imagePart, walletId, PAYMENT_SAVE_DIR, "jpg", "payment");

            response.sendRedirect("ViewBalanceController");
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error processing deposit", ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error saving file", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving file");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error closing connection", ex);
                }
            }
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
        return "DepositController";
    }

    private String saveAndConvertFile(Part part, int id, String saveDir, String extension, String prefix) throws IOException {
        String fileName = id + "." + extension;
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
}
