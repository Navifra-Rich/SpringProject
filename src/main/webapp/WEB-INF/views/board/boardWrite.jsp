<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- include libraries(jQuery, bootstrap) -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=2">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
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
			$('#span_content').html('${selectedPost.content}');
			//alert("Out내용 = " + '${selectedPost.content}');
		}
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
	<h2 style="text-align: center;">글 작성</h2>
	<br>
	<br>
	<br>
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

	<div class="left"></div>
	<div class="right">
		<div id=writeFormd>
			<form method="post" action="/ex/Board/write" id="writeform"
				enctype="multipart/form-data">
				<input type="text" name="title" style="width: 40%;" placeholder="제목" />
				<br /> 지역<select name="loca">
					<c:forEach var="loca" items="${locations}">
						<option value="${loca.name}">${loca.name}
					</c:forEach>
				</select> <br /> 카테고리<select name="cate">
					<c:forEach var="cate" items="${categories}">
						<option value="${cate.name}">${cate.name}
					</c:forEach>
				</select> 날짜 및 시간<br /> <input type="date" name="startDay"><br>
				<select name="startTime">
					<c:forEach begin="0" end="23" varStatus="idx">
						<option value="${idx.index}" />${idx.index}
					</c:forEach>
				</select>시 부터 <br> <input type="date" name="endDay"><br> <select
					name="endTime">
					<c:forEach begin="1" end="24" varStatus="idx">
						<option value="${idx.index}" />${idx.index}
					</c:forEach>
				</select>시 까지 <br> 정원<input type="text" name="max_attendee"> 명<br>
				<textarea id="summernote" name="content"></textarea>
				이미지첨부하기<input type="file" name="image" value="이미지 첨부"> <br />
				파일 첨부하기<input type="file" name="file"><br /> <input
					id="subBtn" type="button" value="글 작성" style="float: right;"
					onclick="goWrite(this.form)" /> <input type="hidden" name="time"
					value="111">
			</form>
		</div>

	</div>

	<script>
		//---------------------------------------글쓰기 버튼 누름---------------------------------
		function goWrite(frm) {
			var title = frm.title.value;
			var content = frm.content.value;
			if (title.trim() == '') {
				alert("t");
			} else if (content.trim() == '') {
				alert("c");
			}
	
 			 var form = $('#writeform')[0];
			
	   	 // Create an FormData object 
        	var data = new FormData(form);
	   	 	alert(queryString);
			$.ajax({
				url : "/ex/Board/write",
				type : "POST",
				enctype : 'multipart/form-data',
				processData: false,
	            contentType: false,
				data : data,
				success : function() {
					location.href = "/ex/Board/getBoardList";
				}
			}); 
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