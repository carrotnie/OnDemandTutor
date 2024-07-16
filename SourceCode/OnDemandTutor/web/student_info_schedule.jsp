<%-- 
    Document   : student_info_schedule
    Created on : 30-Jun-2024, 22:20:31
    Author     : Lam Le
--%>

<%@page import="student.StudentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                max-width: 600px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 10px;
                background-color: #f9f9f9;
            }
            h1 {
                text-align: center;
                color: #333;
            }
            p {
                margin: 10px 0;
            }
            .button-back {
                background-color: #001F3F; /* Màu nền */
                border: none;
                color: white; /* Màu chữ */
                padding: 10px 20px; /* Đệm */
                text-align: center; /* Căn giữa chữ */
                text-decoration: none; /* Bỏ gạch chân */
                display: inline-block; /* Hiển thị dạng khối */
                font-size: 16px; /* Kích thước chữ */
                margin: 4px 2px; /* Khoảng cách */
                cursor: pointer; /* Con trỏ chuột */
                border-radius: 12px; /* Bo góc */
            }
            .button-back button {
                background-color: #001F3F; /* Màu nền */
                border: none;
                color: white; /* Màu chữ */
                padding: 10px 20px; /* Đệm */
                text-align: center; /* Căn giữa chữ */
                text-decoration: none; /* Bỏ gạch chân */
                display: inline-block; /* Hiển thị dạng khối */
                font-size: 16px; /* Kích thước chữ */
                margin: 4px 2px; /* Khoảng cách */
                cursor: pointer; /* Con trỏ chuột */
                border-radius: 12px; /* Bo góc */
            }
            .info-label {
                font-weight: bold;
                display: inline-block;
                width: 150px;
            }
        </style>
    </head>
    <body>
        <div class ="container"
             <h1>Student Details</h1>
            <%
                StudentDTO student = (StudentDTO) session.getAttribute("STUDENT");
                if (student != null) {
            %>
            <p><span class="info-label">Tên:</span> <%= student.getName()%></p>
            <p><span class="info-label">User Name:</span> <%= student.getUsername()%></p>
            <p><span class="info-label">Số Điện Thoại:</span> <%= student.getPhoneNumber()%></p>
            <p><span class="info-label">Địa chỉ:</span> <%= student.getLocation()%></p>
            <%
            } else {
            %>
            <p>No student information available.</p>
            <%
                }
            %>

            <a href="ViewTutorCalendar" class="button-back"><button>Quay về</button></a>
        </div>
    </body>
</html>
