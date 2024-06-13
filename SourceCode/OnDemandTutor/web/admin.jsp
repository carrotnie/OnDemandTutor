<%@ page import="user.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin - Giasumienphi.edu.vn</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: auto;
                padding: 0;
            }
            .nav {
                background-color: #001F3F;
                display: flex;
                align-items: center;
                padding: 10px 20px;
                border-bottom: 1px solid #ddd;
                justify-content: space-between; /* Thêm dòng này */
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
                text-align: center;
                margin-left: auto; /* Thêm dòng này */
                margin-right: auto; /* Thêm dòng này */
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
                min-width: 200px;
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
            .account h2 {
                text-align: center;
            }

            /* CSS for Search button */
            .search-button {
                background-color: #4CAF50; /* Màu nền xanh lá cây */
                color: white; /* Màu chữ trắng */
                padding: 10px 20px; /* Đệm bên trong nút */
                border: none; /* Không có viền */
                border-radius: 4px; /* Đường viền cong */
                cursor: pointer; /* Con trỏ chuột thành bàn tay khi rê chuột qua nút */
                transition: background-color 0.3s ease; /* Hiệu ứng chuyển đổi màu nền */
            }

            /* CSS khi hover vào nút */
            .search-button:hover {
                background-color: #45a049; /* Màu nền xanh lá cây nhạt hơn khi hover */
            }

            /* CSS khi nhấn vào nút */
            .search-button:active {
                background-color: #367c39; /* Màu nền xanh lá cây sậm hơn khi nhấn */
            }

        </style>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"admin".equals(loginUser.getRole())) {
                response.sendRedirect("login.html");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <div class="nav">
            <h1> Welcomeback Admin</h1>
        </div>
        <div class="account">
            <h2>Danh sách tài khoản người dùng</h2>
            <form action="SearchController" method="GET">
                <input type="text" name="search" value="<%= search%>"/>
                <button class="search-button" type="submit">Search</button>
            </form>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>FullName</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Role</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<UserDTO> users = (List<UserDTO>) request.getAttribute("LIST_USER");
                        if (users != null) {
                            for (UserDTO user : users) {
                    %>
                    <tr>
                        <td><%= user.getId()%></td>
                        <td><%= user.getName()%></td>
                        <td><%= user.getUsername()%></td>
                        <td>********</td>
                        <td><%= user.getRole()%></td>
                        <td class="action-buttons">
                            <button class="edit-btn">Edit</button>
                            <button class="delete-btn">Delete</button>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                            out.println("<tr><td colspan='6'>Who you wanna find ?</td></tr>");
                        }
                    %>
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
    </body>
</html>
