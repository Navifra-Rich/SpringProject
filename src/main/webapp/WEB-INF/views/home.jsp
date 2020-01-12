<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">



<!-- jQuery CDN-->

<script src="https://code.jquery.com/jquery-1.9.0.js"
	integrity="sha256-TXsBwvYEO87oOjPQ9ifcb7wn3IrrW91dhj6EMEtRLvM="
	crossorigin="anonymous"></script>



<!-- Web socket CDN -->

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>


</head>



<body>

	<input type="text" id="message" />

	<input type="button" id="sendBtn" value="전송" />



	<div id="data"></div>

</body>



<script type="text/javascript">
	$("#sendBtn").click(function() {

		sendMessage();

		$('#message').val('')
	});
	$("#message").keydown(function(key) {

		if (key.keyCode == 13) {// 엔터

			sendMessage();

			$('#message').val('')

		}

	});
	
	let sock = new SockJS("http://localhost:8220/ex/echo/");

	sock.onmessage = onMessage;

	// 메시지 전송

	function sendMessage() {
		alert("message = "+$("#message").val());
		sock.send($("#message").val());

	}

	// 서버로부터 메시지를 받았을 때

	function onMessage(msg) {

		var data = msg.data;

		$("#data").append(data + "<br/>");

	}

	// 서버와 연결을 끊었을 때

	function onClose(evt) {

		$("#data").append("연결 끊김");

	}
</script>

</html>