package user;

import database.DBUtils;
import hashing.Hasher;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private static final String LOGIN = "SELECT Id, Name, Role FROM Account WHERE Username=? AND Password=?";
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static final String SEARCH = "SELECT a.Id, a.Name, a.Username, a.Password, a.Role, b.Status, b.AmountOfReport "
            + "FROM Account a LEFT JOIN BanAccount b ON a.Id = b.AccountId "
            + "WHERE a.Username LIKE ? OR a.Role LIKE ?";

    private static final String CHECK_DUPLICATE = "SELECT Name FROM Account WHERE Username=?";
    private static final String INSERT = "INSERT INTO Account(Name,Username,Password,Role) VALUES (?,?,?,?)";
    private static final String CHECK_BAN = "SELECT Status FROM BanAccount WHERE AccountId=?";
    private static final String UPDATE_ACCOUNT_STATUS = "UPDATE BanAccount SET Status = 'lock' WHERE AccountId = ?";
    private static final String UNBAN_ACCOUNT_STATUS = "UPDATE BanAccount SET Status = 'active' WHERE AccountId = ?";

    private static final Map<String, String> otpStorage = new HashMap<>();

    public UserDTO checkLogin(String Username, String Password) throws SQLException, NoSuchAlgorithmException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        PreparedStatement ptmBan = null;
        ResultSet rs = null;
        ResultSet rsBan = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                System.out.println("Connected to the database");
                // Sử dụng LOWER hoặc UPPER để chuyển đổi về cùng một dạng
                ptm = conn.prepareStatement("SELECT Id, Name, Username, Password, Role FROM Account WHERE LOWER(Username) = LOWER(?)");
                ptm.setString(1, Username);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String dbPassword = rs.getString("Password");
                    String dbUsername = rs.getString("Username");
                    String hashedPassword = Hasher.hash(Password);
                    // So sánh tên đăng nhập và mật khẩu phân biệt chữ hoa chữ thường trong mã Java
                    if (Username.equalsIgnoreCase(dbUsername) && hashedPassword.equals(dbPassword)) {
                        int Id = rs.getInt("Id");
                        String Name = rs.getString("Name");
                        String Role = rs.getString("Role");

                        // Kiểm tra xem tài khoản có trong bảng BanAccount và trạng thái của nó
                        ptmBan = conn.prepareStatement(CHECK_BAN);
                        ptmBan.setInt(1, Id);
                        rsBan = ptmBan.executeQuery();
                        if (rsBan.next()) {
                            String status = rsBan.getString("Status");
                            if ("lock".equals(status)) {
                                // Tài khoản bị khóa
                                System.out.println("Account is locked.");
                                return null;
                            }
                        }

                        // Tài khoản không bị khóa
                        user = new UserDTO(Id, Name, Username, "***", Role);
                    } else {
                        System.out.println("Incorrect username or password.");
                    }
                } else {
                    System.out.println("No user found with the provided username.");
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at UserDAO: " + e.toString());
        } finally {
            if (rsBan != null) {
                rsBan.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (ptmBan != null) {
                ptmBan.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<UserDTO> getAllUsers(String search) throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int Id = rs.getInt("Id");
                    String Name = rs.getString("Name");
                    String Username = rs.getString("Username");
                    String Password = rs.getString("Password");
                    String Role = rs.getString("Role");
                    String Status = rs.getString("Status");
                    int AmountOfReport = rs.getInt("AmountOfReport");
                    users.add(new UserDTO(Id, Name, Username, Password, Role, AmountOfReport, Status));
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at UserDAO: " + e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return users;
    }

    public boolean checkDuplicate(String Username) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, Username);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insert(UserDTO user) throws SQLException, NoSuchAlgorithmException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ptm.setString(1, user.getName());
                ptm.setString(2, user.getUsername());
                // Hash the password before inserting
                String hashedPassword = Hasher.hash(user.getPassword());
                ptm.setString(3, hashedPassword);
                ptm.setString(4, user.getRole());
                int rowsAffected = ptm.executeUpdate();
                check = rowsAffected > 0;
                LOGGER.log(Level.INFO, "Rows affected: {0}", rowsAffected);
                // Lấy ID tự động tăng sau khi chèn
                rs = ptm.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1)); // Giả sử ID là kiểu int
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Error at insert: ", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at insert: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateAccountStatus(int accountId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ACCOUNT_STATUS);
                ptm.setInt(1, accountId);
                int result = ptm.executeUpdate();
                if (result > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at UserDAO: " + e.toString());
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateAccountStatusToActive(int accountId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UNBAN_ACCOUNT_STATUS);
                ptm.setInt(1, accountId);
                int result = ptm.executeUpdate();
                if (result > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at UserDAO: " + e.toString());
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static String getUserNameById(int userId) throws SQLException, ClassNotFoundException {
        String name = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Name FROM Account WHERE Id = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    name = rs.getString("Name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return name;
    }

    public void updateAccountName(Integer accountId, String name) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "UPDATE Account SET Name = ? WHERE Id = ?";
                stm = conn.prepareStatement(query);
                stm.setString(1, name);
                stm.setInt(2, accountId);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean checkStudent(int accountId) throws ClassNotFoundException {
        String query = "SELECT s.AccountId AS StudentAccountId "
                + "FROM Account a "
                + "LEFT JOIN Student s ON a.Id = s.AccountId "
                + "WHERE a.Id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, accountId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int insertStudent(UserDTO student) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet generatedKeys = null;
        int studentId = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "INSERT INTO Student (AccountId, Yob, [Location], Gender, PhoneNumber, Grade) VALUES (?, ?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, student.getAccountId());
                stm.setInt(2, student.getYob());
                stm.setString(3, student.getLocation());
                stm.setString(4, student.getGender());
                stm.setString(5, student.getPhoneNumber());
                stm.setInt(6, student.getGrade());
                stm.executeUpdate();

                generatedKeys = stm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    studentId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting student failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return studentId;
    }

    public int insertTutor(Integer accountId, String active) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "INSERT INTO Tutor (AccountId, Active) VALUES (?, ?)";
                stm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, accountId);
                if (active == null || active.trim().isEmpty()) {
                    active = "chưa kiểm duyệt";
                }
                stm.setString(2, active);
                stm.executeUpdate();

                try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating tutor failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    public boolean checkTutor(int accountId) throws ClassNotFoundException {
        String query = "SELECT t.AccountId AS TutorAccountId "
                + "FROM Account a "
                + "LEFT JOIN Tutor t ON a.Id = t.AccountId "
                + "WHERE a.Id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, accountId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertCV(UserDTO tutor) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "INSERT INTO CV (TutorId, ModId, PhoneNumber, Yob, Location, Personal_ID, Gender, Experience, Grade, Content, Url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(query);
                stm.setInt(1, tutor.getTutorId());
                stm.setInt(2, tutor.getModId());
                stm.setString(3, tutor.getPhoneNumber());
                stm.setInt(4, tutor.getYob());
                stm.setString(5, tutor.getLocation());
                stm.setString(6, tutor.getPersonalId());
                stm.setString(7, tutor.getGender());
                stm.setInt(8, tutor.getExperience());
                stm.setInt(9, tutor.getGrade());
                stm.setString(10, tutor.getContent());
                stm.setString(11, tutor.getUrl());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void insertTutorSubject(int tutorId, int subjectId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "INSERT INTO TutorSubject (TutorId, SubjectId) VALUES (?, ?)";
                stm = conn.prepareStatement(query);
                stm.setInt(1, tutorId);
                stm.setInt(2, subjectId);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String getTutorActiveStatus(int accountId) throws Exception, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        String activeStatus = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT Active FROM Tutor WHERE AccountId = ?";
                stm = conn.prepareStatement(query);
                stm.setInt(1, accountId);
                ResultSet rs = stm.executeQuery();

                if (rs.next()) {
                    activeStatus = rs.getString("Active");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return activeStatus;
    }

    public String getReasonForDeny(int tutorId) throws ClassNotFoundException, Exception {
        String reason = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT Reason FROM ReasonDenyCv WHERE TutorId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tutorId);
            rs = ps.executeQuery();

            if (rs.next()) {
                reason = rs.getString("Reason");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return reason;
    }

    //status
    public int getTutorIdByAccountId(int accountId) throws ClassNotFoundException, Exception {
        int tutorId = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT Id FROM Tutor WHERE AccountId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();

            if (rs.next()) {
                tutorId = rs.getInt("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return tutorId;
    }

    //lấy info
    public UserDTO getTutorInfoByAccountId(int accountId) throws Exception, ClassNotFoundException {
        UserDTO tutor = getTutorInfo(accountId);
        if (tutor != null) {
            List<Integer> subjects = getTutorSubjects(accountId);
            tutor.setSubjects(subjects);
        }
        return tutor;
    }

    public UserDTO getTutorInfo(int accountId) throws Exception, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO tutor = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT t.Id AS TutorId, a.Name, cv.PhoneNumber, cv.Yob, cv.Location, cv.Personal_ID, cv.Gender, "
                        + "cv.Experience, cv.Grade, cv.Content, cv.Url, cv.ModId "
                        + "FROM Account a "
                        + "JOIN Tutor t ON a.Id = t.AccountId "
                        + "JOIN CV cv ON t.Id = cv.TutorId "
                        + "WHERE a.Id = ?";
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, accountId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    tutor = new UserDTO();
                    tutor.setTutorId(rs.getInt("TutorId"));  // Lấy tutorId từ kết quả truy vấn
                    tutor.setName(rs.getString("Name"));
                    tutor.setPhoneNumber(rs.getString("PhoneNumber"));
                    tutor.setYob(rs.getInt("Yob"));
                    tutor.setLocation(rs.getString("Location"));
                    tutor.setPersonalId(rs.getString("Personal_ID"));
                    tutor.setGender(rs.getString("Gender"));
                    tutor.setExperience(rs.getInt("Experience"));
                    // Trích xuất phần số từ chuỗi "Grade"
                    String gradeStr = rs.getString("Grade");
                    int grade = extractNumberFromString(gradeStr);
                    tutor.setGrade(grade);
                    tutor.setContent(rs.getString("Content"));
                    tutor.setUrl(rs.getString("Url"));
                    tutor.setModId(rs.getInt("ModId"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return tutor;
    }

    // Phương thức để trích xuất phần số từ chuỗi
    private int extractNumberFromString(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String numberStr = str.replaceAll("[^0-9]", "");  // Loại bỏ tất cả ký tự không phải số
        if (numberStr.isEmpty()) {
            return 0;  // Trường hợp không có số trong chuỗi
        }
        try {
            return Integer.parseInt(numberStr);  // Chuyển đổi chuỗi thành số nguyên
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;  // Trường hợp chuỗi không thể chuyển đổi thành số nguyên
        }
    }

    //
    public List<Integer> getTutorSubjects(int accountId) throws Exception, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<Integer> subjects = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String subjectQuery = "SELECT ts.SubjectId "
                        + "FROM TutorSubject ts "
                        + "JOIN Tutor t ON ts.TutorId = t.Id "
                        + "WHERE t.AccountId = ?";
                ptm = conn.prepareStatement(subjectQuery);
                ptm.setInt(1, accountId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    subjects.add(rs.getInt("SubjectId"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return subjects;
    }

    public void updateTutor(int tutorId, String active) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "UPDATE Tutor SET Active = ? WHERE Id = ?";
                stm = conn.prepareStatement(query);
                if (active == null || active.trim().isEmpty()) {
                    active = "chưa kiểm duyệt";
                }
                stm.setString(1, active);
                stm.setInt(2, tutorId);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateCV(UserDTO tutor) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "UPDATE CV SET ModId = ?, PhoneNumber = ?, Yob = ?, Location = ?, Personal_ID = ?, Gender = ?, Experience = ?, Grade = ?, Content = ?, Url = ? WHERE TutorId = ?";
                stm = conn.prepareStatement(query);
                stm.setInt(1, tutor.getModId());
                stm.setString(2, tutor.getPhoneNumber());
                stm.setInt(3, tutor.getYob());
                stm.setString(4, tutor.getLocation());
                stm.setString(5, tutor.getPersonalId());
                stm.setString(6, tutor.getGender());
                stm.setInt(7, tutor.getExperience());
                stm.setInt(8, tutor.getGrade());
                stm.setString(9, tutor.getContent());
                stm.setString(10, tutor.getUrl());
                stm.setInt(11, tutor.getTutorId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateTutorSubject(int tutorId, List<Integer> subjectIds) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String deleteQuery = "DELETE FROM TutorSubject WHERE TutorId = ?";
                stm = conn.prepareStatement(deleteQuery);
                stm.setInt(1, tutorId);
                stm.executeUpdate();

                String insertQuery = "INSERT INTO TutorSubject (TutorId, SubjectId) VALUES (?, ?)";
                stm = conn.prepareStatement(insertQuery);
                for (int subjectId : subjectIds) {
                    stm.setInt(1, tutorId);
                    stm.setInt(2, subjectId);
                    stm.addBatch();
                }
                stm.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    //forgot password
    public UserDTO getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Id, Name, Username, Password, Role, OTP FROM Account WHERE Username = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, username);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    String password = rs.getString("Password");
                    String role = rs.getString("Role");
                    String otp = rs.getString("OTP");
                    user = new UserDTO(id, name, username, password, role);
                    user.setOtp(otp);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean updateOtp(String username, String otp) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE Account SET OTP = ? WHERE Username = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, otp);
                ptm.setString(2, username);
                int result = ptm.executeUpdate();
                return result > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean updatePassword(String username, String newPassword) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE Account SET Password = ? WHERE Username = ?";
                ptm = conn.prepareStatement(sql);
                // Hash the new password before updating
                String hashedPassword = Hasher.hash(newPassword);
                ptm.setString(1, hashedPassword);
                ptm.setString(2, username);
                int result = ptm.executeUpdate();
                return result > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    // Phương thức tạo OTP ngẫu nhiên
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
