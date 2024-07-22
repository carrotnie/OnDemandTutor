<%-- 
    Document   : searchTutors
    Created on : Jun 19, 2024, 3:43:35 PM
    Author     : ASUS
--%>

<%@page import="java.util.List"%>
<%@page import="tutor.TutorDTO"%>
<%@page import="tutor.TutorDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>STUDENT HOME PAGE</title>
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
                background-color: #001e54;
                display: flex;
                align-items: center;
                padding: 5px 20px;
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
                padding: 20px 8px;
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

            /* CSS for the navigation search container */
            .search-container {
                display: flex;
                align-items: center;
                margin-right: 20px; 
            }
            .search-input {
                flex-grow: 1;
                padding: 9px;
                font-size: 13px;
                border: 1px solid #ddd;
                border-radius: 5px 5px; 
                outline: none;
            }
            .search-button{
                padding: 9px 15px;
                font-size: 13px;
                background-color: #ffa500;
                color: white;
                border: none;
                border-radius: 5px 5px ; 
                cursor: pointer;
                margin-left: 4px;
            }
            .logout-button {
                padding: 10px 15px;
                font-size: 15px;
                background-color: #FF4136;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-left: 30px;
                transition: transform 0.3s, box-shadow 0.3s; /* Add transition for hover effect */
            }
            .logout-button:hover {
                background-color: #ff0000; /* Change background color on hover */
                transform: translateY(-3px); /* Slight lift effect */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow effect */
            }
            .search-button:hover {
                background-color: #ff9933; 
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow effect */
            }
            .filter-row .search-button:hover {
                background-color: #FF4136; /* Change background color on hover */
                transform: translateY(-3px); /* Slight lift effect */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow effect */
            }


            /* CSS code for the teacher cards with hover effect */
            .teachers-section {
                padding: 20px;
                background-color: #f8f9fa;
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
                padding: 20px;
                background-color: #ffffff;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s, box-shadow 0.3s;
            }

            .teacher-card:hover {
                transform: translateY(-10px);
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            }

            .teacher-image-container {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                overflow: hidden;
                margin-bottom: 10px;
            }

            .teacher-image {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            .teacher-info {
                text-align: center;
            }

            .teacher-info h3 {
                margin: 10px 0 5px 0;
                font-size: 18px;
                color: #001F3F;
            }

            .teacher-info p {
                margin: 5px 0;
                color: #666;
            }

            .teacher-info .btn {
                display: inline-block;
                margin-top: 10px;
                padding: 10px 20px;
                background-color: #001F3F;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            .teacher-info .btn:hover {
                background-color: #FF4136;
            }

            /* CSS code for the filter row and search button */
            .filter-row {
                display: flex;
                justify-content: center; /* Center the filter row */
                align-items: center;
                margin-top: 20px;
                margin-bottom: 20px;
                gap: 20px; /* Add space between filter elements */
            }

            .filter-row .filter {
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ddd;
                border-radius: 5px;
                width: 300px; /* Set a fixed width for filter elements */
                box-sizing: border-box; /* Ensure padding and border are included in the width */
                margin-bottom: 0; /* Remove bottom margin */
            }

            .filter-row .search-button {
                padding: 10px 20px;
                font-size: 16px;
                background-color: #001F3F;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .filter-row .search-button:hover {
                background-color: #FF4136;
            }

            .filter-row select:focus,
            .filter-row button:focus {
                border-color: #001F3F;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
                outline: none;
            }

        </style>
    </head>
    <body>
        <div class="nav">
            <img src="<%=request.getContextPath()%>/img/logoo.png" alt="Logo">
            <div class="nav-links">
                <a href="MainController?action=checkStudentInfo">Thông tin cá nhân</a>
                <a href="MainController?action=timeTable">Lịch học</a>
                <a href="MainController?action=listClasses">Đăng ký khóa học</a>
                <a href="MainController?action=schedule">Tình trạng đăng ký</a>
                <a href="MainController?action=feedback">Đánh giá khóa học</a>
                <a href="MainController?action=chat">Chat</a>
            </div>
            <form action="SearchTutorController" method="GET">
                <div class="search-container">
                    <input type="text" class="search-input" name="txtSearchTutor" placeholder="Tìm giáo viên...">
                    <button type="submit" class="search-button">Tìm</button>
                    <<button type="button" class="logout-button" onclick="window.location.href = 'MainController?action=HomePage';">Logout</button>
                </div>
            </form>

        </div>

        <!-- HTML code for the filter row -->
        <div class="filter-row">
            <form action="FilterController" method="GET">
                <select name="subject" class="filter">
                    <option value="">Môn học</option>
                    <option value="1">Toán</option>
                    <option value="2">Lí</option>
                    <option value="3">Hóa</option>
                    <option value="4">Sinh</option>
                    <option value="5">Tiếng Anh</option>
                    <option value="6">Sử</option>
                    <option value="7">Địa</option>
                    <option value="8">Văn</option>
                    <option value="9">TOEIC</option>
                    <option value="10">IELTS</option>
                    <option value="11">Chinese</option>
                    <option value="12">Japanese</option>
                    <option value="13">Korean</option>
                </select>

                <select name="district" class="filter">
                    <option value="">Quận</option>
                    <option value="Q1">Quận 1</option>
                    <option value="Q2">Quận 2</option>
                    <option value="Q3">Quận 3</option>
                    <option value="Q4">Quận 4</option>
                    <option value="Q5">Quận 5</option>
                    <option value="Q6">Quận 6</option>
                    <option value="Q7">Quận 7</option>
                    <option value="Q8">Quận 8</option>
                    <option value="Q9">Quận 9</option>
                    <option value="Q10">Quận 10</option>
                    <option value="Q11">Quận 11</option>
                    <option value="Q12">Quận 12</option>
                    <option value="Q.Bình Thạnh">Bình Thạnh</option>
                    <option value="Q.Thủ Đức">Thủ Đức</option>
                    <option value="Q.Gò Vấp">Gò Vấp</option>
                    <option value="Q.Phú Nhuận">Phú Nhuận</option>
                    <option value="Q.Tân Bình">Tân Bình</option>
                    <option value="Q.Tân Phú">Tân Phú</option>
                    <option value="Q.Bình Tân">Bình Tân</option>
                    <option value="Q.Bình Chánh">Bình Chánh</option>
                    <option value="Q.Nhà Bè">Nhà Bè</option>
                    <option value="Q.Cần Giờ">Cần Giờ</option>
                    <option value="Q.Hóc Môn">Hóc Môn</option>
                    <option value="Q.Củ Chi">Củ Chi</option>
                </select>

                <select name="grade" class="filter">
                    <option value="">Cấp</option>
                    <option value="Cấp 1">Cấp 1</option>
                    <option value="Cấp 2">Cấp 2</option>
                    <option value="Cấp 3">Cấp 3</option>
                </select>

                <select name="rating" class="filter">
                    <option value="">Đánh giá</option>
                    <option value="10">10 sao</option>
                    <option value="9">9 sao</option>
                    <option value="8">8 sao</option>
                    <option value="7">7 sao</option>
                    <option value="6">6 sao</option>
                    <option value="5">5 sao</option>
                    <option value="4">4 sao</option>
                    <option value="3">3 sao</option>
                    <option value="2">2 sao</option>
                    <option value="1">1 sao</option>
                </select>

                <button type="submit" class="search-button">Tìm kiếm</button>
            </form>
        </div> 

        <div class="teachers-section">
            <div class="teachers-container">
                <%
                    // Lấy danh sách các gia sư từ request
                    List<TutorDTO> tutors = (List<TutorDTO>) request.getAttribute("tutors");
                    if (tutors == null || tutors.isEmpty()) {
                        // Nếu không có gia sư nào được tìm thấy
                        out.println("<p>No tutors found.</p>");
                    } else {
                        // Duyệt qua danh sách các gia sư và hiển thị
                        for (TutorDTO tutor : tutors) {
                %>
                <div class="teacher-card">
                    <div class="teacher-image-container">
                        <img src="<%= request.getContextPath()%>/img/tutor/<%= tutor.getId()%>.jpg" alt="<%= tutor.getName()%>" class="teacher-image">
                    </div>
                    <div class="teacher-info">
                        <h3><%= tutor.getName()%></h3>
                        <p>Môn: <%= tutor.getSubjectName()%></p>
                        <p>Đánh giá: <%= String.format("%.1f", tutor.getRating())%> ★</p>
                        <a href="student_ViewTutorDetail.jsp?id=<%= tutor.getId()%>" class="btn">Xem thêm</a>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </body>
</html>
