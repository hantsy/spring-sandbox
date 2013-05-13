package com.hantsylabs.example.conference.mongo;

import java.util.List;

import com.hantsylabs.example.conference.model.Conference;

public interface ConferenceRepositoryCustom {
	public List<Conference> searchByDescription(String like);
	
	public void updateConferenceDescription(String description, String id );
}
