<%@page import="java.sql.SQLException"%>
<%@page import="user.UserDAO"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Điền Thông Tin</title>
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
            .error{
                color: red;
            }
        </style>
    </head>
    <body>
        <%
            String Name = (String) session.getAttribute("USER_NAME");
            Integer userId = (Integer) session.getAttribute("USER_ID");
            if (Name == null) {
                Name = "";
                out.println("<p style='color:red;'>Lỗi: Không tìm thấy tên người dùng trong session.</p>");
            }
            if (userId == null) {
                out.println("<p style='color:red;'>Lỗi: Không tìm thấy USER_ID trong session.</p>");
            }
        %>
        <div class="container">
            <div class="form-section">
                <a href="MainController?action=LoginPage" class="back-link">
                    <i class="bi bi-arrow-left-circle"></i>
                </a><br/>
                <h2>Thông Tin Cá Nhân</h2>
                <form action="InsertInfoStudentController" method="post" enctype="multipart/form-data">
                    <!-- Personal Information -->
                    <label for="name">Họ và Tên:</label>
                    <input type="text" id="name" name="Name" value="<%= request.getParameter("Name")%>" required>
                    <span class="error">
                        <%= (request.getAttribute("nameError") != null) ? request.getAttribute("nameError") : ""%>
                    </span>

                    <label for="gender">Giới tính:</label>
                    <select id="gender" name="gender" required>
                        <option value="" disabled selected hidden>Chọn giới tính</option>
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                    </select>
                    <span class="error">
                        <%= (request.getAttribute("genderError") != null) ? request.getAttribute("genderError") : ""%>
                    </span>

                    <label for="yob">Năm Sinh:</label>
                    <input type="number" id="yob" name="yob" min="2004" max="2023" required>
                    <span class="error">
                        <%= (request.getAttribute("yobError") != null) ? request.getAttribute("yobError") : ""%>
                    </span>

                    <label for="location">Địa Chỉ:</label>
                    <input type="text" id="location" name="location" required>
                    <span class="error">
                        <%= (request.getAttribute("locationError") != null) ? request.getAttribute("locationError") : ""%>
                    </span>

                    <label for="phoneNumber">Số Điện Thoại:</label>
                    <input type="tel" id="phoneNumber" name="phoneNumber" required>
                    <span class="error">
                        <%= (request.getAttribute("phoneNumberError") != null) ? request.getAttribute("phoneNumberError") : ""%>
                    </span>

                    <label for="grade">Chọn Lớp:</label>
                    <select id="grade" name="grade" required>
                        <option value="" disabled selected hidden>Chọn lớp</option>
                        <option value="1">Lớp 1</option>
                        <option value="2">Lớp 2</option>
                        <option value="3">Lớp 3</option>
                        <option value="4">Lớp 4</option>
                        <option value="5">Lớp 5</option>
                        <option value="6">Lớp 6</option>
                        <option value="7">Lớp 7</option>
                        <option value="8">Lớp 8</option>
                        <option value="9">Lớp 9</option>
                        <option value="10">Lớp 10</option>
                        <option value="11">Lớp 11</option>
                        <option value="12">Lớp 12</option>
                    </select>
                    <span class="error">
                        <%= (request.getAttribute("gradeError") != null) ? request.getAttribute("gradeError") : ""%>
                    </span>

                    <!-- Profile Picture Upload -->
                    <label for="profile_picture">Ảnh Đại Diện:</label>
                    <input type="file" id="profile_picture" name="picture" accept=".jpg" required>
                    <span class="error">
                        <%= (request.getAttribute("pictureError") != null) ? request.getAttribute("pictureError") : ""%>
                    </span>

                    <!-- Submit Button -->
                    <button type="submit">Lưu Thông Tin</button>
                </form>
            </div>
        </div>

    </body>
</html>
