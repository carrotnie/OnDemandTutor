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

    public int getAmountOfSlotByClassId(int classId) throws SQLException, ClassNotFoundException {
        String GET_AMOUNT_OF_SLOT_SQL = "SELECT AmountOfSlot FROM Class WHERE Id = ?";
        System.out.println("Executing query: " + GET_AMOUNT_OF_SLOT_SQL);
        System.out.println("With parameter: classId = " + classId);

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(GET_AMOUNT_OF_SLOT_SQL)) {
            stmt.setInt(1, classId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int amountOfSlot = rs.getInt(1);
                    System.out.println("Query successful, amountOfSlot: " + amountOfSlot);
                    return amountOfSlot;
                } else {
                    System.out.println("No result found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // or throw an exception if amountOfSlot is not found
    }

    public boolean isTutorApproved(int tutorId) throws SQLException, ClassNotFoundException {
        String query = "SELECT Tutor.Active "
                + "FROM Tutor "
                + "join Account on Account.Id = Tutor.AccountId "
                + "where Tutor.AccountId = ?";
        try (Connection connection = DBUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tutorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return "đã kiểm duyệt".equals(resultSet.getString("Active"));
            }
        }
        return false;
    }
}
