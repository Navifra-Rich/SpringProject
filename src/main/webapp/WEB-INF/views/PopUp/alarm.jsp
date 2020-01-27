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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="head">댓글 알림</div>
	<div id="contents">
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>comment.No</th>
					<th>summary</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="alarm" items="${comment_alarm}">
					<tr>
						<td>${alarm.comment_ID}</td>
						<td class="alarmTitle"
							onClick="clickAlarm('${alarm.post_ID}','${alarm.comment_ID}')">${alarm.writer_ID }님이${alarm.post_Title }게시물에
							댓글을 남겼습니다.</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:right;"> <input type="button" class="btn btn-default bg-dark text-white" value="전체 확인" onClick="checkAll()"></div>
</body>
<script>
	//해당 알림의 게시글로 이동함
	function clickAlarm(post_ID, comment_ID) {
		alert(post_ID);
		opener.location.href = "/ex/Alarm/clickAlarm?comment_ID=" + comment_ID
				+ "&post_ID=" + post_ID;
	}
	//모든 알림 일괄 확인
	function checkAll(){
		location.href='<c:url value="/Alarm/checkAll"/>';
	}
</script>
</html>