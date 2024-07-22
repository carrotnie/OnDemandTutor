<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="payment.SalaryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Salary Details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            padding: 20px;
            max-width: 1200px;
            margin: auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .nav {
            background-color: #001F3F;
            display: flex;
            align-items: center;
            padding: 20px;
            border-bottom: 1px solid #ddd;
        }
        .nav .home-icon {
            font-size: 1.5rem;
            color: white;
            margin-right: 10px;
        }
        .nav .home-icon:hover {
            color: #007BFF;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 18px;
            text-align: left;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
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
        table tbody tr:hover {
            background-color: #ddd;
        }
        .view-more-btn, .back-btn, .approve-btn, .reject-btn, .complete-btn, .transfer-btn {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none; /* Remove underline */
            margin: 2px;
            display: inline-block;
        }
        .view-more-btn:hover, .back-btn:hover, .approve-btn:hover, .reject-btn:hover, .complete-btn:hover, .transfer-btn:hover {
            background-color: #45a049;
        }
        .reject-btn {
            background-color: #f44336;
        }
        .reject-btn:hover {
            background-color: #e53935;
        }
        .hidden {
            display: none;
        }
        /* Modal styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 40%;
            border-radius: 8px; /* Rounded corners */
            box-shadow: 0 4px 8px rgba(0,0,0,0.2); /* Subtle shadow */
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .modal-header {
            font-size: 24px;
            margin-bottom: 15px;
            text-align: center;
        }
        .modal-input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .modal-button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            display: block;
            margin: 0 auto;
        }
        .modal-button:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        function showUploadForm(button) {
            const modal = document.getElementById('uploadModal');
            const form = modal.querySelector('form');
            const salaryId = button.getAttribute('data-salary-id');
            form.salaryId.value = salaryId;
            modal.style.display = "block";
        }

        function closeModal() {
            const modal = document.getElementById('uploadModal');
            modal.style.display = "none";
        }

        window.onclick = function(event) {
            const modal = document.getElementById('uploadModal');
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</head>
<body>
<div class="nav">
    <a href="admin_homepage.jsp" class="home-icon">
        <i class="bi bi-arrow-left-circle"></i>
    </a>
</div>
<div class="container">
    <h2>Quản Lý Lương</h2>
    <table>
        <thead>
            <tr>
                <th>Tên giáo viên</th>
                <th>Số tiền</th>
                <th>Trạng thái</th>
                <th>Tình trạng khóa học</th>
                <th>Ngày kết thúc</th>
                <th>Thông tin chuyển khoản</th>
                <th>Chuyển khoản</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<SalaryDTO> salaries = (List<SalaryDTO>) request.getAttribute("salaries");
                if (salaries != null && !salaries.isEmpty()) {
                    DecimalFormat df = new DecimalFormat("###,###,### đ");
                    Date currentDate = new Date();
                    Calendar calendar = Calendar.getInstance();
                    for (SalaryDTO salary : salaries) {
                        Date endDay = salary.getEndDay();
                        calendar.setTime(endDay);
                        calendar.add(Calendar.DAY_OF_YEAR, 7);
                        Date endDayPlus7 = calendar.getTime();
            %>
            <tr>
                <td><%= salary.getTutorName() %></td>
                <td><%= df.format(salary.getBalance()) %></td>
                <td><%= salary.getStatus() %></td>
                <td>
                    <% if (!"hoàn thành".equalsIgnoreCase(salary.getCoursestatus())) { %>
                        <% if (currentDate.after(endDayPlus7)) { %>
                            <form action="CompleteCourseController" method="post">
                                <input type="hidden" name="salaryId" value="<%= salary.getSalaryId() %>">
                                <button type="submit" class="complete-btn">Hoàn thành</button>
                            </form>
                        <% } else { %>
                            <%= salary.getCoursestatus() %>
                        <% } %>
                    <% } else { %>
                        <%= salary.getCoursestatus() %>
                    <% } %>
                </td>
                <td><%= salary.getEndDay() %></td>
                <td>
                    <form action="ViewSalaryDetailsController" method="get">
                        <input type="hidden" name="tutorId" value="<%= salary.getTutorId() %>">
                        <button type="submit" class="view-more-btn">Xem</button>
                    </form>
                </td>
                <td>
                    <% if ("hoàn thành".equalsIgnoreCase(salary.getCoursestatus())) { %>
                        <button type="button" class="transfer-btn" data-salary-id="<%= salary.getSalaryId() %>" onclick="showUploadForm(this)">Chuyển khoản</button>
                    <% } %>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan='8'>Không tìm thấy bản ghi lương.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<!-- The Modal -->
<div id="uploadModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <div class="modal-header">Nạp Tiền</div>
        <form action="SalaryController" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="transfer">
            <input type="hidden" name="salaryId" value="">
            <label for="imageFile">Hình ảnh xác nhận:</label>
            <input type="file" id="imageFile" name="imageFile" class="modal-input" required>
            <button type="submit" class="modal-button">Lưu</button>
        </form>
    </div>
</div>

</body>
</html>
