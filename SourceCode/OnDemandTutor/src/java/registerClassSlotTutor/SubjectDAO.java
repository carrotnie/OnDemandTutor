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

    private static final String GET_SUBJECTS_BY_ACCOUNT_ID_SQL = "SELECT Subject.Id, Subject.Name "
            + "FROM Subject "
            + "JOIN TutorSubject ON TutorSubject.SubjectId = Subject.Id "
            + "JOIN Tutor ON Tutor.Id = TutorSubject.TutorId "
            + "JOIN Account ON Account.Id = Tutor.AccountId "
            + "WHERE Tutor.AccountId = ? ";

    public List<SubjectDTO> getSubjectsByAccountId(int accountId) throws SQLException, ClassNotFoundException {
        List<SubjectDTO> subjects = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_SUBJECTS_BY_ACCOUNT_ID_SQL)) {
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SubjectDTO subject = new SubjectDTO();
                subject.setId(resultSet.getInt("Id"));
                subject.setName(resultSet.getString("Name"));
                subjects.add(subject);
            }
        }
        return subjects;
    }
}
