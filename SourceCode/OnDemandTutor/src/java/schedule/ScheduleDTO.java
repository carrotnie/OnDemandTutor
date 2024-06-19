package schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDTO {
    private int accountId;
    private int studentId;
    private int slotId;
    private LocalDate startDay;
    private LocalDate endDay;
    private String dayOfSlot;
    private LocalTime startTime;
    private LocalTime endTime;
    private String tutorName;
    private String status;
    private String subjectName;

    public ScheduleDTO() {
    }

    public ScheduleDTO(int accountId, int studentId, int slotId, LocalDate startDay, LocalDate endDay, String dayOfSlot, LocalTime startTime, LocalTime endTime, String tutorName, String status, String subjectName) {
        this.accountId = accountId;
        this.studentId = studentId;
        this.slotId = slotId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.dayOfSlot = dayOfSlot;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tutorName = tutorName;
        this.status = status;
        this.subjectName = subjectName;
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

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public String getDayOfSlot() {
        return dayOfSlot;
    }

    public void setDayOfSlot(String dayOfSlot) {
        this.dayOfSlot = dayOfSlot;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
  
}
