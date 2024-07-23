package slot;

import database.DBUtils;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO {

    private static final String GETALL
            = "SELECT s.Id, s.ClassId, s.DayOfSlot, s.StartTime, s.EndTime, s.Price, su.Name AS SubjectName "
            + "FROM Slot s "
            + "LEFT JOIN Class c ON s.ClassId = c.Id "
            + "LEFT JOIN Subject su ON c.SubjectId = su.Id "
            + "WHERE s.Id NOT IN (SELECT SlotId FROM Schedule WHERE Status = 'thành công' OR Status = 'đang xử lý') "
            + "AND s.ClassId = ?";

    private static final String BOOKINGSLOT = "INSERT INTO [dbo].[Schedule] ([StudentId], [SlotId], [Status]) VALUES (?, ?, N'đang xử lý')";

    public List<SlotDTO> getAll(String classId) throws SQLException {
        List<SlotDTO> slots = new ArrayList<>();
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
                    int id = rs.getInt("Id");
                    String day = rs.getString("DayOfSlot");
                    String startTime = rs.getString("StartTime");
                    String endTime = rs.getString("EndTime");
                    String subjectName = rs.getString("SubjectName");
                    BigDecimal price = rs.getBigDecimal("Price");
                    slots.add(new SlotDTO(id, day, startTime, endTime, subjectName, price));
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
        return slots;
    }

    public boolean checkSlotConflict(String slotId, int studentId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean conflict = false;

        String query = "SELECT 1 "
                + "FROM Schedule sc "
                + "JOIN Slot sl ON sc.SlotId = sl.Id "
                + "JOIN Slot slNew ON slNew.Id = ? "
                + "JOIN Class c ON sl.ClassId = c.Id "
                + "JOIN Class cNew ON slNew.ClassId = cNew.Id "
                + "WHERE sc.StudentId = ? "
                + "AND sl.DayOfSlot = slNew.DayOfSlot "
                + "AND c.StartDay <= cNew.EndDay "
                + "AND c.EndDay >= cNew.StartDay "
                + "AND (sl.StartTime < slNew.EndTime AND sl.EndTime > slNew.StartTime)";

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setString(1, slotId);
                ptm.setInt(2, studentId);
                rs = ptm.executeQuery();
                conflict = rs.next();
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
        return conflict;
    }

    public boolean bookingSlot(String slotId, int studentId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean bookingSuccess = false;

        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false); // Start transaction

            if (checkSlotConflict(slotId, studentId)) {
                return false; // Exit if conflict exists
            }

            String getSlotDetails = "SELECT sl.Price, c.TutorId "
                    + "FROM Slot sl "
                    + "JOIN Class c ON sl.ClassId = c.Id "
                    + "WHERE sl.Id = ?";

            ptm = conn.prepareStatement(getSlotDetails);
            ptm.setString(1, slotId);
            rs = ptm.executeQuery();

            if (rs.next()) {
                BigDecimal slotPrice = rs.getBigDecimal("Price");
                int tutorId = rs.getInt("TutorId");

                if (!checkBalance(studentId, slotPrice)) {
                    return false;
                }

                // Deduct balance from student's wallet
                deductBalance(studentId, slotPrice);

                // Add schedule to Schedule table
                ptm = conn.prepareStatement(BOOKINGSLOT);
                ptm.setInt(1, studentId);
                ptm.setString(2, slotId);
                ptm.executeUpdate();

                // Insert data into Salary table
                insertIntoSalary(tutorId, Integer.parseInt(slotId), slotPrice);

                conn.commit(); // Complete transaction
                bookingSuccess = true;
            } else {
                System.out.println("Không tìm thấy Slot hoặc Tutor tương ứng.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback(); // Rollback transaction if an error occurs
            }
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
        return bookingSuccess;
    }

    public boolean checkBalance(int studentId, BigDecimal slotPrice) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean hasEnoughBalance = false;

        String query = "SELECT SUM(Balance) AS TotalBalance FROM Wallet WHERE StudentId = ? AND Status = N'thành công'";

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, studentId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    BigDecimal totalBalance = rs.getBigDecimal("TotalBalance");
                    if (totalBalance == null) {
                        totalBalance = BigDecimal.ZERO; // Set default value to 0 if no balance found
                    }
                    hasEnoughBalance = totalBalance.compareTo(slotPrice) >= 0;
                } else {
                    System.out.println("No results returned from the database.");
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
        return hasEnoughBalance;
    }

    public void deductBalance(int studentId, BigDecimal slotPrice) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String selectQuery = "SELECT Id, Balance FROM Wallet WHERE StudentId = ? AND Status = N'thành công' ORDER BY Id";

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                ptm = conn.prepareStatement(selectQuery);
                ptm.setInt(1, studentId);
                rs = ptm.executeQuery();
                BigDecimal remainingAmount = slotPrice;

                while (rs.next() && remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
                    int walletId = rs.getInt("Id");
                    BigDecimal balance = rs.getBigDecimal("Balance");

                    BigDecimal amountToDeduct = balance.min(remainingAmount);
                    remainingAmount = remainingAmount.subtract(amountToDeduct);

                    String updateQuery = "UPDATE Wallet SET Balance = Balance - ? WHERE Id = ?";
                    try (PreparedStatement updatePtm = conn.prepareStatement(updateQuery)) {
                        updatePtm.setBigDecimal(1, amountToDeduct);
                        updatePtm.setInt(2, walletId);
                        updatePtm.executeUpdate();
                    }
                }
                conn.commit();
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
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

    public void insertIntoSalary(int tutorId, int slotId, BigDecimal balance) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;

        String query = "INSERT INTO Salary (TutorId, SlotId, AdminId, Balance, Status, CourseStatus) VALUES (?, ?, ?, ?, N'đang xử lý', N'chưa hoàn thành')";

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, tutorId);
                ptm.setInt(2, slotId);
                ptm.setInt(3, 1); // AdminId = 1
                ptm.setBigDecimal(4, balance);
                ptm.executeUpdate();
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int getStudentIdByAccountId(int accountId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int studentId = -1;

        String query = "SELECT Id FROM Student WHERE AccountId = ?";

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, accountId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    studentId = rs.getInt("Id");
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
        return studentId;
    }

    public List<Integer> getClassIdsByTutorId(int tutorId) throws SQLException {
        List<Integer> classIds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT c.Id AS ClassId "
                        + "FROM Slot s "
                        + "LEFT JOIN Class c ON s.ClassId = c.Id "
                        + "LEFT JOIN [Subject] su ON c.SubjectId = su.Id "
                        + "WHERE s.Id NOT IN ( "
                        + "    SELECT SlotId "
                        + "    FROM Schedule "
                        + "    WHERE [Status] = N'thành công' OR [Status] = N'đang xử lý' "
                        + ") AND c.TutorId = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, tutorId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int classId = rs.getInt("ClassId");
                    classIds.add(classId);
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
        return classIds;
    }

}
