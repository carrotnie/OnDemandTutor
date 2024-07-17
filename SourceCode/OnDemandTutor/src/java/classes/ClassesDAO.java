/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO {

    public List<ClassesDTO> getAll(String subjectId) throws SQLException {
        List<ClassesDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT s.Name as SubjectName, c.*, a.Name, "
                        + "(SELECT COUNT(*) FROM Slot sl "
                        + "LEFT JOIN Schedule sc ON sl.Id = sc.SlotId "
                        + "WHERE sl.ClassId = c.Id AND (sc.Status IS NULL OR sc.Status != N'thành công')) as AvailableSlots "
                        + "FROM Class c "
                        + "LEFT JOIN [Subject] s ON c.SubjectId = s.Id "
                        + "LEFT JOIN Tutor t ON t.Id = c.TutorId "
                        + "LEFT JOIN Account a ON a.Id = t.AccountId "
                        + "WHERE (SELECT COUNT(*) FROM Slot sl "
                        + "LEFT JOIN Schedule sc ON sl.Id = sc.SlotId "
                        + "WHERE sl.ClassId = c.Id AND (sc.Status IS NULL OR sc.Status != N'thành công')) > 0";
                if (subjectId != null && !subjectId.isEmpty()) {
                    sql += " AND c.SubjectId = ?";
                    ptm = conn.prepareStatement(sql);
                    ptm.setString(1, subjectId);
                } else {
                    ptm = conn.prepareStatement(sql);
                }
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int Id = rs.getInt("Id");
                    int AmountOfSlot = rs.getInt("AmountOfSlot");
                    String SubjectName = rs.getString("SubjectName");
                    String Name = rs.getString("Name");
                    String StartDay = rs.getString("StartDay");
                    String EndDay = rs.getString("EndDay");
                    int AvailableSlots = rs.getInt("AvailableSlots");
                    list.add(new ClassesDTO(SubjectName, Id, Name, AmountOfSlot, StartDay, EndDay, AvailableSlots));
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return list;
    }
}
