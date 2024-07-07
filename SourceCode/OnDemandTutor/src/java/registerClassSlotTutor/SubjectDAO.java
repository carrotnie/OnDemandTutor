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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Long Dinh
 */
public class SubjectDAO {

    private static final String GET_SUBJECTS_BY_TUTOR_ID_SQL = "SELECT s.Id, s.Name "
            + "FROM Subject s "
            + "JOIN TutorSubject ts ON s.Id = ts.SubjectId "
            + "join Tutor t on t.Id = ts.TutorId "
            + "join Account a on a.Id = t.AccountId "
            + "WHERE t.AccountId = ? ";

    public List<SubjectDTO> getSubjectsByTutorId(int tutorId) throws SQLException, ClassNotFoundException {
        List<SubjectDTO> subjects = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(GET_SUBJECTS_BY_TUTOR_ID_SQL)) {
            stmt.setInt(1, tutorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    subjects.add(new SubjectDTO(id, name));
                }
            }
        }
        return subjects;
    }
}
