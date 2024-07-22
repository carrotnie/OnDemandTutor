<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="payment.SalaryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Balance Details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: auto;
            padding: 20px;
            max-width: 1200px;
            position: relative;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 18px;
            text-align: center;
        }
        table th, table td {
            padding: 12px 15px;
            border: 1px solid #ddd;
        }
        table thead {
            background-color: #f2f2f2;
        }
        table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        table tbody tr:hover {
            background-color: #f1f1f1;
        }
        .view-more-btn, .view-payment-info-btn {
            padding: 8px 16px;
            background-color: #0056b3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .view-more-btn:hover, .view-payment-info-btn:hover {
            background-color: #001e54;
        }
        .full-screen {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.9);
            z-index: 1000;
            justify-content: center;
            align-items: center;
        }
        .full-screen img {
            max-width: 90%;
            max-height: 90%;
        }
        .back-btn {
            padding: 8px 16px;
            background-color: #0056b3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            position: absolute;
            top: 20px;
            right: 20px;
            transition: background-color 0.3s;
        }
        .back-btn:hover {
            background-color: #001e54;
        }
        .nav {
            position: absolute;
            top: 20px;
            left: 20px;
        }
        .back-button {
            font-size: 24px;
            color: #0056b3;
            text-decoration: none;
        }
        .back-button:hover .icon-home {
            color: #001e54;
        }
    </style>
    <script>
        function showFullScreenImage(salaryId) {
            var fullScreenDiv = document.getElementById("full-screen-" + salaryId);
            fullScreenDiv.style.display = "flex";
        }

        function hideFullScreenImage(salaryId) {
            var fullScreenDiv = document.getElementById("full-screen-" + salaryId);
            fullScreenDiv.style.display = "none";
        }
    </script>
</head>
<body>
    <div class="nav">
        <a href="tutor_homepage.jsp" class="back-button">
            <i class="bi bi-house-fill icon-home"></i>
        </a>
    </div>
    <h2>Chi tiết số dư của giáo viên</h2>
    <table>
        <thead>
            <tr>
                <th>Số dư</th>
                <th>Hình ảnh chuyển khoản</th>
                <th>Thông tin chuyển khoản</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<SalaryDTO> salaries = (List<SalaryDTO>) request.getAttribute("salaries");
                DecimalFormat decimalFormat = new DecimalFormat("###,###,### đ");
                if (salaries != null && !salaries.isEmpty()) {
                    for (SalaryDTO salary : salaries) {
            %>
            <tr>
                <td><%= decimalFormat.format(salary.getBalance()) %></td>
                <td>
                    <button class="view-more-btn" onclick="showFullScreenImage('<%= salary.getSalaryId() %>')">Xem thêm</button>
                    <div id="full-screen-<%= salary.getSalaryId() %>" class="full-screen">
                        <img src="img/salary/<%= salary.getSalaryId() %>.jpg" alt="Payment Image">
                        <button class="back-btn" onclick="hideFullScreenImage('<%= salary.getSalaryId() %>')">Quay lại</button>
                    </div>
                </td>
                <td>
                    <form action="ViewPaymentInfoController" method="get">
                        <input type="hidden" name="salaryId" value="<%= salary.getSalaryId() %>">
                        <button type="submit" class="view-payment-info-btn">Xem thông tin chuyển khoản</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="3">Không tìm thấy số dư.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
