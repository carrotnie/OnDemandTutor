<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập - Giasumienphi.edu.vn</title>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                background-color: #F8F9FA;
                display: flex;
                align-items: center;
                padding: 10px 20px;
                border-bottom: 1px solid #ddd;
            }
            .nav img {
                height: 100px;
                margin-right: 20px;
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
                padding: 50px;
            }

            .image-login {
                flex: 1; /* Phần tử ảnh mở rộng ra theo tỷ lệ so với phần tử form */
                max-width: 50%; /* Chỉ định chiều rộng tối đa của phần tử ảnh */

            }

            .image-login img {
                width: 100%;
                height: auto;

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
                
                margin-bottom: 20px;
                font-weight: bold;
            }

            .login-container input {
                margin-bottom: 40px;
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }

            .login-container button {
                margin-bottom: 30px;
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


        </style>
    </head>
    <body>
        <div class="header">
            <h5>Chào mừng đến với trung tâm gia sư miễn phí</h5>
        </div>
        <div class="nav">
            <img src="img/logo.png" alt="Logo">
           
        </div>
        <div class="main-content" >
            <div class="image-login">
                <img src="img/giasu.jpg" alt="Loginbanner">
            </div>
            <div class="login-container" >
                <h1>Đăng Nhập</h1>
                <form action="MainController" method="POST">
                    <label for="Username">Tên đăng nhập:</label>
                    <input type="text" id="username" name="Username" required>
                    <label for="Password">Mật khẩu:</label>
                    ${requestScope.ERROR}
                    <input type="password" id="password" name="Password" required>
                    <button type="submit" name="action" value="Login" class="button-login">Login</button>                   
                </form>
                <%
                String error=(String) request.getAttribute("ERROR");
                if(error==null) error="";
                %>
                Chưa có tài khoản ? <a href="MainController?action=RegisterPage" class="register-link">Đăng Ký</a>
            </div>
        </div>
        <div class="footer">
            <p>&copy; 2024 Giasumienphi.edu.vn. All rights reserved.</p>
        </div>
    </body>
    <%= error %>
</html>