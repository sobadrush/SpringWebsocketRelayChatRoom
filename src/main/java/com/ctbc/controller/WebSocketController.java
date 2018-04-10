package com.ctbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ctbc.model.MessageVO;

@Controller
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	public WebSocketController() {
		System.out.println("=== new WebSocketController ===");
	}

	@MessageMapping(value = "/chatMessage")
	public void chatMessage(MessageVO messageVO) {
		System.out.println("messageVO >>>> " + messageVO.toString());
		System.out.println("convertAndSend URL >>>> " + "/queue/receivedMessage" + messageVO.getSendName());
		simpMessagingTemplate.convertAndSend("/queue/receivedMessage" + messageVO.getSendName(), messageVO);
		simpMessagingTemplate.convertAndSend("/queue/receivedMessage" + messageVO.getName(), messageVO);
	}
}
