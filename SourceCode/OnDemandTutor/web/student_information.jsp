<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="student.StudentDTO" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Cá Nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
            box-sizing: border-box;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
            box-sizing: border-box;
            position: relative;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .photo {
            position: absolute;
            top: 20px;
            left: 20px;
        }
        .photo img {
            max-width: 100px;
            height: auto;
            border-radius: 5px;
        }
        .info-container {
            margin-left: 140px; /* Để tránh ảnh */
        }
        .info-group {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
            height: 40px; /* Đặt chiều cao cố định cho tất cả các hàng */
        }
        .info-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            width: 100px;
            line-height: 40px; /* Đặt dòng căn giữa */
        }
        .info-group input, .info-group select {
            width: calc(100% - 150px);
            height: 100%; /* Đặt chiều cao 100% để khớp với chiều cao hàng */
            padding: 10px;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 5px;
            margin-right: 10px;
            font-family: Arial, sans-serif; /* Đảm bảo phông chữ nhất quán */
        }
        .info-group .select2-container {
            width: calc(100% - 150px) !important;
            height: 100% !important;
            font-family: Arial, sans-serif; /* Đảm bảo phông chữ nhất quán */
        }
        .info-group .select2-selection {
            height: 40px !important; /* Đặt chiều cao cho select2 */
            display: flex;
            align-items: center;
            padding: 0 10px;
            font-family: Arial, sans-serif; /* Đảm bảo phông chữ nhất quán */
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .info-group .select2-selection__rendered {
            font-family: Arial, sans-serif; /* Đảm bảo phông chữ nhất quán */
            line-height: 40px !important; /* Đặt dòng căn giữa */
        }
        .info-group input[readonly], .info-group select[disabled] {
            background-color: #e9e9e9;
            border-color: #d0d0d0;
            color: #000; /* Đảm bảo phông chữ nhất quán */
            opacity: 1; /* Đảm bảo phông chữ rõ ràng */
        }
        .info-group .edit-icon {
            cursor: pointer;
            font-size: 18px;
            margin-left: 10px;
            line-height: 40px; /* Đặt dòng căn giữa */
        }
        .btn-save {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            height: 40px; /* Đặt chiều cao cho nút lưu */
            font-family: Arial, sans-serif; /* Đảm bảo phông chữ nhất quán */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>Thông Tin Cá Nhân</h2>
        </div>
        <div class="photo">
            <img src="<%= request.getAttribute("photoPath") %>" alt="Ảnh học sinh">
        </div>
        <form action="UpdateStudentInfoController" method="post">
            <div class="info-container">
                <div class="info-group">
                    <label for="name">Họ và tên:</label>
                    <input type="text" id="name" name="name" value="<%= request.getAttribute("name") %>" readonly>
                </div>
                <div class="info-group">
                    <label for="gender">Giới tính:</label>
                    <input type="text" id="gender" name="gender" value="<%= request.getAttribute("gender") %>" readonly>
                </div>
                <div class="info-group">
                    <label for="birthYear">Năm sinh:</label>
                    <input type="text" id="birthYear" name="birthYear" value="<%= request.getAttribute("birthYear") %>" readonly>
                </div>
                <div class="info-group">
                    <label for="address">Địa chỉ:</label>
                    <input type="text" id="address" name="address" value="<%= request.getAttribute("address") %>" readonly>
                    <span class="edit-icon" onclick="enableEdit('address')">&#9998;</span>
                </div>
                <div class="info-group">
                    <label for="phone">Điện thoại:</label>
                    <input type="text" id="phone" name="phone" value="<%= request.getAttribute("phone") %>" readonly>
                    <span class="edit-icon" onclick="enableEdit('phone')">&#9998;</span>
                </div>
                <div class="info-group">
                    <label for="class">Lớp:</label>
                    <select id="class" name="class" disabled>
                        <% 
                            Object classAttribute = request.getAttribute("class");
                            int studentClass = 0;
                            if (classAttribute instanceof String) {
                                studentClass = Integer.parseInt((String) classAttribute);
                            } else if (classAttribute instanceof Integer) {
                                studentClass = (Integer) classAttribute;
                            }
                            for (int i = 1; i <= 12; i++) { 
                        %>
                        <option value="<%= i %>" <%= (i == studentClass) ? "selected" : "" %>><%= i %></option>
                        <% } %>
                    </select>
                    <span class="edit-icon" onclick="enableEdit('class')">&#9998;</span>
                </div>
                <button type="submit" class="btn-save">Lưu</button>
            </div>
        </form>
    </div>
    <script>
        function enableEdit(fieldId) {
            var field = document.getElementById(fieldId);
            field.removeAttribute('readonly');
            field.removeAttribute('disabled');
        }
    </script>
</body>
</html>
