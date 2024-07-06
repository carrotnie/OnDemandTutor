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
            }
            .container {
                width: 50%;
                margin: auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
            }
            .form-group input {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
            }
            .form-group button {
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .form-group button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Register Class</h2>
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
                    <label for="startTime">Giờ bắt đầu:</label>
                    <input type="time" id="startTime" name="startTime" required>
                </div>
                <div class="form-group">
                    <label for="endTime">Giờ kết thúc:</label>
                    <input type="time" id="endTime" name="endTime" required>
                </div>
                <div class="form-group">
                    <button type="submit" name="action" value="RegisterSlot">Register</button>
                </div>
            </form>
        </div>
    </body>
</html>
