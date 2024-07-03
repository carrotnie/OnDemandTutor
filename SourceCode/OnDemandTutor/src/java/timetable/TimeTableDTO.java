package timetable;

import java.sql.Date;
import java.sql.Time;

public class TimeTableDTO {
    private int accountId;
    private int studentId;
    private Date startDay;
    private Date endDay;
    private Time startTime;
    private Time endTime;
    private String dayOfSlot;
    private String tutorName;
    private String subjectName;
    private String status;

    public TimeTableDTO() {
    }

    public TimeTableDTO(int accountId, int studentId, Date startDay, Date endDay, Time startTime, Time endTime, String dayOfSlot, String tutorName, String subjectName, String status) {
        this.accountId = accountId;
        this.studentId = studentId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfSlot = dayOfSlot;
        this.tutorName = tutorName;
        this.subjectName = subjectName;
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public String getDayOfSlot() {
        return dayOfSlot;
    }

    public void setDayOfSlot(String dayOfSlot) {
        this.dayOfSlot = dayOfSlot;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
