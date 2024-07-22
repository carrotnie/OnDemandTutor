<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Thanh Toán</title>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Include Bootstrap Icons -->
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
            position: relative;
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
        .back-arrow {
            position: absolute;
            top: 20px;
            left: -40px;
            cursor: pointer;
            font-size: 24px;
            color: #000;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .info-container {
            margin-left: 20px;
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
        .info-group .edit-icon {
            cursor: pointer;
            font-size: 18px;
            color: #007bff;
        }
        .btn-save-container {
            display: flex;
            justify-content: center;
        }
        .btn-save {
            padding: 10px 20px;
            background-color: #001e54;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="ViewBalanceTutorController" class="back-arrow">
            <i class="bi bi-arrow-left"></i>
        </a>
        <div class="header">
            <h2>Thông Tin Thanh Toán</h2>
        </div>
        <% if (request.getAttribute("updateSuccess") != null) { %>
        <div class="alert alert-success" role="alert" id="updateSuccessAlert">
            <%= request.getAttribute("updateSuccess") %>
        </div>
        <% } %>
        <form action="UpdatePaymentInfoController" method="post">
            <div class="info-container">
                <div class="info-group">
                    <label for="tutorName">Tên Gia Sư:</label>
                    <input type="text" id="tutorName" name="tutorName" value="<%= request.getAttribute("tutorName") %>" readonly>
                </div>
                <div class="info-group">
                    <label for="bankAccountNumber">Số Tài Khoản:</label>
                    <input type="text" id="bankAccountNumber" name="bankAccountNumber" value="<%= request.getAttribute("bankAccountNumber") %>" readonly>
                    <span class="edit-icon" onclick="enableEdit('bankAccountNumber')">&#9998;</span>
                </div>
                <div class="info-group">
                    <label for="bank">Ngân Hàng:</label>
                    <input type="text" id="bank" name="bank" value="<%= request.getAttribute("bank") %>" readonly>
                    <span class="edit-icon" onclick="enableEdit('bank')">&#9998;</span>
                </div>
            </div>
            <div class="btn-save-container">
                <button type="submit" class="btn-save">Lưu</button>
            </div>
        </form>
    </div>
    <script>
        function enableEdit(fieldId) {
            var field = document.getElementById(fieldId);
            field.removeAttribute('readonly');
        }

        // Tự động ẩn thông báo sau 2 giây
        document.addEventListener("DOMContentLoaded", function() {
            var updateSuccessAlert = document.getElementById("updateSuccessAlert");
            if (updateSuccessAlert) {
                setTimeout(function() {
                    updateSuccessAlert.style.display = "none";
                }, 1000);
            }
        });
    </script>
    <!-- Include Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
