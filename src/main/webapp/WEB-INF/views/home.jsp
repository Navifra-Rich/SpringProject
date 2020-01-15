<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Home</title>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=1959">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/common.js"/>"></script>
<script src="<c:url value="/resources/js/navbar.js"/>"></script>
<script src="<c:url value="/resources/js/board.js"/>"></script>
<script
	src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
</head>
<body>
	<input type="text" id="msg">
	<input type="button" id="sub" value="submit">
	<div id="pr"></div>

	<div class="wrap">
		<h1>Hello world!</h1>
		<jsp:include page="./module/header-navbar.jsp" flush="true" />

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
					<jsp:include page="./module/login.jsp" flush="false" />
				</div>
				<div class="column_rightBottom">
					<div class="contents_outter">
						<h4 class="mb-3 pb-2 text-secondary">최근 작성된 게시글</h4>
						<table class="table table-bordered">
							<tbody>
								<c:forEach var="con" items="${viewAll}">
									<tr>
										<td>[${con.location}][${con.category}]</td>
										<td>${con.title}</td>
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

	<form action="Board/getBoardList" method="GET">
		<input type="submit" value="temp">
	</form>
	<form action="/ex/Board/selectPost" method="GET" id="selectPost"></form>

	<script>
		

		$('#sub').on('click', function() {
			var msg = $('#msg').val();
			sock.send(msg);
		});
		$('.title').on('click', function() {
			var frm = $('#selectPost');
			frm.attr('method', 'GET');
			frm.append("<input type='hidden' name='idx' value='"+this.id+"'>")
			//alert("id = " + this.id);
			frm.submit();
		});
		$(document).ready(function() {
			if ('${alarmCount}' != '0') {
				$('.alarm').css("background-color", "red");
				$('.alarm').css("color", "white");
				$('.alarm').append('${alarmCount}');
			}
		})
	</script>
</body>
</html>