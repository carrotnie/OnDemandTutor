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

    String subjectName;
    int id;
    String name;
    int amountSlot;
    String fromDate;
    String toDate;

    public ClassesDTO(String subjectName, int id, String name, int amountSlot, String fromDate, String toDate) {
        this.subjectName = subjectName;
        this.id = id;
        this.name = name;
        this.amountSlot = amountSlot;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

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

    public int getAmountSlot() {
        return amountSlot;
    }

    public void setAmountSlot(int amountSlot) {
        this.amountSlot = amountSlot;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
}
