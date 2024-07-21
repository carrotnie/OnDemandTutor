<%@page import="feedback.FeedbackDTO"%>
<%@page import="feedback.FeedbackDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Feedback Information</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .container {
                width: 90%;
                margin: 20px auto;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #ddd;
            }
            .feedback-icon {
                cursor: pointer;
                color: #007bff;
            }
            .feedback-icon:hover {
                color: #0056b3;
            }
            .rating input {
                display: none;
            }
            .rating label {
                font-size: 20px;
                color: #ddd;
                cursor: pointer;
            }
            .rating label:hover,
            .rating label:hover ~ label,
            .rating input:checked ~ label {
                color: #ffd700;
            }
            .rating input:checked ~ label ~ label {
                color: #ddd;
            }
            .feedback-form-container {
                display: none;
                width: 90%;
                margin: 0 auto;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin-top: 20px;
            }
            .rating-container, .feedback-container {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
            }
            .rating-container label, .feedback-container label {
                width: 100px;
            }
            .rating-container .rating {
                display: flex;
            }
            .back-link {
                font-size: 1.5rem;
            }
            .submit-btn {
                display: block;
                margin: 0 auto;
            }
            .centered-title {
                text-align: center;
            }
        </style>
        <script>
            function showFeedbackForm() {
                document.getElementById("feedbackForm").style.display = "block";
            }

            function fillStars() {
                const stars = document.querySelectorAll('.rating label');
                stars.forEach((star, index) => {
                    star.style.color = '#ffd700';
                    if (index === stars.length - 1) {
                        star.previousElementSibling.checked = true;
                    }
                });
            }

            document.addEventListener('DOMContentLoaded', function () {
                const stars = document.querySelectorAll('.rating label');
                stars.forEach((star, index) => {
                    star.addEventListener('mouseover', () => {
                        stars.forEach((s, i) => {
                            if (i <= index) {
                                s.style.color = '#ffd700';
                            } else {
                                s.style.color = '#ddd';
                            }
                        });
                    });
                    star.addEventListener('mouseout', () => {
                        stars.forEach((s) => {
                            s.style.color = '#ddd';
                        });
                        const checkedStar = document.querySelector('.rating input:checked');
                        if (checkedStar) {
                            const checkedIndex = Array.from(stars).indexOf(checkedStar.nextElementSibling);
                            stars.forEach((s, i) => {
                                if (i <= checkedIndex) {
                                    s.style.color = '#ffd700';
                                } else {
                                    s.style.color = '#ddd';
                                }
                            });
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
        <div class="container">
            <a href="MainController?action=StudentPage" class="back-link">
                <i class="bi bi-arrow-left-circle"></i>
            </a><br/>
            <h2 class="centered-title">Feedback Information</h2>
            <%
                List<FeedbackDTO> feedbackList = (List<FeedbackDTO>) request.getAttribute("feedbackList");
                if (feedbackList == null || feedbackList.isEmpty()) {
                    out.println("<p>Không tìm thấy lớp học.</p>");
                    out.println("<p>Lưu ý: Bạn chỉ được đánh giá giảng viên trong 2 tuần kể từ ngày học cuối cùng.</p>");
                } else {
                    int no = 1;
            %>
            <table border="1">
                <tr>
                    <th>No</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th>Tên gia sư</th>
                    <th>Tên môn học</th>
                    <th>Feedback</th>
                </tr>
                <%
                    for (FeedbackDTO feedback : feedbackList) {
                %>
                <tr>
                    <td><%= no++%></td> 
                    <td><%= feedback.getStartDay()%></td>
                    <td><%= feedback.getEndDay()%></td>
                    <td><%= feedback.getTutorName()%></td>
                    <td><%= feedback.getSubjectName()%></td>
                    <td><i class="bi bi-pencil-square feedback-icon" 
                           onclick="showFeedbackForm('<%= feedback.getTutorId()%>',
                                           '<%= feedback.getStudentId()%>',
                                           '<%= feedback.getSlotId()%>',
                                           '<%= feedback.getSubjectId()%>',
                                           '<%= feedback.getModId()%>')"></i></td>
                </tr>
                <%
                    }
                %>
            </table>
            <%
                }
            %>
        </div>

        <!-- Feedback Form -->
        <div class="container feedback-form-container" id="feedbackForm" style="display: none;">
            <h2 style="text-align: center;">Đánh giá Khóa học</h2>
            <form action="submitFeedbackController" method="post">
                <input type="hidden" id="tutorId" name="tutorId">
                <input type="hidden" id="studentId" name="studentId">
                <input type="hidden" id="slotId" name="slotId">
                <input type="hidden" id="subjectId" name="subjectId">
                <input type="hidden" id="modId" name="modId">
                <div class="rating-container">
                    <label for="rating">Rating</label>
                    <div class="rating">
                        <input type="radio" id="star1" name="rating" value="1">
                        <label for="star1">&#9733;</label>
                        <input type="radio" id="star2" name="rating" value="2">
                        <label for="star2">&#9733;</label>
                        <input type="radio" id="star3" name="rating" value="3">
                        <label for="star3">&#9733;</label>
                        <input type="radio" id="star4" name="rating" value="4">
                        <label for="star4">&#9733;</label>
                        <input type="radio" id="star5" name="rating" value="5">
                        <label for="star5">&#9733;</label>
                        <input type="radio" id="star6" name="rating" value="6">
                        <label for="star6">&#9733;</label>
                        <input type="radio" id="star7" name="rating" value="7">
                        <label for="star7">&#9733;</label>
                        <input type="radio" id="star8" name="rating" value="8">
                        <label for="star8">&#9733;</label>
                        <input type="radio" id="star9" name="rating" value="9">
                        <label for="star9">&#9733;</label>
                        <input type="radio" id="star10" name="rating" value="10">
                        <label for="star10">&#9733;</label>
                    </div>
                </div>
                <div class="feedback-container">
                    <label for="feedback">Feedback</label>
                    <textarea class="feedback" id="feedback" name="feedback" placeholder="Nhập phản hồi của bạn vào đây..." style="width: 100%;" required></textarea>
                </div>
                <br>
                <button type="submit" class="submit-btn btn btn-primary">Gửi đánh giá</button>
            </form>
        </div>

        <script>
            function showFeedbackForm(tutorId, studentId, slotId, subjectId, modId) {
                document.getElementById('tutorId').value = tutorId;
                document.getElementById('studentId').value = studentId;
                document.getElementById('slotId').value = slotId;
                document.getElementById('subjectId').value = subjectId;
                document.getElementById('modId').value = modId;
                document.getElementById('feedbackForm').style.display = 'block';
                fillStars();
            }

            function fillStars() {
                const stars = document.querySelectorAll('.rating label');
                stars.forEach((star, index) => {
                    star.style.color = '#ffd700';
                    if (index === stars.length - 1) {
                        star.previousElementSibling.checked = true;
                    }
                });
            }

            document.addEventListener('DOMContentLoaded', function () {
                const stars = document.querySelectorAll('.rating label');
                stars.forEach((star, index) => {
                    star.addEventListener('mouseover', () => {
                        stars.forEach((s, i) => {
                            if (i <= index) {
                                s.style.color = '#ffd700';
                            } else {
                                s.style.color = '#ddd';
                            }
                        });
                    });
                    star.addEventListener('mouseout', () => {
                        stars.forEach((s) => {
                            s.style.color = '#ddd';
                        });
                        const checkedStar = document.querySelector('.rating input:checked');
                        if (checkedStar) {
                            const checkedIndex = Array.from(stars).indexOf(checkedStar.nextElementSibling);
                            stars.forEach((s, i) => {
                                if (i <= checkedIndex) {
                                    s.style.color = '#ffd700';
                                } else {
                                    s.style.color = '#ddd';
                                }
                            });
                        }
                    });
                });
            });
        </script>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
