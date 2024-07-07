<%-- 
    Document   : registerSlot
    Created on : Jul 6, 2024, 2:01:05 AM
    Author     : Long Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Class</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 50%;
                margin: auto;
                padding: 20px;
                background-color: white;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                margin-top: 50px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input, .form-group select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
                margin-bottom: 10px;
                font-size: 16px;
            }
            .form-group button, .form-group .button-back {
                padding: 10px 20px;
                font-size: 16px;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease;
                margin-right: 10px;
            }
            .form-group button {
                background-color: #007bff;
            }
            .form-group button:hover {
                background-color: #0056b3;
                transform: translateY(-2px);
                box-shadow: 0 4px 10px rgba(0, 91, 187, 0.2);
            }
            .form-group .button-back {
                background-color: #6c757d;
                text-decoration: none;
                display: inline-block;
                text-align: center;
            }
            .form-group .button-back:hover {
                background-color: #5a6268;
                transform: translateY(-2px);
                box-shadow: 0 4px 10px rgba(90, 98, 104, 0.2);
            }
            .alert {
                color: red;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Insert Slot</h2>
            <form action="MainController" method="post">
                <div class="form-group">
                    <label for="dayOfSlot">Ngày trong tuần:</label>
                    <select id="dayOfSlot" name="dayOfSlot" required>
                        <option value="Thứ Hai">Thứ Hai</option>
                        <option value="Thứ Ba">Thứ Ba</option>
                        <option value="Thứ Tư">Thứ Tư</option>
                        <option value="Thứ Năm">Thứ Năm</option>
                        <option value="Thứ Sáu">Thứ Sáu</option>
                        <option value="Thứ Bảy">Thứ Bảy</option>
                        <option value="Chủ Nhật">Chủ Nhật</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="startTime">Start Time (HH:mm):</label>
                    <input type="time" id="startTime" name="startTime" required>
                </div>
                <div class="form-group">
                    <label for="endTime">End Time (HH:mm):</label>
                    <input type="time" id="endTime" name="endTime" required>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert">${errorMessage}</div>
                </c:if>
                <div class="form-group">
                    <button type="submit" name="action" value="RegisterSlot">Register</button>
                    <a href="registerMenu.html" class="button-back">Back to Register Menu</a>
                </div>
            </form>
        </div>
    </body>
</html>

