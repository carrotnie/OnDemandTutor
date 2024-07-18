<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Giáo Viên</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .nav {
            background-color: #F8F9FA;
            display: flex;
            align-items: center;
            padding: 10px 20px;
            border-bottom: 1px solid #ddd;
        }
        .nav img {
            height: 50px;
            margin-right: 20px;
        }
        .nav .approve-button, .nav .reject-button {
            padding: 10px 20px;
            text-decoration: none;
            font-weight: bold;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 10px;
            display: inline-block;
        }
        .nav .approve-button {
            background-color: #28A745;
            color: white;
        }
        .nav .reject-button {
            background-color: #DC3545;
            color: white;
        }
        .container {
            padding: 20px;
        }
        .form-section {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            margin: 0 auto;
        }
        .form-section h2 {
            margin-top: 0;
        }
        .form-section form {
            display: flex;
            flex-direction: column;
        }
        .form-section label {
            margin: 10px 0 5px;
        }
        .form-section input, .form-section select, .form-section textarea {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
        }
        .form-section button {
            margin-top: 20px;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007BFF;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .form-section button:hover {
            background-color: #0056b3;
        }
        .photo img {
            max-width: 150px; /* Set the maximum width of the image */
            height: auto; /* Maintain the aspect ratio */
            display: block; /* Ensure the image is displayed as a block element */
            margin: 10px auto; /* Center the image horizontally */
        }
        .certificate img {
            max-width: 300px; 
            height: auto; 
            display: block; 
            margin: 10px auto; 
        }
        /* Confirmation Modal Styles */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            padding-top: 60px;
        }
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
            max-width: 400px;
            text-align: center;
            border-radius: 10px;
        }
        .modal-button {
            margin: 10px;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 80px; /* Set a fixed width for the buttons */
        }
        .modal-button.confirm {
            background-color: #28A745;
            color: white;
        }
        .modal-button.cancel {
            background-color: #DC3545;
            color: white;
        }
        .modal-button-container {
            display: flex;
            justify-content: center;
            gap: 10px; /* Space between buttons */
        }
        .home-button {
            position: fixed;
            top: 10px;
            left: 10px;
            font-size: 24px;
            color: #007BFF;
            text-decoration: none;
        }
        .reason-input {
            display: block; /* Ensure the input is displayed */
            margin-top: 10px;
            width: 100%; /* Make the textarea full width */
        }
        .modal-content textarea {
            width: 100%; /* Make the textarea full width */
            height: 100px; /* Increase height of the textarea */
            margin-bottom: 10px; /* Add margin below textarea */
        }
        .modal-content .modal-button.confirm.reason-input {
            display: block; /* Ensure the button is displayed as block */
            width: 100px; /* Set a fixed width for the button */
            margin: 0 auto; /* Center the button horizontally */
        }
    </style>
    <script>
        function confirmApproval(event) {
            event.preventDefault();
            document.getElementById('approvalModal').style.display = 'block';
        }

        function confirmRejection(event) {
            event.preventDefault();
            document.getElementById('rejectionModal').style.display = 'block';
        }

        function showRejectionReasonModal() {
            document.getElementById('confirmRejectInitial').style.display = 'none';
            document.getElementById('confirmRejectReason').style.display = 'block';
        }

        function handleApprovalResponse(confirm) {
            if (confirm) {
                document.getElementById('actionForm').action = 'approveTutor'; // URL để duyệt giáo viên
                document.getElementById('actionForm').submit();
            } else {
                document.getElementById('approvalModal').style.display = 'none';
            }
        }

        function handleRejectionResponse(confirm) {
            if (confirm) {
                showRejectionReasonModal();
            } else {
                document.getElementById('rejectionModal').style.display = 'none';
            }
        }

        function submitRejection() {
            var reason = document.getElementById('rejectionReason').value;
            if (reason.trim() === "") {
                alert("Lý do từ chối không được để trống");
                return;
            }
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'rejectionReason';
            input.value = reason;
            document.getElementById('actionForm').appendChild(input);
            document.getElementById('actionForm').action = 'rejectTutor'; // URL để từ chối giáo viên
            document.getElementById('actionForm').submit();
        }
    </script>
