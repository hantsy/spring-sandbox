package com.hantsylabs.example.conference.dao;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hantsylabs.example.conference.jpa.ConferenceRepository;
import com.hantsylabs.example.conference.jpa.spec.JpaPredicates;
import com.hantsylabs.example.conference.jpa.spec.QueryDslPredicates;
import com.hantsylabs.example.conference.model.Address;
import com.hantsylabs.example.conference.model.Conference;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/hantsylabs/example/conference/config/applicationContext-jpa.xml")
public class ConferencRepositoryImplTest {
	private static final Logger log = LoggerFactory
			.getLogger(ConferencRepositoryImplTest.class);

	@Autowired
	private ConferenceRepository conferenceRepository;

	@PersistenceContext
	EntityManager em;

	private Conference newConference() {
		Conference conf = new Conference();
		conf.setName("JUD2013");
		conf.setSlug("jud-2013");
		conf.setDescription("JBoss User Developer Conference 2013 Boston");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 30);

		Date startedDate = cal.getTime();

		conf.setStartedDate(startedDate);

		cal.add(Calendar.DAY_OF_YEAR, 7);

		Date endedDate = cal.getTime();
		conf.setEndedDate(endedDate);

		conf.setAddress(newAddress());

		log.debug("new conference object:" + conf);
		return conf;
	}

	private Address newAddress() {
		Address address = new Address();
		address.setAddressLine1("address line 1");
		address.setAddressLine2("address line 2");
		address.setCity("NY");
		address.setCountry("CN");
		address.setZipCode("510000");

		return address;
	}

	@BeforeClass
	public static void init() {
		log.debug("==================before class=========================");

	}

	@Before
	@Transactional
	public void beforeTestCase() {
		log.debug("==================before test case=========================");
		conferenceRepository.save(newConference());
	}

	@After
	@Transactional
	public void afterTestCase() {
		log.debug("==================after test case=========================");
		conferenceRepository.deleteAll();
	}

	@Test
	@Transactional
	public void retrieveConference() {
		Conference conference = newConference();
		conference.setSlug("test-jud");
		conference.setName("Test JUD");
		conference.getAddress().setCountry("US");
		conference = conferenceRepository.save(conference);
		em.flush();

		assertTrue(null != conference.getId());

		conference = conferenceRepository.findBySlug("test-jud");
		assertTrue(null != conference);

		List<Conference> confs = conferenceRepository
				.findByAddressCountry("US");
		assertTrue(!confs.isEmpty());

		confs = conferenceRepository.searchByConferenceName("Test JUD");
		assertTrue(!confs.isEmpty());

		confs = conferenceRepository.searchByNamedConferenceName("Test JUD");
		assertTrue(!confs.isEmpty());

		confs = conferenceRepository.searchByMyNamedQuery("Test JUD");
		assertTrue(!confs.isEmpty());

		confs = conferenceRepository.searchByDescription("Boston");
		assertTrue(!confs.isEmpty());

		confs = conferenceRepository.findByDescriptionLike("%Boston%");
		assertTrue(!confs.isEmpty());

	}

	@Test
	@Transactional
	public void retrieveConferenceByDate() {
		Conference conference = newConference();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		Date startedDate = cal.getTime();

		conference.setStartedDate(startedDate);

		cal.add(Calendar.DAY_OF_YEAR, 7);

		Date endedDate = cal.getTime();
		conference.setEndedDate(endedDate);
		conference = conferenceRepository.save(conference);
		em.flush();

		conference = newConference();

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);

		startedDate = cal.getTime();

		conference.setStartedDate(startedDate);

		cal.add(Calendar.DAY_OF_YEAR, 7);

		endedDate = cal.getTime();
		conference.setEndedDate(endedDate);
		conference = conferenceRepository.save(conference);
		em.flush();

		conference = newConference();

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 30);

		startedDate = cal.getTime();

		conference.setStartedDate(startedDate);

		cal.add(Calendar.DAY_OF_YEAR, 7);

		endedDate = cal.getTime();
		conference.setEndedDate(endedDate);
		conference = conferenceRepository.save(conference);
		em.flush();

		List<Conference> confs = conferenceRepository
				.findAll(JpaPredicates.inProgressConferences());
		assertTrue(confs.size() == 1);

		confs = conferenceRepository.findAll(JpaPredicates.pastConferences(null));
		assertTrue(confs.size() == 1);
		
		confs = conferenceRepository.findAll(JpaPredicates.upcomingConferences());
		assertTrue(confs.size() == 2);
	}

	
	
	@Test
	@Transactional
	public void retrieveConferenceByDateQueryDSL() {
		Conference conference = newConference();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		Date startedDate = cal.getTime();

		conference.setStartedDate(startedDate);

		cal.add(Calendar.DAY_OF_YEAR, 7);

		Date endedDate = cal.getTime();
		conference.setEndedDate(endedDate);
		conference = conferenceRepository.save(conference);
		em.flush();

		conference = newConference();

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);

		startedDate = cal.getTime();

		conference.setStartedDate(startedDate);

		cal.add(Calendar.DAY_OF_YEAR, 7);

		endedDate = cal.getTime();
		conference.setEndedDate(endedDate);
		conference = conferenceRepository.save(conference);
		em.flush();

		conference = newConference();

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 30);

		startedDate = cal.getTime();

		conference.setStartedDate(startedDate);

		cal.add(Calendar.DAY_OF_YEAR, 7);

		endedDate = cal.getTime();
		conference.setEndedDate(endedDate);
		conference = conferenceRepository.save(conference);
		em.flush();

		List<Conference> confs = (List<Conference>) conferenceRepository
				.findAll(QueryDslPredicates.inProgressConferences());
		assertTrue(confs.size() == 1);

		confs = (List<Conference>) conferenceRepository.findAll(QueryDslPredicates.pastConferences(null));
		assertTrue(confs.size() == 1);
		
		confs = (List<Conference>) conferenceRepository.findAll(QueryDslPredicates.upcomingConferences());
		assertTrue(confs.size() == 2);
	}

}
