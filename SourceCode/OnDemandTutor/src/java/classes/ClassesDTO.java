/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Admin
 */
public class ClassesDTO {
    private String subjectName;
    private int id;
    private String name;
    private int amountOfSlot;
    private String startDay;
    private String endDay;
    private int availableSlots;

    public ClassesDTO(String subjectName, int id, String name, int amountOfSlot, String startDay, String endDay, int availableSlots) {
        this.subjectName = subjectName;
        this.id = id;
        this.name = name;
        this.amountOfSlot = amountOfSlot;
        this.startDay = startDay;
        this.endDay = endDay;
        this.availableSlots = availableSlots;
    }

    // Các getter và setter tương ứng
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfSlot() {
        return amountOfSlot;
    }

    public void setAmountOfSlot(int amountOfSlot) {
        this.amountOfSlot = amountOfSlot;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
}
