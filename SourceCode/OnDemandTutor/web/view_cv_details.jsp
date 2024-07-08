<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            .nav .approve-button {
                background-color: #28A745;
                color: white;
                padding: 10px 20px;
                text-decoration: none;
                font-weight: bold;
                border-radius: 5px;
                margin-left: auto;
                cursor: pointer;
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
            }
            .modal-button.confirm {
                background-color: #28A745;
                color: white;
            }
            .modal-button.cancel {
                background-color: #dc3545;
                color: white;
            }
            .home-button {
                position: fixed;
                top: 10px;
                left: 10px;
                font-size: 24px;
                color: #007BFF;
                text-decoration: none;
            }
        </style>
        <script>
            function confirmApproval(event) {
                event.preventDefault();
                document.getElementById('confirmationModal').style.display = 'block';
            }

            function handleModalResponse(confirm) {
                if (confirm) {
                    document.getElementById('approvalForm').submit();
                    document.getElementById('approveButton').style.display = 'none';
                } else {
                    document.getElementById('confirmationModal').style.display = 'none';
                }
            }
        </script>
    </head>
    <body>
        <div class="nav">
            <form id="approvalForm" action="ViewCvDetailsController" method="post" style="margin-left: auto;">
                <input type="hidden" name="cvId" value="${cvId}">
                <button id="approveButton" type="submit" class="approve-button" onclick="confirmApproval(event)">Duyệt</button>
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
        <!-- Confirmation Modal -->
        <div id="confirmationModal" class="modal">
            <div class="modal-content">
                <p>Bạn có chắc chắn muốn duyệt hồ sơ giáo viên này không?</p>
                <button class="modal-button confirm" onclick="handleModalResponse(true)">Có</button>
                <button class="modal-button cancel" onclick="handleModalResponse(false)">Không</button>
            </div>
        </div>
        <!-- Home Icon -->
        <a href="moderator_homepage.jsp" class="home-button"><i class="bi bi-arrow-left-circle"></i></a>
    </body>
</html>
