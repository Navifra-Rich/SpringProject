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
<link rel="stylesheet" href="/ex/resources/css/myPage.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
</head>
<body>
	<div class="header">
		<div class="top">
			<a href="ex/main/home">홈</a>
		</div>
		oijm
		<div class="myNav"></div>
	</div>
	<div class="container">
		<div class="column">
			기본설정<br />
			<button class="btn btn-default">수정</button>
		</div>
		<div class="column"></div>
		<div class="column"></div>
		<div class="column">
			구글 연동 설정<br /> 계정 이름 : ${googleAccountID}<br />
			<button class="btn btn-default">변경</button>
			권한:
			<c:forEach items="${authorities_gogl}" var="auth">
				<br />
			</c:forEach>
		</div>
		<div class="column">
			카카오톡 연동<br />
			<button class="btn btn-default">수정</button>
		</div>

	</div>
</body>
</html>