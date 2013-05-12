package com.hantsylabs.example.conference.dao;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.hantsylabs.example.conference.model.Address;
import com.hantsylabs.example.conference.model.Conference;
import com.hantsylabs.example.conference.model.QConference;
import com.hantsylabs.example.conference.model.QSignup;
import com.hantsylabs.example.conference.model.Signup;
import com.hantsylabs.example.conference.model.Status;
import com.hantsylabs.example.conference.mongo.ConferenceRepository;
import com.hantsylabs.example.conference.mongo.SignupRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/hantsylabs/example/conference/config/applicationContext-mongo.xml")
public class ConferencRepositoryImplTest {
	private static final Logger log = LoggerFactory
			.getLogger(ConferencRepositoryImplTest.class);

	@Autowired
	private ConferenceRepository conferenceRepository;

	@Autowired
	private SignupRepository signupRepository;

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
	public void beforeTestCase() {
		log.debug("==================before test case=========================");
		conferenceRepository.save(newConference());
	}

	@After
	public void afterTestCase() {
		log.debug("==================after test case=========================");
		conferenceRepository.deleteAll();
	}

	@Test
	public void retrieveConference() {
		Conference conference = newConference();
		conference.setSlug("test-jud");
		conference.setName("Test JUD");

		conference = conferenceRepository.save(conference);

		assertTrue(null != conference.getId());

		conference = conferenceRepository.findBySlug("test-jud");
		assertTrue(null != conference);
		
		
		conference = conferenceRepository.findByAddressCountry("CN");
		log.debug("findByAddressCountry@"+conference);
		assertTrue(null != conference);

		Signup signup = newSignup();
		signup.setConference(conference);

		signupRepository.save(signup);

		assertTrue(null != signup.getId());

		List<Signup> signups = signupRepository.findByConference(conference);
		assertTrue(1 == signups.size());

		Signup signup2 = newSignup();
		signup2.setComment("another comments");
		signup2.setConference(conference);

		signupRepository.save(signup2);

		assertTrue(null != signup2.getId());

		List<Signup> signups2 = signupRepository.findByConference(conference);
		assertTrue(2 == signups2.size());

	}

	@Test
	public void retrieveConferenceByQueryDSL() {
		Conference conference = newConference();
		conference.setSlug("test-jud");
		conference.setName("Test JUD");

		conference = conferenceRepository.save(conference);
		log.debug("conference @" + conference);

		assertTrue(null != conference.getId());

		QConference qconf = QConference.conference;
		List<Conference> conferences = (List<Conference>) conferenceRepository
				.findAll(qconf.slug.eq("test-jud"));
		assertTrue(1 == conferences.size());

		List<Conference> conferences2 = (List<Conference>) conferenceRepository
				.findAll(qconf.address.country.eq("CN"));
		assertTrue(1 == conferences2.size());

		Signup signup = newSignup();
		signup.setConference(conference);

		signupRepository.save(signup);
		log.debug("signup @" + signup);

		assertTrue(null != signup.getId());

		QSignup qsignup = QSignup.signup;

		List<Signup> signups = (List<Signup>) signupRepository
				.findAll(qsignup.conference.eq(conference));
		log.debug("signups.size()@" + signups.size());

		assertTrue(1 == signups.size());

		Signup signup2 = newSignup();
		signup2.setComment("another comments");
		signup2.setConference(conference);

		signupRepository.save(signup2);

		assertTrue(null != signup2.getId());

		List<Signup> signups2 = (List<Signup>) signupRepository
				.findAll(qsignup.conference.eq(conference));
		assertTrue(2 == signups2.size());

		log.debug("signups2.size()@" + signups2.size());

	}
}
