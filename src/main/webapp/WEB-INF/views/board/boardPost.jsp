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
<link rel="stylesheet" href="<c:url value='/resources/css/myCss.css'/>" />
<body>
	<div class="myhead"></div>
	<div class="mymiddle">
		<div class="myleft"></div>
		<div class="mycontent">
			<div>
				<span>제목 : ${selectedPost.title}</span><br /> 
				<span>날짜 : ${selectedPost.day}</span><br />
				<span>시간 : ${selectedPost.startTime}시 부터</span><br />
				<span>시간 : ${selectedPost.endTime}시 까지</span><br />
				<input type="button" value="참여하기" onClick="attend()">
				<span>글쓴이 :
					${selectedPost.author}</span><br /> <br /> <br /> <span>글 내용 :
					${selectedPost.content}</span><br /> <br />
			</div>
			<div class="files">
				<c:forEach var="files" items="${files}">
					<p>첨부된 파일 =/ex/File/fileDownload?path=${files.directory}</p>
					<input type="button" value="${files.name}"
						onclick="downloadFile();">
				</c:forEach>
			</div>
			<div class="comment">
				댓글 쓰기<br />
				<form class="commentForm" action="/ex/Comment/writeComment">
					<input type="text" name="id" value="">
					<textarea id="summernote" name="content"></textarea>
					<input type="button" id="commentSubmit" value="쓰기"> <input
						type="hidden" name="postNum" value="${selectedPost.num }">
					<input type="hidden" name="curPage" value="${page.curPage }">
				</form>	
			</div>
			<div class="commentList">
			댓글 목록<br/>
				<c:forEach var="coms" items="${comments}" varStatus="status">
					<p>댓글 id = ${coms.id}</p>
					<p>댓글 내용 = ${coms.content}</p>
					<br/>
				</c:forEach>
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
			end="${page.endPage}">
			<input type="button" value="${status.current}" class="selectPage">
		</c:forEach>
	</div>
	<script>
	var postID="${selectedPost.num}";
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
		function attend(){
			alert("${selectedPost.num}");
			location.href="/ex/Board/attend?postID="+postID;
		}
	</script>
</body>
</html>