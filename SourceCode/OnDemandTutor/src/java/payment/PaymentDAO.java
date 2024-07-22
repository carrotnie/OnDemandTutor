package payment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PaymentDAO {

    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(PaymentDAO.class.getName());

    private static final String GET_TOTAL_BALANCE_BY_ACCOUNT_ID
            = "SELECT SUM(w.Balance) AS TotalBalance "
            + "FROM Wallet w "
            + "JOIN Student s ON w.StudentId = s.Id "
            + "JOIN Account a ON s.AccountId = a.Id "
            + "WHERE a.Id = ? AND w.Status = N'thành công'";

    private static final String INSERT_TO_WALLET
            = "INSERT INTO Wallet (StudentId, AdminId, Balance, Status) "
            + "VALUES ((SELECT Id FROM Student WHERE AccountId = ?), 1, ?, N'đang xử lý')";

    private static final String GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() AS walletId";
    private static final String GET_STUDENT_ID_BY_ACCOUNT_ID
            = "SELECT Id FROM Student WHERE AccountId = ?";

    private static final String GET_ADMIN_ID_BY_ACCOUNT_ID
            = "SELECT Admin.Id AS AdminId "
            + "FROM Admin "
            + "JOIN Account ON Admin.AccountId = Account.Id "
            + "WHERE Account.Id = ?";

    private static final String UPDATE_WALLET_STATUS
            = "UPDATE Wallet SET Status = N'thành công' WHERE Id = ?";

    private static final String INSERT_PAYMENT
            = "INSERT INTO Payment (TutorId, AdminId, BankAccountNumber, Bank, BankAccountName) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_TUTOR_ID_BY_ACCOUNT_ID
            = "SELECT Tutor.Id AS TutorId "
            + "FROM Tutor "
            + "JOIN Account ON Tutor.AccountId = Account.Id "
            + "WHERE Account.Id = ?";
    private static final String GET_PAYMENT_DETAILS_BY_TUTOR_ID
            = "SELECT t.Id AS TutorId, a.Name AS TutorName, p.BankAccountNumber, p.Bank "
            + "FROM Payment p "
            + "JOIN Tutor t ON p.TutorId = t.Id "
            + "JOIN Account a ON t.AccountId = a.Id "
            + "WHERE t.Id = ?";

    private static final String UPDATE_PAYMENT_INFO
            = "UPDATE Payment SET BankAccountNumber = ?, Bank = ? WHERE TutorId = ?";

    private static final String UPDATE_SALARY_STATUS_TO_COMPLETE
            = "UPDATE Salary SET Status = N'hoàn thành' WHERE Id = ?";

    private static final String GET_BALANCES_BY_TUTOR_ID
            = "SELECT Id, Balance, SlotId, Status "
            + "FROM Salary "
            + "WHERE TutorId = ? AND Status = N'hoàn thành'";

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer getAdminIdByAccountId(int accountId) throws SQLException {
        if (connection == null) {
            LOGGER.severe("Database connection is null.");
            return null;
        }
        try (PreparedStatement stmt = connection.prepareStatement(GET_ADMIN_ID_BY_ACCOUNT_ID)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("AdminId");
                } else {
                    LOGGER.warning("No Admin found for AccountId: " + accountId);
                    return null;
                }
            }
        }
    }

    public BigDecimal getTotalBalanceByAccountId(int accountId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(GET_TOTAL_BALANCE_BY_ACCOUNT_ID)) {
            stmt.setInt(1, accountId);
            LOGGER.info("Executing query with accountId: " + accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BigDecimal totalBalance = rs.getBigDecimal("TotalBalance");
                    if (totalBalance == null) {
                        totalBalance = BigDecimal.ZERO;
                    }
                    LOGGER.info("Total balance found for AccountId " + accountId + ": " + totalBalance);
                    return totalBalance;
                } else {
                    LOGGER.warning("No balance found for AccountId: " + accountId);
                    return BigDecimal.ZERO;
                }
            }
        }
    }

    public void depositToAccount(int accountId, BigDecimal amount) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_TO_WALLET)) {
            stmt.setInt(1, accountId);
            stmt.setBigDecimal(2, amount);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                LOGGER.info("Deposit successful for AccountId " + accountId + ": " + amount);
            } else {
                LOGGER.warning("Deposit failed for AccountId " + accountId + ": " + amount);
            }
        }
    }

    public int getStudentIdByAccountId(int accountId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(GET_STUDENT_ID_BY_ACCOUNT_ID)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Id");
                } else {
                    throw new SQLException("No student found for AccountId: " + accountId);
                }
            }
        }
    }

    public List<PaymentDTO> getPaymentsByAdminId(int adminId) throws SQLException {
        List<PaymentDTO> payments = new ArrayList<>();
        String sql = "SELECT s.Id AS StudentId, a.Name AS StudentName, w.Balance, w.Status, w.Id AS WalletId "
                + "FROM Wallet w "
                + "JOIN Student s ON w.StudentId = s.Id "
                + "JOIN Account a ON s.AccountId = a.Id "
                + "WHERE w.AdminId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, adminId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int studentId = rs.getInt("StudentId");
                    String studentName = rs.getString("StudentName");
                    BigDecimal balance = rs.getBigDecimal("Balance");
                    String status = rs.getString("Status");
                    int walletId = rs.getInt("WalletId");
                    PaymentDTO payment = new PaymentDTO();
                    payment.setStudentId(studentId);
                    payment.setStudentName(studentName);
                    payment.setBalance(balance);
                    payment.setStatus(status);
                    payment.setWalletId(walletId); // Set WalletId
                    payments.add(payment);
                }
            }
        }
        return payments;
    }

    public List<PaymentDTO> getPaymentsByAdminIdAndStatus(int adminId, String status) throws SQLException {
        List<PaymentDTO> payments = new ArrayList<>();
        String sql = "SELECT s.Id AS StudentId, a.Name AS StudentName, w.Balance, w.Status, w.Id AS WalletId "
                + "FROM Wallet w "
                + "JOIN Student s ON w.StudentId = s.Id "
                + "JOIN Account a ON s.AccountId = a.Id "
                + "WHERE w.AdminId = ? AND w.Status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, adminId);
            stmt.setString(2, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int studentId = rs.getInt("StudentId");
                    String studentName = rs.getString("StudentName");
                    BigDecimal balance = rs.getBigDecimal("Balance");
                    String paymentStatus = rs.getString("Status");
                    int walletId = rs.getInt("WalletId");
                    PaymentDTO payment = new PaymentDTO();
                    payment.setStudentId(studentId);
                    payment.setStudentName(studentName);
                    payment.setBalance(balance);
                    payment.setStatus(paymentStatus);
                    payment.setWalletId(walletId); // Set WalletId
                    payments.add(payment);
                }
            }
        }
        return payments;
    }

    public boolean updateWalletStatus(int walletId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_WALLET_STATUS)) {
            stmt.setInt(1, walletId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean updateWalletStatusToFailed(int walletId) throws SQLException {
        String sql = "UPDATE Wallet SET Status = N'thất bại' WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, walletId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public List<SalaryDTO> getSalariesByAdminId(int adminId) throws SQLException {
        List<SalaryDTO> salaries = new ArrayList<>();
        String sql = "SELECT s.Id AS SalaryId, t.Id AS TutorId, a.Name AS TutorName, s.Balance, s.Status, s.CourseStatus, c.EndDay "
                + "FROM Salary s "
                + "JOIN Tutor t ON s.TutorId = t.Id "
                + "JOIN Account a ON t.AccountId = a.Id "
                + "JOIN Slot sl ON s.SlotId = sl.Id "
                + "JOIN Class c ON sl.ClassId = c.Id "
                + "WHERE s.AdminId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, adminId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int salaryId = rs.getInt("SalaryId");
                    int tutorId = rs.getInt("TutorId");
                    String tutorName = rs.getString("TutorName");
                    BigDecimal balance = rs.getBigDecimal("Balance");
                    String status = rs.getString("Status");
                    String courseStatus = rs.getString("CourseStatus");
                    Date endDay = rs.getDate("EndDay");

                    SalaryDTO salary = new SalaryDTO();
                    salary.setSalaryId(salaryId);
                    salary.setTutorId(tutorId); // Set TutorId
                    salary.setTutorName(tutorName);
                    salary.setBalance(balance);
                    salary.setStatus(status);
                    salary.setCoursestatus(courseStatus);
                    salary.setEndDay(endDay);

                    salaries.add(salary);
                }
            }
        }
        return salaries;
    }

    public boolean updateCourseStatus(int salaryId) throws SQLException {
        String sql = "UPDATE Salary SET CourseStatus = N'hoàn thành' WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, salaryId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean updateSalaryStatusToComplete(int salaryId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_SALARY_STATUS_TO_COMPLETE)) {
            stmt.setInt(1, salaryId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public Integer getTutorIdByAccountId(int accountId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(GET_TUTOR_ID_BY_ACCOUNT_ID)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("TutorId");
                } else {
                    return null;
                }
            }
        }
    }

    public PaymentInfoDTO getPaymentDetailsByTutorId(int tutorId) throws SQLException {
        String sql = "SELECT t.Id AS TutorId, a.Name AS TutorName, p.BankAccountNumber, p.Bank "
                + "FROM Payment p "
                + "JOIN Tutor t ON p.TutorId = t.Id "
                + "JOIN Account a ON t.AccountId = a.Id "
                + "WHERE t.Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tutorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PaymentInfoDTO paymentInfo = new PaymentInfoDTO();
                    paymentInfo.setTutorId(rs.getInt("TutorId"));
                    paymentInfo.setTutorName(rs.getString("TutorName"));
                    paymentInfo.setBankAccountNumber(rs.getString("BankAccountNumber"));
                    paymentInfo.setBank(rs.getString("Bank"));
                    return paymentInfo;
                } else {
                    return null;
                }
            }
        }
    }

    public boolean updatePaymentInfo(int tutorId, String bankAccountNumber, String bank) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_PAYMENT_INFO)) {
            stmt.setString(1, bankAccountNumber);
            stmt.setString(2, bank);
            stmt.setInt(3, tutorId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public int insertWalletEntry(int accountId, BigDecimal amount) throws SQLException {
        int walletId = -1;
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_TO_WALLET, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, accountId);
            stmt.setBigDecimal(2, amount);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                LOGGER.info("Deposit successful for AccountId " + accountId + ": " + amount);
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        walletId = rs.getInt(1);
                    }
                }
            } else {
                LOGGER.warning("Deposit failed for AccountId " + accountId + ": " + amount);
            }
        }
        return walletId;
    }

    public List<SalaryDTO> getBalancesByTutorId(int tutorId) throws SQLException {
        List<SalaryDTO> salaries = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(GET_BALANCES_BY_TUTOR_ID)) {
            stmt.setInt(1, tutorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SalaryDTO salary = new SalaryDTO();
                    salary.setSalaryId(rs.getInt("Id"));
                    salary.setBalance(rs.getBigDecimal("Balance"));
                    salary.setSlotId(rs.getInt("SlotId"));
                    salary.setStatus(rs.getString("Status"));

                    salaries.add(salary);
                }
            }
        }
        return salaries;
    }
}
