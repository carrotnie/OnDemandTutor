<%-- 
    Document   : tutor_calendar
    Created on : Jun 24, 2024, 7:50:36 AM
    Author     : Long Dinh
--%>

<%@page import="tutor.ScheduleDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lịch Dạy - Giasumienphi.edu.vn</title>
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
                margin: 0;
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
            .schedule-section {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .schedule-section h2 {
                margin-top: 0;
            }
            .schedule-table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            .schedule-table th, .schedule-table td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: left;
            }
            .schedule-table th {
                background-color: #007BFF;
                color: white;
            }
            .schedule-table tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            .no-schedule, .status-message {
                text-align: center;
                padding: 20px;
                color: #999;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h5>Lịch Dạy</h5>
        </div>
        <div class="nav">
            <img src="img/logo.png" alt="Logo">
            <a href="tutor_homepage.jsp">Trang Chủ</a>
            <a href="ViewTutorInfoController">Thông Tin Cá Nhân</a>
            <a href="">Học Sinh</a>
            <a href="">Lịch Dạy</a>
            <a href="logout.html" class="logout-button">Đăng Xuất</a>
        </div>
        <div class="container">
            <div class="schedule-section">
                <h2>Lịch Dạy Của Bạn</h2>
                <div id="statusMessage">
                    <%
                        String status = (String) request.getAttribute("status");
                        if ("đang xử lý".equals(status)) {
                    %>
                    <p class="status-message">Chờ xác nhận đăng ký</p>
                    <% } else if ("thất bại".equals(status)) { %>
                    <p class="status-message">Không thể tải lịch dạy. Vui lòng thử lại sau.</p>
                    <% } %>
                </div>
                <% if ("thành công".equals(status)) {
                        List<ScheduleDTO> schedules = (List<ScheduleDTO>) request.getAttribute("schedules");
                        if (schedules != null && !schedules.isEmpty()) {
                %>
                <table class="schedule-table">
                    <thead>
                        <tr>
                            <th>Ngày</th>
                            <th>Giờ Bắt Đầu</th>
                            <th>Môn Học</th>
                            <th>Học Sinh</th>
                            <th>Trạng Thái</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (ScheduleDTO schedule : schedules) {%>
                        <tr>
                            <td><%= schedule.getStartDay()%></td>
                            <td><%= schedule.getStartTime()%></td>
                            <td><%= schedule.getSubjectName()%></td>
                            <td><%= schedule.getStudentName()%></td>
                            <td><%= schedule.getStatus()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } else { %>
                <p class="no-schedule">Không có lịch dạy nào được tìm thấy.</p>
                <% }
                    }%>
            </div>
        </div>
    </body>
</html>
