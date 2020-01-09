<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sp.ex.dto.postDTO"%>
<%@ page import="com.sp.ex.dto.MeetingDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=210">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<%
	String userID = (String) session.getAttribute("userID");
	MeetingDTO mDTO = (MeetingDTO) request.getAttribute("meeting");
	int max_att = mDTO.getMax_attendee();
	int cur_att = mDTO.getCur_attendee();
%>
<body>
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
							<a class="dropdown-item" href="#">서울</a> <a class="dropdown-item"
								href="#">느그집</a> <a class="dropdown-item" href="#">우리집</a>
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
		<div class="column_left">
			<div class="mycontent" style="float: right;">
				<div>
					<span>제목 : ${selectedPost.title} // 글쓴이 :
						${selectedPost.author}</span>
					<p>날짜 : ${selectedPost.time}</p>
					<span>지역 : ${selectedPost.location }</span> <span>범주 :
						${selectedPost.category}</span><br /> <span>시간
						:${selectedPost.startDay} </span><span> ${selectedPost.startTime}시
						부터 ${selectedPost.endDay} ${selectedPost.endTime}시 까지</span>

					<%
						if (max_att <= cur_att) {
					%>
					<p class="text-danger">${meeting.cur_attendee}/${meeting.max_attendee}
						정원 초과!</p>
					<%
						} else {
					%>
					<span class="text-secondary">${meeting.cur_attendee}</span><span>/${meeting.max_attendee}</span>
					<%
						if (request.getAttribute("attended") != null) {
					%>
					<input type="button" value="참여하기" onClick="attend()">
					<p>
						<%
							} else {
						%>
						<input type="button" value="참여중!" disabled="disabled"><br />
						<%
							}
							}
						%>


					</p>
					<br /> <br />
					<p>
						이미지 출력 <img src="/ex/File/displayImage?postID=${selectedPost.num}"
							style="max-width: 100%; height: auto;">
					</p>
					<br /> <br />
					<p>글 내용 : ${selectedPost.content}</p>
					<br />
				</div>
				<div class="files">
					<c:forEach var="file" items="${files}">
						<p>첨부된 파일 =/ex/File/fileDownload?path=${file.directory}</p>
						<input type="button" value="${file.name}"
							onclick="downloadFile();">
					</c:forEach>
				</div>
				<div class="comment">
					<form class="commentForm" action="/ex/Comment/writeComment">
						<input type="hidden" name="id" value="<%=userID%>">
						<textarea name="content" placeholder="댓글 쓰기"
							style="width: 650px; height: 80px;"></textarea>
						<input type="button" id="commentSubmit" value="등록"> <input
							type="hidden" name="postNum" value="${selectedPost.num }">
						<input type="hidden" name="curPage" value="${page.curPage }">
					</form>
				</div>
				<div class="commentList">
					<table class="table table-secondary table-striped">
						<thead>
							<tr>
								<th></th>
								<th>댓글 ${fn:length(comments)}</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="coms" items="${comments}" varStatus="status">
								<tr>
									<td>id = ${coms.id}</td>
									<td>${coms.content}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="column_right" style="width: 30%">
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
					<div style="text-align: center; display: inline-block; width: 80%;">
						<div>
							<div class="border">
								<img src="#" style="min-width: 90px; min-height: 90px;">
								<%=userID%>님 로그인 됨
								<button type="submit" class="btn btn-default" onClick="logout()">로그아웃</button>
								<button type="submit" class="btn btn-default"
									onClick="mypageClick()">마이페이지</button>
							</div>
							<div class="row m-0">
								<div class="col-3 border p-2 bg-secondary">알람</div>
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
		</div>
	</div>

	<div class="content">
		<form action="/ex/Board/selectPost" method="GET" id="selectPost">
		</form>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>댓글 수</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${viewAll}" varStatus="status">
					<tr>
						<td>${post.num}</td>
						<td class="title" id="${post.num}">${post.title}</td>
						<td>${post.author}</td>
						<td>${post.comCount}</td>
						<td>${post.time}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="page">
		<p>현재 페이지 = ${page.curPage}</p>
		<c:forEach var="pg" varStatus="status" begin="${page.startPage }"
			end="${page.endPage}">
			<input type="button" value="${status.current}" class="selectPage">
		</c:forEach>
	</div>
	<script>
		var postID = "${selectedPost.num}";
		$('.title').on('click', function() {
			var frm = $('#selectPost');
			frm.attr('method', 'GET');
			frm.append("<input type='hidden' name='idx' value='"+this.id+"'>")
			frm.submit();
		});
		$('.selectPage').on('click', function() {
			location.href = "/ex/Board/getBoardList?setPage=" + this.value;
		})

		$("#commentSubmit").on('click', function() {
			alert("얍");
			var frm = $('.commentForm')
			frm.submit();
		});
		function downloadFile() {
			var temp = "${files['0'].directory}";
			var url = "/ex/File/fileDownload?path=" + temp;
			alert(temp);
			location.href = url;
		}
		function attend() {
			alert("${selectedPost.num}");
	<%//------------------------------리펙토링 필요 -> 보드 controller에서 애초에 뷰를 리턴할 때 세션 설정하기------------- 
			postDTO dto = (postDTO) request.getAttribute("selectedPost");
			session.setAttribute("startTime", dto.getStartTime());
			session.setAttribute("endTime", dto.getEndTime());
			session.setAttribute("startDay", dto.getStartDay());
			session.setAttribute("endDay", dto.getEndDay());%>
		//window.open("/ex/Board/attend?postID="+postID);
			var left = window.screen.width / 2 - 400;
			var top = window.screen.height / 2 - 300;
			window
					.open(
							"/ex/Meeting/attendPopUp?post_ID="
									+
	<%=dto.getNum()%>
		,
							"attend",
							'fullscreen=no, width=800 height=600 scrollbars=no, location=no, resizable=no, left='
									+ left + ', top=' + top);
		}
	</script>
	<%
		request.setAttribute("startTime", "abcd123");
		request.setAttribute("endTime", "${selectedPost.endDay}");
		// 		<span>시간 : ${selectedPost.endDay} ${selectedPost.endTime}시 까지
	%>
</body>
</html>