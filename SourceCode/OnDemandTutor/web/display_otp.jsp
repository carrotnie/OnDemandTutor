<%-- 
    Document   : display_otp
    Created on : 19-Jul-2024, 13:09:02
    Author     : Lam Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đổi mật khẩu</title>
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
                text-align: center;
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
            }
            .container button:hover {
                background-color: #ff4136;
            }
            .container .back-button {
                margin-top: 20px;
                background-color: #6c757d;
            }
            .container .back-button:hover {
                background-color: #5a6268;
            }
            .error-message, .success-message {
                color: red;
                text-align: center;
                margin-top: -20px;
                margin-bottom: 20px;
                font-weight: bold;
            }
            .success-message {
                color: green;
            }
            .otp-error-message {
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
            <h2>Đổi mật khẩu</h2>
            <p><strong>OTP của bạn là: </strong><%= request.getAttribute("otp")%></p>
            <form id="resetPasswordForm" action="ResetPasswordController" method="post">
                <input type="hidden" name="username" value="<%= request.getAttribute("username")%>">
                <label for="otp">OTP:</label>
                <input type="text" id="otp" name="otp" required>
                <div class="otp-error-message">
                    <%= request.getAttribute("otpError") != null ? request.getAttribute("otpError") : ""%>
                </div>
                <label for="newPassword">Mật khẩu mới:</label>
                <input type="password" id="newPassword" name="newPassword" required>
                <div class="error-message">
                    <%= request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
                </div>
                <div class="success-message">
                    <%= request.getAttribute("message") != null ? request.getAttribute("message") : ""%>
                </div>
                <button type="submit">Đặt lại mật khẩu</button>
            </form>
            <button class="back-button" onclick="window.location.href = 'login.jsp'">Quay lại trang đăng nhập</button>

        </div>

        <script>
            document.getElementById('newPassword').addEventListener('input', function () {
                var password = this.value;
                var errorMessage = '';
                if (password.length < 6 || password.length > 15) {
                    errorMessage = 'Mật khẩu phải có độ dài từ 6 đến 15 ký tự.';
                } else if (!/^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,15}$/.test(password)) {
                    errorMessage = 'Mật khẩu phải có ít nhất 1 chữ cái viết hoa, 1 chữ số, 1 ký tự đặc biệt và không có khoảng trắng.';
                }
                document.querySelector('.error-message').textContent = errorMessage;
            });

            document.getElementById('resetPasswordForm').addEventListener('submit', function (event) {
                var password = document.getElementById('newPassword').value;
                var otp = document.getElementById('otp').value;
                var otpErrorMessage = '';
                if (otp.length !== 6 || isNaN(otp)) {
                    otpErrorMessage = 'OTP phải là một số có 6 chữ số.';
                }
                document.querySelector('.otp-error-message').textContent = otpErrorMessage;

                var errorMessage = document.querySelector('.error-message').textContent;
                if (errorMessage !== '' || otpErrorMessage !== '') {
                    event.preventDefault(); // Ngăn chặn gửi form nếu có lỗi
                }
            });
        </script>
    </body>
</html>
