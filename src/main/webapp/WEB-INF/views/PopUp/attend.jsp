<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
</head>
<body>
	참가신청 완료<br/>
	<%
		if (request.getAttribute("calendarList")!=null) {
	%>
	<div>
	시작시간 : <%= session.getAttribute("startTime") %> <br/>
		캘린더 선택 :
		<select name="calendar_ID">
		<c:forEach var="calendar" items="${calendarList}">
				<option value="${calendar.calendar_ID}">${calendar.summary }</option>
		</c:forEach>
		</select>
		<br/><br/>
	</div>

	<%
		} else {
	%>
		<div>
			구글 캘린더와 연동되어있지 않습니다.
			<br/>
			<br/>
			<br/>
			구글 캘린더와 연동하기<br/>
			<button type="button" class="btn btn-default" onclick="enrollGoogleCalendar()">go</button>
			<br/><br/>
		</div>

	<%
		}
	%>
	<button type="button" class="btn btn-default" onclick="confirm()">확인</button>
	<script>
	function enrollGoogleCalendar(){
		location.href="/ex/Gogl/getCode";
	}
	function confirm(){
		<% if(request.getAttribute("calendarList")!=null){ %>
			alert($('[name="calendar_ID"]').val());
			location.href="/ex/Gogl/addEventToCalendar?calendar_ID="+$('[name="calendar_ID"]').val();
		<% }%>
		
		//window.close();
	}
	</script>
</body>
</html>