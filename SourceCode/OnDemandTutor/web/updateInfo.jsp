<%-- 
    Document   : updateInfo
    Created on : Jul 20, 2024, 11:02:30 PM
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông Tin Cá Nhân</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .container {
                padding: 20px;
            }
            .form-section {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 800px;
                margin: 0 auto;
            }
            .form-section h2 {
                margin-top: 0;
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
            .back-link {
                font-size: 1.5rem;
            }
            .subject-checkboxes {
                display: flex;
                flex-wrap: wrap;
                gap: 30px;
            }
            .subject-checkboxes label {
                display: flex;
                align-items: center;
                gap: 7px;
                white-space: nowrap;
            }
            .error{
                color: red;
            }
            .hidden {
            display: none;
            }
        </style>
    </head>
<body>
    <%
        UserDTO tutor = (UserDTO) request.getAttribute("tutor");
        String profileImage = (String) request.getAttribute("profileImage");
        String certificateImage = (String) request.getAttribute("certificateImage");
        String certificateImage2 = (String) request.getAttribute("certificateImage2");
        String certificateImage3 = (String) request.getAttribute("certificateImage3");

        if (tutor == null) {
            out.println("<p style='color:red;'>Lỗi: Không tìm thấy thông tin gia sư.</p>");
        } else {
    %>
    <div class="container">
        <div class="form-section">
            <a href="MainController?action=tutorHomePage" class="back-link">
                <i class="bi bi-arrow-left-circle"></i>
            </a><br/>
            <h2>Thông Tin Cá Nhân</h2>
            <!--enctype="multipart/form-data"-->
            <form action="UpdateInsertInfoTutorController" method="post" enctype="multipart/form-data">
                <label for="name">Họ và Tên: </label>
                <input type="text" id="name" name="Name" value="<%= tutor.getName() %>" readonly>
                <span class="error"><%= request.getAttribute("nameError") != null ? request.getAttribute("nameError") : "" %></span>

                <label for="phoneNumber">Số Điện Thoại: </label>
                <input type="tel" id="phoneNumber" name="phoneNumber" value="<%= tutor.getPhoneNumber() %>" required>
                <span class="error"><%= request.getAttribute("phoneNumberError") != null ? request.getAttribute("phoneNumberError") : "" %></span>

                <label for="yob">Năm Sinh: </label>
                <input type="number" id="yob" name="yob" value="<%= tutor.getYob() %>" min="1974" max="2005" required>
                <span class="error"><%= request.getAttribute("yobError") != null ? request.getAttribute("yobError") : "" %></span>

                <label for="personalId">CCCD: </label>
                <input type="text" id="personalId" name="personalId" value="<%= tutor.getPersonalId() %>" placeholder="Số căn cước công dân" required>
                <span class="error"><%= request.getAttribute("personalIdError") != null ? request.getAttribute("personalIdError") : "" %></span>

                <label for="gender">Giới tính: </label>
                <select id="gender" name="gender" required>
                    <option value="" disabled hidden>Chọn giới tính</option>
                    <option value="Nam" <%= "Nam".equals(tutor.getGender()) ? "selected" : "" %>>Nam</option>
                    <option value="Nữ" <%= "Nữ".equals(tutor.getGender()) ? "selected" : "" %>>Nữ</option>
                </select>
                <span class="error"><%= request.getAttribute("genderError") != null ? request.getAttribute("genderError") : "" %></span>

                <label for="subject">Môn:</label>
                <div class="subject-checkboxes">
                    <%
                        List<Integer> selectedSubjects = tutor.getSubjects();
                        String[] allSubjects = {"Toán học", "Vật lý", "Hóa học", "Sinh học", "Tiếng Anh", "Lịch sử", "Địa lý", "Văn học", "Tiếng anh Toeic", "Tiếng anh Ielts", "Tiếng trung", "Tiếng hàn", "Tiếng nhật"};
                        for (int i = 0; i < allSubjects.length; i++) {
                            String subjectName = allSubjects[i];
                            int subjectId = i + 1;
                            boolean isChecked = selectedSubjects.contains(subjectId);
                    %>
                    <label><input type="checkbox" name="subject[]" value="<%= subjectId %>" <%= isChecked ? "checked" : "" %>> <%= subjectName %></label>
                        <% } %>
                </div>
                <span class="error"><%= request.getAttribute("subjectError") != null ? request.getAttribute("subjectError") : "" %></span>

                <label for="grade">Cấp: </label>
                <select id="grade" name="grade" required>
                    <option value="" disabled hidden>Chọn trình độ giảng dạy</option>
                    <option value="1" <%= tutor.getGrade() == 1 ? "selected" : "" %>>Cấp 1</option>
                    <option value="2" <%= tutor.getGrade() == 2 ? "selected" : "" %>>Cấp 2</option>
                    <option value="3" <%= tutor.getGrade() == 3 ? "selected" : "" %>>Cấp 3</option>
                </select>
                <span class="error"><%= request.getAttribute("gradeError") != null ? request.getAttribute("gradeError") : "" %></span>

                <label for="location">Địa Chỉ: </label>
                <input type="text" id="location" name="location" value="<%= tutor.getLocation() %>" placeholder="Quận...,TP.HCM" required>
                <span class="error"><%= request.getAttribute("locationError") != null ? request.getAttribute("locationError") : "" %></span>

                <label for="url">Link Youtube: </label>
                <input type="text" id="url" name="url" value="<%= tutor.getUrl() %>" placeholder="https://www.youtube.com/..." required>
                <span class="error"><%= request.getAttribute("urlError") != null ? request.getAttribute("urlError") : "" %></span>

                <label for="content">Bio: </label>
                <input type="text" id="content" name="content" value="<%= tutor.getContent() %>" placeholder="Chuyên môn của tôi là ..." required>
                <span class="error"><%= request.getAttribute("contentError") != null ? request.getAttribute("contentError") : "" %></span>

                <label for="experience">Kinh Nghiệm: </label>
                <select id="experience" name="experience" required>
                    <option value="" disabled hidden>Số năm kinh nghiệm</option>
                    <% for (int i = 1; i <= 10; i++) { %>
                    <option value="<%= i %>" <%= tutor.getExperience() == i ? "selected" : "" %>><%= i %> năm</option>
                    <% } %>
                </select>
                <span class="error"><%= request.getAttribute("experienceError") != null ? request.getAttribute("experienceError") : "" %></span>

                <label for="profileImage">Ảnh Đại Diện: <a href="<%= profileImage %>" target="_blank">Xem thêm</a></label>
                <input type="file" id="profileImage" name="profile" accept=".jpg">
                <span class="error"><%= request.getAttribute("profileImageError") != null ? request.getAttribute("profileImageError") : "" %></span>

                <label for="certificateImage">Bằng Cấp: <a href="<%= certificateImage %>" target="_blank">Xem thêm</a></label>
                <input type="file" id="certificateImage" name="certificate" accept=".png">
                <span class="error"><%= request.getAttribute("certificateImageError") != null ? request.getAttribute("certificateImageError") : "" %></span>
                
                <label for="certificateImage2">Bằng Cấp 2: (nếu có) <a href="<%= certificateImage2 %>" target="_blank">Xem thêm</a></label>
                    <input type="file" id="certificateImage2" name="certificate2" accept=".png">
                    <span class="error"><%= request.getAttribute("certificateImageError") != null ? request.getAttribute("certificateImageError") : ""%></span>

                <div id="certificate3-container" class="hidden">
                    <label for="certificateImage3">Bằng Cấp 3: (nếu có) <a href="<%= certificateImage3 %>" target="_blank">Xem thêm</a></label>
                    <input type="file" id="certificateImage3" name="certificate3" accept=".png">
                    <span class="error"><%= request.getAttribute("certificateImageError") != null ? request.getAttribute("certificateImageError") : ""%></span>
                </div>

                    <script>
                        document.getElementById('certificateImage2').addEventListener('change', function () {
                            let certificate3Container = document.getElementById('certificate3-container');

                            if (this.files.length > 0) {
                                certificate3Container.classList.remove('hidden');
                            } else {
                                certificate3Container.classList.add('hidden');
                            }
                        });
                    </script>

                <button type="submit" value="updateTutor">Lưu thông tin</button>
            </form>
        </div>
    </div>
    <%
        }
    %>
</body>


</html>
