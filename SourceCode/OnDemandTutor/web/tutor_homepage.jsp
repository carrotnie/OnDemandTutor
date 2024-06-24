<%-- 
    Document   : tutor_homepage
    Created on : Jun 16, 2024, 1:15:29 AM
    Author     : Long Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ Giáo Viên - Giasumienphi.edu.vn</title>
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
        .welcome-message {
            background-color: #007BFF;
            color: white;
            text-align: center;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .info-section {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .info-section h2 {
            margin-top: 0;
        }
        .info-section p {
            margin: 5px 0;
        }
        .students-list {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .students-list h2 {
            margin-top: 0;
        }
        .students-list ul {
            list-style-type: none;
            padding: 0;
        }
        .students-list ul li {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .students-list ul li:last-child {
            border-bottom: none;
        }
        .actions {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }
        .actions a {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            font-weight: bold;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="header">
        <h5>Chào mừng đến với trung tâm gia sư miễn phí</h5>
    </div>
    <div class="nav">
        <img src="img/logo.png" alt="Logo">
        <a href="tutor_homepage.jsp">Trang Chủ</a>
        <a href="ViewTutorInfoController">Thông Tin Cá Nhân</a>
        <a href="#">Học Sinh</a>
        <a href="ViewTutorCalendar">Lịch Dạy</a>
        <a href="#" class="logout-button">Đăng Xuất</a>
    </div>
    <div class="container">
        <div class="welcome-message">
            <h2>Chào mừng, Giáo Viên ${sessionScope.LOGIN_USER.name}!</h2>
            <p>Chúng tôi rất vui khi bạn tham gia cùng chúng tôi.</p>
        </div>

    </div>
</body>
</html>
