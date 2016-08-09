package com.sked.ofrestclient.entity;

import java.util.List;

//@XmlRootElement(name = "sessions")
public class SessionEntities {
	List<SessionEntity> sessions;

	public SessionEntities() {
	}

	public SessionEntities(List<SessionEntity> sessions) {
		this.sessions = sessions;
	}

	//@XmlElement(name = "session")
	public List<SessionEntity> getSessions() {
		return sessions;
	}

	public void setSessions(List<SessionEntity> sessions) {
		this.sessions = sessions;
	}
}
