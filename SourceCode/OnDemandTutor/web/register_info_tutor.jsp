<%-- 
    Document   : registor_info_tutor
    Created on : 18-Jun-2024, 22:50:36
    Author     : Lam Le
--%>

<%@page import="user.UserDTO"%>
<%@page import="tutor.TutorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Điền Thông Tin Giáo Viên - Giasumienphi.edu.vn</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .header {
                background-color: #ffffff;
                color: #001F3F;
                text-align: center;
                padding: 5px 0;
                margin: 0;
            }
            .header h5 {
                margin: 0;
            }
            .nav {
                background-color: #001e54;
                display: flex;
                align-items: center;
                padding: 10px 20px;
                border-bottom: 1px solid #ddd;
                color: white;
            }
            .nav img {
                height: 50px;
                margin-right: 20px;
            }
            .nav a {
                text-decoration: none;
                color: white;
                font-weight: bold;
                padding: 10px 20px;
                margin: 0 5px;
                display: inline-block;
            }
            .nav .logout-button {
                background-color: #FF4136;
                color: white;
                padding: 10px 20px;
                text-decoration: none;
                font-weight: bold;
                border-radius: 5px;
                margin-left: auto;
            }
            .container {
                padding: 20px;
                display: flex;
                justify-content: center;
            }
            .form-section {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 800px;
                width: 100%;
            }
            .form-section h2 {
                margin-top: 0;
                text-align: center;
            }
            .form-section form {
                display: flex;
                flex-direction: column;
            }
            .form-section label {
                margin: 10px 0 5px;
            }
            .form-section input, .form-section select, .form-section textarea {
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
                width: 100%;
                box-sizing: border-box;
            }
            .form-section button {
                margin-top: 20px;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                background-color: #007BFF;
                color: white;
                font-size: 16px;
                cursor: pointer;
            }
            .form-section button:hover {
                background-color: #0056b3;
            }
            .photo {
                text-align: center;
                margin-bottom: 20px;
            }
            .photo img {
                max-width: 150px; /* Set the maximum width of the image */
                height: auto; /* Maintain the aspect ratio */
                display: block; /* Ensure the image is displayed as a block element */
                margin: 0 auto; /* Center the image horizontally */
            }
            .certificate img {
                max-width: 300px; 
                height: auto; 
                display: block; 
                margin: 10px auto; 
            }
            .error-message {
                color: red;
                margin-top: 5px;
            }
        </style>
        <script>
            function validateForm() {
                let isValid = true;

                // Kiểm tra tên
                let name = document.getElementById("name");
                if (name.value.trim() === "") {
                    setError(name, "Tên không được để trống.");
                    isValid = false;
                } else {
                    clearError(name);
                }

                // Kiểm tra số điện thoại
                let phone = document.getElementById("phonenumber");
                if (!/^\d{10}$/.test(phone.value)) {
                    setError(phone, "Số điện thoại phải là một số có 10 chữ số.");
                    isValid = false;
                } else {
                    clearError(phone);
                }

                // Kiểm tra năm sinh
                let yob = document.getElementById("yob");
                if (!/^\d+$/.test(yob.value)) {
                    setError(yob, "Năm sinh phải là một số.");
                    isValid = false;
                } else {
                    clearError(yob);
                }

                // Kiểm tra địa chỉ
                let location = document.getElementById("location");
                if (location.value.trim() === "") {
                    setError(location, "Địa chỉ không được để trống.");
                    isValid = false;
                } else {
                    clearError(location);
                }

                // Kiểm tra Personal Id
                let personalId = document.getElementById("personalId");
                if (!/^\d{12}$/.test(personalId.value)) {
                    setError(personalId, "Personal Id phải là một số có 12 chữ số.");
                    isValid = false;
                } else {
                    clearError(personalId);
                }

                // Kiểm tra giới tính
                let gender = document.getElementById("gender");
                if (gender.value !== "Nam" && gender.value !== "Nữ") {
                    setError(gender, "Giới tính phải là Nam hoặc Nữ.");
                    isValid = false;
                } else {
                    clearError(gender);
                }

                // Kiểm tra kinh nghiệm
                let experience = document.getElementById("experience");
                if (!/^\d+$/.test(experience.value) || parseInt(experience.value) < 0) {
                    setError(experience, "Kinh nghiệm phải là một số nguyên không âm.");
                    isValid = false;
                } else {
                    clearError(experience);
                }

                // Kiểm tra lớp
                let grade = document.getElementById("grade");
                if (!/^\d+$/.test(grade.value) || parseInt(grade.value) < 1 || parseInt(grade.value) > 12) {
                    setError(grade, "Lớp phải là một số từ 1 đến 12.");
                    isValid = false;
                } else {
                    clearError(grade);
                }

                return isValid;
            }

            function setError(element, message) {
                let errorContainer = element.nextElementSibling;
                if (!errorContainer || !errorContainer.classList.contains("error-message")) {
                    errorContainer = document.createElement("div");
                    errorContainer.classList.add("error-message");
                    element.parentNode.insertBefore(errorContainer, element.nextSibling);
                }
                errorContainer.textContent = message;
            }

            function clearError(element) {
                let errorContainer = element.nextElementSibling;
                if (errorContainer && errorContainer.classList.contains("error-message")) {
                    errorContainer.textContent = "";
                }
            }
        </script>
    </head>
    <body>
        <div class="header">
            <h5>Điền Thông Tin Giáo Viên</h5>
        </div>
        <div class="nav">
            <img src="img/logoo.png" alt="Logo">
            <a href="tutor_homepage.jsp">Trang Chủ</a>
            <a href="">Thông Tin Cá Nhân</a>
            <a href="registerMenu.html">Đăng ký Class&Slot </a>
            <a href="ViewTutorCalendar">Lịch Dạy</a>
            <a href="ChatController">Chat</a>
            <button type="button" class="logout-button" onclick="window.location.href = 'home.html';">Logout</button>
        </div>
        <div class="container">
            <div class="form-section">
                <h2>Thông Tin Cá Nhân</h2>
                <div class="photo">
                    <img src="<%= request.getAttribute("photoPath")%>" alt="Ảnh giáo viên">
                </div>

                <% if (request.getAttribute("errorMsg") != null) {%>
                <div class="error-message"><%= request.getAttribute("errorMsg")%></div>
                <% }%>

                <form action="UpdateTutorInfoController" method="post" onsubmit="return validateForm()">
                    <label for="name">Tên:</label>
                    <input type="text" id="name" name="name" value="<%= request.getAttribute("name")%>" required=""/>

                    <label for="phone">Số Điện Thoại:</label>
                    <input type="text" id="phonenumber" name="phonenumber" value="<%= request.getAttribute("phonenumber")%>" required=""/>

                    <label for="location">Địa Chỉ:</label>
                    <input type="text" id="location" name="location" value="<%= request.getAttribute("location")%>" required=""/>

                    <label for="yob">Năm Sinh:</label>
                    <input type="number" id="yob" name="yob" value="<%= request.getAttribute("yob")%>" required=""/>  

                    <label for="personalId">Personal Id:</label>
                    <input type="text" id="personalId" name="personalId" value="<%= request.getAttribute("personalId")%>" required=""/>

                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender" required>
                        <option value="Nữ" <%= "Nữ".equals(request.getAttribute("gender")) ? "selected" : ""%>>Nữ</option>
                        <option value="Nam" <%= "Nam".equals(request.getAttribute("gender")) ? "selected" : ""%>>Nam</option>
                    </select>

                    <label for="experience">Experience:</label>
                    <input type="number" id="experience" name="experience" value="<%= request.getAttribute("experience")%>" required=""/>

                    <label for="grade">Grade:</label>
                    <input type="number" id="grade" name="grade" value="<%= request.getAttribute("grade")%>" required=""/>

                    <label for="url">Link URL:</label>
                    <input type="text" id="url" name="url" value="<%= request.getAttribute("url")%>" required=""/>

                    <label for="certificate">Bằng Cấp:</label>
                    <div class="certificate">
                        <img src="<%= request.getAttribute("certificatePath")%>" alt="Ảnh bằng cấp">
                    </div>

                    <!-- Submit Button -->
                    <button type="submit">Lưu thông tin</button>
                </form>
            </div>
        </div>
    </body>
</html>
