package com.hantsylabs.example.conference.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hantsylabs.example.conference.model.Conference;

public class Hibernate4ConferenceDaoImpl implements
		ConferenceDao {
	private static final Logger log = LoggerFactory
			.getLogger(Hibernate4ConferenceDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;
	
	Session session(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Conference findById(Long id) {
		return (Conference)session().load(Conference.class, id);

	}

	@Override
	public Long save(final Conference conference) {
		return (Long) session().save(conference);
	}

	@Override
	public void update(final Conference conference) {
		session().update(conference);
	}

	@Override
	public void delete(final Long id) {
		session().delete(
				session().get(Conference.class, id));
	}

}
