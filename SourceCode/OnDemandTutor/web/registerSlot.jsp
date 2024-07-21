<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng ký Slot</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f8f8;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: center;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            .button-container {
                text-align: center;
                margin-top: 20px;
            }
            .btn-submit, .btn-back, .btn-add-row {
                display: inline-block;
                width: 120px;
                padding: 12px;
                margin: 10px;
                font-size: 16px;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-align: center;
            }
            .btn-submit {
                background-color: #4CAF50;
            }
            .btn-submit:hover {
                background-color: #45a049;
            }
            .btn-back {
                background-color: #f44336;
                text-decoration: none;
                padding: 10px 20px;
                display: inline-block;
                border-radius: 5px;
            }
            .btn-back:hover {
                background-color: #e41f1f;
            }
            .btn-add-row {
                background-color: #2196F3;
            }
            .btn-add-row:hover {
                background-color: #1E88E5;
            }
        </style>
        <script>
            function addRow() {
                const table = document.getElementById('slotsTable').getElementsByTagName('tbody')[0];
                const newRow = table.insertRow();

                const dayCell = newRow.insertCell(0);
                const startTimeCell = newRow.insertCell(1);
                const endTimeCell = newRow.insertCell(2);

                dayCell.innerHTML = `
                    <select name="dayOfSlot[]">
                        <option value="Thứ Hai">Thứ Hai</option>
                        <option value="Thứ Ba">Thứ Ba</option>
                        <option value="Thứ Tư">Thứ Tư</option>
                        <option value="Thứ Năm">Thứ Năm</option>
                        <option value="Thứ Sáu">Thứ Sáu</option>
                        <option value="Thứ Bảy">Thứ Bảy</option>
                        <option value="Chủ Nhật">Chủ Nhật</option>
                    </select>
                `;
                startTimeCell.innerHTML = `
                    <select name="startTime[]">
                        <option value="08:00">08:00</option>
                        <option value="10:00">10:00</option>
                        <option value="12:00">12:00</option>
                        <option value="14:00">14:00</option>
                        <option value="16:00">16:00</option>
                        <option value="18:00">18:00</option>
                        <option value="20:00">20:00</option>
                    </select>
                `;
                endTimeCell.innerHTML = `
                    <select name="endTime[]">
                        <option value="10:00">10:00</option>
                        <option value="12:00">12:00</option>
                        <option value="14:00">14:00</option>
                        <option value="16:00">16:00</option>
                        <option value="18:00">18:00</option>
                        <option value="20:00">20:00</option>
                        <option value="22:00">22:00</option>
                    </select>
                `;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Đăng ký Slot</h1>
            <form action="MainController" method="post" accept-charset="UTF-8">
                <input type="hidden" name="action" value="RegisterSlot">
                <table id="slotsTable">
                    <thead>
                        <tr>
                            <th>Ngày</th>
                            <th>Thời gian bắt đầu</th>
                            <th>Thời gian kết thúc</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <select name="dayOfSlot[]">
                                    <option value="Thứ Hai">Thứ Hai</option>
                                    <option value="Thứ Ba">Thứ Ba</option>
                                    <option value="Thứ Tư">Thứ Tư</option>
                                    <option value="Thứ Năm">Thứ Năm</option>
                                    <option value="Thứ Sáu">Thứ Sáu</option>
                                    <option value="Thứ Bảy">Thứ Bảy</option>
                                    <option value="Chủ Nhật">Chủ Nhật</option>
                                </select>
                            </td>
                            <td>
                                <select name="startTime[]">
                                    <option value="08:00">08:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="20:00">20:00</option>
                                </select>
                            </td>
                            <td>
                                <select name="endTime[]">
                                    <option value="10:00">10:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="22:00">22:00</option>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="button-container">
                    <button type="button" class="btn-add-row" onclick="addRow()">Thêm Slot</button>
                    <button type="submit" name="action" value="RegisterSlot" class="btn-submit">Đăng ký</button>
                    <a href="registerMenu.html" class="btn-back">Quay lại</a>
                </div>
            </form>
            <% if (request.getAttribute("errorMessage") != null) {%>
            <p style="color:red; text-align: center;"><%= request.getAttribute("errorMessage")%></p>
            <% }%>
        </div>
    </body>
</html>
