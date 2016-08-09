package com.sked.ofrestclient.entity;

import java.util.List;

//@XmlRootElement(name = "participants")
public class ParticipantEntities {
	List<ParticipantEntity> participants;

	public ParticipantEntities() {
	}

	public ParticipantEntities(List<ParticipantEntity> participants) {
		this.participants = participants;
	}

	//@XmlElement(name = "participant")
	public List<ParticipantEntity> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantEntity> participants) {
		this.participants = participants;
	}
}
