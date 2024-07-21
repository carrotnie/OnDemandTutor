/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerClassSlotTutor;

import java.sql.Time;

/**
 *
 * @author Long Dinh
 */
public class SlotDTO {

    private int classId;
    private String dayOfSlot;
    private Time startTime;
    private Time endTime;

    public SlotDTO() {
    }

    public SlotDTO(int classId, String dayOfSlot, Time startTime, Time endTime) {
        this.classId = classId;
        this.dayOfSlot = dayOfSlot;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getDayOfSlot() {
        return dayOfSlot;
    }

    public void setDayOfSlot(String dayOfSlot) {
        this.dayOfSlot = dayOfSlot;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
