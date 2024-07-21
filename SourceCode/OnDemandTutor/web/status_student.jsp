<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tình trạng xác nhận đăng ký học</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f9f9f9;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            width: 90%;
            max-width: 1200px;
            text-align: center; /* Center the title */
        }
        h1 {
            margin-bottom: 20px;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            text-align: left;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
        }
        th {
            background-color: #001e54;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .back-button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #001e54;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Tình trạng xác nhận đăng ký học</h1>
        <c:if test="${not empty schedules}">
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th>Ngày học</th>
                        <th>Giờ bắt đầu</th>
                        <th>Giờ kết thúc</th>
                        <th>Tên gia sư</th>
                        <th>Tình trạng</th>
                        <th>Tên môn học</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="schedule" items="${schedules}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${schedule.startDay}</td>
                            <td>${schedule.endDay}</td>
                            <td>${schedule.dayOfSlot}</td>
                            <td>${schedule.startTime}</td>
                            <td>${schedule.endTime}</td>
                            <td>${schedule.tutorName}</td>
                            <td>${schedule.status}</td>
                            <td>${schedule.subjectName}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty schedules}">
            <p>Hiện bạn chưa có lịch học nào</p>
        </c:if>
        <a href="MainController?action=StudentPage" class="back-button">Quay lại trang chủ</a>
    </div>
</body>
</html>
