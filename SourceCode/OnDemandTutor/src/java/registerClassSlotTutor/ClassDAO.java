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
public class ClassDAO {

    private static final String INSERT_CLASS_SQL = "INSERT INTO Class (TutorId, SubjectId, AmountOfSlot, StartDay, EndDay)\n"
            + "VALUES ( "
            + "(SELECT Id FROM Tutor WHERE AccountId = ?), "
            + "?, "
            + "?, "
            + "?, "
            + "? "
            + ") ";

    public void addClass(ClassDTO classDTO) throws SQLException, ClassNotFoundException {
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_CLASS_SQL)) {
            stmt.setInt(1, classDTO.getTutorId());
            stmt.setInt(2, classDTO.getSubjectId());
            stmt.setInt(3, classDTO.getAmountOfSlot());
            stmt.setDate(4, new java.sql.Date(classDTO.getStartDay().getTime()));
            stmt.setDate(5, new java.sql.Date(classDTO.getEndDay().getTime()));
            stmt.executeUpdate();
        }
    }
}
