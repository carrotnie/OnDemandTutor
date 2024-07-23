package mod;

import database.DBUtils;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModDAO {

    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(ModDAO.class.getName());

    public ModDAO(Connection connection) {
        this.connection = connection;
    }

    public List<FeedbackDTO> getFeedbackByModId(int modId) throws SQLException {
        List<FeedbackDTO> feedBacks = new ArrayList<>();

        String query = "SELECT "
                + "f.Id AS feedbackId, "
                + "ma.Id AS accountId, "
                + "f.ModId AS modId, "
                + "s.Id AS studentId, "
                + "sa.Name AS studentName, "
                + "t.Id AS tutorId, "
                + "ta.Name AS tutorName, "
                + "f.FeedbackText "
                + "FROM Feedback f "
                + "JOIN Student s ON f.StudentId = s.Id "
                + "JOIN Account sa ON s.AccountId = sa.Id "
                + "JOIN Tutor t ON f.TutorId = t.Id "
                + "JOIN Account ta ON t.AccountId = ta.Id "
                + "JOIN Moderator m ON f.ModId = m.Id "
                + "JOIN Account ma ON m.AccountId = ma.Id "
                + "WHERE f.ModId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, modId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FeedbackDTO feedback = new FeedbackDTO();
                    feedback.setAccountId(rs.getInt("accountId"));
                    feedback.setFeedbackId(rs.getInt("feedbackId"));
                    feedback.setStudentId(rs.getInt("studentId"));
                    feedback.setModId(rs.getInt("modId"));
                    feedback.setTutorId(rs.getInt("tutorId"));
                    feedback.setStudentName(rs.getString("studentName"));
                    feedback.setTutorName(rs.getString("tutorName"));
                    feedback.setFeedBackText(rs.getString("FeedbackText"));
                    feedBacks.add(feedback);
                }
            }
        }

        return feedBacks;
    }

    public Integer getModIdByAccountId(int accountId) throws SQLException {
        String query = "SELECT Id FROM Moderator WHERE AccountId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Id");
                } else {
                    LOGGER.log(Level.WARNING, "No Moderator found for AccountId: {0}", accountId);
                    return null;
                }
            }
        }
    }

    public boolean deleteFeedback(int feedbackId) throws SQLException {
        String query = "DELETE FROM Feedback WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, feedbackId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<ReportDTO> getReportByModId(int modId) throws SQLException {
        List<ReportDTO> reports = new ArrayList<>();

        String query = "SELECT c.Id AS complaintId, m.AccountId AS modAccountId, m.Id AS modId, "
                + "s.Id AS studentId, t.Id AS tutorId, a_s.Name AS studentName, "
                + "a_t.Name AS tutorName, c.Content, c.Status, c.SlotId, sl.Price "
                + "FROM Complaint c "
                + "INNER JOIN Student s ON c.StudentId = s.Id "
                + "INNER JOIN Tutor t ON c.TutorId = t.Id "
                + "INNER JOIN Moderator m ON c.ModId = m.Id "
                + "INNER JOIN Account a_s ON s.AccountId = a_s.Id "
                + "INNER JOIN Account a_t ON t.AccountId = a_t.Id "
                + "INNER JOIN Slot sl ON c.SlotId = sl.Id "
                + "WHERE m.Id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, modId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ReportDTO report = new ReportDTO();
                    report.setComplaintId(rs.getInt("complaintId"));
                    report.setAccountId(rs.getInt("modAccountId"));
                    report.setModId(rs.getInt("modId"));
                    report.setStudentId(rs.getInt("studentId"));
                    report.setTutorId(rs.getInt("tutorId"));
                    report.setSlotId(rs.getInt("SlotId"));
                    report.setStudentName(rs.getString("studentName"));
                    report.setTutorName(rs.getString("tutorName"));
                    report.setContent(rs.getString("Content"));
                    report.setStatus(rs.getString("Status"));
                    report.setPrice(rs.getBigDecimal("Price"));
                    reports.add(report);
                }
            }
        }

        return reports;
    }

    public List<ReportDTO> getReportByModIdAndStatus(int modId, String status) throws SQLException {
        List<ReportDTO> reports = new ArrayList<>();

        String query = "SELECT "
                + "c.Id AS complaintId, "
                + "m.AccountId AS modAccountId, "
                + "m.Id AS modId, "
                + "s.Id AS studentId, "
                + "t.Id AS tutorId, "
                + "a_s.Name AS studentName, "
                + "a_t.Name AS tutorName, "
                + "c.Content, "
                + "c.Status, "
                + "c.SlotId "
                + "FROM Complaint c "
                + "INNER JOIN Student s ON c.StudentId = s.Id "
                + "INNER JOIN Tutor t ON c.TutorId = t.Id "
                + "INNER JOIN Moderator m ON c.ModId = m.Id "
                + "INNER JOIN Account a_s ON s.AccountId = a_s.Id "
                + "INNER JOIN Account a_t ON t.AccountId = a_t.Id "
                + "WHERE m.Id = ? AND c.Status = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, modId);
            stmt.setString(2, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ReportDTO report = new ReportDTO();
                    report.setComplaintId(rs.getInt("complaintId"));
                    report.setAccountId(rs.getInt("modAccountId"));
                    report.setModId(rs.getInt("modId"));
                    report.setStudentId(rs.getInt("studentId"));
                    report.setTutorId(rs.getInt("tutorId"));
                    report.setSlotId(rs.getInt("SlotId"));
                    report.setStudentName(rs.getString("studentName"));
                    report.setTutorName(rs.getString("tutorName"));
                    report.setContent(rs.getString("Content"));
                    report.setStatus(rs.getString("Status"));
                    reports.add(report);
                }
            }
        }

        return reports;
    }

    public void updateComplaintStatus(int complaintId, String status) throws SQLException {
        String query = "UPDATE Complaint SET Status = ? WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, complaintId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Complaint status updated. Rows affected: " + rowsAffected);
        }
    }

    public void updateScheduleStatus(int slotId, String status) throws SQLException {
        String query = "UPDATE Schedule SET Status = ? WHERE SlotId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, slotId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Schedule status updated. Rows affected: " + rowsAffected);
        }
    }

    public void incrementTutorReportCount(int tutorId) throws SQLException {
        String getAccountIdQuery = "SELECT AccountId FROM Tutor WHERE Id = ?";
        String getReportCountQuery = "SELECT AmountOfReport FROM BanAccount WHERE AccountId = ?";
        String incrementReportCountQuery = "UPDATE BanAccount SET AmountOfReport = AmountOfReport + 1 WHERE AccountId = ?";

        try (PreparedStatement getAccountIdStmt = connection.prepareStatement(getAccountIdQuery)) {
            getAccountIdStmt.setInt(1, tutorId);

            try (ResultSet rs = getAccountIdStmt.executeQuery()) {
                if (rs.next()) {
                    int accountId = rs.getInt("AccountId");

                    try (PreparedStatement getReportCountStmt = connection.prepareStatement(getReportCountQuery)) {
                        getReportCountStmt.setInt(1, accountId);
                        try (ResultSet rsCount = getReportCountStmt.executeQuery()) {
                            if (rsCount.next()) {
                                int currentReportCount = rsCount.getInt("AmountOfReport");

                                // Assume the constraint is that AmountOfReport must be <= 5
                                if (currentReportCount < 5) {
                                    try (PreparedStatement incrementReportCountStmt = connection.prepareStatement(incrementReportCountQuery)) {
                                        incrementReportCountStmt.setInt(1, accountId);
                                        int rowsAffected = incrementReportCountStmt.executeUpdate();
                                        LOGGER.info("Tutor report count incremented. Rows affected: " + rowsAffected);
                                    }
                                } else {
                                    LOGGER.warning("Cannot increment AmountOfReport as it would violate the constraint for AccountId: " + accountId);
                                }
                            }
                        }
                    }
                } else {
                    LOGGER.warning("No AccountId found for TutorId: " + tutorId);
                }
            }
        }
    }

    public List<CvDTO> getTutorDataByActiveStatusAndModId(String activeStatus, int modId) throws SQLException {
        List<CvDTO> tutorDataList = new ArrayList<>();

        String query = "SELECT "
                + "m.AccountId AS modAccountId, "
                + "m.Id AS modId, "
                + "t.Id AS tutorId, "
                + "c.Id AS cvId, "
                + "t.Active, "
                + "a.Name AS tutorName, "
                + "c.PhoneNumber, "
                + "c.Yob, "
                + "c.Location, "
                + "c.Personal_ID AS personalId, "
                + "c.Gender, "
                + "c.Experience, "
                + "c.Grade, "
                + "c.CreateTime, "
                + "c.Content, "
                + "c.Url "
                + "FROM "
                + "CV c "
                + "JOIN Tutor t ON c.TutorId = t.Id "
                + "JOIN Moderator m ON c.ModId = m.Id "
                + "JOIN Account a ON t.AccountId = a.Id "
                + "WHERE "
                + "t.Active = N'chưa kiểm duyệt' AND "
                + "m.Id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, modId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CvDTO tutorData = new CvDTO();
                    tutorData.setModId(rs.getInt("modId"));
                    tutorData.setModAccountId(rs.getInt("modAccountId"));
                    tutorData.setTutorId(rs.getInt("tutorId"));
                    tutorData.setCvId(rs.getInt("cvId"));
                    tutorData.setActive(rs.getString("Active"));
                    tutorData.setTutorName(rs.getString("tutorName"));
                    tutorData.setPhoneNumber(rs.getString("PhoneNumber"));
                    tutorData.setYob(rs.getInt("Yob"));
                    tutorData.setLocation(rs.getString("Location"));
                    tutorData.setPersonalId(rs.getString("personalId"));
                    tutorData.setGender(rs.getString("Gender"));
                    tutorData.setExperience(rs.getInt("Experience"));
                    tutorData.setGrade(rs.getString("Grade"));
                    tutorData.setCreateTime(rs.getDate("CreateTime"));
                    tutorData.setContent(rs.getString("Content"));
                    tutorData.setUrl(rs.getString("Url"));
                    tutorDataList.add(tutorData);
                }
            }
        }
        return tutorDataList;
    }

    public List<CvDTO> getTutorCheckedByActiveStatusAndModId(String activeStatus, int modId) throws SQLException {
        List<CvDTO> tutorDataList = new ArrayList<>();

        String query = "SELECT "
                + "m.AccountId AS modAccountId, "
                + "m.Id AS modId, "
                + "t.Id AS tutorId, "
                + "c.Id AS cvId, "
                + "t.Active, "
                + "a.Name AS tutorName, "
                + "c.PhoneNumber, "
                + "c.Yob, "
                + "c.Location, "
                + "c.Personal_ID AS personalId, "
                + "c.Gender, "
                + "c.Experience, "
                + "c.Grade, "
                + "c.CreateTime, "
                + "c.Content, "
                + "c.Url "
                + "FROM "
                + "CV c "
                + "JOIN Tutor t ON c.TutorId = t.Id "
                + "JOIN Moderator m ON c.ModId = m.Id "
                + "JOIN Account a ON t.AccountId = a.Id "
                + "WHERE "
                + "t.Active = N'đã kiểm duyệt' AND "
                + "m.Id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, modId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CvDTO tutorData = new CvDTO();
                    tutorData.setModId(rs.getInt("modId"));
                    tutorData.setModAccountId(rs.getInt("modAccountId"));
                    tutorData.setTutorId(rs.getInt("tutorId"));
                    tutorData.setCvId(rs.getInt("cvId"));
                    tutorData.setActive(rs.getString("Active"));
                    tutorData.setTutorName(rs.getString("tutorName"));
                    tutorData.setPhoneNumber(rs.getString("PhoneNumber"));
                    tutorData.setYob(rs.getInt("Yob"));
                    tutorData.setLocation(rs.getString("Location"));
                    tutorData.setPersonalId(rs.getString("personalId"));
                    tutorData.setGender(rs.getString("Gender"));
                    tutorData.setExperience(rs.getInt("Experience"));
                    tutorData.setGrade(rs.getString("Grade"));
                    tutorData.setCreateTime(rs.getDate("CreateTime"));
                    tutorData.setContent(rs.getString("Content"));
                    tutorData.setUrl(rs.getString("Url"));
                    tutorDataList.add(tutorData);
                }
            }
        }
        return tutorDataList;
    }

    public List<CvDTO> getTutorRejectedByActiveStatusAndModId(String activeStatus, int modId) throws SQLException {
        List<CvDTO> tutorDataList = new ArrayList<>();

        String query = "SELECT "
                + "m.AccountId AS modAccountId, "
                + "m.Id AS modId, "
                + "t.Id AS tutorId, "
                + "c.Id AS cvId, "
                + "t.Active, "
                + "a.Name AS tutorName, "
                + "c.PhoneNumber, "
                + "c.Yob, "
                + "c.Location, "
                + "c.Personal_ID AS personalId, "
                + "c.Gender, "
                + "c.Experience, "
                + "c.Grade, "
                + "c.CreateTime, "
                + "c.Content, "
                + "c.Url "
                + "FROM "
                + "CV c "
                + "JOIN Tutor t ON c.TutorId = t.Id "
                + "JOIN Moderator m ON c.ModId = m.Id "
                + "JOIN Account a ON t.AccountId = a.Id "
                + "WHERE "
                + "t.Active = N'bị từ chối' AND "
                + "m.Id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, modId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CvDTO tutorData = new CvDTO();
                    tutorData.setModId(rs.getInt("modId"));
                    tutorData.setModAccountId(rs.getInt("modAccountId"));
                    tutorData.setTutorId(rs.getInt("tutorId"));
                    tutorData.setCvId(rs.getInt("cvId"));
                    tutorData.setActive(rs.getString("Active"));
                    tutorData.setTutorName(rs.getString("tutorName"));
                    tutorData.setPhoneNumber(rs.getString("PhoneNumber"));
                    tutorData.setYob(rs.getInt("Yob"));
                    tutorData.setLocation(rs.getString("Location"));
                    tutorData.setPersonalId(rs.getString("personalId"));
                    tutorData.setGender(rs.getString("Gender"));
                    tutorData.setExperience(rs.getInt("Experience"));
                    tutorData.setGrade(rs.getString("Grade"));
                    tutorData.setCreateTime(rs.getDate("CreateTime"));
                    tutorData.setContent(rs.getString("Content"));
                    tutorData.setUrl(rs.getString("Url"));
                    tutorDataList.add(tutorData);
                }
            }
        }
        return tutorDataList;
    }

    public CvDTO getCvById(int cvId) throws SQLException {
        String sql = "SELECT "
                + "m.AccountId AS modAccountId, "
                + "m.Id AS modId, "
                + "t.Id AS tutorId, "
                + "c.Id AS cvId, "
                + "t.Active, "
                + "a.Name AS tutorName, "
                + "c.PhoneNumber, "
                + "c.Yob, "
                + "c.Location, "
                + "c.Personal_ID AS personalId, "
                + "c.Gender, "
                + "c.Experience, "
                + "c.Grade, "
                + "c.CreateTime, "
                + "c.Content, "
                + "c.Url "
                + "FROM "
                + "CV c "
                + "JOIN Tutor t ON c.TutorId = t.Id "
                + "JOIN Moderator m ON c.ModId = m.Id "
                + "JOIN Account a ON t.AccountId = a.Id "
                + "WHERE "
                + "c.Id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cvId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CvDTO cv = new CvDTO();
                    cv.setModId(rs.getInt("modId"));
                    cv.setModAccountId(rs.getInt("modAccountId"));
                    cv.setTutorId(rs.getInt("tutorId"));
                    cv.setCvId(rs.getInt("cvId"));
                    cv.setActive(rs.getString("Active"));
                    cv.setTutorName(rs.getString("tutorName"));
                    cv.setPhoneNumber(rs.getString("PhoneNumber"));
                    cv.setYob(rs.getInt("Yob"));
                    cv.setLocation(rs.getString("Location"));
                    cv.setPersonalId(rs.getString("personalId"));
                    cv.setGender(rs.getString("Gender"));
                    cv.setExperience(rs.getInt("Experience"));
                    cv.setGrade(rs.getString("Grade"));
                    cv.setCreateTime(rs.getDate("CreateTime"));
                    cv.setContent(rs.getString("Content"));
                    cv.setUrl(rs.getString("Url"));
                    return cv;
                }
            }
        }
        return null;
    }

    public boolean approveTutor(int cvId) throws SQLException {
        String query = "UPDATE Tutor SET Active = N'đã kiểm duyệt' WHERE Id = (SELECT TutorId FROM CV WHERE Id = ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cvId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean rejectTutor(int cvId, String reason) throws SQLException {
        String updateTutorQuery = "UPDATE Tutor SET Active = N'bị từ chối' WHERE Id = (SELECT TutorId FROM CV WHERE Id = ?)";
        String deleteReasonQuery = "DELETE FROM ReasonDenyCv WHERE TutorId = (SELECT TutorId FROM CV WHERE Id = ?)";
        String insertReasonQuery = "INSERT INTO ReasonDenyCv (ModId, TutorId, Reason) VALUES (?, ?, ?)";

        try (PreparedStatement updateStmt = connection.prepareStatement(updateTutorQuery);
                PreparedStatement deleteStmt = connection.prepareStatement(deleteReasonQuery);
                PreparedStatement insertStmt = connection.prepareStatement(insertReasonQuery)) {

            updateStmt.setInt(1, cvId);
            int updateRows = updateStmt.executeUpdate();

            if (updateRows > 0) {
                deleteStmt.setInt(1, cvId);
                deleteStmt.executeUpdate();

                CvDTO cv = getCvById(cvId);
                if (cv != null) {
                    insertStmt.setInt(1, cv.getModId());
                    insertStmt.setInt(2, cv.getTutorId());
                    insertStmt.setString(3, reason);
                    int insertRows = insertStmt.executeUpdate();
                    return insertRows > 0;
                }
            }
        }
        return false;
    }

    public void insertMoneyIntoWallet(int studentId, double amount) throws SQLException {
        String query = "INSERT INTO Wallet (StudentId, AdminId, Balance, Status) VALUES (?, 1, ?, 'thành công')";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setDouble(2, amount);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Money inserted into wallet. Rows affected: " + rowsAffected);
        }
    }

    public ReportDTO getReportDetailsByComplaintId(int complaintId) throws SQLException {
        String query = "SELECT c.SlotId, c.TutorId, c.StudentId, sl.Price "
                + "FROM Complaint c "
                + "INNER JOIN Slot sl ON c.SlotId = sl.Id "
                + "WHERE c.Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, complaintId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int slotId = rs.getInt("SlotId");
                    int tutorId = rs.getInt("TutorId");
                    int studentId = rs.getInt("StudentId");
                    BigDecimal price = rs.getBigDecimal("Price");
                    return new ReportDTO(slotId, tutorId, studentId, price);
                }
            }
        }
        return null;
    }

    // Thêm phương thức xóa Payment
    public void deletePayment(int slotId, int tutorId) throws SQLException {
        String query = "DELETE FROM Salary WHERE SlotId = ? AND TutorId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            stmt.setInt(2, tutorId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Payment deleted. Rows affected: " + rowsAffected);
        }
    }

}
