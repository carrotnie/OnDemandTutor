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
    private String name;
    private String phoneNumber;
    private String location;
    private int yob;
    private int Id;
    private String subjectName;
    private double rating;

    public TutorDTO() {
    }
    
    public TutorDTO(String phoneNumber, String location, int yob, int Id) {
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.yob = yob;
        this.Id = Id;
    }

    public TutorDTO(String name, String phoneNumber, String location, int yob) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.yob = yob;
    }

    public TutorDTO(String name, String phoneNumber, String location, int yob, int Id, String subjectName, double rating) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.yob = yob;
        this.Id = Id;
        this.subjectName = subjectName;
        this.rating = rating;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    
    
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public int getYob() {
        return yob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

}
