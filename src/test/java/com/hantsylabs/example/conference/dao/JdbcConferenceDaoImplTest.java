package com.hantsylabs.example.conference.dao;

import junit.framework.Assert;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hantsylabs.example.conference.model.Conference;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/hantsylabs/example/conference/dao/applicationContext-jdbc.xml")
public class JdbcConferenceDaoImplTest {

	@Autowired
	private ConferenceDao conferenceDao;

	@BeforeClass
	public void init() {

	}

	@Test
	public void retrieveConference() {
		Conference conference = conferenceDao.findById(1L);	
		Assert.assertTrue("OSC2012".equals(conference.getName()));
	}

	@Test
	@Transactional
	public void updateConference() {

	}

}
