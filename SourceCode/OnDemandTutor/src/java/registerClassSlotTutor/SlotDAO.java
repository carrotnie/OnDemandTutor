/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerClassSlotTutor;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Long Dinh
 */
public class SlotDAO {

    private static final String INSERT_SLOT_SQL = "INSERT INTO Slot (ClassId, DayOfSlot, StartTime, EndTime) VALUES (?, ?, ?, ?) ";

    public void addSlot(SlotDTO slotDTO) throws SQLException, ClassNotFoundException {
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_SLOT_SQL)) {
            stmt.setInt(1, slotDTO.getClassId());
            stmt.setNString(2, slotDTO.getDayOfSlot());
            stmt.setString(3, slotDTO.getStartTime());
            stmt.setString(4, slotDTO.getEndTime());
            stmt.executeUpdate();
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
}
