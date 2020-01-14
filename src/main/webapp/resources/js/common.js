/**
 * 대부분의 페이지에서 공통적으로 이용해야 하는 기능들
 */

// Mypage 클릭
function mypageClick() {
	location.href = "/ex/Main/myPage";
}
// Logout 버튼 클릭
function logout() {
	location.href = "/ex/Main/logOut";
}
//홈 로고 클릭 (시작 페이지 이동)
function goHome() {
	location.href = "/ex/";
}
//알람 리스트 출력
function getAlarmList() {
	var left = window.screen.width / 2 - 300;
	var top = window.screen.height / 2 - 200;
	window.open("/ex/Alarm/getAlarmList", "alarm",
			"width=600, height=400, left=" + left + ", top=" + top);
}
//모임 리스트 출력
function getMeetingList() {
	var left = window.screen.width / 2 - 250;
	var top = window.screen.height / 2 - 200;
	window.open("/ex/Meeting/getMeetingList?userID=${userID}",
			"meeting", "width=500, height=400, left=" + left + ", top="
					+ top);
}
