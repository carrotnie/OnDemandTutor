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
        </style>
    </head>
    <body>
        <div class="sidebar" id="mySidebar">
            <a href="MainController?action=ViewFeedback"><i class="bi bi-envelope"></i>Nhận Xét</a>
            <a href="MainController?action=ViewReport"><i class="bi bi-file-earmark-text"></i>Reports</a>
            <a href="MainController?action=ViewCv"><i class="bi bi-file-earmark-person"></i>Hồ Sơ Chờ Duyệt</a>
            <a href="MainController?action=ViewCvChecked"><i class="bi bi-file-earmark-person"></i>Hồ Sơ Đã Duyệt</a>
            <a href="MainController?action=ViewRejectedCv"><i class="bi bi-file-earmark-person"></i>Hồ Sơ Đã Bị Từ Chối</a>
            <a href="MainController?action=HomePage"><i class="bi bi-box-arrow-right"></i>Đăng Xuất</a>
        </div>
        <div class="main-content" id="main">
            <div class="card">
                <h2 class="card-title">Quản Lý Nhận Xét</h2>
                <div class="error-message">
                    <c:if test="${not empty requestScope.errorMessage}">
                        ${requestScope.errorMessage}
                    </c:if>
                </div>
                <div class="success-message">
                    <c:if test="${not empty requestScope.successMessage}">
                        ${requestScope.successMessage}
                    </c:if>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Học Sinh</th>
                                <th>Giáo Viên</th>
                                <th>Nội Dung</th>
                                <th>Quản Lý</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty requestScope.FEEDBACK_LIST}">
                                <tr>
                                    <td colspan="4" class="text-center">Không có nhận xét nào được tìm thấy</td>
                                </tr>
                            </c:if>
                            <c:forEach var="feedback" items="${requestScope.FEEDBACK_LIST}">
                                <tr>
                                    <td>${feedback.studentName}</td>
                                    <td>${feedback.tutorName}</td>
                                    <td>${feedback.feedBackText}</td>
                                    <td>
                                        <form action="ViewFeedbackController" method="post" class="delete-form">
                                            <input type="hidden" name="deleteFeedbackId" value="${feedback.feedbackId}">
                                            <button type="button" class="btn btn-danger btn-sm delete-button">Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div id="deleteModal" class="modal">
            <div class="modal-content">
                <p>Bạn đã chắc chắn muốn xóa nhận xét này chưa?</p>
                <button id="confirmDelete" class="btn btn-danger">Có</button>
                <button id="cancelDelete" class="btn btn-secondary">Không</button>
            </div>
        </div>

        <script>
            // Get the modal
            var modal = document.getElementById("deleteModal");

            // Get the button that opens the modal
            var deleteButtons = document.querySelectorAll(".delete-button");

            // Get the <span> element that closes the modal
            var cancelDelete = document.getElementById("cancelDelete");

            // When the user clicks on the button, open the modal
            deleteButtons.forEach(function (button) {
                button.onclick = function () {
                    var form = this.closest('form');
                    modal.style.display = "block";

                    // When the user clicks on <span> (x), close the modal
                    cancelDelete.onclick = function () {
                        modal.style.display = "none";
                    }

                    // When the user clicks on "Confirm Delete", submit the form
                    var confirmDelete = document.getElementById("confirmDelete");
                    confirmDelete.onclick = function () {
                        form.submit();
                    }
                }
            });

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>
    </body>
</html>
