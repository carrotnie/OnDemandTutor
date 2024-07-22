/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class SalaryDTO {
    private int adminId;
    private int tutorId;
    private int slotId;
    private String tutorName;
    private BigDecimal balance;
    private String status;
    private String coursestatus;
    private Date endDay;
    private int salaryId;

    public SalaryDTO() {
    }

    public SalaryDTO(int adminId, int tutorId, int slotId, String tutorName, BigDecimal balance, String status, String coursestatus, Date endDay, int salaryId) {
        this.adminId = adminId;
        this.tutorId = tutorId;
        this.slotId = slotId;
        this.tutorName = tutorName;
        this.balance = balance;
        this.status = status;
        this.coursestatus = coursestatus;
        this.endDay = endDay;
        this.salaryId = salaryId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
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

    public String getCoursestatus() {
        return coursestatus;
    }

    public void setCoursestatus(String coursestatus) {
        this.coursestatus = coursestatus;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    
}
