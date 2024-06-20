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
import subject.SubjectDTO;

/**
 *
 * @author Admin
 */
public class ClassesDAO {

    private static final String GETALL = "  select s.Name as SubjectName, c.*, a.Name from Class c left join [Subject] s on c.SubjectId = s.Id left join Tutor t on t.Id = c.TutorId left join Account a on a.Id = t.AccountId";
    
    public List<ClassesDTO> getAll(String subjectId) throws SQLException {
        List<ClassesDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GETALL;
                if (subjectId != "") {
                    sql += " where SubjectId = " + subjectId;
                }
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int Id = rs.getInt("Id");
                    int AmountOfSlot = rs.getInt("AmountOfSlot");
                    String SubjectName = rs.getString("SubjectName");
                    String Name = rs.getString("Name");
                    String StartDay = rs.getString("StartDay");
                    String EndDay = rs.getString("EndDay");
                    list.add(new ClassesDTO(SubjectName, Id, Name, AmountOfSlot, StartDay, EndDay));
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
