// var rightDiv = document.getElementById("div.left");
//
// // 달력 생성 함수
// function createCalendar() {
//   // 현재 날짜 가져오기
//   var today = new Date();
//
//   // 년도와 월 가져오기
//   var year = today.getFullYear();
//   var month = today.getMonth() + 1;
//
//    // 달력 표시할 위치 선택 및 초기화
//    var calendarDiv = document.getElementById("calendar");
//    calendarDiv.innerHTML = "";
//
//    // 년도와 월 표시하기
//    var title = document.createElement("h2");
//    title.textContent = year + "년 " + month + "월";
//    calendarDiv.appendChild(title);
//
//    // 요일 표시하기 (일 ~ 토)
//    var daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
//
//    var dayOfWeekGrid = document.createElement("div");
//    dayOfWeekGrid.classList.add("calendar-grid");
//
//     for (var i = 0; i < daysOfWeek.length; i++) {
//       var dayOfWeekElement = document.createElement("div");
//       dayOfWeekElement.classList.add("calendar-cell");
//       dayOfWeekElement.textContent = daysOfWeek[i];
//       dayOfWeekGrid.appendChild(dayOfWeekElement);
//      }
//
//     calendarDiv.appendChild(dayOfWeekGrid);
//
//     // 첫 번째 날짜의 요일 구하기 (0: 일요일, ..., 6: 토요일)
//     var firstDayOfMonthIndex= new Date(year, month -1,1).getDay();
//
//     // 달력 그리기 (칸으로 구성)
//     var daysInMonth= new Date(year, month,0).getDate();
//
//     var calendarGrid= document.createElement("div");
//     calendarGrid.classList.add('calendar-grid');
//
//      for(var i=0; i<firstDayOfMonthIndex; i++){
//        let emptyCell=document.createElement('div');
//        emptyCell.classList.add('calendar-cell');
//        calendarGrid.appendChild(emptyCell);
//      }
//
//      for(var i=1; i<=daysInMonth; i++){
//        let cell=document.createElement('div');
//        cell.classList.add('calendar-cell');
//        cell.textContent=i.toString();
//
//         if(i===today.getDate() && month === today.getMonth()+1 && year === today.getFullYear()){
//           cell.classList.add('today');
//         }
//         cell.textContent=i.toString();
//         calendarGrid.appendChild(cell);
//      }
//
//      calendarDiv.appendChild(calendarGrid);
//
//      // 각 날짜 칸에 클릭 이벤트 추가하기
//      var dateCells=document.getElementsByClassName('calendar-cell');
//
//       for(var j=0;j<dateCells.length;j++){
//           dateCells[j].addEventListener('click', function(){
//               console.log(this.textContent);
//               alert(this.textContent+'를 클릭하셨습니다.');
//           });
//       }
// }
//
//
// // 페이지 로드 시 달력 생성 함수 호출하기
// window.onload=createCalendar;
//
//
// function divBtn() {
//     location.href="/member/myCalendar";
// }
//
// function divBtn1() {
//     location.href="/community/page=1";
// }


var rightDiv = document.getElementById("div.left");

