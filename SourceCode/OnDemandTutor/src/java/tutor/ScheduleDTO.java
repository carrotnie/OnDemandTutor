/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutor;

/**
 *
 * @author Long Dinh
 */
public class ScheduleDTO {

    private String startDay;
    private String startTime;
    private String subjectName;
    private String studentName;
    private String status;
    private int studentAccountId;
    private int slotId;

    public ScheduleDTO() {
    }

    public ScheduleDTO(String startDay, String startTime, String subjectName, String studentName, String status, int studentAccountId, int slotId) {
        this.startDay = startDay;
        this.startTime = startTime;
        this.subjectName = subjectName;
        this.studentName = studentName;
        this.status = status;
        this.studentAccountId = studentAccountId;
        this.slotId = slotId;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getStartTime() {
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

    public void setStartTime(String startTime) {
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
    
}
