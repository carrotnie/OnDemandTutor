/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedback;

import database.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FeedbackDAO {

    public List<FeedbackDTO> feedbackByStudent(int accountId) throws ClassNotFoundException, SQLException {
        List<FeedbackDTO> feedbackList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT "
                        + "c.StartDay, "
                        + "c.EndDay, "
                        + "a.Name AS TutorName, "
                        + "sub.Name AS SubjectName, "
                        + "sch.StudentId, "
                        + "t.Id AS TutorId, "
                        + "sch.SlotId, "
                        + "c.SubjectId "
                        + "FROM Class c "
                        + "JOIN Slot s ON s.ClassId = c.Id "
                        + "JOIN Schedule sch ON sch.SlotId = s.Id "
                        + "JOIN Tutor t ON c.TutorId = t.Id "
                        + "JOIN Account a ON t.AccountId = a.Id "
                        + "JOIN Subject sub ON c.SubjectId = sub.Id "
                        + "WHERE sch.StudentId = ? AND sch.Status NOT IN (N'thất bại', N'đang xử lý') "
                        + "AND c.EndDay >= ? AND c.EndDay <= ?";

                stm = conn.prepareStatement(query);
                stm.setInt(1, accountId);
                LocalDate today = LocalDate.now();
                stm.setDate(2, Date.valueOf(today.minusWeeks(2)));
                stm.setDate(3, Date.valueOf(today));
                rs = stm.executeQuery();
                while (rs.next()) {
                    FeedbackDTO feedback = new FeedbackDTO();
                    feedback.setStartDay(rs.getDate("StartDay").toLocalDate());
                    feedback.setEndDay(rs.getDate("EndDay").toLocalDate());
                    feedback.setTutorName(rs.getString("TutorName"));
                    feedback.setSubjectName(rs.getString("SubjectName"));
                    feedback.setStudentId(rs.getInt("StudentId"));
                    feedback.setTutorId(rs.getInt("TutorId"));
                    feedback.setSlotId(rs.getInt("SlotId"));
                    feedback.setSubjectId(rs.getInt("SubjectId"));
                    feedbackList.add(feedback);
                }
            }
        } catch (Exception e) {
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
        return feedbackList;
    }

    public static void insertRating(FeedbackDTO rating) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "INSERT INTO Rating (TutorId, StudentId, SubjectId, Rating) VALUES (?, ?, ?, ?)";
                stm = conn.prepareStatement(query);
                stm.setInt(1, rating.getTutorId());
                stm.setInt(2, rating.getStudentId());
                stm.setInt(3, rating.getSubjectId());
                stm.setInt(4, rating.getRating());
                stm.executeUpdate();
                System.out.println("Rating inserted successfully.");
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
    }

    public static void insertFeedback(FeedbackDTO feedback) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "INSERT INTO Feedback (ModId, StudentId, SlotId, TutorId, FeedbackText) VALUES (?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(query);
                stm.setInt(1, feedback.getModId());
                stm.setInt(2, feedback.getStudentId());
                stm.setInt(3, feedback.getSlotId());
                stm.setInt(4, feedback.getTutorId());
                stm.setString(5, feedback.getFeedback());

                stm.executeUpdate();
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
    }
}
