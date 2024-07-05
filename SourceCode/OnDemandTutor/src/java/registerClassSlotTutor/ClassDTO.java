/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerClassSlotTutor;

import java.sql.Date;

/**
 *
 * @author Long Dinh
 */
public class ClassDTO {

    private int tutorId;
    private int subjectId;
    private int amountOfSlot;
    private Date startDay;
    private Date endDay;

    public ClassDTO() {
    }

    public ClassDTO(int tutorId, int subjectId, int amountOfSlot, Date startDay, Date endDay) {
        this.tutorId = tutorId;
        this.subjectId = subjectId;
        this.amountOfSlot = amountOfSlot;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    // Getters and setters for all fields
    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getAmountOfSlot() {
        return amountOfSlot;
    }

    public void setAmountOfSlot(int amountOfSlot) {
        this.amountOfSlot = amountOfSlot;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }
}
