/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author ASUS
 */
public class StudentError {
    private String nameError = "";
    private String genderError = "";
    private String yobError = "";
    private String locationError = "";
    private String phoneNumberError = "";
    private String gradeError = "";
    private String pictureError = "";

    // Getters and Setters
    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getGenderError() {
        return genderError;
    }

    public void setGenderError(String genderError) {
        this.genderError = genderError;
    }

    public String getYobError() {
        return yobError;
    }

    public void setYobError(String yobError) {
        this.yobError = yobError;
    }

    public String getLocationError() {
        return locationError;
    }

    public void setLocationError(String locationError) {
        this.locationError = locationError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getGradeError() {
        return gradeError;
    }

    public void setGradeError(String gradeError) {
        this.gradeError = gradeError;
    }

    public String getPictureError() {
        return pictureError;
    }

    public void setPictureError(String pictureError) {
        this.pictureError = pictureError;
    }

    // Validation methods
    public boolean checkYob(String yob) {
        if (yob == null || yob.trim().isEmpty()) {
            yobError = "Năm sinh không được để trống.";
            return false;
        }
        if (!yob.matches("\\d{4}")) {
            yobError = "Năm sinh chỉ được phép nhập số.";
            return false;
        } else {
            yobError = "";
            return true;
        }
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            phoneNumberError = "Số điện thoại không được để trống.";
            return false;
        }
        if (!phoneNumber.matches("\\d{10}")) {
            phoneNumberError = "Số điện thoại chỉ được phép nhập số và bắt buộc 10 số.";
            return false;
        } else {
            phoneNumberError = "";
            return true;
        }
    }

    public boolean checkGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            genderError = "Vui lòng chọn giới tính.";
            return false;
        } else {
            genderError = "";
            return true;
        }
    }

    public boolean checkLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            locationError = "Địa chỉ không được để trống.";
            return false;
        } else {
            locationError = "";
            return true;
        }
    }

    public boolean checkGrade(String grade) {
        if (grade == null || grade.trim().isEmpty()) {
            gradeError = "Vui lòng chọn lớp.";
            return false;
        } else {
            gradeError = "";
            return true;
        }
    }

    public boolean checkPicture(String picture) {
        if (picture == null || picture.trim().isEmpty()) {
            pictureError = "Vui lòng chọn ảnh đại diện.";
            return false;
        } else {
            pictureError = "";
            return true;
        }
    }

    // Phương thức kiểm tra tất cả các lỗi
    public boolean isValid() {
        return nameError.isEmpty() && genderError.isEmpty() && yobError.isEmpty() &&
               locationError.isEmpty() && phoneNumberError.isEmpty() &&
               gradeError.isEmpty() && pictureError.isEmpty();
    }
}

