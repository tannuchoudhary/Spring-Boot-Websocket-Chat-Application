package com.bnymellon.constants;

// https://stackoverflow.com/questions/55282469/maintain-path-constants-for-restcontrollers
public final class APIRoutes {
	public class SYSTEM {
		public static final String HEALTH_CHECK = "/actuator/health";
	}

	public class API {
		public static final String CHAT_SEND = "/chat.send";
		public static final String CHAT_REGISTER = "/chat.register";
		public static final String CHAT_PRIVATE_USERNAME = "/chat.private";
		public static final String PRIVATE_CHAT_TOPIC_URL = "/topic/" + CommonConstants.PRIVATE_CHAT_TOPIC;
		
		public static final String USERS = "/users";
		public static final String ROLES = "/roles";
		public static final String ROLE_ADDTOUSER = "/role/addtouser";
		public static final String API = "/api";
		public static final String API_ROLES = "/api/roles";
		public static final String API_USERS = "/api/users";
		public static final String USERS_USERNAME = "/users/{username}";
		
		public static final String TOPIC = "/topic/";
		
		public static final String WEBSOCKETAPP = "/websocketApp";
		public static final String APP = "/app";
	}

	public class CACHE {
		public static final String RELOAD_X = "/actuator/caches";
	}
}
