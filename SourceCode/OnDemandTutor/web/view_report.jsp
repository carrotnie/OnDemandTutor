<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Moderator HomePage</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f5f5f5;
            }
            .sidebar {
                height: 100%;
                width: 250px;
                position: fixed;
                top: 0;
                left: 0;
                background-color: #001F3F;
                padding-top: 20px;
                z-index: 1;
                color: white;
            }
            .sidebar a {
                text-decoration: none;
                color: white;
                display: flex;
                align-items: center;
                padding: 10px 15px;
                font-size: 18px;
                margin: 5px 0;
                transition: background-color 0.3s, color 0.3s;
            }
            .sidebar a i {
                margin-right: 10px;
            }
            .sidebar a:hover {
                background-color: #0056b3;
                color: white;
            }
            .main-content {
                margin-left: 270px;
                padding: 20px;
            }
            .card {
                background-color: white;
                border-radius: 8px;
                padding: 20px;
                margin-bottom: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .card-title {
                margin-bottom: 20px;
                font-weight: bold;
                text-align: center;
            }
            .table-responsive {
                margin-top: 20px;
            }
            .table {
                width: 100%;
                margin-bottom: 1rem;
                color: #212529;
            }
            .table tbody tr {
                background-color: #ffffff;
            }
            .table tbody tr:nth-child(odd) {
                background-color: #f2f2f2;
            }
            .table tbody tr:hover {
                background-color: #e0e0e0;
            }
            .error-message {
                color: red;
                font-weight: bold;
                text-align: center;
            }
            .success-message {
                color: green;
                font-weight: bold;
                text-align: center;
            }
            /* Modal styles */
            .modal {
                display: none; 
                position: fixed; 
                z-index: 999; 
                left: 0;
                top: 0;
                width: 100%; 
                height: 100%; 
                overflow: auto; 
                background-color: rgba(0,0,0,0.4); 
            }
            .modal-content {
                background-color: #fefefe;
                margin: 15% auto; 
                padding: 20px;
                border: 1px solid #888;
                width: 80%; 
                max-width: 300px; 
                text-align: center;
                border-radius: 10px;
            }
            .modal-content button {
                margin: 10px;
            }
            .btn-equal {
                width: 60px; /* Adjust width as needed */
            }
        </style>
        <script>
            function showModal(action, complaintId) {
                var modal = document.getElementById('confirmationModal');
                var modalContent = document.getElementById('modalContent');
                var confirmButton = document.getElementById('confirmButton');
                var cancelButton = document.getElementById('cancelButton');

                if (action === 'confirm') {
                    modalContent.innerHTML = 'Bạn có chắc chắn muốn xác nhận báo cáo này?';
                    confirmButton.onclick = function () {
                        document.getElementById('confirmationForm').action = 'ConfirmReportController';
                        document.getElementById('complaintIdInput').value = complaintId;
                        document.getElementById('actionInput').value = 'confirm'; // Set action
                        document.getElementById('confirmationForm').submit();
                    };
                } else if (action === 'deny') {
                    modalContent.innerHTML = 'Bạn có chắc chắn muốn từ chối báo cáo này?';
                    confirmButton.onclick = function () {
                        document.getElementById('confirmationForm').action = 'ConfirmReportController';
                        document.getElementById('complaintIdInput').value = complaintId;
                        document.getElementById('actionInput').value = 'deny'; // Set action
                        document.getElementById('confirmationForm').submit();
                    };
                }

                modal.style.display = 'block';

                cancelButton.onclick = function () {
                    modal.style.display = 'none';
                };
            }

            window.onclick = function (event) {
                var modal = document.getElementById('confirmationModal');
                if (event.target == modal) {
                    modal.style.display = 'none';
                }
            };

        </script>
    </head>
    <body>
        <div class="sidebar" id="mySidebar">
            <a href="ViewFeedbackController"><i class="bi bi-envelope"></i>Nhận Xét</a>
            <a href="ViewReportController"><i class="bi bi-file-earmark-text"></i>Báo Cáo</a>
            <a href="ViewCvController"><i class="bi bi-file-earmark-person"></i>Hồ Sơ Giáo Viên</a>
            <a href="login.jsp"><i class="bi bi-box-arrow-right"></i>Đăng Xuất</a>
        </div>
        <div class="main-content" id="main">
            <div class="card">
                <h2 class="card-title">Quản Lý Báo Cáo</h2>
                <c:if test="${not empty successMessage}">
                    <div class="success-message">${successMessage}</div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">${errorMessage}</div>
                </c:if>

                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Học Sinh</th>
                                <th>Giáo Viên</th>
                                <th>Lý Do</th>
                                <th>Trạng Thái</th>
                                <th>Quản Lý</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="report" items="${REPORT_LIST}">
                                <tr>
                                    <td>${report.studentName}</td>
                                    <td>${report.tutorName}</td>
                                    <td>${report.content}</td>
                                    <td>${report.status}</td>
                                    <td>
                                        <c:if test="${report.status == 'đang xử lý'}">
                                            <button type="button" class="btn btn-primary btn-sm btn-equal" onclick="showModal('confirm', ${report.complaintId})">Có</button>
                                            <button type="button" class="btn btn-danger btn-sm btn-equal" onclick="showModal('deny', ${report.complaintId})">Không</button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- The Modal -->
        <div id="confirmationModal" class="modal">
            <div class="modal-content">
                <p id="modalContent"></p>
                <form id="confirmationForm" method="post">
                    <input type="hidden" id="complaintIdInput" name="complaintId" value=""/>
                    <input type="hidden" id="actionInput" name="action" value=""/>
                    <button type="button" id="confirmButton" class="btn btn-primary btn-sm btn-equal">Có</button>
                    <button type="button" id="cancelButton" class="btn btn-danger btn-sm btn-equal">Không</button>
                </form>
            </div>
        </div>
    </body>
</html>
