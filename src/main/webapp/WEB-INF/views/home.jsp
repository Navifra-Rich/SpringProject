<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<title>Home</title>

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=1959">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

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
								<a class="dropdown-item" href="#">서울</a> <a
									class="dropdown-item" href="#">느그집</a> <a class="dropdown-item"
									href="#">우리집</a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown">활동으로 찾기 </a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">스포츠</a> <a
									class="dropdown-item" href="#">음악</a> <a class="dropdown-item"
									href="#">독서</a>
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
									<div class="col-3 border p-2 bg-secondary">알람</div>
									<div class="col-3 border p-2 bg-secondary">쪽지</div>
									<div class="col-3 border p-2 bg-secondary">모임</div>
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
										<td>[${con.title}]</td>
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
	<form action="/ex/Board/selectPost" method="GET" id="selectPost"></form>
	<script>
		function myFunction() {
			//alert('!!');
		}
		$('.title').on('click', function() {
			var frm = $('#selectPost');
			frm.attr('method', 'GET');
			frm.append("<input type='hidden' name='idx' value='"+this.id+"'>")
			//alert("id = " + this.id);
			frm.submit();
		});
		$(document).ready(function() {
			$.ajax({
				url : "/ex/Main/getHomeInfo",
				method : "POST",
				dataType : "text",
				success : function(text) {
					//alert("@@!!@!@" + text);
				},
				error : function(e) {
					//alert(e.responseText);
				}
			})
		})

		$('.a').on('click', function() {
			alert("!");
			location.href = "/ex/Board/writeForm";
		})
		function mypageClick() {
			location.href = "/ex/Main/myPage";
		}
		function logout() {
			location.href = "/ex/Main/logOut";
		}
		function temp() {
			location.href = "/ex/Gogl/temp";
		}
		function goHome() {
			location.href = "/ex/";
		}
	</script>
</body>
</html>
