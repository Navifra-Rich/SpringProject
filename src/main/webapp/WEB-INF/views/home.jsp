<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<title>Home</title>

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
</head>
<body>
<%String userID=(String)session.getAttribute("userID"); %>
	<div class="wrap">
		<h1>Hello world!</h1>
		<div class="header">
			<div class="goHomeLogo">
				GO<br />HOME
				<button type="button" onClick="temp()">abc</button>
			</div>
			<div class="headLogo">head Logo~</div>
			<div class="search_navbar"></div>

			<div class="section_navbar">
				<nav class="navbar navbar-expand navbar-dark bg-dark">
					<a class="navbar-brand" href="#">Always expand</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarsExample02" aria-controls="navbarsExample02"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class="collapse navbar-collapse" id="navbarsExample02">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link" href="/ex/Board/getBoardList">전체게시판
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
						</ul>
						<form class="form-inline my-2 my-md-0">
							<input class="form-control" type="text" placeholder="Search">
						</form>
					</div>
				</nav>
			</div>
		</div>
		<div class="container_main">
			<div class="column_left">
				<div class="column_leftTop"></div>
				<div class="column_leftBottom"></div>
			</div>
			<div class="column_right">
				<div class="column_rightTop">
					<div class="loginBox">
						<%
							if (userID==null) {
						%>
						<form action="Main/logIn" method="POST" class="form">
							<div class="form-group">
								<label for="inputID">ID</label> <input name="id" type="text"
									id="inputID" class="form-control">
							</div>
							<div class="form-group">
								<label for="inputPassowrd">PW</label> <input name="pw"
									type="password" id="inputPassword" class="form-control">
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-default" value="전송">
							</div>
						</form>
						<%
							} else {
						%>
							<%=userID%>님 로그인 됨
							<div>
								<button type="submit"  class="btn btn-default" onClick="logout()">로그아웃</button>
								<button type="submit"  class="btn btn-default" onClick="mypageClick()">마이페이지</button>
							</div>
						<%
							}
						%>
					</div>
				</div>
				<div class="column_rightBottom"></div>
			</div>
			<div class="column_bottom">column_left3</div>
		</div>
	</div>

	<form action="Main/getCode" method="POST">
		<input type="submit" value="얍">
	</form>
	<form action="Kakao/getFriendList" method="POST">
		<input type="submit" value="메세지">
	</form>
	<form action="Gogl/getCode" method="POST">
		<input type="submit" value="google/gettoken">
	</form>
	<form action="Kakao/self" method="POST">
		<input type="submit" value="self">
	</form>

	<form action="Board/getBoardList" method="GET">
		<input type="submit" value="temp">
	</form>
	<div class="a">asdf</div>
	<script>
		$('.a').on('click', function() {
			alert("!");
			location.href = "/ex/Board/writeForm";
		})
		function mypageClick(){
			location.href="/ex/Main/myPage";
		}
		function logout(){
			location.href="/ex/Main/logOut";
		}
		function temp(){
			location.href="/ex/Gogl/temp";
		}
	</script>
</body>
</html>
