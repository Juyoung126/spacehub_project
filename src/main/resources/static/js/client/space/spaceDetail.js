const calendarContainer = document.getElementById('calendar');
const selectedDateContainer = document.getElementById('selectedDate');
const timePickerContainer = document.getElementById('timePickerContainer');
//const timePicker = document.getElementById('timePicker');
const calendarTitle = document.getElementById('calendarTitle');
const prevButton = document.getElementById('prevMonth');
const nextButton = document.getElementById('nextMonth');
const $timeSlots = $(".time-slot");
const $selectedTimeDisplay = $("#selected-time");
const $scrollableContainer = $(".scrollable-container");

let currentYear = new Date().getFullYear();
let currentMonth = new Date().getMonth();
let startTime = null;
let endTime = null;
let selectedCount = 0;
let isMouseDown = false;
let startX, scrollLeft;

function createCalendar(year, month) {
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const daysInMonth = lastDay.getDate();
    const startDay = firstDay.getDay();

    calendarContainer.innerHTML = '';

    for (let i = 0; i < startDay; i++) {
        calendarContainer.innerHTML += '<div class="disabled"></div>';
    }

    for (let i = 1; i <= daysInMonth; i++) {
        calendarContainer.innerHTML += `<div>${i}</div>`;
    }

    calendarTitle.textContent = `${year}년 ${month + 1}월`;

    const cells = calendarContainer.querySelectorAll('div:not(.disabled)');
    cells.forEach(cell => {
        cell.addEventListener('click', function () {
            cells.forEach(c => c.classList.remove('selected'));
            this.classList.add('selected');
            const day = this.textContent;
            const date = `${year}-${month + 1}-${day}`;
            selectedDateContainer.textContent = `선택된 날짜: ${year}년 ${month + 1}월 ${day}일`;
            timePickerContainer.style.display = 'block';
        });
    });
}

function populateTimePicker() {
    for (let hour = 9; hour <= 21; hour++) {
        const option = document.createElement('option');
        const formattedHour = hour < 10 ? `0${hour}` : hour;
        option.value = `${formattedHour}:00`;
        option.textContent = `${formattedHour}:00`;
        //timePicker.appendChild(option);
    }
}

function handleNavigation() {
    prevButton.addEventListener('click', () => {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        createCalendar(currentYear, currentMonth);
    });

    nextButton.addEventListener('click', () => {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        createCalendar(currentYear, currentMonth);
    });
}

function selectTimeRange(start, end) {
    selectedCount = 0;
    $timeSlots.each(function () {
        const time = parseInt($(this).data("time"));
        if (time >= start && time <= end) {
            $(this).addClass("selected");
            selectedCount++;
        }
    });
}

function resetSelection() {
    $timeSlots.removeClass("selected");
    startTime = null;
    endTime = null;
    selectedCount = 0;
    updateSelectedTime();
}

function updateSelectedTime() {
    const dateText = selectedDateContainer.textContent || '날짜 없음';
    if (startTime !== null && endTime !== null) {
        $selectedTimeDisplay.text(`선택된 시간: ${startTime}시 ~ ${endTime}시 (${selectedCount}시간 선택됨)`);
    } else if (startTime !== null) {
        $selectedTimeDisplay.text(`시작 시간: ${startTime}시`);
    } else {
        $selectedTimeDisplay.text("선택된 시간: 없음");
    }
    $("#selectedDateTime").text(`${dateText} | ${$selectedTimeDisplay.text()}`);
}

$scrollableContainer.on("mousedown", function (e) {
    isMouseDown = true;
    startX = e.pageX - $scrollableContainer.offset().left;
    scrollLeft = $scrollableContainer.scrollLeft();
    $scrollableContainer.css("cursor", "grabbing");
});

$(document).on("mouseup", function () {
    isMouseDown = false;
    $scrollableContainer.css("cursor", "grab");
});

$scrollableContainer.on("mousemove", function (e) {
    if (!isMouseDown) return;
    e.preventDefault();
    const x = e.pageX - $scrollableContainer.offset().left;
    const walk = (x - startX) * 2;
    $scrollableContainer.scrollLeft(scrollLeft - walk);
});

$timeSlots.on("click", function () {
    const time = parseInt($(this).data("time"));

    if (startTime === null) {
        startTime = time;
        $(this).addClass("selected");
    } else if (endTime === null) {
        if (time < startTime) {
            endTime = startTime;
            startTime = time;
        } else {
            endTime = time;
        }
        selectTimeRange(startTime, endTime);
    } else {
        resetSelection();
    }

    updateSelectedTime();
});

// 페이지 로드 후 초기화
createCalendar(currentYear, currentMonth);
populateTimePicker();
handleNavigation();
