<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - Giasumienphi.edu.vn</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            .nav {
                background-color: #001F3F;
                display: flex;
                align-items: center;
                padding: 10px 20px;
                border-bottom: 1px solid #ddd;
            }
            .nav img {
                height: 100px;
                margin-right: auto;
            }
            .nav a, .nav .dropbtn {
                text-decoration: none;
                color: black;
                font-weight: bold;
                padding: 14px 20px;
                margin: 0 5px;
                display: inline-block;
            }
            .nav h1 {
                color: red;
                margin-right: 750px;
            }

            .statistics {
                background-color: #001F3F;
                color: white;
                text-align: center;
                padding: 20px 0;
                display: flex;
                justify-content: space-around;
                align-items: center;
                flex-wrap: wrap;
            }
            .statistic {
                text-align: center;
                flex: 1;
                padding: 20px;
                box-sizing: border-box;
                min-width: 200px; /* Ensure the items don't get too small */
            }
            .statistic img {
                width: 50px;
                height: 50px;
                margin-bottom: 10px;
            }
            .statistic p {
                margin: 0;
            }
            .statistic .number {
                font-size: 24px;
                font-weight: bold;
                margin-top: 5px;
            }
            .account {
                padding: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                font-size: 18px;
                text-align: left;
            }
            table thead {
                background-color: #001F3F;
                color: white;
            }
            table th, table td {
                padding: 12px 15px;
                border: 1px solid #ddd;
            }
            table tbody tr:nth-child(even) {
                background-color: #f3f3f3;
            }
            .action-buttons button {
                padding: 8px 12px;
                margin: 0 5px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .edit-btn {
                background-color: #4CAF50;
                color: white;
            }
            .delete-btn {
                background-color: #f44336;
                color: white;
            }
            .account h2{
                text-align: center;

            }
        </style>
    </head>
    <body>
    
    <div class="nav">
        <img src="img/logo.png" alt="Logo">
        <h1> Welcomeback Admin</h1>
    </div>
    <div class="account">
        <h2>Danh sách tài khoản người dùng</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>FullName</th>
                    <th>Password</th>
                    <th>Year of birth</th>
                    <th>Gender</th>
                    <th>Role</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Alo</td>
                    <td>********</td>
                    <td>01/01/2000</td>
                    <td>Nam</td>
                    <td>Học sinh</td>
                    <td class="action-buttons">
                        <button class="edit-btn">Edit</button>
                        <button class="delete-btn">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Alooo</td>
                    <td>********</td>
                    <td>02/02/1990</td>
                    <td>Nữ</td>
                    <td>Gia Sư</td>
                    <td class="action-buttons">
                        <button class="edit-btn">Edit</button>
                        <button class="delete-btn">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Aloooo </td>
                    <td>********</td>
                    <td>03/03/1985</td>
                    <td>Nam</td>
                    <td>Gia Sư</td>
                    <td class="action-buttons">
                        <button class="edit-btn">Edit</button>
                        <button class="delete-btn">Delete</button>
                    </td>
                </tr>

            </tbody>
        </table>
    </div>
    <div class="statistics">
        <div class="statistic">
            <img src="img/student-icon.png" alt="Student Icon">
            <p>Tổng số học sinh</p>
            <p class="number" id="student-count">1000</p>
        </div>
        <div class="statistic">
            <img src="img/teacher-icon.png" alt="Teacher Icon">
            <p>Tổng giáo viên</p>
            <p class="number" id="teacher-count">500</p>
        </div>
        <div class="statistic">
            <img src="img/graduate-icon.png" alt="Graduate Icon">
            <p>Số học sinh giỏi sau khi học tại trung tâm</p>
            <p class="number" id="graduate-count">700</p>
        </div>
        <div class="statistic">
            <img src="img/parent-icon.png" alt="Parent Icon">
            <p>Số phụ huynh quay lại đăng ký cho con em</p>
            <p class="number" id="parent-count">500</p>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/countup.js/2.0.7/countUp.min.js"></script>
    <script>

    </script>
    ${requestScope.ERROR}
</body>
</html>
