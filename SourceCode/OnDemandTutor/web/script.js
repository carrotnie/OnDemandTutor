function padToTwoDigits(num) {
    return num.toString().padStart(2, '0');
}

function formatDateForServer(dateStr) {
    const [day, month] = dateStr.split('/');
    const year = new Date().getFullYear();
    return `${year}-${padToTwoDigits(month)}-${padToTwoDigits(day)}`;
}

document.addEventListener('DOMContentLoaded', function () {
    const reportButtons = document.querySelectorAll('.report-btn');

    // Lấy giá trị từ URL
    const params = new URLSearchParams(window.location.search);
    const startDateParam = params.get('startDate');
    const endDateParam = params.get('endDate');
    let selectedStartDate, selectedEndDate;

    if (startDateParam && endDateParam) {
        selectedStartDate = new Date(startDateParam);
        selectedEndDate = new Date(endDateParam);
    } else {
        // Lấy giá trị từ dropdown tuần
        const weekDropdown = document.getElementById('week');
        const selectedWeek = weekDropdown.value;
        const [selectedStartDateStr, selectedEndDateStr] = selectedWeek.split(' Đến ');
        selectedStartDate = new Date(formatDateForServer(selectedStartDateStr));
        selectedEndDate = new Date(formatDateForServer(selectedEndDateStr));
    }

    reportButtons.forEach(button => {
        const form = button.closest('form');
        const startDate = new Date(form.querySelector('.report-start').value);
        const reportEndDate = new Date(form.querySelector('.report-end').value);

        // Tính toán reportStartDate là ngày StartDay lùi lại 14 ngày
        const reportStartDate = new Date(startDate);
        reportStartDate.setDate(reportStartDate.getDate() - 14);

        // Kiểm tra xem tuần được chọn có nằm trong khoảng từ reportStartDate đến reportEndDate không
        if ((selectedStartDate >= reportStartDate && selectedStartDate <= reportEndDate) ||
            (selectedEndDate >= reportStartDate && selectedEndDate <= reportEndDate)) {
            button.style.display = 'inline-block';
        } else {
            button.style.display = 'none';
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const weekDropdown = document.getElementById('week');

    weekDropdown.addEventListener('change', function () {
        const selectedWeek = this.value;
        const [startDate, endDate] = selectedWeek.split(' Đến ');

        document.getElementById('startDate').value = formatDateForServer(startDate);
        document.getElementById('endDate').value = formatDateForServer(endDate);

        document.getElementById('dateForm').submit();
    });

    // Đảm bảo tuần được chọn được lưu lại
    const params = new URLSearchParams(window.location.search);
    const startDateParam = params.get('startDate');
    const endDateParam = params.get('endDate');
    if (startDateParam && endDateParam) {
        const startDate = new Date(startDateParam);
        const endDate = new Date(endDateParam);
        const formattedStartDate = padToTwoDigits(startDate.getDate()) + "/" + padToTwoDigits(startDate.getMonth() + 1);
        const formattedEndDate = padToTwoDigits(endDate.getDate()) + "/" + padToTwoDigits(endDate.getMonth() + 1);
        const selectedWeek = formattedStartDate + " Đến " + formattedEndDate;
        weekDropdown.value = selectedWeek;
    }
});
