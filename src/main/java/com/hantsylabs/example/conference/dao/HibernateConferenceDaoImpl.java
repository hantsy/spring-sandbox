package com.hantsylabs.example.conference.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hantsylabs.example.conference.model.Conference;

public class HibernateConferenceDaoImpl extends HibernateDaoSupport implements
		ConferenceDao {
	private static final Logger log = LoggerFactory
			.getLogger(HibernateConferenceDaoImpl.class);

	@Override
	public Conference findById(Long id) {
		return getHibernateTemplate().load(Conference.class, id);

	}

	@Override
	public Long save(final Conference conference) {
		return (Long) getHibernateTemplate().save(conference);
	}

	@Override
	public void update(final Conference conference) {
		getHibernateTemplate().update(conference);
	}

	@Override
	public void delete(final Long id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().get(Conference.class, id));
	}

}
