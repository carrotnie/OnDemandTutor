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
            =  "SELECT Account.Id, Account.Name, CV.PhoneNumber, CV.Location, CV.Yob, " +
             "CV.Personal_ID, CV.Gender, CV.Experience, CV.Grade, CV.Url, Tutor.Id AS TutorId " +
             "FROM CV " +
             "JOIN Tutor ON Tutor.Id = CV.TutorId " +
             "JOIN Account ON Account.Id = Tutor.AccountId " +
             "WHERE Tutor.AccountId = ?";

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
    
     private static final String UPDATE_TUTOR_INFO = "UPDATE CV "
            + "SET PhoneNumber = ?, Yob = ?, Location = ?, Personal_ID = ?, Gender = ?, Experience = ?, Grade = ? "
            + "FROM CV "
            + "JOIN Tutor ON Tutor.Id = CV.TutorId "
            + "WHERE Tutor.AccountId = ?";

    private static final String UPDATE_ACCOUNT_NAME = "UPDATE Account "
            + "SET Name = ? "
            + "WHERE Id = ?";

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
                    int tutorId = rs.getInt("TutorId");
                    tutor = new TutorDTO(personalId, gender, experience, grade, phoneNumber, location, yob, accountId, id, url);
                    tutor.setName(name); // Set name from Account table
                    tutor.setTutorId(tutorId);
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

    public boolean updateTutorInfo(String name, String phoneNumber, int yob, String location, String personalId, String gender, int experience, int grade, int accountId) {
        Connection conn = null;
        PreparedStatement ptm1 = null;
        PreparedStatement ptm2 = null;
        boolean isUpdated = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Update CV table
                ptm1 = conn.prepareStatement(UPDATE_TUTOR_INFO);
                ptm1.setString(1, phoneNumber);
                ptm1.setInt(2, yob);
                ptm1.setString(3, location);
                ptm1.setString(4, personalId);
                ptm1.setString(5, gender);
                ptm1.setInt(6, experience);
                ptm1.setInt(7, grade);
                ptm1.setInt(8, accountId);
                
                // Update Account table
                ptm2 = conn.prepareStatement(UPDATE_ACCOUNT_NAME);
                ptm2.setString(1, name);
                ptm2.setInt(2, accountId);

                int rowsUpdated1 = ptm1.executeUpdate();
                int rowsUpdated2 = ptm2.executeUpdate();

                isUpdated = (rowsUpdated1 > 0) && (rowsUpdated2 > 0);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error at TutorDAO: " + e.toString());
        } finally {
            if (ptm1 != null) {
                try {
                    ptm1.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.SEVERE, "Error at TutorDAO: " + e.toString());
                }
            }
            if (ptm2 != null) {
                try {
                    ptm2.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.SEVERE, "Error at TutorDAO: " + e.toString());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.SEVERE, "Error at TutorDAO: " + e.toString());
                }
            }
        }

        return isUpdated;
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
