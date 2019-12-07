<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<style>
html, body {
	height: 100%;
}

.head {
	margin: 0;
	width: 100%;
	height: 20%;
	background-color: black;
}

.middle {
	height: 300px;
	position: relative;
}

.content {
	display: inline-block;
	position: absolute;
	left: 10%;
	height: 100%;
	background-color: green;
	width: 100%;
}

.left {
	display: inline-block;
	margin: 0;
	width: 10%;
	height: 100%;
	background-color: red;
}

.right {
	display: inline-block;
	margin: 0;
	width: 10%;
	height: 100%;
	width: 10%;
}
</style>
<body>
	<div class="head"></div>
	<div class="middle">
		<div class="left"></div>
		<div class="content">
			<div>
				<span>제목 : ${selectedPost.title}</span><br /> <span>글쓴이 :
					${selectedPost.author}</span><br /> <br /> <br /> <span>글 내용 :
					${selectedPost.content}</span><br /> <br />
			</div>
			<div class="comment">
				여따가 댓글 ㅇㅇ<br />
				<form class="commentForm" action="/ex/Board/addComment">
				<input type="text" name="id" value="">
					<textarea id="summernote" name="content"></textarea>
					<input type="button" id="commentSubmit" value="쓰기">
					<input type="hidden" name="postNum" value="${selectedPost.num }">
				</form>
			</div>
		</div>

	</div>
	<div class="left"></div>
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
			end="${page.endPage }">
			<input type="button" value="${status.current}" class="selectPage">
		</c:forEach>
	</div>
	<script>
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
			var frm=$('.commentForm')
			frm.submit();
		});
	</script>
</body>
</html>