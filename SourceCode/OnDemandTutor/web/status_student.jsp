<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            height: 100vh;
            background-color: #f0f0f0;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            width: 800px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .status {
            margin-top: 20px;
            padding: 10px;
            background-color: #e7f3fe;
            border: 1px solid #b3d7ff;
            border-radius: 4px;
            display: none;
        }
        .error {
            color: #ff0000;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th {
            background-color: #4682b4;
            color: white;
            padding: 8px;
            text-align: left;
        }
        td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Tình trạng xác nhận đăng ký học</h1>
        <table border="1">
            <tr>
                <th>No</th>
                <th>Start Day</th>
                <th>End Day</th>
                <th>Day of Slot</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Tutor Name</th>
                <th>Status</th>
            </tr>
                <tr>
                    <td>1</td>
                    <td>24/07/2003</td>
                    <td>24/07/2003</td>
                    <td>thứ hai</td>
                    <td>04:30</td>
                    <td>05:30</td>
                    <td>Linh</td>
                    <td>đang xử lý</td>
                </tr>
        </table>
    </div>
</body>
</html>
