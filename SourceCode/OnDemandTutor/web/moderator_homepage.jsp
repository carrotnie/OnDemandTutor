<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Moderator HomePage</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #001F3F;
            padding-top: 20px;
            z-index: 1;
            color: white;
        }
        .sidebar a {
            text-decoration: none;
            color: white;
            display: flex;
            align-items: center;
            padding: 10px 15px;
            font-size: 18px;
            margin: 5px 0;
            transition: background-color 0.3s, color 0.3s;
        }
        .sidebar a i {
            margin-right: 10px;
        }
        .sidebar a:hover {
            background-color: #0056b3;
            color: white;
        }
        .main-content {
            margin-left: 270px;
            padding: 20px;
        }
        .card {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .top-member {
            display: flex;
            align-items: center;
        }
        .top-member img {
            border-radius: 50%;
            margin-right: 10px;
        }
        .card-title {
            margin-bottom: 20px;
            font-weight: bold;
            text-align: center; /* Center the text */
        }
    </style>
</head>
<body>
    <div class="sidebar" id="mySidebar">
            <a href="ViewFeedbackController"><i class="bi bi-envelope"></i>Nhận Xét</a>
            <a href="ViewReportController"><i class="bi bi-file-earmark-text"></i>Reports</a>
            <a href="ViewCvController"><i class="bi bi-file-earmark-person"></i>Hồ Sơ Chờ Duyệt</a>
            <a href="ViewCvCheckedController"><i class="bi bi-file-earmark-person"></i>Hồ Sơ Đã Duyệt</a>
            <a href="ViewRejectedCvController"><i class="bi bi-file-earmark-person"></i>Hồ Sơ Đã Bị Từ Chối</a>
            <a href="login.jsp"><i class="bi bi-box-arrow-right"></i>Đăng Xuất</a>
        </div>
    <div class="main-content" id="main">
        <div class="card">
            <h2 class="card-title">Welcome Moderator</h2>
        </div>
    </div>
</body>
</html>
