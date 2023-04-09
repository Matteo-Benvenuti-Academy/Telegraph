package com.Telegraph.models;

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
@Table(name = "chat_room")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomID")
	private Integer id;
	@Column
	private String roomname;
	@Column
	private String uniquecode;
	@Column
	private String roomdescription;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private List<Message> messages = new ArrayList<>();

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private List<RoomSubscription> subscriptions = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getUniquecode() {
		return uniquecode;
	}
	public void setUniquecode(String uniquecode) {
		this.uniquecode = uniquecode;
	}
	public String getRoomdescription() {
		return roomdescription;
	}
	public void setRoomdescription(String roomdescription) {
		this.roomdescription = roomdescription;
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
