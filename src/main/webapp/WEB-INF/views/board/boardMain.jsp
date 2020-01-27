<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/common.js"/>"></script>
<script src="<c:url value="/resources/js/navbar.js?ver=1"/>"></script>
<script src="<c:url value="/resources/js/board.js"/>"></script>
</head>

<body>

	<%
		String userID = (String) session.getAttribute("userID");
	%>
	<div>
		<jsp:include page="../module/header-navbar.jsp" flush="true"/>

		<div class="container_main">
			<div class="column_left" style="width: 70%;">
				<div style="width: 1000px; height: 600px; float: right;">
					<div class="col-sm-10" style="float: right; right: 3%;">
						<table class="table table-striped table-hover table-sm">
							<thead>
								<tr>
									<th style="width: 10%; text-align: center;">글번호</th>
									<th style="width: 50%;">제목</th>
									<th style="width: 20%; text-align: center;">작성자</th>
									<th style="width: 10%; text-align: center;">댓글 수</th>
									<th style="width: 10%; text-align: center;">시간</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="post" items="${viewAll}" varStatus="status">
									<tr>
										<td style="text-align: center;">${post.num}</td>
										<td class="title" id="${post.num}" onClick="selectPost('${post.num}')">${post.title}</td>
										<td style="text-align: center;">${post.author}</td>
										<td style="text-align: center;">${post.comCount}</td>
										<td style="text-align: center;">${post.time}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="" style="display: inline-block;">
					<form action="/ex/Board/writeForm" method="POST"
						style="display: inline;">
						<input type="submit" value="글쓰기">
					</form>
					<div class="page" style="display: inline;">
						<c:if test="${page.prev}">
							<input type="button" value="이전" id="prev" class="selectPage">
						</c:if>
						<c:forEach varStatus="idx" var="page" begin="${page.startPage}"
							end="${page.endPage-1}">
							<input type="button" value="${idx.current}" class="selectPage" onClick="selectPage()">
						</c:forEach>
						<c:if test="${page.next}">
							<input type="button" value="다음" id="next" class="selectPage">
						</c:if>
						<p>현재 페이지${page.curPage}</p>
						<p>시작 페이지${page.startPage}</p>
						<p>끝 페이지${page.endPage}</p>
					</div>
				</div>
			</div>
			<div class="column_right" style="width: 30%; height: 800px;">
				<div class="column_rightTop">
				<jsp:include page="../module/login.jsp" flush="false"/>
				</div>
			</div>
		</div>
		<div class="write"></div>
	</div>
	<form action="/ex/Board/selectPost" method="GET" id="selectPost"></form>
</body>
<%
	String ss = (String) request.getAttribute("searchQuery");
%>
<script>
	//------------------------------------------Web Socket-----------------------------------------------------
	let sock = new SockJS("http://localhost:8220/ex/echo");
	sock.onmessage = onMessage;

	function onMessage() {
		alert("메세지 받았따!!!!");
	};

	//-----------------------------------------------------------------------------------------------
	var query = '${searchQuery}'
	$('#temp').on('click', function() {
		alert("!");
		alert("!!!" + aaa);
	});

	$(document).ready(function() {
		alarmSet('${alarmCount}');
	})
</script>
</html>
