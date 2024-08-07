<%-- 
    Document   : thankYou
    Created on : Jul 2, 2024, 12:46:33 PM
    Author     : ASUS
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thank You</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            border-radius: 8px;
        }
        h1 {
            color: #333;
        }
        p {
            color: #666;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: #fff;
            background-color: #007bff;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thank You!</h1>
        <p>Phản hồi của bạn đã được gửi thành công.</p> 
        <p>Chúng tôi rất trân trọng ý kiến của bạn và sẽ sử dụng nó để cải thiện dịch vụ của chúng tôi.</p>
        <a href="MainController?action=StudentPage">Quay lại trang chủ</a>
    </div>
</body>
</html>

