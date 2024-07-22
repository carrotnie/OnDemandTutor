package registerClassSlotTutor;

import database.DBUtils;
import static database.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author Long Dinh
 */
public class SlotDAO {

    private static final String INSERT_SLOT_SQL = "INSERT INTO Slot (ClassId, DayOfSlot, StartTime, EndTime) VALUES (?, ?, ?, ?) ";

    public void addSlot(SlotDTO slot) throws SQLException, ClassNotFoundException {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_SLOT_SQL)) {
            stmt.setInt(1, slot.getClassId());
            stmt.setString(2, slot.getDayOfSlot());
            stmt.setTime(3, slot.getStartTime());
            stmt.setTime(4, slot.getEndTime());
            int rowsInserted = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            throw e;
        }
    }

    public int getClassIdByTutorAccountId(int accountId) throws SQLException, ClassNotFoundException {
        String GET_CLASS_ID_SQL = "SELECT TOP 1 Class.Id "
                + "FROM Class "
                + "JOIN Tutor ON Tutor.Id = Class.TutorId "
                + "WHERE Tutor.AccountId = ? "
                + "ORDER BY Class.Id DESC ";

        System.out.println("Executing query: " + GET_CLASS_ID_SQL);
        System.out.println("With parameter: accountId = " + accountId);

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(GET_CLASS_ID_SQL)) {
            stmt.setInt(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int classId = rs.getInt(1);
                    System.out.println("Query successful, classId: " + classId);
                    return classId;
                } else {
                    System.out.println("No result found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // or throw an exception if classId is not found
    }

    public int countSlotsByClassId(int classId) throws SQLException, ClassNotFoundException {
        String COUNT_SLOTS_SQL = "SELECT COUNT(*) FROM Slot WHERE ClassId = ?";
        System.out.println("Executing query: " + COUNT_SLOTS_SQL);
        System.out.println("With parameter: classId = " + classId);

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(COUNT_SLOTS_SQL)) {

            // Kiểm tra kết nối
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
                return 0;
            }

            stmt.setInt(1, classId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("Query successful, count: " + count);
                    return count;
                } else {
                    System.out.println("No result found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred.");
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isSlotDuplicate(int tutorId, String dayOfSlot, Time startTime, Time endTime) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM Slot "
                + "JOIN Class ON Slot.ClassId = Class.Id "
                + "WHERE Class.TutorId = ? AND DayOfSlot = ? "
                + "AND ((CAST(StartTime AS DATETIME) < CAST(? AS DATETIME) AND CAST(EndTime AS DATETIME) > CAST(? AS DATETIME)) "
                + "OR (CAST(StartTime AS DATETIME) < CAST(? AS DATETIME) AND CAST(EndTime AS DATETIME) > CAST(? AS DATETIME)) "
                + "OR (CAST(StartTime AS DATETIME) >= CAST(? AS DATETIME) AND CAST(EndTime AS DATETIME) <= CAST(? AS DATETIME)))";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tutorId);
            stmt.setString(2, dayOfSlot);
            stmt.setTimestamp(3, new Timestamp(endTime.getTime()));
            stmt.setTimestamp(4, new Timestamp(startTime.getTime()));
            stmt.setTimestamp(5, new Timestamp(endTime.getTime()));
            stmt.setTimestamp(6, new Timestamp(startTime.getTime()));
            stmt.setTimestamp(7, new Timestamp(startTime.getTime()));
            stmt.setTimestamp(8, new Timestamp(endTime.getTime()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
