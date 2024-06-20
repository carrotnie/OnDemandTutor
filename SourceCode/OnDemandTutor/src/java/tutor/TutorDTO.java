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
public class TutorDTO {

    private String Personal_Id;
    private String Gender;
    private int Experience;
    private int Grade;
    private String Name;
    private String PhoneNumber;
    private String Location;
    private int Yob;
    private int AccountId;
    private int Id;
    private String subjectName;
    private double rating;

    public TutorDTO() {
        this.Personal_Id = "";
        this.Gender = "";
        this.Experience = 0;
        this.Grade = 0;
        this.PhoneNumber = "";
        this.Location = "";
        this.Yob = 0;
        this.AccountId = 0;
        this.Id = 0;
        this.Name = "";
    }
    
    

    public String getPersonalId() {
        return Personal_Id;
    }

    public String getGender() {
        return Gender;
    }

    public int getExperience() {
        return Experience;
    }

    public int getGrade() {
        return Grade;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getLocation() {
        return Location;
    }

    public int getYob() {
        return Yob;
    }

    public int getId() {
        return Id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public double getRating() {
        return rating;
    }

    public void setPersonalId(String Personal_Id) {
        this.Personal_Id = Personal_Id;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setExperience(int Experience) {
        this.Experience = Experience;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setYob(int Yob) {
        this.Yob = Yob;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    
    
    public TutorDTO(String Personal_Id, String Gender, int Experience, int Grade, String PhoneNumber, String Location, int Yob, int AccountId, int Id) {
        this.Personal_Id = Personal_Id;
        this.Gender = Gender;
        this.Experience = Experience;
        this.Grade = Grade;
        this.PhoneNumber = PhoneNumber;
        this.Location = Location;
        this.Yob = Yob;
        this.AccountId = AccountId;
        this.Id = Id;
        this.Name = Name;
    }



    public TutorDTO(String Name, String PhoneNumber, String location, int Yob, int Id, String subjectName, double rating) {
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.Location = Location;
        this.Yob = Yob;
        this.Id = Id;
        this.subjectName = subjectName;
        this.rating = rating;
    }

}
