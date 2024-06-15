/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutor;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Long Dinh
 */
public class TutorDAO {
    public TutorDTO getTeacherByUsername(String username) throws ClassNotFoundException {
        TutorDTO tutor = null;
        String query = "SELECT Account.Name, CV.PhoneNumber, CV.Location, CV.Yob "
                + "FROM CV join Tutor on Tutor.Id = CV.TutorId "
                + "join Account on Account.Id = Tutor.AccountId WHERE AccountId like = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tutor = new TutorDTO();
                tutor.setName(resultSet.getString("Name"));
                tutor.setPhoneNumber(resultSet.getString("PhoneNumber"));
                tutor.setLocation(resultSet.getString("Location"));
                tutor.setYob(resultSet.getInt("Yob"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tutor;
    }
}
