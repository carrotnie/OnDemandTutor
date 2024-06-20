<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .nav {
            background-color: #001e54;
            display: flex;
            align-items: center;
            padding: 10px 20px;
            border-bottom: 1px solid #ddd;
        }
        .nav .home-icon {
            font-size: 1.5rem; /* Increase icon size */
            color: white;
            margin-right: 10px; /* Adjust margin to align properly */
        }
        .nav .home-icon:hover {
            color: #007BFF;
        }
        .nav h2 {
            color: white;
            margin: 0 auto; /* Center the text */
        }
        .container {
            padding: 20px;
        }
        .info-section {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .students-list {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .students-list ul {
            list-style-type: none;
            padding: 0;
        }
        .students-list ul li {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .students-list ul li:last-child {
            border-bottom: none;
        }
        .actions {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }
        .actions a {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            font-weight: bold;
            border-radius: 5px;
        }
        .class-box {
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .class-content {
            flex-grow: 1;
        }
        .btn-center {
            display: flex;
            justify-content: center;
        }
        .btn-info {
            background-color: #007BFF;
            border-color: #007BFF;
        }
        .btn-info:hover {
            background-color: #0056b3;
            border-color: #0056b3;
            color: white;
        }
    </style>
</head>
<body>
    <div class="nav">
        <a href="student_homepage.jsp" class="home-icon">
            <i class="bi bi-house-fill"></i>
        </a>
        <h2>Danh Sách Lớp học</h2>
    </div>
    <div class="container">
        <div class="row">
            <div style="margin: 10px 0px;" class="col-md-12">
                <form method="ListClasses">
                    <select class="form-select" onchange="this.form.submit()" name="suId">
                        <option value="">All</option>
                        <c:forEach var="s" items="${sl}">
                            <option value="${s.id}" ${param.suId==s.id?"Selected":""}>${s.name}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
            <c:forEach var="c" items="${cl}">
                <div class="col-md-4 mb-3">
                    <div class="class-box" style="margin: 10px; box-shadow: 0 0 5px 5px #8cbbff; padding: 15px;">
                        <div class="class-content">
                            <h4 style="text-align: center;">Giáo viên: ${c.name}</h4>
                            <h5 style="text-align: center;">Môn: ${c.subjectName}</h5>
                            <p>Số lượng slot: ${c.amountSlot}</p>
                            <p>Thời gian: ${c.fromDate} 	&rarr; ${c.fromDate}</p>
                        </div>
                        <div class="btn-center">
                            <a class="btn btn-info mt-2" href="ListSlot?cId=${c.id}">Chi Tiết Giờ Học</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
