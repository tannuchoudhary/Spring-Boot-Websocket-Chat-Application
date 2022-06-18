package com.bnymellon.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.bnymellon.constants.APIRoutes;


// https://www.mokkapps.de/blog/sending-message-to-specific-anonymous-user-on-spring-websocket/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	@Autowired
	UserHandshakeHandler userHandshakeHandler;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(APIRoutes.API.WEBSOCKETAPP)
				.setHandshakeHandler(userHandshakeHandler)
				.setAllowedOriginPatterns("*")
				.withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes(APIRoutes.API.APP);
	}
}
