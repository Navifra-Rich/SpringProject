<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<style>
.head {
	display:block;
	margin: 0;
	width: 100%;
	height: 20%;
	background-color: black;
}

.middle {
	display:block;
	height: 500px;
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
	height: 500px;
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
<div>
<form action = "/ex/Board/temp"><input type="submit"></form>
</div>
	<div class="head"></div>
	<div class="middle">
		<div class="search">
			<form action="/ex/Board/getBoardList" id="searchPost" method="GET">
				<input type="text" name="searchContent"> <input
					type="submit" value="검색">
			</form>

		</div>
		<div class="left"></div>
		<div class="content">
			<form action="/ex/Board/selectPost" method="GET" id="selectPost">
			</form>
			<table class="table table-striped table-hover">

				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>댓글 수</th>
						<th>시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="post" items="${viewAll}" varStatus="status">
						<tr>
							<td>${post.num}</td>
							<td class="title" id="${post.num}">${post.title}</td>
							<td>${post.content}</td>
							<td>${post.comCount}</td>
							<td>${post.time}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="write">
		<form action="/ex/Board/writeForm" method="POST" style="display:block">
			<input type="submit" value="글쓰기">
		</form>
	</div>
	<div class="page">
		<p>현재 페이지${page.curPage}</p>
		<p>시작 페이지${page.startPage}</p>
		<p>끝 페이지${page.endPage}</p>
		<p>페이지 시작<%=request.getAttribute("abcd")%></p>
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
		<p>끝</p>
	</div>
	<input type="button" id="temp" value="aaaa">
	<div>a</div>
	<div>b</div>
	<div>c</div>
	<div>d</div>
</body>
<%
	String ss = (String) request.getAttribute("searchQuery");
%>
<script>
	$('#temp').on('click', function() {
		alert("!");
		var aaa ="<%=ss%>";
		alert("!!!" + aaa);
	});
	$(document).ready(function() {


	});
	$('.title').on('click', function() {
		var frm = $('#selectPost');
		frm.attr('method', 'GET');
		frm.append("<input type='hidden' name='idx' value='"+this.id+"'>")
		alert("id = "+this.id);
		frm.submit();
	});
	$('.selectPage').on('click', function() {
		var url = "";
		alert("id = " + this.id);
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
		var query = "<%=ss%>";
		if(query==null){
			alert("검색내용 없음");
		}else{
			url+="&searchContent="+query;
		}
		alert("url = "+url);
		location.href = url;
	})
</script>
</html>
