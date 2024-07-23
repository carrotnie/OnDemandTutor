<%@page import="slot.SlotDTO"%>
<%@page import="slot.SlotDAO"%>
<%@page import="java.util.List"%>
<%@page import="tutor.TutorDAO"%>
<%@page import="tutor.TutorDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi tiết giáo viên</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f4f4;
            }
            .container {
                max-width: 1000px; /* Tăng kích thước tối đa để bố cục tốt hơn */
                margin: auto;
                background-color: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
                position: relative;
            }
            .back-icon {
                position: absolute;
                top: 10px;
                left: 20px;
                display: flex;
                align-items: center;
                text-decoration: none;
                color: #001e54; /* Màu xanh Bootstrap */
                font-size: 30px;
                font-weight: bold;
                transition: color 0.3s ease; /* Chuyển màu mượt mà */
            }
            .back-icon i {
                margin-right: 8px;
                transition: color 0.3s ease; /* Chuyển màu mượt mà cho biểu tượng */
            }
            .back-icon:hover {
                color: #4CAF50; /* Màu xanh đậm hơn khi hover */
            }
            .back-icon:hover i {
                color: #4CAF50; /* Đảm bảo màu biểu tượng thay đổi cùng với văn bản */
            }
            .content {
                display: flex;
                align-items: flex-start;
            }
            .teacher-info {
                flex: 1;
                max-width: 200px;
                text-align: left;
                margin: 30px;
            }
            .teacher-info img {
                width: 70%;
                height: auto;
                border-radius: 0; /* Xóa viền bo tròn cho hình vuông */
                margin-bottom: 10px;
            }
            .teacher-info h2 {
                margin: 10px 0;
                color: #001e54;
            }
            .teacher-info p {
                color: #666;
                margin: 5px 0;
            }
            .video-container {
                flex: 1;
                max-width: 680px; /* Đảm bảo video không vượt quá chiều rộng này */
                margin-top: 30px;
                margin-bottom: 10px;
            }
            .video-container iframe {
                width: 100%;
                height: 360px;
                border-radius: 8px;
                border: none; /* Xóa viền */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- Biểu tượng và liên kết trở về trang chủ -->
            <a href="MainController?action=StudentPage" class="back-icon">
                <i class="bi bi-reply-fill"></i> 
            </a>
            <%
                String tutorId = request.getParameter("id");
                if (tutorId == null || tutorId.isEmpty()) {
                    out.println("<p>Không tìm thấy thông tin giáo viên.</p>");
                } else {
                    TutorDAO dao = new TutorDAO();
                    TutorDTO tutor = dao.getTutorById(tutorId);
                    if (tutor != null) { 
            %>
            <div class="content">
                <div class="teacher-info">
                    <img src="<%= request.getContextPath()%>/img/tutor/<%= tutor.getId() %>.jpg" alt="<%= tutor.getName() %>">
                    <h2><%= tutor.getName() %></h2>
                    <p>Môn: <%= tutor.getSubjectName() %></p>
                    <p>Vị Trí: <%= tutor.getLocation() %></p>
                    <p>Giảng dạy: <%= tutor.getGrade() %></p>
                    <p>Đánh giá: <%= String.format("%.1f", tutor.getRating()) %> ★</p>
                    <p>UserName: <%= tutor.getUserName() %></p>
                    <h6>*Note: UserName được dùng để kết nối với GV ở khung Chat</h6>

                    <%
                    SlotDAO slotDAO = new SlotDAO();
                    List<Integer> classIds = slotDAO.getClassIdsByTutorId(tutor.getId()); // Lấy danh sách ClassId dựa trên TutorId
                    out.println("<p>Số lượng lớp: " + classIds.size() + "</p>"); // In ra số lượng classId

                    if (classIds != null && !classIds.isEmpty()) {
                        for (Integer classId : classIds) {
//                            out.println("<p>Class: " + classId + "</p>"); // In ra ID của từng class
                %>
                <a class="btn btn-info mt-2" href="ListSlot?cId=<%= classId %>">Chi Tiết Giờ Học</a><br/>
                <%
                        }
                    } else {
                        out.println("<p>Không có thông tin chi tiết giờ học.</p>");
                    }
                %>
            </div>
                <div class="video-container">
                    <% if (tutor.getYoutubeUrl() != null && !tutor.getYoutubeUrl().isEmpty()) { %>
                        <iframe src="https://www.youtube.com/embed/<%= tutor.getYoutubeUrl() %>" allowfullscreen></iframe>
                        <% } else { %>
                    <p>Không có video YouTube nào.</p>
                    <% } %>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </body>
</html>
