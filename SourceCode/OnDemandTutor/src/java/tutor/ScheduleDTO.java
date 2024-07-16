/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutor;

import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 *
 * @author Long Dinh
 */
public class ScheduleDTO {

    private String startDay;
    private String endDay;
    private Time startTime;
    private Time endTime;
    private String subjectName;
    private String studentName;
    private String status;
    private int studentAccountId;
    private int slotId;

    public ScheduleDTO() {
    }

    public ScheduleDTO(String startDay, String endDay, Time startTime, Time endTime, String subjectName, String studentName, String status, int studentAccountId, int slotId) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subjectName = subjectName;
        this.studentName = studentName;
        this.status = status;
        this.studentAccountId = studentAccountId;
        this.slotId = slotId;
    }

    public String getEndDay() {
        return endDay;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getStartDay() {
        return startDay;
    }

    public Time getStartTime() {
        return startTime;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStudentAccountId() {
        return studentAccountId;
    }

    public void setStudentAccountId(int studentAccountId) {
        this.studentAccountId = studentAccountId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getFormattedStartTime() {
        return new SimpleDateFormat("HH:mm").format(this.startTime);
    }

    public String getFormattedEndTime() {
        return new SimpleDateFormat("HH:mm").format(this.endTime);
    }

}
