<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Register Class</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                width: 50%;
                padding: 20px;
                background-color: white;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
            }
            .form-group {
                margin-bottom: 15px;
                display: flex;
                align-items: center;
            }
            .form-group label {
                width: 150px;
                margin-right: 10px;
                font-weight: bold;
            }
            .form-group input, .form-group select {
                flex: 1;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
                margin-bottom: 10px;
                font-size: 16px;
            }
            .subject-container {
                display: flex;
                justify-content: space-between;
                margin-bottom: 10px;
            }
            .subject-container .subject-item {
                display: flex;
                align-items: center;
            }
            .subject-container .subject-item label {
                margin-left: 5px;
                font-weight: normal;
            }
            .form-actions {
                display: flex;
                justify-content: space-between;
                align-items: center;
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
                box-shadow: 0 4px 6px rgba(0, 123, 255, 0.3);
            }
            .form-group button:hover {
                background-color: #0056b3;
                transform: translateY(-2px);
                box-shadow: 0 6px 8px rgba(0, 91, 187, 0.4);
            }
            .form-group .button-back {
                background-color: #6c757d;
                text-decoration: none;
                display: inline-block;
                text-align: center;
                box-shadow: 0 4px 6px rgba(108, 117, 125, 0.3);
            }
            .form-group .button-back:hover {
                background-color: #5a6268;
                transform: translateY(-2px);
                box-shadow: 0 6px 8px rgba(90, 98, 104, 0.4);
            }
            .alert {
                color: red;
                margin-bottom: 15px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Register Class</h2>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="RegisterClass">
                <div class="form-group">
                    <label for="subjects">Tên môn học:</label>
                    <div class="subject-container" id="subject">
                        <c:forEach var="subject" items="${subjects}">
                            <div class="subject-item">
                                <input type="radio" id="subject${subject.id}" name="subjectId" value="${subject.id}">
                                <label for="subject${subject.id}">${subject.name}</label>
                            </div>
                        </c:forEach>
                        <c:if test="${empty subjects}">
                            <p>Không có môn học nào được tìm thấy.</p>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="amountOfSlot">Amount of Slot:</label>
                    <input type="number" id="amountOfSlot" name="amountOfSlot" required>
                </div>
                <div class="form-group">
                    <label for="startDay">Ngày bắt đầu:</label>
                    <input type="date" id="startDay" name="startDay" required>
                </div>
                <div class="form-group">
                    <label for="endDay">Ngày kết thúc:</label>
                    <input type="date" id="endDay" name="endDay" required>
                </div>
                <c:if test="${not empty errorMessage}">
                    <div class="alert">${errorMessage}</div>
                </c:if>
                <div class="form-group">
                    <button type="submit" name="action" value="RegisterClass">Register</button>
                    <a href="RegisterMenuController" class="button-back">Back to Register Menu</a>
                </div>
            </form>
        </div>
    </body>
</html>
