package payment;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class PaymentDTO {
    private int studentId;
    private int adminId;
    private BigDecimal balance;
    private String status;
    private String studentName;
    private int walletId;

    public PaymentDTO() {
    }

    public PaymentDTO(int studentId, int adminId, BigDecimal balance, String status, String studentName, int walletId) {
        this.studentId = studentId;
        this.adminId = adminId;
        this.balance = balance;
        this.status = status;
        this.studentName = studentName;
        this.walletId = walletId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    
}