/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerClassSlotTutor;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Long Dinh
 */
public class SlotDAO {

    private static final String INSERT_SLOT_SQL = "INSERT INTO Slot (ClassId, DayOfSlot, StartTime, EndTime) "
            + "values ( "
            + "(SELECT TOP 1 Class.Id "
            + "FROM Class "
            + "join Tutor on Tutor.Id = Class.TutorId "
            + "WHERE Tutor.AccountId = ?), "
            + "? , ?, ?) ";

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
}
