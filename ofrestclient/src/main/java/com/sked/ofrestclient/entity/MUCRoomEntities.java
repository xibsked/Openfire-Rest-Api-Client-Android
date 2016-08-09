package com.sked.ofrestclient.entity;

import java.util.List;

//@XmlRootElement(name = "chatRooms")
public class MUCRoomEntities {
	List<MUCRoomEntity> mucRooms;

	public MUCRoomEntities() {
	}

	public MUCRoomEntities(List<MUCRoomEntity> mucRooms) {
		this.mucRooms = mucRooms;
	}

	//@XmlElement(name = "chatRoom")
	public List<MUCRoomEntity> getMucRooms() {
		return mucRooms;
	}

	public void setMucRooms(List<MUCRoomEntity> mucRooms) {
		this.mucRooms = mucRooms;
	}
}
