package com.hantsylabs.example.conference.dao;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hantsylabs.example.conference.jpa.ConferenceRepository;
import com.hantsylabs.example.conference.model.Conference;
import com.hantsylabs.example.conference.model.Contact;
import com.hantsylabs.example.conference.model.Signup;
import com.hantsylabs.example.conference.model.Status;
import com.hantsylabs.example.conference.mongo.ContactRepository;
import com.hantsylabs.example.conference.mongo.SignupRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/com/hantsylabs/example/conference/config/applicationContext-jpa.xml",
		"classpath:/com/hantsylabs/example/conference/config/applicationContext-mongo.xml" })
// @TransactionConfiguration(transactionManager = "transactionManager")
@TestExecutionListeners({ TransactionalTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class })
public class ConferenceCrossStoreImplTest {

	private static final Logger log = LoggerFactory
			.getLogger(ConferenceCrossStoreImplTest.class);

	@Autowired
	private ConferenceRepository conferenceRepository;

	@Autowired
	private SignupRepository signupRepository;

	@Autowired
	private ContactRepository contactRepository;
	
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

		log.debug("new conference object:" + conf);
		return conf;
	}

	private Signup newSignup() {
		Signup signup = new Signup();

		signup.setComment("test comments");
		signup.setCompany("TestCompany");
		signup.setCreatedDate(new Date());
		signup.setEmail("test@test.com");
		signup.setFirstName("Hantsy");
		signup.setLastName("Bai");
		signup.setOccupation("Developer");
		signup.setPhone("123 222 444");
		signup.setStatus(Status.PENDING);

		return signup;
	}

	@BeforeClass
	public static void init() {
		log.debug("==================before class=========================");

	}

	@Before
	@Transactional(propagation = Propagation.REQUIRED)
	public void beforeTestCase() {
		log.debug("==================before test case=========================");
		Conference conference = newConference();
		conference.setSlug("test-jud");
		conference.setName("Test JUD");
		Signup signup1 = newSignup();
		Signup signup2 = newSignup();

		signup2.setEmail("testanother@tom.com");

		conference.addSignup(signup1);
		conference.addSignup(signup2);
		conference.setContact(new Contact("Hantsy"));
		em.persist(conference);
		em.flush();

		Long id = conference.getId();

		em.clear();
	}

	@After
	@Transactional
	public void afterTestCase() {
		log.debug("==================after test case=========================");
		conferenceRepository.deleteAll();
	}

	@Test
	@Transactional
	public void saveContactSignup() {
		log.debug("==================enter saveContanct and Signup=========================");
		Contact contact = contactRepository.save(new Contact("Hantsy"));

		log.debug("saved contact @" + contact.getId());
		assertTrue(null != contact.getId());

		Signup singup = signupRepository.save(newSignup());
		log.debug("saved singup @" + singup.getId());
		assertTrue(null != singup.getId());

	}

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void retrieveConference() {
		log.debug("==================enter retrieveConference=========================");

		// assertTrue(null != id);

		Conference conf = em.find(Conference.class, 1L);

		log.debug("conf@@@" + conf);
		assertTrue(null != conf);

		// log.debug("contact id @@@" + conf.getContact().getId());
		// assertTrue(null!=conf.getContact().getId());

		/**
		 * Id is not assigned by Spring data mongo....why@
		 */
		assertTrue("Hantsy".equals(conf.getContact().getName()));

		log.debug("signup size @" + conf.getSignups().size());
		assertTrue(2 == conf.getSignups().size());
	}

}
