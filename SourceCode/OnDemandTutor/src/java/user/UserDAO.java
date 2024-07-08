package user;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public UserDTO checkLogin(String Username, String Password) throws SQLException {
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
                    // So sánh tên đăng nhập và mật khẩu phân biệt chữ hoa chữ thường trong mã Java
                    if (Username.equals(dbUsername) && Password.equals(dbPassword)) {
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

    public boolean insert(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ptm.setNString(1, user.getName());
                ptm.setString(2, user.getUsername());
                ptm.setString(3, user.getPassword());
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

    public void insertStudent(UserDTO student) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "INSERT INTO Student (AccountId, Yob, [Location], Gender, PhoneNumber, Grade) VALUES (?, ?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(query);
                stm.setInt(1, student.getAccountId());
                stm.setInt(2, student.getYob());
                stm.setString(3, student.getLocation());
                stm.setString(4, student.getGender());
                stm.setString(5, student.getPhoneNumber());
                stm.setInt(6, student.getGrade());
                stm.executeUpdate();
                
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
    }

}
