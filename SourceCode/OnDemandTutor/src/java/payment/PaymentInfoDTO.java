/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

/**
 *
 * @author ADMIN
 */
public class PaymentInfoDTO {
    private int tutorId;
    private int adminId;
    private String tutorName;
    private String bankAccountNumber;
    private String bank;

    public PaymentInfoDTO() {
    }

    public PaymentInfoDTO(int tutorId, int adminId, String tutorName, String bankAccountNumber, String bank) {
        this.tutorId = tutorId;
        this.adminId = adminId;
        this.tutorName = tutorName;
        this.bankAccountNumber = bankAccountNumber;
        this.bank = bank;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    
}
