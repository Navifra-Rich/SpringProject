/**
 * 	웹 소켓 관련 JS
 */

//------------------------------------웹 소켓---------------------------
		let sock = new SockJS("http://localhost:8220/ex/echo");
		sock.onmessage = OnMessage;
		sock.onclose = OnClose;
		
		function OnMessage(msg) {
			alert("알람왔음");
			var data = msg.data;
			var comment_alarm = 'comment_alarm';
			var next = '${alarmCount}';
			$('#pr').append(data + '<br/>');
			alert(data);
			if (data == comment_alarm) {
				$('.alarm').css("background-color", "red");
				$('.alarm').css("color", "white");
				$('.alarm').val(next);
				//리펙토링 필요, 궁극적인 목적 아님 next값 정하는데 모델쪽에서 처리해서 뷰로 줘야됨
			} else
				alert(msg);
		};
		function OnClose() {
		};