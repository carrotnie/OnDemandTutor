<%@page import="java.text.DecimalFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student Wallet</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                max-width: 600px;
                margin: auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            .balance {
                font-size: 24px;
                font-weight: bold;
                color: #28a745;
            }
            .balance-label {
                font-size: 18px;
                margin-bottom: 10px;
            }
            .btn {
                background-color: #28a745;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }
            .btn:hover {
                background-color: #218838;
            }
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
                width: 80%;
                max-width: 600px;
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
            .confirmation-message {
                display: none;
                color: green;
                font-weight: bold;
                margin-top: 10px;
            }
            .icon-home {
                color: black;
                font-size: 24px;
            }
            .back-button:hover .icon-home {
                color: #717171;
            }
            .nav {
                position: absolute;
                top: 20px;
                left: 20px;
            }
            .formatted-amount {
                font-size: 16px;
                color: #555;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="nav">
            <a href="student_homepage.jsp" class="back-button">
                <i class="bi bi-house-fill icon-home"></i>
            </a>
        </div>
        <div class="container">
            <div class="balance-label">Số dư tài khoản của bạn hiện có:</div>
            <div class="balance">
                <%
                    BigDecimal balance = (BigDecimal) request.getAttribute("balance");
                    if (balance == null) {
                        balance = BigDecimal.ZERO;
                    }
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,### đ");
                    String formattedBalance = decimalFormat.format(balance);
                    out.println(formattedBalance);
                %>
            </div>
            <button class="btn" id="myBtn">Nạp Tiền</button>
            <div class="confirmation-message" id="confirmationMessage">Tiền sẽ được cập nhật khi nhân viên xác nhận.</div>
        </div>

        <!-- Modal -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h2>Nạp Tiền</h2>
                <form id="depositForm" action="DepositController" method="post" enctype="multipart/form-data">
                    <label for="amount">Số tiền muốn nạp:</label>
                    <input type="number" id="amount" name="amount" required>
                    <div class="formatted-amount" id="formattedAmount"></div>
                    <br><br>
                    <label for="image">Hình ảnh xác nhận:</label>
                    <input type="file" id="image" name="image" accept=".jpg" required>
                    <br><br>
                    <button type="submit" class="btn">Nạp Tiền</button>
                </form>
            </div>
        </div>

        <script>
            var modal = document.getElementById("myModal");
            var btn = document.getElementById("myBtn");
            var span = document.getElementsByClassName("close")[0];
            var form = document.getElementById("depositForm");
            var confirmationMessage = document.getElementById("confirmationMessage");
            var amountInput = document.getElementById("amount");
            var formattedAmountDiv = document.getElementById("formattedAmount");

            btn.onclick = function () {
                modal.style.display = "block";
            }

            span.onclick = function () {
                modal.style.display = "none";
            }

            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            form.onsubmit = function (event) {
                event.preventDefault(); // Ngăn chặn việc submit form mặc định
                modal.style.display = "none";
                confirmationMessage.style.display = "block"; // Hiển thị thông báo xác nhận
                setTimeout(function () {
                    form.submit(); // Submit form sau khi hiển thị thông báo
                }, 2000); // Delay 2 giây trước khi submit form
            }

            amountInput.oninput = function () {
                var amount = parseFloat(amountInput.value);
                if (!isNaN(amount)) {
                    var formattedAmount = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
                    formattedAmountDiv.innerText = "Số tiền: " + formattedAmount;
                } else {
                    formattedAmountDiv.innerText = "";
                }
            }
        </script>
    </body>
</html>
