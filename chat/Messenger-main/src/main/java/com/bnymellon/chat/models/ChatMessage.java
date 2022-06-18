package com.bnymellon.chat.models;

import lombok.Data;

@Data
public class ChatMessage {
	private String content;
	private String sender;
	private String receiver;
	private MessageType type;
	private String group;

	public enum MessageType {
		CHAT, LEAVE, JOIN
	}
}
