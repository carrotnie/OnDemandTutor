/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.addEventListener("DOMContentLoaded", function() {
    console.log("Fetching status...");
    fetch('TutorStatusController')
        .then(response => {
            console.log("Response received:", response);
            if (!response.ok) {
                throw new Error("Network response was not ok " + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log("Data received: ", data);
            const status = data.status;
            const reason = data.reason;

            console.log("Parsed status: ", status);
            console.log("Parsed reason: ", reason);

            const notificationPopup = document.getElementById("notificationPopup");
            const notificationCount = document.getElementById("notificationCount");

            let message = "";
            let hasMessage = false;

            if (status === "đã kiểm duyệt") {
                message = "Tài khoản của bạn đã được kiểm duyệt.";
                hasMessage = true;
            } else if (status === "chưa kiểm duyệt") {
                message = "Tài khoản của bạn chưa được kiểm duyệt.";
                hasMessage = true;
            } else if (status === "bị từ chối") {
                message = "Tài khoản của bạn đã bị từ chối.";
                if (reason) {
                    message += `<br>Lý do: ${reason} <a href='UpdateInsertInfoTutorController' style='color: red;'>Vui lòng cập nhật thông tin</a>`;
                }
                hasMessage = true;
            } else if (status === "Giáo viên lưu lòng nhập thông tin cá nhân") {
                message = "Giáo viên lưu lòng nhập thông tin cá nhân.";
                hasMessage = true;
            } else {
                message = "Không xác định trạng thái tài khoản.";
            }

            if (hasMessage) {
                notificationPopup.innerHTML = `
                    <div class="menu-item">
                        <p>${message}</p>
                    </div>
                `;
                notificationCount.innerText = "1";
                notificationCount.style.display = "block";
            } else {
                notificationPopup.innerHTML = "";
                notificationCount.style.display = "none";
            }
        })
        .catch(error => {
            const notificationPopup = document.getElementById("notificationPopup");
            notificationPopup.innerHTML = "<p>Failed to fetch status.</p>";
            const notificationCount = document.getElementById("notificationCount");
            notificationCount.style.display = "none";
        });

    // Add event listener for the notification button
    document.getElementById("notificationButton").addEventListener("click", function() {
        const notificationPopup = document.getElementById("notificationPopup");
        notificationPopup.classList.toggle("show");
    });
});










