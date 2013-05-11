package com.hantsylabs.example.conference.service;

import java.util.Date;
import java.util.List;

import com.hantsylabs.example.conference.model.Conference;
import com.hantsylabs.example.conference.model.Signup;

public interface ConferenceService {

	public abstract long countAllConferences();

	public abstract void deleteConference(Conference conference);

	public abstract Conference findConference(Long id);

	public abstract Conference findConferenceBySlug(String id);

	public abstract List<Conference> findAllConferences();

	public abstract List<Conference> findConferenceEntries(int firstResult,
			int maxResults);

	public abstract void saveConference(Conference conference);

	public abstract Conference updateConference(Conference conference);

	public abstract long countAllSignups();

	public abstract void deleteSignup(Signup signup);

	public abstract Signup findSignup(Long id);

	public abstract List<Signup> findAllSignups();

	public abstract List<Signup> findSignupEntries(int firstResult,
			int maxResults);

	public abstract void saveSignup(Signup signup);

	public abstract Signup updateSignup(Signup signup);

	public abstract List<Conference> findUpcomingConferences();

	public abstract List<Conference> findTodayConferences();

	public abstract List<Conference> findPastConferences(Date past);

}