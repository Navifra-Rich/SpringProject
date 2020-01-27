<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<c:url value="/resources/js/common.js?ver=5500"/>"></script>
<script src="<c:url value="/resources/js/navbar.js"/>"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script
	src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
<link rel="stylesheet" href="/ex/resources/css/myCss.css?ver=1959">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../module/header-navbar.jsp"></jsp:include>
	<div class="col-md-6">
		<form method="POST" action="/ex/Main/signUp">
			<div class="col-md-6">
				<label for="inputName"></label> name<input name="name" type="text"
					id="inputName"
					class="form-control form-control-sm bg-secondary text-white-50">
			</div>
			<div class="col-md-6">
				<label for="inputID"></label> id<input name="id" type="text"
					id="inputID"
					class="form-control form-control-sm bg-secondary text-white-50">
			</div>

			<div class="col-md-6">
				<label for="inputPassowrd"></label> pw<input name="pw"
					type="password" id="inputPassword"
					class="form-control form-control-sm bg-secondary text-white-50">
			</div>
			<div class="login_right">
				<div class="form-group">
					<input type="submit" class="btn btn-default bg-dark text-white"
						value="전송">
				</div>
			</div>
		</form>
	</div>
</body>
</html>