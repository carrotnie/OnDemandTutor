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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Long Dinh
 */
public class TutorDAO {

    private static final Logger LOGGER = Logger.getLogger(TutorDAO.class.getName());
    private static final String GET_TUTOR_BY_ACCOUNT_ID
            = "select a.Id, a.Name, cv.PhoneNumber, cv.Yob, "
            + "cv.Location, cv.Personal_ID, cv.Gender, cv.Experience, cv.Grade, cv.URL "
            + "from CV cv "
            + "join CV_Tutor cvt ON cv.Id = cvt.CVId "
            + "join Tutor t ON cvt.TutorId = t.Id "
            + "join Account a ON t.AccountId = a.Id "
            + "WHERE a.Id = ?";

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
                while (rs.next()) {
                    TutorDTO tutor = new TutorDTO();
                    tutor.setId(rs.getInt("TutorId"));
                    tutor.setName(rs.getString("TutorName"));
                    tutor.setSubjectName(rs.getString("SubjectName"));
                    tutor.setRating(rs.getDouble("AverageRating"));
                    tutors.add(tutor);
                    // Debugging: Print out each tutor to verify data
                    System.out.println("Loaded Tutor: " + tutor.getName() + " with rating: " + tutor.getRating());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

}
