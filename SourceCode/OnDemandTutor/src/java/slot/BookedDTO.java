/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slot;

/**
 *
 * @author Admin
 */
public class BookedDTO {

    String status;
    String dayOfSlot;
    String startTime;
    String endTime;
    String subjectName;
    String teacherName;

    public BookedDTO(String status, String dayOfSlot, String startTime, String endTime, String subjectName, String teacherName) {
        this.status = status;
        this.dayOfSlot = dayOfSlot;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDayOfSlot() {
        return dayOfSlot;
    }

    public void setDayOfSlot(String dayOfSlot) {
        this.dayOfSlot = dayOfSlot;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
    
}
