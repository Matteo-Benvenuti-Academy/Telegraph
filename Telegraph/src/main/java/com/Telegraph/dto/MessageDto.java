package com.Telegraph.dto;

import java.time.LocalDateTime;


public class MessageDto {
	
	private	LocalDateTime sendtime;

	private String messagetext;

	public LocalDateTime getSendtime() {
		return sendtime;
	}

	public void setSendtime(LocalDateTime sendtime) {
		this.sendtime = sendtime;
	}

	public String getMessagetext() {
		return messagetext;
	}

	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
	}
}
