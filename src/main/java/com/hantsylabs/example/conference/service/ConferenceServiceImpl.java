package com.hantsylabs.example.conference.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hantsylabs.example.conference.model.Conference;
import com.hantsylabs.example.conference.model.Signup;
import com.hantsylabs.example.conference.repository.ConferenceRepository;
import com.hantsylabs.example.conference.repository.SignupRepository;
import com.hantsylabs.example.conference.repository.specs.InProgressConferences;
import com.hantsylabs.example.conference.repository.specs.PastConferences;
import com.hantsylabs.example.conference.repository.specs.UpcomingConferences;

@Service
@Transactional
public class ConferenceServiceImpl implements ConferenceService {

	@Autowired
	ConferenceRepository conferenceRepository;

	@Autowired
	SignupRepository signupRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hantsylabs.example.conference.service.ConferenceService#
	 * countAllConferences()
	 */
	@Override
	public long countAllConferences() {
		return conferenceRepository.count();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#deleteConference
	 * (com.hantsylabs.example.conference.model.Conference)
	 */
	@Override
	public void deleteConference(Conference conference) {
		conferenceRepository.delete(conference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#findConference
	 * (java.lang.Long)
	 */
	@Override
	public Conference findConference(Long id) {
		return conferenceRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hantsylabs.example.conference.service.ConferenceService#
	 * findAllConferences()
	 */
	@Override
	public List<Conference> findAllConferences() {
		return conferenceRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hantsylabs.example.conference.service.ConferenceService#
	 * findConferenceEntries(int, int)
	 */
	@Override
	public List<Conference> findConferenceEntries(int firstResult,
			int maxResults) {
		return conferenceRepository.findAll(
				new org.springframework.data.domain.PageRequest(firstResult
						/ maxResults, maxResults)).getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#saveConference
	 * (com.hantsylabs.example.conference.model.Conference)
	 */
	@Override
	public void saveConference(Conference conference) {
		conferenceRepository.save(conference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#updateConference
	 * (com.hantsylabs.example.conference.model.Conference)
	 */
	@Override
	public Conference updateConference(Conference conference) {
		return conferenceRepository.save(conference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#countAllSignups
	 * ()
	 */
	@Override
	public long countAllSignups() {
		return signupRepository.count();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#deleteSignup
	 * (com.hantsylabs.example.conference.model.Signup)
	 */
	@Override
	public void deleteSignup(Signup signup) {
		signupRepository.delete(signup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#findSignup
	 * (java.lang.Long)
	 */
	@Override
	public Signup findSignup(Long id) {
		return signupRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#findAllSignups
	 * ()
	 */
	@Override
	public List<Signup> findAllSignups() {
		return signupRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#findSignupEntries
	 * (int, int)
	 */
	@Override
	public List<Signup> findSignupEntries(int firstResult, int maxResults) {
		return signupRepository.findAll(
				new org.springframework.data.domain.PageRequest(firstResult
						/ maxResults, maxResults)).getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#saveSignup
	 * (com.hantsylabs.example.conference.model.Signup)
	 */
	@Override
	public void saveSignup(Signup signup) {
		signupRepository.save(signup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hantsylabs.example.conference.service.ConferenceService#updateSignup
	 * (com.hantsylabs.example.conference.model.Signup)
	 */
	@Override
	public Signup updateSignup(Signup signup) {
		return signupRepository.save(signup);
	}

	@Override
	public List<Conference> findUpcomingConferences() {
		return conferenceRepository.findAll(new UpcomingConferences());
	}

	@Override
	public List<Conference> findTodayConferences() {
		return conferenceRepository.findAll(new InProgressConferences());
	}

	@Override
	public List<Conference> findPastConferences(Date past) {
		return conferenceRepository.findAll(new PastConferences(past));
	}

	@Override
	public Conference findConferenceBySlug(String id) {
		return conferenceRepository.findBySlug(id);
	}
}
