/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slot;

import database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SlotDAO {

    private static final String GETALL = "  select s.*,su.Name as SubjectName from Slot s left join Class c on s.ClassId = c.Id left join [Subject] su on c.SubjectId = su.Id \n"
            + "  where s.Id not in (Select SlotId from Schedule where [Status] = N'thành công' or [Status] = N'đang xử lý' ) and s.ClassId = ?";
    private static final String BOOKINGSLOT = "INSERT INTO [dbo].[Schedule]   ([StudentId] ,[SlotId]   ,[Status]) VALUES  ( ? , ? ,N'đang xử lý')";
    private static final String BOOKEDLIST = "  select  sc.Status, sl.DayOfSlot, sl.StartTime, sl.EndTime, s.Name as subjectName, a.Name as teacherName from Schedule sc left join Slot sl on sc.SlotId = sl.Id\n"
            + "	  left join Class c on sl.ClassId = c.Id\n"
            + "	  left join [Subject] s on c.SubjectId = s.Id \n"
            + "	  left join Tutor t on t.Id = c.TutorId \n"
            + "	  left join Account a on a.Id = t.AccountId\n"
            + "	  where sc.StudentId = ?";

    public List<SlotDTO> getAll(String classId) throws SQLException {
        List<SlotDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETALL);
                ptm.setString(1, classId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int Id = rs.getInt("Id");
                    String day = rs.getString("DayOfSlot");
                    String startTime = rs.getString("startTime");
                    String endTime = rs.getString("endTime");
                    String subjectName = rs.getString("subjectName");
                    users.add(new SlotDTO(Id, day, startTime, endTime, subjectName));
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
        return users;
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(new SlotDAO().getAllBookedList(1));
    }
    public List<BookedDTO> getAllBookedList(int id) throws SQLException {
        List<BookedDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(BOOKEDLIST);
                ptm.setInt(1, id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String status = rs.getString(1);
                    String dayOfSlot = rs.getString(2);
                    String endTime = rs.getString(4);
                    String startTime = rs.getString(3);
                    String subjectName = rs.getString(5);
                    String teacherName = rs.getString(6);
                    users.add(new BookedDTO(status, dayOfSlot, startTime, endTime, subjectName, teacherName));
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
        return users;
    }



    public void bookingSlot(String slotId, int StudentId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(BOOKINGSLOT);
                ptm.setInt(1, StudentId);
                ptm.setString(2, slotId);
                ptm.executeUpdate();

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
    }
}
