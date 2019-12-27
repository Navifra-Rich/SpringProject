<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String s=request.getParameter("code").toString();
%>
<form action="https://www.googleapis.com/oauth2/v4/token" method="POST" enctype="application/x-www-form-urlencoded">
	코드 : <input type="text" name="code" value="<%=s %>"><br/>
	id : <input type="text" name="client_id" value="741951758945-qtt2bbvcf6fhqm6acpim429maj11hqjp.apps.googleusercontent.com"><br/>
	sc : <input type="text" name="client_secret" value="E6alvBW5f0G9YBSuBo_oTWbk"><br/>
	url : <input type="text" name="redirect_uri" value="http://localhost:8220/ex/Gogl/getCodePage"><br/>
	type : <input type="text" name="grant_type" value="authorization_code"><br/>
	<input type="submit" value="전송">
</form>
</body>
</html>