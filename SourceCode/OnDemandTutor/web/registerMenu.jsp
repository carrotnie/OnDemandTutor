

<%@page import="registerClassSlotTutor.ClassDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu page</title>
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
            .form-section {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 100%;
                text-align: center;
            }
            .form-section h2 {
                margin-top: 0;
                color: #001F3F;
                text-align: center; /* Chỉnh căn lề cho tiêu đề */
                font-size: 24px;
            }
            .content {
                margin-top: 20px;
            }
            .button-link {
                display: inline-block;
                padding: 15px 30px;
                font-size: 18px;
                color: #001F3F;
                background-color: #ffffff;
                text-align: center;
                text-decoration: none;
                border-radius: 10px;
                margin: 10px;
                transition: background-color 0.3s ease, transform 0.3s ease;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
            .button-link:hover {
                background-color: #0056b3;
                transform: translateY(-5px);
                box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15);
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
            <a href="">Đăng ký Class&Slot</a>
            <a href="ViewTutorCalendar">Lịch Dạy</a>
            <a href="ChatController">Chat</a>
            <a href="ViewBalanceTutorController">Tiền lương</a>
            <button type="button" class="logout-button" onclick="window.location.href = 'home.html';">Logout</button>
        </div>
        <div class="container">
            <div class="content">
                <%
                    Boolean isApproved = (Boolean) request.getAttribute("isApproved");
                    if (isApproved == null) {
                        isApproved = false; // Đặt giá trị mặc định nếu null
                    }
                %>
                <a href="InsertClassServlet" class="button-link" <%= isApproved ? "" : "style='display:none;'"%>>Đăng Ký Lớp Học</a>
                <a href="registerSlot.jsp" class="button-link" <%= isApproved ? "" : "style='display:none;'"%>>Đăng Ký Slot</a>
                <% if (!isApproved) { %>
                <p>Bạn cần được admin duyệt trước khi đăng ký Class hoặc Slot.</p>
                <% }%>
            </div>
        </div>
    </body>
</html>
