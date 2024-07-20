<%@page import="user.UserError"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký - Giasumienphi.edu.vn</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #F8F9FA;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .header {
            background-color: #001F3F;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
        .header h5 {
            margin: 0;
        }
        .nav {
            background-color: #001e54;
            display: flex;
            align-items: center;
            padding: 8px 10px;
            border-bottom: 1px solid #ddd;
        }
        .nav img {
            height: 50px;
            margin-left: 23px;
        }
        .nav a, .nav .dropbtn {
            text-decoration: none;
            color: black;
            font-weight: bold;
            padding: 14px 20px;
            margin: 0 5px;
            display: inline-block;
        }
        .nav .dropdown {
            position: relative;
        }
        .nav .dropbtn {
            background-color: inherit;
            border: none;
            font-weight: bold;
            cursor: pointer;
        }
        .nav .dropdown-content {
            display: none;
            position: absolute;
            background-color: #001F3F;
            min-width: 160px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }
        .nav .dropdown-content a {
            color: white;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }
        .nav .dropdown-content a:hover {
            background-color: #575757;
        }
        .nav .dropdown:hover .dropdown-content {
            display: block;
        }
        .nav .nav-links {
            display: flex;
            flex-grow: 1;
            justify-content: center;
        }
        .register-button {
            background-color: #FF4136;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            font-weight: bold;
            border-radius: 5px;
            margin-left: auto;
        }

        .main-content {
            display: flex;
            padding-left: 50px;
            padding-right: 50px;
        }

        .image-login {
            flex: 1; /* Phần tử ảnh mở rộng ra theo tỷ lệ so với phần tử form */
            max-width: 50%; /* Chỉ định chiều rộng tối đa của phần tử ảnh */
        }

        .image-login img {
            width: 100%;
            margin-top: 50px;
        }

        .login-container {
            flex: 1; /* Phần tử form mở rộng ra theo tỷ lệ so với phần tử ảnh */
            padding: 50px;
        }

        .login-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .login-container form {
            display: flex;
            flex-direction: column;
        }

        .login-container label {
            margin-bottom: 10px;
            font-weight: bold;
        }

        .login-container input {
            margin-bottom: 20px;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .login-container button {
            margin-bottom: 20px;
            padding: 10px;
            font-size: 16px;
            background-color: #001F3F;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .login-container button:hover {
            background-color: #FF4136;
        }

        .register-link:hover {
            color: #FF725C; /* Màu chữ khi hover */
            text-decoration: underline; /* Gạch chân khi hover */
        }
        .footer {
            text-align: center;
            padding: 10px;
            background-color: #001F3F;
            color: white;
        }

        .wrapper {
            margin: 0 auto;
            margin-bottom: 20px;
            display: inline-flex;
            background: #fff;
            height: 40px;
            width: 400px;
            align-items: center;
            justify-content: center;
            border-radius: 5px;
            padding: 10px 10px;
            box-shadow: 5px 5px 30px rgba(0, 0, 0, 0.2);
        }
        .wrapper .option {
            background: #fff;
            height: 100%;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 10px;
            border-radius: 5px;
            cursor: pointer;
            padding: 0 10px;
            border: 2px solid lightgrey;
            transition: all 0.3s ease;
        }
        .wrapper .option .dot {
            height: 20px;
            width: 20px;
            background: #d9d9d9;
            border-radius: 50%;
            position: relative;
        }
        .wrapper .option .dot::before {
            position: absolute;
            content: "";
            top: 4px;
            left: 4px;
            width: 12px;
            height: 12px;
            background: #001F3F;
            border-radius: 50%;
            opacity: 0;
            transform: scale(1.5);
            transition: all 0.3s ease;
        }
        input[type="radio"] {
            display: none;
        }
        #option-1:checked ~ .option-1,
        #option-2:checked ~ .option-2 {
            border-color: #001F3F;
            background: #001F3F;
        }
        #option-1:checked ~ .option-1 .dot,
        #option-2:checked ~ .option-2 .dot {
            background: #fff;
        }
        #option-1:checked ~ .option-1 .dot:before,
        #option-2:checked ~ .option-2 .dot:before {
            opacity: 1;
            transform: scale(1);
        }
        .wrapper .option span {
            font-size: 20px;
            color: #808080;
            margin-left: 5px;
        }
        #option-1:checked ~ .option-1 span,
        #option-2:checked ~ .option-2 span {
            color: #fff;
        }

        /* Thêm lớp CSS cho thông báo lỗi */
        .error-message {
            color: red;
            font-size: 14px;
            margin-top: -15px; /* Đưa thông báo lỗi sát phía dưới của input */
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <%
        UserError userError= (UserError)request.getAttribute("USER_ERROR");
        if(userError==null) userError= new UserError();
        String error=(String)request.getAttribute("ERROR");
        if(error==null) error="";
    %>
    <div class="nav">
        <img src="img/logoo.png" alt="Logo">
    </div>
    <div class="main-content">
        <div class="image-login">
            <img src="img/giasu.jpg" alt="Loginbanner">
        </div>
        <div class="login-container">
            <h1>Đăng Ký</h1>
            <form action="MainController" method="POST">
                <input type="hidden" name="action" value="Register">
                
                <label>Họ và tên:</label>
                <input type="text" id="Name" name="Name" required>
                <div class="error-message"><%= userError.getNameError() %></div>
                
                <label for="Username">Tên đăng nhập:</label>
                <input type="text" id="Username" name="Username" required>
                <div class="error-message"><%= userError.getUsernameError() %></div>
                
                <label>Mật khẩu:</label>
                <input type="Password" id="Password" name="Password" required>
                <div class="error-message"><%= userError.getPasswordError() %></div>
                
                <label>Nhập lại mật khẩu:</label>
                <input type="password" id="Confirm" name="Confirm" required>
                <div class="error-message"><%= userError.getConfirmError() %></div>
                
                <div class="wrapper">
                    <input type="radio" name="Role" value="student" id="option-1" checked>
                    <input type="radio" name="Role" value="tutor" id="option-2">
                    <label for="option-1" class="option option-1">
                        <div class="dot"></div>
                        <span>Học Sinh</span>
                    </label>
                    <label for="option-2" class="option option-2">
                        <div class="dot"></div>
                        <span>Gia Sư</span>
                    </label>
                </div>
                
                <button type="submit" class="button-login" name="submit">Register</button>
            </form>

            Đã có tài khoản ! <a href="MainController?action=LoginPage" class="register-link">Đăng Nhập</a> hoặc 
            <a href="MainController?action=HomePage" class="register-link">Trở về Trang Chủ</a>
        </div>
    </div>
</body>
</html>
