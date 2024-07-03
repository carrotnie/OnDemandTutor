<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.ArrayList"%>
<%@page import="timetable.TimeTableDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student Timetable</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                text-align: center;
            }
            th, td {
                padding: 10px;
                text-align: center;
                border: 1px solid black;
            }
            th {
                background-color: #c4e2f0;
            }
            .day-row {
                background-color: pink;
            }
            .attribute-col {
                width: 150px;
                background-color: #c4e2f0;
                font-weight: bold;
            }
            .nav {
                background-color: white;
                display: flex;
                align-items: center;
                padding: 10px 20px;
                border-bottom: 1px solid #ddd;
                color: #000;
            }
            .nav img {
                height: 100px;
                margin-right: 20px;
            }
            .nav a, .nav .dropbtn {
                text-decoration: none;
                color: black;
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
            .main {
                padding: 20px;
            }
            .container {
                max-width: 1200px;
                margin: 0 auto;
            }
            .main {
                padding: 20px;
            }
            .back-button {
                margin-top: 20px;
                padding: 10px 20px;
                background-color: transparent;
                color: white;
                text-decoration: none;
                border: none;
                border-radius: 5px;
                display: inline-block;
            }
            .icon-home {
                color: black;
                font-size: 24px;
            }
            .back-button:hover .icon-home {
                color: #717171;
            }
            .report-btn {
                display: none;
            }
        </style>
    </head>
    <body>
        <div class="nav">
            <a href="student_homepage.jsp" class="back-button">
                <i class="bi bi-house-fill icon-home"></i>
            </a>
        </div>
        <form id="dateForm" method="get" action="TimeTableController">
            <input type="hidden" id="startDate" name="startDate" value="${param.startDate}">
            <input type="hidden" id="endDate" name="endDate" value="${param.endDate}">
        </form>

        <c:if test="${not empty errorMessage}">
            <div style="color: red; text-align: center; margin-top: 20px;">
                ${errorMessage}
            </div>
        </c:if>

        <table>
            <tr>
                <td class="attribute-col">
                    <label for="year">Năm:</label>
                    <span id="currentYear"><%= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%></span>
                    <br>
                    <label for="week">Tuần:</label>
                    <select name="week" id="week">
                        <%
                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
                            String selectedWeek = request.getParameter("week");
                            for (int i = 0; i < 10; i++) {
                                cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
                                String startDate = sdf.format(cal.getTime());
                                cal.add(Calendar.DATE, 6);
                                String endDate = sdf.format(cal.getTime());
                                String weekRange = startDate + " Đến " + endDate;
                        %>
                        <option value="<%= weekRange%>" <%= weekRange.equals(selectedWeek) ? "selected" : ""%>><%= weekRange%></option>
                        <%
                                cal.add(Calendar.DATE, 1);
                            }
                        %>
                    </select>
                </td>
                <th>Thứ Hai</th>
                <th>Thứ Ba</th>
                <th>Thứ Tư</th>
                <th>Thứ Năm</th>
                <th>Thứ Sáu</th>
                <th>Thứ Bảy</th>
                <th>Chủ Nhật</th>
            </tr>
            <%
                String[] daysOfWeek = {"Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật"};
                String[] timesWeekday = {"16:00 - 18:00", "18:00 - 20:00", "20:00 - 22:00"};
                String[] timesWeekend = {"08:00 - 10:00", "10:00 - 12:00", "12:00 - 14:00", "14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00", "20:00 - 22:00"};

                List<TimeTableDTO> timeTables = (List<TimeTableDTO>) request.getAttribute("timeTables");

                for (int slot = 1; slot <= 8; slot++) {
                    out.print("<tr class='day-row'>");
                    out.print("<td>Slot " + slot + "</td>");

                    for (int dayIndex = 0; dayIndex < daysOfWeek.length; dayIndex++) {
                        String day = daysOfWeek[dayIndex];
                        String[] times = (dayIndex < 5) ? timesWeekday : timesWeekend;

                        if (slot > times.length) {
                            out.print("<td></td>");
                            continue;
                        }

                        String timeRange = times[slot - 1];
                        boolean hasData = false;

                        for (TimeTableDTO timeTable : timeTables) {
                            String startTime = new SimpleDateFormat("HH:mm").format(timeTable.getStartTime());
                            String endTime = new SimpleDateFormat("HH:mm").format(timeTable.getEndTime());
                            String timeSlot = startTime + " - " + endTime;

                            if (timeTable.getDayOfSlot().equals(day) && timeSlot.equals(timeRange)) {
                                out.print("<td>");
                                out.print("Môn: " + timeTable.getSubjectName() + "<br>");
                                out.print("Giáo viên: " + timeTable.getTutorName() + "<br>");
                                out.print(timeRange + "<br>");

                                // Tính toán để kiểm tra thời gian hiển thị nút "Report"
                                java.sql.Date startDate = timeTable.getStartDay();
                                Calendar cal2 = Calendar.getInstance();
                                cal2.setTime(startDate);
                                cal2.add(Calendar.DATE, 14);
                                java.sql.Date reportEndDate = new java.sql.Date(cal2.getTimeInMillis());
                                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

                                // Hiển thị nút "Report" nếu ngày hiện tại nằm trong khoảng từ startDate đến reportEndDate (14 ngày sau startDate)
                                String reportUrl = "report.jsp?subjectName=" + timeTable.getSubjectName() + "&tutorName=" + timeTable.getTutorName() + "&timeRange=" + timeSlot + "&startDate=" + startDate;
                                out.print("<button class='report-btn' data-url='" + reportUrl + "'>Report</button>");

                                out.print("<input type='hidden' class='report-start' value='" + startDate + "'>");
                                out.print("<input type='hidden' class='report-end' value='" + reportEndDate + "'>");

                                hasData = true;
                                out.print("</td>");
                                break; // Thêm break tại đây để tiếp tục kiểm tra các slot khác
                            }
                        }

                        if (!hasData) {
                            out.print("<td></td>");
                        }
                    }
                    out.print("</tr>");
                }
            %>
        </table>

        <script src="script.js"></script>
    </body>
</html>