</head>
<body>
    <div class="nav">
        <form id="actionForm" method="post" style="margin-left: auto;">
            <%
                String cvActive = request.getParameter("cvActive");
            %>
            <input type="hidden" name="cvId" value="${cvId}">
            <c:set var="cvActive" value="<%= cvActive %>" />
            <c:if test="${cvActive eq 'chưa kiểm duyệt'}">
                <button id="approveButton" class="approve-button" onclick="confirmApproval(event)">Duyệt</button>
                <button id="rejectButton" class="reject-button" onclick="confirmRejection(event)">Từ chối</button>
            </c:if>
        </form>
    </div>
    <div class="container">
        <div class="form-section">
            <h2>Thông Tin Cá Nhân</h2>
            <c:if test="${not empty message}">
                <div style="color: green; font-weight: bold;">${message}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div style="color: red; font-weight: bold;">${errorMessage}</div>
            </c:if>
            <div class="photo">
                <img src="${photoPath}" alt="Ảnh giáo viên">
            </div>
            <form action="ViewCvController" method="post">
                <label for="name">Tên:</label>
                <input type="text" id="name" name="name" value="${name}" required/>

                <label for="phonenumber">Số Điện Thoại:</label>
                <input type="text" id="phonenumber" name="phonenumber" value="${phonenumber}" required/>

                <label for="location">Địa Chỉ:</label>
                <input type="text" id="location" name="location" value="${location}" required/>

                <label for="yob">Ngày Sinh:</label>
                <input type="text" id="yob" name="yob" value="${yob}" required/>  

                <label for="personalId">Personal Id:</label>
                <input type="text" id="personalId" name="personalId" value="${personalId}" required/>

                <label for="gender">Gender:</label>
                <input type="text" id="gender" name="gender" value="${gender}" required/>

                <label for="experience">Experience:</label>
                <input type="text" id="experience" name="experience" value="${experience}" required/>

                <label for="grade">Grade:</label>
                <input type="text" id="grade" name="grade" value="${grade}" required/>

                <label for="url">Link URL:</label>
                <input type="text" id="url" name="url" value="${url}" required/>

                <label for="certificate">Bằng Cấp:</label>
                <div class="certificate">
                    <img src="${certificatePath}" alt="Ảnh bằng cấp">
                </div>
            </form>
        </div>
    </div>
    <!-- Approval Modal -->
    <div id="approvalModal" class="modal">
        <div class="modal-content">
            <p>Bạn có chắc chắn muốn duyệt hồ sơ giáo viên này không?</p>
            <div class="modal-button-container">
                <button class="modal-button confirm" onclick="handleApprovalResponse(true)">Có</button>
                <button class="modal-button cancel" onclick="handleApprovalResponse(false)">Không</button>
            </div>
        </div>
    </div>
    <!-- Rejection Modal -->
    <div id="rejectionModal" class="modal">
        <div class="modal-content" id="confirmRejectInitial">
            <p>Bạn có chắc chắn muốn từ chối hồ sơ giáo viên này không?</p>
            <div class="modal-button-container">
                <button class="modal-button confirm" onclick="handleRejectionResponse(true)">Có</button>
                <button class="modal-button cancel" onclick="handleRejectionResponse(false)">Không</button>
            </div>
        </div>
        <div class="modal-content" id="confirmRejectReason" style="display:none;">
            <p>Lý do bạn từ chối hồ sơ giáo viên này:</p>
            <textarea id="rejectionReason" placeholder="Lý do từ chối"></textarea>
            <button class="modal-button confirm reason-input" onclick="submitRejection()">Xác nhận</button>
        </div>
    </div>
    <!-- Home Icon -->
    <a href="moderator_homepage.jsp" class="home-button"><i class="bi bi-arrow-left-circle"></i></a>
</body>
</html>
