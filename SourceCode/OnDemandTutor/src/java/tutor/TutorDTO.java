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

    public TutorDTO() {
    }

    public TutorDTO(String name, String phoneNumber, String location, int yob) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.yob = yob;
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
