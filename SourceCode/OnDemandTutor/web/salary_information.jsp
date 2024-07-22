<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Lương</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css" rel="stylesheet">
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
            height: auto;
            box-sizing: border-box;
            position: relative;
        }
        .nav {
            position: absolute;
            top: 20px;
            left: 20px;
        }
        .nav .home-icon {
            font-size: 2rem;
            color: #001F3F;
            text-align: center;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: transparent;
        }
        .nav .home-icon:hover {
            color: #007BFF;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .info-container {
            margin-top: 20px; /* Adjust this value to move the information closer to the header */
        }
        .info-group {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .info-group label {
            font-weight: bold;
            width: 150px;
        }
        .info-group input {
            flex: 1;
            padding: 10px;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 10px;
            font-family: Arial, sans-serif;
        }
        .info-group input[readonly] {
            background-color: #e9e9f9;
            border-color: #d0d0d0;
            color: #000;
            opacity: 1;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="nav">
        <a href="SalaryController" class="home-icon">
            <i class="bi bi-arrow-left-circle"></i>
        </a>
    </div>
    <div class="header">
        <h2>Thông Tin Chuyển Khoản</h2>
    </div>
    <div class="info-container">
        <div class="info-group">
            <label for="tutorName">Tên Gia Sư:</label>
            <input type="text" id="tutorName" name="tutorName" value="<%= request.getAttribute("tutorName") %>" readonly>
        </div>
        <div class="info-group">
            <label for="bankAccountNumber">Số Tài Khoản:</label>
            <input type="text" id="bankAccountNumber" name="bankAccountNumber" value="<%= request.getAttribute("bankAccountNumber") %>" readonly>
        </div>
        <div class="info-group">
            <label for="bank">Ngân Hàng:</label>
            <input type="text" id="bank" name="bank" value="<%= request.getAttribute("bank") %>" readonly>
        </div>
    </div>
</div>
<!-- Include Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
