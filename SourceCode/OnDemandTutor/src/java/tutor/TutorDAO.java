/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutor;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Long Dinh
 */
public class TutorDAO {

    private static final Logger LOGGER = Logger.getLogger(TutorDAO.class.getName());
    private static final String GET_TUTOR_BY_ACCOUNT_ID
            =  "select Account.Id, Account.Name, CV.PhoneNumber, CV.Location, CV.Yob, CV.Personal_ID, CV.Gender,CV.Experience,CV.Grade, CV.Url "
            + "from CV "
            + "join Tutor  ON Tutor.Id = CV.TutorId "
            + "join Account ON Account.Id = Tutor.AccountId "
            + "WHERE Tutor.AccountId = ? ";

    private static final String GET_SCHEDULES_BY_ACCOUNT_ID
            = "SELECT c.StartDay AS startDay, s.StartTime AS startTime, sub.Name AS SubjectName, "
            + "a_st.Name AS StudentName, sch.Status, st.AccountId, sch.SlotId "
            + "FROM Schedule sch "
            + "JOIN Slot s ON sch.SlotId = s.Id "
            + "JOIN Class c ON s.ClassId = c.Id "
            + "JOIN Subject sub ON c.SubjectId = sub.Id "
            + "JOIN Student st ON sch.StudentId = st.Id "
            + "JOIN Account a_st ON st.AccountId = a_st.Id "
            + "JOIN Tutor t ON c.TutorId = t.Id "
            + "JOIN Account a_t ON t.AccountId = a_t.Id "
            + "WHERE a_t.Id = ? ";

