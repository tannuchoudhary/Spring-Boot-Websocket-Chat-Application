package com.bnymellon.chat.controller;


import java.security.Principal;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bnymellon.chat.models.ChatMessage;
import com.bnymellon.chat.repositories.UserSessionRepository;
import com.bnymellon.constants.APIRoutes;
import com.bnymellon.constants.CommonConstants;

import lombok.extern.slf4j.Slf4j;


// https://stackoverflow.com/questions/27047310/path-variables-in-spring-websockets-sendto-mapping
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/messaging/simp/SimpMessageHeaderAccessor.html
// https://www.baeldung.com/spring-interface-driven-controllers
@Controller
@Slf4j
@CrossOrigin("http://localhost:3000")
public class ChatControllerImpl implements ChatOperations {
	

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	UserSessionRepository repository;
	
	@Override
	@MessageMapping(value = APIRoutes.API.CHAT_REGISTER)
	public void register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
		headerAccessor.getSessionAttributes().put(CommonConstants.USERNAME, chatMessage.getSender());
		repository.setUserSession(chatMessage.getSender(), principal.getName());
		log.info(chatMessage.getSender() + CommonConstants.JOINED_THE_CHAT);
		
		if (chatMessage.getGroup() != CommonConstants.PRIVATE_CHAT_TOPIC)
			simpMessagingTemplate.convertAndSend(APIRoutes.API.TOPIC + chatMessage.getGroup(), chatMessage);
	}
	
	@Override
	@MessageMapping(value = APIRoutes.API.CHAT_SEND)
	public void sendMessage(@Payload ChatMessage chatMessage) {
	    simpMessagingTemplate.convertAndSend(APIRoutes.API.TOPIC + chatMessage.getGroup(), chatMessage);
	    log.info("Message received on topic {} from {} : {}", chatMessage.getGroup(), chatMessage.getSender(), chatMessage.getContent());
	}
	
	@Override
	@MessageMapping(value = APIRoutes.API.CHAT_PRIVATE_USERNAME)
	@SendToUser(value = APIRoutes.API.PRIVATE_CHAT_TOPIC_URL)
	public ChatMessage sendPrivateMessage(@Payload ChatMessage chatMessage) {
		try {
			String id = repository.getSessionId(chatMessage.getReceiver()).get();
		    simpMessagingTemplate.convertAndSendToUser(id, APIRoutes.API.PRIVATE_CHAT_TOPIC_URL, chatMessage);
		    log.info("Message from {} to {} received : {}", chatMessage.getSender(), chatMessage.getReceiver(), chatMessage);
		}catch(NoSuchElementException ex) {
			String message = String.format("%s is offline!", chatMessage.getReceiver());
			log.info(message);
		}
			
		return chatMessage;
	}
}
