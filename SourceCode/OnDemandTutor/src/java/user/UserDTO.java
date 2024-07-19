/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author MSI
 */
public class UserDTO {

    private int Id;
    private String Name;
    private String Username;
    private String Password;
    private String Role;
    private int AmountOfReport;
    private String Status;
    private String otp;

    private int accountId;
    private String gender;
    private int yob;
    private String location;
    private String phoneNumber;
    private int grade;
    
    private String personalId;
    private int subject;
    private String url;
    private String content;
    private int experience;
    private String active;
    private int modId;
    private int tutorId;

    //fotgot password

    
    
    
    
    public UserDTO(int accountId, String gender, int yob, String location, String phoneNumber, int grade) {
        this.accountId = accountId;
        this.gender = gender;
        this.yob = yob;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.grade = grade;
    }

    public UserDTO(int tutorId, int modId, String phoneNumber, int yob, String location, String personalId, String gender, int experience, int grade, String content, String url) {
        this.tutorId = tutorId;
        this.modId = modId;
        this.phoneNumber = phoneNumber;
        this.yob = yob;
        this.location = location;
        this.personalId = personalId;
        this.gender = gender;
        this.experience = experience;
        this.grade = grade;
        this.content = content;
        this.url = url;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public UserDTO() {
        this.Id = 0;
        this.Name = "";
        this.Username = "";
        this.Password = "";
        this.Role = "";
    }

    public UserDTO(int Id, String Name, String Username, String Password, String Role, int AmountOfReport, String Status) {
        this.Id = Id;
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.AmountOfReport = AmountOfReport;
        this.Status = Status;
    }

    public UserDTO(int Id, String Name, String Username, String Password, String Role) {
        this.Id = Id;
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
    }
    
    public UserDTO(String Name, String Username, String Password, String Role) {
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public int getAmountOfReport() {
        return AmountOfReport;
    }

    public void setAmountOfReport(int AmountOfReport) {
        this.AmountOfReport = AmountOfReport;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    //forgot password

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
    
    

}
