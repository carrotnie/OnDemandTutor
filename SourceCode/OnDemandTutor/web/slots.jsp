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
            justify-content: space-between;
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
            flex-grow: 1;
            text-align: center;
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
            background-color: #f4f4f4;
            color: black;
        }
        .class-content {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .btn-center {
            display: flex;
            justify-content: center;
            margin-top: 10px;
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
        <a href="ListClasses" class="home-icon">
            <i class="bi bi-arrow-left-circle"></i>
        </a>
        <h2>Danh Sách Slot ${sl.size()}</h2>
    </div>
    <div class="container">
        <div class="row">
            <c:forEach var="c" items="${sl}">
                <div class="col-md-4 mb-3">
                    <div class="class-box" style="margin: 10px; box-shadow: 0 0 5px 5px #8cbbff; padding: 15px; height: 100%;">
                        <div class="class-content">
                            <h5 style="text-align: center;">Môn: ${c.subjectName}</h5>
                            <p>Ngày: ${c.day}</p>
                            <p>Thời gian: 
                                <c:out value="${c.startTime.substring(0, 5)}" /> → 
                                <c:out value="${c.endTime.substring(0, 5)}" />
                            </p>
                        </div>
                        <div class="btn-center">
                            <a class="btn btn-info mt-2" href="BookSlot?slotId=${c.id}">Đăng ký</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
