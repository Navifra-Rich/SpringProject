<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<title>글쓰기</title>
<style>
div {
	
}

div.left {
	width: 30%;
	float: left;
	box-sizing: border-box;
}

div.right {
	width: 70%;
	float: right;
}
</style>
<script>
	$(document).ready(function() {
		//$('#writeFormd').style.display = 'block';
		if ('${selectedPost}' == '') {
			$('#viewForm').hide();
			//alert("null");
		} else {
			$('#viewForm').show();
			alert("바꿈 " + $('#viewForm').css("display"));

			$('#span_title').html('${selectedPost.title}');
			$('#span_author').html('${selectedPost.author}');
			$('#span_content').html('${selectedPost.content}');
			//alert("Out내용 = " + '${selectedPost.content}');
		}
		$('#summernote').summernote({
			placeholder : 'content',
			minHeight : 370,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR'
		});
	});
</script>
</head>
<body>
	<h2 style="text-align: center;">글 작성</h2>
	<br>
	<br>
	<br>
	<div>
		<div class="left">

			<c:forEach items="${viewAll}" var="list" varStatus="status">
				<p id="${list.num}" class="title">제목 : ${list.title}</p>
				<p>저자 : ${list.author}</p>
				<p>번호 : ${list.num}</p>
				<p>--------------------------------------------</p>
			</c:forEach>

		</div>
		<div class="right">
			<div id=writeFormd style="display: none">
				<form method="post" action="/write" id="writeform">
					<input type="text" name="writer" style="width: 20%;"
						placeholder="작성자" /><br> <input type="text" name="title"
						style="width: 40%;" placeholder="제목" /> <br> <br>
					<textarea id="summernote" name="content"></textarea>
					<input id="subBtn" type="button" value="글 작성" style="float: right;"
						onclick="goWrite(this.form)" />
				</form>
			</div>
			<div id=viewForm style="display: none">

				<form action="/ex/Kakao/self" method="POST">
					<span id="span_title">${selectedPost.title}</span> <span
						id="span_author">${selectedPost.author}</span> <span
						id="span_content">${selectedPost.content}</span>
						<input type="hidden" name="span_title" value="${selectedPost.title}">
						<input type="hidden" name="span_author" value="${selectedPost.author}">
						 <input type="hidden" name="span_content" value="${selectedPost.content}">
						 <input type="submit" name="submit" value="전송">
				</form>
			</div>
		</div>
	</div>
	<script>
		//---------------------------------------글쓰기 버튼 누름---------------------------------
		function goWrite(frm) {
			var title = frm.title.value;
			var author = frm.author.value;
			var content = frm.content.value;
			alert("t = " + title + " w = " + author + " c = " + content);
			if (title.trim() == '') {
				alert("t");
			} else if (author.trim() == '') {
				alert("w");
			} else if (content.trim() == '') {
				alert("c");
			}
			frm.submit();
		}
		//--------------------------------------글쓰기 폼 onoff----------------------------------
		function hideForm() {
			//alert($('#writeform').css("display"));
			$('#writeform').hide();
		}
		//--------------------------------------글 목록에서 글 선택----------------------------------
		$('.title').on('click', function() {

			var frm = $('#boardID');
			$('#boardID input[name="idx"]').val(this.id)
			frm.submit();
		});
	</script>
	<form id="boardID" action="../Board/selectPost">
		<input type="hidden" name="idx" value="">
	</form>
</body>
</html>