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
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>

<body>

	<%
		String userID = (String) session.getAttribute("userID");
	%>
	<div>
		<div class="header">
			<div class="goHomeLogo" onClick="goHome()">
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
					<form class="form-inline my-2 my-md-0"
						action="/ex/Board/getBoardList" id="searchPost" method="GET">
						<input name="searchContent" class="form-control" type="text"
							placeholder="Search"><input type="submit" value="검색">
					</form>
				</nav>
			</div>
		</div>

		<div class="container_main">
			<div class="column_left" style="width: 70%;">
				<div style="width: 1000px; height: 600px; float:right;">
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
										<td class="title" id="${post.num}">${post.title}</td>
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
					<form action="/ex/Board/writeForm" method="POST" style="display: inline;">
						<input type="submit" value="글쓰기">
					</form>
					<div class="page" style="display: inline;">
						<c:if test="${page.prev}">
							<input type="button" value="이전" id="prev" class="selectPage">
						</c:if>
						<c:forEach varStatus="idx" var="page" begin="${page.startPage}"
							end="${page.endPage-1}">
							<input type="button" value="${idx.current}" class="selectPage">
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
			<div class="column_right" style="width: 30%; height:800px;">
				<div class="column_rightTop">
					<div class="loginBox">
						<%
							if (userID == null) {
						%>
						<form action="/ex/Main/logIn" method="POST" class="form">
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
						<%=userID%>님 로그인 됨
						<div>
							<button type="submit" class="btn btn-default" onClick="logout()">로그아웃</button>
							<button type="submit" class="btn btn-default"
								onClick="mypageClick()">마이페이지</button>
						</div>
						<%
							}
						%>
					</div>
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
	var query = '${searchQuery}'
	$('#temp').on('click', function() {
		alert("!");
		alert("!!!" + aaa);
	});

	$('.title').on('click', function() {
		var frm = $('#selectPost');
		frm.attr('method', 'GET');
		frm.append("<input type='hidden' name='idx' value='"+this.id+"'>")
		//alert("id = " + this.id);
		frm.submit();
	});
	$('.selectPage').on('click', function() {
		var url = "";
		//alert("id = " + this.id);
		//--------------------------------------------페이지 번호 붙임--------------------------------------------------------
		if (this.id == "prev") {
			url = '/ex/Board/getBoardList?setPage=' + $
			{
				page.curPage - page.range
			}
			;
		} else if (this.id == "next") {
			url = '/ex/Board/getBoardList?setPage=' + $
			{
				page.curPage + page.range
			}
			;
		} else {
			url = '/ex/Board/getBoardList?setPage=' + this.value;
		}

		//--------------------------------------------검색 쿼리 붙임--------------------------------------------------------(

		if (query == null) {
			alert("검색내용 없음");
		} else {
			url += "&searchContent=" + query;
		}
		//alert("url = " + url);
		location.href = url;
	})
	function goHome() {
		location.href = "/ex/";
	}
</script>
</html>
