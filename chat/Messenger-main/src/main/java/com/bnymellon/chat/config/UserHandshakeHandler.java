package com.bnymellon.chat.config;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.bnymellon.constants.CommonConstants;

import lombok.extern.slf4j.Slf4j;


// Comparison with UUIDs
// https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
@Slf4j
@Component
public class UserHandshakeHandler extends DefaultHandshakeHandler{
	
	@Override
	protected Principal determineUser(
			ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
		String randomId = UUID.randomUUID().toString();
		
		log.info(CommonConstants.NEW_USER_LOGGINED_WITH_ID + randomId);
		
		UserPrincipal user = () -> {
			return randomId;
		};
		
		return user;
	}
}
