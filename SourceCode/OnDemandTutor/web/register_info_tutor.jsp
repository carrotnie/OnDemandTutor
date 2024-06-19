<%-- 
    Document   : registor_info_tutor
    Created on : 18-Jun-2024, 22:50:36
    Author     : Lam Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                background-color: #001F3F;
                color: white;
                text-align: center;
                padding: 10px 0;
                margin: -25px;
            }
            .header h1 {
                margin: 0;
            }
            .nav {
                background-color: #F8F9FA;
                display: flex;
                align-items: center;
                padding: 10px 20px;
                border-bottom: 1px solid #ddd;
            }
            .nav img {
                height: 50px;
                margin-right: 20px;
            }
            .nav a {
                text-decoration: none;
                color: black;
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
        </style>
    </head>
    <body>
        <div class="header">
<h5>Điền Thông Tin Giáo Viên</h5>
        </div>
        <div class="nav">
            <img src="img/logo.png" alt="Logo">
            <a href="tutor_homepage.jsp">Trang Chủ</a>
            <a href="tutor_infor.jsp">Thông Tin Cá Nhân</a>
            <a href="students.html">Học Sinh</a>
            <a href="schedule.html">Lịch Dạy</a>
            <a href="logout.html" class="logout-button">Đăng Xuất</a>
        </div>
        <div class="container">
            <div class="form-section">
                <h2>Thông Tin Cá Nhân</h2>
                <form action="UpdateTutorInfoController" method="post" >
                    <!-- Personal Information -->
                    <label for="name">Tên:</label>
                    <input type="text" id="name" name="name" value="<%= request.getAttribute("name")%>" required=""/>

                    <label for="phone">Số Điện Thoại:</label>
                    <input type="text" id="phonenumber" name="phonenumber" value="<%= request.getAttribute("phonenumber")%>" required=""/>

                    <label for="address">Địa Chỉ:</label>
                    <input type="text" id="location" name="location" value="<%= request.getAttribute("location")%>" required=""/>

                    <label for="dob">Ngày Sinh:</label>
                    <input type="text" id="yob" name="yob" value="<%= request.getAttribute("yob")%>" required=""/>       

                    <!-- Submit Button -->
                    <button type="submit">Lưu thông tin</button>
                </form>
            </div>
        </div>
    </body>
</html>
</html>
