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

	<div class="container">
		<h2>그룹</h2>
		<ul class="nav nav-tabs" role="tablist">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#t1">내가 참여중인 모임</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#t2">내가 개설한 모임</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#t3">내가 참여했던 모임</a></li>
		</ul>

		<div class="tab-content">
			<div id="t1" class="container tab-pane active">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>id</th>
							<th>제목</th>
							<th>참여자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="met" items="${meetings}">
							<tr>
								<td>${met.post_ID }</td>
								<td>${met.summary }</td>
								<td>${met.cur_attendee }/${met.max_attendee }
									<button onClick="leave('${met.post_ID }')">나가기</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="t2" class="container tab-pane fade">
			<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>id</th>
							<th>제목</th>
							<th>참여자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="met" items="${organizedMeetings}">
							<tr>
								<td>${met.post_ID }</td>
								<td>${met.summary }</td>
								<td>${met.cur_attendee }/${met.max_attendee }
									<button onClick="leave('${met.post_ID }')">나가기</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="t3" class="container tab-pane fade">333333333333</div>
		</div>
	</div>
</body>
<script>
function leave(post_ID){
	alert('leave!');
	location.href("/ex/Meeting/leaveMeeting?post_ID="+post_ID);
};
</script>
</html>