<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}. 123abc</P>
	<form action="Main/logIn" method="POST">
		id<input name="id" type="text"><br> pw<input name="pw"
			type="text"><br> <input type="submit">
	</form>
	<form action="Main/getCode" method="POST">
		<input type="submit" value="얍">
	</form>
	<form action="Kakao/getFriendList" method="POST">
		<input type="submit" value="메세지">
	</form>
	<form action="Main/test" method="POST">
		<input type="submit" value="main/test">
	</form>
	<form action="Kakao/self" method="POST">
		<input type="submit" value="self">
	</form>

	<form action="Board/getBoardList" method="GET">
		<input type="submit" value="temp">
	</form>
</body>
</html>
