package timetable;

import java.sql.Date;
import java.sql.Time;

public class TimeTableDTO {
    private int accountId;
    private int studentId;
    private int tutorId;
    private int ModId;
    private int slotId;
    private Date startDay;
    private Date endDay;
    private Time startTime;
    private Time endTime;
    private String dayOfSlot;
    private String tutorName;
    private String subjectName;
    private String status;
    private String content;

    public TimeTableDTO() {
    }

    public TimeTableDTO(int accountId, int studentId, int tutorId, int ModId, int slotId, Date startDay, Date endDay, Time startTime, Time endTime, String dayOfSlot, String tutorName, String subjectName, String status, String content) {
        this.accountId = accountId;
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.ModId = ModId;
        this.slotId = slotId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfSlot = dayOfSlot;
        this.tutorName = tutorName;
        this.subjectName = subjectName;
        this.status = status;
        this.content = content;
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

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getModId() {
        return ModId;
    }

    public void setModId(int ModId) {
        this.ModId = ModId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
