<%-- 
    Document   : registerClass
    Created on : Jul 6, 2024, 1:41:39 AM
    Author     : Long Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <label for="subjectName">Tên môn học:</label>
                    <select id="subjectName" name="subjectId" required>
                        <option value="1">1: Toán Học</option>
                        <option value="2">2: Vật lý</option>
                        <option value="3">3: Hóa học</option>
                        <option value="4">4: Sinh học</option>
                        <option value="5">5: Tiếng Anh</option>
                        <option value="6">6: Lịch sử</option>
                        <option value="7">7: Địa lý</option>
                        <option value="8">8: Văn học</option>
                        <option value="9">9: Tiếng anh Toeic</option>
                        <option value="10">10: Tiếng anh Ielts</option>
                        <option value="11">11: Tiếng trung</option>
                        <option value="12">12: Tiếng hàn</option>
                        <option value="13">13: Tiếng nhật</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="amountOfSlot">Amount of Slot:</label>
                    <input type="text" id="amountOfSlot" name="amountOfSlot" required>
                </div>
                <div class="form-group">
                    <label for="startDay">Ngày bắt đầu:</label>
                    <input type="date" id="startDay" name="startDay" required>
                </div>
                <div class="form-group">
                    <label for="endDay">Ngày kết thúc:</label>
                    <input type="date" id="endDay" name="endDay" required>
                </div>
                <div class="form-group">
                    <button type="submit" name="action" value="RegisterClass">Register</button>
                </div>
            </form>
        </div>
    </body>
</html>
