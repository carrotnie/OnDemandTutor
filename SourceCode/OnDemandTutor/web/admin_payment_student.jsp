<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="payment.PaymentDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin Payments</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">
    <style>
        /* Existing styles */
        body {
            font-family: Arial, sans-serif;
            margin: auto;
            padding: 0;
        }
        .container {
            padding: 20px;
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
        table tbody tr:hover {
            background-color: #ddd;
        }
        .view-more-btn, .back-btn, .approve-btn, .reject-btn {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .view-more-btn:hover, .back-btn:hover, .approve-btn:hover, .reject-btn:hover {
            background-color: #45a049;
        }
        .reject-btn {
            background-color: #f44336;
        }
        .reject-btn:hover {
            background-color: #e53935;
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
        .full-screen .back-btn {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        /* New styles for the dropdown */
        .form-select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            appearance: none; /* Remove default dropdown arrow */
            -webkit-appearance: none;
            -moz-appearance: none;
            background: url('data:image/svg+xml;utf8,<svg fill="none" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5H7z" fill="rgba(0, 0, 0, 0.54)"/></svg>') no-repeat right 10px center;
            background-color: white; /* Ensure the background is white */
            background-size: 16px 16px; /* Size of the arrow */
        }
    </style>
    <script>
        /* Existing scripts */
        function showFullScreenImage(walletId) {
            var fullScreenDiv = document.getElementById("full-screen-" + walletId);
            fullScreenDiv.style.display = "flex";
        }

        function hideFullScreenImage(walletId) {
            var fullScreenDiv = document.getElementById("full-screen-" + walletId);
            fullScreenDiv.style.display = "none";
        }

        function approvePayment(walletId) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "ManagePaymentController", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    location.reload(); // Reload the page to see the updated status
                }
            };
            xhr.send("walletId=" + walletId + "&action=approve");
        }

        function rejectPayment(walletId) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "ManagePaymentController", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    location.reload(); // Reload the page to see the updated status
                }
            };
            xhr.send("walletId=" + walletId + "&action=reject");
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
    <h2>Payment Details</h2>
    <!-- Filter by status -->
    <div style="margin: 10px 0px;" class="col-md-12">
        <form method="GET" action="ManagePaymentController">
            <select class="form-select" onchange="this.form.submit()" name="status">
                <option value="">All</option>
                <option value="thành công" ${param.status == 'thành công' ? 'selected' : ''}>Thành công</option>
                <option value="thất bại" ${param.status == 'thất bại' ? 'selected' : ''}>Thất bại</option>
                <option value="đang xử lý" ${param.status == 'đang xử lý' ? 'selected' : ''}>Đang xử lý</option>
            </select>
        </form>
    </div>
    <table>
        <thead>
            <tr>
                <th>Tên Học Sinh</th>
                <th>Số Tiền</th>
                <th>Trạng Thái</th>
                <th>Hình Ảnh Chuyển Khoản</th>
                <th>Quản Lý</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<PaymentDTO> payments = (List<PaymentDTO>) request.getAttribute("payments");
                if (payments != null && !payments.isEmpty()) {
                    DecimalFormat df = new DecimalFormat("###,###,### đ");
                    for (PaymentDTO payment : payments) {
            %>
            <tr>
                <td><%= payment.getStudentName() %></td>
                <td><%= df.format(payment.getBalance()) %></td>
                <td><%= payment.getStatus() %></td>
                <td>
                    <button class="view-more-btn" onclick="showFullScreenImage('<%= payment.getWalletId() %>')">Xem thêm</button>
                    <div id="full-screen-<%= payment.getWalletId() %>" class="full-screen">
                        <img src="img/payment/<%= payment.getWalletId() %>.jpg" alt="Payment Image">
                        <button class="back-btn" onclick="hideFullScreenImage('<%= payment.getWalletId() %>')">Quay lại</button>
                    </div>
                </td>
                <td>
                    <%
                        if ("đang xử lý".equals(payment.getStatus())) {
                    %>
                    <button class="approve-btn" onclick="approvePayment('<%= payment.getWalletId() %>')">Duyệt</button>
                    <button class="reject-btn" onclick="rejectPayment('<%= payment.getWalletId() %>')">Từ Chối</button>
                    <%
                        }
                    %>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan='5'>No payments found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
