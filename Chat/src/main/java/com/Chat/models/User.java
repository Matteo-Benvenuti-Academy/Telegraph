package com.Chat.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	private Integer id;
	@Column
	private String username;
	@Column
	private String pass;
	@Column
	private String email;
	@Column
	private Boolean removed;
	
	@Column
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Message> messages = new ArrayList<>();
	
	@Column
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<RoomSubscription> subscriptions = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getRemoved() {
		return removed;
	}
	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public List<RoomSubscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<RoomSubscription> subscriptions) {
		this.subscriptions = subscriptions;
	} 
	
}	
