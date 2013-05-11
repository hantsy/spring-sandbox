package com.hantsylabs.example.conference.repository.specs;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hantsylabs.example.conference.model.Conference;

public class PastConferences implements Specification<Conference> {

	private final Date past;

	public PastConferences(Date _past) {
		this.past = _past;
	}

	@Override
	public Predicate toPredicate(Root<Conference> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Expression<Timestamp> currentTimestamp = cb.currentTimestamp();
		if (past == null) {
			return cb.greaterThan(currentTimestamp,
					root.get("endedDate").as(Date.class));
		} else {
			return cb.and(
					cb.greaterThan(currentTimestamp,
							root.get("endedDate").as(Date.class)),
					cb.greaterThan(root.get("endedDate").as(Date.class), past));
		}
	}
}
