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
            background-color: #001e54;
            color: white;
            padding: 20px;
            display: flex;
            justify-content: center;
            border-radius: 10px;
            max-width: 1000px;
            margin: 20px auto; /* Add margin for vertical spacing */
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
    </style>
</head>
<body>
    <div class="header">
        <h5>Chào mừng đến với trung tâm gia sư miễn phí</h5>
    </div>
    <div class="nav">
        <img src="img/logoo.png" alt="Logo">
        <a href="tutor_homepage.jsp">Trang Chủ</a>
        <a href="ViewTutorInfoController">Thông Tin Cá Nhân</a>
        <a href="registerMenu.html">Đăng ký Class&Slot</a>
        <a href="ViewTutorCalendar">Lịch Dạy</a>
        <a href="ChatController">Chat</a> <!-- Nút Chat -->
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
