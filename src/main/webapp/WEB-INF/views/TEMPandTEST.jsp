<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


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
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : 'content',
			minHeight : 370,
			maxHeight : null,
			focus : false,
			lang : 'ko-KR'
		});
	});
</script>
</head>
<body>
	<div id=writeFormd>
		<form method="post" action="/ex/Board/write" id="writeform"
			enctype="multipart/form-data">
			<input type="text" name="title" placeholder="제목" />
			<textarea id="summernote" name="content"></textarea>
			<input id="subBtn" type="button" value="글 작성"onclick="goWrite(this.form)" /> 
		</form>
	</div>
	<script>
		//---------------------------------------글쓰기 버튼 누름---------------------------------
		function goWrite(frm) {
			frm.submit();
		}
		//--------------------------------------글 목록에서 글 선택----------------------------------
		$('.title').on('click', function() {
			var frm = $('#boardID');
			$('#boardID input[name="idx"]').val(this.id)
			frm.submit();
		});
	</script>
</body>
</html>