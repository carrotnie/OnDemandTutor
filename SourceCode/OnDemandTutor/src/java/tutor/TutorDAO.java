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

    private static final String GET_TUTOR_BY_ACCOUNT_ID
            = "SELECT Account.Name, CV.PhoneNumber, CV.Location, CV.Yob "
            + "FROM CV JOIN Tutor ON Tutor.Id = CV.TutorId "
            + "JOIN Account ON Account.Id = Tutor.AccountId WHERE Tutor.AccountId = ?";

    private static final Logger LOGGER = Logger.getLogger(TutorDAO.class.getName());

    public TutorDTO getTutorByAccountId(int accountId) throws ClassNotFoundException, SQLException {
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
                    String name = rs.getString("Name");
                    tutor = new TutorDTO(phoneNumber, location, yob, id);
                    tutor.setName(name);

                }
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
        Connection conn = null;
        PreparedStatement ptm = null;

        // Assuming SQL Server syntax for demonstration
        String UPDATE_TUTOR_BY_ACCOUNT_ID = "UPDATE CV "
                + "SET CV.PhoneNumber = ?, CV.Location = ?, CV.Yob = ? "
                + "FROM CV JOIN Tutor ON Tutor.Id = CV.TutorId "
                + "JOIN Account ON Account.Id = Tutor.AccountId "
                + "WHERE Tutor.AccountId = ?; "
                + "UPDATE Account SET Name = ? "
                + "WHERE Account.Id IN (SELECT AccountId FROM Tutor WHERE AccountId = ?);";

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_TUTOR_BY_ACCOUNT_ID);
                ptm.setString(1, tutor.getName());
                ptm.setString(2, tutor.getPhoneNumber());
                ptm.setString(3, tutor.getLocation());
                ptm.setInt(4, tutor.getYob());
                rowUpdated = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating tutor", e);
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
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
