package com.hantsylabs.example.conference.jpa;

import java.util.List;

import com.hantsylabs.example.conference.model.Conference;

public interface ConferenceRepositoryCustom {
	List<Conference> searchByDescription(String like);
}
