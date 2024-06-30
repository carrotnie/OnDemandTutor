package student;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {
    private static final String GET_STUDENT_BY_ACCOUNT_ID = 
        "SELECT s.Id, s.AccountId, s.Yob, s.Location, s.Gender, s.Grade, s.PhoneNumber, a.Name " +
        "FROM Student s " +
        "JOIN Account a ON s.AccountId = a.Id " +
        "WHERE s.AccountId=?";
    
    
     //View stu info from schedule
    private static String VIEW_INFO_STU_FROM_SCHEDULE
            = "SELECT Account.Name, Student.PhoneNumber, Student.Location, Student.Gender, Student.Id "
            + "FROM Student "
            + "JOIN Account ON Student.AccountId = Account.Id "
            + "WHERE Student.AccountId = ?";

    
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    public StudentDTO getStudentByAccountId(int accountId) throws SQLException {
        StudentDTO student = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUDENT_BY_ACCOUNT_ID);
                ptm.setInt(1, accountId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("Id");
                    int yob = rs.getInt("Yob");
                    String location = rs.getString("Location");
                    String gender = rs.getString("Gender");
                    int grade = rs.getInt("Grade");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String name = rs.getString("Name");
                    student = new StudentDTO(id, accountId, yob, location, gender, grade, phoneNumber);
                    student.setName(name); // Set name from Account table
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at StudentDAO: " + e.toString());
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return student;
    }

    public void updateStudentInfo(int accountId, String location, String phone, Integer grade) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        StringBuilder query = new StringBuilder("UPDATE Student SET ");
        boolean needComma = false;

        if (location != null && !location.isEmpty()) {
            query.append("Location=?");
            needComma = true;
        }
        if (phone != null && !phone.isEmpty()) {
            if (needComma) query.append(", ");
            query.append("PhoneNumber=?");
            needComma = true;
        }
        if (grade != null) {
            if (needComma) query.append(", ");
            query.append("Grade=?");
        }
        query.append(" WHERE AccountId=?");

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query.toString());
                int paramIndex = 1;
                if (location != null && !location.isEmpty()) {
                    ptm.setString(paramIndex++, location);
                }
                if (phone != null && !phone.isEmpty()) {
                    ptm.setString(paramIndex++, phone);
                }
                if (grade != null) {
                    ptm.setInt(paramIndex++, grade);
                }
                ptm.setInt(paramIndex, accountId);
                ptm.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating student info", e);
            throw e;
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
    }
    
     //View stu info from schedule
    public StudentDTO viewStuInfoFromSchedule(int accountId) throws SQLException {
        StudentDTO student = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_INFO_STU_FROM_SCHEDULE);
                ptm.setInt(1, accountId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String phoneNumber = rs.getString("PhoneNumber");
                    String location = rs.getString("Location");
                    String name = rs.getString("Name");
                    student = new StudentDTO(accountId, location, phoneNumber, name);
                    student.setName(name);
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at StudentDAO: " + e.toString());
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

        return student;
    }
    
}
