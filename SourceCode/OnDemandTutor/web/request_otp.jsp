<%-- 
    Document   : request_otp
    Created on : 19-Jul-2024, 12:19:57
    Author     : Lam Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Yêu cầu OTP</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .container {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 300px;
            }
            .container h2 {
                text-align: center;
            }
            .container form {
                display: flex;
                flex-direction: column;
            }
            .container label {
                margin-bottom: 10px;
                font-weight: bold;
            }
            .container input {
                margin-bottom: 20px;
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .container button {
                padding: 10px;
                font-size: 16px;
                background-color: #001f3f;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-bottom: 10px;
            }
            .container button:hover {
                background-color: #ff4136;
            }
            .buttons {
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .error-message {
                color: red;
                text-align: center;
                margin-top: -20px;
                margin-bottom: 20px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Yêu cầu OTP</h2>
            <form action="RequestOtpController" method="post">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <button type="submit">Yêu cầu OTP</button>
            </form>
            <div class="error-message">
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null && !error.isEmpty()) {
                        out.print(error);
                    }
                %>
            </div>
            <button onclick="window.location.href = 'login.jsp'">Quay lại đăng nhập</button>
        </div>
    </body>
</html>
