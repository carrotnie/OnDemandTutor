/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedback;

import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class FeedbackDTO {

    private int accountId;
    private int tutorId;
    private int studentId;
    private int slotId;
    private int subjectId;
    private LocalDate startDay;
    private LocalDate endDay;
    private String tutorName;
    private String subjectName;
    private String feedback;
    private int rating;
    private int modId;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int accountId, int tutorId, int studentId, int slotId, int subjectId, LocalDate startDay, LocalDate endDay, String tutorName, String subjectName, String feedback, int rating, int modId) {
        this.accountId = accountId;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.slotId = slotId;
        this.subjectId = subjectId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.tutorName = tutorName;
        this.subjectName = subjectName;
        this.feedback = feedback;
        this.rating = rating;
        this.modId = modId;
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

}
