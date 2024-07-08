package mod;

import java.sql.Date;
/**
 *
 * @author ADMIN
 */
public class CvDTO {
    private int modAccountId;
    private int modId;
    private int tutorId;
    private int cvId;
    private String active;
    private String tutorName;
    private String phoneNumber;
    private int yob;
    private String location;
    private String personalId;
    private String gender;
    private int experience;
    private int grade;
    private Date createTime;
    private String content;
    private String url;

    public CvDTO() {
    }

    public CvDTO(int modAccountId, int modId, int tutorId, int cvId, String active, String tutorName, String phoneNumber, int yob, String location, String personalId, String gender, int experience, int grade, Date createTime, String content, String url) {
        this.modAccountId = modAccountId;
        this.modId = modId;
        this.tutorId = tutorId;
        this.cvId = cvId;
        this.active = active;
        this.tutorName = tutorName;
        this.phoneNumber = phoneNumber;
        this.yob = yob;
        this.location = location;
        this.personalId = personalId;
        this.gender = gender;
        this.experience = experience;
        this.grade = grade;
        this.createTime = createTime;
        this.content = content;
        this.url = url;
    }

    public int getModAccountId() {
        return modAccountId;
    }

    public void setModAccountId(int modAccountId) {
        this.modAccountId = modAccountId;
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getCvId() {
        return cvId;
    }

    public void setCvId(int cvId) {
        this.cvId = cvId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
}
