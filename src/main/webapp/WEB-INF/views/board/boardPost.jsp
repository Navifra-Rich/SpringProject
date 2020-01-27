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
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=210">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/common.js"/>"></script>
<script src="<c:url value="/resources/js/navbar.js"/>"></script>
<script src="<c:url value="/resources/js/board.js"/>"></script>
<script src="<c:url value="/resources/js/webSocket.js"/>"></script>
<%
	String userID = (String) session.getAttribute("userID");
	MeetingDTO mDTO = (MeetingDTO) request.getAttribute("meeting");
	int max_att = mDTO.getMax_attendee();
	int cur_att = mDTO.getCur_attendee();
%>
<body>
	<jsp:include page="../module/header-navbar.jsp" flush="true"/>
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
					<form class="commentForm" action="/ex/Comment/writeComment"
						method="POST">
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
						<tbody id="commentListBody">
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
		<div class="column_right" style="width: 30%; height: 800px;">
			<div class="column_rightTop">
					<jsp:include page="../module/login.jsp" flush="false"/>
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

		$('.selectPage').on('click', function() {
			location.href = "/ex/Board/getBoardList?setPage=" + this.value;
		})

		$("#commentSubmit").on(
				'click',
				function() {
					alert("얍");
					var frm = $('.commentForm')[0];
					var data = new FormData(frm);
					$.ajax({
						url : '/ex/Comment/writeComment',
						data : data,
						processData : false,
						contentType : false,
						async : false,
						type : 'POST',
						success : function() {
							//---댓글 등록중 등록된 다른 댓글에 대한 처리 필요
							$('#commentListBody').append(
									"<tr><td>id = " + frm.id.value
											+ "</td><td>" + frm.content.value
											+ "</td></tr>");
							$('.commentForm [name="content"]').val("");
							sock.send('{"id":"${selectedPost.author}", "alarm":"comment_alarm"}');
							alert("1")
						}
					});

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
		$(document).ready(function() {
			alarmSet('${alarmCount}');
		})
	</script>
	<%
		request.setAttribute("startTime", "abcd123");
		request.setAttribute("endTime", "${selectedPost.endDay}");
		// 		<span>시간 : ${selectedPost.endDay} ${selectedPost.endTime}시 까지
	%>
</body>
</html>