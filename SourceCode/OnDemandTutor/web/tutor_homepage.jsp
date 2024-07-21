<%-- 
    Document   : tutor_homepage
    Created on : Jun 16, 2024, 1:15:29 AM
    Author     : Long Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ Giáo Viên</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://kit.fontawesome.com/a076d05399.js">
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
            justify-content: space-between;
        }
        .nav .left-nav {
            display: flex;
            align-items: center;
        }
        .nav .right-nav {
            display: flex;
            align-items: center;
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
        .nav .icon-button {
            background: none;
            border: none;
            cursor: pointer;
            color: white;
            font-size: 20px;
            margin-left: 20px;
        }
        .nav .logout-button {
            background-color: #FF4136;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            font-weight: bold;
            border-radius: 5px;
            margin-left: 20px;
        }
        .container {
            background-color: #001e54;
            color: white;
            padding: 20px;
            display: flex;
            justify-content: center;
            border-radius: 10px;
            max-width: 1000px;
            margin: 20px auto;
        }
        .welcome-section {
            background-color: #007BFF;
            color: white;
            text-align: center;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
            max-width: 1200px;
            width: 100%;
        }
        .welcome-section h2 {
            margin: 0;
            font-size: 24px;
        }
        .welcome-section p {
            margin: 10px 0 0 0;
            font-size: 18px;
        }
        .border-wrapper {
            background-color: #ffffff;
            border: 2px solid #007BFF;
            border-radius: 10px;
            padding: 20px;
        }
        .alert {
            background-color: #ff9800;
            color: white;
            padding: 15px;
            border-radius: 5px;
            text-align: center;
            margin-bottom: 20px;
        }
        .notification-bell {
            position: relative;
            display: inline-block;
        }
        .notification-count {
            position: absolute;
            top: -10px;
            right: -10px;
            background: red;
            color: white;
            border-radius: 100%;
            padding: 4px 10px;
            font-size: 12px;
            display: none;
        }
        .notification-popup {
            display: none;
            position: absolute;
            top: 40px;
            right: 0;
            background-color: white;
            color: black;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            width: 13cm; /* Đặt chiều rộng thành 10cm */
            height: 15cm; /* Đặt chiều cao thành 15cm */
            border-radius: 10px; /* Thêm border-radius để tạo góc bo tròn */
        }
        .notification-popup.show {
            display: block;
        }
        .notification-popup .menu-item {
            padding: 10px 20px;
            cursor: pointer;
        }
        .notification-popup .menu-item:hover {
            background-color: #f0f0f0;
            border-radius: 10px;
        }
        .notification-popup .menu-item.active {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>
    <div class="nav">
        <div class="left-nav">
            <img src="img/logoo.png" alt="Logo">
            <a href="tutor_homepage.jsp">Trang Chủ</a>
            <a href="CheckTutorInfoController">Thông Tin Cá Nhân</a>
            <a href="registerMenu.html">Đăng ký Class&Slot</a>
            <a href="ViewTutorCalendar">Lịch Dạy</a>
            <a href="ChatController">Chat</a>
        </div>
        <div class="right-nav">
            <div class="notification-bell">
                <button class="icon-button" id="notificationButton">&#128276;</button> <!-- Bell icon -->
                <div class="notification-popup" id="notificationPopup"></div>
                <span class="notification-count" id="notificationCount"></span>
            </div>
            <button type="button" class="logout-button" onclick="window.location.href = 'home.html';">Logout</button>
        </div>
    </div>
    <div class="container">
        <div class="welcome-message">
            <h2>Chào mừng, Giáo Viên ${sessionScope.LOGIN_USER.name}!</h2>
            <p>Chúng tôi rất vui khi bạn tham gia cùng chúng tôi.</p>
        </div>

    </div>

    <script src="tutor.js"></script>
</body>
</html>