    public TutorDTO getTutorByAccountId(int accountId) throws SQLException {
        TutorDTO tutor = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TUTOR_BY_ACCOUNT_ID);
                ptm.setInt(1, accountId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("Id");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String location = rs.getString("Location");
                    int yob = rs.getInt("Yob");
                    String personalId = rs.getString("Personal_ID");
                    String gender = rs.getString("Gender");
                    int experience = rs.getInt("Experience");
                    int grade = rs.getInt("Grade");
                    String name = rs.getString("Name");
                    String url = rs.getString("url");
                    tutor = new TutorDTO(personalId, gender, experience, grade, phoneNumber, location, yob, accountId, id, url);
                    tutor.setName(name); // Set name from Account table
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at TutorDAO: " + e.toString());
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

    //Trang Student_Homepage
    public List<TutorDTO> info() {
        List<TutorDTO> tutors = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                System.out.println("Connection successfully.");

                String query = "SELECT t.Id AS TutorId, a.[Name] AS TutorName, s.[Name] AS SubjectName, "
                        + "AVG(r.Rating) AS AverageRating "
                        + "FROM Tutor t "
                        + "JOIN Account a ON t.AccountId = a.Id "
                        + "JOIN TutorSubject ts ON t.Id = ts.TutorId "
                        + "JOIN Subject s ON ts.SubjectId = s.Id "
                        + "LEFT JOIN Rating r ON t.Id = r.TutorId "
                        + "GROUP BY t.Id, a.[Name], s.[Name] "
                        + "HAVING AVG(r.Rating) BETWEEN 7 AND 9 "
                        + // Filter to only include ratings between 7 and 9
                        "ORDER BY AverageRating DESC";
                stm = conn.prepareStatement(query);
                rs = stm.executeQuery();
                Map<Integer, TutorDTO> tutorMap = new LinkedHashMap<>();
                while (rs.next()) {
                    int tutorId = rs.getInt("TutorId");
                    TutorDTO tutor = tutorMap.get(tutorId);
                    if (tutor == null) {
                        tutor = new TutorDTO();
                        tutor.setId(tutorId);
                        tutor.setName(rs.getString("TutorName"));
                        tutor.setRating(rs.getDouble("AverageRating"));
                        tutor.setSubjects(new ArrayList<>());
                        tutorMap.put(tutorId, tutor);
                    }
                    tutor.getSubjects().add(rs.getString("SubjectName"));
                }
                tutors.addAll(tutorMap.values());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stm != null) try { stm.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return tutors;
    }

    public boolean updateTutor(TutorDTO tutor) throws ClassNotFoundException, SQLException {
        boolean rowUpdated = false;
        Connection connection = null;
        PreparedStatement ptmCV = null;
        PreparedStatement ptmAccount = null;

        String UPDATE_CV = "UPDATE CV "
                + "SET PhoneNumber = ?, Location = ?, Yob = ?, PersonalId = ?, Gender = ?, Experience = ? "
                + "FROM CV JOIN Tutor ON Tutor.Id = CV.TutorId "
                + "JOIN Account ON Account.Id = Tutor.AccountId "
                + "WHERE Tutor.AccountId = ?";

        String UPDATE_ACCOUNT = "UPDATE Account SET Name = ? "
                + "WHERE Id = ?";

        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Update CV
            ptmCV = connection.prepareStatement(UPDATE_CV);
            ptmCV.setString(1, tutor.getPhoneNumber());
            ptmCV.setString(2, tutor.getLocation());
            ptmCV.setInt(3, tutor.getYob());
            ptmCV.setString(4, tutor.getPersonalId());
            ptmCV.setString(5, tutor.getGender());
            ptmCV.setInt(6, tutor.getExperience());
            ptmCV.setInt(7, tutor.getAccountId());
            ptmCV.executeUpdate();

            // Update Account
            ptmAccount = connection.prepareStatement(UPDATE_ACCOUNT);
            ptmAccount.setString(1, tutor.getName());
            ptmAccount.setInt(2, tutor.getAccountId());
            ptmAccount.executeUpdate();

            connection.commit(); // Commit transaction
            rowUpdated = true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (ptmCV != null) {
                ptmCV.close();
            }
            if (ptmAccount != null) {
                ptmAccount.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true); // Reset auto-commit to true
                connection.close();
            }
        }
        return rowUpdated;
    }

    //Trang Student_HomePage (search tutor)
    public List<TutorDTO> searchTutors(String search) throws SQLException, ClassNotFoundException {
        List<TutorDTO> tutors = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT t.Id AS TutorId, a.[Name] AS TutorName, "
                        + "STRING_AGG(s.[Name], ', ') AS SubjectName, "
                        + "COALESCE(AVG(r.Rating), 0) AS AverageRating "
                        + "FROM Tutor t "
                        + "JOIN Account a ON t.AccountId = a.Id "
                        + "JOIN TutorSubject ts ON t.Id = ts.TutorId "
                        + "JOIN Subject s ON ts.SubjectId = s.Id "
                        + "LEFT JOIN Rating r ON t.Id = r.TutorId "
                        + "WHERE a.[Name] LIKE ? "
                        + "GROUP BY t.Id, a.[Name] "
                        + "ORDER BY AverageRating DESC";

                // Chuẩn bị câu lệnh với điều kiện tìm kiếm
                stm = conn.prepareStatement(query);
                stm.setString(1, "%" + search + "%");

                // Thực thi câu lệnh SQL
                rs = stm.executeQuery();

                // Duyệt qua kết quả trả về
                while (rs.next()) {
                    TutorDTO tutor = new TutorDTO();
                    tutor.setId(rs.getInt("TutorId"));
                    tutor.setName(rs.getString("TutorName"));
                    tutor.setSubjectName(rs.getString("SubjectName"));
                    tutor.setRating(rs.getDouble("AverageRating"));
                    tutors.add(tutor);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        // Trả về danh sách các gia sư
        return tutors;
    }

     //Trang Student_HomePage(detail)
    public TutorDTO getTutorById(String id) throws ClassNotFoundException, SQLException {
        TutorDTO tutor = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT t.Id AS TutorId, a.[Name] AS TutorName, "
                        + "STRING_AGG(s.[Name], ', ') AS SubjectName, "
                        + "COALESCE(AVG(r.Rating), 0) AS AverageRating, "
                        + "cv.[Location] AS Location, "
                        + "cv.[Url] AS YoutubeUrl "
                        + "FROM Tutor t "
                        + "JOIN Account a ON t.AccountId = a.Id "
                        + "JOIN TutorSubject ts ON t.Id = ts.TutorId "
                        + "JOIN Subject s ON ts.SubjectId = s.Id "
                        + "LEFT JOIN Rating r ON t.Id = r.TutorId "
                        + "LEFT JOIN CV cv ON t.Id = cv.Id " // Assuming TutorId matches CV Id
                        + "WHERE t.Id = ? "
                        + "GROUP BY t.Id, a.[Name], cv.[Location], cv.[Url]";

                stm = conn.prepareStatement(query);
                stm.setString(1, id);
                rs = stm.executeQuery();

                if (rs.next()) {
                    tutor = new TutorDTO();
                    tutor.setId(rs.getInt("TutorId"));
                    tutor.setName(rs.getString("TutorName"));
                    tutor.setSubjectName(rs.getString("SubjectName"));
                    tutor.setRating(rs.getDouble("AverageRating"));
                    tutor.setYoutubeUrl(rs.getString("YoutubeUrl"));
                    tutor.setLocation(rs.getString("Location"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return tutor;
    }

    public List<ScheduleDTO> getSchedulesByAccountId(int accountId) throws SQLException, ClassNotFoundException {
        List<ScheduleDTO> schedules = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SCHEDULES_BY_ACCOUNT_ID);
                ptm.setInt(1, accountId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String startDay = rs.getString("startDay");
                    String startTime = rs.getString("startTime");
                    String subjectName = rs.getString("SubjectName");
                    String studentName = rs.getString("StudentName");
                    String status = rs.getString("Status");
                    int studentAccountId = rs.getInt("AccountID");
                    int slotId = rs.getInt("SlotId");
                    schedules.add(new ScheduleDTO(startDay, startTime, subjectName, studentName, status, studentAccountId, slotId));
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at ScheduleDAO: " + e.toString());
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
        return schedules;
    }
    
    public boolean updateScheduleStatus(int slotId, String status) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE Schedule SET Status= ? WHERE SlotId= ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1, status);
                ps.setInt(2, slotId);
                result = ps.executeUpdate() > 0;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
}
