package com.bnymellon.chat.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class UserSessionRepositoryImpl implements UserSessionRepository {
	private Map<String, String> dict = new HashMap<>();
	
	@Override
	public Optional<String> getSessionId(String username) {
		return Optional.ofNullable(dict.getOrDefault(username, null));
	}
	
	@Override
	public void setUserSession(String username, String sessionId) {
		if (dict.containsKey(username)) {
			dict.replace(username, sessionId);
		}else {
			dict.put(username, sessionId);
		}
	}
}