// 달력 생성 함수
function createCalendar() {
    // 현재 날짜 가져오기
    var today = new Date();

    // 년도와 월 가져오기
    var year = today.getFullYear();
    var month = today.getMonth() + 1;

    // 출석 체크 데이터 저장 객체 초기화
    var attendanceData = {};

// 운동 정보 저장 배열 초기화
    var exerciseData = [];

    // 달력 표시할 위치 선택 및 초기화
    var calendarDiv = document.getElementById("calendar");
    calendarDiv.innerHTML = "";

    // 년도와 월 표시하기
    var title = document.createElement("h2");
    title.textContent = year + "년 " + month + "월";
    calendarDiv.appendChild(title);

    // 요일 표시하기 (일 ~ 토)
    var daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

    var dayOfWeekGrid = document.createElement("div");
    dayOfWeekGrid.classList.add("calendar-grid");

    for (var i = 0; i < daysOfWeek.length; i++) {
        var dayOfWeekElement = document.createElement("div");
        dayOfWeekElement.classList.add("calendar-cell");
        dayOfWeekElement.textContent = daysOfWeek[i];
        dayOfWeekGrid.appendChild(dayOfWeekElement);
    }

    calendarDiv.appendChild(dayOfWeekGrid);

    // 첫 번째 날짜의 요일 구하기 (0: 일요일, ..., 6: 토요일)
    var firstDayOfMonthIndex= new Date(year, month -1,1).getDay();

    // 달력 그리기 (칸으로 구성)
    var daysInMonth= new Date(year, month,0).getDate();

    var calendarGrid= document.createElement("div");
    calendarGrid.classList.add('calendar-grid');

    for(var i=0; i<firstDayOfMonthIndex; i++){
        let emptyCell=document.createElement('div');
        emptyCell.classList.add('calendar-cell');
        calendarGrid.appendChild(emptyCell);
    }

    for(var i=1; i<=daysInMonth; i++){
        let cell=document.createElement('div');
        cell.classList.add('calendar-cell');
        cell.textContent=i.toString();

        if(i===today.getDate() && month === today.getMonth()+1 && year === today.getFullYear()){
            cell.classList.add('today');
        }
        cell.textContent=i.toString();
        calendarGrid.appendChild(cell);
    }

    calendarDiv.appendChild(calendarGrid);

    // 각 날짜 칸에 클릭 이벤트 추가하기
    var dateCells=document.getElementsByClassName('calendar-cell');

    for(var j=0;j<dateCells.length;j++){
        dateCells[j].addEventListener('click', function(){
            console.log(this.textContent);
            alert(this.textContent+'를 클릭하셨습니다.');
        });
    }
}


// 페이지 로드 시 달력 생성 함수 호출하기
window.onload=createCalendar;


function divBtn() {
    location.href="calender.html"
}

function divBtn1() {
    location.href="heal_pan.html"
}

// 출석 체크 토글 함수

function toggleAttendanceCheck(cell){
    var dateKey=cell.dataset.dateKey;

    if(attendanceData[dateKey]){
        delete attendanceData[dateKey];
        cell.classList.remove("attendance-check");
    }else{
        attendanceData[dateKey]=true;
        cell.classList.add("attendance-check");
    }
}

// 운동 정보 저장 폼 제출 시 처리 함수

document.getElementById("exercise-form").addEventListener("submit", function(event) {
    event.preventDefault();

    var exerciseDateInput=document.getElementById ("exercise-date") ;
    var exerciseNameInput=document.getElementById ("exercise-name") ;

    var exerciseDateValue=exerciseDateInput.value;
    var exerciseNameValue=exerciseNameInput.value;

    if(exerciseDateValue&&exerciseNameValue){

        var exerciseItem={ date: exerciseDateValue,name: exerciseNameValue};
        exerciseData.push(exerciseItem);

        rerenderExerciseList ();
        exerciseDateInput.value='';
        exerciseNameInput.value='';
    }
});

// 운동정보출력함수

function renderExerciseList() {
    var exerciseListDiv=document.getElementById ('exercise-list') ;

    exerciseListDiv.innerHTML='';

    for(var item of exerciseData){

        var listItem=document.createElement ('div') ;
        listItem.classList. add ('exercise-item') ;

        var dateSpan=document.createElement ('span') ;
        dateSpan.classList.add ('date-span') ;
        dateSpan.textContent=item .date;

        var nameSpan=document.createElement ('span') ;
        nameSpan.classList. add ('name-span') ;
        nameSpan.textContent=item .name;

        listItem.appendChild(dateSpan) ; listItem.appendChild(nameSpan);

        exerciseListDiv.appendChild(listItem ) ;

    }
}