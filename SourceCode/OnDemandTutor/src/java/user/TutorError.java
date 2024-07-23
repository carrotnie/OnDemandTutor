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
public class TutorError {
    private String nameError = "";
    private String phoneNumberError = "";
    private String yobError = "";
    private String personalIdError = "";
    private String genderError = "";
    private String subjectError = "";
    private String gradeError = "";
    private String locationError = "";
    private String urlError = "";
    private String contentError = "";
    private String experienceError = "";
    private String pictureError = "";
    private String certificateError = "";

    // Getters và setters
    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getYobError() {
        return yobError;
    }

    public void setYobError(String yobError) {
        this.yobError = yobError;
    }

    public String getPersonalIdError() {
        return personalIdError;
    }

    public void setPersonalIdError(String personalIdError) {
        this.personalIdError = personalIdError;
    }

    public String getGenderError() {
        return genderError;
    }

    public void setGenderError(String genderError) {
        this.genderError = genderError;
    }

    public String getSubjectError() {
        return subjectError;
    }

    public void setSubjectError(String subjectError) {
        this.subjectError = subjectError;
    }

    public String getGradeError() {
        return gradeError;
    }

    public void setGradeError(String gradeError) {
        this.gradeError = gradeError;
    }

    public String getLocationError() {
        return locationError;
    }

    public void setLocationError(String locationError) {
        this.locationError = locationError;
    }

    public String getUrlError() {
        return urlError;
    }

    public void setUrlError(String urlError) {
        this.urlError = urlError;
    }

    public String getContentError() {
        return contentError;
    }

    public void setContentError(String contentError) {
        this.contentError = contentError;
    }

    public String getExperienceError() {
        return experienceError;
    }

    public void setExperienceError(String experienceError) {
        this.experienceError = experienceError;
    }

    public String getPictureError() {
        return pictureError;
    }

    public void setPictureError(String pictureError) {
        this.pictureError = pictureError;
    }

    public String getCertificateError() {
        return certificateError;
    }

    public void setCertificateError(String certificateError) {
        this.certificateError = certificateError;
    }

    // Validation methods
    public boolean checkName(String name) {
        if (name == null || name.trim().isEmpty()) {
            nameError = "Tên không được để trống.";
            return false;
        } else if (name.matches(".*\\d.*")) {
            nameError = "Tên không được chứa số.";
            return false;
        } else {
            nameError = "";
            return true;
        }
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            phoneNumberError = "Số điện thoại chỉ được phép nhập 10 số.";
            return false;
        } else {
            phoneNumberError = "";
            return true;
        }
    }

    public boolean checkYob(String yob) {
        if (yob == null || !yob.matches("\\d{4}") || Integer.parseInt(yob) < 1974 || Integer.parseInt(yob) > 2005) {
            yobError = "Năm sinh chỉ từ 1974 đến 2005.";
            return false;
        } else {
            yobError = "";
            return true;
        }
    }

    public boolean checkPersonalId(String personalId) {
        if (personalId == null || !personalId.matches("\\d{12}")) {
            personalIdError = "CCCD không được phép để trống, bắt buộc 12 số.";
            return false;
        } else {
            personalIdError = "";
            return true;
        }
    }

    public boolean checkGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            genderError = "Vui lòng chọn giới tính.";
            return false;
        } else {
            genderError = "";
            return true;
        }
    }

    public boolean checkSubjects(String[] subjects) {
        if (subjects == null || subjects.length == 0) {
            subjectError = "Vui lòng chọn ít nhất một môn.";
            return false;
        } else {
            subjectError = "";
            return true;
        }
    }

    public boolean checkGrade(String grade) {
        if (grade == null || grade.isEmpty()) {
            gradeError = "Vui lòng chọn trình độ giảng dạy.";
            return false;
        } else {
            gradeError = "";
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

    public boolean checkUrl(String url) {
        if (url == null || !url.startsWith("https://www.youtube.com/")) {
            urlError = "Link URL phải gồm https://www.youtube.com/";
            return false;
        } else {
            urlError = "";
            return true;
        }
    }

    public boolean checkContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            contentError = "Bio không được để trống.";
            return false;
        } else {
            contentError = "";
            return true;
        }
    }

    public boolean checkExperience(String experience) {
        if (experience == null || experience.isEmpty()) {
            experienceError = "Vui lòng chọn số năm kinh nghiệm.";
            return false;
        } else {
            experienceError = "";
            return true;
        }
    }

    public boolean checkPicture(String picture) {
        if (picture == null || picture.isEmpty()) {
            pictureError = "Vui lòng chọn ảnh đại diện.";
            return false;
        } else {
            pictureError = "";
            return true;
        }
    }

    public boolean checkCertificate(String certificate) {
        if (certificate == null || certificate.isEmpty()) {
            certificateError = "Vui lòng chọn chứng chỉ.";
            return false;
        } else {
            certificateError = "";
            return true;
        }
    }

    // Method to check if all fields are valid
    public boolean isValid() {
        return nameError.isEmpty() && phoneNumberError.isEmpty() && yobError.isEmpty() && personalIdError.isEmpty() &&
               genderError.isEmpty() && subjectError.isEmpty() && gradeError.isEmpty() && locationError.isEmpty() &&
               urlError.isEmpty() && contentError.isEmpty() && experienceError.isEmpty() && pictureError.isEmpty() &&
               certificateError.isEmpty();
    }
}

