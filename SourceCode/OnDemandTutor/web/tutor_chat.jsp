<%-- 
    Document   : tutor_chat
    Created on : 09-Jul-2024, 11:01:08
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
                background-color: #ffffff;
                color: #001F3F;
                text-align: center;
                padding: 0 0;
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
                margin: 20px auto;
            }
            .chat-section {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 100%;
            }
            .chat-section h2 {
                margin-top: 0;
                color: #001F3F;
                text-align: center;
                font-size: 24px;
            }
            #chatWindow {
                border: 1px solid #ddd;
                height: 400px;
                overflow-y: scroll;
                padding: 10px;
                background-color: #fff;
                border-radius: 5px;
                color: black; /* Đổi màu chữ thành đen */
            }
            .message {
                padding: 5px;
                margin: 5px 0;
                border-bottom: 1px solid #eee;
                color: black; /* Đổi màu chữ thành đen */
            }
            button {
                background-color: #001F3F;
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
        </div>
        <div class="nav">
            <img src="img/logoo.png" alt="Logo">
            <a href="tutor_homepage.jsp">Trang Chủ</a>
            <a href="ViewTutorInfoController">Thông Tin Cá Nhân</a>
            <a href="RegisterMenuController">Đăng ký Class&Slot</a>
            <a href="ViewTutorCalendar">Lịch Dạy</a>
            <a href="ChatController">Chat</a>
            <button type="button" class="logout-button" onclick="window.location.href = 'home.html';">Logout</button>
        </div>
        <div class="container">
            <div class="chat-section">
                <h2>Trò chuyện với học sinh</h2>
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

                    var username = "${sessionScope.LOGIN_USER.username}";
                    var name = "${sessionScope.LOGIN_USER.name}";
                    ws.send(username + "|" + name);
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
