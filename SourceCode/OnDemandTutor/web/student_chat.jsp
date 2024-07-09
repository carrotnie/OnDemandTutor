<%-- 
    Document   : student_chat
    Created on : 09-Jul-2024, 11:01:19
    Author     : Lam Le
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chat - Giasumienphi.edu.vn</title>
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
            .chat-section {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .chat-section h2 {
                margin-top: 0;
            }
            #chatWindow {
                border: 1px solid #ddd;
                height: 400px;
                overflow-y: scroll;
                padding: 10px;
                background-color: #fff;
                border-radius: 5px;
            }
            .message {
                padding: 5px;
                margin: 5px 0;
                border-bottom: 1px solid #eee;
            }
            button {
                background-color: #007BFF;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 12px;
            }
            input[type=text] {
                width: 80%;
                padding: 10px;
                margin: 5px 0;
                box-sizing: border-box;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h5>Chào mừng, Học Sinh ${sessionScope.LOGIN_USER.name}!</h5>
        </div>
        <div class="nav">
            <img src="img/logo.png" alt="Logo">
            <a href="student_homepage.jsp">Trang Chủ</a>
            <a href="ViewStudentInfoController">Thông Tin Cá Nhân</a>
            <a href="#">Gia Sư</a>
            <a href="ViewStudentCalendar">Lịch Học</a>
            <a href="#" class="logout-button">Đăng Xuất</a>
        </div>
        <div class="container">
            <div class="chat-section">
                <h2>Trò chuyện với giáo viên</h2>
                <div id="chatWindow"></div>
                <form id="sendMessageForm">
                    <input type="text" id="message" placeholder="Nhập tin nhắn">
                    <button type="submit">Gửi</button>
                </form>
            </div>
        </div>

        <script>
            var ws;
            document.addEventListener("DOMContentLoaded", function () {
                var ws = new WebSocket("ws://localhost:8084/OnDemandTutor/chat");

                ws.onopen = function () {
                    console.log("WebSocket is open now.");
                    var chatWindow = document.getElementById("chatWindow");
                    var messageElement = document.createElement("div");
                    messageElement.classList.add("message");
                    messageElement.textContent = "Connected to chat server.";
                    chatWindow.appendChild(messageElement);
                    chatWindow.scrollTop = chatWindow.scrollHeight;

                    // Send the username (in this case, the student's name) to the server
                    ws.send("${sessionScope.LOGIN_USER.name}");
                };

                ws.onmessage = function (event) {
                    console.log("Received message:", event.data);
                    var chatWindow = document.getElementById("chatWindow");
                    var messageElement = document.createElement("div");
                    messageElement.classList.add("message");
                    messageElement.textContent = event.data;
                    chatWindow.appendChild(messageElement);
                    chatWindow.scrollTop = chatWindow.scrollHeight;
                };

                document.getElementById('sendMessageForm').addEventListener('submit', function (e) {
                    e.preventDefault();
                    var message = document.getElementById('message').value.trim();
                    if (message === "") {
                        console.log("Empty message, not sending.");
                        return; // Không gửi tin nhắn rỗng
                    }
                    console.log("Message input value:", message); // In ra giá trị của message

                    // Hiển thị tin nhắn vừa gửi lên khung chat
                    var chatWindow = document.getElementById("chatWindow");
                    var messageElement = document.createElement("div");
                    messageElement.classList.add("message");
                    messageElement.textContent = message;
                    chatWindow.appendChild(messageElement);
                    chatWindow.scrollTop = chatWindow.scrollHeight;

                    ws.send(message);

                    document.getElementById('message').value = '';
                });
            });
        </script>
    </body>
</html>

