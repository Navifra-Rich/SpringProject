<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Home</title>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=1959">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/common.js"/>"></script>
<script src="<c:url value="/resources/js/navbar.js"/>"></script>
<script src="<c:url value="/resources/js/board.js"/>"></script>
<script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
</head>
<body>
	<input type="text" id="msg">
	<input type="button" id="sub" value="submit">
	<div id="pr"></div>
	<%
		String userID = (String) session.getAttribute("userID");
	%>
	<div class="wrap">
		<h1>Hello world!</h1>
		<div class="header">
			<div class="goHomeLogo">
				GO<br />HOME
			</div>
			<div class="headLogo">head Logo~</div>
			<div class="search_navbar"></div>
			<div class="section_navbar">
				<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown">지역으로 찾기 </a>
							<div class="dropdown-menu">
								<c:forEach var="loca" items="${locations}">
									<a class="dropdown-item" onClick="byLocation('${loca.name}')">${loca.name}</a>
								</c:forEach>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown">활동으로 찾기 </a>
							<div class="dropdown-menu">
								<c:forEach var="cate" items="${categories}">
									<a class="dropdown-item" onClick="byCategory'${cate.name}')">${cate.name}</a>
								</c:forEach>
							</div></li>
						<li class="nav-item active"><a class="nav-link"
							href="/ex/Board/getBoardList">전체게시판 <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item active"><a class="nav-link"
							href="/ex/Board/getBoardList">삐롱삐롱삐로로롱 <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
					</ul>
					<form class="form-inline my-2 my-md-0">
						<input class="form-control" type="text" placeholder="Search">
					</form>
				</nav>
			</div>
		</div>
		<div class="container_main">
			<div class="column_left">
				<div class="column_leftTop">
					<div class="contents_outter">
						<div class="hitContents">
							<h3 class="pb-2 mb-3 border-bottom">hit 게시글</h3>
							<c:forEach var="con" items="${hitContents}">
								<div class="hit_content">
									<img src="/ex/File/displayImage?postID=${con.num}"
										style="max-width: 90px; height: auto; text-align: center;">
									<p class="title" id="${con.num }">${con.title}</p>
									<p>${con.category }</p>
									<p>댓글 ${con.comCount }개</p>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="column_leftBottom">
					<div class="contents_outter">
						<ul class="nav nav-tabs bg-dark">
							<li class="nav-item"><button class="nav-link active"
									onClick="leftBottumClick()">1</button></li>
							<li class="nav-item"><button class="nav-link active"
									onClick="leftBottumClick()">2</button></li>
							<li class="nav-item"><button class="nav-link active"
									onClick="leftBottumClick()">3</button></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="column_right">
				<div class="column_rightTop">
					<div class="loginBox">
						<%
							if (userID == null) {
						%>
						<form action="Main/logIn" method="POST" class="form">
							<div class="login_left">
								<div class="col-md-12">
									<label for="inputID"></label> <input name="id" type="text"
										id="inputID"
										class="form-control form-control-sm bg-secondary text-white-50">
								</div>

								<div class="col-md-12">
									<label for="inputPassowrd"></label> <input name="pw"
										type="password" id="inputPassword"
										class="form-control form-control-sm bg-secondary text-white-50">
								</div>
							</div>
							<div class="login_right">
								<div class="form-group">
									<input type="submit" class="btn btn-default bg-dark text-white"
										value="전송">
								</div>
							</div>
						</form>
						<%
							} else {
						%>
						<div
							style="text-align: center; display: inline-block; width: 80%;">
							<div>
								<div class="border">
									<img src="#" style="min-width: 90px; min-height: 90px;">
									<%=userID%>님 로그인 됨
									<button type="submit" class="btn btn-default"
										onClick="logout()">로그아웃</button>
									<button type="submit" class="btn btn-default"
										onClick="mypageClick()">마이페이지</button>
								</div>
								<div class="row m-0">
									<div class="col-3 border p-2 bg-secondary"
										onClick="getAlarmList()">
										알람
										<div class="alarm"
											style="display: inline-block; position: relative; width: 10px; height: 10px;"></div>
									</div>
									<div class="col-3 border p-2 bg-secondary">쪽지</div>
									<div class="col-3 border p-2 bg-secondary"
										onClick="getMeetingList()">모임</div>
									<div class="col-3 border p-2 bg-secondary">삐롱</div>
								</div>
							</div>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<div class="column_rightBottom">
					<div class="contents_outter">
						<h4 class="mb-3 pb-2 text-secondary">최근 작성된 게시글</h4>
						<table class="table table-bordered">
							<tbody>
								<c:forEach var="con" items="${viewAll}">
									<tr>
										<td>[${con.location}][${con.category}]</td>
										<td>${con.title}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="column_bottom">column_left3</div>
		</div>
	</div>

	<form action="Main/getCode" method="POST">
		<input type="submit" value="얍">
	</form>

	<form action="Board/getBoardList" method="GET">
		<input type="submit" value="temp">
	</form>
	<form action="/ex/Board/selectPost" method="GET" id="selectPost"></form>

	<script>
		//------------------------------------웹 소켓---------------------------
		let sock = new SockJS("http://localhost:8220/ex/echo");
		sock.onmessage = OnMessage;
		sock.onclose = OnClose;
		//background-color: red;
		function OnMessage(msg) {
			var data = msg.data;
			var comment_alarm = 'comment_alarm';
			var next = $
			{
				alarmCount
			}
			+1;
			$('#pr').append(data + '<br/>');
			alert(data);
			if (data == comment_alarm) {
				$('.alarm').css("background-color", "red");
				$('.alarm').css("color", "white");
				$('.alarm').val(next);
				//리펙토링 필요, 궁극적인 목적 아님 next값 정하는데 모델쪽에서 처리해서 뷰로 줘야됨
			} else
				alert(msg);
		}
		function OnClose() {
		}

		$('#sub').on('click', function() {
			var msg = $('#msg').val();
			sock.send(msg);
		});
		$('.title').on('click', function() {
			var frm = $('#selectPost');
			frm.attr('method', 'GET');
			frm.append("<input type='hidden' name='idx' value='"+this.id+"'>")
			//alert("id = " + this.id);
			frm.submit();
		});
		$(document).ready(function() {
			if ('${alarmCount}' != '0') {
				$('.alarm').css("background-color", "red");
				$('.alarm').css("color", "white");
				$('.alarm').append('${alarmCount}');
			}
		})
	</script>
</body>
</html>