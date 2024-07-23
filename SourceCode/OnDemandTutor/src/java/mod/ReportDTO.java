package mod;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class ReportDTO {
    private int accountId; //Mod AccountID
    private int modId;
    private int complaintId;
    private int studentId;
    private int tutorId;
    private int accountTutorId;
    private int slotId;
    private String studentName;
    private String tutorName;
    private String content;
    private String status;
    private int walletId;
    private int adminId;
    private int balance;
    private BigDecimal price;
    private int SalaryId;

    public ReportDTO() {
    }

    public ReportDTO(int accountId, int modId, int complaintId, int studentId, int tutorId, int accountTutorId, int slotId, String studentName, String tutorName, String content, String status, int walletId, int adminId, int balance, BigDecimal price, int SalaryId) {
        this.accountId = accountId;
        this.modId = modId;
        this.complaintId = complaintId;
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.accountTutorId = accountTutorId;
        this.slotId = slotId;
        this.studentName = studentName;
        this.tutorName = tutorName;
        this.content = content;
        this.status = status;
        this.walletId = walletId;
        this.adminId = adminId;
        this.balance = balance;
        this.price = price;
        this.SalaryId = SalaryId;
    }
    
    public ReportDTO(int slotId, int tutorId, int studentId, BigDecimal price) {
        this.slotId = slotId;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.price = price;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getAccountTutorId() {
        return accountTutorId;
    }

    public void setAccountTutorId(int accountTutorId) {
        this.accountTutorId = accountTutorId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSalaryId() {
        return SalaryId;
    }

    public void setSalaryId(int SalaryId) {
        this.SalaryId = SalaryId;
    }
    

   
    
}
