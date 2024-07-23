package timetable;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeTableDAO {

    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(TimeTableDAO.class.getName());

    public TimeTableDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TimeTableDTO> getTimeTablesByStudentId(int studentId) throws SQLException {
        List<TimeTableDTO> timeTables = new ArrayList<>();

        String query = "SELECT "
                + "st.AccountId, "
                + "sc.StudentId, "
                + "sl.Id AS SlotId, " // Ensure SlotId is selected
                + "sl.StartTime, "
                + "sl.EndTime, "
                + "sl.DayOfSlot, "
                + "c.StartDay, "
                + "c.EndDay, "
                + "t.Id AS TutorId, " // Ensure TutorId is selected
                + "a.[Name] AS TutorName, "
                + "sub.[Name] AS SubjectName "
                + "FROM Schedule sc "
                + "JOIN Slot sl ON sc.SlotId = sl.Id "
                + "JOIN Class c ON sl.ClassId = c.Id "
                + "JOIN Tutor t ON c.TutorId = t.Id "
                + "JOIN Account a ON t.AccountId = a.Id "
                + "JOIN Subject sub ON c.SubjectId = sub.Id "
                + "JOIN Student st ON sc.StudentId = st.Id "
                + "WHERE sc.StudentId = ? AND sc.status ='thành công'";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TimeTableDTO timeTable = new TimeTableDTO();
                    timeTable.setAccountId(rs.getInt("AccountId"));
                    timeTable.setStudentId(rs.getInt("StudentId"));
                    timeTable.setSlotId(rs.getInt("SlotId")); // Set SlotId
                    timeTable.setTutorId(rs.getInt("TutorId")); // Set TutorId
                    timeTable.setStartDay(rs.getDate("StartDay"));
                    timeTable.setEndDay(rs.getDate("EndDay"));
                    timeTable.setStartTime(rs.getTime("StartTime"));
                    timeTable.setEndTime(rs.getTime("EndTime"));
                    timeTable.setDayOfSlot(rs.getString("DayOfSlot"));
                    timeTable.setTutorName(rs.getString("TutorName"));
                    timeTable.setSubjectName(rs.getString("SubjectName"));
                    timeTables.add(timeTable);
                }
            }
        }

        return timeTables;
    }

    public Integer getStudentIdByAccountId(int accountId) throws SQLException {
        String query = "SELECT Id FROM Student WHERE AccountId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Id");
                } else {
                    LOGGER.log(Level.WARNING, "No student found for AccountId: {0}", accountId);
                    return null;
                }
            }
        }
    }

    public static void insertComplaint(TimeTableDTO complaint) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "INSERT INTO Complaint (StudentId, SlotId, TutorId, ModId, Content) VALUES (?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(query);

                stm.setInt(1, complaint.getStudentId());
                stm.setInt(2, complaint.getSlotId());
                stm.setInt(3, complaint.getTutorId());
                stm.setInt(4, complaint.getModId());  // Correct index here
                stm.setString(5, complaint.getContent());  // Correct index here

                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error inserting complaint: " + e.getMessage(), e);
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
