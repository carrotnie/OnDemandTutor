package schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleDAO {
    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(ScheduleDAO.class.getName());

    public ScheduleDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ScheduleDTO> getSchedulesByStudentId(int studentId) throws SQLException {
        List<ScheduleDTO> schedules = new ArrayList<>();

        String query = "SELECT " +
                       "s.Id AS SlotId, " +
                       "c.StartDay, " +
                       "c.EndDay, " +
                       "s.DayOfSlot, " +
                       "s.StartTime, " +
                       "s.EndTime, " +
                       "a.Name AS TutorName, " +
                       "sch.Status, " +
                       "sub.Name AS SubjectName, " +
                       "a.Id AS AccountId " +  // Added AccountId here
                       "FROM Slot s " +
                       "JOIN Class c ON s.ClassId = c.Id " +
                       "JOIN Tutor t ON c.TutorId = t.Id " +
                       "JOIN Account a ON t.AccountId = a.Id " +
                       "JOIN Schedule sch ON sch.SlotId = s.Id " +
                       "JOIN Subject sub ON c.SubjectId = sub.Id " +
                       "WHERE sch.StudentId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ScheduleDTO schedule = new ScheduleDTO();
                    schedule.setSlotId(rs.getInt("SlotId"));
                    schedule.setStartDay(rs.getDate("StartDay").toLocalDate());
                    schedule.setEndDay(rs.getDate("EndDay").toLocalDate());
                    schedule.setDayOfSlot(rs.getString("DayOfSlot"));
                    schedule.setStartTime(rs.getTime("StartTime").toLocalTime());
                    schedule.setEndTime(rs.getTime("EndTime").toLocalTime());
                    schedule.setTutorName(rs.getString("TutorName"));
                    schedule.setStatus(rs.getString("Status"));
                    schedule.setSubjectName(rs.getString("SubjectName"));
                    schedule.setAccountId(rs.getInt("AccountId")); // Added AccountId field
                    schedules.add(schedule);
                }
            }
        }

        return schedules;
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
}
