package com.hantsylabs.example.conference.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hantsylabs.example.conference.model.Conference;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/hantsylabs/example/conference/dao/applicationContext-jdbc.xml")
public class HibernateConferenceDaoImplTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@BeforeClass
	public void init(){
		
	}

	@Test
	public void retrieveConference() {
		Session session = sessionFactory.openSession(); // not part of a
														// transaction, so we
														// need to open a
														// session manually
		Query query = session.createQuery("from Conference a where a.id=:id")
				.setInteger("id", 1);
		Conference a = (Conference) query.uniqueResult();
		session.close();

	}

	@Test
	@Transactional
	public void updateConference() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Conference a where a.id=:id")
				.setInteger("id", 1);
		Conference a = (Conference) query.uniqueResult();
		a.setName("foo");
	}

}
