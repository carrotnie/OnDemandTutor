<%@page import="java.util.List"%>
<%@page import="tutor.TutorDTO"%>
<%@page import="tutor.TutorDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giasumienphi.edu.vn</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            .header h5 {
                margin: 0;
            }
            .nav {
                background-color: #001F3F;
                display: flex;
                align-items: center;
                padding: 10px 20px;
                border-bottom: 1px solid #ddd;
                color: #000;
            }
            .nav img {
                height: 50px;
                margin-left: 14px;
            }
            .nav a, .nav .dropbtn {
                text-decoration: none;
                color: #ddd;
                font-weight: bold;
                padding: 14px 20px;
                margin: 0 5px;
                display: inline-block;
            }
            .nav .dropdown {
                position: relative;
            }
            .nav .dropbtn {
                background-color: inherit;
                border: none;
                font-weight: bold;
                cursor: pointer;
            }
            .nav .dropdown-content {
                display: none;
                position: absolute;
                background-color: #001F3F;
                min-width: 160px;
                box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }
            .nav .dropdown-content a {
                color: white;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                text-align: left;
            }
            .nav .dropdown-content a:hover {
                background-color: #575757;
            }
            .nav .dropdown:hover .dropdown-content {
                display: block;
            }
            .nav .nav-links {
                display: flex;
                flex-grow: 1;
                justify-content: center;
            }
            .register-button {
                background-color: #FF4136;
                color: white;
                padding: 10px 20px;
                text-decoration: none;
                font-weight: bold;
                border-radius: 5px;
                margin-left: auto;
            }
            .slideshow-container {
                position: relative;
                max-width: 100%;
                margin: auto;
            }
            .slides {
                display: none;
            }
            .slides img {
                width: 100%;
                height: auto;
            }
            .prev, .next {
                cursor: pointer;
                position: absolute;
                top: 50%;
                width: auto;
                padding: 16px;
                margin-top: -22px;
                color: white;
                font-weight: bold;
                font-size: 18px;
                transition: 0.6s ease;
                border-radius: 0 3px 3px 0;
                user-select: none;
            }
            .next {
                right: 0;
                border-radius: 3px 0 0 3px;
            }
            .prev:hover, .next:hover {
                background-color: rgba(0,0,0,0.8);
            }
            .text {
                color: white;
                font-size: 15px;
                padding: 8px 12px;
                position: absolute;
                bottom: 8px;
                width: 100%;
                text-align: center;
            }
            .active {
                background-color: #717171;
            }
            .dot {
                cursor: pointer;
                height: 15px;
                width: 15px;
                margin: 0 2px;
                background-color: #bbb;
                border-radius: 50%;
                display: inline-block;
                transition: background-color 0.6s ease;
            }
            .dot:hover {
                background-color: #717171;
            }
            .statistics {
                background-color: #001F3F;
                color: white;
                text-align: center;
                padding: 20px 0;
                display: flex;
                justify-content: space-around;
                align-items: center;
                flex-wrap: wrap;
            }
            .statistic {
                text-align: center;
                flex: 1;
                padding: 20px;
                box-sizing: border-box;
                min-width: 200px; 
            }
            .statistic img {
                width: 50px;
                height: 50px;
                margin-bottom: 10px;
            }
            .statistic p {
                margin: 0;
            }
            .statistic .number {
                font-size: 24px;
                font-weight: bold;
                margin-top: 5px;
            }
            .teachers-container {
                display: flex;
                justify-content: center;
                flex-wrap: wrap;
                gap: 20px;
            }

            .teacher-card {
                display: flex;
                flex-direction: column;
                align-items: center;
                text-align: center;
                width: 200px;
            }

            .teacher-image-container {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                overflow: hidden;
            }

            .teacher-image {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <div class="nav">
            <img src="<%=request.getContextPath()%>/img/logoo.png" alt="Logo">
            <div class="nav-links">
                <a href="ViewStuInfoController">Thông tin cá nhân</a>
                <a href="studentTimeTable.jsp">Lịch học</a>
                <a href="#">Tình trạng đăng ký</a>
                <a href="#">Đánh giá khóa học</a>
            </div>
            <div class="search-container">
                <input type="text" class="search-input" placeholder="Tìm kiếm...">
                <button class="search-button">Tìm kiếm</button>
            </div>
        </div>
        <div class="teachers-section">
            <h2>Giáo viên nổi bật</h2>
            <div class="teachers-container">
                <%
                    TutorDAO dao = new TutorDAO();
                    List<TutorDTO> tutors = dao.info();
                    if (tutors.isEmpty()) {
                        out.println("<p>No tutors found.</p>");
                    } else {
                        for (TutorDTO tutor : tutors) {
                %>
                <div class="teacher-card">
                    <div class="teacher-image-container">
                <img src="<%= request.getContextPath()%>/img/tutor/<%= tutor.getId() %>.jpg" alt="<%= tutor.getName() %>" class="teacher-image">
                    </div>
                    <div class="teacher-info">
                        <h3><%= tutor.getName()%></h3>
                        <p>Môn: <%= tutor.getSubjectName()+1%></p>
                        <p>Đánh giá: <%= String.format("%.1f", tutor.getRating())%> ★</p>
                        <a href="#" class="btn">Xem thêm</a>
                    </div>
                </div>
                <%
                    }}
                %>
            </div>
        </div>

    </body>
</html>
