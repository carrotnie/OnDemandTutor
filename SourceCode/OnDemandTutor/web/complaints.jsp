<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Báo cáo giáo viên</title>
        <style>
            .container {
                max-width: 600px;
                margin: 0 auto;
                padding: 40px;
                background-color: #f0f0f0;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                margin-bottom: 30px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
            }
            .form-group input,
            .form-group textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }
            .form-group textarea {
                height: 120px;
            }
            .btn {
                display: block;
                width: 100%;
                padding: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }
            .btn:hover {
                background-color: #0056b3;
            }
            .windows-activation {
                margin-top: 20px;
                text-align: center;
                color: #999;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Báo cáo giáo viên</h1>
            <form action="SubmitComplaintController" method="post">
                <input type="hidden" id="studentId" name="studentId" value="${param.studentId}">
                <input type="hidden" id="slotId" name="slotId" value="${param.slotId}">
                <input type="hidden" id="tutorId" name="tutorId" value="${param.tutorId}">
                <input type="hidden" id="modId" name="modId" value="${param.modId}">
                <div class="form-group">
                    <label for="teacherName">Tên giáo viên</label>
                    <input type="text" id="teacherName" name="tutorName" value="${param.tutorName}" readonly>
                </div>
                <div class="form-group">
                    <label for="slot">Slot học</label>
                    <input type="text" id="slot" name="timeRange" value="${param.timeRange}" readonly>
                </div>
                <div class="form-group">
                    <label for="subjectName">Môn học</label>
                    <input type="text" id="subjectName" name="subjectName" value="${param.subjectName}" readonly>
                </div>
                <div class="form-group">
                    <label for="message">Mô tả vấn đề của bạn:</label>
                    <textarea id="message" name="content" required></textarea>
                </div>
                <button type="submit" class="btn">Gửi đơn</button>
            </form>

            <div class="windows-activation">
                <p>Cảm ơn những đóng góp của bạn</p>
                <p>Chúng tôi sẽ cố gắng phản hồi sớm nhất</p>
            </div>
        </div>
    </body>
</html>
