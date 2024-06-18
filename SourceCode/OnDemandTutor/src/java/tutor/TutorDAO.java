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
import java.util.ArrayList;
import java.util.List;

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

    public List<TutorDTO> info() {
        List<TutorDTO> tutors = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                System.out.println("Connection successfully.");
                
                String query = "SELECT t.Id AS TutorId, a.[Name] AS TutorName, s.[Name] AS SubjectName, "
                        + "AVG(r.Rating) AS AverageRating "
                        + "FROM Tutor t "
                        + "JOIN Account a ON t.AccountId = a.Id "
                        + "JOIN TutorSubject ts ON t.Id = ts.TutorId "
                        + "JOIN Subject s ON ts.SubjectId = s.Id "
                        + "LEFT JOIN Rating r ON t.Id = r.TutorId "
                        + "GROUP BY t.Id, a.[Name], s.[Name] "
                        + "HAVING AVG(r.Rating) BETWEEN 7 AND 9 "
                        + // Filter to only include ratings between 7 and 9
                        "ORDER BY AverageRating DESC";
                stm = conn.prepareStatement(query);
                rs = stm.executeQuery();
                while (rs.next()) {
                    TutorDTO tutor = new TutorDTO();
                    tutor.setId(rs.getInt("TutorId"));
                    tutor.setName(rs.getString("TutorName"));
                    tutor.setSubjectName(rs.getString("SubjectName"));
                    tutor.setRating(rs.getDouble("AverageRating"));
                    tutors.add(tutor);
                    // Debugging: Print out each tutor to verify data
                    System.out.println("Loaded Tutor: " + tutor.getName() + " with rating: " + tutor.getRating());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tutors;
    }

}
