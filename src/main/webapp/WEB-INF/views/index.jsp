<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>首頁</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/sockjs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/stomp.js"></script>
	<script type="text/javascript">
		var stompClient = null;
		
		$(function(){
			connect();
		});
		
		function setConnected(connected) {
			$("#connect").prop("disabled", connected);
			$("#disconnect").prop("disabled", !connected);
		}
		
		function connect() {
			var url = "<%=request.getContextPath() %>/chat";
			var socket = new SockJS(url);
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame){
				setConnected(true);
				console.log('Connected >>>> ', frame);
				stompClient.subscribe('/queue/receivedMessage' + $("#name").val(), function(response){
					showChat(response.body);
				});
			});
		}
		
		// 顯示聊天訊息
		function showChat(message) {
			console.log('message >>>>>>>> ', message);
			message = JSON.parse(message);
			var response = document.getElementById('chat_content');
			response.value += decodeURIComponent(message.name) + ' : ' + decodeURIComponent(message.content) + '\n';
		}
		
		function send() {
			var name = $('#name').val();
			var sendName = $('#sendName').val();
			var content = $('#content').val();
			stompClient.send('/app/chatMessage',{},JSON.stringify(
					{
						'name' : name,
						'sendName' : sendName,
						'content' : content,
					}
			));
			
			$("#content").val("");
		}
		
		function disconnect() {
			if (stompClient != null) {
				stompClient.disconnect();
			}
			
			setConnected(false);
			console.log('Disconnected');
		}
	</script>
</head>
<body>
	<button id="connect" onclick="connect();">Connect</button>
	<button id="disconnect" onclick="disconnect();">Disonnect</button>
	<br>	
	<br>
	Name: <input type="text" id="name" value="${userName}" readonly="readonly" disabled="disabled" />
	<br>	
	<br>
	Send To Name: <input type="text" id="sendName" autofocus />	
	<br>	
	<br>
	Message: <input type="text" id="content">
	<button id="send" onclick="send();">Send</button>
	<br>	
	<br>
	<div class="field">
		<textarea rows="10" cols="50" id="chat_content" disabled="disabled"></textarea>	
	</div>
</body>
</html>